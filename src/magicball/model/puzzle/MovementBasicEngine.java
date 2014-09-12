package magicball.model.puzzle;

import magicball.model.geometry.*;


public interface MovementBasicEngine < M extends Movement, T extends Transformation >
{
	public MovementBasicEngine clone();
	public M createSimpleMovementByTransformation( T trans );
	public T divideMovementIntoTransformation( M move, Number from, Number to );
}
