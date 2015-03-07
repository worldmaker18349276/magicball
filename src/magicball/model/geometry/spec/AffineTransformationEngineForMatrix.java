package magicball.model.geometry.spec;

import magicball.model.geometry.*;
import magicball.model.math.*;
import magicball.model.*;


public class AffineTransformationEngineForMatrix implements TransformationBasicEngine, SpecEngine<Transformation,AffineTransformationMatrixExpression>
{
	private NumberBasicEngine numEngine;
	private FunctionBasicEngine funcEngine;

	public AffineTransformationEngineForMatrix() {
		super();
	}

	public AffineTransformationEngineForMatrix( NumberBasicEngine numEng, FunctionBasicEngine funcEng ) {
		super();
		setEngine(numEng);
		setEngine(funcEng);
	}

	public void setEngine( NumberBasicEngine numEng ) {
		numEngine = numEng;
	}

	public void setEngine( FunctionBasicEngine funcEng ) {
		funcEngine = funcEng;
	}


	protected Number[] rotationMatrix2RotationVector( Number[][] rmat ) {
		Number one = numEngine.number1();
		Number two = numEngine.number(2);
		Number trace = numEngine.trace(rmat);
		Number cos = numEngine.dividedBy(numEngine.subtract(trace, one), two);
		Number angle = numEngine.acos(cos);
		Number sin = numEngine.sin(angle);
		Number factor = numEngine.dividedBy(angle, numEngine.multiply(two, sin));
		Number[] axis = new Number [ 3 ];
		axis[0] = numEngine.subtract(rmat[2][1],rmat[1][2]);
		axis[1] = numEngine.subtract(rmat[0][2],rmat[2][0]);
		axis[2] = numEngine.subtract(rmat[1][0],rmat[0][1]);
		return numEngine.multiply(axis, factor);
	}

	protected Number[][] rotationVector2RotationMatrix( Number[] rvec ) {
		Number[] axis = numEngine.normalize(rvec);
		Number angle = numEngine.norm(rvec);
		Number cos = numEngine.cos(angle);
		Number sin = numEngine.sin(angle);
		Number versin = numEngine.subtract(numEngine.number1(), cos);
		Number[] sins = numEngine.multiply(axis,sin);

		Number[][] rmat = new Number [ 3 ][ 3 ];
		rmat[0][0] =      numEngine.add(numEngine.multiply(axis[0],axis[0],versin), cos);
		rmat[1][1] =      numEngine.add(numEngine.multiply(axis[1],axis[1],versin), cos);
		rmat[2][2] =      numEngine.add(numEngine.multiply(axis[2],axis[2],versin), cos);
		rmat[1][0] =      numEngine.add(numEngine.multiply(axis[0],axis[1],versin), sins[2]);
		rmat[2][1] =      numEngine.add(numEngine.multiply(axis[1],axis[2],versin), sins[0]);
		rmat[0][2] =      numEngine.add(numEngine.multiply(axis[2],axis[0],versin), sins[1]);
		rmat[0][1] = numEngine.subtract(numEngine.multiply(axis[0],axis[1],versin), sins[2]);
		rmat[1][2] = numEngine.subtract(numEngine.multiply(axis[1],axis[2],versin), sins[0]);
		rmat[2][0] = numEngine.subtract(numEngine.multiply(axis[2],axis[0],versin), sins[1]);

		return rmat;
	}


