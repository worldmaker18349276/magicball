package magicball.model.geometry;

import io.netty.util.AttributeKey;

import magicball.model.math.*;


public interface RegionBasicAttribute
{
	public static AttributeKey<RegionBasicAttribute> KEY = AttributeKey.<RegionBasicAttribute>valueOf("RegionBasicAttribute");
	// attribute
	public boolean contains( Region reg, Number[] point );
}
