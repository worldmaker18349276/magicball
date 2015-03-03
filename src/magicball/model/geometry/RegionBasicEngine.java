package magicball.model.geometry;

import magicball.model.math.Func;
import magicball.model.math.Num;


public interface RegionBasicEngine extends
		RegionBehavior,
		RegionCreator,
		RegionOperator,
		RegionPredicate
{
	// behavior
	@Override /* RegionBehavior */
	public boolean contains( Region reg, Num[] point );
	@Override /* RegionBehavior */
	public boolean containsAll( Region reg1, Region reg2 );


	// creater
	@Override /* RegionCreator */
	public Region createUniversalRegion();
	@Override /* RegionCreator */
	public Region createEmptyRegion();

	@Override /* RegionCreator */
	public Region createRegionByFunction( Func<Num[],Boolean> func );


	// operator
	@Override /* RegionOperator */
	public Region intersect( Region... regs );
	@Override /* RegionOperator */
	public Region union( Region... regs );
	@Override /* RegionOperator */
	public Region complement( Region reg1, Region reg2 );
	@Override /* RegionOperator */
	public Region complement( Region reg2 );
	
	@Override /* RegionOperator */
	public Region transformsBy( Region reg, Transformation trans );


	// predicate
	@Override /* RegionPredicate */
	public boolean isUniversal( Region reg );
	@Override /* RegionPredicate */
	public boolean isEmpty( Region reg );
	@Override /* RegionPredicate */
	public boolean equals( Region reg1, Region reg2 );
}
