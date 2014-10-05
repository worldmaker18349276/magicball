package magicball.model.puzzle;

import magicball.model.geometry.*;


public class PhysicalPuzzle
{
	protected java.util.Set<Solid> components;
	protected PhysicalPuzzleEngine engine;


	public PhysicalPuzzle( java.util.Set<Solid> sols, PhysicalPuzzleEngine eng ) {
		setComponents(sols);
		setEngine(eng);
	}

	public java.util.Set<Solid> getComponents() {
		return this.components;
	}

	public void setComponents( java.util.Set<Solid> sols ) {
		this.components = sols;
	}

	public PhysicalPuzzleEngine getEngine() {
		return this.engine;
	}

	public void setEngine( PhysicalPuzzleEngine eng ) {
		this.engine = eng;
	}

	public PhysicalPuzzle clone() {
		java.util.Set<Solid> sols = new java.util.HashSet<Solid>();
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
		Transformation trans = ((magicball.basic.puzzle.SimpleMovementTransExpression) m).getTransformation();
		for ( Solid sol : getComponents() )
			getEngine().apply(sol,trans);
		// Transformation trans = getEngine().divideMovementByDivisor(m,1).get(1);
		// for ( Solid sol : getComponents() ) {
		// 	getEngine().apply(sol,trans);
		// }
	}

	public void apply( RegionalMovement rm ) throws IllegalOperationException {
		java.util.Set<Solid> selected_sols = getEngine().filter(getComponents(),rm.getRegion());
		Transformation trans = ((magicball.basic.puzzle.SimpleMovementTransExpression) rm.getMovement()).getTransformation();
		for ( Solid sol : selected_sols )
			getEngine().apply(sol,trans);
		// try {

			// java.util.Set<Solid> selected_sols = getEngine().filter(getComponents(),rm.getRegion());
			// java.util.List<Transformation> trans_list = getEngine().divideMovement(rm.getMovement());
			// for ( Transformation trans : trans_list ) {
			// 	for ( Solid sol : selected_sols )
			// 		getEngine().apply(sol,trans);
				// validate();
			// }

		// } catch ( IllegalStateException e ) {
		// 	throw new IllegalOperationException();
		// }
	}
}

