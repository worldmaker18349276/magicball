package magicball.model.math;

import magicball.model.*;


public interface BooleanFunctionPredicate extends Engine<Func>
{
	public < I > boolean isAlwaysTrue( Func<I,Boolean> func );
	public < I > boolean isAlwaysFalse( Func<I,Boolean> func );
	public < I > boolean implies( Func<I,Boolean> func1, Func<I,Boolean> func2 );
}
