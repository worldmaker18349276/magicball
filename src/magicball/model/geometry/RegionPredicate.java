package magicball.model.geometry;

import magicball.model.*;


public interface RegionPredicate extends Engine<Region>
{
	public boolean isUniversal( Region reg );
	public boolean isEmpty( Region reg );
	public boolean equals( Region reg1, Region reg2 );
}
