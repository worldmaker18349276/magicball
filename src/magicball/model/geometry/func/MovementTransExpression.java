package magicball.model.geometry.func;

import magicball.model.geometry.*;


public abstract class MovementTransExpression < T extends Transformation > extends Movement
{
	public abstract MovementTransExpression clone();
	public abstract T getTransformation( Number from, Number to );
}

