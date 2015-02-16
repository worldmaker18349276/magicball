package magicball.model.geometry;

import magicball.model.math.*;


public interface SurfaceAttributeForFunc
{
	// attribute
	public Function<Number[],Number> getIsosurfaceFunction( Surface face );
}
