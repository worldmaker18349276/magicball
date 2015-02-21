package magicball.model.math;

import io.netty.util.AttributeKey;


public interface FunctionBasicOperator
{
	public static AttributeKey<FunctionBasicOperator> KEY = AttributeKey.<FunctionBasicOperator>valueOf("FunctionBasicOperator");
	// operator
	public < I, M, O > Function<I,O> compose( Function<I,M> func1, Function<M,O> func2 );
	public < I, O > Function<O,I> invert( Function<I,O> func );
}
