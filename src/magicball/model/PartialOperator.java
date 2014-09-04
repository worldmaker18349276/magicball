package magicball.model;


public abstract class PartialOperator < Op extends Operator > extends Operator
{
	public abstract Filter getFilter();
	public abstract Op getOperator();
}

