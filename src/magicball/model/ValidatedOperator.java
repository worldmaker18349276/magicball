package magicball.model;

import java.util.List;


public interface ValidatedOperator < Op extends Operator > extends Operator
{
	public Op getOperator();
}

