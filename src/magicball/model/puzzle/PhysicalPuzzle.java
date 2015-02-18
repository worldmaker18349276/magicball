package magicball.model.puzzle;

import magicball.model.geometry.*;


public class PhysicalPuzzle
{
	protected java.util.Set<Solid> components;
	protected PhysicalPuzzleBasicEngine engine;


	public PhysicalPuzzle( java.util.Set<Solid> sols, PhysicalPuzzleBasicEngine eng ) {
		setComponents(sols);
		setEngine(eng);
	}

	public java.util.Set<Solid> getComponents() {
		return this.components;
	}

	public void setComponents( java.util.Set<Solid> sols ) {
		this.components = sols;
	}

	public PhysicalPuzzleBasicEngine getEngine() {
		return this.engine;
	}

	public void setEngine( PhysicalPuzzleBasicEngine eng ) {
		this.engine = eng;
	}

	public PhysicalPuzzle clone() {
		java.util.Set<Solid> sols = new java.util.HashSet<Solid>();
		for ( Solid sol : getComponents() ) {
			sols.add(sol.clone());
		}
		return new PhysicalPuzzle(sols,getEngine());
	}


	public void applyBy( Motion move ) throws IllegalOperationException {
		engine.appliesBy(this,move);
	}

	public void applyBy( RegionalMotion rmove ) throws IllegalOperationException {
		engine.appliesBy(this,rmove);
	}

	public boolean equals( Object puzzle2 ) {
		if ( puzzle2 instanceof PhysicalPuzzle )
			return engine.equals(this,(PhysicalPuzzle)puzzle2);
		else
			return false;
	}

	public boolean isValid() {
		return engine.isValid(this);
	}
}

