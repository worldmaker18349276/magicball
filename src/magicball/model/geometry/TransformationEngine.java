package magicball.model.geometry;


public interface TransformationEngine
{
	public Transformation createIdentityTransformation();
	public Transformation compose( Transformation... trans );
	public Transformation invert( Transformation trans );
	public Transformation createRotation( Number [] axis, Number deg );
	public Transformation createShift( Number [] vec );
}
