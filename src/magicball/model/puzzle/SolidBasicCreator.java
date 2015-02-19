package magicball.model.puzzle;

import io.netty.util.AttributeKey;

import magicball.model.geometry.*;


public interface SolidBasicCreator
{
	public static AttributeKey<SolidBasicCreator> KEY = AttributeKey.<SolidBasicCreator>valueOf("SolidBasicCreator");
	// creater
	public Solid createSolidByRegion( Region reg );
}
