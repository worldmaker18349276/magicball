package magicball.model.geometry;

import magicball.model.math.Func;
import magicball.model.math.Num;
import magicball.model.*;


public interface RegionCreator extends Engine<Region>
{
	public Region createUniversalRegion();
	public Region createEmptyRegion();

	public Region createRegionByFunction( Func<Num[],Boolean> func );
}
