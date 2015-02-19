package magicball.model.math;

import io.netty.util.AttributeKey;


public interface BooleanFunctionPredicate
{
	public static AttributeKey<BooleanFunctionPredicate> KEY = AttributeKey.<BooleanFunctionPredicate>valueOf("BooleanFunctionPredicate");
	// predicate
	public < I > boolean isAlwaysTrue( Function<I,Boolean> func );
	public < I > boolean isAlwaysFalse( Function<I,Boolean> func );
	public < I > boolean implies( Function<I,Boolean> func1, Function<I,Boolean> func2 );
}
