package magicball.model.geometry;

import magicball.model.math.*;


public interface RegionBasicEngine extends
		RegionBasicCreator,
		RegionBasicAttribute,
		RegionBasicOperator,
		RegionBasicPredicate,
		RegionCreatorForFunc,
		RegionAttributeForFunc
{
	public RegionBasicEngine clone();


	// creater
	public Region createRegionByFunction( Function<Number[],Boolean> func );
	public Region createRegionByFace( Surface face, int side );

	public Region createUniversalRegion();
	public Region createEmptyRegion();


	// attribute
	public Function<Number[],Boolean> getRegionIntensionFunction( Region reg );

	public boolean contains( Region reg, Number[] point );


	// operator
	public Region intersect( Region... regs );
	public Region union( Region... regs );
	public Region complement( Region reg1, Region reg2 );
	public Region complement( Region reg2 );
	
	public Region transformsBy( Region reg, Transformation trans );

	public boolean isEmpty( Region reg );
	public boolean isUniversal( Region reg );
	public boolean containsAll( Region reg1, Region reg2 );
	public boolean equals( Region reg1, Region reg2 );
}
