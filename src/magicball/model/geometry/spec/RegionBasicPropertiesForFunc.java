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
	private FunctionBasicEngine funcEngine;
	private ArbitraryTransformationBasicProperty.Operator transOperator;
	private ArbitraryTransformationBasicProperty.Attribute transAttribute;

	public RegionBasicPropertiesForFunc() {
		super();
	}

	public RegionBasicPropertiesForFunc( FunctionBasicEngine funcEng, ArbitraryTransformationBasicProperty.Operator transOp, ArbitraryTransformationBasicProperty.Attribute transAttr ) {
		super();
		setEngine(funcEng);
		setEngine(transOp);
		setEngine(transAttr);
	}

	public void setEngine( FunctionBasicEngine funcEng ) {
		funcEngine = funcEng;
	}

	public void setEngine( ArbitraryTransformationBasicProperty.Operator transOp ) {
		transOperator = transOp;
	}

	public void setEngine( ArbitraryTransformationBasicProperty.Attribute transAttr ) {
		transAttribute = transAttr;
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
		return region(funcEngine.<Num[],Boolean>createConstantFunctionWithValue(Boolean.TRUE));
	}

	@Override
	public Region createEmptyRegion() {
		return region(funcEngine.<Num[],Boolean>createConstantFunctionWithValue(Boolean.FALSE));
	}


	// attribute
	@Override
	public boolean contains( Region reg, Num[] point ) {
		return funcEngine.applyTo(function(reg),point);
	}

	@Override
	public boolean containsAll( Region reg1, Region reg2 ) {
		return funcEngine.implies(function(reg2),function(reg1));
	}


	// operator
	@Override
	public Region intersect( Region reg1, Region reg2 ) {
		return region( funcEngine.and(function(reg1),function(reg2)) );
	}

	@Override
	public Region intersect( Region... regs ) {
		return Stream.of(regs)
			.map(this::function)
			.reduce(funcEngine::and)
			.map(this::region)
			.get();
	}

	@Override
	public Region union( Region reg1, Region reg2 ) {
		return region( funcEngine.or(function(reg1),function(reg2)) );
	}

	@Override
	public Region union( Region... regs ) {
		return Stream.of(regs)
			.map(this::function)
			.reduce(funcEngine::or)
			.map(this::region)
			.get();
	}

	@Override
	public Region complement( Region reg1, Region reg2 ) {
		return region( funcEngine.not(function(reg1),function(reg2)) );
	}

	@Override
	public Region complement( Region reg ) {
		return region( funcEngine.not(function(reg)) );
	}

	@Override
	public Region transformsBy( Region reg, Transformation trans ) {
		Func<Num[],Num[]> trans_func = transAttribute.getTransformationFunctionOf(transOperator.invert(trans));
		Func<Num[],Boolean> reg_func = funcEngine.compose(trans_func,function(reg));
		return region(reg_func);
	}

	@Override
	public boolean equals( Region reg1, Region reg2 ) {
		return funcEngine.equals(function(reg1), function(reg2));
	}

	@Override
	public boolean isEmpty( Region reg ) {
		return funcEngine.isAlwaysFalse(function(reg));
	}

	@Override
	public boolean isUniversal( Region reg ) {
		return funcEngine.isAlwaysTrue(function(reg));
	}
}
