package magicball.model.puzzle.basic;

import magicball.model.puzzle.*;
import magicball.model.geometry.*;
import magicball.model.math.*;
import magicball.model.*;


public class BasicSolidEngineForRegion implements SolidBasicEngine, Engine<BasicSolidRegionExpression>
{
	protected RegionBasicEngine regEngine;

	public BasicSolidEngineForRegion( RegionBasicEngine regEng ) {
		this.regEngine = regEng;
	}

	public BasicSolidEngineForRegion( EngineProvider provider ) {
		this.regEngine = provider.getRegionEngine();
	}

	@Override
	public BasicSolidEngineForRegion clone() {
		return new BasicSolidEngineForRegion(this.regEngine);
	}


	// creater
	@Override
	public Solid createSolidByRegion( Region reg ) {
		return new BasicSolidRegionExpression(reg,this);
	}


	// attribute
	@Override
	public Region getOccupiedRegion( Solid sol_ ) {
		BasicSolidRegionExpression sol = cast(sol_);
		return sol.getRegion();
	}


	// operator
	@Override
	public void transformsBy( Solid sol_, Transformation trans ) {
		BasicSolidRegionExpression sol = cast(sol_);
		sol.setRegion(regEngine.transformsBy(sol.getRegion(),trans));
	}

	@Override
	public java.util.Set<Solid> filtersBy( java.util.Set<Solid> sols, Region reg ) throws IllegalOperationException {
		java.util.Set<Solid> selected_sols = new java.util.HashSet<Solid>();
		for ( Solid sol : sols )
			if ( regEngine.containsAll(reg,getOccupiedRegion(sol)) )
				selected_sols.add(sol);
		return selected_sols;
	}

	@Override
	public boolean noDuplicateOccupyIn( java.util.Set<Solid> sols ) {
		for ( Solid sol1 : sols )
			for ( Solid sol2 : sols )
				if ( sol1 != sol2 )
					if ( !regEngine.isEmpty(regEngine.intersect(getOccupiedRegion(sol1),getOccupiedRegion(sol2))) )
						return false;
		return true;
	}

	@Override
	public boolean areSameShape( Solid sol1, Solid sol2 ) {
		return regEngine.equals(getOccupiedRegion(sol1),getOccupiedRegion(sol2));
	}

	@Override
	public boolean equals( Solid sol1, Solid sol2 ) {
		return areSameShape(sol1,sol2);
	}
}
