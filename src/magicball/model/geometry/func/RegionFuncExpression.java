package magicball.model.geometry.func;

import magicball.model.geometry.*;


public abstract class RegionFuncExpression extends Region
{
	public abstract RegionFuncExpression clone();
	public abstract int at( Number [] vec );
}
