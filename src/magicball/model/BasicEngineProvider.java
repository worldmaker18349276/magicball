package magicball.model;

import magicball.model.math.*;
import magicball.model.geometry.*;
import magicball.model.puzzle.*;


public abstract class BasicEngineProvider
{
	public abstract NumberBasicEngine getNumberEngine();
	public abstract FunctionBasicEngine getFunctionEngine();
	public abstract TransformationBasicEngine getTransformationEngine();
	public abstract RegionBasicEngine getRegionEngine();
	public abstract MotionBasicEngine getMotionEngine();
	public abstract SolidBasicEngine getSolidEngine();
}
