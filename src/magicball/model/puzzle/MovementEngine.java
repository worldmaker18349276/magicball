package magicball.model.puzzle;

import magicball.model.geometry.*;


public interface MovementEngine
{
	public MovementEngine clone();
	public Transformation getTransformation( Movement move );
	public Movement createSimpleMovementByTransformation( Transformation trans );
	public Transformation divideMovementIntoTransformation( Movement move, Number from, Number to );
	public boolean isSimpleMovement( Movement move );
}
