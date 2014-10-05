package magicball.model.geometry.poly;

import magicball.model.geometry.*;
import magicball.model.math.*;
import magicball.model.*;


public class TransformationBasicEngine implements TransformationEngine
{
	protected NumberEngine mathEngine;
	protected FunctionEngine funcEngine;

	public TransformationBasicEngine( NumberEngine mathEng, FunctionEngine funcEng ) {
		this.mathEngine = mathEng;
		this.funcEngine = funcEng;
	}

	public TransformationBasicEngine( EngineProvider provider ) {
		this.mathEngine = provider.getNumberEngine();
		this.funcEngine = provider.getFunctionEngine();
	}

	@Override
	public TransformationBasicEngine clone() {
		return new TransformationBasicEngine(this.mathEngine,this.funcEngine);
	}

	protected TransformationMatrixExpression cast( Transformation trans ) {
		try {
			return (TransformationMatrixExpression) trans;
		} catch ( ClassCastException e ) {
			throw new UnsupportedExpressionException(trans.getClass());
		}
	}

	protected Number[] rotationMatrix2RotationVector( Number[][] rmat ) {
		double angle = Math.acos((mathEngine.doubleValue(mathEngine.trace(rmat))-1)/2);
		double factor = angle/(2*Math.sin(angle));
		Number[] axis = new Number [ 3 ];
		axis[0] = mathEngine.subtract(rmat[2][1],rmat[1][2]);
		axis[1] = mathEngine.subtract(rmat[0][2],rmat[2][0]);
		axis[2] = mathEngine.subtract(rmat[1][0],rmat[0][1]);
		return mathEngine.multiply(axis,mathEngine.number(factor));
	}

	protected Number[][] rotationVector2RotationMatrix( Number[] rvec ) {
		double[] axis = mathEngine.doubleValue(mathEngine.normalize(rvec));
		double angle = mathEngine.doubleValue(mathEngine.norm(rvec));
		double cos = Math.cos(angle);
		double sin = Math.sin(angle);
		double versin = 1-cos;

		Number [][] rmat = new Number [ 3 ][ 3 ];
		rmat[0][0] = axis[0]*axis[0]*versin + cos;
		rmat[1][1] = axis[1]*axis[1]*versin + cos;
		rmat[2][2] = axis[2]*axis[2]*versin + cos;
		rmat[0][1] = axis[0]*axis[1]*versin - sin*axis[2];
		rmat[1][0] = axis[0]*axis[1]*versin + sin*axis[2];
		rmat[1][2] = axis[1]*axis[2]*versin - sin*axis[0];
		rmat[2][1] = axis[1]*axis[2]*versin + sin*axis[0];
		rmat[2][0] = axis[2]*axis[0]*versin - sin*axis[1];
		rmat[0][2] = axis[2]*axis[0]*versin + sin*axis[1];

		return rmat;
	}


	// creater
	@Override
	public Transformation createTransformationByVectors( Number[] rvec, Number[] sh ) {
		return new TransformationMatrixExpression(rotationVector2RotationMatrix(rvec),sh);
	}

	@Override
	public Transformation createRotationByVector( Number[] rvec ) {
		return new TransformationMatrixExpression(rotationVector2RotationMatrix(rvec),mathEngine.vector0(3));
	}

	@Override
	public Transformation createShiftByVector( Number[] sh ) {
		return new TransformationMatrixExpression(mathEngine.matrix1(3),sh);
	}

	@Override
	public Transformation createIdentityTransformation() {
		return new TransformationMatrixExpression(mathEngine.matrix1(3),mathEngine.vector0(3));
	}

	@Override
	public Reflection createReflectionByPlane( Surface plane ) {
		throw new UnsupportedAlgorithmException();
	}


	// attribute
	@Override
	public Number[][] getRotationMatrix( Transformation trans_ ) {
		TransformationMatrixExpression trans = cast(trans_);
		return trans.getRotationMatrix();
	}

	@Override
	public Number[] getShiftVector( Transformation trans_ ) {
		TransformationMatrixExpression trans = cast(trans_);
		return trans.getShiftVector();
	}

