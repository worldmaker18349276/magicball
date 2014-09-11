package magicball.model.geometry.func;

import magicball.model.geometry.*;


public class SimpleMovementEngineForFunc implements MovementBasicEngine<SimpleMovementTransExpression<TransformationMatrixExpression>,TransformationMatrixExpression>
{
	protected TransformationEngineForFunc transEngine;

	public SimpleMovementEngineForFunc( TransformationEngineForFunc transEng ) {
		this.transEngine = transEng;
	}

	public SimpleMovementEngineForFunc clone() {
		return new SimpleMovementEngineForFunc(this.transEngine);
	}

	public SimpleMovementTransExpression<TransformationMatrixExpression> createSimpleMovementByTransformation( TransformationMatrixExpression trans ) {
		return new SimpleMovementTransExpression<TransformationMatrixExpression>(trans);
	}

	public TransformationMatrixExpression divideMovementIntoTransformation( SimpleMovementTransExpression<TransformationMatrixExpression> move, Number from, Number to ) {
		return transEngine.divide(move.getTransformation(),(Double)(to.doubleValue()-from.doubleValue()));
	}
}
