package magicball.model.puzzle;

import magicball.model.geometry.*;


// definition: a geometric object that occupy a specific region and is rigid.
public class Solid < R extends Region >
{
	final private R region;

	public Solid( R reg ) {
		this.region = reg;
	}

	public Solid<R> clone() {
		return new Solid<R>(getOccupiedRegion());
	}

	final public R getOccupiedRegion() {
		return this.region;
	}
}
