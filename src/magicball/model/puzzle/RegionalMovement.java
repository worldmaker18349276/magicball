package magicball.model.puzzle;

import magicball.model.geometry.*;


// definition: a geometric operation that continously move geometric object in space.
public class RegionalMovement < M extends Movement, R extends Region >
{
	protected R region;
	protected M move;

	public RegionalMovement( R reg, M m ) {
		this.region = reg;
		this.move = m;
	}

	public R getRegion() {
		return this.region;
	}

	public M getMovement() {
		return this.move;
	}
}

