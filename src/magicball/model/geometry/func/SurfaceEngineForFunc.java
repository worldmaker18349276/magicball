package magicball.model.geometry.func;

import magicball.model.geometry.*;
import magicball.model.math.*;


public class SurfaceEngineForFunc implements SurfaceBasicEngine
{
	public SurfaceEngineForFunc() {}

	public SurfaceEngineForFunc clone() {
		return new SurfaceEngineForFunc();
	}

	public Surface createSurfaceByFunction( Function<Number[],Number> func ) {
		return new SurfaceFuncExpression(func);
	}
}
