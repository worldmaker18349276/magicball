package magicball.model.puzzle;

import magicball.model.geometry.*;
import java.util.Set;
import java.util.List;


// physical puzzle abstraction layer
public interface GeometryEngine
{
	public abstract GeometryEngine clone();

	public abstract void apply( Solid sol, Transformation trans );
	public abstract void apply( Face face, Transformation trans );
	public abstract boolean isSameShape( Region reg1, Region reg2 );
	public abstract Set<Solid> filter( Set<Solid> sols, Region reg ) throws IllegalOperationException;
	public abstract boolean noDuplicateOccupy( Set<Solid> sols );
	public abstract List<Transformation> divideIntoTransformations( Movement m, int divisor );
	public abstract List<Transformation> divideIntoTransformations( Movement m );
}
