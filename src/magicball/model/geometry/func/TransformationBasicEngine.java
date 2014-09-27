package magicball.model.geometry.func;

import magicball.model.geometry.*;
import magicball.model.math.*;
import magicball.model.*;


public class TransformationBasicEngine implements TransformationEngine
{
	protected NumberEngine mathEngine;

	public TransformationBasicEngine( NumberEngine mathEng ) {
		this.mathEngine = mathEng;
	}

	public TransformationBasicEngine clone() {
		return new TransformationBasicEngine(this.mathEngine);
	}

	protected TransformationMatrixExpression cast( Transformation trans ) {
		try {
			return (TransformationMatrixExpression) trans;
		} catch ( ClassCastException e ) {
			throw new UnsupportedExpressionException(trans.getClass());
		}
	}

	public Transformation createIdentityTransformation() {
		return new TransformationMatrixExpression(mathEngine.matrix1(3),mathEngine.vector0(3));
	}

	public Transformation compose( Transformation trans1_, Transformation trans2_ ) {
		TransformationMatrixExpression trans1 = cast(trans1_);
		TransformationMatrixExpression trans2 = cast(trans2_);
		Number [][] rot = mathEngine.matrixMultiply(trans2.getRotationMatrix(),trans1.getRotationMatrix());
		Number [] sh = mathEngine.add(trans2.getShiftVector(),mathEngine.matrixMultiply(trans2.getRotationMatrix(),trans1.getShiftVector()));
		return new TransformationMatrixExpression(rot,sh);
	}

	public Transformation compose( Transformation... trans ) {
		Transformation result = trans[0];
		for ( int i=1; i<trans.length; i++ )
			result = compose(result,trans[i]);
		return result;
	}

	public Transformation pow( Transformation trans, int exp ) {
		Transformation result = cast(trans);
		for ( int i=1; i<exp; i++ )
			result = compose(result,trans);
		return result;
	}

	public Transformation invert( Transformation trans_ ) {
		TransformationMatrixExpression trans = cast(trans_);
		Number [][] rot = mathEngine.transpose(trans.getRotationMatrix());
		Number [] sh = mathEngine.negate(mathEngine.matrixMultiply(rot,trans.getShiftVector()));
		return new TransformationMatrixExpression(rot,sh);
	}

	public Transformation dividedBy( Transformation trans_, Number divisor ) {
		// TODO: use arg to select number of turns
		TransformationMatrixExpression trans = cast(trans_);
		if ( isIdentity(trans) ) {
			return trans;
		} else if ( isRotation(trans) ) {

			Number[][] rot = trans.getRotationMatrix();
			Number [] rvec = mathEngine.rotationMatrix2RotationVector(rot);
			rot = mathEngine.rotationVector2RotationMatrix(mathEngine.dividedBy(rvec,divisor));
			return new TransformationMatrixExpression(rot,mathEngine.vector0(3));

		} else if ( isShift(trans) ) {

			Number[] sh = trans.getShiftVector();
			sh = mathEngine.dividedBy(sh,divisor);
			return new TransformationMatrixExpression(mathEngine.matrix1(3),sh);

		} else { // only for int divisor

			int n = divisor.intValue();

			Number[][] rot = trans.getRotationMatrix();
			Number[] sh = trans.getShiftVector();
			Number [] rvec = mathEngine.rotationMatrix2RotationVector(rot);
			Number[][] rot_n = mathEngine.rotationVector2RotationMatrix(mathEngine.dividedBy(rvec,divisor));

			Number[][] m = mathEngine.matrix1(3);
			Number[][] rot_i = mathEngine.matrix1(3);
			for ( int i=1; i<n; i++ ) {
				rot_i = mathEngine.matrixMultiply(rot_i,rot_n);
				m = mathEngine.add(m,rot_i);
			}
			Number[] sh_n = mathEngine.matrixMultiply(mathEngine.invert33(m),sh);

			return new TransformationMatrixExpression(rot_n,sh_n);

		}
	}

	public boolean isIdentity( Transformation trans ) {
		return mathEngine.equals(cast(trans).getRotationMatrix(),mathEngine.matrix1(3)) &&
				mathEngine.equals(cast(trans).getShiftVector(),mathEngine.vector0(3));
	}

	public boolean isRotation( Transformation trans ) {
		return mathEngine.equals(cast(trans).getShiftVector(),mathEngine.vector0(3));
	}

	public boolean isShift( Transformation trans ) {
		return mathEngine.equals(cast(trans).getRotationMatrix(),mathEngine.matrix1(3));
	}

	public boolean equals( Transformation trans1, Transformation trans2 ) {
		return mathEngine.equals(cast(trans1).getRotationMatrix(),cast(trans2).getRotationMatrix()) &&
				mathEngine.equals(cast(trans1).getShiftVector(),cast(trans2).getShiftVector());
	}

	public Function<Number[],Number[]> createTransformationFunction( final Transformation trans ) {
		final NumberEngine math = this.mathEngine;
		return new Function<Number[],Number[]>() {
			public Number[] apply( Number[] in ) {
				return math.add(math.matrixMultiply(cast(trans).getRotationMatrix(),in),cast(trans).getShiftVector());
			}
		};
	}

	public Reflection createReflectionByPlane( Surface plane ) {
		throw new UnsupportedAlgorithmException();
	}
	public Function<Number[],Number[]> createReflectionFunction( Reflection ref ) {
		throw new UnsupportedAlgorithmException();
	}
}
