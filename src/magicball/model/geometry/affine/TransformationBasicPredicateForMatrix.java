package magicball.model.geometry.affine;

import magicball.model.geometry.*;
import magicball.model.math.*;
import magicball.model.*;


public class TransformationBasicPredicateForMatrix implements TransformationPredicate, SpecEngine<Transformation,AffineTransformationMatrixExpression>
{
	private VectorEngine vecEngine;
	private MatrixEngine matEngine;

	public TransformationBasicPredicateForMatrix() {
		super();
	}

	public TransformationBasicPredicateForMatrix( VectorEngine vecEng, MatrixEngine matEng ) {
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


	// predicate
	@Override
	public boolean isIdentity( Transformation trans ) {
		return matEngine.equals(cast(trans).getMatrix(),matEngine.matrix1(3)) &&
				vecEngine.equals(cast(trans).getVector(),vecEngine.vector0(3));
	}

	@Override
	public boolean equals( Transformation trans1, Transformation trans2 ) {
		return matEngine.equals(cast(trans1).getMatrix(),cast(trans2).getMatrix()) &&
				vecEngine.equals(cast(trans1).getVector(),cast(trans2).getVector());
	}
}
