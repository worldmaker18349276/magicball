package magicball.model;


public abstract class Region
{
	public abstract boolean inside( Vector v );
	public abstract void apply( Displacement dis );
	public abstract Region clone();
}
