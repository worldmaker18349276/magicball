package magicball.model.geometry;


public interface MovementEngine < M extends Movement, T extends Transformation >
{
	public M createSimpleMovementByTransformation( T trans );
}
