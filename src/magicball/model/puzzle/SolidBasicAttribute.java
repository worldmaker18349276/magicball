package magicball.model.puzzle;

import magicball.model.geometry.*;
import magicball.model.*;


public interface SolidBasicAttribute extends Engine<Solid>
{
	// attribute
	public Region getOccupiedRegion( Solid sol );
}
