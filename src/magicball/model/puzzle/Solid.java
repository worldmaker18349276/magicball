package magicball.model.puzzle;

import magicball.model.geometry.*;


// definition: a geometric object that occupy a specific region and is rigid.
public class Solid
{
	final private Region region;

	public Solid( Region reg ) {
		this.region = reg;
	}

	public Solid clone() {
		return new Solid(getOccupiedRegion());
	}

	public Region getOccupiedRegion() {
		return this.region;
	}
}
