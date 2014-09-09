package magicball.model.geometry.func;

import magicball.model.geometry.*;


public abstract class SurfaceFuncExpression extends Surface
{
	public abstract SurfaceFuncExpression clone();
	public abstract int at( Number [] vec );
}
