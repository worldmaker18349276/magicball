package magicball.model;


public interface PartialOperator < Op extends Operator > extends Operator
{
	public Filter getFilter();
	public Op getOperator();
}

