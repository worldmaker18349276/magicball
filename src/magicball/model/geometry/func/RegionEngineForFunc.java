package magicball.model.geometry.func;

import magicball.model.geometry.*;
import magicball.model.math.*;
import java.util.Arrays;
import java.lang.reflect.Array;


public class RegionEngineForFunc implements RegionBasicEngine<RegionSetExpression,SurfaceFuncExpression>
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

	@SuppressWarnings({"unchecked"})
	protected Set<Number[]> [] getSets( RegionSetExpression [] regs ) {
		Set<Number[]> [] sets = new Set [ regs.length ];
		for ( int i=0; i<regs.length; i++ )
			sets[i] = regs[i].getSet();
		return sets;
	}

	public RegionSetExpression intersect( RegionSetExpression... regs ) {
		return new RegionSetExpression(setEngine.intersect(getSets(regs)));
	}

	public RegionSetExpression union( RegionSetExpression... regs ) {
		return new RegionSetExpression(setEngine.union(getSets(regs)));
	}

	public RegionSetExpression complement( RegionSetExpression reg1, RegionSetExpression reg2 ) {
		return new RegionSetExpression(setEngine.complement(reg1.getSet(),reg2.getSet()));
	}

	public RegionSetExpression complement( RegionSetExpression reg2 ) {
		return new RegionSetExpression(setEngine.complement(reg2.getSet()));
	}

	public RegionSetExpression createUniverseRegion() {
		return new RegionSetExpression(setEngine.<Number[]>createUniverseSet());
	}

	public RegionSetExpression createEmptyRegion() {
		return new RegionSetExpression(setEngine.<Number[]>createEmptySet());
	}

	public RegionSetExpression createRegionByFace( final SurfaceFuncExpression face, final int side ) {
		final NumberBasicEngine math = this.mathEngine;
		return new RegionSetExpression(setEngine.createSetByIntensionalDefinition(new Function<Number[],Boolean>() {
			public Boolean apply( Number [] vec ) {
				return math.greaterThan(
					math.multiply( face.getFunction().apply(vec), math.number(side) ),
					math.number0());
			}
		}));
	}
}
