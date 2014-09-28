package magicball.model.geometry.func;

import magicball.model.geometry.*;
import magicball.model.math.*;
import magicball.model.math.func.*;
import magicball.model.*;
import java.util.Arrays;


public class RegionBasicEngine implements RegionEngine
{
	protected SetEngine setEngine;
	protected NumberEngine mathEngine;
	protected FunctionEngine funcEngine;
	protected TransformationEngine transEngine;

	public RegionBasicEngine( double a, double eps ) {
		this.mathEngine = new NumberBasicEngine(eps);
		double d = 2*eps;
		java.util.Set<Number[]> grid = new java.util.HashSet<Number[]>();
		for ( double x=-a; x<=a; x=x+d )
			for ( double y=-a; y<=a; y=y+d )
				for ( double z=-a; z<=a; z=z+d )
					grid.add(mathEngine.vector(x,y,z));
		this.funcEngine = new FunctionBasicEngine();
		this.setEngine = new SetEngineSampleAlgorithm<Number[]>(this.funcEngine,grid);
		this.transEngine = new TransformationBasicEngine(this.mathEngine,this.funcEngine);
	}

	public RegionBasicEngine( NumberEngine mathEng, SetEngine setEng, FunctionEngine funcEng, TransformationEngine transEng ) {
		this.mathEngine = mathEng;
		this.setEngine = setEng;
		this.funcEngine = funcEng;
		this.transEngine = transEng;
	}

	public RegionBasicEngine clone() {
		return new RegionBasicEngine(this.mathEngine,this.setEngine,this.funcEngine,this.transEngine);
	}

	protected SurfaceFuncExpression cast( Surface face ) {
		try {
			return (SurfaceFuncExpression) face;
		} catch ( ClassCastException e ) {
			throw new UnsupportedExpressionException(face.getClass());
		}
	}

	protected SetFunctionExpression<Number[]> cast( Set<Number[]> set ) {
		try {
			return (SetFunctionExpression<Number[]>) set;
		} catch ( ClassCastException e ) {
			throw new UnsupportedExpressionException(set.getClass());
		}
	}

	protected RegionSetExpression cast( Region reg ) {
		try {
			return (RegionSetExpression) reg;
		} catch ( ClassCastException e ) {
			throw new UnsupportedExpressionException(reg.getClass());
		}
	}

	protected RegionSetExpression[] cast( Region[] reg ) {
		try {
			return Arrays.copyOf(reg,reg.length,RegionSetExpression[].class);
		} catch ( ClassCastException e ) {
			throw new UnsupportedExpressionException(reg.getClass());
		}
	}

	@SuppressWarnings({"unchecked"})
	protected Set<Number[]> [] getSets( RegionSetExpression [] regs ) {
		Set<Number[]> [] sets = new Set [ regs.length ];
		for ( int i=0; i<regs.length; i++ )
			sets[i] = regs[i].getSet();
		return sets;
	}

	public Region intersect( Region... regs ) {
		return createRegionBySet(setEngine.intersect(getSets(cast(regs))));
	}

	public Region union( Region... regs ) {
		return createRegionBySet(setEngine.union(getSets(cast(regs))));
	}

	public Region complement( Region reg1, Region reg2 ) {
		return createRegionBySet(setEngine.complement(cast(reg1).getSet(),cast(reg2).getSet()));
	}

	public Region complement( Region reg2 ) {
		return createRegionBySet(setEngine.complement(cast(reg2).getSet()));
	}

	public Region createUniversalRegion() {
		return createRegionBySet(setEngine.<Number[]>createUniversalSet());
	}

	public Region createEmptyRegion() {
		return createRegionBySet(setEngine.<Number[]>createEmptySet());
	}

	public Region createRegionBySet( Set<Number[]> set ) {
		return new RegionSetExpression(set);
	}

	public Region createRegionByFunction( Function<Number[],Boolean> func ) {
		return createRegionBySet(setEngine.createSetByFunction(func));
	}

	public Region createRegionByLambda( LambdaFunction<Number[],Boolean> lambda ) {
		return createRegionByFunction(funcEngine.createFunctionByLambda(lambda));
	}

	public Region createRegionByFace( final Surface face, final int side ) {
		final NumberEngine math = this.mathEngine;
		final FunctionEngine funcEng = this.funcEngine;
		final Function<Number[],Number> func = cast(face).getFunction();
		return createRegionByLambda(new LambdaFunction<Number[],Boolean>() {
			public Boolean apply( Number [] vec ) {
				return math.greaterThan(
					math.multiply( funcEng.applies(func,vec), math.number(side) ),
					math.number0());
			}
		});
	}

	public boolean isEmpty( Region reg ) {
		return this.setEngine.isEmpty(cast(reg).getSet());
	}

	public boolean isUniversal( Region reg ) {
		return this.setEngine.isUniversal(cast(reg).getSet());
	}

	public boolean equals( Region reg1, Region reg2 ) {
		return this.setEngine.equals(cast(reg1).getSet(),cast(reg2).getSet());
	}

	public boolean contains( Region reg, Number[] point ) {
		return setEngine.contains(cast(reg).getSet(),point);
	}

	public boolean containsAll( Region reg1, Region reg2 ) {
		return this.setEngine.containsAll(cast(reg1).getSet(),cast(reg2).getSet());
	}

	public Region transformsBy( Region reg, Transformation trans ) {
		Transformation _trans = this.transEngine.invert(trans);
		Function<Number[],Number[]> trans_func = this.transEngine.createTransformationFunction(_trans);
		Function<Number[],Boolean> reg_func = cast(cast(reg).getSet()).getFunction();
		Function<Number[],Boolean> reg_func_ = this.funcEngine.compose(trans_func,reg_func);
		return createRegionByFunction(reg_func_);
	}

	public Region reflectsBy( Region reg, Reflection ref ) {
		Function<Number[],Number[]> ref_func = this.transEngine.createReflectionFunction(ref);
		Function<Number[],Boolean> reg_func = cast(cast(reg).getSet()).getFunction();
		Function<Number[],Boolean> reg_func_ = this.funcEngine.compose(ref_func,reg_func);
		return createRegionByFunction(reg_func_);
	}
}
