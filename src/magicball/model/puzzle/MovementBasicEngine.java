package magicball.model.puzzle;

import magicball.model.geometry.*;


public interface MovementBasicEngine
{
	public MovementBasicEngine clone();
	public Movement createSimpleMovementByTransformation( Transformation trans );
	public Transformation divideMovementIntoTransformation( Movement move, Number from, Number to );
}
