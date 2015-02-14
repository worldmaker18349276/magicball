package magicball.model.geometry.basic;

import magicball.model.geometry.*;
import magicball.model.math.*;
import magicball.model.*;
import java.util.Arrays;


public class RegionEngineForFunc implements RegionEngine
{
	protected SetEngine setEngine;
	protected NumberEngine mathEngine;
	protected FunctionEngine funcEngine;
	protected SurfaceEngine faceEngine;
	protected TransformationEngine transEngine;

	public RegionEngineForFunc( NumberEngine mathEng, SetEngine setEng, FunctionEngine funcEng, SurfaceEngine faceEng, TransformationEngine transEng ) {
		this.mathEngine = mathEng;
		this.setEngine = setEng;
		this.funcEngine = funcEng;
		this.faceEngine = faceEng;
		this.transEngine = transEng;
	}

	public RegionEngineForFunc( EngineProvider provider ) {
		this.mathEngine = provider.getNumberEngine();
		this.setEngine = provider.getSetEngine();
		this.funcEngine = provider.getFunctionEngine();
		this.faceEngine = provider.getSurfaceEngine();
		this.transEngine = provider.getTransformationEngine();
	}

	@Override
	public RegionEngineForFunc clone() {
		return new RegionEngineForFunc(this.mathEngine,this.setEngine,this.funcEngine,this.faceEngine,this.transEngine);
	}

	protected RegionSetExpression cast( Region reg ) {
		try {
			return (RegionSetExpression) reg;
		} catch ( ClassCastException e ) {
			throw new UnsupportedExpressionException(reg.getClass());
		}
	}


	// creater
	public Region createRegionBySet( Set<Number[]> set ) {
		return new RegionSetExpression(set);
	}

	@Override
	public Region createRegionByFunction( Function<Number[],Boolean> func ) {
		return createRegionBySet(setEngine.createSetByFunction(func));
	}

	@Override
	public Region createRegionByFace( Surface face, int side ) {
		Function<Number[],Number> func = faceEngine.getIsosurfaceFunction(face);
		return createRegionByFunction(funcEngine.function(
				vec -> mathEngine.greaterThan(
					mathEngine.multiply( funcEngine.applies(func,vec), mathEngine.number(side) ),
					mathEngine.number0())
		));
	}

	@Override
	public Region createUniversalRegion() {
		return createRegionBySet(setEngine.<Number[]>createUniversalSet());
	}

	@Override
	public Region createEmptyRegion() {
		return createRegionBySet(setEngine.<Number[]>createEmptySet());
	}


	// attribute
	public Set<Number[]> getRegionSet( Region reg ) {
		return cast(reg).getSet();
	}

	@SuppressWarnings({"unchecked"})
	public Set<Number[]> [] getRegionSets( Region [] regs ) {
		Set<Number[]> [] sets = new Set [ regs.length ];
		for ( int i=0; i<regs.length; i++ )
			sets[i] = getRegionSet(regs[i]);
		return sets;
	}

	@Override
	public Function<Number[],Boolean> getRegionIntensionFunction( Region reg ) {
		return setEngine.getIntensionFunction(getRegionSet(reg));
	}

	@Override
	public boolean contains( Region reg, Number[] point ) {
		return setEngine.contains(getRegionSet(reg),point);
	}

	@Override
	public boolean containsAll( Region reg1, Region reg2 ) {
		return this.setEngine.containsAll(getRegionSet(reg1),getRegionSet(reg2));
	}


	// operator
	@Override
	public Region intersect( Region... regs ) {
		return createRegionBySet(setEngine.intersect(getRegionSets(regs)));
	}

	@Override
	public Region union( Region... regs ) {
		return createRegionBySet(setEngine.union(getRegionSets(regs)));
	}

	@Override
	public Region complement( Region reg1, Region reg2 ) {
		return createRegionBySet(setEngine.complement(getRegionSet(reg1),getRegionSet(reg2)));
	}

	@Override
	public Region complement( Region reg2 ) {
		return createRegionBySet(setEngine.complement(getRegionSet(reg2)));
	}

	@Override
	public Region transformsBy( Region reg, Transformation trans ) {
		Transformation _trans = this.transEngine.invert(trans);
		Function<Number[],Number[]> trans_func = this.transEngine.getTransformationFunction(_trans);
		Function<Number[],Boolean> reg_func = getRegionIntensionFunction(reg);
		Function<Number[],Boolean> reg_func_ = this.funcEngine.compose(trans_func,reg_func);
		return createRegionByFunction(reg_func_);
	}

	@Override
	public Region reflectsBy( Region reg, Reflection ref ) {
		Function<Number[],Number[]> ref_func = this.transEngine.getReflectionFunction(ref);
		Function<Number[],Boolean> reg_func = getRegionIntensionFunction(reg);
		Function<Number[],Boolean> reg_func_ = this.funcEngine.compose(ref_func,reg_func);
		return createRegionByFunction(reg_func_);
	}

	@Override
	public boolean isEmpty( Region reg ) {
		return this.setEngine.isEmpty(getRegionSet(reg));
	}

	@Override
	public boolean isUniversal( Region reg ) {
		return this.setEngine.isUniversal(getRegionSet(reg));
	}

	@Override
	public boolean equals( Region reg1, Region reg2 ) {
		return this.setEngine.equals(getRegionSet(reg1),getRegionSet(reg2));
	}
}
