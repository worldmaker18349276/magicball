package magicball.model.geometry.affine;

import magicball.model.geometry.*;
import magicball.model.math.*;
import magicball.model.*;


public class TransformationBasicAttributeForMatrix implements ArbitraryTransformationBasicProperty.Attribute, SpecEngine<Transformation,AffineTransformationMatrixExpression>
{
	private ArbitraryFunctionBasicProperty.Creator funcCreator;
	private VectorEngine vecEngine;
	private MatrixEngine matEngine;

	public TransformationBasicAttributeForMatrix() {
		super();
	}

	public TransformationBasicAttributeForMatrix( ArbitraryFunctionBasicProperty.Creator funcC, VectorEngine vecEng, MatrixEngine matEng ) {
		super();
		setEngine(funcC);
		setEngine(vecEng);
		setEngine(matEng);
	}

	public void setEngine( ArbitraryFunctionBasicProperty.Creator funcC ) {
		funcCreator = funcC;
	}

	public void setEngine( VectorEngine vecEng ) {
		vecEngine = vecEng;
	}

	public void setEngine( MatrixEngine matEng ) {
		matEngine = matEng;
	}


	// attribute
	@Override
	public Number[] applyTo( Transformation trans, Number[] point ) {
		Number[][] mat = cast(trans).getMatrix();
		Number[] vec = cast(trans).getVector();
		return vecEngine.add(matEngine.matrixMultiply(mat,point),vec);
	}

	@Override
	public Function<Number[],Number[]> getTransformationFunction( Transformation trans ) {
		Number[][] mat = cast(trans).getMatrix();
		Number[] vec = cast(trans).getVector();
		return funcCreator.createFunctionByLambda(
			in -> vecEngine.add(matEngine.matrixMultiply(mat,in),vec)
		);
	}
}
