package magicball.model;


public class Solid
{
	protected Region region;

	public Solid( Region reg ) {
		this.region = reg;
	}

	public Solid clone() {
		return new Solid(getRegion().clone());
	}

	public Region getRegion() {
		return this.region;
	}

	public boolean equals( Object sol ) {
		if ( sol instanceof Solid )
			return equals((Solid) sol);
		else
			return false;
	}

	public boolean equals( Solid sol ) {
		return getRegion().equals(sol.getRegion());
	}

	public void apply( Displacement dis ) {
		getRegion().apply(dis);
	}
}
