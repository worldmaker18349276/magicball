package magicball.model.geometry.func;

import magicball.model.geometry.*;
import magicball.model.math.*;
import java.util.Arrays;


public class RegionEngineForFunc implements RegionBasicEngine
{
	protected SetBasicEngine setEngine;
	protected NumberBasicEngine mathEngine;

	public RegionEngineForFunc( NumberBasicEngine mathEng, SetBasicEngine setEng ) {
		this.mathEngine = mathEng;
		this.setEngine = setEng;
	}

	public RegionEngineForFunc clone() {
		return new RegionEngineForFunc(this.mathEngine,this.setEngine);
	}

	protected SurfaceFuncExpression cast( Surface face ) {
		return (SurfaceFuncExpression) face;
	}

	protected RegionSetExpression cast( Region reg ) {
		return (RegionSetExpression) reg;
	}

	protected RegionSetExpression[] cast( Region[] reg_ ) {
		return Arrays.copyOf(reg_,reg_.length,RegionSetExpression[].class);
	}

	@SuppressWarnings({"unchecked"})
	protected Set<Number[]> [] getSets( RegionSetExpression [] regs ) {
		Set<Number[]> [] sets = new Set [ regs.length ];
		for ( int i=0; i<regs.length; i++ )
			sets[i] = regs[i].getSet();
		return sets;
	}

	public Region intersect( Region... regs ) {
		return new RegionSetExpression(setEngine.intersect(getSets(cast(regs))));
	}

	public Region union( Region... regs ) {
		return new RegionSetExpression(setEngine.union(getSets(cast(regs))));
	}

	public Region complement( Region reg1, Region reg2 ) {
		return new RegionSetExpression(setEngine.complement(cast(reg1).getSet(),cast(reg2).getSet()));
	}

	public Region complement( Region reg2 ) {
		return new RegionSetExpression(setEngine.complement(cast(reg2).getSet()));
	}

	public Region createUniverseRegion() {
		return new RegionSetExpression(setEngine.<Number[]>createUniverseSet());
	}

	public Region createEmptyRegion() {
		return new RegionSetExpression(setEngine.<Number[]>createEmptySet());
	}

	public Region createRegionByFace( final Surface face, final int side ) {
		final NumberBasicEngine math = this.mathEngine;
		return new RegionSetExpression(setEngine.createSetByIntensionalDefinition(new Function<Number[],Boolean>() {
			public Boolean apply( Number [] vec ) {
				return math.greaterThan(
					math.multiply( cast(face).getFunction().apply(vec), math.number(side) ),
					math.number0());
			}
		}));
	}

	public boolean contain( Region reg, Number[] point ) {
		return cast(reg).getSet().isElement(point);
	}
}
