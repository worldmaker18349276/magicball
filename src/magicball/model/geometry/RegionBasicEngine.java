package magicball.model.geometry;


public interface RegionBasicEngine
{
	public RegionBasicEngine clone();
	public Region intersect( Region[] regs );
	public Region union( Region[] regs );
	public Region complement( Region reg1, Region reg2 );
	public Region complement( Region reg2 );
	public Region createUniverseRegion();
	public Region createEmptyRegion();
	public Region createRegionByFace( Surface face, int side );
}
