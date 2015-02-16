package magicball.model.puzzle.basic;

import magicball.model.puzzle.*;
import magicball.model.geometry.*;
import magicball.model.math.*;
import magicball.model.*;


public class BasicSolidEngineForRegion implements SolidBasicEngine
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

	protected BasicSolidRegionExpression cast( Solid sol ) {
		try {
			return (BasicSolidRegionExpression) sol;
		} catch ( ClassCastException e ) {
			throw new UnsupportedExpressionException(sol.getClass());
		}
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
	public void applies( Solid sol_, Transformation trans ) {
		BasicSolidRegionExpression sol = cast(sol_);
		sol.setRegion(regEngine.transformsBy(sol.getRegion(),trans));
	}

	@Override
	public java.util.Set<Solid> filterBy( java.util.Set<Solid> sols, Region reg ) throws IllegalOperationException {
		java.util.Set<Solid> selected_sols = new java.util.HashSet<Solid>();
		for ( Solid sol : sols )
			if ( regEngine.containsAll(reg,getOccupiedRegion(sol)) )
				selected_sols.add(sol);
		return selected_sols;
	}

	@Override
	public boolean noDuplicateOccupy( java.util.Set<Solid> sols ) {
		for ( Solid sol1 : sols )
			for ( Solid sol2 : sols )
				if ( sol1 != sol2 )
					if ( !regEngine.isEmpty(regEngine.intersect(getOccupiedRegion(sol1),getOccupiedRegion(sol2))) )
						return false;
		return true;
	}

	@Override
	public boolean isSameShape( Solid sol1, Solid sol2 ) {
		return regEngine.equals(getOccupiedRegion(sol1),getOccupiedRegion(sol2));
	}

	@Override
	public boolean equals( Solid sol1, Solid sol2 ) {
		return isSameShape(sol1,sol2);
	}
}
