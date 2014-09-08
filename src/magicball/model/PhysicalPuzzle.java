package magicball.model;

import java.util.Set;
import java.util.List;
import java.util.HashSet;


// physical puzzle abstraction layer
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
		for ( Solid sol : getComponents() ) {
			sols.add(sol.clone());
		}
		return new PhysicalPuzzle(sols);
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


	public boolean isValid() {
		return getComponents().iterator().next().noDuplicateOccupy(getComponents());
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

			Set<Solid> selected_sols = rtrans.getRegion().filter(getComponents());
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

