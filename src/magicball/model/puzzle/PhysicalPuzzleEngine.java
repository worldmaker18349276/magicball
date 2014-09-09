package magicball.model.puzzle;

import magicball.model.geometry.*;
import java.util.Set;
import java.util.List;


public interface PhysicalPuzzleEngine
{
	public abstract PhysicalPuzzleEngine clone();

	public abstract void apply( Solid sol, Transformation trans );
	public abstract void apply( Surface face, Transformation trans );
	public abstract boolean isSameShape( Region reg1, Region reg2 );
	public abstract Set<Solid> filter( Set<Solid> sols, Region reg ) throws IllegalOperationException;
	public abstract boolean noDuplicateOccupy( Set<Solid> sols );
	public abstract List<Transformation> divideMovement( Movement m );
	public abstract List<Transformation> divideMovementByDivisor( Movement m, int divisor );
	public abstract List<Transformation> divideMovementByIntervals( Movement m, List<Number> intervals );
}
