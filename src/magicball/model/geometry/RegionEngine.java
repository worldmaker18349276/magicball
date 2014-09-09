package magicball.model.geometry;


public interface RegionEngine
{
	public Region intersect( Region... regs );
	public Region union( Region... regs );
	public Region complement( Region reg1, Region reg2 );
	public Region createUniverseRegion();
	public Region createEmptyRegion();
	public Region createRegionByFace( Face face, int side );
}
