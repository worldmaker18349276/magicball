package magicball.model;


public interface PartialOperator < Op extends Operator > extends Operator
{
	public abstract Filter getFilter();
	public abstract Op getOperator();
}

