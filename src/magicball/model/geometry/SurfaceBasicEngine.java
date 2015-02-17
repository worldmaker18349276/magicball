package magicball.model.geometry;

import magicball.model.math.*;


public interface SurfaceBasicEngine extends
		SurfaceBasicCreator,
		SurfaceBasicOperator,
		PlaneCreator,
		PlanePredicate
{
	public SurfaceBasicEngine clone();


	// creater
	public Surface createSurfaceByFunction( Function<Number[],Number> func );
	public Surface createPlaneByVector( Number[] fvec );


	// operator
	public Surface transformsBy( Surface face, Transformation trans );
	

	// predicate
	public boolean isPlane( Surface face );
}
