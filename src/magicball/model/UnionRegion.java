package magicball.model;

import java.util.Set;
import java.util.HashSet;


public class UnionRegion extends Region
{
	protected Set<Region> regions;

	public UnionRegion( Set<Region> regs ) {
		this.regions = regs;
	}

	public UnionRegion clone() {
		Set<Region> regs = new HashSet<Region>();
		for ( Region reg : regs ) {
			regs.add(reg.clone());
		}
		return new UnionRegion(regs);
	}

	public Set<Region> getRegions() {
		return regions;
	}

	public boolean inside( Vector v ) {
		for ( Region reg : getRegions() )
			if ( reg.inside(v) )
				return true;
		return false;
	}

	public void apply( Displacement dis ) {
		for ( Region reg : getRegions() )
			reg.apply(dis);
	}
}
