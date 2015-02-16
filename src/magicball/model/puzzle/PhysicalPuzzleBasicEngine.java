package magicball.model.puzzle;

import magicball.model.geometry.*;


public interface PhysicalPuzzleBasicEngine extends
		PhysicalPuzzleBasicOperator,
		PhysicalPuzzleBasicPredicate
{
	public PhysicalPuzzleBasicEngine clone();

	public void appliesBy( PhysicalPuzzle puzzle, Motion m ) throws IllegalOperationException;
	public void appliesBy( PhysicalPuzzle puzzle, RegionalMotion rm ) throws IllegalOperationException;

	public boolean equals( PhysicalPuzzle puzzle1, PhysicalPuzzle puzzle2 );
	public boolean isValid( PhysicalPuzzle puzzle );
}
