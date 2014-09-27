package magicball.model.geometry.func;

import magicball.model.geometry.*;
import magicball.model.math.*;
import magicball.model.*;


public class SurfaceBasicEngine implements SurfaceEngine
{
	public SurfaceBasicEngine() {}

	public SurfaceBasicEngine clone() {
		return new SurfaceBasicEngine();
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
