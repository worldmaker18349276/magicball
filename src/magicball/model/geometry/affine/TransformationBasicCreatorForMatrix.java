package magicball.model.geometry.affine;

import magicball.model.geometry.*;
import magicball.model.math.*;
import magicball.model.*;


public class TransformationBasicCreatorForMatrix implements TransformationCreator, SpecEngine<Transformation,AffineTransformationMatrixExpression>
{
	private VectorEngine vecEngine;
	private MatrixEngine matEngine;

	public TransformationBasicCreatorForMatrix() {
		super();
	}

	public TransformationBasicCreatorForMatrix( VectorEngine vecEng, MatrixEngine matEng ) {
		super();
		setEngine(vecEng);
		setEngine(matEng);
	}

	public void setEngine( VectorEngine vecEng ) {
		vecEngine = vecEng;
	}

	public void setEngine( MatrixEngine matEng ) {
		matEngine = matEng;
	}


	// creater
	@Override
	public Transformation createTransformationByFunction( Function<Number[],Number[]> func ) {
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public Transformation createIdentityTransformation() {
		return new AffineTransformationMatrixExpression(matEngine.matrix1(3),vecEngine.vector0(3));
	}
}
