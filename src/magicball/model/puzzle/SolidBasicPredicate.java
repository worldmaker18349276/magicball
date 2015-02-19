package magicball.model.puzzle;

import io.netty.util.AttributeKey;

import magicball.model.geometry.*;


public interface SolidBasicPredicate
{
	public static AttributeKey<SolidBasicPredicate> KEY = AttributeKey.<SolidBasicPredicate>valueOf("SolidBasicPredicate");
	// predicate
	public boolean noDuplicateOccupyIn( java.util.Set<Solid> sols );
	public boolean areSameShape( Solid sol1, Solid sol2 );
	public boolean equals( Solid sol1, Solid sol2 );
}
