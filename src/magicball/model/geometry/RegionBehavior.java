package magicball.model.geometry;

import magicball.model.math.Num;
import magicball.model.*;


public interface RegionBehavior extends Engine<Region>
{
	public boolean contains( Region reg, Num[] point );
	public boolean containsAll( Region reg1, Region reg2 );
}
