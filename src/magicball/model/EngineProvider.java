package magicball.model;

import magicball.model.math.*;
import magicball.model.geometry.*;
import magicball.model.puzzle.*;


public abstract class EngineProvider
{
	public abstract NumberEngine getNumberEngine();
	public abstract SetEngine getSetEngine();
	public abstract FunctionEngine getFunctionEngine();
	public abstract SurfaceEngine getSurfaceEngine();
	public abstract RegionEngine getRegionEngine();
	public abstract TransformationEngine getTransformationEngine();
	public abstract MovementEngine getMovementEngine();
}
