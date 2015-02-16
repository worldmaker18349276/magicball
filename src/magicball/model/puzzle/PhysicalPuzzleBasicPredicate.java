package magicball.model.puzzle;

import magicball.model.geometry.*;


public interface PhysicalPuzzleBasicPredicate
{
	public boolean equals( PhysicalPuzzle puzzle1, PhysicalPuzzle puzzle2 );
	public boolean isValid( PhysicalPuzzle puzzle );
}
