package magicball.model.geometry.affine;

import magicball.model.geometry.*;
import magicball.model.math.*;
import magicball.model.*;


public class AffineTransformationEngineForMatrix implements TransformationBasicEngine
{
	protected NumberBasicEngine mathEngine;
	protected FunctionBasicEngine funcEngine;

	public AffineTransformationEngineForMatrix( NumberBasicEngine mathEng, FunctionBasicEngine funcEng ) {
		this.mathEngine = mathEng;
		this.funcEngine = funcEng;
	}

	public AffineTransformationEngineForMatrix( EngineProvider provider ) {
		this.mathEngine = provider.getNumberEngine();
		this.funcEngine = provider.getFunctionEngine();
	}

	@Override
	public AffineTransformationEngineForMatrix clone() {
		return new AffineTransformationEngineForMatrix(this.mathEngine,this.funcEngine);
	}

	protected AffineTransformationMatrixExpression cast( Transformation trans ) {
		try {
			return (AffineTransformationMatrixExpression) trans;
		} catch ( ClassCastException e ) {
			throw new UnsupportedExpressionException(trans.getClass());
		}
	}

	protected Number[] rotationMatrix2RotationVector( Number[][] rmat ) {
		Number one = mathEngine.number1();
		Number two = mathEngine.number(2);
		Number trace = mathEngine.trace(rmat);
		Number cos = mathEngine.dividedBy(mathEngine.subtract(trace, one), two);
		Number angle = mathEngine.acos(cos);
		Number sin = mathEngine.sin(angle);
		Number factor = mathEngine.dividedBy(angle, mathEngine.multiply(two, sin));
		Number[] axis = new Number [ 3 ];
		axis[0] = mathEngine.subtract(rmat[2][1],rmat[1][2]);
		axis[1] = mathEngine.subtract(rmat[0][2],rmat[2][0]);
		axis[2] = mathEngine.subtract(rmat[1][0],rmat[0][1]);
		return mathEngine.multiply(axis, factor);
	}

	protected Number[][] rotationVector2RotationMatrix( Number[] rvec ) {
		Number[] axis = mathEngine.normalize(rvec);
		Number angle = mathEngine.norm(rvec);
		Number cos = mathEngine.cos(angle);
		Number sin = mathEngine.sin(angle);
		Number versin = mathEngine.subtract(mathEngine.number1(), cos);
		Number[] sins = mathEngine.multiply(axis,sin);

		Number[][] rmat = new Number [ 3 ][ 3 ];
		rmat[0][0] =      mathEngine.add(mathEngine.multiply(axis[0],axis[0],versin), cos);
		rmat[1][1] =      mathEngine.add(mathEngine.multiply(axis[1],axis[1],versin), cos);
		rmat[2][2] =      mathEngine.add(mathEngine.multiply(axis[2],axis[2],versin), cos);
		rmat[1][0] =      mathEngine.add(mathEngine.multiply(axis[0],axis[1],versin), sins[2]);
		rmat[2][1] =      mathEngine.add(mathEngine.multiply(axis[1],axis[2],versin), sins[0]);
		rmat[0][2] =      mathEngine.add(mathEngine.multiply(axis[2],axis[0],versin), sins[1]);
		rmat[0][1] = mathEngine.subtract(mathEngine.multiply(axis[0],axis[1],versin), sins[2]);
		rmat[1][2] = mathEngine.subtract(mathEngine.multiply(axis[1],axis[2],versin), sins[0]);
		rmat[2][0] = mathEngine.subtract(mathEngine.multiply(axis[2],axis[0],versin), sins[1]);

		return rmat;
	}


	// creater
	@Override
	public Transformation createRotationByVector( Number[] rvec ) {
		return new AffineTransformationMatrixExpression(rotationVector2RotationMatrix(rvec),mathEngine.vector0(3));
	}

	@Override
	public Transformation createTranslationByVector( Number[] sh ) {
		return new AffineTransformationMatrixExpression(mathEngine.matrix1(3),sh);
	}

	@Override
	public Transformation createIdentityTransformation() {
		return new AffineTransformationMatrixExpression(mathEngine.matrix1(3),mathEngine.vector0(3));
	}

