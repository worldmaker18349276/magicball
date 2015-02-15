package magicball.model.puzzle;

import magicball.model.geometry.*;


// definition: a geometric operation that continously move geometric object in space.
public class RegionalMotion
{
	protected Region region;
	protected Motion move;

	public RegionalMotion( Region reg, Motion m ) {
		this.region = reg;
		this.move = m;
	}

	public Region getRegion() {
		return this.region;
	}

	public Motion getMotion() {
		return this.move;
	}
}

