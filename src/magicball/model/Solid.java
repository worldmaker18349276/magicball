package magicball.model;


public abstract class Solid
{
	public abstract void apply( Transform trans );
	public abstract Region getRegion();

	public abstract boolean equals( Solid sol );

	public abstract Solid clone();
}
