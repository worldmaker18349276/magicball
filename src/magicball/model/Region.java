package magicball.model;

import java.util.Set;
import java.util.HashSet;


public abstract class Region
{
	public abstract int at( Solid sol ); // 1: outside; -1: inside; 0: middle
	public abstract boolean inside( Vector v );

	public Set<Solid> filter( Set<Solid> sols ) throws IllegalOperationException {
		Set<Solid> selected_sols = new HashSet<Solid>();
		for ( Solid sol : sols ) {
			switch ( at(sol) ) {
			case 0:
				throw new IllegalOperationException();
			case -1:
				selected_sols.add(sol);
			}
		}
		return selected_sols;
	}
}
