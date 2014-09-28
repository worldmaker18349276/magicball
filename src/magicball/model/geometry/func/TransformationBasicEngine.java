package magicball.model.geometry.func;

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

	public Transformation createIdentityTransformation() {
		return new TransformationMatrixExpression(mathEngine.matrix1(3),mathEngine.vector0(3));
	}

	public Transformation createTransformationByVectors( Number[] rvec, Number[] sh ) {
		return new TransformationMatrixExpression(rotationVector2RotationMatrix(rvec),sh);
	}

	public Transformation createRotationByVector( Number[] rvec ) {
		return new TransformationMatrixExpression(rotationVector2RotationMatrix(rvec),mathEngine.vector0(3));
	}

	public Transformation createShiftByVector( Number[] sh ) {
		return new TransformationMatrixExpression(mathEngine.matrix1(3),sh);
	}

	public Transformation compose( Transformation trans1_, Transformation trans2_ ) {
		TransformationMatrixExpression trans1 = cast(trans1_);
		TransformationMatrixExpression trans2 = cast(trans2_);
		Number [][] rot = mathEngine.matrixMultiply(trans2.getRotationMatrix(),trans1.getRotationMatrix());
		Number [] sh = mathEngine.add(trans2.getShiftVector(),mathEngine.matrixMultiply(trans2.getRotationMatrix(),trans1.getShiftVector()));
		return new TransformationMatrixExpression(rot,sh);
	}

	public Transformation compose( Transformation... trans ) {
		Transformation result = trans[0];
		for ( int i=1; i<trans.length; i++ )
			result = compose(result,trans[i]);
		return result;
	}

	public Transformation pow( Transformation trans, int exp ) {
		Transformation result = cast(trans);
		for ( int i=1; i<exp; i++ )
			result = compose(result,trans);
		return result;
	}

	public Transformation invert( Transformation trans_ ) {
		TransformationMatrixExpression trans = cast(trans_);
		Number [][] rot = mathEngine.transpose(trans.getRotationMatrix());
		Number [] sh = mathEngine.negate(mathEngine.matrixMultiply(rot,trans.getShiftVector()));
		return new TransformationMatrixExpression(rot,sh);
	}

	public Transformation dividedBy( Transformation trans_, Number divisor ) {
		// TODO: use arg to select number of turns
		TransformationMatrixExpression trans = cast(trans_);
		if ( isIdentity(trans) ) {
			return trans;
		} else if ( isRotation(trans) ) {

			Number[][] rot = trans.getRotationMatrix();
			Number [] rvec = rotationMatrix2RotationVector(rot);
			rot = rotationVector2RotationMatrix(mathEngine.dividedBy(rvec,divisor));
			return new TransformationMatrixExpression(rot,mathEngine.vector0(3));

		} else if ( isShift(trans) ) {

			Number[] sh = trans.getShiftVector();
			sh = mathEngine.dividedBy(sh,divisor);
			return new TransformationMatrixExpression(mathEngine.matrix1(3),sh);

		} else { // only for int divisor

			int n = divisor.intValue();

			Number[][] rot = trans.getRotationMatrix();
			Number[] sh = trans.getShiftVector();
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

	public boolean isIdentity( Transformation trans ) {
		return mathEngine.equals(cast(trans).getRotationMatrix(),mathEngine.matrix1(3)) &&
				mathEngine.equals(cast(trans).getShiftVector(),mathEngine.vector0(3));
	}

	public boolean isRotation( Transformation trans ) {
		return mathEngine.equals(cast(trans).getShiftVector(),mathEngine.vector0(3));
	}

	public boolean isShift( Transformation trans ) {
		return mathEngine.equals(cast(trans).getRotationMatrix(),mathEngine.matrix1(3));
	}

	public boolean equals( Transformation trans1, Transformation trans2 ) {
		return mathEngine.equals(cast(trans1).getRotationMatrix(),cast(trans2).getRotationMatrix()) &&
				mathEngine.equals(cast(trans1).getShiftVector(),cast(trans2).getShiftVector());
	}

	public Function<Number[],Number[]> createTransformationFunction( Transformation trans_ ) {
		final NumberEngine math = this.mathEngine;
		final TransformationMatrixExpression trans = cast(trans_);
		return this.funcEngine.createFunctionByLambda(
			new LambdaFunction<Number[],Number[]>() {
				public Number[] apply( Number[] in ) {
					return math.add(math.matrixMultiply(trans.getRotationMatrix(),in),trans.getShiftVector());
				}
			}
		);
	}

	public Reflection createReflectionByPlane( Surface plane ) {
		throw new UnsupportedAlgorithmException();
	}
	public Function<Number[],Number[]> createReflectionFunction( Reflection ref ) {
		throw new UnsupportedAlgorithmException();
	}
}
