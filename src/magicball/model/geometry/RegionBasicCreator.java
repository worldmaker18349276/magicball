package magicball.model.geometry;

import magicball.model.math.*;


public interface RegionBasicCreator
{
	// creater
	public Region createUniversalRegion();
	public Region createEmptyRegion();

	public Region createRegionByFunction( Function<Number[],Boolean> func );
	public Region createRegionByFace( Surface face, int side );
}
