package magicball.model;

import magicball.model.math.*;
import magicball.model.geometry.*;


public abstract class EngineProvider
{
	public abstract NumberEngine getNumberEngine();
	public abstract SetEngine getSetEngine();
	public abstract FunctionEngine getFunctionEngine();
	public abstract SurfaceEngine getSurfaceEngine();
	public abstract RegionEngine getRegionEngine();
	public abstract TransformationEngine getTransformationEngine();
}
