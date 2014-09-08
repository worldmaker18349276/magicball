package magicball.model;


// geometry abstraction layer
public interface TransformBuilder
{
	public Transform createSimpleTransformByDisplacement( Displacement dis );
}
