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
		return TransformationMatrixExpression(mathEngine.matrix1(3),mathEngine.vector0(3));
	}

	public TransformationMatrixExpression compose( TransformationMatrixExpression trans1, TransformationMatrixExpression trans2 ) {
		Number [][] rot = mathEngine.matrixMultiply(trans1.getRotationMatrix(),trans2.getRotationMatrix());
		Number [] sh = mathEngine.add(trans1.getShiftVector(),mathEngine.matrixMultiply(trans1.getRotationMatrix(),trans2.getShiftVector()));
		return new TransformationMatrixExpression(rot,sh);
	}

	public TransformationMatrixExpression compose( TransformationMatrixExpression... trans ) {
		TransformationMatrixExpression result = trans[0];
		for ( int i=1; i<trans.length,; i++ )
			result = compose(result,trans[i]);
		return result;
	}

	public TransformationMatrixExpression pow( TransformationMatrixExpression trans, int exp ) {
		TransformationMatrixExpression result = trans;
		for ( int i=1; i<exp,; i++ )
			result = compose(result,trans);
		return result;
	}

	public TransformationMatrixExpression invert( TransformationMatrixExpression trans ) {
		return new TransformationMatrixExpression(mathEngine.transpose(trans.getRotationMatrix()),mathEngine.negate(trans.getShiftVector()));
	}

	public TransformationMatrixExpression dividedBy( TransformationMatrixExpression trans, Number divisor ) {
		if ( isRotation(trans) ) {

			Number[][] rot = trans.getRotationMatrix();
			Number[] axis = mathEngine.axisOfRotationMatrix(rot);
			Number angle = mathEngine.angleOfRotationMatrix(rot,axis);
			rot = mathEngine.createRotationMatrix(axis,mathEngine.dividedBy(angle,divisor));
			return new TransformationMatrixExpression(rot,mathEngine.vector0(3));

		} else if ( isShift(trans) ) {

			Number[] sh = trans.getShiftVector();
			sh = mathEngine.dividedBy(sh,divisor);
			return new TransformationMatrixExpression(mathEngine.matrix1(3),sh);

		} else { // only for int divisor

			int n = divisor.intValue();

			Number[][] rot = trans.getRotationMatrix();
			Number[] sh = trans.getShiftVector();
			Number[] axis = mathEngine.axisOfRotationMatrix(rot);
			Number angle = mathEngine.angleOfRotationMatrix(rot,axis);
			Number[][] rot_n = mathEngine.createRotationMatrix(axis,mathEngine.dividedBy(angle,divisor));

			Number[][] m = matrix1(3);
			for ( int i=1; i<n; i++ )
				m = mathEngine.add(m,mathEngine.pow(rot_n,i));
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
}
