package magicball.model.puzzle;

import magicball.model.geometry.*;


public interface SolidBasicOperator
{
	// operator
	public void applies( Solid sol, Transformation trans );
	public java.util.Set<Solid> filterBy( java.util.Set<Solid> sols, Region reg ) throws IllegalOperationException;
}
