package magicball.model.geometry.affine;

import magicball.model.geometry.*;


public class AffineTransformationMatrixExpression extends Transformation
{
	final private Number [][] rotation;
	final private Number [] shift;

	public AffineTransformationMatrixExpression( Number [][] rot, Number [] sh ) {
		this.rotation = rot;
		this.shift = sh;
	}

	final public Number [][] getRotationMatrix() {
		return this.rotation;
	}

	final public Number [] getShiftVector() {
		return this.shift;
	}
}

