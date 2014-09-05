package magicball.model;


public abstract class Shift extends Displacement
{
	public abstract Shift divide( int divisor );
	public abstract Rotation getRotation();

	public Shift getShift() {
		return this;
	}

	public abstract Vector getShiftVector();
}

