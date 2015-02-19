package magicball.model.puzzle;

import io.netty.util.AttributeKey;

import magicball.model.geometry.*;


public interface SolidBasicAttribute
{
	public static AttributeKey<SolidBasicAttribute> KEY = AttributeKey.<SolidBasicAttribute>valueOf("SolidBasicAttribute");
	// attribute
	public Region getOccupiedRegion( Solid sol );
}
