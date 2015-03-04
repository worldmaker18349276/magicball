package magicball.model.math;


public interface NumberBasicEngine extends
	ScalarCreator,
	ScalarAttribute,
	ScalarOperator,
	ScalarPredicate,
	VectorCreator,
	VectorAttribute,
	VectorOperator,
	VectorPredicate,
	MatrixCreator,
	MatrixAttribute,
	MatrixOperator,
	MatrixPredicate
{
	// scalar ( Number )
	@Override /* ScalarCreator */
	public Num createNumberByDouble( double n );
	@Override /* ScalarCreator */
	public Num createZero();
	@Override /* ScalarCreator */
	public Num createOne();
	@Override /* ScalarCreator */
	public Num createPi();
	@Override /* ScalarCreator */
	public Num createE();

	@Override /* ScalarAttribute */
	public double getDoubleValueOf( Num n );

	@Override /* ScalarOperator */
	public Num negate( Num n );
	@Override /* ScalarOperator */
	public Num plus( Num n1, Num n2 );
	@Override /* ScalarOperator */
	public Num plus( Num... ns );
	@Override /* ScalarOperator */
	public Num minus( Num n1, Num n2 );
	@Override /* ScalarOperator */
	public Num times( Num n1, Num n2 );
	@Override /* ScalarOperator */
	public Num times( Num... ns );
	@Override /* ScalarOperator */
	public Num over( Num n1, Num n2 );
	@Override /* ScalarOperator */
	public Num pow( Num n1, int n2 );
	@Override /* ScalarOperator */
	public Num pow( Num n1, Num n2 );
	@Override /* ScalarOperator */
	public Num sqrt( Num n );
	@Override /* ScalarOperator */
	public Num abs( Num n );
	@Override /* ScalarOperator */
	public Num floor( Num n );
	@Override /* ScalarOperator */
	public Num ceil( Num n );
	@Override /* ScalarOperator */
	public Num max( Num n1, Num n2 );
	@Override /* ScalarOperator */
	public Num max( Num... ns );
	@Override /* ScalarOperator */
	public Num min( Num n1, Num n2 );
	@Override /* ScalarOperator */
	public Num min( Num... ns );
	@Override /* ScalarOperator */
	public Num exp( Num n );
	@Override /* ScalarOperator */
	public Num ln( Num n );
	@Override /* ScalarOperator */
	public Num sin( Num n );
	@Override /* ScalarOperator */
	public Num cos( Num n );
	@Override /* ScalarOperator */
	public Num tan( Num n );
	@Override /* ScalarOperator */
	public Num asin( Num n );
	@Override /* ScalarOperator */
	public Num acos( Num n );
	@Override /* ScalarOperator */
	public Num atan( Num n );

	@Override /* ScalarPredicate */
	public boolean equals( Num n1, Num n2 );
	@Override /* ScalarPredicate */
	public boolean isGreaterThan( Num n1, Num n2 );
	@Override /* ScalarPredicate */
	public boolean isLessThan( Num n1, Num n2 );
	@Override /* ScalarPredicate */
	public boolean isZero( Num n );
	@Override /* ScalarPredicate */
	public boolean isOne( Num n );


	// vector ( Number[] )
	@Override /* VectorCreator */
	public Num[] createVectorByDoubles( double... ns );
	@Override /* VectorCreator */
	public Num[] createZeroVectorWithDim( int d );

	@Override /* VectorAttribute */
	public double[] getDoubleValueOf( Num[] v );

	@Override /* VectorOperator */
	public Num[] clone( Num[] v );
	@Override /* VectorOperator */
	public Num[] subvectorOf( Num[] v, int i1, int i2 );
	@Override /* VectorOperator */
	public Num[] augmentsWith( Num[] v, Num... ns );

	@Override /* VectorOperator */
	public Num[] negate( Num[] v );
	@Override /* VectorOperator */
	public Num[] plus( Num[] v1, Num[] v2 );
	@Override /* VectorOperator */
	public Num[] plus( Num[]... vs );
	@Override /* VectorOperator */
	public Num[] minus( Num[] v1, Num[] v2 );
	@Override /* VectorOperator */
	public Num[] times( Num[] v1, Num n2 );
	@Override /* VectorOperator */
	public Num[] over( Num[] v1, Num n2 );
	@Override /* VectorOperator */
	public Num norm( Num[] v );
	@Override /* VectorOperator */
	public Num[] normalize( Num[] v );
	@Override /* VectorOperator */
	public Num dotProduct( Num[] v1, Num[] v2 );
	@Override /* VectorOperator */
	public Num[] crossProduct( Num[] v1, Num[] v2 );

	@Override /* VectorPredicate */
	public boolean equals( Num[] v1, Num[] v2 );
	@Override /* VectorPredicate */
	public boolean isZeroVector( Num[] v );


	// matrix ( Number[][] )
	@Override /* MatrixCreator */
	public Num[][] createMatrixByDoubles( double[][] ns );
	@Override /* MatrixCreator */
	public Num[][] createZeroMatrixWithDim( int d1, int d2 );
	@Override /* MatrixCreator */
	public Num[][] createIdentityMatrixWithDim( int d );

	@Override /* MatrixAttribute */
	public double[][] getDoubleValueOf( Num[][] m );

	@Override /* MatrixOperator */
	public Num[][] clone( Num[][] m );
	@Override /* MatrixOperator */
	public Num[][] colVectorOf( Num[] v );
	@Override /* MatrixOperator */
	public Num[][] rowVectorOf( Num[] v );
	@Override /* MatrixOperator */
	public Num[][] submatrixOf( Num[][] m, int i1, int i2, int j1, int j2 );
	@Override /* MatrixOperator */
	public Num[][] augmentsColumnWith( Num[][] m, Num[]... vs );
	@Override /* MatrixOperator */
	public Num[][] augmentsRowWith( Num[][] m, Num[]... vs );

	@Override /* MatrixOperator */
	public Num[][] negate( Num[][] m );
	@Override /* MatrixOperator */
	public Num[][] plus( Num[][] m1, Num[][] m2 );
	@Override /* MatrixOperator */
	public Num[][] plus( Num[][]... ms );
	@Override /* MatrixOperator */
	public Num[][] minus( Num[][] m1, Num[][] m2 );
	@Override /* MatrixOperator */
	public Num[][] times( Num[][] m1, Num n2 );
	@Override /* MatrixOperator */
	public Num[][] over( Num[][] m1, Num n2 );
	@Override /* MatrixOperator */
	public Num[][] transpose( Num[][] m1 );
	@Override /* MatrixOperator */
	public Num[][] matrixMultiply( Num[][] m1, Num[][] m2 );
	@Override /* MatrixOperator */
	public Num[][] matrixMultiply( Num[][]... ms );
	@Override /* MatrixOperator */
	public Num[][] pow( Num[][] m, int exp );
	@Override /* MatrixOperator */
	public Num[] matrixMultiply( Num[][] m1, Num[] v2 );
	@Override /* MatrixOperator */
	public Num[] matrixMultiply( Num[] v1, Num[][] m2 );
	@Override /* MatrixOperator */
	public Num trace( Num[][] m );
	@Override /* MatrixOperator */
	public Num determinant( Num[][] m );
	@Override /* MatrixOperator */
	public Num[][] invert( Num[][] m );

	@Override /* MatrixPredicate */
	public boolean equals( Num[][] m1, Num[][] m2 );
	@Override /* MatrixPredicate */
	public boolean isZeroMatrix( Num[][] m );
	@Override /* MatrixPredicate */
	public boolean isIdentityMatrix( Num[][] m );
}
