package magicball.model;

import java.util.List;


public interface ContinuousOperator < Op extends Operator > extends SequenceOperator<Op>
{
	public abstract List<Op> DividedBy( int divisor );
	public abstract List<Op> Divided();
}

