package magicball.model.geometry;

import magicball.model.math.*;


public interface SurfaceEngine
{
	public SurfaceEngine clone();


	// creater
	public Surface createSurfaceByFunction( Function<Number[],Number> func );
	public Surface createSurfaceByLambda( LambdaFunction<Number[],Number> lambda );

	public Surface createPlaneByVector( Number[] fvec );


	// attribute
	public Function<Number[],Number> getIsosurfaceFunction( Surface face );


	// operator
	public Surface transformsBy( Surface face, Transformation trans );
	public Surface reflectsBy( Surface face, Reflection ref );
	
	public boolean isPlane( Surface face );
}
