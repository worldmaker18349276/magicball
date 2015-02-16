 package magicball.model.geometry.basic;

import magicball.model.geometry.*;
import magicball.model.math.*;
import magicball.model.*;


public class SurfaceEngineForFunc implements SurfaceBasicEngine
{
	protected TransformationBasicEngine transEngine;
	protected FunctionBasicEngine funcEngine;
	protected NumberBasicEngine mathEngine;

	public SurfaceEngineForFunc( TransformationBasicEngine transEng, FunctionBasicEngine funcEng, NumberBasicEngine mathEng ) {
		this.transEngine = transEng;
		this.funcEngine = funcEng;
		this.mathEngine = mathEng;
	}

	public SurfaceEngineForFunc( EngineProvider provider ) {
		this.transEngine = provider.getTransformationEngine();
		this.funcEngine = provider.getFunctionEngine();
		this.mathEngine = provider.getNumberEngine();
	}

	public SurfaceEngineForFunc clone() {
		return new SurfaceEngineForFunc(this.transEngine,this.funcEngine,this.mathEngine);
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
	
	public Surface createPlaneByVectorAndDistance( Number[] nvec, Number dis ) {
		return createSurfaceByFunction(this.funcEngine.createFunctionByLambda(
			vec -> mathEngine.subtract(mathEngine.dotProduct(vec,nvec),dis)
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
	public boolean isPlane( Surface face ) {
		throw new UnsupportedAlgorithmException();
	}
}
