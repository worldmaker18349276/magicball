package magicball.model.geometry;

import magicball.model.math.*;


public interface SurfaceBasicCreator
{
	// creater
	public Surface createSurfaceByFunction( Function<Number[],Number> func );
}
