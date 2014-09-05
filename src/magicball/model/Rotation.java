package magicball.model;


public abstract class Rotation extends Displacement
{
	public abstract Rotation divide( int divisor );
	public abstract Shift getShift();

	public Rotation getRotation() {
		return this;
	}

	public abstract Vector getAxisVector();
	public abstract Degree getRotationDegree();
}

