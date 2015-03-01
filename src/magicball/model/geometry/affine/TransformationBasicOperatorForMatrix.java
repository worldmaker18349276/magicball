package magicball.model.geometry.affine;

import magicball.model.geometry.*;
import magicball.model.math.*;
import magicball.model.*;


public class TransformationBasicOperatorForMatrix implements TransformationBasicOperator, SpecEngine<Transformation,AffineTransformationMatrixExpression>
{
	private TransformationBasicCreator transCreator;
	private FunctionBasicCreator funcCreator;
	private ScalarEngine scaEngine;
	private VectorEngine vecEngine;
	private MatrixEngine matEngine;

	public TransformationBasicOperatorForMatrix() {
		super();
	}

	public TransformationBasicOperatorForMatrix( TransformationBasicCreator transC, FunctionBasicCreator funcC, ScalarEngine scaEng, VectorEngine vecEng, MatrixEngine matEng ) {
		super();
		setEngine(transC);
		setEngine(funcC);
		setEngine(scaEng);
		setEngine(vecEng);
		setEngine(matEng);
	}

	public void setEngine( TransformationBasicCreator transC ) {
		transCreator = transC;
	}

	public void setEngine( FunctionBasicCreator funcC ) {
		funcCreator = funcC;
	}

	public void setEngine( ScalarEngine scaEng ) {
		scaEngine = scaEng;
	}

	public void setEngine( VectorEngine vecEng ) {
		vecEngine = vecEng;
	}

	public void setEngine( MatrixEngine matEng ) {
		matEngine = matEng;
	}


	// operator
	protected Number[] rotationMatrix2RotationVector( Number[][] rmat ) {
		Number one = scaEngine.number1();
		Number two = scaEngine.number(2);
		Number trace = matEngine.trace(rmat);
		Number cos = scaEngine.dividedBy(scaEngine.subtract(trace, one), two);
		Number angle = scaEngine.acos(cos);
		Number sin = scaEngine.sin(angle);
		Number factor = scaEngine.dividedBy(angle, scaEngine.multiply(two, sin));
		Number[] axis = new Number [ 3 ];
		axis[0] = scaEngine.subtract(rmat[2][1],rmat[1][2]);
		axis[1] = scaEngine.subtract(rmat[0][2],rmat[2][0]);
		axis[2] = scaEngine.subtract(rmat[1][0],rmat[0][1]);
		return vecEngine.multiply(axis, factor);
	}

	protected Number[][] rotationVector2RotationMatrix( Number[] rvec ) {
		Number[] axis = vecEngine.normalize(rvec);
		Number angle = vecEngine.norm(rvec);
		Number cos = scaEngine.cos(angle);
		Number sin = scaEngine.sin(angle);
		Number versin = scaEngine.subtract(scaEngine.number1(), cos);
		Number[] sins = vecEngine.multiply(axis,sin);

		Number[][] rmat = new Number [ 3 ][ 3 ];
		rmat[0][0] =      scaEngine.add(scaEngine.multiply(axis[0],axis[0],versin), cos);
		rmat[1][1] =      scaEngine.add(scaEngine.multiply(axis[1],axis[1],versin), cos);
		rmat[2][2] =      scaEngine.add(scaEngine.multiply(axis[2],axis[2],versin), cos);
		rmat[1][0] =      scaEngine.add(scaEngine.multiply(axis[0],axis[1],versin), sins[2]);
		rmat[2][1] =      scaEngine.add(scaEngine.multiply(axis[1],axis[2],versin), sins[0]);
		rmat[0][2] =      scaEngine.add(scaEngine.multiply(axis[2],axis[0],versin), sins[1]);
		rmat[0][1] = scaEngine.subtract(scaEngine.multiply(axis[0],axis[1],versin), sins[2]);
		rmat[1][2] = scaEngine.subtract(scaEngine.multiply(axis[1],axis[2],versin), sins[0]);
		rmat[2][0] = scaEngine.subtract(scaEngine.multiply(axis[2],axis[0],versin), sins[1]);

		return rmat;
	}


	public Transformation compose( Transformation trans1, Transformation trans2 ) {
		Number[][] mat1 = cast(trans1).getMatrix();
		Number[] vec1 = cast(trans1).getVector();
		Number[][] mat2 = cast(trans2).getMatrix();
		Number[] vec2 = cast(trans2).getVector();
		Number[][] mat = matEngine.matrixMultiply(mat2,mat1);
		Number[] vec = vecEngine.add(vec2,matEngine.matrixMultiply(mat2,vec1));
		return new AffineTransformationMatrixExpression(mat,vec);
	}

	@Override
	public Transformation compose( Transformation... trans ) {
		return Stream.of(trans)
			.reduce(this::compose)
			.get();
	}

	@Override
	public Transformation pow( Transformation trans, int exp ) {
		if ( exp >= 0 ) {

			return Stream.generate(()->trans)
				.limit(exp)
				.reduce(this::compose)
				.orElseGet(transCreator::createIdentityTransformation);

		} else {

			return pow(invert(trans),-exp);

		}
	}

	@Override
	public Transformation invert( Transformation trans ) {
		Number[][] mat = matEngine.invert33(cast(trans).getMatrix());
		Number[] vec = vecEngine.negate(vecEngine.matrixMultiply(mat,cast(trans).getVector()));
		return new AffineTransformationMatrixExpression(mat,vec);
	}

	@Override
	public Transformation dividedBy( Transformation trans, Number divisor ) {
		// TODO: use arg to select number of turns
		if ( isIdentity(trans) ) {
			return trans;
		} else if ( isLinear(trans) && isRigid(trans) ) {

			Number[][] rot = cast(trans).getMatrix();
			Number[] rvec = rotationMatrix2RotationVector(rot);
			rot = rotationVector2RotationMatrix(matEngine.dividedBy(rvec,divisor));
			return new AffineTransformationMatrixExpression(rot,vecEngine.vector0(3));

		} else if ( isTranslation(trans) ) {

			Number[] sh = cast(trans).getVector();
			sh = vecEngine.dividedBy(sh,divisor);
			return new AffineTransformationMatrixExpression(matEngine.matrix1(3),sh);

		} else if ( isRigid(trans) ) { // only for int divisor

			int n = divisor.intValue();

			Number[][] rot = cast(trans).getMatrix();
			Number[] sh = cast(trans).getVector();
			Number[] rvec = rotationMatrix2RotationVector(rot);
			Number[][] rot_n = rotationVector2RotationMatrix(vecEngine.dividedBy(rvec,divisor));

			Number[][] m = matEngine.matrix1(3);
			Number[][] rot_i = matEngine.matrix1(3);
			for ( int i=1; i<n; i++ ) {
				rot_i = matEngine.matrixMultiply(rot_i,rot_n);
				m = matEngine.add(m,rot_i);
			}
			Number[] sh_n = vecEngine.matrixMultiply(matEngine.invert33(m),sh);

			return new AffineTransformationMatrixExpression(rot_n,sh_n);

		} else {
			throw new UnsupportedAlgorithmException();
		}
	}

	@Override
	public Transformation transformsBy( Transformation t, Transformation p ) {
		return compose(p, t, invert(p));
	}
}
