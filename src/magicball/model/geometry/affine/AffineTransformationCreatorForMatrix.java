package magicball.model.geometry.affine;

import magicball.model.geometry.*;
import magicball.model.math.*;
import magicball.model.*;


public class AffineTransformationCreatorForMatrix implements AffineTransformationAdvanced.Creator, SpecEngine<Transformation,AffineTransformationMatrixExpression>
{
	private ScalarEngine scaEngine;
	private VectorEngine vecEngine;
	private MatrixEngine matEngine;

	public AffineTransformationCreatorForMatrix() {
		super();
	}

	public AffineTransformationCreatorForMatrix( ScalarEngine scaEng, VectorEngine vecEng, MatrixEngine matEng ) {
		super();
		setEngine(scaEng);
		setEngine(vecEng);
		setEngine(matEng);
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


	// creater
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

	@Override
	public Transformation createAffineTransformationByAugmentedMatrix( Number[][] mat ) {
		Number[][] m = matEngine.submatrix(mat,0,3,0,3);
		Number[][] v = matEngine.transpose(matEngine.submatrix(mat,0,3,3,4));
		return new AffineTransformationMatrixExpression(m,v[0]);
	}

	@Override
	public Transformation createAffineTransformationByMatrixAndVector( Number[][] mat, Number[] vec ) {
		return new AffineTransformationMatrixExpression(mat,vec);
	}

	@Override
	public Transformation createLinearTransformationByMatrix( Number[][] mat ) {
		return new AffineTransformationMatrixExpression(mat,vecEngine.vector0(3));
	}

	@Override
	public Transformation createRotationByVector( Number[] rvec ) {
		return createLinearTransformationByMatrix(rotationVector2RotationMatrix(rvec));
	}

	@Override
	public Transformation createReflectionByVector( Number[] fvec ) {
		Number[] nvec = vecEngine.normalize(fvec);
		Number[][] nn = matEngine.matrixMultiply(matEngine.colVector(nvec), matEngine.rowVector(nvec));
		Number[][] mat = matEngine.subtract(matEngine.matrix1(3), matEngine.multiply(nn, scaEngine.number(2)));
		return createLinearTransformationByMatrix(mat);
	}

	@Override
	public Transformation createTranslationByVector( Number[] sh ) {
		return new AffineTransformationMatrixExpression(matEngine.matrix1(3),sh);
	}

	@Override
	public Transformation createScalingByFactor( Number factor ) {
		return createLinearTransformationByMatrix(matEngine.multiply(matEngine.matrix1(3),factor));
	}

	@Override
	public Transformation createShearingByOffsets( Number a, Number b ) {
		Number[][] mat = matEngine.matrix1(3);
		mat[0][2] = a;
		mat[1][2] = b;
		return createLinearTransformationByMatrix(mat);
	}
}
