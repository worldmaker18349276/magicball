package magicball.model.geometry;

import magicball.model.math.*;
import magicball.model.*;


public interface RegionBasicCreator extends Engine<Region>
{
	// creater
	public Region createRegionByFunction( Function<Number[],Boolean> func );

	public Region createUniversalRegion();
	public Region createEmptyRegion();
}
