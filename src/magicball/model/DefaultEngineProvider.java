package magicball.model;

import magicball.model.*;
import magicball.model.math.*;
import magicball.model.geometry.*;
import magicball.model.puzzle.*;
import magicball.model.math.basic.*;
import magicball.model.geometry.basic.*;
import magicball.model.geometry.affine.*;
import magicball.model.puzzle.basic.*;


public class DefaultEngineProvider extends BasicEngineProvider
{
	protected double epsilon;
	protected double a;
	protected int n;
	protected CompositeNumberBasicEngine numberEng;
	protected CompositeFunctionBasicEngine functionEng;
	protected RegionBasicEngine regionEng;
	protected TransformationBasicEngine transformationEng;
	protected MotionBasicEngine MotionEng;
	protected SolidBasicEngine solidEng;


	public DefaultEngineProvider( double eps, double a, int n ) {
		this.epsilon = eps;
		this.a = a;
		this.n = n;
	}

	public NumberBasicEngine getNumberEngine() {
		if ( this.numberEng == null ) {
			this.numberEng = new CompositeNumberBasicEngine();
			this.numberEng.add(new NumberEngineForNative(this.epsilon));
		}
		return this.numberEng;
	}

	public FunctionBasicEngine getFunctionEngine() {
		if ( this.functionEng == null ) {
			this.functionEng = new CompositeFunctionBasicEngine();
			this.functionEng.add(new FunctionEngineForLambdaWithSampleAlgorithm<Number[]>(createSkyGrid()));
		}
		return this.functionEng;
	}

	protected java.util.Set<Number[]> createSkyGrid() {
		java.util.Set<Number[]> sam = new java.util.HashSet<Number[]>();
		NumberBasicEngine math = getNumberEngine();
		double d = 2*a / n;
		for ( double x=-a; x<a; x=x+d )
			for ( double y=-a; y<a; y=y+d )
				for ( double z=-a; z<a; z=z+d )
					sam.add(math.vector(x,y,z));
		return sam;
	}

	public TransformationBasicEngine getTransformationEngine() {
		if ( this.transformationEng == null )
			this.transformationEng = new AffineTransformationEngineForMatrix(this); // numberEng, functionEng
		return this.transformationEng;
	}

	public RegionBasicEngine getRegionEngine() {
		if ( this.regionEng == null )
			this.regionEng = new RegionEngineForFunc(this); // numberEng, functionEng, transformationEng, surfaceEng
		return this.regionEng;
	}

	public MotionBasicEngine getMotionEngine() {
		if ( this.MotionEng == null )
			this.MotionEng = new SimpleMotionEngineForTrans(this); // numberEng, transformationEng
		return this.MotionEng;
	}

	public SolidBasicEngine getSolidEngine() {
		if ( this.solidEng == null )
			this.solidEng = new BasicSolidEngineForRegion(this); // regionEng
		return this.solidEng;
	}
}
