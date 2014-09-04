package magicball.model;


public abstract class RegionalTransform extends PartialOperator<Transform>
{
	public abstract Region getFilter();
	public abstract Transform getOperator();
}

