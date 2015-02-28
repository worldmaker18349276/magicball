package magicball.model.puzzle;

import magicball.model.geometry.*;
import magicball.model.*;


public interface PhysicalPuzzleBasicOperator extends Engine<PhysicalPuzzle>
{
	public void appliesBy( PhysicalPuzzle puzzle, Motion m ) throws IllegalOperationException;
	public void appliesBy( PhysicalPuzzle puzzle, RegionalMotion rm ) throws IllegalOperationException;
}
