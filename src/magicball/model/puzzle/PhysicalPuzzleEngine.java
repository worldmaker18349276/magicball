package magicball.model.puzzle;

import magicball.model.geometry.*;


public interface PhysicalPuzzleEngine
{
	public PhysicalPuzzleEngine clone();

	public Solid apply( Solid sol, Transformation trans );
	public boolean isSameShape( Solid sol1, Solid sol2 );
	public java.util.Set<Solid> filter( java.util.Set<Solid> sols, Region reg ) throws IllegalOperationException;
	public boolean noDuplicateOccupy( java.util.Set<Solid> sols );
	public java.util.List<Transformation> divideMovement( Movement m );
	public java.util.List<Transformation> divideMovementByDivisor( Movement m, int divisor );
	public java.util.List<Transformation> divideMovementByIntervals( Movement m, java.util.List<Number> intervals );
}
