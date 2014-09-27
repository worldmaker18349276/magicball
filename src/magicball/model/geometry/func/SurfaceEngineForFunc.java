package magicball.model.geometry.func;

import magicball.model.geometry.*;
import magicball.model.math.*;
import magicball.model.*;


public class SurfaceEngineForFunc implements SurfaceBasicEngine
{
	public SurfaceEngineForFunc() {}

	public SurfaceEngineForFunc clone() {
		return new SurfaceEngineForFunc();
	}

	public Surface createSurfaceByFunction( Function<Number[],Number> func ) {
		return new SurfaceFuncExpression(func);
	}

	public Surface createPlaneByVector( Number[] fvec ) {
		throw new UnsupportedAlgorithmException();
	}
	public boolean isPlane( Surface face ) {
		throw new UnsupportedAlgorithmException();
	}
	public Surface transformsBy( Surface face, Transformation trans ) {
		throw new UnsupportedAlgorithmException();
	}
	public Surface reflectsBy( Surface face, Reflection ref ) {
		throw new UnsupportedAlgorithmException();
	}
}
