package magicball.model.geometry;


// geometry abstraction layer
public interface TransformBuilder
{
	public Transform createSimpleTransformByDisplacement( Displacement dis );
	public RegionalTransform createRegionalTransform( Region reg, Transform trans );
}
