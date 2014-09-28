package magicball.model.geometry;

import magicball.model.math.*;


public interface RegionEngine
{
	public RegionEngine clone();
	public Region intersect( Region... regs );
	public Region union( Region... regs );
	public Region complement( Region reg1, Region reg2 );
	public Region complement( Region reg2 );
	public Region createUniversalRegion();
	public Region createEmptyRegion();
	public Region createRegionBySet( Set<Number[]> set );
	public Region createRegionByFunction( Function<Number[],Boolean> func );
	public Region createRegionByLambda( LambdaFunction<Number[],Boolean> lambda );
	public Region createRegionByFace( Surface face, int side );

	public boolean isEmpty( Region reg );
	public boolean isUniversal( Region reg );
	public boolean equals( Region reg1, Region reg2 );
	public boolean containsAll( Region reg1, Region reg2 );
	public boolean contains( Region reg, Number[] point );
	public Region transformsBy( Region reg, Transformation trans );
	public Region reflectsBy( Region reg, Reflection ref );
}