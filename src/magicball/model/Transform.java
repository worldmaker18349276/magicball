package magicball.model;

import java.util.List;


public abstract class Transform implements ContinuousOperator<ValidatedOperator<Displacement>>
{
	public abstract List<ValidatedOperator<Displacement>> dividedBy( int divisor );
	public abstract List<ValidatedOperator<Displacement>> divided();
}

