package magicball.model.geometry.func;

import magicball.model.geometry.*;


public class SimpleMovementTransExpression < T extends Transformation > extends Movement
{
	final private T transformation;

	public SimpleMovementTransExpression( T trans ) {
		this.transformation = trans;
	}

	final public T getTransformation() {
		return this.transformation;
	}
}

