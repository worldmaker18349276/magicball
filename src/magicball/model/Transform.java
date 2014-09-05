package magicball.model;

import java.util.List;


public abstract class Transform
{
	public abstract Displacement getDisplacement();
	public abstract List<Displacement> dividedIntoDisplacement( int divisor ); // continuous operator
	public abstract List<Displacement> dividedIntoDisplacement();
}

