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

	public SurfaceBasicEngine( EngineProvider provider ) {
		this.transEngine = provider.getTransformationEngine();
		this.funcEngine = provider.getFunctionEngine();
		this.mathEngine = provider.getNumberEngine();
	}

	public SurfaceBasicEngine clone() {
		return new SurfaceBasicEngine(this.transEngine,this.funcEngine,this.mathEngine);
	}

	protected SurfaceFuncExpression cast( Surface face ) {
		try {
			return (SurfaceFuncExpression) face;
		} catch ( ClassCastException e ) {
			throw new UnsupportedExpressionException(face.getClass());
		}
	}


	// creater
	@Override
	public Surface createSurfaceByFunction( Function<Number[],Number> func ) {
		return new SurfaceFuncExpression(func);
	}

	@Override
	public Surface createPlaneByVector( Number[] fvec ) {
		Number dis = this.mathEngine.norm(fvec);
		Number[] nvec = this.mathEngine.normalize(fvec);
		return createPlaneByVectorAndDistance(nvec,dis);
	}
	
	public Surface createPlaneByVectorAndDistance( final Number[] nvec, final Number dis ) {
		final NumberEngine math = this.mathEngine;
		return createSurfaceByFunction(this.funcEngine.function(
			new LambdaFunction<Number[],Number>() {
				@Override
				public Number apply( Number[] vec ) {
					return math.subtract(math.dotProduct(vec,nvec),dis);
				}
			}
		));
	}


	// attribute
	@Override
	public Function<Number[],Number> getIsosurfaceFunction( Surface face_ ) {
		SurfaceFuncExpression face = cast(face_);
		return face.getFunction();
	}


	// operator
	@Override
	public Surface transformsBy( Surface face, Transformation trans ) {
		Transformation _trans = this.transEngine.invert(trans);
		Function<Number[],Number[]> trans_func = this.transEngine.getTransformationFunction(_trans);
		Function<Number[],Number> face_func = getIsosurfaceFunction(face);
		Function<Number[],Number> face_func_ = this.funcEngine.compose(trans_func,face_func);
		return createSurfaceByFunction(face_func_);
	}
	
	@Override
	public Surface reflectsBy( Surface face, Reflection ref ) {
		Function<Number[],Number[]> ref_func = this.transEngine.getReflectionFunction(ref);
		Function<Number[],Number> face_func = getIsosurfaceFunction(face);
		Function<Number[],Number> face_func_ = this.funcEngine.compose(ref_func,face_func);
		return createSurfaceByFunction(face_func_);
	}

	@Override
	public boolean isPlane( Surface face ) {
		throw new UnsupportedAlgorithmException();
	}
}
