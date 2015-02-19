package magicball.model.math.basic;

import magicball.model.*;
import magicball.model.math.*;


public class ScalarEngineForDouble implements ScalarEngine, Engine<Double>
{
	protected double epsilon;

	public ScalarEngineForDouble( double eps ) {
		this.epsilon = eps;
	}

	public double eps() {
		return this.epsilon;
	}


	// scalar ( Number )
	@Override
	public Number number( double n ) {
		return (Double) n;
	}

	@Override
	public Number number0() {
		return number(0.0);
	}

	@Override
	public Number number1() {
		return number(1.0);
	}

	@Override
	public Number pi() {
		return number(Math.PI);
	}

	@Override
	public Number e() {
		return number(Math.E);
	}

	@Override
	public double doubleValue( Number n ) {
		return n.doubleValue();
	}

	@Override
	public boolean equals( Number n1, Number n2 ) {
		return Math.abs(doubleValue(n1)-doubleValue(n2)) < this.epsilon;
	}

	@Override
	public boolean greaterThan( Number n1, Number n2 ) {
		return (doubleValue(n1)-doubleValue(n2)) > this.epsilon;
	}

	@Override
	public boolean lessThan( Number n1, Number n2 ) {
		return (doubleValue(n1)-doubleValue(n2)) < -this.epsilon;
	}

	@Override
	public Number negate( Number n ) {
		return number(-doubleValue(n));
	}

	@Override
	public Number add( Number n1, Number n2 ) {
		return number( doubleValue(n1) + doubleValue(n2) );
	}

	@Override
	public Number add( Number... ns ) {
		Number result = ns[0];
		for ( int i=1; i<ns.length; i++ )
			result = add(result,ns[i]);
		return result;
	}

	@Override
	public Number subtract( Number n1, Number n2 ) {
		return number( doubleValue(n1) - doubleValue(n2) );
	}

	@Override
	public Number multiply( Number n1, Number n2 ) {
		return number( doubleValue(n1) * doubleValue(n2) );
	}

	@Override
	public Number multiply( Number... ns ) {
		Number result = ns[0];
		for ( int i=1; i<ns.length; i++ )
			result = multiply(result,ns[i]);
		return result;
	}

	@Override
	public Number dividedBy( Number n1, Number n2 ) {
		return number( doubleValue(n1) / doubleValue(n2) );
	}

	@Override
	public Number pow( Number n1, int n2 ) {
		return number( Math.pow(doubleValue(n1),n2) );
	}

	@Override
	public Number pow( Number n1, Number n2 ) {
		return number( Math.pow(doubleValue(n1),doubleValue(n2)) );
	}

	@Override
	public Number sqrt( Number n ) {
		return number(Math.sqrt(doubleValue(n)));
	}

	@Override
	public Number abs( Number n ) {
		return number(Math.abs(doubleValue(n)));
	}

	@Override
	public Number floor( Number n ) {
		return number(Math.floor(doubleValue(n)));
	}

	@Override
	public Number ceil( Number n ) {
		return number(Math.ceil(doubleValue(n)));
	}

	@Override
	public Number max( Number n1, Number n2 ) {
		return number(Math.max(doubleValue(n1), doubleValue(n2)));
	}

	@Override
	public Number min( Number n1, Number n2 ) {
		return number(Math.min(doubleValue(n1), doubleValue(n2)));
	}

	@Override
	public Number exp( Number n ) {
		return number(Math.exp(doubleValue(n)));
	}

	@Override
	public Number ln( Number n ) {
		return number(Math.log(doubleValue(n)));
	}

	@Override
	public Number sin( Number n ) {
		return number(Math.sin(doubleValue(n)));
	}

	@Override
	public Number cos( Number n ) {
		return number(Math.cos(doubleValue(n)));
	}

	@Override
	public Number tan( Number n ) {
		return number(Math.tan(doubleValue(n)));
	}

	@Override
	public Number asin( Number n ) {
		return number(Math.asin(doubleValue(n)));
	}

	@Override
	public Number acos( Number n ) {
		return number(Math.acos(doubleValue(n)));
	}

	@Override
	public Number atan( Number n ) {
		return number(Math.atan(doubleValue(n)));
	}
}
