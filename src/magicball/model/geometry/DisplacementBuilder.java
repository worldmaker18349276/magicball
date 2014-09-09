package magicball.model.geometry;


// geometry abstraction layer
public interface DisplacementBuilder
{
	public Displacement createIdentityDisplacement();
	public Displacement createCompositeDisplacement( Displacement... dis );
	public Displacement createInverseDisplacement( Displacement dis );
	public Displacement createRotation( Number [] axis, Number deg );
	public Displacement createShift( Number [] vec );
}
