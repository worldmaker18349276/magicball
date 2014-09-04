package magicball.model;

import java.util.List;


public abstract class Transform
{
	public abstract List<Transform> getDividedTransform( int divisor ); // continuous operator
	public abstract List<Transform> getDividedTransform();
}

