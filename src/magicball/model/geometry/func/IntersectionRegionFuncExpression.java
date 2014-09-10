package magicball.model.geometry.func;

import magicball.model.geometry.*;


class IntersectionRegionFuncExpression extends RegionFuncExpression
{
	final private RegionFuncExpression [] regions;

	public IntersectionRegionFuncExpression( RegionFuncExpression... regs ) {
		this.regions = regs;
	}

	final public int at( Number [] vec ) {
		for ( RegionFuncExpression reg : this.regions )
			if ( reg.at(vec) > 0 )
				return 1;
		return -1;
	}
}
