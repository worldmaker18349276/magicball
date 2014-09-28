package magicball.model.geometry.func;

import magicball.model.geometry.*;
import magicball.model.math.*;
import magicball.model.*;


public class SurfaceBasicEngine implements SurfaceEngine
{
	protected TransformationEngine transEngine;
	protected FunctionEngine funcEngine;
	protected NumberEngine mathEngine;

	public SurfaceBasicEngine( TransformationEngine transEngine, FunctionEngine funcEngine, NumberEngine mathEng ) {
		this.transEngine = transEngine;
		this.funcEngine = funcEngine;
		this.mathEngine = mathEng;
	}

	public SurfaceBasicEngine clone() {
		return new SurfaceBasicEngine();
	}

	public Surface createSurfaceByFunction( Function<Number[],Number> func ) {
		return new SurfaceFuncExpression(func);
	}

	public Surface createSurfaceByLambda( LambdaFunction<Number[],Number> lambda ) {
		return new SurfaceFuncExpression(this.funcEngine.createFunctionByLambda(lambda));
	}

	public Surface createPlaneByVector( Number[] fvec ) {
		Number dis = this.mathEngine.norm(fvec);
		Number[] nvec = this.mathEngine.normalize(fvec);
		return createPlaneByVectorAndDistance(nvec,dis);
	}
	
	public Surface createPlaneByVectorAndDistance( final Number[] nvec, final Number dis ) {
		final NumberEngine math = this.mathEngine;
		return createSurfaceByLambda(
			new LambdaFunction<Number[],Number>() {
				public Number apply( Number[] vec ) {
					return math.subtract(math.dotProduct(vec,nor),dis);
				}
			}
		);
	}

	public boolean isPlane( Surface face ) {
		throw new UnsupportedAlgorithmException();
	}

	public Surface transformsBy( Surface face, Transformation trans ) {
		Transformation _trans = this.transEngine.invert(trans);
		Function<Number[],Number[]> trans_func = this.transEngine.createTransformationFunction(_trans);
		Function<Number[],Number> face_func = cast(face).getFunction();
		Function<Number[],Number> face_func_ = this.funcEngine.compose(trans_func,face_func);
		return createSurfaceByFunction(face_func_);
	}
	
	public Surface reflectsBy( Surface face, Reflection ref ) {
		Function<Number[],Number[]> ref_func = this.transEngine.createReflectionFunction(ref);
		Function<Number[],Number> face_func = cast(face).getFunction();
		Function<Number[],Number> face_func_ = this.funcEngine.compose(ref_func,face_func);
		return createSurfaceByFunction(face_func_);
	}
}
