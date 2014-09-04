package magicball.model;

import java.util.Set;
import java.util.List;
import java.util.HashSet;


public class PhysicalPuzzle
{
	protected Set<Solid> components;


	public PhysicalPuzzle( Set<Solid> sols ) {
		setComponents(sols);
	}

	public Set<Solid> getComponents() {
		return this.components;
	}

	public void setComponents( Set<Solid> sols ) {
		this.components = sols;
	}

	public PhysicalPuzzle clone() {
		Set<Solid> sols = new HashSet<Solid>();
		for ( Solid sol : sols ) {
			sols.add(sol.clone());
		}
		return new PhysicalPuzzle(sols);
	}


	public boolean isValid() {
		// check no duplicate occupy
		Set<Solid> sols = getComponents();
		for ( Solid sol1 : sols ) {
			Region reg = sol1.getRegion();
			for ( Solid sol2 : sols ) {
				if ( sol1 != sol2 )
					if ( reg.at(sol2) != 1 )
						return false;
			}
		}
		return true;
		// TODO: more effecent algorithm
	}

	public boolean equals( Object puzzle ) {
		if ( puzzle instanceof PhysicalPuzzle )
			return equals((PhysicalPuzzle) puzzle);
		else
			return false;
	}

	public boolean equals( PhysicalPuzzle puzzle ) {
		return getComponents().equals(puzzle.getComponents());
	}

	public boolean apply( Transform trans ) {
		for ( Solid sol : getComponents() ) {
			sol.apply(trans);
		}
		return true;
	}

	public boolean apply( RegionalTransform rtrans ) {
		PhysicalPuzzle copy = clone();
		Set<Solid> selected_sols = rtrans.getRegion().filter(copy.getComponents());
		if ( selected_sols == null )
			return false;
		List<Transform> trans_list = rtrans.getTransform().getDividedTransform();
		for ( Transform trans : trans_list ) {
			for ( Solid sol : selected_sols )
				sol.apply(trans);
			if ( !copy.isValid() )
				return false;
		}
		setComponents(copy.getComponents());
		return true;
	}
}

