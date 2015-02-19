package magicball.model.puzzle;

import io.netty.util.AttributeKey;

import magicball.model.geometry.*;


public interface SimpleMotionPredicate
{
	public static AttributeKey<SimpleMotionPredicate> KEY = AttributeKey.<SimpleMotionPredicate>valueOf("SimpleMotionPredicate");
	// predicate
	public boolean isSimpleMotion( Motion move );
}
