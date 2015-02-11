package magicball.model;

import magicball.model.*;
import magicball.model.math.*;
import magicball.model.geometry.*;
import magicball.model.puzzle.*;
import magicball.model.math.basic.*;
import magicball.model.geometry.basic.*;
import magicball.model.geometry.poly.*;
import magicball.model.puzzle.basic.*;


public class BasicEngineProvider extends EngineProvider
{
	protected double epsilon;
	protected double a;
	protected int n;
	protected NumberEngine numberEng;
	protected SetEngine setEng;
	protected FunctionEngine functionEng;
	protected SurfaceEngine surfaceEng;
	protected RegionEngine regionEng;
	protected TransformationEngine transformationEng;
	protected MovementEngine movementEng;
	protected SolidEngine solidEng;


	public BasicEngineProvider( double eps, double a, int n ) {
		this.epsilon = eps;
		this.a = a;
		this.n = n;
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
			this.setEng = new SetBasicEngineWithSampleAlgorithm<Number[]>(getFunctionEngine(),createSkyGrid()); // functionEng
		return this.setEng;
	}

	protected java.util.Set<Number[]> createSkyGrid() {
		java.util.Set<Number[]> sam = new java.util.HashSet<Number[]>();
		NumberEngine math = getNumberEngine();
		double d = 2*a / n;
		for ( double x=-a; x<a; x=x+d )
			for ( double y=-a; y<a; y=y+d )
				for ( double z=-a; z<a; z=z+d )
					sam.add(math.vector(x,y,z));
		return sam;
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

	public MovementEngine getMovementEngine() {
		if ( this.movementEng == null )
			this.movementEng = new SimpleMovementEngine(this); // numberEng, transformationEng
		return this.movementEng;
	}

	public SolidEngine getSolidEngine() {
		if ( this.solidEng == null )
			this.solidEng = new SolidBasicEngine(this); // regionEng
		return this.solidEng;
	}
}
