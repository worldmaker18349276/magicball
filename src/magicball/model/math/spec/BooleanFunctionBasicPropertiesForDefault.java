package magicball.model.math.spec;

import java.util.stream.*;

import magicball.model.*;
import magicball.model.math.*;


// base on lambda expression
public class BooleanFunctionBasicPropertiesForDefault implements SpecEngine<Func,Func>,
		BooleanFunctionBasicProperty.Creator,
		BooleanFunctionBasicProperty.Predicate

{
	protected ArbitraryFunctionBasicProperty.Creator funcCreator;
	protected ArbitraryFunctionBasicProperty.Predicate funcPredicate;
	protected BooleanFunctionBasicProperty.Operator predOperator;

	public BooleanFunctionBasicPropertiesForDefault() {
	}

	public BooleanFunctionBasicPropertiesForDefault(
			ArbitraryFunctionBasicProperty.Creator funcC,
			ArbitraryFunctionBasicProperty.Predicate funcP,
			BooleanFunctionBasicProperty.Operator predO ) {
		setEngine(funcC);
		setEngine(funcP);
		setEngine(predO);
	}

	public void setEngine( ArbitraryFunctionBasicProperty.Creator funcC ) {
		funcCreator = funcC;
	}

	public void setEngine( ArbitraryFunctionBasicProperty.Predicate funcP ) {
		funcPredicate = funcP;
	}

	public void setEngine( BooleanFunctionBasicProperty.Operator predO ) {
		predOperator = predO;
	}


	// creator
	public < I > Func<I,Boolean> createTrueFunction() {
		return funcCreator.createConstantFunctionWithValue(Boolean.TRUE);
	}

	public < I > Func<I,Boolean> createFalseFunction() {
		return funcCreator.createConstantFunctionWithValue(Boolean.FALSE);
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
