package magicball.model;


public abstract class Solid
{
	public boolean isSameShape( Solid sol ) {
		return getRegion().equals(sol.getRegion());
	}

	public boolean equals( Object sol ) {
		if ( sol instanceof Solid )
			return equals((Solid) sol);
		else
			return false;
	}
	
	public abstract boolean equals( Solid sol );
	public abstract Solid clone();

	public abstract void apply( Displacement dis );
	public abstract Region getRegion();
}
