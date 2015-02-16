package magicball.model.geometry;

import magicball.model.math.*;


public interface RegionBasicPredicate
{
	// predicate
	public boolean isEmpty( Region reg );
	public boolean isUniversal( Region reg );
	public boolean containsAll( Region reg1, Region reg2 );
	public boolean equals( Region reg1, Region reg2 );
}
