package magicball.model.geometry;

import magicball.model.math.*;


public interface SurfaceCreatorForFunc
{
	// creater
	public Surface createSurfaceByFunction( Function<Number[],Number> func );
}
