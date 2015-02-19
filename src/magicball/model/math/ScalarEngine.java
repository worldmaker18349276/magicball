package magicball.model.math;

import io.netty.util.AttributeKey;


public interface ScalarEngine
{
	public static AttributeKey<ScalarEngine> KEY = AttributeKey.<ScalarEngine>valueOf("ScalarEngine");
	// scalar ( Number )
	public Number number( double n );
	public Number number0();
	public Number number1();
	public Number pi();
	public Number e();
	public double doubleValue( Number n );
	public boolean equals( Number n1, Number n2 );
	public boolean greaterThan( Number n1, Number n2 );
	public boolean lessThan( Number n1, Number n2 );
	public Number negate( Number n );
	public Number add( Number n1, Number n2 );
	public Number add( Number... ns );
	public Number subtract( Number n1, Number n2 );
	public Number multiply( Number n1, Number n2 );
	public Number multiply( Number... ns );
	public Number dividedBy( Number n1, Number n2 );
	public Number pow( Number n1, int n2 );
	public Number pow( Number n1, Number n2 );
	public Number sqrt( Number n );
	public Number abs( Number n );
	public Number floor( Number n );
	public Number ceil( Number n );
	public Number max( Number n1, Number n2 );
	public Number min( Number n1, Number n2 );
	public Number exp( Number n );
	public Number ln( Number n );
	public Number sin( Number n );
	public Number cos( Number n );
	public Number tan( Number n );
	public Number asin( Number n );
	public Number acos( Number n );
	public Number atan( Number n );
}
