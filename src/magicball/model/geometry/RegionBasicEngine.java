package magicball.model.geometry;

import magicball.model.math.Func;
import magicball.model.math.Num;


public interface RegionBasicEngine extends
		RegionBasic.Behavior,
		RegionBasic.Creator,
		RegionBasic.Operator,
		RegionBasic.Predicate
{
	// behavior
	@Override /* RegionBasic.Behavior */
	public boolean contains( Region reg, Num[] point );
	@Override /* RegionBasic.Behavior */
	public boolean containsAll( Region reg1, Region reg2 );


	// creater
	@Override /* RegionBasic.Creator */
	public Region createRegionByFunction( Func<Num[],Boolean> func );

	@Override /* RegionBasic.Creator */
	public Region createUniversalRegion();
	@Override /* RegionBasic.Creator */
	public Region createEmptyRegion();


	// operator
	@Override /* RegionBasic.Operator */
	public Region intersect( Region... regs );
	@Override /* RegionBasic.Operator */
	public Region union( Region... regs );
	@Override /* RegionBasic.Operator */
	public Region complement( Region reg1, Region reg2 );
	@Override /* RegionBasic.Operator */
	public Region complement( Region reg2 );
	
	@Override /* RegionBasic.Operator */
	public Region transformsBy( Region reg, Transformation trans );


	// predicate
	@Override /* RegionBasic.Predicate */
	public boolean equals( Region reg1, Region reg2 );
	@Override /* RegionBasic.Predicate */
	public boolean isUniversal( Region reg );
	@Override /* RegionBasic.Predicate */
	public boolean isEmpty( Region reg );
}
