package magicball.model.math;

import magicball.model.*;


public class MatrixBasic
{
	static public interface Creator extends Engine<Num>
	{
		public Num[][] createMatrixByDoubles( double[][] ns );
		public Num[][] createZeroMatrixWithDim( int d1, int d2 );
		public Num[][] createIdentityMatrixWithDim( int d );
	}

	static public interface Attribute extends Engine<Num>
	{
		public double[][] getDoubleValueOf( Num[][] m );
	}

	static public interface Operator extends Engine<Num>
	{
		public Num[][] clone( Num[][] m );
		public Num[][] colVectorOf( Num[] v );
		public Num[][] rowVectorOf( Num[] v );
		public Num[][] submatrixOf( Num[][] m, int i1, int i2, int j1, int j2 );
		public Num[][] augmentsColumnWith( Num[][] m1, Num[][] m2 );
		public Num[][] augmentsRowWith( Num[][] m1, Num[][] m2 );

		public Num[][] negate( Num[][] m );
		public Num[][] plus( Num[][] m1, Num[][] m2 );
		public Num[][] plus( Num[][]... ms );
		public Num[][] minus( Num[][] m1, Num[][] m2 );
		public Num[][] times( Num[][] m1, Num n2 );
		public Num[][] over( Num[][] m1, Num n2 );
		public Num[][] transpose( Num[][] m1 );
		public Num[][] matrixMultiply( Num[][] m1, Num[][] m2 );
		public Num[][] matrixMultiply( Num[][]... ms );
		public Num[][] pow( Num[][] m, int exp );
		public Num[] matrixMultiply( Num[][] m1, Num[] v2 );
		public Num[] matrixMultiply( Num[] v1, Num[][] m2 );
		public Num trace( Num[][] m );
		public Num determinant( Num[][] m );
		public Num[][] invert( Num[][] m );
	}

	static public interface Predicate extends Engine<Num>
	{
		public boolean equals( Num[][] m1, Num[][] m2 );
		public boolean isZeroMatrix( Num[][] m );
		public boolean isIdentityMatrix( Num[][] m );
	}
}
