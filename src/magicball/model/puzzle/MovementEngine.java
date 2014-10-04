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
	public boolean isSimpleMovement( Movement move );
}
