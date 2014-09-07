package magicball.model;

import java.util.Set;
import java.util.List;


// physical puzzle abstraction layer
public interface GeometryEngine
{
	public abstract GeometryEngine clone();

	public abstract void apply( Solid sol, Displacement dis );
	public abstract void apply( Face face, Displacement dis );
	public abstract boolean isSameShape( Region reg1, Region reg2 );
	public abstract Set<Solid> filter( Set<Solid> sols, Region reg ) throws IllegalOperationException;
	public abstract boolean noDuplicateOccupy( Set<Solid> sols );
	public abstract List<Displacement> divideIntoDisplacements( Transform trans, int divisor );
	public abstract List<Displacement> divideIntoDisplacements( Transform trans );
}
