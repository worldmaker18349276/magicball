package magicball.model.math;

import magicball.model.*;


public interface VectorCreator extends Engine<Num>
{
	public Num[] createVectorByDoubles( double... ns );
	public Num[] createZeroVectorWithDim( int d );
}
