package magicball.model.geometry;

import magicball.model.math.*;


public interface RegionEngine
{
	public RegionEngine clone();


	// creater
	public Region createRegionByFunction( Function<Number[],Boolean> func );
	public Region createRegionByFace( Surface face, int side );

	public Region createUniversalRegion();
	public Region createEmptyRegion();


	// attribute
	public Function<Number[],Boolean> getRegionIntensionFunction( Region reg );

	public boolean contains( Region reg, Number[] point );
	public boolean containsAll( Region reg1, Region reg2 );


	// operator
	public Region intersect( Region... regs );
	public Region union( Region... regs );
	public Region complement( Region reg1, Region reg2 );
	public Region complement( Region reg2 );
	
	public Region transformsBy( Region reg, Transformation trans );
	public Region reflectsBy( Region reg, Reflection ref );

	public boolean isEmpty( Region reg );
	public boolean isUniversal( Region reg );
	public boolean equals( Region reg1, Region reg2 );
}
