package magicball.model.puzzle.basic;

import magicball.model.geometry.*;
import magicball.model.puzzle.*;


public class SimpleMovementTransExpression extends Movement
{
	protected Transformation transformation;

	public SimpleMovementTransExpression( Transformation trans ) {
		this.transformation = trans;
	}

	@Override
	public SimpleMovementTransExpression clone() {
		return new SimpleMovementTransExpression(this.transformation);
	}

	public Transformation getTransformation() {
		return this.transformation;
	}
}

