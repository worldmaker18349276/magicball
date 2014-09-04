package magicball.model;

import java.util.List;


public abstract class Transform implements ContinuousOperator<Displacement>
{
	public abstract List<Displacement> dividedBy( int divisor );
	public abstract List<Displacement> divided();
}

