package magicball.model.geometry.func;

import magicball.model.geometry.*;


public abstract class SolidRegionExpression < R extends Region > extends Solid
{
	public abstract R getOccupiedRegion();
}
