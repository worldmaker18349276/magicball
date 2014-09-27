package magicball.model.puzzle;

import magicball.model.geometry.*;


// definition: a geometric operation that continously move geometric object in space.
public class RegionalMovement
{
	protected Region region;
	protected Movement move;

	public RegionalMovement( Region reg, Movement m ) {
		this.region = reg;
		this.move = m;
	}

	public Region getRegion() {
		return this.region;
	}

	public Movement getMovement() {
		return this.move;
	}
}

