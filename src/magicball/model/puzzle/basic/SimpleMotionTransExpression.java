package magicball.model.puzzle.basic;

import magicball.model.geometry.*;
import magicball.model.puzzle.*;


public class SimpleMotionTransExpression extends Motion
{
	protected Transformation transformation;

	public SimpleMotionTransExpression( Transformation trans ) {
		this.transformation = trans;
	}

	@Override
	public SimpleMotionTransExpression clone() {
		return new SimpleMotionTransExpression(this.transformation);
	}

	public Transformation getTransformation() {
		return this.transformation;
	}
}

