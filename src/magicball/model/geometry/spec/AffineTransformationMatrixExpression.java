package magicball.model.geometry.spec;

import magicball.model.geometry.*;


public class AffineTransformationMatrixExpression extends Transformation
{
	final private Number[][] matrix;
	final private Number[] vector;

	public AffineTransformationMatrixExpression( Number[][] mat, Number[] vec ) {
		this.matrix = mat;
		this.vector = vec;
	}

	public Number[][] getMatrix() {
		return this.matrix;
	}

	public Number[] getVector() {
		return this.vector;
	}
}

