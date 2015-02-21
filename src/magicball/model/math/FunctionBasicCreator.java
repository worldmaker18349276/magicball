package magicball.model.math;

import io.netty.util.AttributeKey;


public interface FunctionBasicCreator
{
	public static AttributeKey<FunctionBasicCreator> KEY = AttributeKey.<FunctionBasicCreator>valueOf("FunctionBasicCreator");
	// creater
	public < I > Function<I,I> createIdentityFunction();
	public < I, O > Function<I,O> createConstantFunction( O c );
	
	public < I, O > Function<I,O> createFunctionByLambda( java.util.function.Function<I,O> lambda );
	public < I, O > Function<I,O> createFunctionByDescription( String syntax, String description );
}
