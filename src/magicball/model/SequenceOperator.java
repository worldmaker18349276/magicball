package magicball.model;

import java.util.List;


public abstract class SequenceOperator < Op extends Operator > extends Operator
{
	public abstract List<Op> Divided();
}

