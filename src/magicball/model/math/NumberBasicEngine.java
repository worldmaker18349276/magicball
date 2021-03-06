package magicball.model.math;


public interface NumberBasicEngine
{
	public NumberBasicEngine clone();

	// scalar ( Number )
	public Number number( double n );
	public Number number0();
	public Number number1();
	public double doubleValue( Number n );
	public boolean equals( Number n1, double n2 );
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


	// vector ( Number[] )
	public Number[] vector( double... ns );
	public Number[] vector0( int d );
	public double[] doubleValue( Number[] v );
	public boolean equals( Number[] v1, double[] v2 );
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


	// matrix ( Number[][] )
	public Number[][] matrix( double[][] ns );
	public Number[][] matrix0( int d1, int d2 );
	public Number[][] matrix1( int d );
	public double[][] doubleValue( Number[][] m );
	public boolean equals( Number[][] m1, double[][] m2 );
	public boolean equals( Number[][] m1, Number[][] m2 );
	public Number[][] negate( Number[][] m );
	public Number[][] add( Number[][] m1, Number[][] m2 );
	public Number[][] add( Number[][]... ms );
	public Number[][] subtract( Number[][] m1, Number[][] m2 );
	public Number[][] multiply( Number[][] m1, Number n2 );
	public Number[][] dividedBy( Number[][] m1, Number n2 );
	public Number[][] transpose( Number[][] m1 );
	public Number[][] matrixMultiply( Number[][] m1, Number[][] m2 );
	public Number[][] matrixMultiply( Number[][]... ms );
	public Number[][] pow( Number[][] m, int exp );
	public Number[] matrixMultiply( Number[][] m1, Number[] v2 );
	public Number[] matrixMultiply( Number[] v1, Number[][] m2 );
	public Number trace( Number[][] m1 );
	public Number determinant33( Number[][] m1 );
	public Number[][] invert33( Number[][] m );


	// numerical mathods
	// m = l * u
	public Number[][] getLU( Number[][] m );
	// m * x = b
	public Number[] solveByLU( Number[][] m, Number[] b );
	// mat -> tri ( without sort )
	public Number[][] solveByGauss( Number[][] mat );
	public Number[] rotationMatrix2RotationVector( Number[][] rmat );
	public Number[][] rotationVector2RotationMatrix( Number[] rvec );
}
