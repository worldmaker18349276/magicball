package magicball.model.puzzle.basic;

import io.netty.util.DefaultAttributeMap;

import magicball.model.geometry.*;
import magicball.model.puzzle.*;
import magicball.model.math.*;
import magicball.model.*;


public class SimpleMotionEngineForTrans extends DefaultAttributeMap implements MotionBasicEngine, Engine<SimpleMotionTransExpression>
{
	public SimpleMotionEngineForTrans() {
		super();
	}

	public SimpleMotionEngineForTrans( NumberBasicEngine numEng, TransformationAdvancedEngine transEng ) {
		super();
		setEngine(numEng);
		setEngine(transEng);
	}

	public void setEngine( NumberBasicEngine numEng ) {
		attr(NumberBasicEngine.KEY).set(numEng);
	}

	public void setEngine( TransformationAdvancedEngine transEng ) {
		attr(TransformationAdvancedEngine.KEY).set(transEng);
	}

	public NumberBasicEngine numEngine() {
		return attr(NumberBasicEngine.KEY).get();
	}

	public TransformationAdvancedEngine transEngine() {
		return attr(TransformationAdvancedEngine.KEY).get();
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
			return transEngine().dividedBy(getTransformation(move),numEngine().subtract(to,from));
		else
			throw new UnsupportedAlgorithmException();
	}

	@Override
	public java.util.List<Transformation> divideMotionByDivisor( Motion move, int divisor ) {
		java.util.List<Transformation> trans_list = new java.util.ArrayList<Transformation>();
		Number from = (Integer) 0;
		Number to = (Integer) 0;
		Number d = numEngine().dividedBy(numEngine().number1(),numEngine().number(divisor));
		for ( int i=0; i<=divisor; i++ ) {
			to = numEngine().add(from,d);
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
			to = numEngine().add(from,d);
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
