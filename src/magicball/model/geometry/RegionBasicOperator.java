package magicball.model.geometry;

import magicball.model.math.*;
import magicball.model.*;


public interface RegionBasicOperator extends Engine<Region>
{
	// operator
	public Region intersect( Region... regs );
	public Region union( Region... regs );
	public Region complement( Region reg1, Region reg2 );
	public Region complement( Region reg2 );
	
	public Region transformsBy( Region reg, Transformation trans );
}
