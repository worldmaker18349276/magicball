package magicball.model.math;


public interface NumberBasicEngine extends
	ArbitraryScalarBasicProperty.Creator,
	ArbitraryScalarBasicProperty.Attribute,
	ArbitraryScalarBasicProperty.Operator,
	ArbitraryScalarBasicProperty.Predicate,
	ArbitraryVectorBasicProperty.Creator,
	ArbitraryVectorBasicProperty.Attribute,
	ArbitraryVectorBasicProperty.Operator,
	ArbitraryVectorBasicProperty.Predicate,
	ArbitraryMatrixBasicProperty.Creator,
	ArbitraryMatrixBasicProperty.Attribute,
	ArbitraryMatrixBasicProperty.Operator,
	ArbitraryMatrixBasicProperty.Predicate
{
	// scalar ( Number )
	@Override /* ArbitraryScalarBasicProperty.Creator */
	public Num createNumberByDouble( double n );
	@Override /* ArbitraryScalarBasicProperty.Creator */
	public Num createZero();
	@Override /* ArbitraryScalarBasicProperty.Creator */
	public Num createOne();
	@Override /* ArbitraryScalarBasicProperty.Creator */
	public Num createPi();
	@Override /* ArbitraryScalarBasicProperty.Creator */
	public Num createE();

	@Override /* ArbitraryScalarBasicProperty.Attribute */
	public double getDoubleValueOf( Num n );

	@Override /* ArbitraryScalarBasicProperty.Operator */
	public Num negate( Num n );
	@Override /* ArbitraryScalarBasicProperty.Operator */
	public Num plus( Num n1, Num n2 );
	@Override /* ArbitraryScalarBasicProperty.Operator */
	public Num plus( Num... ns );
	@Override /* ArbitraryScalarBasicProperty.Operator */
	public Num minus( Num n1, Num n2 );
	@Override /* ArbitraryScalarBasicProperty.Operator */
	public Num times( Num n1, Num n2 );
	@Override /* ArbitraryScalarBasicProperty.Operator */
	public Num times( Num... ns );
	@Override /* ArbitraryScalarBasicProperty.Operator */
	public Num over( Num n1, Num n2 );
	@Override /* ArbitraryScalarBasicProperty.Operator */
	public Num pow( Num n1, int n2 );
	@Override /* ArbitraryScalarBasicProperty.Operator */
	public Num pow( Num n1, Num n2 );
	@Override /* ArbitraryScalarBasicProperty.Operator */
	public Num sqrt( Num n );
	@Override /* ArbitraryScalarBasicProperty.Operator */
	public Num abs( Num n );
	@Override /* ArbitraryScalarBasicProperty.Operator */
	public Num floor( Num n );
	@Override /* ArbitraryScalarBasicProperty.Operator */
	public Num ceil( Num n );
	@Override /* ArbitraryScalarBasicProperty.Operator */
	public Num max( Num n1, Num n2 );
	@Override /* ArbitraryScalarBasicProperty.Operator */
	public Num max( Num... ns );
	@Override /* ArbitraryScalarBasicProperty.Operator */
	public Num min( Num n1, Num n2 );
	@Override /* ArbitraryScalarBasicProperty.Operator */
	public Num min( Num... ns );
	@Override /* ArbitraryScalarBasicProperty.Operator */
	public Num exp( Num n );
	@Override /* ArbitraryScalarBasicProperty.Operator */
	public Num ln( Num n );
	@Override /* ArbitraryScalarBasicProperty.Operator */
	public Num sin( Num n );
	@Override /* ArbitraryScalarBasicProperty.Operator */
	public Num cos( Num n );
	@Override /* ArbitraryScalarBasicProperty.Operator */
	public Num tan( Num n );
	@Override /* ArbitraryScalarBasicProperty.Operator */
	public Num asin( Num n );
	@Override /* ArbitraryScalarBasicProperty.Operator */
	public Num acos( Num n );
	@Override /* ArbitraryScalarBasicProperty.Operator */
	public Num atan( Num n );

	@Override /* ArbitraryScalarBasicProperty.Predicate */
	public boolean equals( Num n1, Num n2 );
	@Override /* ArbitraryScalarBasicProperty.Predicate */
	public boolean isGreaterThan( Num n1, Num n2 );
	@Override /* ArbitraryScalarBasicProperty.Predicate */
	public boolean isLessThan( Num n1, Num n2 );
	@Override /* ArbitraryScalarBasicProperty.Predicate */
	public boolean isZero( Num n );
	@Override /* ArbitraryScalarBasicProperty.Predicate */
	public boolean isOne( Num n );


	// vector ( Number[] )
	@Override /* ArbitraryVectorBasicProperty.Creator */
	public Num[] createVectorByDoubles( double... ns );
	@Override /* ArbitraryVectorBasicProperty.Creator */
	public Num[] createZeroVectorWithDim( int d );

	@Override /* ArbitraryVectorBasicProperty.Attribute */
	public double[] getDoubleValueOf( Num[] v );

	@Override /* ArbitraryVectorBasicProperty.Operator */
	public Num[] clone( Num[] v );
	@Override /* ArbitraryVectorBasicProperty.Operator */
	public Num[] subvectorOf( Num[] v, int i1, int i2 );
	@Override /* ArbitraryVectorBasicProperty.Operator */
	public Num[] augmentsWith( Num[] v, Num... ns );

	@Override /* ArbitraryVectorBasicProperty.Operator */
	public Num[] negate( Num[] v );
	@Override /* ArbitraryVectorBasicProperty.Operator */
	public Num[] plus( Num[] v1, Num[] v2 );
	@Override /* ArbitraryVectorBasicProperty.Operator */
	public Num[] plus( Num[]... vs );
	@Override /* ArbitraryVectorBasicProperty.Operator */
	public Num[] minus( Num[] v1, Num[] v2 );
	@Override /* ArbitraryVectorBasicProperty.Operator */
	public Num[] times( Num[] v1, Num n2 );
	@Override /* ArbitraryVectorBasicProperty.Operator */
	public Num[] over( Num[] v1, Num n2 );
	@Override /* ArbitraryVectorBasicProperty.Operator */
	public Num norm( Num[] v );
	@Override /* ArbitraryVectorBasicProperty.Operator */
	public Num[] normalize( Num[] v );
	@Override /* ArbitraryVectorBasicProperty.Operator */
	public Num dotProduct( Num[] v1, Num[] v2 );
	@Override /* ArbitraryVectorBasicProperty.Operator */
	public Num[] crossProduct( Num[] v1, Num[] v2 );

	@Override /* ArbitraryVectorBasicProperty.Predicate */
	public boolean equals( Num[] v1, Num[] v2 );
	@Override /* ArbitraryVectorBasicProperty.Predicate */
	public boolean isZeroVector( Num[] v );


	// matrix ( Number[][] )
	@Override /* ArbitraryMatrixBasicProperty.Creator */
	public Num[][] createMatrixByDoubles( double[][] ns );
	@Override /* ArbitraryMatrixBasicProperty.Creator */
	public Num[][] createZeroMatrixWithDim( int d1, int d2 );
	@Override /* ArbitraryMatrixBasicProperty.Creator */
	public Num[][] createIdentityMatrixWithDim( int d );

	@Override /* ArbitraryMatrixBasicProperty.Attribute */
	public double[][] getDoubleValueOf( Num[][] m );

	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num[][] clone( Num[][] m );
	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num[][] colVectorOf( Num[] v );
	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num[][] rowVectorOf( Num[] v );
	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num[][] submatrixOf( Num[][] m, int i1, int i2, int j1, int j2 );
	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num[][] augmentsColumnWith( Num[][] m1, Num[][] m2 );
	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num[][] augmentsRowWith( Num[][] m1, Num[][] m2 );

	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num[][] negate( Num[][] m );
	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num[][] plus( Num[][] m1, Num[][] m2 );
	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num[][] plus( Num[][]... ms );
	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num[][] minus( Num[][] m1, Num[][] m2 );
	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num[][] times( Num[][] m1, Num n2 );
	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num[][] over( Num[][] m1, Num n2 );
	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num[][] transpose( Num[][] m1 );
	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num[][] matrixMultiply( Num[][] m1, Num[][] m2 );
	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num[][] matrixMultiply( Num[][]... ms );
	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num[][] pow( Num[][] m, int exp );
	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num[] matrixMultiply( Num[][] m1, Num[] v2 );
	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num[] matrixMultiply( Num[] v1, Num[][] m2 );
	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num trace( Num[][] m );
	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num determinant( Num[][] m );
	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num[][] invert( Num[][] m );

	@Override /* ArbitraryMatrixBasicProperty.Predicate */
	public boolean equals( Num[][] m1, Num[][] m2 );
	@Override /* ArbitraryMatrixBasicProperty.Predicate */
	public boolean isZeroMatrix( Num[][] m );
	@Override /* ArbitraryMatrixBasicProperty.Predicate */
	public boolean isIdentityMatrix( Num[][] m );
}
