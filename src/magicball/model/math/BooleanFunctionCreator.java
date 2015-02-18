package magicball.model.math;


public interface BooleanFunctionCreator
{
	// creater
	public < I > Function<I,Boolean> createEqualToFunction( I in );

	public Function<Boolean,Boolean> createNotFunction();
	public Function<Boolean,Function<Boolean,Boolean>> createNotToFunction();
	public Function<Boolean,Function<Boolean,Boolean>> createAndFunction();
	public Function<Boolean,Function<Boolean,Boolean>> createOrFunction();
	public Function<Boolean,Function<Boolean,Boolean>> createXorFunction();
}
