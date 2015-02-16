package magicball.model.geometry;

import magicball.model.math.*;


public interface RegionCreatorForFunc
{
	// creater
	public Region createRegionByFunction( Function<Number[],Boolean> func );
	public Region createRegionByFace( Surface face, int side );
}
