package magicball.model.geometry.spec;

import java.util.stream.Stream;

import magicball.model.geometry.*;
import magicball.model.math.*;
import magicball.model.*;


public class RegionBasicPropertiesForFunc implements SpecEngine<Region,RegionFuncExpression>,
		ArbitraryRegionBasicProperty.Behavior,
		ArbitraryRegionBasicProperty.Creator,
		ArbitraryRegionBasicProperty.Operator,
		ArbitraryRegionBasicProperty.Predicate
{
	protected ArbitraryFunctionBasicProperty.Behavior funcBehavior;
	protected ArbitraryFunctionBasicProperty.Operator funcOperator;
	protected ArbitraryFunctionBasicProperty.Predicate funcPredicate;
	protected BooleanFunctionBasicProperty.Creator predCreator;
	protected BooleanFunctionBasicProperty.Operator predOperator;
	protected BooleanFunctionBasicProperty.Predicate predPredicate;
	protected ArbitraryTransformationBasicProperty.Operator transOperator;
	protected ArbitraryTransformationBasicProperty.Attribute transAttribute;

	public RegionBasicPropertiesForFunc() {
	}

	public <F extends ArbitraryFunctionBasicProperty.Behavior &
					ArbitraryFunctionBasicProperty.Operator &
					ArbitraryFunctionBasicProperty.Predicate &
					BooleanFunctionBasicProperty.Creator &
					BooleanFunctionBasicProperty.Operator &
					BooleanFunctionBasicProperty.Predicate,
			T extends ArbitraryTransformationBasicProperty.Operator &
					ArbitraryTransformationBasicProperty.Attribute> RegionBasicPropertiesForFunc( F funcEng, T transEng ) {
		setEngine((ArbitraryFunctionBasicProperty.Behavior)funcEng);
		setEngine((ArbitraryFunctionBasicProperty.Operator)funcEng);
		setEngine((ArbitraryFunctionBasicProperty.Predicate)funcEng);
		setEngine((BooleanFunctionBasicProperty.Creator)funcEng);
		setEngine((BooleanFunctionBasicProperty.Operator)funcEng);
		setEngine((BooleanFunctionBasicProperty.Predicate)funcEng);
		setEngine((ArbitraryTransformationBasicProperty.Operator)transEng);
		setEngine((ArbitraryTransformationBasicProperty.Attribute)transEng);
	}

	public RegionBasicPropertiesForFunc(
			ArbitraryFunctionBasicProperty.Behavior funcB,
			ArbitraryFunctionBasicProperty.Operator funcO,
			ArbitraryFunctionBasicProperty.Predicate funcP,
			BooleanFunctionBasicProperty.Creator predC,
			BooleanFunctionBasicProperty.Operator predO,
			BooleanFunctionBasicProperty.Predicate predP,
			ArbitraryTransformationBasicProperty.Operator transO,
			ArbitraryTransformationBasicProperty.Attribute transA ) {
		setEngine(funcB);
		setEngine(funcO);
		setEngine(funcP);
		setEngine(predC);
		setEngine(predO);
		setEngine(predP);
		setEngine(transO);
		setEngine(transA);
	}

	public void setEngine( ArbitraryFunctionBasicProperty.Behavior funcB ) {
		funcBehavior = funcB;
	}

	public void setEngine( ArbitraryFunctionBasicProperty.Operator funcO ) {
		funcOperator = funcO;
	}

	public void setEngine( ArbitraryFunctionBasicProperty.Predicate funcP ) {
		funcPredicate = funcP;
	}

	public void setEngine( BooleanFunctionBasicProperty.Creator predC ) {
		predCreator = predC;
	}

	public void setEngine( BooleanFunctionBasicProperty.Operator predO ) {
		predOperator = predO;
	}

	public void setEngine( BooleanFunctionBasicProperty.Predicate predP ) {
		predPredicate = predP;
	}


	public void setEngine( ArbitraryTransformationBasicProperty.Operator transO ) {
		transOperator = transO;
	}

	public void setEngine( ArbitraryTransformationBasicProperty.Attribute transA ) {
		transAttribute = transA;
	}


	private Func<Num[],Boolean> function( Region reg ) {
		return cast(reg).getFunction();
	}

	private Region region( Func<Num[],Boolean> func ) {
		return new RegionFuncExpression(func);
	}


	// creater
	@Override
	public Region createRegionByFunction( Func<Num[],Boolean> func ) {
		return region(func);
	}

	@Override
	public Region createUniversalRegion() {
		return region(predCreator.<Num[]>createTrueFunction());
	}

	@Override
	public Region createEmptyRegion() {
		return region(predCreator.<Num[]>createFalseFunction());
	}


	// attribute
	@Override
	public boolean contains( Region reg, Num[] point ) {
		return funcBehavior.applyTo(function(reg),point);
	}

	@Override
	public boolean containsAll( Region reg1, Region reg2 ) {
		return predPredicate.implies(function(reg2),function(reg1));
	}


	// operator
	@Override
	public Region intersect( Region reg1, Region reg2 ) {
		return region( predOperator.and(function(reg1),function(reg2)) );
	}

	@Override
	public Region intersect( Region... regs ) {
		return Stream.of(regs)
			.map(this::function)
			.reduce(predOperator::and)
			.map(this::region)
			.get();
	}

	@Override
	public Region union( Region reg1, Region reg2 ) {
		return region( predOperator.or(function(reg1),function(reg2)) );
	}

	@Override
	public Region union( Region... regs ) {
		return Stream.of(regs)
			.map(this::function)
			.reduce(predOperator::or)
			.map(this::region)
			.get();
	}

	@Override
	public Region complement( Region reg1, Region reg2 ) {
		return region( predOperator.not(function(reg1),function(reg2)) );
	}

	@Override
	public Region complement( Region reg ) {
		return region( predOperator.not(function(reg)) );
	}

	@Override
	public Region transformsBy( Region reg, Transformation trans ) {
		Func<Num[],Num[]> trans_func = transAttribute.getTransformationFunctionOf(transOperator.invert(trans));
		Func<Num[],Boolean> reg_func = funcOperator.compose(trans_func,function(reg));
		return region(reg_func);
	}

	@Override
	public boolean equals( Region reg1, Region reg2 ) {
		return funcPredicate.equals(function(reg1), function(reg2));
	}

	@Override
	public boolean isEmpty( Region reg ) {
		return predPredicate.isAlwaysFalse(function(reg));
	}

	@Override
	public boolean isUniversal( Region reg ) {
		return predPredicate.isAlwaysTrue(function(reg));
	}
}
