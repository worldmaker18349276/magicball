package magicball.model.geometry;


public interface RegionBasicEngine < R extends Region, F extends Surface >
{
	public RegionBasicEngine clone();
	public R intersect( R... regs );
	public R union( R... regs );
	public R complement( R reg1, R reg2 );
	public R createUniverseRegion();
	public R createEmptyRegion();
	public R createRegionByFace( F face, int side );
}
