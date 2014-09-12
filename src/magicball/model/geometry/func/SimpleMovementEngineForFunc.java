package magicball.model.geometry.func;

import magicball.model.geometry.*;
import magicball.model.math.*;


public class SimpleMovementEngineForFunc implements MovementBasicEngine<SimpleMovementTransExpression<TransformationMatrixExpression>,TransformationMatrixExpression>
{
	protected TransformationEngineForFunc transEngine;
	protected NumberBasicEngine mathEngine;

	public SimpleMovementEngineForFunc( NumberBasicEngine mathEng, TransformationEngineForFunc transEng ) {
		this.mathEngine = mathEng;
		this.transEngine = transEng;
	}

	public SimpleMovementEngineForFunc clone() {
		return new SimpleMovementEngineForFunc(this.mathEngine,this.transEngine);
	}

	public SimpleMovementTransExpression<TransformationMatrixExpression> createSimpleMovementByTransformation( TransformationMatrixExpression trans ) {
		return new SimpleMovementTransExpression<TransformationMatrixExpression>(trans);
	}

	public TransformationMatrixExpression divideMovementIntoTransformation( SimpleMovementTransExpression<TransformationMatrixExpression> move, Number from, Number to ) {
		return transEngine.divide(move.getTransformation(),mathEngine.subtract(to,from));
	}
}
