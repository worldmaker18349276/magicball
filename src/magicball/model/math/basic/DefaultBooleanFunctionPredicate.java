package magicball.model.math.basic;

import java.util.stream.*;

import magicball.model.*;
import magicball.model.math.*;


// base on lambda expression
public class DefaultBooleanFunctionPredicate implements BooleanFunctionPredicate, SpecEngine<Function,Function>
{
	private FunctionPredicate funcPredicate;
	private BooleanFunctionOperator predOperator;

	public DefaultBooleanFunctionPredicate() {
		super();
	}

	public DefaultBooleanFunctionPredicate( FunctionPredicate funcPred, BooleanFunctionOperator predOp ) {
		super();
		setEngine(funcPred);
		setEngine(predOp);
	}

	public void setEngine( FunctionPredicate funcPred ) {
		funcPredicate = funcPred;
	}

	public void setEngine( BooleanFunctionOperator predOp ) {
		predOperator = predOp;
	}

	// operator
	@Override
	public < I > boolean isAlwaysTrue( Function<I,Boolean> func ) {
		return funcPredicate.isAlwaysEqualTo(func, Boolean.TRUE);
	}

	@Override
	public < I > boolean isAlwaysFalse( Function<I,Boolean> func ) {
		return funcPredicate.isAlwaysEqualTo(func, Boolean.FALSE);
	}

	@Override
	public < I > boolean implies( Function<I,Boolean> func1, Function<I,Boolean> func2 ) {
		return isAlwaysFalse(predOperator.not(func1,func2));
	}
}
