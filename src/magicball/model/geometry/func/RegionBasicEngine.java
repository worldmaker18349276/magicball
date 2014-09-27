package magicball.model.geometry.func;

import magicball.model.geometry.*;
import magicball.model.math.*;
import magicball.model.*;
import java.util.Arrays;


public class RegionBasicEngine implements RegionEngine
{
	protected SetEngine setEngine;
	protected NumberEngine mathEngine;

	public RegionBasicEngine( NumberEngine mathEng, SetEngine setEng ) {
		this.mathEngine = mathEng;
		this.setEngine = setEng;
	}

	public RegionBasicEngine clone() {
		return new RegionBasicEngine(this.mathEngine,this.setEngine);
	}

	protected SurfaceFuncExpression cast( Surface face ) {
		try {
			return (SurfaceFuncExpression) face;
		} catch ( ClassCastException e ) {
			throw new UnsupportedExpressionException(face.getClass());
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

	public Region createUniversalRegion() {
		return new RegionSetExpression(setEngine.<Number[]>createUniversalSet());
	}

	public Region createEmptyRegion() {
		return new RegionSetExpression(setEngine.<Number[]>createEmptySet());
	}

	public Region createRegionByFace( final Surface face, final int side ) {
		final NumberEngine math = this.mathEngine;
		return new RegionSetExpression(setEngine.createSetByIntensionalDefinition(new Function<Number[],Boolean>() {
			public Boolean apply( Number [] vec ) {
				return math.greaterThan(
					math.multiply( cast(face).getFunction().apply(vec), math.number(side) ),
					math.number0());
			}
		}));
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
		return cast(reg).getSet().isElement(point);
	}

	public boolean containsAll( Region reg1, Region reg2 ) {
		return this.setEngine.containsAll(cast(reg1).getSet(),cast(reg2).getSet());
	}

	
	public Region transformsBy( Region reg, Transformation trans ) {
		throw new UnsupportedAlgorithmException();
	}
	public Region reflectsBy( Region reg, Reflection ref ) {
		throw new UnsupportedAlgorithmException();
	}
}
