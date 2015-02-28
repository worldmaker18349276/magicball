package magicball.model.math.basic;

import magicball.model.*;
import magicball.model.math.*;


public class DefaultVectorEngine implements VectorEngine, Engine<Number>
{
	private ScalarEngine scaEngine;

	public DefaultVectorEngine() {
		super();
	}

	public DefaultVectorEngine( ScalarEngine scaEng ) {
		super();
		setEngine(scaEng);
	}

	public void setEngine( ScalarEngine scaEng ) {
		scaEngine = scaEng;
	}


	// vector ( Number[] )
	@Override
	public Number[] vector( double... ns ) {
		Number[] vec = new Number [ ns.length ];
		for ( int i=0; i<vec.length; i++ )
			vec[i] = scaEngine.number(ns[i]);
		return vec;
	}

	@Override
	public Number[] vector0( int d ) {
		Number[] vec = new Number [ d ];
		for ( int i=0; i<vec.length; i++ )
			vec[i] = scaEngine.number0();
		return vec;
	}

	@Override
	public Number[] clone( Number[] v ) {
		Number[] vec = new Number [ v.length ];
		for ( int i=0; i<vec.length; i++ )
			vec[i] = v[i];
		return vec;
	}

	@Override
	public Number[] subvector( Number[] v, int i1, int i2 ) {
		Number[] v_ = new Number [ i2-i1 ];
		for ( int i=i1; i<i2; i++ )
			v_[i-i1] = v[i];
		return v_;
	}

	@Override
	public Number[] augment( Number[] v1, Number[] v2 ) {
		Number[] v12 = new Number [ v1.length+v2.length ];
		for ( int i=0; i<v1.length; i++ )
			v12[i] = v1[i];
		for ( int i=0; i<v2.length; i++ )
			v12[i+v1.length] = v2[i];
		return v12;
	}

	@Override
	public double[] doubleValue( Number[] v ) {
		double[] result = new double [ v.length ];
		for ( int i=0; i<result.length; i++ )
			result[i] = scaEngine.doubleValue(v[i]);
		return result;
	}

	@Override
	public boolean equals( Number[] v1, Number[] v2 ) {
		if ( v1.length != v2.length )
			return false;
		for ( int i=0; i<v1.length; i++ )
			if ( !scaEngine.equals(v1[i],v2[i]) )
				return false;
		return true;
	}

	@Override
	public Number[] negate( Number[] v ) {
		Number[] result = new Number [ v.length ];
		for ( int i=0; i<result.length; i++ )
			result[i] = scaEngine.negate(v[i]);
		return result;
	}

	@Override
	public Number[] add( Number[] v1, Number[] v2 ) {
		if ( v1.length != v2.length )
			throw new ArithmeticException("v1.length != v2.length");
		Number[] result = new Number [ v1.length ];
		for ( int i=0; i<result.length; i++ )
			result[i] = scaEngine.add(v1[i],v2[i]);
		return result;
	}

	@Override
	public Number[] add( Number[]... vs ) {
		Number[] result = vs[0];
		for ( int i=1; i<vs.length; i++ )
			result = add(result,vs[i]);
		return result;
	}

	@Override
	public Number[] subtract( Number[] v1, Number[] v2 ) {
		if ( v1.length != v2.length )
			throw new ArithmeticException("v1.length != v2.length");
		Number[] result = new Number [ v1.length ];
		for ( int i=0; i<result.length; i++ )
			result[i] = scaEngine.subtract(v1[i],v2[i]);
		return result;
	}

	@Override
	public Number[] multiply( Number[] v1, Number n2 ) {
		Number[] result = new Number [ v1.length ];
		for ( int i=0; i<result.length; i++ )
			result[i] = scaEngine.multiply(v1[i],n2);
		return result;
	}

	@Override
	public Number[] dividedBy( Number[] v1, Number n2 ) {
		Number[] result = new Number [ v1.length ];
		for ( int i=0; i<result.length; i++ )
			result[i] = scaEngine.dividedBy(v1[i],n2);
		return result;
	}

	@Override
	public Number norm( Number[] v ) {
		Number result = scaEngine.number0();
		for ( int i=0; i<v.length; i++ )
			result = scaEngine.add(result,scaEngine.pow(v[i],2));
		return scaEngine.sqrt(result);
	}

	@Override
	public Number[] normalize( Number[] v ) {
		return dividedBy(v,norm(v));
	}

	@Override
	public Number dotProduct( Number[] v1, Number[] v2 ) {
		Number result = scaEngine.number0();
			for ( int i=0; i<v1.length; i++ )
				result = scaEngine.add(result,scaEngine.multiply(v1[i],v2[i]));
			return result;
	}

	@Override
	public Number[] crossProduct( Number[] v1, Number[] v2 ) {
		Number[] result = new Number [ 3 ];
		result[0] = scaEngine.subtract(scaEngine.multiply(v1[1],v2[2]),scaEngine.multiply(v1[2],v2[1]));
		result[1] = scaEngine.subtract(scaEngine.multiply(v1[2],v2[0]),scaEngine.multiply(v1[0],v2[2]));
		result[2] = scaEngine.subtract(scaEngine.multiply(v1[0],v2[1]),scaEngine.multiply(v1[1],v2[0]));
		return result;
	}
}
