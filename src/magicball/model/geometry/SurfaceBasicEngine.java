package magicball.model.geometry;

import magicball.model.math.*;


public interface SurfaceBasicEngine extends
		SurfaceBasicOperator,
		SurfaceCreatorForFunc,
		SurfaceAttributeForFunc,
		PlaneCreator,
		PlanePredicate
{
	public SurfaceBasicEngine clone();


	// creater
	public Surface createSurfaceByFunction( Function<Number[],Number> func );

	public Surface createPlaneByVector( Number[] fvec );


	// attribute
	public Function<Number[],Number> getIsosurfaceFunction( Surface face );


	// operator
	public Surface transformsBy( Surface face, Transformation trans );
	
	public boolean isPlane( Surface face );
}
