package magicball.model;

import java.util.Set;
import java.util.List;
import java.util.HashSet;


public abstract class PhysicalPuzzle
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

	public abstract PhysicalPuzzle clone();

	public boolean equals( Object puzzle ) {
		if ( puzzle instanceof PhysicalPuzzle )
			return equals((PhysicalPuzzle) puzzle);
		else
			return false;
	}

	public boolean equals( PhysicalPuzzle puzzle ) {
		return getComponents().equals(puzzle.getComponents());
	}


	public abstract int at( Solid sol, Region reg );

	public Set<Solid> filter( Set<Solid> sols, Region reg ) throws IllegalOperationException {
		Set<Solid> selected_sols = new HashSet<Solid>();
		for ( Solid sol : sols ) {
			switch ( at(sol,reg) ) {
			case 0:
				throw new IllegalOperationException();
			case -1:
				selected_sols.add(sol);
			}
		}
		return selected_sols;
	}

	public boolean noDuplicateOccupy( Set<Solid> sols ) {
		for ( Solid sol1 : sols ) {
			Region reg = sol1.getRegion();
			for ( Solid sol2 : sols ) {
				if ( sol1 != sol2 )
					if ( at(sol2,reg) != 1 )
						return false;
			}
		}
		return true;
	}

	public boolean isValid() {
		return noDuplicateOccupy(getComponents());
	}

	public void validate() throws IllegalStateException {
		if ( !isValid() )
			throw new IllegalStateException();
	}


	public void apply( Transform trans ) {
		Displacement dis = trans.getDisplacement();
		for ( Solid sol : getComponents() ) {
			sol.apply(dis);
		}
	}

	public void apply( RegionalTransform rtrans ) throws IllegalOperationException {
		try {

			Set<Solid> selected_sols = filter(getComponents(),rtrans.getRegion());
			List<Displacement> dis_list = rtrans.getTransform().divideIntoDisplacements();
			for ( Displacement dis : dis_list ) {
				for ( Solid sol : selected_sols )
					sol.apply(dis);
				validate();
			}

		} catch ( IllegalStateException e ) {
			throw new IllegalOperationException();
		}
	}
}

