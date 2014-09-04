package magicball.model;

import java.util.List;


public interface SequenceOperator < Op extends Operator > extends Operator
{
	public abstract List<Op> divided();
}

