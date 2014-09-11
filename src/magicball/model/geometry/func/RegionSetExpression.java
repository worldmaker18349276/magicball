package magicball.model.geometry.func;

import magicball.model.geometry.*;
import magicball.model.math.*;


public class RegionSetExpression extends Region
{
	final private Set<Number[]> set;

	public RegionSetExpression( Set<Number[]> set ) {
		this.set = set;
	}

	final public Set<Number[]> getSet() {
		return this.set;
	}
}
