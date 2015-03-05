package magicball.model.math;

import magicball.model.*;


public class ArbitraryFunctionAdvancedProperty
{
	static public interface Creator extends Engine<Func>
	{
		public < I, O > Func<I,O> createFunctionByDescription( String syntax, String description );
	}
}
