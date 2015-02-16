package magicball.model.puzzle;

import magicball.model.geometry.*;


public interface PhysicalPuzzleBasicOperator
{
	public void appliesBy( PhysicalPuzzle puzzle, Motion m ) throws IllegalOperationException;
	public void appliesBy( PhysicalPuzzle puzzle, RegionalMotion rm ) throws IllegalOperationException;
}
