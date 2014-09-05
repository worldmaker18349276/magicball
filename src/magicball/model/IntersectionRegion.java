package magicball.model;

import java.util.Set;
import java.util.HashSet;


public class IntersectionRegion extends Region
{
	protected Set<Region> regions;

	public IntersectionRegion( Set<Region> regs ) {
		this.regions = regs;
	}

	public IntersectionRegion clone() {
		Set<Region> regs = new HashSet<Region>();
		for ( Region reg : regs ) {
			regs.add(reg.clone());
		}
		return new IntersectionRegion(regs);
	}

	public Set<Region> getRegions() {
		return regions;
	}

	public boolean inside( Vector v ) {
		for ( Region reg : getRegions() )
			if ( !reg.inside(v) )
				return false;
		return true;
	}

	public void apply( Displacement dis ) {
		for ( Region reg : getRegions() )
			reg.apply(dis);
	}
}