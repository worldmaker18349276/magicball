package magicball.model.puzzle.func;

import magicball.model.geometry.*;
import magicball.model.geometry.func.*;
import magicball.model.puzzle.*;
import magicball.model.math.*;
import magicball.model.*;


public class SimpleMovementEngine implements MovementEngine
{
	protected TransformationEngine transEngine;
	protected NumberEngine mathEngine;

	public SimpleMovementEngine( NumberEngine mathEng, TransformationEngine transEng ) {
		this.mathEngine = mathEng;
		this.transEngine = transEng;
	}

	public SimpleMovementEngine( EngineProvider provider ) {
		this.mathEngine = provider.getNumberEngine();
		this.transEngine = provider.getTransformationEngine();
	}

	@Override
	public SimpleMovementEngine clone() {
		return new SimpleMovementEngine(this.mathEngine,this.transEngine);
	}

	protected SimpleMovementTransExpression castToSimpleMovement( Movement move ) {
		try {
			return (SimpleMovementTransExpression) move;
		} catch ( ClassCastException e ) {
			throw new UnsupportedExpressionException(move.getClass());
		}
	}


	// creater
	@Override
	public Movement createSimpleMovementByTransformation( Transformation trans ) {
		return new SimpleMovementTransExpression(trans);
	}


	// attribute
	@Override
	public Transformation getTransformation( Movement move_ ) {
		SimpleMovementTransExpression smove = castToSimpleMovement(move_);
		return smove.getTransformation();
	}


	// operator
	@Override
	public Transformation divideMovementIntoTransformation( Movement move, Number from, Number to ) {
		if ( isSimpleMovement(move) )
			return transEngine.dividedBy(getTransformation(move),mathEngine.subtract(to,from));
		else
			throw new UnsupportedAlgorithmException();
	}

	@Override
	public boolean isSimpleMovement( Movement move ) {
		return move instanceof SimpleMovementTransExpression;
	}
}
