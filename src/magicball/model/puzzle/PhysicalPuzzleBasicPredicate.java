package magicball.model.puzzle;

import magicball.model.geometry.*;
import magicball.model.*;


public interface PhysicalPuzzleBasicPredicate extends Engine<PhysicalPuzzle>
{
	public boolean equals( PhysicalPuzzle puzzle1, PhysicalPuzzle puzzle2 );
	public boolean isValid( PhysicalPuzzle puzzle );
}
