package magicball.model.geometry.spec;

import magicball.model.math.*;
import magicball.model.geometry.*;


public class AffineTransformationMatrixExpression extends Transformation
{
	final private Num[][] matrix;
	final private Num[] vector;

	public AffineTransformationMatrixExpression( Num[][] mat, Num[] vec ) {
		this.matrix = mat;
		this.vector = vec;
	}

	public Num[][] getMatrix() {
		return this.matrix;
	}

	public Num[] getVector() {
		return this.vector;
	}
}

