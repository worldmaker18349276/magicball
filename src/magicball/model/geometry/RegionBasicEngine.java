package magicball.model.geometry;

import magicball.model.math.Func;
import magicball.model.math.Num;


public interface RegionBasicEngine extends
		ArbitraryRegionBasicProperty.Behavior,
		ArbitraryRegionBasicProperty.Creator,
		ArbitraryRegionBasicProperty.Operator,
		ArbitraryRegionBasicProperty.Predicate
{
	// behavior
	@Override /* ArbitraryRegionBasicProperty.Behavior */
	public boolean contains( Region reg, Num[] point );
	@Override /* ArbitraryRegionBasicProperty.Behavior */
	public boolean containsAll( Region reg1, Region reg2 );


	// creater
	@Override /* ArbitraryRegionBasicProperty.Creator */
	public Region createRegionByFunction( Func<Num[],Boolean> func );

	@Override /* ArbitraryRegionBasicProperty.Creator */
	public Region createUniversalRegion();
	@Override /* ArbitraryRegionBasicProperty.Creator */
	public Region createEmptyRegion();


	// operator
	@Override /* ArbitraryRegionBasicProperty.Operator */
	public Region intersect( Region reg1, Region reg2 );
	@Override /* ArbitraryRegionBasicProperty.Operator */
	public Region intersect( Region... regs );
	@Override /* ArbitraryRegionBasicProperty.Operator */
	public Region union( Region reg1, Region reg2 );
	@Override /* ArbitraryRegionBasicProperty.Operator */
	public Region union( Region... regs );
	@Override /* ArbitraryRegionBasicProperty.Operator */
	public Region complement( Region reg1, Region reg2 );
	@Override /* ArbitraryRegionBasicProperty.Operator */
	public Region complement( Region reg );
	
	@Override /* ArbitraryRegionBasicProperty.Operator */
	public Region transformsBy( Region reg, Transformation trans );


	// predicate
	@Override /* ArbitraryRegionBasicProperty.Predicate */
	public boolean equals( Region reg1, Region reg2 );
	@Override /* ArbitraryRegionBasicProperty.Predicate */
	public boolean isUniversal( Region reg );
	@Override /* ArbitraryRegionBasicProperty.Predicate */
	public boolean isEmpty( Region reg );
}
