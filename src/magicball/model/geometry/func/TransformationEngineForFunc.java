package magicball.model.geometry.func;

import magicball.model.geometry.*;


public abstract class TransformationEngineForFunc implements TransformationBasicEngine<TransformationMatrixExpression>
{
	public abstract TransformationEngineForFunc clone();
	public abstract TransformationMatrixExpression createIdentityTransformation();
	public abstract TransformationMatrixExpression compose( TransformationMatrixExpression[] trans );
	public abstract TransformationMatrixExpression invert( TransformationMatrixExpression trans );
	public abstract TransformationMatrixExpression createRotation( Number [] axis, Number deg );
	public abstract TransformationMatrixExpression createShift( Number [] vec );
	public abstract TransformationMatrixExpression divide( TransformationMatrixExpression move, Number divisor );
}
