package magicball.model.geometry;


public class RegionEngineForFunc implements RegionBasicEngine<RegionSetExpression,SurfaceFuncExpression>
{
	public RegionEngineForFunc clone();
	public RegionSetExpression cast( Region reg );
	public RegionSetExpression intersect( RegionSetExpression... regs );
	public RegionSetExpression union( RegionSetExpression... regs );
	public RegionSetExpression complement( RegionSetExpression reg1, RegionSetExpression reg2 );
	public RegionSetExpression createUniverseRegion();
	public RegionSetExpression createEmptyRegion();
	public RegionSetExpression createRegionByFace( SurfaceFuncExpression face, int side );
}
