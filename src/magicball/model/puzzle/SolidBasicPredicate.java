package magicball.model.puzzle;

import magicball.model.geometry.*;
import magicball.model.*;


public interface SolidBasicPredicate extends Engine<Solid>
{
	// predicate
	public boolean noDuplicateOccupyIn( java.util.Set<Solid> sols );
	public boolean areSameShape( Solid sol1, Solid sol2 );
	public boolean equals( Solid sol1, Solid sol2 );
}
