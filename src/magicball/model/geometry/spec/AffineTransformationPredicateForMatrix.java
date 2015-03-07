package magicball.model.geometry.spec;

import magicball.model.geometry.*;
import magicball.model.math.*;
import magicball.model.*;


public class AffineTransformationPredicateForMatrix implements AffineTransformationAdvancedProperty.Predicate, SpecEngine<Transformation,AffineTransformationMatrixExpression>
{
	private ScalarEngine scaEngine;
	private VectorEngine vecEngine;
	private MatrixEngine matEngine;

	public AffineTransformationPredicateForMatrix() {
		super();
	}

	public AffineTransformationPredicateForMatrix( ScalarEngine scaEng, VectorEngine vecEng, MatrixEngine matEng ) {
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


	// predicate
	@Override
	public boolean isAffine( Transformation trans ) {
		if ( trans instanceof AffineTransformationMatrixExpression )
			return true;
		else
			throw new UnsupportedAlgorithmException();
	}

	@Override
	public boolean isLinear( Transformation trans ) {
		return vecEngine.equals(getTranslationVector(trans),vecEngine.vector0(3));
	}

	@Override
	public boolean isSimilar( Transformation trans ) {
		if ( trans instanceof AffineTransformationMatrixExpression ) {
			Number[][] mat = cast(trans).getMatrix();
			return matEngine.equals(matEngine.invert33(mat),matEngine.transpose(mat));
		} else
			throw new UnsupportedAlgorithmException();
	}

	@Override
	public boolean isIsometric( Transformation trans ) {
		if ( trans instanceof AffineTransformationMatrixExpression ) {
			if ( isSimilar(trans) ) {
				Number[][] mat = cast(trans).getMatrix;
				Number det = matEngine.determinant33(mat);
				return scaEngine.equals(scaEngine.abs(det),scaEngine.number1());
			} else
				return false;
		} else
			throw new UnsupportedAlgorithmException();
	}

	@Override
	public boolean isRigid( Transformation trans ) {
		if ( trans instanceof AffineTransformationMatrixExpression ) {
			if ( isSimilar(trans) ) {
				Number[][] mat = cast(trans).getMatrix();
				Number det = matEngine.determinant33(mat);
				return scaEngine.equals(det,scaEngine.number1());
			} else
				return false;
		} else
			throw new UnsupportedAlgorithmException();
	}

	@Override
	public boolean isTranslation( Transformation trans ) {
		return matEngine.equals(cast(trans).getMatrix(),matEngine.matrix1(3));
	}
}
