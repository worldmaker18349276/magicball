package magicball.model.geometry;

import io.netty.util.AttributeKey;

import magicball.model.math.*;


public interface RegionBasicOperator
{
	public static AttributeKey<RegionBasicOperator> KEY = AttributeKey.<RegionBasicOperator>valueOf("RegionBasicOperator");
	// operator
	public Region intersect( Region... regs );
	public Region union( Region... regs );
	public Region complement( Region reg1, Region reg2 );
	public Region complement( Region reg2 );
	
	public Region transformsBy( Region reg, Transformation trans );
}