	@Override
	public Function<Number[],Number[]> getTransformationFunction( Transformation trans ) {
		final NumberEngine math = this.mathEngine;
		final Number[][] mat = getRotationMatrix(trans);
		final Number[] vec = getShiftVector(trans);
		return this.funcEngine.function(
			new LambdaFunction<Number[],Number[]>() {
				@Override
				public Number[] apply( Number[] in ) {
					return math.add(math.matrixMultiply(mat,in),vec);
				}
			}
		);
	}

	@Override
	public Function<Number[],Number[]> getReflectionFunction( Reflection ref ) {
		throw new UnsupportedAlgorithmException();
	}


	// operator
	public Transformation compose( Transformation trans1, Transformation trans2 ) {
		Number [][] rot = mathEngine.matrixMultiply(getRotationMatrix(trans2),getRotationMatrix(trans1));
		Number [] sh = mathEngine.add(getShiftVector(trans2),mathEngine.matrixMultiply(getRotationMatrix(trans2),getShiftVector(trans1)));
		return new TransformationMatrixExpression(rot,sh);
	}

	@Override
	public Transformation compose( Transformation... trans ) {
		Transformation result = trans[0];
		for ( int i=1; i<trans.length; i++ )
			result = compose(result,trans[i]);
		return result;
	}

	@Override
	public Transformation pow( Transformation trans, int exp ) {
		Transformation result = trans;
		for ( int i=1; i<exp; i++ )
			result = compose(result,trans);
		return result;
	}

	@Override
	public Transformation invert( Transformation trans ) {
		Number [][] rot = mathEngine.transpose(getRotationMatrix(trans));
		Number [] sh = mathEngine.negate(mathEngine.matrixMultiply(rot,getShiftVector(trans)));
		return new TransformationMatrixExpression(rot,sh);
	}

	@Override
	public Transformation dividedBy( Transformation trans, Number divisor ) {
		// TODO: use arg to select number of turns
		if ( isIdentity(trans) ) {
			return trans;
		} else if ( isRotation(trans) ) {

			Number[][] rot = getRotationMatrix(trans);
			Number [] rvec = rotationMatrix2RotationVector(rot);
			rot = rotationVector2RotationMatrix(mathEngine.dividedBy(rvec,divisor));
			return new TransformationMatrixExpression(rot,mathEngine.vector0(3));

		} else if ( isShift(trans) ) {

			Number[] sh = getShiftVector(trans);
			sh = mathEngine.dividedBy(sh,divisor);
			return new TransformationMatrixExpression(mathEngine.matrix1(3),sh);

		} else { // only for int divisor

			int n = divisor.intValue();

			Number[][] rot = getRotationMatrix(trans);
			Number[] sh = getShiftVector(trans);
			Number [] rvec = rotationMatrix2RotationVector(rot);
			Number[][] rot_n = rotationVector2RotationMatrix(mathEngine.dividedBy(rvec,divisor));

			Number[][] m = mathEngine.matrix1(3);
			Number[][] rot_i = mathEngine.matrix1(3);
			for ( int i=1; i<n; i++ ) {
				rot_i = mathEngine.matrixMultiply(rot_i,rot_n);
				m = mathEngine.add(m,rot_i);
			}
			Number[] sh_n = mathEngine.matrixMultiply(mathEngine.invert33(m),sh);

			return new TransformationMatrixExpression(rot_n,sh_n);

		}
	}

	@Override
	public boolean isIdentity( Transformation trans ) {
		return mathEngine.equals(getRotationMatrix(trans),mathEngine.matrix1(3)) &&
				mathEngine.equals(getShiftVector(trans),mathEngine.vector0(3));
	}

	@Override
	public boolean isRotation( Transformation trans ) {
		return mathEngine.equals(getShiftVector(trans),mathEngine.vector0(3));
	}

	@Override
	public boolean isShift( Transformation trans ) {
		return mathEngine.equals(getRotationMatrix(trans),mathEngine.matrix1(3));
	}

	@Override
	public boolean equals( Transformation trans1, Transformation trans2 ) {
		return mathEngine.equals(getRotationMatrix(trans1),getRotationMatrix(trans2)) &&
				mathEngine.equals(getShiftVector(trans1),getShiftVector(trans2));
	}
}
