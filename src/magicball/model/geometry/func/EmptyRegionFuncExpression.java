package magicball.model.geometry.func;

import magicball.model.geometry.*;


class EmptyRegionFuncExpression extends RegionFuncExpression
{
	public EmptyRegionFuncExpression() {
	}

	final public int at( Number [] vec ) {
		return 1;
	}
}
