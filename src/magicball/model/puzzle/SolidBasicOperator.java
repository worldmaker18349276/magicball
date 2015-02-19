package magicball.model.puzzle;

import io.netty.util.AttributeKey;

import magicball.model.geometry.*;


public interface SolidBasicOperator
{
	public static AttributeKey<SolidBasicOperator> KEY = AttributeKey.<SolidBasicOperator>valueOf("SolidBasicOperator");
	// operator
	public void transformsBy( Solid sol, Transformation trans );
	public java.util.Set<Solid> filtersBy( java.util.Set<Solid> sols, Region reg ) throws IllegalOperationException;
}
