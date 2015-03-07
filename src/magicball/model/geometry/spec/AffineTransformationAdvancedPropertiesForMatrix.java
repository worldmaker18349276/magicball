package magicball.model.geometry.spec;

import java.util.stream.Stream;
import java.util.Optional;

import magicball.model.geometry.*;
import magicball.model.math.*;
import magicball.model.*;


public class AffineTransformationAdvancedPropertiesForMatrix implements SpecEngine<Transformation,AffineTransformationMatrixExpression>,
		ArbitraryTransformationBasicProperty.Behavior,
		ArbitraryTransformationBasicProperty.Creator,
		ArbitraryTransformationBasicProperty.Attribute,
		ArbitraryTransformationBasicProperty.Operator,
		ArbitraryTransformationBasicProperty.Predicate,
		ArbitraryTransformationAdvancedProperty.Operator,
		AffineTransformationAdvancedProperty.Creator,
		AffineTransformationAdvancedProperty.Attribute,
		AffineTransformationAdvancedProperty.Predicate
{
	private NumberBasicEngine numEngine;
	private FunctionBasicEngine funcEngine;

	public AffineTransformationAdvancedPropertiesForMatrix() {
		super();
	}

	public AffineTransformationAdvancedPropertiesForMatrix( NumberBasicEngine numEng, FunctionBasicEngine funcEng ) {
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


	private Num[][] matrix( Transformation trans ) {
		return cast(trans).getMatrix();
	}

	private Num[] vector( Transformation trans ) {
		return cast(trans).getVector();
	}

	private Transformation trans( Num[][] mat, Num[] vec ) {
		return new AffineTransformationMatrixExpression(mat,vec);
	}

	private Transformation trans( Num[][] mat ) {
		return new AffineTransformationMatrixExpression(
			mat,
			numEngine.createZeroVectorWithDim(3));
	}

	private Transformation trans( Num[] vec ) {
		return new AffineTransformationMatrixExpression(
			numEngine.createIdentityMatrixWithDim(3),
			vec);
	}

	private Transformation trans() {
		return new AffineTransformationMatrixExpression(
			numEngine.createIdentityMatrixWithDim(3),
			numEngine.createZeroVectorWithDim(3));
	}

	private Num[] rotationMatrix2RotationVector( Num[][] rmat ) {
		Num one = numEngine.createOne();
		Num two = numEngine.createNumberByDouble(2);
		Num trace = numEngine.trace(rmat);
		Num cos = numEngine.over(numEngine.minus(trace, one), two);
		Num angle = numEngine.acos(cos);
		Num sin = numEngine.sin(angle);
		Num factor = numEngine.over(angle, numEngine.times(two, sin));
		Num[] axis = new Num [ 3 ];
		axis[0] = numEngine.minus(rmat[2][1],rmat[1][2]);
		axis[1] = numEngine.minus(rmat[0][2],rmat[2][0]);
		axis[2] = numEngine.minus(rmat[1][0],rmat[0][1]);
		return numEngine.times(axis, factor);
	}

	private Num[][] rotationVector2RotationMatrix( Num[] rvec ) {
		Num[] axis = numEngine.normalize(rvec);
		Num angle = numEngine.norm(rvec);
		Num cos = numEngine.cos(angle);
		Num sin = numEngine.sin(angle);
		Num versin = numEngine.minus(numEngine.createOne(), cos);
		Num[] sins = numEngine.times(axis,sin);

		Num[][] rmat = new Num [ 3 ][ 3 ];
		rmat[0][0] =  numEngine.plus(numEngine.times(axis[0],axis[0],versin), cos);
		rmat[1][1] =  numEngine.plus(numEngine.times(axis[1],axis[1],versin), cos);
		rmat[2][2] =  numEngine.plus(numEngine.times(axis[2],axis[2],versin), cos);
		rmat[1][0] =  numEngine.plus(numEngine.times(axis[0],axis[1],versin), sins[2]);
		rmat[2][1] =  numEngine.plus(numEngine.times(axis[1],axis[2],versin), sins[0]);
		rmat[0][2] =  numEngine.plus(numEngine.times(axis[2],axis[0],versin), sins[1]);
		rmat[0][1] = numEngine.minus(numEngine.times(axis[0],axis[1],versin), sins[2]);
		rmat[1][2] = numEngine.minus(numEngine.times(axis[1],axis[2],versin), sins[0]);
		rmat[2][0] = numEngine.minus(numEngine.times(axis[2],axis[0],versin), sins[1]);

		return rmat;
	}


	// behavior
	@Override
	public Num[] applyTo( Transformation trans, Num[] point ) {
		Num[][] mat = matrix(trans);
		Num[] vec = vector(trans);
		return numEngine.plus(numEngine.matrixMultiply(mat,point),vec);
	}


	// creater
	@Override
	public Transformation createTransformationByFunction( Func<Num[],Num[]> func ) {
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public Transformation createIdentityTransformation() {
		return trans();
	}

	@Override
	public Transformation createAffineTransformationByAugmentedMatrix( Num[][] mat ) {
		Num[][] m = numEngine.submatrixOf(mat,0,3,0,3);
		Num[][] v = numEngine.transpose(numEngine.submatrixOf(mat,0,3,3,4));
		return trans(m,v[0]);
	}

	@Override
	public Transformation createAffineTransformationByMatrixAndVector( Num[][] mat, Num[] vec ) {
		return trans(mat,vec);
	}

	@Override
	public Transformation createLinearTransformationByMatrix( Num[][] mat ) {
		return trans(mat);
	}

	@Override
	public Transformation createRotationByVector( Num[] rvec ) {
		return trans(rotationVector2RotationMatrix(rvec));
	}

	@Override
	public Transformation createReflectionByVector( Num[] fvec ) {
		Num[] nvec = numEngine.normalize(fvec);
		Num[][] nn = numEngine.matrixMultiply(numEngine.colVectorOf(nvec), numEngine.rowVectorOf(nvec));
		Num[][] mat = numEngine.minus(numEngine.createIdentityMatrixWithDim(3), numEngine.times(nn, numEngine.createNumberByDouble(2)));
		return trans(mat);
	}

	@Override
	public Transformation createTranslationByVector( Num[] sh ) {
		return trans(sh);
	}

	@Override
	public Transformation createScalingByFactor( Num factor ) {
		return trans(numEngine.times(numEngine.createIdentityMatrixWithDim(3),factor));
	}

	@Override
	public Transformation createShearingByOffsets( Num a, Num b ) {
		Num[][] mat = numEngine.createIdentityMatrixWithDim(3);
		mat[0][2] = a;
		mat[1][2] = b;
		return trans(mat);
	}


	// attribute
	@Override
	public Func<Num[],Num[]> getTransformationFunctionOf( Transformation trans ) {
		Num[][] mat = matrix(trans);
		Num[] vec = vector(trans);
		return funcEngine.createFunctionByLambda(
			in -> numEngine.plus(numEngine.matrixMultiply(mat,in),vec)
		);
	}

	@Override
	public Optional<Num[][]> getTransformationMatrixOf( Transformation trans_ ) {
		if ( isAffine(trans_) ) {
			return Optional.of(matrix(trans_));
		} else
			return Optional.<Num[][]>empty();
	}

	@Override
	public Optional<Num[]> getTranslationVectorOf( Transformation trans_ ) {
		if ( isAffine(trans_) ) {
			return Optional.of(vector(trans_));
		} else
			return Optional.<Num[]>empty();
	}

	@Override
	public Optional<Num[]> getRotationVectorOf( Transformation trans_ ) {
		if ( isRigid(trans_) )
			return Optional.of(rotationMatrix2RotationVector(matrix(trans_)));
		else
			return Optional.<Num[]>empty();
	}
	
	@Override
	public Optional<Num[]> getReflectionVectorOf( Transformation trans_ ) {
		if ( isIsometric(trans_) && !isRigid(trans_) )
			throw new UnsupportedAlgorithmException();
		else
			return Optional.<Num[]>empty();
	}


	// operator
	public Transformation compose( Transformation trans1, Transformation trans2 ) {
		Num[][] mat1 = matrix(trans1);
		Num[] vec1 = vector(trans1);
		Num[][] mat2 = matrix(trans2);
		Num[] vec2 = vector(trans2);
		Num[][] mat = numEngine.matrixMultiply(mat2,mat1);
		Num[] vec = numEngine.plus(vec2,numEngine.matrixMultiply(mat2,vec1));
		return trans(mat,vec);
	}

	@Override
	public Transformation compose( Transformation... trans ) {
		return Stream.of(trans)
			.reduce(this::compose)
			.orElseGet(this::createIdentityTransformation);
	}

	@Override
	public Transformation invert( Transformation trans ) {
		Num[][] mat = numEngine.invert(matrix(trans));
		Num[] vec = numEngine.negate(numEngine.matrixMultiply(mat,vector(trans)));
		return trans(mat,vec);
	}

	@Override
	public Transformation transformsBy( Transformation t, Transformation p ) {
		return compose(p, t, invert(p));
	}

	@Override
	public Transformation pow( Transformation trans, int exp ) {
		return Stream.generate(()->trans)
			.limit(exp)
			.reduce(this::compose)
			.orElseGet(this::createIdentityTransformation);
	}

	@Override
	public Transformation dividedBy( Transformation trans, Num divisor ) {
		// TODO: use arg to select number of turns
		if ( isIdentity(trans) ) {
			return trans;
		} else if ( isLinear(trans) && isRigid(trans) ) {

			Num[][] rot = matrix(trans);
			Num[] rvec = rotationMatrix2RotationVector(rot);
			rot = rotationVector2RotationMatrix(numEngine.over(rvec,divisor));
			return trans(rot);

		} else if ( isTranslation(trans) ) {

			Num[] sh = vector(trans);
			sh = numEngine.over(sh,divisor);
			return trans(sh);

		} else if ( isRigid(trans) ) { // only for int divisor

			divisor = numEngine.floor(divisor);
			int n = (int)numEngine.getDoubleValueOf(divisor);

			Num[][] rot = matrix(trans);
			Num[] sh = vector(trans);
			Num[] rvec = rotationMatrix2RotationVector(rot);
			Num[][] rot_n = rotationVector2RotationMatrix(numEngine.over(rvec,divisor));

			Num[][] m = numEngine.createIdentityMatrixWithDim(3);
			Num[][] rot_i = numEngine.createIdentityMatrixWithDim(3);
			for ( int i=1; i<n; i++ ) {
				rot_i = numEngine.matrixMultiply(rot_i,rot_n);
				m = numEngine.plus(m,rot_i);
			}
			Num[] sh_n = numEngine.matrixMultiply(numEngine.invert(m),sh);

			return trans(rot_n,sh_n);

		} else {
			throw new UnsupportedAlgorithmException();
		}
	}


	// predicate
	@Override
	public boolean equals( Transformation trans1, Transformation trans2 ) {
		return numEngine.equals(matrix(trans1),matrix(trans2)) &&
				numEngine.equals(vector(trans1),vector(trans2));
	}

	@Override
	public boolean isIdentity( Transformation trans ) {
		return numEngine.equals(matrix(trans),numEngine.createIdentityMatrixWithDim(3)) &&
				numEngine.equals(vector(trans),numEngine.createZeroVectorWithDim(3));
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
		return numEngine.equals(vector(trans),numEngine.createZeroVectorWithDim(3));
	}

	@Override
	public boolean isSimilar( Transformation trans ) {
		if ( trans instanceof AffineTransformationMatrixExpression ) {
			Num[][] mat = matrix(trans);
			return numEngine.equals(numEngine.invert(mat),numEngine.transpose(mat));
		} else
			throw new UnsupportedAlgorithmException();
	}

	@Override
	public boolean isIsometric( Transformation trans ) {
		if ( trans instanceof AffineTransformationMatrixExpression ) {
			if ( isSimilar(trans) ) {
				Num[][] mat = matrix(trans);
				Num det = numEngine.determinant(mat);
				return numEngine.equals(numEngine.abs(det),numEngine.createOne());
			} else
				return false;
		} else
			throw new UnsupportedAlgorithmException();
	}

	@Override
	public boolean isRigid( Transformation trans ) {
		if ( trans instanceof AffineTransformationMatrixExpression ) {
			if ( isSimilar(trans) ) {
				Num[][] mat = matrix(trans);
				Num det = numEngine.determinant(mat);
				return numEngine.equals(det,numEngine.createOne());
			} else
				return false;
		} else
			throw new UnsupportedAlgorithmException();
	}

	@Override
	public boolean isTranslation( Transformation trans ) {
		return numEngine.equals(matrix(trans),numEngine.createIdentityMatrixWithDim(3));
	}
}
