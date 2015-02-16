package magicball.model.puzzle;

import magicball.model.geometry.*;


public interface PhysicalPuzzleBasicEngine
{
	public PhysicalPuzzleBasicEngine clone();

	public void applies( PhysicalPuzzle puzzle, Motion m ) throws IllegalOperationException;
	public void applies( PhysicalPuzzle puzzle, RegionalMotion rm ) throws IllegalOperationException;

	public boolean equals( PhysicalPuzzle puzzle1, PhysicalPuzzle puzzle2 );
	public boolean isValid( PhysicalPuzzle puzzle );
}
