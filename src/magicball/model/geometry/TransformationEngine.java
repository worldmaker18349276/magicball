package magicball.model.geometry;


public interface TransformationEngine < T extends Transformation >
{
	public T cast( Transformation trans );
	public T createIdentityTransformation();
	public T compose( T... trans );
	public T invert( T trans );
	public T createRotation( Number [] axis, Number deg );
	public T createShift( Number [] vec );
}
