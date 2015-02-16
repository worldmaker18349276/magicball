package magicball.model.puzzle.basic;

import magicball.model.puzzle.*;
import magicball.model.geometry.*;
import magicball.model.math.*;
import magicball.model.*;


public class PhysicalPuzzleEngineForBasic implements PhysicalPuzzleBasicEngine
{
	protected MotionBasicEngine moveEngine;
	protected SolidBasicEngine solEngine;

	public PhysicalPuzzleEngineForBasic( MotionBasicEngine moveEng, SolidBasicEngine solEng ) {
		this.moveEngine = moveEng;
		this.solEngine = solEng;
	}

	public PhysicalPuzzleEngineForBasic( EngineProvider provider ) {
		this.moveEngine = provider.getMotionEngine();
		this.solEngine = provider.getSolidEngine();
	}

	@Override
	public PhysicalPuzzleBasicEngine clone() {
		return new PhysicalPuzzleEngineForBasic(this.moveEngine,this.solEngine);
	}


	@Override
	public void applies( PhysicalPuzzle puzzle, Motion move ) {
		Transformation trans = moveEngine.getTransformation(move);
		for ( Solid sol : puzzle.getComponents() )
			solEngine.applies(sol,trans);
		// Transformation trans = moveEngine.getTransformation(move);
		// for ( Solid sol : getComponents() ) {
		// 	solEngine.applies(sol,trans);
		// }
	}

	@Override
	public void applies( PhysicalPuzzle puzzle, RegionalMotion rmove ) throws IllegalOperationException {
		java.util.Set<Solid> selected_sols = solEngine.filterBy(puzzle.getComponents(),rmove.getRegion());
		Transformation trans = moveEngine.getTransformation(rmove.getMotion());
		for ( Solid sol : selected_sols )
			solEngine.applies(sol,trans);
		// java.util.Set<Solid> selected_sols = solEngine.filterBy(getComponents(),rmove.getRegion());
		// java.util.List<Transformation> trans_list = moveEngine.divideMotionByDivisor(rmove.getMotion(),10);
		// for ( Transformation trans : trans_list ) {
		// 	for ( Solid sol : selected_sols )
		// 		solEngine.applies(sol,trans);
		// 	if ( !isValid(puzzle) )
		// 		throw new IllegalOperationException();
		// }
	}


	@Override
	public boolean equals( PhysicalPuzzle puzzle1, PhysicalPuzzle puzzle2 ) {
		return puzzle1.getComponents().equals(puzzle2.getComponents());
	}

	@Override
	public boolean isValid( PhysicalPuzzle puzzle ) {
		return solEngine.noDuplicateOccupy(puzzle.getComponents());
	}

}
