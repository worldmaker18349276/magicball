package magicball.model;


public abstract class Solid
{
	public boolean equals( Object sol ) {
		if ( sol instanceof Solid )
			return equals((Solid) sol)
		else
			return false;
	}

	public abstract boolean equals( Solid sol );
	public abstract Solid clone();

	public abstract void apply( Transform trans );
	public abstract Region getRegion();
}
