package magicball.model.geometry.func;

import magicball.model.geometry.*;


public class SolidRegionExpression < R extends Region > extends Solid
{
	final private R region;
	public SolidRegionExpression( R reg ) {
		this.region = reg;
	}

	final public SolidRegionExpression<R> clone() {
		return new SolidRegionExpression<R>(getOccupiedRegion());
	}

	final public R getOccupiedRegion() {
		return this.region;
	}
}
