package magicball.model.geometry.func;

import magicball.model.geometry.*;


class RegionBelowSurfaceFuncExpression extends RegionFuncExpression
{
	final private SurfaceFuncExpression surface;
	final private int side;

	public RegionBelowSurfaceFuncExpression( SurfaceFuncExpression surface, int side ) {
		this.surface = surface;
		this.side = side;
	}

	final public int at( Number [] vec ) {
		return -this.surface.at(vec)*this.side;
	}
}
