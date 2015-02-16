package magicball.model.puzzle;

import magicball.model.geometry.*;


public interface PhysicalPuzzleBasicOperator
{
	public void applies( PhysicalPuzzle puzzle, Motion m ) throws IllegalOperationException;
	public void applies( PhysicalPuzzle puzzle, RegionalMotion rm ) throws IllegalOperationException;
}
