package magicball.model.math;


public interface NumberBasicEngine extends
	ScalarBasic.Creator,
	ScalarBasic.Attribute,
	ScalarBasic.Operator,
	ScalarBasic.Predicate,
	VectorBasic.Creator,
	VectorBasic.Attribute,
	VectorBasic.Operator,
	VectorBasic.Predicate,
	MatrixBasic.Creator,
	MatrixBasic.Attribute,
	MatrixBasic.Operator,
	MatrixBasic.Predicate
{
	// scalar ( Number )
	@Override /* ScalarBasic.Creator */
	public Num createNumberByDouble( double n );
	@Override /* ScalarBasic.Creator */
	public Num createZero();
	@Override /* ScalarBasic.Creator */
	public Num createOne();
	@Override /* ScalarBasic.Creator */
	public Num createPi();
	@Override /* ScalarBasic.Creator */
	public Num createE();

	@Override /* ScalarBasic.Attribute */
	public double getDoubleValueOf( Num n );

	@Override /* ScalarBasic.Operator */
	public Num negate( Num n );
	@Override /* ScalarBasic.Operator */
	public Num plus( Num n1, Num n2 );
	@Override /* ScalarBasic.Operator */
	public Num plus( Num... ns );
	@Override /* ScalarBasic.Operator */
	public Num minus( Num n1, Num n2 );
	@Override /* ScalarBasic.Operator */
	public Num times( Num n1, Num n2 );
	@Override /* ScalarBasic.Operator */
	public Num times( Num... ns );
	@Override /* ScalarBasic.Operator */
	public Num over( Num n1, Num n2 );
	@Override /* ScalarBasic.Operator */
	public Num pow( Num n1, int n2 );
	@Override /* ScalarBasic.Operator */
	public Num pow( Num n1, Num n2 );
	@Override /* ScalarBasic.Operator */
	public Num sqrt( Num n );
	@Override /* ScalarBasic.Operator */
	public Num abs( Num n );
	@Override /* ScalarBasic.Operator */
	public Num floor( Num n );
	@Override /* ScalarBasic.Operator */
	public Num ceil( Num n );
	@Override /* ScalarBasic.Operator */
	public Num max( Num n1, Num n2 );
	@Override /* ScalarBasic.Operator */
	public Num max( Num... ns );
	@Override /* ScalarBasic.Operator */
	public Num min( Num n1, Num n2 );
	@Override /* ScalarBasic.Operator */
	public Num min( Num... ns );
	@Override /* ScalarBasic.Operator */
	public Num exp( Num n );
	@Override /* ScalarBasic.Operator */
	public Num ln( Num n );
	@Override /* ScalarBasic.Operator */
	public Num sin( Num n );
	@Override /* ScalarBasic.Operator */
	public Num cos( Num n );
	@Override /* ScalarBasic.Operator */
	public Num tan( Num n );
	@Override /* ScalarBasic.Operator */
	public Num asin( Num n );
	@Override /* ScalarBasic.Operator */
	public Num acos( Num n );
	@Override /* ScalarBasic.Operator */
	public Num atan( Num n );

	@Override /* ScalarBasic.Predicate */
	public boolean equals( Num n1, Num n2 );
	@Override /* ScalarBasic.Predicate */
	public boolean isGreaterThan( Num n1, Num n2 );
	@Override /* ScalarBasic.Predicate */
	public boolean isLessThan( Num n1, Num n2 );
	@Override /* ScalarBasic.Predicate */
	public boolean isZero( Num n );
	@Override /* ScalarBasic.Predicate */
	public boolean isOne( Num n );


	// vector ( Number[] )
	@Override /* VectorBasic.Creator */
	public Num[] createVectorByDoubles( double... ns );
	@Override /* VectorBasic.Creator */
	public Num[] createZeroVectorWithDim( int d );

	@Override /* VectorBasic.Attribute */
	public double[] getDoubleValueOf( Num[] v );

	@Override /* VectorBasic.Operator */
	public Num[] clone( Num[] v );
	@Override /* VectorBasic.Operator */
	public Num[] subvectorOf( Num[] v, int i1, int i2 );
	@Override /* VectorBasic.Operator */
	public Num[] augmentsWith( Num[] v, Num... ns );

	@Override /* VectorBasic.Operator */
	public Num[] negate( Num[] v );
	@Override /* VectorBasic.Operator */
	public Num[] plus( Num[] v1, Num[] v2 );
	@Override /* VectorBasic.Operator */
	public Num[] plus( Num[]... vs );
	@Override /* VectorBasic.Operator */
	public Num[] minus( Num[] v1, Num[] v2 );
	@Override /* VectorBasic.Operator */
	public Num[] times( Num[] v1, Num n2 );
	@Override /* VectorBasic.Operator */
	public Num[] over( Num[] v1, Num n2 );
	@Override /* VectorBasic.Operator */
	public Num norm( Num[] v );
	@Override /* VectorBasic.Operator */
	public Num[] normalize( Num[] v );
	@Override /* VectorBasic.Operator */
	public Num dotProduct( Num[] v1, Num[] v2 );
	@Override /* VectorBasic.Operator */
	public Num[] crossProduct( Num[] v1, Num[] v2 );

	@Override /* VectorBasic.Predicate */
	public boolean equals( Num[] v1, Num[] v2 );
	@Override /* VectorBasic.Predicate */
	public boolean isZeroVector( Num[] v );


	// matrix ( Number[][] )
	@Override /* MatrixBasic.Creator */
	public Num[][] createMatrixByDoubles( double[][] ns );
	@Override /* MatrixBasic.Creator */
	public Num[][] createZeroMatrixWithDim( int d1, int d2 );
	@Override /* MatrixBasic.Creator */
	public Num[][] createIdentityMatrixWithDim( int d );

	@Override /* MatrixBasic.Attribute */
	public double[][] getDoubleValueOf( Num[][] m );

	@Override /* MatrixBasic.Operator */
	public Num[][] clone( Num[][] m );
	@Override /* MatrixBasic.Operator */
	public Num[][] colVectorOf( Num[] v );
	@Override /* MatrixBasic.Operator */
	public Num[][] rowVectorOf( Num[] v );
	@Override /* MatrixBasic.Operator */
	public Num[][] submatrixOf( Num[][] m, int i1, int i2, int j1, int j2 );
	@Override /* MatrixBasic.Operator */
	public Num[][] augmentsColumnWith( Num[][] m1, Num[][] m2 );
	@Override /* MatrixBasic.Operator */
	public Num[][] augmentsRowWith( Num[][] m1, Num[][] m2 );

	@Override /* MatrixBasic.Operator */
	public Num[][] negate( Num[][] m );
	@Override /* MatrixBasic.Operator */
	public Num[][] plus( Num[][] m1, Num[][] m2 );
	@Override /* MatrixBasic.Operator */
	public Num[][] plus( Num[][]... ms );
	@Override /* MatrixBasic.Operator */
	public Num[][] minus( Num[][] m1, Num[][] m2 );
	@Override /* MatrixBasic.Operator */
	public Num[][] times( Num[][] m1, Num n2 );
	@Override /* MatrixBasic.Operator */
	public Num[][] over( Num[][] m1, Num n2 );
	@Override /* MatrixBasic.Operator */
	public Num[][] transpose( Num[][] m1 );
	@Override /* MatrixBasic.Operator */
	public Num[][] matrixMultiply( Num[][] m1, Num[][] m2 );
	@Override /* MatrixBasic.Operator */
	public Num[][] matrixMultiply( Num[][]... ms );
	@Override /* MatrixBasic.Operator */
	public Num[][] pow( Num[][] m, int exp );
	@Override /* MatrixBasic.Operator */
	public Num[] matrixMultiply( Num[][] m1, Num[] v2 );
	@Override /* MatrixBasic.Operator */
	public Num[] matrixMultiply( Num[] v1, Num[][] m2 );
	@Override /* MatrixBasic.Operator */
	public Num trace( Num[][] m );
	@Override /* MatrixBasic.Operator */
	public Num determinant( Num[][] m );
	@Override /* MatrixBasic.Operator */
	public Num[][] invert( Num[][] m );

	@Override /* MatrixBasic.Predicate */
	public boolean equals( Num[][] m1, Num[][] m2 );
	@Override /* MatrixBasic.Predicate */
	public boolean isZeroMatrix( Num[][] m );
	@Override /* MatrixBasic.Predicate */
	public boolean isIdentityMatrix( Num[][] m );
}
