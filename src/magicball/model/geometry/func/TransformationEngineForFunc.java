package magicball.model.geometry;


public class TransformationEngineForFunc implements TransformationBasicEngine<TransformationMatrixExpression>
{
	public TransformationEngineForFunc clone();
	public TransformationMatrixExpression cast( Transformation trans );
	public TransformationMatrixExpression createIdentityTransformation();
	public TransformationMatrixExpression compose( TransformationMatrixExpression... trans );
	public TransformationMatrixExpression invert( TransformationMatrixExpression trans );
	public TransformationMatrixExpression createRotation( Number [] axis, Number deg );
	public TransformationMatrixExpression createShift( Number [] vec );
}
