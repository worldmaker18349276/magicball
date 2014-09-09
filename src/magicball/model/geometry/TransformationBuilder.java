package magicball.model.geometry;


// geometry abstraction layer
public interface TransformationBuilder
{
	public Transformation createIdentityTransformation();
	public Transformation createCompositeTransformation( Transformation... dis );
	public Transformation createInverseTransformation( Transformation dis );
	public Transformation createRotation( Number [] axis, Number deg );
	public Transformation createShift( Number [] vec );
}
