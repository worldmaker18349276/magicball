package magicball.model.puzzle;

import io.netty.util.AttributeKey;

import magicball.model.geometry.*;


public interface MotionBasicAttribute
{
	public static AttributeKey<MotionBasicAttribute> KEY = AttributeKey.<MotionBasicAttribute>valueOf("MotionBasicAttribute");
	// attribute
	public Transformation getTransformation( Motion move );
}
