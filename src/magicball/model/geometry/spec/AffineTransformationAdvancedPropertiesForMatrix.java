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
	protected ArbitraryScalarBasicProperty.Creator scaCreator;
	protected ArbitraryScalarBasicProperty.Attribute scaAttribute;
	protected ArbitraryScalarBasicProperty.Operator scaOperator;
	protected ArbitraryScalarBasicProperty.Predicate scaPredicate;
	protected ArbitraryVectorBasicProperty.Creator vecCreator;
	protected ArbitraryVectorBasicProperty.Operator vecOperator;
	protected ArbitraryVectorBasicProperty.Predicate vecPredicate;
	protected ArbitraryMatrixBasicProperty.Creator matCreator;
	protected ArbitraryMatrixBasicProperty.Operator matOperator;
	protected ArbitraryMatrixBasicProperty.Predicate matPredicate;
	protected ArbitraryFunctionBasicProperty.Creator funcCreator;


	public AffineTransformationAdvancedPropertiesForMatrix() {
	}

	public <N extends ArbitraryScalarBasicProperty.Creator &
					ArbitraryScalarBasicProperty.Attribute &
					ArbitraryScalarBasicProperty.Operator &
					ArbitraryScalarBasicProperty.Predicate &
					ArbitraryVectorBasicProperty.Creator &
					ArbitraryVectorBasicProperty.Operator &
					ArbitraryVectorBasicProperty.Predicate &
					ArbitraryMatrixBasicProperty.Creator &
					ArbitraryMatrixBasicProperty.Operator &
					ArbitraryMatrixBasicProperty.Predicate> AffineTransformationAdvancedPropertiesForMatrix( N numEng, ArbitraryFunctionBasicProperty.Creator funcC ) {
		setEngine((ArbitraryScalarBasicProperty.Creator)numEng);
		setEngine((ArbitraryScalarBasicProperty.Attribute)numEng);
		setEngine((ArbitraryScalarBasicProperty.Operator)numEng);
		setEngine((ArbitraryScalarBasicProperty.Predicate)numEng);
		setEngine((ArbitraryVectorBasicProperty.Creator)numEng);
		setEngine((ArbitraryVectorBasicProperty.Operator)numEng);
		setEngine((ArbitraryVectorBasicProperty.Predicate)numEng);
		setEngine((ArbitraryMatrixBasicProperty.Creator)numEng);
		setEngine((ArbitraryMatrixBasicProperty.Operator)numEng);
		setEngine((ArbitraryMatrixBasicProperty.Predicate)numEng);
		setEngine(funcC);
	}

	public AffineTransformationAdvancedPropertiesForMatrix(
			ArbitraryScalarBasicProperty.Creator scaC,
			ArbitraryScalarBasicProperty.Attribute scaA,
			ArbitraryScalarBasicProperty.Operator scaO,
			ArbitraryScalarBasicProperty.Predicate scaP,
			ArbitraryVectorBasicProperty.Creator vecC,
			ArbitraryVectorBasicProperty.Operator vecO,
			ArbitraryVectorBasicProperty.Predicate vecP,
			ArbitraryMatrixBasicProperty.Creator matC,
			ArbitraryMatrixBasicProperty.Operator matO,
			ArbitraryMatrixBasicProperty.Predicate matP,
			ArbitraryFunctionBasicProperty.Creator funcC ) {
		setEngine(scaC);
		setEngine(scaA);
		setEngine(scaO);
		setEngine(scaP);
		setEngine(vecC);
		setEngine(vecO);
		setEngine(vecP);
		setEngine(matC);
		setEngine(matO);
		setEngine(matP);
		setEngine(funcC);
	}

	public void setEngine( ArbitraryScalarBasicProperty.Creator scaC ) {
		scaCreator = scaC;
	}

	public void setEngine( ArbitraryScalarBasicProperty.Attribute scaA ) {
		scaAttribute = scaA;
	}

	public void setEngine( ArbitraryScalarBasicProperty.Operator scaO ) {
		scaOperator = scaO;
	}

	public void setEngine( ArbitraryScalarBasicProperty.Predicate scaP ) {
		scaPredicate = scaP;
	}

	public void setEngine( ArbitraryVectorBasicProperty.Creator vecC ) {
		vecCreator = vecC;
	}

	public void setEngine( ArbitraryVectorBasicProperty.Operator vecO ) {
		vecOperator = vecO;
	}

	public void setEngine( ArbitraryVectorBasicProperty.Predicate vecP ) {
		vecPredicate = vecP;
	}

	public void setEngine( ArbitraryMatrixBasicProperty.Creator matC ) {
		matCreator = matC;
	}

	public void setEngine( ArbitraryMatrixBasicProperty.Operator matO ) {
		matOperator = matO;
	}

	public void setEngine( ArbitraryMatrixBasicProperty.Predicate matP ) {
		matPredicate = matP;
	}

	public void setEngine( ArbitraryFunctionBasicProperty.Creator funcC ) {
		funcCreator = funcC;
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
			vecCreator.createZeroVectorWithDim(3));
	}

	private Transformation trans( Num[] vec ) {
		return new AffineTransformationMatrixExpression(
			matCreator.createIdentityMatrixWithDim(3),
			vec);
	}

	private Transformation trans() {
		return new AffineTransformationMatrixExpression(
			matCreator.createIdentityMatrixWithDim(3),
			vecCreator.createZeroVectorWithDim(3));
	}

	private Num[] rotationMatrix2RotationVector( Num[][] rmat ) {
		Num one = scaCreator.createOne();
		Num two = scaCreator.createNumberByDouble(2);
		Num trace = matOperator.trace(rmat);
		Num cos = scaOperator.over(scaOperator.minus(trace, one), two);
		Num angle = scaOperator.acos(cos);
		Num sin = scaOperator.sin(angle);
		Num factor = scaOperator.over(angle, scaOperator.times(two, sin));
		Num[] axis = new Num [ 3 ];
		axis[0] = scaOperator.minus(rmat[2][1],rmat[1][2]);
		axis[1] = scaOperator.minus(rmat[0][2],rmat[2][0]);
		axis[2] = scaOperator.minus(rmat[1][0],rmat[0][1]);
		return vecOperator.times(axis, factor);
	}

	private Num[][] rotationVector2RotationMatrix( Num[] rvec ) {
		Num[] axis = vecOperator.normalize(rvec);
		Num angle = vecOperator.norm(rvec);
		Num cos = scaOperator.cos(angle);
		Num sin = scaOperator.sin(angle);
		Num versin = scaOperator.minus(scaCreator.createOne(), cos);
		Num[] sins = vecOperator.times(axis,sin);

		Num[][] rmat = new Num [ 3 ][ 3 ];
		rmat[0][0] =  scaOperator.plus(scaOperator.times(axis[0],axis[0],versin), cos);
		rmat[1][1] =  scaOperator.plus(scaOperator.times(axis[1],axis[1],versin), cos);
		rmat[2][2] =  scaOperator.plus(scaOperator.times(axis[2],axis[2],versin), cos);
		rmat[1][0] =  scaOperator.plus(scaOperator.times(axis[0],axis[1],versin), sins[2]);
		rmat[2][1] =  scaOperator.plus(scaOperator.times(axis[1],axis[2],versin), sins[0]);
		rmat[0][2] =  scaOperator.plus(scaOperator.times(axis[2],axis[0],versin), sins[1]);
		rmat[0][1] = scaOperator.minus(scaOperator.times(axis[0],axis[1],versin), sins[2]);
		rmat[1][2] = scaOperator.minus(scaOperator.times(axis[1],axis[2],versin), sins[0]);
		rmat[2][0] = scaOperator.minus(scaOperator.times(axis[2],axis[0],versin), sins[1]);

		return rmat;
	}


	// behavior
	@Override
	public Num[] applyTo( Transformation trans, Num[] point ) {
		Num[][] mat = matrix(trans);
		Num[] vec = vector(trans);
		return vecOperator.plus(matOperator.matrixMultiply(mat,point),vec);
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
		Num[][] m = matOperator.submatrixOf(mat,0,3,0,3);
		Num[][] v = matOperator.transpose(matOperator.submatrixOf(mat,0,3,3,4));
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
		Num[] nvec = vecOperator.normalize(fvec);
		Num[][] nn = matOperator.matrixMultiply(matOperator.colVectorOf(nvec), matOperator.rowVectorOf(nvec));
		Num[][] mat = matOperator.minus(matCreator.createIdentityMatrixWithDim(3), matOperator.times(nn, scaCreator.createNumberByDouble(2)));
		return trans(mat);
	}

	@Override
	public Transformation createTranslationByVector( Num[] sh ) {
		return trans(sh);
	}

	@Override
	public Transformation createScalingByFactor( Num factor ) {
		return trans(matOperator.times(matCreator.createIdentityMatrixWithDim(3),factor));
	}

	@Override
	public Transformation createShearingByOffsets( Num a, Num b ) {
		Num[][] mat = matCreator.createIdentityMatrixWithDim(3);
		mat[0][2] = a;
		mat[1][2] = b;
		return trans(mat);
	}


	// attribute
	@Override
	public Func<Num[],Num[]> getTransformationFunctionOf( Transformation trans ) {
		Num[][] mat = matrix(trans);
		Num[] vec = vector(trans);
		return funcCreator.createFunctionByLambda(
			in -> vecOperator.plus(matOperator.matrixMultiply(mat,in),vec)
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
		Num[][] mat = matOperator.matrixMultiply(mat2,mat1);
		Num[] vec = vecOperator.plus(vec2,matOperator.matrixMultiply(mat2,vec1));
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
		Num[][] mat = matOperator.invert(matrix(trans));
		Num[] vec = vecOperator.negate(matOperator.matrixMultiply(mat,vector(trans)));
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
			rot = rotationVector2RotationMatrix(vecOperator.over(rvec,divisor));
			return trans(rot);

		} else if ( isTranslation(trans) ) {

			Num[] sh = vector(trans);
			sh = vecOperator.over(sh,divisor);
			return trans(sh);

		} else if ( isRigid(trans) ) { // only for int divisor

			divisor = scaOperator.floor(divisor);
			int n = (int)scaAttribute.getDoubleValueOf(divisor);

			Num[][] rot = matrix(trans);
			Num[] sh = vector(trans);
			Num[] rvec = rotationMatrix2RotationVector(rot);
			Num[][] rot_n = rotationVector2RotationMatrix(vecOperator.over(rvec,divisor));

			Num[][] m = matCreator.createIdentityMatrixWithDim(3);
			Num[][] rot_i = matCreator.createIdentityMatrixWithDim(3);
			for ( int i=1; i<n; i++ ) {
				rot_i = matOperator.matrixMultiply(rot_i,rot_n);
				m = matOperator.plus(m,rot_i);
			}
			Num[] sh_n = matOperator.matrixMultiply(matOperator.invert(m),sh);

			return trans(rot_n,sh_n);

		} else {
			throw new UnsupportedAlgorithmException();
		}
	}


	// predicate
	@Override
	public boolean equals( Transformation trans1, Transformation trans2 ) {
		return matPredicate.equals(matrix(trans1),matrix(trans2)) &&
				vecPredicate.equals(vector(trans1),vector(trans2));
	}

	@Override
	public boolean isIdentity( Transformation trans ) {
		return matPredicate.equals(matrix(trans),matCreator.createIdentityMatrixWithDim(3)) &&
				vecPredicate.equals(vector(trans),vecCreator.createZeroVectorWithDim(3));
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
		return vecPredicate.equals(vector(trans),vecCreator.createZeroVectorWithDim(3));
	}

	@Override
	public boolean isSimilar( Transformation trans ) {
		if ( trans instanceof AffineTransformationMatrixExpression ) {
			Num[][] mat = matrix(trans);
			return matPredicate.equals(matOperator.invert(mat),matOperator.transpose(mat));
		} else
			throw new UnsupportedAlgorithmException();
	}

	@Override
	public boolean isIsometric( Transformation trans ) {
		if ( trans instanceof AffineTransformationMatrixExpression ) {
			if ( isSimilar(trans) ) {
				Num[][] mat = matrix(trans);
				Num det = matOperator.determinant(mat);
				return scaPredicate.equals(scaOperator.abs(det),scaCreator.createOne());
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
				Num det = matOperator.determinant(mat);
				return scaPredicate.equals(det,scaCreator.createOne());
			} else
				return false;
		} else
			throw new UnsupportedAlgorithmException();
	}

	@Override
	public boolean isTranslation( Transformation trans ) {
		return matPredicate.equals(matrix(trans),matCreator.createIdentityMatrixWithDim(3));
	}
}
