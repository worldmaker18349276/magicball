package magicball.model.geometry;

import magicball.model.math.*;
import magicball.model.*;


public interface RegionBasicAttribute extends Engine<Region>
{
	// attribute
	public boolean contains( Region reg, Number[] point );
}
