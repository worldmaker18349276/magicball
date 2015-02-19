package magicball.model.puzzle.basic;

import io.netty.util.DefaultAttributeMap;

import magicball.model.puzzle.*;
import magicball.model.geometry.*;
import magicball.model.math.*;
import magicball.model.*;


public class PhysicalPuzzleEngineForBasic extends DefaultAttributeMap implements PhysicalPuzzleBasicEngine
{
	public PhysicalPuzzleEngineForBasic() {
		super();
	}

	public PhysicalPuzzleEngineForBasic( MotionBasicEngine moveEng, SolidBasicEngine solEng ) {
		super();
		setEngine(moveEng);
		setEngine(solEng);
	}

	public PhysicalPuzzleEngineForBasic( BasicEngineProvider provider ) {
		super();
		setEngine(provider.getMotionEngine());
		setEngine(provider.getSolidEngine());
	}

	public void setEngine( MotionBasicEngine moveEng ) {
		attr(MotionBasicEngine.KEY).set(moveEng);
	}

	public void setEngine( SolidBasicEngine solEng ) {
		attr(SolidBasicEngine.KEY).set(solEng);
	}

	public MotionBasicEngine moveEngine() {
		return attr(MotionBasicEngine.KEY).get();
	}

	public SolidBasicEngine solEngine() {
		return attr(SolidBasicEngine.KEY).get();
	}


	@Override
	public void appliesBy( PhysicalPuzzle puzzle, Motion move ) {
		Transformation trans = moveEngine().getTransformation(move);
		for ( Solid sol : puzzle.getComponents() )
			solEngine().transformsBy(sol,trans);
		// Transformation trans = moveEngine().getTransformation(move);
		// for ( Solid sol : getComponents() ) {
		// 	solEngine().transformsBy(sol,trans);
		// }
	}

	@Override
	public void appliesBy( PhysicalPuzzle puzzle, RegionalMotion rmove ) throws IllegalOperationException {
		java.util.Set<Solid> selected_sols = solEngine().filtersBy(puzzle.getComponents(),rmove.getRegion());
		Transformation trans = moveEngine().getTransformation(rmove.getMotion());
		for ( Solid sol : selected_sols )
			solEngine().transformsBy(sol,trans);
		// java.util.Set<Solid> selected_sols = solEngine().filtersBy(getComponents(),rmove.getRegion());
		// java.util.List<Transformation> trans_list = moveEngine().divideMotionByDivisor(rmove.getMotion(),10);
		// for ( Transformation trans : trans_list ) {
		// 	for ( Solid sol : selected_sols )
		// 		solEngine().transformsBy(sol,trans);
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
		return solEngine().noDuplicateOccupyIn(puzzle.getComponents());
	}

}
