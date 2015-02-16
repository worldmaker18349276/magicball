package magicball.model.puzzle;

import magicball.model.geometry.*;


public interface SolidBasicPredicate
{
	// predicate
	public boolean noDuplicateOccupy( java.util.Set<Solid> sols );
	public boolean isSameShape( Solid sol1, Solid sol2 );
	public boolean equals( Solid sol1, Solid sol2 );
}
