package magicball.model.geometry.func;

import magicball.model.geometry.*;


public class SimpleMovementEngineForFunc implements MovementBasicEngine<SimpleMovementTransExpression<TransformationMatrixExpression>,TransformationMatrixExpression>
{
	public SimpleMovementEngineForFunc() {}

	public SimpleMovementEngineForFunc clone() {
		return new SimpleMovementEngineForFunc();
	}

	public SimpleMovementTransExpression<TransformationMatrixExpression> createSimpleMovementByTransformation( TransformationMatrixExpression trans ) {
		return new SimpleMovementTransExpression<TransformationMatrixExpression>(trans);
	}
}
