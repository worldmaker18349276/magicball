package magicball.model.geometry.spec;

import magicball.model.geometry.*;
import magicball.model.math.*;
import magicball.model.*;


public class AffineTransformationAttributeForMatrix implements AffineTransformationAdvancedProperty.Attribute, SpecEngine<Transformation,AffineTransformationMatrixExpression>
{
	private AffineTransformationAdvancedProperty.Predicate affPredicate;
	private ScalarEngine scaEngine;
	private VectorEngine vecEngine;
	private MatrixEngine matEngine;

	public AffineTransformationAttributeForMatrix() {
		super();
	}

	public AffineTransformationAttributeForMatrix( AffineTransformationAdvancedProperty.Predicate affPred, ScalarEngine scaEng, VectorEngine vecEng, MatrixEngine matEng ) {
		super();
		setEngine(affPred);
		setEngine(scaEng);
		setEngine(vecEng);
		setEngine(matEng);
	}

	public void setEngine( AffineTransformationAdvancedProperty.Predicate affPred ) {
		affPredicate = affPred;
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


	// attribute
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

	@Override
	public Number[][] getTransformationMatrix( Transformation trans_ ) {
		if ( affPredicate.isAffine(trans_) ) {
			AffineTransformationMatrixExpression trans = cast(trans_);
			return trans.getMatrix();
		} else
			throw new ArithmeticException("this transformation is not affine");
	}

	@Override
	public Number[] getRotationVector( Transformation trans ) {
		if ( affPredicate.isRigid(trans) )
			return rotationMatrix2RotationVector(getTransformationMatrix(trans));
		else
			throw new ArithmeticException("this transformation is not rigid");
	}
	
	@Override
	public Number[] getReflectionVector( Transformation trans ) {
		if ( affPredicate.isIsometric(trans) && !affPredicate.isRigid(trans) )
			throw new UnsupportedAlgorithmException();
		else
			throw new ArithmeticException("this transformation is not reflection");
	}

	@Override
	public Number[] getTranslationVector( Transformation trans_ ) {
		if ( affPredicate.isAffine(trans_) ) {
			AffineTransformationMatrixExpression trans = cast(trans_);
			return trans.getVector();
		} else
			throw new ArithmeticException("this transformation is not affine");
	}
}
