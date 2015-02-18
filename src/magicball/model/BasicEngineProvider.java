package magicball.model;

import magicball.model.math.*;
import magicball.model.geometry.*;
import magicball.model.puzzle.*;


public abstract class BasicEngineProvider
{
	public abstract NumberAdvancedEngine getNumberEngine();
	public abstract FunctionAdvancedEngine getFunctionEngine();
	public abstract TransformationAdvancedEngine getTransformationEngine();
	public abstract RegionBasicEngine getRegionEngine();
	public abstract MotionBasicEngine getMotionEngine();
	public abstract SolidBasicEngine getSolidEngine();
}
