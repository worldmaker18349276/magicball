package magicball.model.math.basic;

import java.util.stream.*;

import magicball.model.*;
import magicball.model.math.*;


// base on lambda expression
public class DefaultBooleanFunctionPredicate implements SpecEngine<Func,Func>,
		BooleanFunctionBasicProperty.Predicate
{
	private ArbitraryFunctionBasicProperty.Predicate funcPredicate;
	private BooleanFunctionBasicProperty.Operator predOperator;

	public DefaultBooleanFunctionPredicate() {
		super();
	}

	public DefaultBooleanFunctionPredicate( ArbitraryFunctionBasicProperty.Predicate funcPred, BooleanFunctionBasicProperty.Operator predOp ) {
		super();
		setEngine(funcPred);
		setEngine(predOp);
	}

	public void setEngine( ArbitraryFunctionBasicProperty.Predicate funcPred ) {
		funcPredicate = funcPred;
	}

	public void setEngine( BooleanFunctionBasicProperty.Operator predOp ) {
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
