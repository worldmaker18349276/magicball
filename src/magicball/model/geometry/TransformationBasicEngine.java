package magicball.model.geometry;


public interface TransformationBasicEngine < T extends Transformation >
{
	public TransformationBasicEngine clone();
	public T createIdentityTransformation();
	public T compose( T[] trans );
	public T invert( T trans );
	public T createRotation( Number [] axis, Number deg );
	public T createShift( Number [] vec );
}
