package magicball.model.math;


public interface MatrixEngine
{
	// matrix ( Number[][] )
	public Number[][] matrix( double[][] ns );
	public Number[][] matrix0( int d1, int d2 );
	public Number[][] matrix1( int d );
	public Number[][] colVector( Number[] v );
	public Number[][] rowVector( Number[] v );
	public Number[][] submatrix( Number[][] m, int i1, int i2, int j1, int j2 );
	public Number[][] augmentCol( Number[][] m1, Number[][] m2 );
	public Number[][] augmentRow( Number[][] m1, Number[][] m2 );
	public double[][] doubleValue( Number[][] m );
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
	public Number determinant( Number[][] m1 );
	public Number[][] invert( Number[][] m );
}
