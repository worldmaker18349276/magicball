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

	public TransformationMatrixExpression invert( TransformationMatrixExpression trans );
	public TransformationMatrixExpression dividedBy( TransformationMatrixExpression trans, int divisor );

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
