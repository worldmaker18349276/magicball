package magicball.model.geometry;

import magicball.model.math.*;


public interface SurfaceBasicOperator
{
	// operator
	public Surface transformsBy( Surface face, Transformation trans );
}
