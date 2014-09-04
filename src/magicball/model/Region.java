package magicball.model;

import java.util.Set;
import java.util.HashSet;


public abstract class Region implements Filter
{
	public < Sol > Set<Sol> filter( Set<Sol> sols ) {
		Set<Sol> selected_sols = new HashSet<Sol>();
		for ( Sol sol : sols ) {
			if ( sol instanceof Solid ) {
				switch ( at((Solid) sol) ) {
				case 0:
					return null;
				case -1:
					selected_sols.add(sol);
				}
			} else
				return null;
		}
		return selected_sols;
	}

	public abstract int at( Solid sol ); // 1: outside; -1: inside; 0: middle
}
