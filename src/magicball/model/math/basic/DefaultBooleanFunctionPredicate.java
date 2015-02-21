package magicball.model.math.basic;

import java.util.stream.*;

import io.netty.util.DefaultAttributeMap;

import magicball.model.*;
import magicball.model.math.*;


// base on lambda expression
public class DefaultBooleanFunctionPredicate extends DefaultAttributeMap implements BooleanFunctionPredicate, Engine<Function>
{
	public DefaultBooleanFunctionPredicate() {
		super();
	}

	public DefaultBooleanFunctionPredicate( FunctionBasicPredicate funcPred, BooleanFunctionOperator predOp ) {
		super();
		setEngine(funcPred);
		setEngine(predOp);
	}

	public void setEngine( FunctionBasicPredicate funcPred ) {
		attr(FunctionBasicPredicate.KEY).set(funcPred);
	}

	public void setEngine( BooleanFunctionOperator predOp ) {
		attr(BooleanFunctionOperator.KEY).set(predOp);
	}

	public FunctionBasicPredicate funcPredicate() {
		return attr(FunctionBasicPredicate.KEY).get();
	}

	public BooleanFunctionOperator predOperator() {
		return attr(BooleanFunctionOperator.KEY).get();
	}


	// operator
	@Override
	public < I > boolean isAlwaysTrue( Function<I,Boolean> func ) {
		return funcPredicate().isAlwaysEqualTo(func, Boolean.TRUE);
	}

	@Override
	public < I > boolean isAlwaysFalse( Function<I,Boolean> func ) {
		return funcPredicate().isAlwaysEqualTo(func, Boolean.FALSE);
	}

	@Override
	public < I > boolean implies( Function<I,Boolean> func1, Function<I,Boolean> func2 ) {
		return isAlwaysFalse(predOperator().not(func1,func2));
	}
}
