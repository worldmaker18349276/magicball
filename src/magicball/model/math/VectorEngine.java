package magicball.model.math;

import magicball.model.*;


public interface VectorEngine extends Engine<Number>
{
	// vector ( Number[] )
	public Number[] vector( double... ns );
	public Number[] vector0( int d );
	public Number[] clone( Number[] v );
	public Number[] subvector( Number[] v, int i1, int i2 );
	public Number[] augment( Number[] v1, Number[] v2 );
	public double[] doubleValue( Number[] v );
	public boolean equals( Number[] v1, Number[] v2 );
	public Number[] negate( Number[] v );
	public Number[] add( Number[] v1, Number[] v2 );
	public Number[] add( Number[]... vs );
	public Number[] subtract( Number[] v1, Number[] v2 );
	public Number[] multiply( Number[] v1, Number n2 );
	public Number[] dividedBy( Number[] v1, Number n2 );
	public Number norm( Number[] v );
	public Number[] normalize( Number[] v );
	public Number dotProduct( Number[] v1, Number[] v2 );
	public Number[] crossProduct( Number[] v1, Number[] v2 );
}
