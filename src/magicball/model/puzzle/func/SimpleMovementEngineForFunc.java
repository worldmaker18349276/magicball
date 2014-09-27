package magicball.model.puzzle.func;

import magicball.model.geometry.*;
import magicball.model.geometry.func.*;
import magicball.model.puzzle.*;
import magicball.model.math.*;
import magicball.model.*;


public class SimpleMovementEngineForFunc implements MovementBasicEngine
{
	protected TransformationBasicEngine transEngine;
	protected NumberBasicEngine mathEngine;

	public SimpleMovementEngineForFunc( NumberBasicEngine mathEng, TransformationBasicEngine transEng ) {
		this.mathEngine = mathEng;
		this.transEngine = transEng;
	}

	public SimpleMovementEngineForFunc clone() {
		return new SimpleMovementEngineForFunc(this.mathEngine,this.transEngine);
	}

	public SimpleMovementTransExpression cast( SimpleMovement move ) {
		try {
			return (SimpleMovementTransExpression) move;
		} catch ( ClassCastException e ) {
			throw new UnsupportedExpressionException(move.getClass());
		}
	}

	public SimpleMovement createSimpleMovementByTransformation( Transformation trans ) {
		return new SimpleMovementTransExpression(trans);
	}

	public Transformation divideMovementIntoTransformation( Movement move, Number from, Number to ) {
		if ( move instanceof SimpleMovement )
			return transEngine.dividedBy(cast((SimpleMovement)move).getTransformation(),mathEngine.subtract(to,from));
		else
			throw new UnsupportedAlgorithmException();
	}
}
