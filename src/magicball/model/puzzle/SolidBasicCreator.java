package magicball.model.puzzle;

import magicball.model.geometry.*;
import magicball.model.*;


public interface SolidBasicCreator extends Engine<Solid>
{
	// creater
	public Solid createSolidByRegion( Region reg );
}
