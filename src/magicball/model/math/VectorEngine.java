package magicball.model.math;


public interface VectorEngine
{
	// vector ( Number[] )
	public Number[] vector( double... ns );
	public Number[] vector0( int d );
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