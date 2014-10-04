package magicball.model.puzzle;

import magicball.model.geometry.*;


// definition: a geometric object that occupy a specific region and is rigid.
public class Solid
{
	private Region region;

	public Solid( Region reg ) {
		setRegion(reg);
	}

	public Solid clone() {
		return new Solid(getRegion());
	}

	public Region getRegion() {
		return this.region;
	}

	public void setRegion( Region reg ) {
		this.region = reg;
	}
}
