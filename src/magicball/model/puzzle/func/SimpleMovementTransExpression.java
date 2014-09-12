package magicball.model.puzzle.func;

import magicball.model.geometry.*;
import magicball.model.puzzle.*;


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

