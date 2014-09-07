package magicball.model;

import java.util.Set;
import java.util.List;
import java.util.HashSet;


// physical puzzle abstraction layer
public class PhysicalPuzzle
{
	protected Set<Solid> components;
	protected GeometryEngine engine;


	public PhysicalPuzzle( Set<Solid> sols, GeometryEngine eng ) {
		setComponents(sols);
		setEngine(eng);
	}

	public Set<Solid> getComponents() {
		return this.components;
	}

	public void setComponents( Set<Solid> sols ) {
		this.components = sols;
	}

	public GeometryEngine getEngine() {
		return this.engine;
	}

	public void setEngine( GeometryEngine eng ) {
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


	public void apply( Transform trans ) {
		Displacement dis = getEngine().divideIntoDisplacements(trans,1).get(1);
		for ( Solid sol : getComponents() ) {
			getEngine().apply(sol,dis);
		}
	}

	public void apply( RegionalTransform rtrans ) throws IllegalOperationException {
		try {

			Set<Solid> selected_sols = getEngine().filter(getComponents(),rtrans.getRegion());
			List<Displacement> dis_list = getEngine().divideIntoDisplacements(rtrans.getTransform());
			for ( Displacement dis : dis_list ) {
				for ( Solid sol : selected_sols )
					getEngine().apply(sol,dis);
				validate();
			}

		} catch ( IllegalStateException e ) {
			throw new IllegalOperationException();
		}
	}
}

