package magicball.model.math;

import magicball.model.*;


public interface VectorPredicate extends Engine<Num>
{
	public boolean equals( Num[] v1, Num[] v2 );
	public boolean isZeroVector( Num[] v );
}
