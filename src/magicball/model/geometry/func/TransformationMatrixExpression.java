package magicball.model.geometry.func;

import magicball.model.geometry.*;


public class TransformationMatrixExpression extends Transformation
{
	final private Number [][] rotation;
	final private Number [] shift;

	public TransformationMatrixExpression( Number [][] rot, Number [] sh ) {
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

