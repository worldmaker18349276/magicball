package magicball.model.geometry;


// geometry abstraction layer
public interface MovementBuilder
{
	public Movement createSimpleMovementByTransformation( Transformation dis );
}
