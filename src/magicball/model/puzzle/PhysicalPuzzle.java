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


	public void apply( Motion move ) throws IllegalOperationException {
		engine.applies(this,move);
	}

	public void apply( RegionalMotion rmove ) throws IllegalOperationException {
		engine.applies(this,rmove);
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

