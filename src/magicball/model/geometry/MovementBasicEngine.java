package magicball.model.geometry;


public interface MovementBasicEngine < M extends Movement, T extends Transformation >
{
	public MovementBasicEngine clone();
	public M createSimpleMovementByTransformation( T trans );
}
