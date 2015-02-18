package magicball.model.geometry.basic;

import java.util.stream.*;

import magicball.model.geometry.*;
import magicball.model.math.*;
import magicball.model.*;
import java.util.Arrays;


public class RegionEngineForFunc implements RegionBasicEngine, Engine<RegionFuncExpression>
{
	protected NumberAdvancedEngine mathEngine;
	protected FunctionAdvancedEngine funcEngine;
	protected TransformationAdvancedEngine transEngine;

	public RegionEngineForFunc( NumberAdvancedEngine mathEng, FunctionAdvancedEngine funcEng, TransformationAdvancedEngine transEng ) {
		this.mathEngine = mathEng;
		this.funcEngine = funcEng;
		this.transEngine = transEng;
	}

	public RegionEngineForFunc( BasicEngineProvider provider ) {
		this.mathEngine = provider.getNumberEngine();
		this.funcEngine = provider.getFunctionEngine();
		this.transEngine = provider.getTransformationEngine();
	}

	@Override
	public RegionEngineForFunc clone() {
		return new RegionEngineForFunc(this.mathEngine,this.funcEngine,this.transEngine);
	}


	// creater
	@Override
	public Region createRegionByFunction( Function<Number[],Boolean> func ) {
		return new RegionFuncExpression(func);
	}

	@Override
	public Region createUniversalRegion() {
		return createRegionByFunction(funcEngine.<Number[],Boolean>createConstantFunction(true));
	}

	@Override
	public Region createEmptyRegion() {
		return createRegionByFunction(funcEngine.<Number[],Boolean>createConstantFunction(false));
	}


	// attribute
	private Function<Number[],Boolean> function( Region reg ) {
		return cast(reg).getFunction();
	}

	@Override
	public boolean contains( Region reg, Number[] point ) {
		return funcEngine.applies(function(reg),point);
	}

	@Override
	public boolean containsAll( Region reg1, Region reg2 ) {
		return funcEngine.implies(function(reg2),function(reg1));
	}


	// operator
	@Override
	public Region intersect( Region... regs ) {
		return Stream.of(regs)
			.map(this::function)
			.reduce(funcEngine::and)
			.map(this::createRegionByFunction)
			.get();
	}

	@Override
	public Region union( Region... regs ) {
		return Stream.of(regs)
			.map(this::function)
			.reduce(funcEngine::or)
			.map(this::createRegionByFunction)
			.get();
	}

	@Override
	public Region complement( Region reg1, Region reg2 ) {
		return createRegionByFunction( funcEngine.not(function(reg1),function(reg2)) );
	}

	@Override
	public Region complement( Region reg2 ) {
		return createRegionByFunction( funcEngine.not(function(reg2)) );
	}

	@Override
	public Region transformsBy( Region reg, Transformation trans ) {
		Transformation _trans = transEngine.invert(trans);
		Function<Number[],Number[]> trans_func = transEngine.getTransformationFunction(_trans);
		Function<Number[],Boolean> reg_func = function(reg);
		Function<Number[],Boolean> reg_func_ = funcEngine.compose(trans_func,reg_func);
		return createRegionByFunction(reg_func_);
	}

	@Override
	public boolean isEmpty( Region reg ) {
		return funcEngine.isAlwaysFalse(function(reg));
	}

	@Override
	public boolean isUniversal( Region reg ) {
		return funcEngine.isAlwaysTrue(function(reg));
	}

	@Override
	public boolean equals( Region reg1, Region reg2 ) {
		return funcEngine.equals(function(reg1), function(reg2));
	}
}
