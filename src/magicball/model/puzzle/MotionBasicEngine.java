package magicball.model.puzzle;

import magicball.model.geometry.*;


public interface MotionBasicEngine extends
		MotionBasicCreator,
		MotionBasicAttribute,
		MotionBasicOperator,
		SimpleMotionPredicate
{
	public MotionBasicEngine clone();


	// creater
	public Motion createSimpleMotionByTransformation( Transformation trans );
	public RegionalMotion createRegionalMotion( Region reg, Motion move );


	// attribute
	public Transformation getTransformation( Motion move );


	// operator
	public Transformation divideMotionIntoTransformation( Motion move, Number from, Number to );
	public java.util.List<Transformation> divideMotionByDivisor( Motion move, int divisor );
	public java.util.List<Transformation> divideMotionByIntervals( Motion move, java.util.List<Number> intervals );

	public boolean isSimpleMotion( Motion move );
}