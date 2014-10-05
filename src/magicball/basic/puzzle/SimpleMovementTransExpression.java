package magicball.basic.puzzle;

import magicball.model.geometry.*;
import magicball.model.puzzle.*;


public class SimpleMovementTransExpression extends Movement
{
	final private Transformation transformation;

	public SimpleMovementTransExpression( Transformation trans ) {
		this.transformation = trans;
	}

	final public Transformation getTransformation() {
		return this.transformation;
	}
}

