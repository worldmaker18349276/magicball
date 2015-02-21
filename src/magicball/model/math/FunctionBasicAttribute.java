package magicball.model.math;

import io.netty.util.AttributeKey;


public interface FunctionBasicAttribute
{
	public static AttributeKey<FunctionBasicAttribute> KEY = AttributeKey.<FunctionBasicAttribute>valueOf("FunctionBasicAttribute");
	// attribute
	public < I, O > O applies( Function<I,O> func, I in );
}
