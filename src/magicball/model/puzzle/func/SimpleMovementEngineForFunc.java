package magicball.model.puzzle.func;

import magicball.model.geometry.*;
import magicball.model.geometry.func.*;
import magicball.model.puzzle.*;
import magicball.model.math.*;


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

	public Movement createSimpleMovementByTransformation( Transformation trans ) {
		return new SimpleMovementTransExpression(trans);
	}

	public Transformation divideMovementIntoTransformation( Movement move, Number from, Number to ) {
		SimpleMovementTransExpression smove = (SimpleMovementTransExpression) move;
		return transEngine.dividedBy(smove.getTransformation(),mathEngine.subtract(to,from));
	}
}
