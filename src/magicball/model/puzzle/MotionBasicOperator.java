package magicball.model.puzzle;

import magicball.model.geometry.*;
import magicball.model.*;


public interface MotionBasicOperator extends Engine<Motion>
{
	// operator
	public Transformation divideMotionIntoTransformation( Motion move, Number from, Number to );
	public java.util.List<Transformation> divideMotionByDivisor( Motion move, int divisor );
	public java.util.List<Transformation> divideMotionByIntervals( Motion move, java.util.List<Number> intervals );
}
