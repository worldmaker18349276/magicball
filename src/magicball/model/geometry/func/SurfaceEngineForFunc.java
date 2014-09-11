package magicball.model.geometry.func;

import magicball.model.geometry.*;
import magicball.model.math.*;


public class SurfaceEngineForFunc implements SurfaceBasicEngine<SurfaceFuncExpression>
{
	public SurfaceEngineForFunc() {}

	public SurfaceEngineForFunc clone() {
		return new SurfaceEngineForFunc();
	}

	public SurfaceFuncExpression createSurfaceByFunction( Function<Number[],Number> func ) {
		return new SurfaceFuncExpression(func);
	}
}
