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
	protected CompositeFunctionAdvancedEngine functionEng;
	protected CompositeRegionBasicEngine regionEng;
	protected CompositeTransformationAdvancedEngine transformationEng;
	protected CompositeMotionBasicEngine motionEng;
	protected CompositeSolidBasicEngine solidEng;


	public DefaultEngineProvider( double eps, double a, int n ) {
		this.epsilon = eps;
		this.a = a;
		this.n = n;
	}

	public NumberBasicEngine getNumberEngine() {
		if ( this.numberEng == null ) {
			ScalarEngine scaEngine = new ScalarEngineForDouble(this.epsilon);
			VectorEngine vecEngine = new DefaultVectorEngine(scaEngine);
			MatrixEngine matEngine = new DefaultMatrixEngine(scaEngine,vecEngine);

			this.numberEng = new CompositeNumberBasicEngine();
			this.numberEng.add(scaEngine);
			this.numberEng.add(vecEngine);
			this.numberEng.add(matEngine);
		}
		return this.numberEng;
	}

	public FunctionAdvancedEngine getFunctionEngine() {
		if ( this.functionEng == null ) {
			FunctionBasicCreator funcCreator = new FunctionBasicCreatorForLambda();
			BooleanFunctionOperator predOperator = new BooleanFunctionOperatorForLambda(funcCreator);
			FunctionBasicAttribute funcAttribute = new FunctionBasicAttributeForLambda();
			FunctionBasicOperator funcOperator = new FunctionBasicOperatorForLambda(funcCreator);
			FunctionBasicPredicate funcPredicate = new FunctionBasicPredicateWithSampleAlgorithm<Number[]>(createSkyGrid(),funcAttribute);
			BooleanFunctionPredicate predPredicate = new DefaultBooleanFunctionPredicate(funcPredicate,funcOperator);

			this.functionEng = new CompositeFunctionAdvancedEngine();
			this.functionEng.add(funcCreator);
			this.functionEng.add(predOperator);
			this.functionEng.add(funcAttribute);
			this.functionEng.add(funcOperator);
			this.functionEng.add(funcPredicate);
			this.functionEng.add(predPredicate);
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

	public TransformationAdvancedEngine getTransformationEngine() {
		if ( this.transformationEng == null ) {
			this.transformationEng = new CompositeTransformationAdvancedEngine();
			this.transformationEng.add(new AffineTransformationEngineForMatrix(getNumberEngine(), getFunctionEngine()));
		}
		return this.transformationEng;
	}

	public RegionBasicEngine getRegionEngine() {
		if ( this.regionEng == null ) {
			this.regionEng = new CompositeRegionBasicEngine();
			this.regionEng.add(new RegionEngineForFunc(getFunctionEngine(), getTransformationEngine()));
		}
		return this.regionEng;
	}

	public MotionBasicEngine getMotionEngine() {
		if ( this.motionEng == null ) {
			this.motionEng = new CompositeMotionBasicEngine();
			this.motionEng.add(new SimpleMotionEngineForTrans(getNumberEngine(), getTransformationEngine()));
		}
		return this.motionEng;
	}

	public SolidBasicEngine getSolidEngine() {
		if ( this.solidEng == null ) {
			this.solidEng = new CompositeSolidBasicEngine();
			this.solidEng.add(new BasicSolidEngineForRegion(getRegionEngine()));
		}
		return this.solidEng;
	}
}
