package magicball.model;


public abstract class Displacement
{
	public abstract Displacement divide( int divisor );
	public abstract Shift getShift();
	public abstract Rotation getRotation();
}

