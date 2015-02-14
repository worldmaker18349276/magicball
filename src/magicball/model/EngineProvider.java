package magicball.model;

import magicball.model.math.*;
import magicball.model.geometry.*;
import magicball.model.puzzle.*;


public abstract class EngineProvider
{
	public abstract NumberEngine getNumberEngine();
	public abstract FunctionEngine getFunctionEngine();
	public abstract TransformationEngine getTransformationEngine();
	public abstract SurfaceEngine getSurfaceEngine();
	public abstract RegionEngine getRegionEngine();
	public abstract MovementEngine getMovementEngine();
	public abstract SolidEngine getSolidEngine();
}
