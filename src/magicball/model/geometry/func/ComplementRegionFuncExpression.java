package magicball.model.geometry.func;

import magicball.model.geometry.*;


class ComplementRegionFuncExpression extends RegionFuncExpression
{
	final private RegionFuncExpression region1;
	final private RegionFuncExpression region2;

	public ComplementRegionFuncExpression( RegionFuncExpression reg1, RegionFuncExpression reg2 ) {
		this.region1 = reg1;
		this.region2 = reg2;
	}

	final public int at( Number [] vec ) {
		if ( this.region2.at(vec) < 0 )
			return 1;
		return this.region1.at(vec);
	}
}
