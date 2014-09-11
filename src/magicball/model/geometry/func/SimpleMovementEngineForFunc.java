package magicball.model.geometry;


public class SimpleMovementEngineForFunc implements MovementBasicEngine<SimpleMovementTransExpression,TransformationMatrixExpression>
{
	public SimpleMovementEngineForFunc clone();
	public SimpleMovementTransExpression createSimpleMovementByTransformation( TransformationMatrixExpression trans );
}
