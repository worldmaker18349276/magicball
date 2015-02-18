package magicball.model.puzzle.basic;

import magicball.model.geometry.*;
import magicball.model.puzzle.*;
import magicball.model.math.*;
import magicball.model.*;


public class SimpleMotionEngineForTrans implements MotionBasicEngine, Engine<SimpleMotionTransExpression>
{
	protected TransformationBasicEngine transEngine;
	protected NumberBasicEngine mathEngine;

	public SimpleMotionEngineForTrans( NumberBasicEngine mathEng, TransformationBasicEngine transEng ) {
		this.mathEngine = mathEng;
		this.transEngine = transEng;
	}

	public SimpleMotionEngineForTrans( BasicEngineProvider provider ) {
		this.mathEngine = provider.getNumberEngine();
		this.transEngine = provider.getTransformationEngine();
	}

	@Override
	public SimpleMotionEngineForTrans clone() {
		return new SimpleMotionEngineForTrans(this.mathEngine,this.transEngine);
	}


	// creater
	@Override
	public Motion createSimpleMotionByTransformation( Transformation trans ) {
		return new SimpleMotionTransExpression(trans);
	}

	@Override
	public RegionalMotion createRegionalMotion( Region reg, Motion move ) {
		return new RegionalMotion(reg,move);
	}


	// attribute
	@Override
	public Transformation getTransformation( Motion move_ ) {
		SimpleMotionTransExpression smove = cast(move_);
		return smove.getTransformation();
	}


	// operator
	@Override
	public Transformation divideMotionIntoTransformation( Motion move, Number from, Number to ) {
		if ( isSimpleMotion(move) )
			return transEngine.dividedBy(getTransformation(move),mathEngine.subtract(to,from));
		else
			throw new UnsupportedAlgorithmException();
	}

	@Override
	public java.util.List<Transformation> divideMotionByDivisor( Motion move, int divisor ) {
		java.util.List<Transformation> trans_list = new java.util.ArrayList<Transformation>();
		Number from = (Integer) 0;
		Number to = (Integer) 0;
		Number d = mathEngine.dividedBy(mathEngine.number1(),mathEngine.number(divisor));
		for ( int i=0; i<=divisor; i++ ) {
			to = mathEngine.add(from,d);
			trans_list.add(divideMotionIntoTransformation(move,from,to));
			from = to;
		}
		return trans_list;
	}

	@Override
	public java.util.List<Transformation> divideMotionByIntervals( Motion move, java.util.List<Number> intervals ) {
		java.util.List<Transformation> trans_list = new java.util.ArrayList<Transformation>();
		Number from = (Integer) 0;
		Number to = (Integer) 0;
		for ( Number d : intervals ) {
			to = mathEngine.add(from,d);
			trans_list.add(divideMotionIntoTransformation(move,from,to));
			from = to;
		}
		return trans_list;
	}

	@Override
	public boolean isSimpleMotion( Motion move ) {
		return move instanceof SimpleMotionTransExpression;
	}
}