	// creater
	@Override
	public Transformation createTransformationByFunction( Function<Number[],Number[]> func ) {
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public Transformation createAffineTransformationByAugmentedMatrix( Number[][] mat ) {
		Number[][] m = numEngine.submatrix(mat,0,3,0,3);
		Number[][] v = numEngine.transpose(numEngine.submatrix(mat,0,3,3,4));
		return new AffineTransformationMatrixExpression(m,v[0]);
	}

	@Override
	public Transformation createAffineTransformationByMatrixAndVector( Number[][] mat, Number[] vec ) {
		return new AffineTransformationMatrixExpression(mat,vec);
	}

	@Override
	public Transformation createLinearTransformationByMatrix( Number[][] mat ) {
		return new AffineTransformationMatrixExpression(mat,numEngine.vector0(3));
	}

	@Override
	public Transformation createRotationByVector( Number[] rvec ) {
		return createLinearTransformationByMatrix(rotationVector2RotationMatrix(rvec));
	}

	@Override
	public Transformation createReflectionByVector( Number[] fvec ) {
		Number[] nvec = numEngine.normalize(fvec);
		Number[][] nn = numEngine.matrixMultiply(numEngine.colVector(nvec), numEngine.rowVector(nvec));
		Number[][] mat = numEngine.subtract(numEngine.matrix1(3), numEngine.multiply(nn, numEngine.number(2)));
		return createLinearTransformationByMatrix(mat);
	}

	@Override
	public Transformation createTranslationByVector( Number[] sh ) {
		return new AffineTransformationMatrixExpression(numEngine.matrix1(3),sh);
	}

	@Override
	public Transformation createScalingByFactor( Number factor ) {
		return createLinearTransformationByMatrix(numEngine.multiply(numEngine.matrix1(3),factor));
	}

	@Override
	public Transformation createShearingByOffsets( Number a, Number b ) {
		Number[][] mat = numEngine.matrix1(3);
		mat[0][2] = a;
		mat[1][2] = b;
		return createLinearTransformationByMatrix(mat);
	}

	@Override
	public Transformation createIdentityTransformation() {
		return new AffineTransformationMatrixExpression(numEngine.matrix1(3),numEngine.vector0(3));
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
		return funcEngine.createFunctionByLambda(
			in -> numEngine.add(numEngine.matrixMultiply(mat,in),vec)
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
	public Number[] applyTo( Transformation trans, Number[] point ) {
		Number[][] mat = getTransformationMatrix(trans);
		Number[] vec = getTranslationVector(trans);
		return numEngine.add(numEngine.matrixMultiply(mat,point),vec);
	}


	// operator
	public Transformation compose( Transformation trans1, Transformation trans2 ) {
		Number[][] mat1 = getTransformationMatrix(trans1);
		Number[] vec1 = getTranslationVector(trans1);
		Number[][] mat2 = getTransformationMatrix(trans2);
		Number[] vec2 = getTranslationVector(trans2);
		Number[][] mat = numEngine.matrixMultiply(mat2,mat1);
		Number[] vec = numEngine.add(vec2,numEngine.matrixMultiply(mat2,vec1));
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
		Number[][] mat = numEngine.invert33(getTransformationMatrix(trans));
		Number[] vec = numEngine.negate(numEngine.matrixMultiply(mat,getTranslationVector(trans)));
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
			rot = rotationVector2RotationMatrix(numEngine.dividedBy(rvec,divisor));
			return new AffineTransformationMatrixExpression(rot,numEngine.vector0(3));

		} else if ( isTranslation(trans) ) {

			Number[] sh = getTranslationVector(trans);
			sh = numEngine.dividedBy(sh,divisor);
			return new AffineTransformationMatrixExpression(numEngine.matrix1(3),sh);

		} else if ( isRigid(trans) ) { // only for int divisor

			int n = divisor.intValue();

			Number[][] rot = getTransformationMatrix(trans);
			Number[] sh = getTranslationVector(trans);
			Number[] rvec = rotationMatrix2RotationVector(rot);
			Number[][] rot_n = rotationVector2RotationMatrix(numEngine.dividedBy(rvec,divisor));

			Number[][] m = numEngine.matrix1(3);
			Number[][] rot_i = numEngine.matrix1(3);
			for ( int i=1; i<n; i++ ) {
				rot_i = numEngine.matrixMultiply(rot_i,rot_n);
				m = numEngine.add(m,rot_i);
			}
			Number[] sh_n = numEngine.matrixMultiply(numEngine.invert33(m),sh);

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
		return numEngine.equals(getTranslationVector(trans),numEngine.vector0(3));
	}

	@Override
	public boolean isSimilar( Transformation trans ) {
		if ( trans instanceof AffineTransformationMatrixExpression ) {
			Number[][] mat = getTransformationMatrix(trans);
			return numEngine.equals(numEngine.invert33(mat),numEngine.transpose(mat));
		} else
			throw new UnsupportedAlgorithmException();
	}

	@Override
	public boolean isIsometric( Transformation trans ) {
		if ( trans instanceof AffineTransformationMatrixExpression ) {
			if ( isSimilar(trans) ) {
				Number[][] mat = getTransformationMatrix(trans);
				Number det = numEngine.determinant33(mat);
				return numEngine.equals(numEngine.abs(det),numEngine.number1());
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
				Number det = numEngine.determinant33(mat);
				return numEngine.equals(det,numEngine.number1());
			} else
				return false;
		} else
			throw new UnsupportedAlgorithmException();
	}

	@Override
	public boolean isTranslation( Transformation trans ) {
		return numEngine.equals(getTransformationMatrix(trans),numEngine.matrix1(3));
	}


	@Override
	public boolean isIdentity( Transformation trans ) {
		return numEngine.equals(getTransformationMatrix(trans),numEngine.matrix1(3)) &&
				numEngine.equals(getTranslationVector(trans),numEngine.vector0(3));
	}

	@Override
	public boolean equals( Transformation trans1, Transformation trans2 ) {
		return numEngine.equals(getTransformationMatrix(trans1),getTransformationMatrix(trans2)) &&
				numEngine.equals(getTranslationVector(trans1),getTranslationVector(trans2));
	}
}
