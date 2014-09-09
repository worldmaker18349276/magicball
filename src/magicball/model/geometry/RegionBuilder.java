package magicball.model.geometry;


public interface RegionBuilder
{
	public Region createIntersectionRegion( Region... regs );
	public Region createUnionRegion( Region... regs );
	public Region createComplementRegion( Region reg1, Region reg2 );
	public Region createUniverseRegion();
	public Region createEmptyRegion();
	public Region createRegionByFace( Face face, int side );
}