	@Override
	public Transformation createReflectionByVector( Number[] fvec ) {
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public Transformation createTransformationByFunction( Function<Number[],Number[]> func ) {
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public Transformation createLinearTransformationByMatrix( Number[][] mat ) {
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public Transformation createScalingByFactor( Number factor ) {
		throw new UnsupportedAlgorithmException();
	}


	// attribute
	@Override
	public Number[][] getTransformationMatrix( Transformation trans_ ) {
		AffineTransformationMatrixExpression trans = cast(trans_);
		return trans.getRotationMatrix();
	}

	@Override
	public Number[] getTranslationVector( Transformation trans_ ) {
		AffineTransformationMatrixExpression trans = cast(trans_);
		return trans.getShiftVector();
	}

	@Override
	public Function<Number[],Number[]> getTransformationFunction( Transformation trans ) {
		Number[][] mat = getTransformationMatrix(trans);
		Number[] vec = getTranslationVector(trans);
		return this.funcEngine.createFunctionByLambda(
			in -> mathEngine.add(mathEngine.matrixMultiply(mat,in),vec)
		);
	}

	@Override
	public Number[] getRotationVector( Transformation trans ) {
		return rotationMatrix2RotationVector(getTransformationMatrix(trans));
	}
	
	@Override
	public Number[] getReflectionVector( Transformation trans ) {
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public Number getScalingFactor( Transformation trans ) {
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public Number[] applies( Transformation trans, Number[] point ) {
		Number[][] mat = getTransformationMatrix(trans);
		Number[] vec = getTranslationVector(trans);
		return mathEngine.add(mathEngine.matrixMultiply(mat,point),vec);
	}


	// operator
	public Transformation compose( Transformation trans1, Transformation trans2 ) {
		Number[][] rot = mathEngine.matrixMultiply(getTransformationMatrix(trans2),getTransformationMatrix(trans1));
		Number[] sh = mathEngine.add(getTranslationVector(trans2),mathEngine.matrixMultiply(getTransformationMatrix(trans2),getTranslationVector(trans1)));
		return new AffineTransformationMatrixExpression(rot,sh);
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
		Number[][] rot = mathEngine.transpose(getTransformationMatrix(trans));
		Number[] sh = mathEngine.negate(mathEngine.matrixMultiply(rot,getTranslationVector(trans)));
		return new AffineTransformationMatrixExpression(rot,sh);
	}

	@Override
	public Transformation dividedBy( Transformation trans, Number divisor ) {
		// TODO: use arg to select number of turns
		if ( isIdentity(trans) ) {
			return trans;
		} else if ( isRotation(trans) ) {

			Number[][] rot = getTransformationMatrix(trans);
			Number[] rvec = rotationMatrix2RotationVector(rot);
			rot = rotationVector2RotationMatrix(mathEngine.dividedBy(rvec,divisor));
			return new AffineTransformationMatrixExpression(rot,mathEngine.vector0(3));

		} else if ( isTranslation(trans) ) {

			Number[] sh = getTranslationVector(trans);
			sh = mathEngine.dividedBy(sh,divisor);
			return new AffineTransformationMatrixExpression(mathEngine.matrix1(3),sh);

		} else { // only for int divisor

			int n = divisor.intValue();

			Number[][] rot = getTransformationMatrix(trans);
			Number[] sh = getTranslationVector(trans);
			Number[] rvec = rotationMatrix2RotationVector(rot);
			Number[][] rot_n = rotationVector2RotationMatrix(mathEngine.dividedBy(rvec,divisor));

			Number[][] m = mathEngine.matrix1(3);
			Number[][] rot_i = mathEngine.matrix1(3);
			for ( int i=1; i<n; i++ ) {
				rot_i = mathEngine.matrixMultiply(rot_i,rot_n);
				m = mathEngine.add(m,rot_i);
			}
			Number[] sh_n = mathEngine.matrixMultiply(mathEngine.invert33(m),sh);

			return new AffineTransformationMatrixExpression(rot_n,sh_n);

		}
	}

	@Override
	public Transformation transformsBy( Transformation t, Transformation p ) {
		return compose(p, t, invert(p));
	}


	@Override
	public boolean isAffine( Transformation trans ) {
		if ( !(trans instanceof AffineTransformationMatrixExpression) )
			throw new UnsupportedAlgorithmException();
		return true;
	}

	@Override
	public boolean isLinear( Transformation trans ) {
		return mathEngine.equals(getTranslationVector(trans),mathEngine.vector0(3));
	}

	@Override
	public boolean isSimilar( Transformation trans ) {
		if ( !(trans instanceof AffineTransformationMatrixExpression) )
			throw new UnsupportedAlgorithmException();
		return true;
	}

	@Override
	public boolean isIsometric( Transformation trans ) {
		if ( !(trans instanceof AffineTransformationMatrixExpression) )
			throw new UnsupportedAlgorithmException();
		return true;
	}

	@Override
	public boolean isRigid( Transformation trans ) {
		if ( !(trans instanceof AffineTransformationMatrixExpression) )
			throw new UnsupportedAlgorithmException();
		return true;
	}


	@Override
	public boolean isIdentity( Transformation trans ) {
		return mathEngine.equals(getTransformationMatrix(trans),mathEngine.matrix1(3)) &&
				mathEngine.equals(getTranslationVector(trans),mathEngine.vector0(3));
	}

	@Override
	public boolean isRotation( Transformation trans ) {
		return mathEngine.equals(getTranslationVector(trans),mathEngine.vector0(3));
	}

	@Override
	public boolean isReflection( Transformation trans ) {
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public boolean isTranslation( Transformation trans ) {
		return mathEngine.equals(getTransformationMatrix(trans),mathEngine.matrix1(3));
	}

	@Override
	public boolean isScaling( Transformation trans ) {
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public boolean equals( Transformation trans1, Transformation trans2 ) {
		return mathEngine.equals(getTransformationMatrix(trans1),getTransformationMatrix(trans2)) &&
				mathEngine.equals(getTranslationVector(trans1),getTranslationVector(trans2));
	}
}
