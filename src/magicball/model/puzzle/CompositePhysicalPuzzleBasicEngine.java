package magicball.model.puzzle;

import magicball.model.geometry.*;
import magicball.model.*;


public class CompositePhysicalPuzzleBasicEngine extends CompositeEngine<PhysicalPuzzle> implements PhysicalPuzzleBasicEngine
{
	protected java.util.List<Engine<? extends PhysicalPuzzle>> engines;

	public CompositePhysicalPuzzleBasicEngine() {
		engines = new java.util.LinkedList<>();
	}

	public CompositePhysicalPuzzleBasicEngine( java.util.List<Engine<? extends PhysicalPuzzle>> eng ) {
		engines = eng;
	}

	public CompositePhysicalPuzzleBasicEngine clone() {
		return new CompositePhysicalPuzzleBasicEngine(new java.util.LinkedList<>(engines));
	}

	public void add( Engine<? extends PhysicalPuzzle> engine ) {
		engines.add(engine);
	}

	public void add( int index, Engine<? extends PhysicalPuzzle> engine ) {
		engines.add(index, engine);
	}


	public void appliesBy( PhysicalPuzzle puzzle, Motion m ) throws IllegalOperationException {
		for ( Engine<? extends PhysicalPuzzle> engine : engines ) if ( engine instanceof PhysicalPuzzleBasicOperator ) {

			try {
				((PhysicalPuzzleBasicOperator)engine).appliesBy(puzzle,m);
				return;
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public void appliesBy( PhysicalPuzzle puzzle, RegionalMotion rm ) throws IllegalOperationException {
		for ( Engine<? extends PhysicalPuzzle> engine : engines ) if ( engine instanceof PhysicalPuzzleBasicOperator ) {

			try {
				((PhysicalPuzzleBasicOperator)engine).appliesBy(puzzle,rm);
				return;
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public boolean equals( PhysicalPuzzle puzzle1, PhysicalPuzzle puzzle2 ) {
		for ( Engine<? extends PhysicalPuzzle> engine : engines ) if ( engine instanceof PhysicalPuzzleBasicPredicate ) {

			try {
				return ((PhysicalPuzzleBasicPredicate)engine).equals(puzzle1,puzzle2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public boolean isValid( PhysicalPuzzle puzzle ) {
		for ( Engine<? extends PhysicalPuzzle> engine : engines ) if ( engine instanceof PhysicalPuzzleBasicPredicate ) {

			try {
				return ((PhysicalPuzzleBasicPredicate)engine).isValid(puzzle);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
}
