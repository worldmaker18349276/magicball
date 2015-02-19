package magicball.model.puzzle;

import magicball.model.geometry.*;
import magicball.model.*;


public class CompositeMotionBasicEngine extends DefaultCompositeEngine<Motion> implements MotionBasicEngine
{
	// creater
	public Motion createSimpleMotionByTransformation( Transformation trans ) {
		for ( Engine<? extends Motion> engine : engines ) if ( engine instanceof MotionBasicCreator ) {

			try {
				return ((MotionBasicCreator)engine).createSimpleMotionByTransformation(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public RegionalMotion createRegionalMotion( Region reg, Motion move ) {
		for ( Engine<? extends Motion> engine : engines ) if ( engine instanceof MotionBasicCreator ) {

			try {
				return ((MotionBasicCreator)engine).createRegionalMotion(reg,move);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}



	// attribute
	public Transformation getTransformation( Motion move ) {
		for ( Engine<? extends Motion> engine : engines ) if ( engine instanceof MotionBasicAttribute ) {

			try {
				return ((MotionBasicAttribute)engine).getTransformation(move);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}


	// operator
	public Transformation divideMotionIntoTransformation( Motion move, Number from, Number to ) {
		for ( Engine<? extends Motion> engine : engines ) if ( engine instanceof MotionBasicOperator ) {

			try {
				return ((MotionBasicOperator)engine).divideMotionIntoTransformation(move,from,to);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public java.util.List<Transformation> divideMotionByDivisor( Motion move, int divisor ) {
		for ( Engine<? extends Motion> engine : engines ) if ( engine instanceof MotionBasicOperator ) {

			try {
				return ((MotionBasicOperator)engine).divideMotionByDivisor(move,divisor);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public java.util.List<Transformation> divideMotionByIntervals( Motion move, java.util.List<Number> intervals ) {
		for ( Engine<? extends Motion> engine : engines ) if ( engine instanceof MotionBasicOperator ) {

			try {
				return ((MotionBasicOperator)engine).divideMotionByIntervals(move,intervals);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public boolean isSimpleMotion( Motion move ) {
		for ( Engine<? extends Motion> engine : engines ) if ( engine instanceof SimpleMotionPredicate ) {

			try {
				return ((SimpleMotionPredicate)engine).isSimpleMotion(move);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
}
