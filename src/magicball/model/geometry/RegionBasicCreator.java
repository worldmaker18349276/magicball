package magicball.model.geometry;

import io.netty.util.AttributeKey;

import magicball.model.math.*;


public interface RegionBasicCreator
{
	public static AttributeKey<RegionBasicCreator> KEY = AttributeKey.<RegionBasicCreator>valueOf("RegionBasicCreator");
	// creater
	public Region createRegionByFunction( Function<Number[],Boolean> func );

	public Region createUniversalRegion();
	public Region createEmptyRegion();
}
