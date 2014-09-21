package magicball.model.geometry.func;

import magicball.model.geometry.*;
import magicball.model.math.*;


public class TransformationEngineForFunc implements TransformationBasicEngine<TransformationMatrixExpression>
{
	protected NumberBasicEngine mathEngine;

	public TransformationEngineForFunc( NumberBasicEngine mathEng ) {
		this.mathEngine = mathEng;
	}

	public TransformationEngineForFunc clone() {
		return new TransformationEngineForFunc(this.mathEngine);
	}

	public TransformationMatrixExpression createIdentityTransformation() {
		return new TransformationMatrixExpression(mathEngine.matrix1(3),mathEngine.vector0(3));
	}

	public TransformationMatrixExpression compose( TransformationMatrixExpression trans1, TransformationMatrixExpression trans2 ) {
		Number [][] rot = mathEngine.matrixMultiply(trans2.getRotationMatrix(),trans1.getRotationMatrix());
		Number [] sh = mathEngine.add(trans2.getShiftVector(),mathEngine.matrixMultiply(trans2.getRotationMatrix(),trans1.getShiftVector()));
		return new TransformationMatrixExpression(rot,sh);
	}

	public TransformationMatrixExpression compose( TransformationMatrixExpression... trans ) {
		TransformationMatrixExpression result = trans[0];
		for ( int i=1; i<trans.length; i++ )
			result = compose(result,trans[i]);
		return result;
	}

	public TransformationMatrixExpression pow( TransformationMatrixExpression trans, int exp ) {
		TransformationMatrixExpression result = trans;
		for ( int i=1; i<exp; i++ )
			result = compose(result,trans);
		return result;
	}

	public TransformationMatrixExpression invert( TransformationMatrixExpression trans ) {
		Number [][] rot = mathEngine.transpose(trans.getRotationMatrix());
		Number [] sh = mathEngine.negate(mathEngine.matrixMultiply(rot,trans.getShiftVector()));
		return new TransformationMatrixExpression(rot,sh);
	}

	public TransformationMatrixExpression dividedBy( TransformationMatrixExpression trans, Number divisor ) {
		// TODO: use arg to select number of turns
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

	public boolean isIdentity( TransformationMatrixExpression trans ) {
		return mathEngine.equals(trans.getRotationMatrix(),mathEngine.matrix1(3)) &&
				mathEngine.equals(trans.getShiftVector(),mathEngine.vector0(3));
	}

	public boolean isRotation( TransformationMatrixExpression trans ) {
		return mathEngine.equals(trans.getShiftVector(),mathEngine.vector0(3));
	}

	public boolean isShift( TransformationMatrixExpression trans ) {
		return mathEngine.equals(trans.getRotationMatrix(),mathEngine.matrix1(3));
	}

	public boolean equals( TransformationMatrixExpression trans1, TransformationMatrixExpression trans2 ) {
		return mathEngine.equals(trans1.getRotationMatrix(),trans2.getRotationMatrix()) &&
				mathEngine.equals(trans1.getShiftVector(),trans2.getShiftVector());
	}

	public Function<Number[],Number[]> createTransformationFunction( final TransformationMatrixExpression trans ) {
		final NumberBasicEngine math = this.mathEngine;
		return new Function<Number[],Number[]>() {
			public Number[] apply( Number[] in ) {
				return math.add(math.matrixMultiply(trans.getRotationMatrix(),in),trans.getShiftVector());
			}
		};
	}
}
