package magicball.model.math;


public interface BooleanFunctionPredicate
{
	// predicate
	public < I > boolean isAlwaysTrue( Function<I,Boolean> func );
	public < I > boolean isAlwaysFalse( Function<I,Boolean> func );
	public < I > boolean implies( Function<I,Boolean> func1, Function<I,Boolean> func2 );
}
