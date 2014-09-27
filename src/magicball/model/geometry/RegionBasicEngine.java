package magicball.model.geometry;


public interface RegionBasicEngine
{
	public RegionBasicEngine clone();
	public Region intersect( Region... regs );
	public Region union( Region... regs );
	public Region complement( Region reg1, Region reg2 );
	public Region complement( Region reg2 );
	public Region createUniversalRegion();
	public Region createEmptyRegion();
	public Region createRegionByFace( Surface face, int side );

	public boolean isEmpty( Region reg );
	public boolean isUniversal( Region reg );
	public boolean equals( Region reg1, Region reg2 );
	public boolean containsAll( Region reg1, Region reg2 );
	public boolean contains( Region reg, Number[] point );
	public Region transformsBy( Region reg, Transformation trans );
	public Region reflectsBy( Region reg, Reflection ref );
}
