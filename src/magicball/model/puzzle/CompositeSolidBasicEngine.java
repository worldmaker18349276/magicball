package magicball.model.puzzle;

import magicball.model.geometry.*;
import magicball.model.*;


public class CompositeSolidBasicEngine extends CompositeEngine<Solid> implements SolidBasicEngine
{
	protected java.util.List<Engine<? extends Solid>> engines;

	public CompositeSolidBasicEngine() {
		engines = new java.util.LinkedList<>();
	}

	public CompositeSolidBasicEngine( java.util.List<Engine<? extends Solid>> eng ) {
		engines = eng;
	}

	public CompositeSolidBasicEngine clone() {
		return new CompositeSolidBasicEngine(new java.util.LinkedList<>(engines));
	}

	public void add( Engine<? extends Solid> engine ) {
		engines.add(engine);
	}

	public void add( int index, Engine<? extends Solid> engine ) {
		engines.add(index, engine);
	}


	// creater
	public Solid createSolidByRegion( Region reg ) {
		for ( Engine<? extends Solid> engine : engines ) if ( engine instanceof SolidBasicCreator ) {

			try {
				return ((SolidBasicCreator)engine).createSolidByRegion(reg);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}


	// attribute
	public Region getOccupiedRegion( Solid sol ) {
		for ( Engine<? extends Solid> engine : engines ) if ( engine instanceof SolidBasicAttribute ) {

			try {
				return ((SolidBasicAttribute)engine).getOccupiedRegion(sol);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}


	// operator
	public void transformsBy( Solid sol, Transformation trans ) {
		for ( Engine<? extends Solid> engine : engines ) if ( engine instanceof SolidBasicOperator ) {

			try {
				((SolidBasicOperator)engine).transformsBy(sol,trans);
				return;
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public java.util.Set<Solid> filtersBy( java.util.Set<Solid> sols, Region reg ) throws IllegalOperationException {
		for ( Engine<? extends Solid> engine : engines ) if ( engine instanceof SolidBasicOperator ) {

			try {
				return ((SolidBasicOperator)engine).filtersBy(sols,reg);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public boolean noDuplicateOccupyIn( java.util.Set<Solid> sols ) {
		for ( Engine<? extends Solid> engine : engines ) if ( engine instanceof SolidBasicPredicate ) {

			try {
				return ((SolidBasicPredicate)engine).noDuplicateOccupyIn(sols);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public boolean areSameShape( Solid sol1, Solid sol2 ) {
		for ( Engine<? extends Solid> engine : engines ) if ( engine instanceof SolidBasicPredicate ) {

			try {
				return ((SolidBasicPredicate)engine).areSameShape(sol1,sol2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public boolean equals( Solid sol1, Solid sol2 ) {
		for ( Engine<? extends Solid> engine : engines ) if ( engine instanceof SolidBasicPredicate ) {

			try {
				return ((SolidBasicPredicate)engine).equals(sol1,sol2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

}
