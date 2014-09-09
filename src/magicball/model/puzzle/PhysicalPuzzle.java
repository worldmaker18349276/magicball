package magicball.model.puzzle;

import magicball.model.geometry.*;
import java.util.Set;
import java.util.List;
import java.util.HashSet;


public class PhysicalPuzzle
{
	protected Set<Solid> components;
	protected PhysicalPuzzleEngine engine;


	public PhysicalPuzzle( Set<Solid> sols, PhysicalPuzzleEngine eng ) {
		setComponents(sols);
		setEngine(eng);
	}

	public Set<Solid> getComponents() {
		return this.components;
	}

	public void setComponents( Set<Solid> sols ) {
		this.components = sols;
	}

	public PhysicalPuzzleEngine getEngine() {
		return this.engine;
	}

	public void setEngine( PhysicalPuzzleEngine eng ) {
		this.engine = eng;
	}

	public PhysicalPuzzle clone() {
		Set<Solid> sols = new HashSet<Solid>();
		for ( Solid sol : getComponents() ) {
			sols.add(sol.clone());
		}
		return new PhysicalPuzzle(sols,getEngine().clone());
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
		return getEngine().noDuplicateOccupy(getComponents());
	}

	public void validate() throws IllegalStateException {
		if ( !isValid() )
			throw new IllegalStateException();
	}


	public void apply( Movement m ) {
		Transformation trans = getEngine().divideMovementByDivisor(m,1).get(1);
		for ( Solid sol : getComponents() ) {
			getEngine().apply(sol,trans);
		}
	}

	public void apply( RegionalMovement rm ) throws IllegalOperationException {
		try {

			Set<Solid> selected_sols = getEngine().filter(getComponents(),rm.getRegion());
			List<Transformation> trans_list = getEngine().divideMovement(rm.getMovement());
			for ( Transformation trans : trans_list ) {
				for ( Solid sol : selected_sols )
					getEngine().apply(sol,trans);
				validate();
			}

		} catch ( IllegalStateException e ) {
			throw new IllegalOperationException();
		}
	}
}

