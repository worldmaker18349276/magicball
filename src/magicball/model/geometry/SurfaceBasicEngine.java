package magicball.model.geometry;

import magicball.model.math.*;


public interface SurfaceBasicEngine
{
	public SurfaceBasicEngine clone();
	public Surface createSurfaceByFunction( Function<Number[],Number> func );
}
