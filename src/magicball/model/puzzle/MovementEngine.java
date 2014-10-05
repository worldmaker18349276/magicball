package magicball.model.puzzle;

import magicball.model.geometry.*;


public interface MovementEngine
{
	public MovementEngine clone();


	// creater
	public Movement createSimpleMovementByTransformation( Transformation trans );


	// attribute
	public Transformation getTransformation( Movement move );


	// operator
	public Transformation divideMovementIntoTransformation( Movement move, Number from, Number to );
	public java.util.List<Transformation> divideMovementByDivisor( Movement move, int divisor );
	public java.util.List<Transformation> divideMovementByIntervals( Movement move, java.util.List<Number> intervals );

	public boolean isSimpleMovement( Movement move );
}
