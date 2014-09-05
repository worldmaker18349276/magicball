package magicball.model;

import java.util.Set;
import java.util.HashSet;


public abstract class UnionRegion extends Region
{
	public abstract Set<Region> getRegions();
	public abstract int at( Solid sol );

	public boolean inside( Vector v ) {
		for ( Region reg : getRegions() )
			if ( reg.inside(v) )
				return true;
		return false;
	}
}
