package magicball.model.puzzle.basic;

import magicball.model.puzzle.*;
import magicball.model.geometry.*;
import magicball.model.math.*;
import magicball.model.*;


public class PhysicalPuzzleBasicEngine implements PhysicalPuzzleEngine
{
	protected MovementEngine moveEngine;
	protected SolidEngine solEngine;

	public PhysicalPuzzleBasicEngine( MovementEngine moveEng, SolidEngine solEng ) {
		this.moveEngine = moveEng;
		this.solEngine = solEng;
	}

	public PhysicalPuzzleBasicEngine( EngineProvider provider ) {
		this.moveEngine = provider.getMovementEngine();
		this.solEngine = provider.getSolidEngine();
	}

	@Override
	public PhysicalPuzzleEngine clone() {
		return new PhysicalPuzzleBasicEngine(this.moveEngine,this.solEngine);
	}


	@Override
	public void applies( PhysicalPuzzle puzzle, Movement move ) {
		Transformation trans = moveEngine.getTransformation(move);
		for ( Solid sol : puzzle.getComponents() )
			solEngine.applies(sol,trans);
		// Transformation trans = moveEngine.getTransformation(move);
		// for ( Solid sol : getComponents() ) {
		// 	solEngine.applies(sol,trans);
		// }
	}

	@Override
	public void applies( PhysicalPuzzle puzzle, RegionalMovement rmove ) throws IllegalOperationException {
		java.util.Set<Solid> selected_sols = solEngine.filterBy(puzzle.getComponents(),rmove.getRegion());
		Transformation trans = moveEngine.getTransformation(rmove.getMovement());
		for ( Solid sol : selected_sols )
			solEngine.applies(sol,trans);
		// java.util.Set<Solid> selected_sols = solEngine.filterBy(getComponents(),rmove.getRegion());
		// java.util.List<Transformation> trans_list = moveEngine.divideMovementByDivisor(rmove.getMovement(),10);
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
