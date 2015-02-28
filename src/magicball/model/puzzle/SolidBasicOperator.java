package magicball.model.puzzle;

import magicball.model.geometry.*;
import magicball.model.*;


public interface SolidBasicOperator extends Engine<Solid>
{
	// operator
	public void transformsBy( Solid sol, Transformation trans );
	public java.util.Set<Solid> filtersBy( java.util.Set<Solid> sols, Region reg ) throws IllegalOperationException;
}
