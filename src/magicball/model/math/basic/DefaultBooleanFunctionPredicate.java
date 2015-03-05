package magicball.model.math.basic;

import java.util.stream.*;

import magicball.model.*;
import magicball.model.math.*;


// base on lambda expression
public class DefaultBooleanFunctionPredicate implements SpecEngine<Func,Func>,
		BooleanFunctionBasic.Predicate
{
	private FunctionBasic.Predicate funcPredicate;
	private BooleanFunctionBasic.Operator predOperator;

	public DefaultBooleanFunctionPredicate() {
		super();
	}

	public DefaultBooleanFunctionPredicate( FunctionBasic.Predicate funcPred, BooleanFunctionBasic.Operator predOp ) {
		super();
		setEngine(funcPred);
		setEngine(predOp);
	}

	public void setEngine( FunctionBasic.Predicate funcPred ) {
		funcPredicate = funcPred;
	}

	public void setEngine( BooleanFunctionBasic.Operator predOp ) {
		predOperator = predOp;
	}


	// operator
	@Override
	public < I > boolean isAlwaysTrue( Func<I,Boolean> func ) {
		return funcPredicate.isAlwaysEqualTo(func, Boolean.TRUE);
	}

	@Override
	public < I > boolean isAlwaysFalse( Func<I,Boolean> func ) {
		return funcPredicate.isAlwaysEqualTo(func, Boolean.FALSE);
	}

	@Override
	public < I > boolean implies( Func<I,Boolean> func1, Func<I,Boolean> func2 ) {
		return isAlwaysFalse(predOperator.not(func1,func2));
	}
}
