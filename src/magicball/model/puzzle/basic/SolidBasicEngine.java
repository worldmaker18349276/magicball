package magicball.model.puzzle.basic;

import magicball.model.puzzle.*;
import magicball.model.geometry.*;
import magicball.model.math.*;
import magicball.model.*;


public class SolidBasicEngine implements SolidEngine
{
	protected RegionEngine regEngine;

	public SolidBasicEngine( RegionEngine regEng ) {
		this.regEngine = regEng;
	}

	public SolidBasicEngine( EngineProvider provider ) {
		this.regEngine = provider.getRegionEngine();
	}

	@Override
	public SolidBasicEngine clone() {
		return new SolidBasicEngine(this.regEngine);
	}


	// creater
	@Override
	public Solid createSolidByRegion( Region reg ) {
		final SolidBasicEngine engine = this;
		return new Solid(reg) {
			@Override
			public boolean equals( Object sol2 ) {
				if ( sol2 instanceof Solid )
					return engine.equals(this,(Solid)sol2);
				else
					return false;
			}
		};
	}


	// operator
	@Override
	public void applies( Solid sol, Transformation trans ) {
		sol.setRegion(regEngine.transformsBy(sol.getRegion(),trans));
	}

	@Override
	public java.util.Set<Solid> filterBy( java.util.Set<Solid> sols, Region reg ) throws IllegalOperationException {
		java.util.Set<Solid> selected_sols = new java.util.HashSet<Solid>();
		for ( Solid sol : sols )
			if ( regEngine.containsAll(reg,sol.getRegion()) )
				selected_sols.add(sol);
		return selected_sols;
	}

	@Override
	public boolean noDuplicateOccupy( java.util.Set<Solid> sols ) {
		for ( Solid sol1 : sols )
			for ( Solid sol2 : sols )
				if ( sol1 != sol2 )
					if ( !regEngine.isEmpty(regEngine.intersect(sol1.getRegion(),sol2.getRegion())) )
						return false;
		return true;
	}

	@Override
	public boolean isSameShape( Solid sol1, Solid sol2 ) {
		return regEngine.equals(sol1.getRegion(),sol2.getRegion());
	}

	@Override
	public boolean equals( Solid sol1, Solid sol2 ) {
		return isSameShape(sol1,sol2);
	}
}
