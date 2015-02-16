package magicball.model.geometry;

import magicball.model.math.*;


public interface RegionAttributeForFunc
{
	// attribute
	public Function<Number[],Boolean> getRegionIntensionFunction( Region reg );
}
