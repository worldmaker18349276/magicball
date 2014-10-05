package magicball.basic.puzzle;

import magicball.model.puzzle.*;
import magicball.model.geometry.*;
import magicball.model.math.*;
import magicball.model.*;


public class PhysicalPuzzleBasicEngine implements PhysicalPuzzleEngine
{
	protected RegionEngine regEngine;
	protected MovementEngine moveEngine;
	protected NumberEngine mathEngine;

	public PhysicalPuzzleBasicEngine( RegionEngine regEng, MovementEngine moveEng, NumberEngine mathEng ) {
		this.regEngine = regEng;
		this.moveEngine = moveEng;
		this.mathEngine = mathEng;
	}

	public PhysicalPuzzleBasicEngine( EngineProvider provider ) {
		this.regEngine = provider.getRegionEngine();
		this.moveEngine = provider.getMovementEngine();
		this.mathEngine = provider.getNumberEngine();
	}

	public PhysicalPuzzleEngine clone() {
		return new PhysicalPuzzleBasicEngine(this.regEngine,this.moveEngine,this.mathEngine);
	}

	public Solid apply( Solid sol, Transformation trans ) {
		sol.setRegion(regEngine.transformsBy(sol.getRegion(),trans));
		return sol;
	}

	public boolean isSameShape( Solid sol1, Solid sol2 ) {
		return regEngine.equals(sol1.getRegion(),sol2.getRegion());
	}

	public java.util.Set<Solid> filter( java.util.Set<Solid> sols, Region reg ) throws IllegalOperationException {
		java.util.Set<Solid> selected_sols = new java.util.HashSet<Solid>();
		for ( Solid sol : sols )
			if ( regEngine.containsAll(reg,sol.getRegion()) )
				selected_sols.add(sol);
		return selected_sols;
	}

	public boolean noDuplicateOccupy( java.util.Set<Solid> sols ) {
		for ( Solid sol1 : sols )
			for ( Solid sol2 : sols )
				if ( sol1 != sol2 )
					if ( !regEngine.isEmpty(regEngine.intersect(sol1.getRegion(),sol2.getRegion())) )
						return false;
		return true;
	}

	public java.util.List<Transformation> divideMovement( Movement m ) {
		return divideMovementByDivisor(m,10);
	}

	public java.util.List<Transformation> divideMovementByDivisor( Movement m, int divisor ) {
		java.util.List<Transformation> moves = new java.util.ArrayList<Transformation>();
		Number from = (Integer) 0;
		Number to = (Integer) 0;
		Number d = mathEngine.dividedBy(mathEngine.number1(),mathEngine.number(divisor));
		for ( int i=0; i<=divisor; i++ ) {
			to = mathEngine.add(from,d);
			moves.add(moveEngine.divideMovementIntoTransformation(m,from,to));
			from = to;
		}
		return moves;
	}

	public java.util.List<Transformation> divideMovementByIntervals( Movement m, java.util.List<Number> intervals ) {
		java.util.List<Transformation> moves = new java.util.ArrayList<Transformation>();
		Number from = (Integer) 0;
		Number to = (Integer) 0;
		for ( Number d : intervals ) {
			to = mathEngine.add(from,d);
			moves.add(moveEngine.divideMovementIntoTransformation(m,from,to));
			from = to;
		}
		return moves;
	}
}
