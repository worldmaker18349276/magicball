package magicball.model.math;

import magicball.model.*;


public interface FunctionAdvancedCreator extends Engine<Func>
{
	public < I, O > Func<I,O> createFunctionByDescription( String syntax, String description );
}
