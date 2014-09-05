package magicball.model;

import java.util.List;


public interface ContinuousOperator < Op extends Operator > extends SequenceOperator<Op>
{
	public List<Op> DividedBy( int divisor );
	public List<Op> Divided();
}

