package magicball.model.geometry.affine;

import magicball.model.geometry.*;
import magicball.model.math.*;
import magicball.model.*;


public class AffineTransformationEngineForMatrix implements TransformationAdvancedEngine, Engine<AffineTransformationMatrixExpression>
{
	protected NumberBasicEngine mathEngine;
	protected FunctionAdvancedEngine funcEngine;

	public AffineTransformationEngineForMatrix( NumberBasicEngine mathEng, FunctionAdvancedEngine funcEng ) {
		this.mathEngine = mathEng;
		this.funcEngine = funcEng;
	}

	public AffineTransformationEngineForMatrix( BasicEngineProvider provider ) {
		this.mathEngine = provider.getNumberEngine();
		this.funcEngine = provider.getFunctionEngine();
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
	public Transformation createTransformationByFunction( Function<Number[],Number[]> func ) {
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public Transformation createAffineTransformationByAugmentedMatrix( Number[][] mat ) {
		Number[][] m = mathEngine.submatrix(mat,0,3,0,3);
		Number[][] v = mathEngine.transpose(mathEngine.submatrix(mat,0,3,3,4));
		return new AffineTransformationMatrixExpression(m,v[0]);
	}

	@Override
	public Transformation createAffineTransformationByMatrixAndVector( Number[][] mat, Number[] vec ) {
		return new AffineTransformationMatrixExpression(mat,vec);
	}

	@Override
	public Transformation createLinearTransformationByMatrix( Number[][] mat ) {
		return new AffineTransformationMatrixExpression(mat,mathEngine.vector0(3));
	}

	@Override
	public Transformation createRotationByVector( Number[] rvec ) {
		return createLinearTransformationByMatrix(rotationVector2RotationMatrix(rvec));
	}

	@Override
	public Transformation createReflectionByVector( Number[] fvec ) {
		Number[] nvec = mathEngine.normalize(fvec);
		Number[][] nn = mathEngine.matrixMultiply(mathEngine.colVector(nvec), mathEngine.rowVector(nvec));
		Number[][] mat = mathEngine.subtract(mathEngine.matrix1(3), mathEngine.multiply(nn, mathEngine.number(2)));
		return createLinearTransformationByMatrix(mat);
	}

	@Override
	public Transformation createTranslationByVector( Number[] sh ) {
		return new AffineTransformationMatrixExpression(mathEngine.matrix1(3),sh);
	}

	@Override
	public Transformation createScalingByFactor( Number factor ) {
		return createLinearTransformationByMatrix(mathEngine.multiply(mathEngine.matrix1(3),factor));
	}

	@Override
	public Transformation createShearingByOffsets( Number a, Number b ) {
		Number[][] mat = mathEngine.matrix1(3);
		mat[0][2] = a;
		mat[1][2] = b;
		return createLinearTransformationByMatrix(mat);
	}

	@Override
	public Transformation createIdentityTransformation() {
		return new AffineTransformationMatrixExpression(mathEngine.matrix1(3),mathEngine.vector0(3));
	}


	// attribute
	@Override
	public Number[][] getTransformationMatrix( Transformation trans_ ) {
		if ( isAffine(trans_) ) {
			AffineTransformationMatrixExpression trans = cast(trans_);
			return trans.getMatrix();
		} else
			throw new ArithmeticException("this transformation is not affine");
	}

	@Override
	public Number[] getTranslationVector( Transformation trans_ ) {
		if ( isAffine(trans_) ) {
			AffineTransformationMatrixExpression trans = cast(trans_);
			return trans.getVector();
		} else
			throw new ArithmeticException("this transformation is not affine");
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
		if ( isRigid(trans) )
			return rotationMatrix2RotationVector(getTransformationMatrix(trans));
		else
			throw new ArithmeticException("this transformation is not rigid");
	}
	
	@Override
	public Number[] getReflectionVector( Transformation trans ) {
		if ( isIsometric(trans) && !isRigid(trans) )
			throw new UnsupportedAlgorithmException();
		else
			throw new ArithmeticException("this transformation is not reflection");
	}

	@Override
	public Number[] applies( Transformation trans, Number[] point ) {
		Number[][] mat = getTransformationMatrix(trans);
		Number[] vec = getTranslationVector(trans);
		return mathEngine.add(mathEngine.matrixMultiply(mat,point),vec);
	}


	// operator
	public Transformation compose( Transformation trans1, Transformation trans2 ) {
		Number[][] mat1 = getTransformationMatrix(trans1);
		Number[] vec1 = getTranslationVector(trans1);
		Number[][] mat2 = getTransformationMatrix(trans2);
		Number[] vec2 = getTranslationVector(trans2);
		Number[][] mat = mathEngine.matrixMultiply(mat2,mat1);
		Number[] vec = mathEngine.add(vec2,mathEngine.matrixMultiply(mat2,vec1));
		return new AffineTransformationMatrixExpression(mat,vec);
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
		Number[][] mat = mathEngine.invert33(getTransformationMatrix(trans));
		Number[] vec = mathEngine.negate(mathEngine.matrixMultiply(mat,getTranslationVector(trans)));
		return new AffineTransformationMatrixExpression(mat,vec);
	}

	@Override
	public Transformation dividedBy( Transformation trans, Number divisor ) {
		// TODO: use arg to select number of turns
		if ( isIdentity(trans) ) {
			return trans;
		} else if ( isLinear(trans) && isRigid(trans) ) {

			Number[][] rot = getTransformationMatrix(trans);
			Number[] rvec = rotationMatrix2RotationVector(rot);
			rot = rotationVector2RotationMatrix(mathEngine.dividedBy(rvec,divisor));
			return new AffineTransformationMatrixExpression(rot,mathEngine.vector0(3));

		} else if ( isTranslation(trans) ) {

			Number[] sh = getTranslationVector(trans);
			sh = mathEngine.dividedBy(sh,divisor);
			return new AffineTransformationMatrixExpression(mathEngine.matrix1(3),sh);

		} else if ( isRigid(trans) ) { // only for int divisor

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

		} else {
			throw new UnsupportedAlgorithmException();
		}
	}

	@Override
	public Transformation transformsBy( Transformation t, Transformation p ) {
		return compose(p, t, invert(p));
	}


	@Override
	public boolean isAffine( Transformation trans ) {
		if ( trans instanceof AffineTransformationMatrixExpression )
			return true;
		else
			throw new UnsupportedAlgorithmException();
	}

	@Override
	public boolean isLinear( Transformation trans ) {
		return mathEngine.equals(getTranslationVector(trans),mathEngine.vector0(3));
	}

	@Override
	public boolean isSimilar( Transformation trans ) {
		if ( trans instanceof AffineTransformationMatrixExpression ) {
			Number[][] mat = getTransformationMatrix(trans);
			return mathEngine.equals(mathEngine.invert33(mat),mathEngine.transpose(mat));
		} else
			throw new UnsupportedAlgorithmException();
	}

	@Override
	public boolean isIsometric( Transformation trans ) {
		if ( trans instanceof AffineTransformationMatrixExpression ) {
			if ( isSimilar(trans) ) {
				Number[][] mat = getTransformationMatrix(trans);
				Number det = mathEngine.determinant33(mat);
				return mathEngine.equals(mathEngine.abs(det),mathEngine.number1());
			} else
				return false;
		} else
			throw new UnsupportedAlgorithmException();
	}

	@Override
	public boolean isRigid( Transformation trans ) {
		if ( trans instanceof AffineTransformationMatrixExpression ) {
			if ( isSimilar(trans) ) {
				Number[][] mat = getTransformationMatrix(trans);
				Number det = mathEngine.determinant33(mat);
				return mathEngine.equals(det,mathEngine.number1());
			} else
				return false;
		} else
			throw new UnsupportedAlgorithmException();
	}

	@Override
	public boolean isTranslation( Transformation trans ) {
		return mathEngine.equals(getTransformationMatrix(trans),mathEngine.matrix1(3));
	}


	@Override
	public boolean isIdentity( Transformation trans ) {
		return mathEngine.equals(getTransformationMatrix(trans),mathEngine.matrix1(3)) &&
				mathEngine.equals(getTranslationVector(trans),mathEngine.vector0(3));
	}

	@Override
	public boolean equals( Transformation trans1, Transformation trans2 ) {
		return mathEngine.equals(getTransformationMatrix(trans1),getTransformationMatrix(trans2)) &&
				mathEngine.equals(getTranslationVector(trans1),getTranslationVector(trans2));
	}
}
