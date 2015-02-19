package magicball.model.math;

import io.netty.util.AttributeKey;


public interface FunctionBasicPredicate
{
	public static AttributeKey<FunctionBasicPredicate> KEY = AttributeKey.<FunctionBasicPredicate>valueOf("FunctionBasicPredicate");
	// predicate
	public < I, O > boolean equals( Function<I,O> func1, Function<I,O> func2 );
	public < I, O > boolean isAlwaysEqualTo( Function<I,O> func, O value );
}
