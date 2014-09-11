package magicball.model.geometry;

import magicball.model.math.*;


public interface SurfaceBasicEngine < S extends Surface >
{
	public SurfaceBasicEngine clone();
	public S createSurfaceByFunction( Function<Number[],Number> func );
}
