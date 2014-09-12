package magicball.model.geometry;


public interface TransformationBasicEngine < T extends Transformation >
{
	public TransformationBasicEngine clone();
	public T compose( T[] trans );
	public T pow( T trans, int exp );
	public T dividedBy( T trans, int divisor );
	public T invert( T trans );
	public T createIdentityTransformation();
	public boolean isIdentity( T trans );
	public boolean isRotation( T trans );
	public boolean isShift( T trans );
}
