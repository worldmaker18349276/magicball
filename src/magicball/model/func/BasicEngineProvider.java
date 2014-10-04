package magicball.model.func;

import magicball.model.*;
import magicball.model.math.*;
import magicball.model.geometry.*;
import magicball.model.math.func.*;
import magicball.model.geometry.func.*;


public class BasicEngineProvider extends EngineProvider
{
	protected double epsilon;
	protected NumberEngine numberEng;
	protected SetEngine setEng;
	protected FunctionEngine functionEng;
	protected SurfaceEngine surfaceEng;
	protected RegionEngine regionEng;
	protected TransformationEngine transformationEng;


	public BasicEngineProvider( double eps ) {
		this.epsilon = eps;
	}

	public NumberEngine getNumberEngine() {
		if ( this.numberEng == null )
			this.numberEng = new NumberBasicEngine(this.epsilon);
		return this.numberEng;
	}

	public FunctionEngine getFunctionEngine() {
		if ( this.functionEng == null )
			this.functionEng = new FunctionBasicEngine();
		return this.functionEng;
	}

	public SetEngine getSetEngine() {
		if ( this.setEng == null )
			this.setEng = new SetBasicEngine(this); // functionEng
		return this.setEng;
	}

	public TransformationEngine getTransformationEngine() {
		if ( this.transformationEng == null )
			this.transformationEng = new TransformationBasicEngine(this); // numberEng, functionEng
		return this.transformationEng;
	}

	public SurfaceEngine getSurfaceEngine() {
		if ( this.surfaceEng == null )
			this.surfaceEng = new SurfaceBasicEngine(this); // numberEng, functionEng, transformationEng
		return this.surfaceEng;
	}

	public RegionEngine getRegionEngine() {
		if ( this.regionEng == null )
			this.regionEng = new RegionBasicEngine(this); // numberEng, functionEng, setEng, transformationEng, surfaceEng
		return this.regionEng;
	}
}
