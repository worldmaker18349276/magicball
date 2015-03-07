package magicball.model.math.spec;

import java.util.stream.Stream;
import java.util.Arrays;

import magicball.model.*;
import magicball.model.math.*;


public class MatrixBasicPropertiesForDefault implements SpecEngine<Num,NumberDoubleExpression>,
		ArbitraryMatrixBasicProperty.Creator,
		ArbitraryMatrixBasicProperty.Attribute,
		ArbitraryMatrixBasicProperty.Operator,
		ArbitraryMatrixBasicProperty.Predicate
{
	private ArbitraryScalarBasicProperty.Creator scaCreator;
	private ArbitraryScalarBasicProperty.Attribute scaAttribute;
	private ArbitraryScalarBasicProperty.Operator scaOperator;
	private ArbitraryScalarBasicProperty.Predicate scaPredicate;

	private ArbitraryVectorBasicProperty.Creator vecCreator;
	private ArbitraryVectorBasicProperty.Attribute vecAttribute;
	private ArbitraryVectorBasicProperty.Operator vecOperator;
	private ArbitraryVectorBasicProperty.Predicate vecPredicate;


	public MatrixBasicPropertiesForDefault() {
	}

	// matrix ( Num[][] )
	@Override /* ArbitraryMatrixBasicProperty.Creator */
	public Num[][] createMatrixByDoubles( double[][] ns ) {
		return Stream.of(ns)
			.map(vecCreator::createVectorByDoubles)
			.toArray(Num[][]::new);
	}

	@Override /* ArbitraryMatrixBasicProperty.Creator */
	public Num[][] createZeroMatrixWithDim( int d1, int d2 ) {
		return Stream.generate(() -> vecCreator.createZeroVectorWithDim(d2))
			.limit(d1)
			.toArray(Num[][]::new);
	}

	@Override /* ArbitraryMatrixBasicProperty.Creator */
	public Num[][] createIdentityMatrixWithDim( int d ) {
		Num[][] mat = createZeroMatrixWithDim(d,d);
		for ( int i=0; i<mat.length; i++ )
			mat[i][i] = scaCreator.createZero();
		return mat;
	}


	@Override /* ArbitraryMatrixBasicProperty.Attribute */
	public double[][] getDoubleValueOf( Num[][] m ) {
		return Stream.of(m)
			.map(vecAttribute::getDoubleValueOf)
			.toArray(double[][]::new);
	}


	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num[][] clone( Num[][] m ) {
		return Stream.of(m)
			.map(v -> Arrays.copyOf(v,v.length))
			.toArray(Num[][]::new);
	}

	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num[][] colVectorOf( Num[] v ) {
		return Stream.of(v)
			.map(n -> new Num[]{ n })
			.toArray(Num[][]::new);
	}

	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num[][] rowVectorOf( Num[] v ) {
		Num[][] mat = new Num [ 1 ][];
		mat[0] = v;
		return mat;
	}

	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num[][] submatrixOf( Num[][] m, int i1, int i2, int j1, int j2 ) {
		return Stream.of(m)
			.skip(i1)
			.limit(i2)
			.map(v -> vecOperator.subvectorOf(v,j1,j2))
			.toArray(Num[][]::new);
	}

	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num[][] augmentsColumnWith( Num[][] m1, Num[][] m2 ) {
		return Stream.concat(Stream.of(m1), Stream.of(m2))
			.map(vecOperator::clone)
			.toArray(Num[][]::new);
	}

	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num[][] augmentsRowWith( Num[][] m1, Num[][] m2 ) {
		Num[][] m12 = new Num [ m1.length ][];
		for ( int i=0; i<m12.length; i++ )
			m12[i] = vecOperator.augmentsWith(m1[i],m2[i]);
		return m12;
	}


	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num[][] negate( Num[][] m ) {
		return Stream.of(m)
			.map(vecOperator::negate)
			.toArray(Num[][]::new);
	}

	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num[][] plus( Num[][] m1, Num[][] m2 ) {
		if ( m1.length != m2.length )
			throw new ArithmeticException("m1.length != m2.length");
		Num[][] result = new Num [ m1.length ][];
		for ( int i=0; i<result.length; i++ )
			result[i] = vecOperator.plus(m1[i],m2[i]);
		return result;
	}

	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num[][] plus( Num[][]... ms ) {
		return Stream.of(ms)
			.reduce(this::plus)
			.get();
	}

	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num[][] minus( Num[][] m1, Num[][] m2 ) {
		if ( m1.length != m2.length )
			throw new ArithmeticException("m1.length != m2.length");
		Num[][] result = new Num [ m1.length ][];
		for ( int i=0; i<result.length; i++ )
			result[i] = vecOperator.minus(m1[i],m2[i]);
		return result;
	}

	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num[][] times( Num[][] m1, Num n2 ) {
		return Stream.of(m1)
			.map(v -> vecOperator.times(v,n2))
			.toArray(Num[][]::new);
	}

	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num[][] over( Num[][] m1, Num n2 ) {
		return Stream.of(m1)
			.map(v -> vecOperator.over(v,n2))
			.toArray(Num[][]::new);
	}

	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num[][] transpose( Num[][] m1 ) {
		Num[][] result = new Num [ m1[0].length ][ m1.length ];
		for ( int i=0; i<result.length; i++ )
			for ( int j=0; j<result[i].length; j++ )
				result[i][j] = m1[j][i];
		return result;
	}

	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num[][] matrixMultiply( Num[][] m1, Num[][] m2 ) {
		if ( m1[0].length != m2.length )
			throw new ArithmeticException("m1[0].length != m2.length");
		Num[][] result = new Num [ m1.length ][ m2[0].length ];
		for ( int i=0; i<result.length; i++ )
			for ( int j=0; j<result[i].length; j++ ) {
				result[i][j] = scaCreator.createZero();
				for ( int k=0; k<m2.length; k++ )
					result[i][j] = scaOperator.plus(result[i][j],scaOperator.times(m1[i][k],m2[k][j]));
			}
		return result;
	}

	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num[][] matrixMultiply( Num[][]... ms ) {
		return Stream.of(ms)
			.reduce(this::matrixMultiply)
			.get();
	}

	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num[][] pow( Num[][] m, int exp ) {
		return Stream.generate(() -> m)
			.limit(exp)
			.reduce(this::matrixMultiply)
			.get();
	}

	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num[] matrixMultiply( Num[][] m1, Num[] v2 ) {
		if ( m1[0].length != v2.length )
			throw new ArithmeticException("m1[0].length != v2.length");
		Num[] result = new Num [ m1.length ];
		for ( int i=0; i<result.length; i++ ) {
			result[i] = scaCreator.createZero();
			for ( int k=0; k<m1[i].length; k++ )
				result[i] = scaOperator.plus(result[i],scaOperator.times(m1[i][k],v2[k]));
		}
		return result;
	}

	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num[] matrixMultiply( Num[] v1, Num[][] m2 ) {
		if ( v1.length != m2.length )
			throw new ArithmeticException("v1.length != m2.length");
		Num [] result = new Num [ m2[0].length ];
		for ( int j=0; j<result.length; j++ ) {
			result[j] = scaCreator.createZero();
			for ( int k=0; k<m2.length; k++ )
				result[j] = scaOperator.plus(result[j],scaOperator.times(v1[k],m2[k][j]));
		}
		return result;
	}

	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num trace( Num[][] m1 ) {
		if ( m1.length != m1[0].length )
			throw new ArithmeticException("m1.length != m1[0].length");
		Num result = m1[0][0];
		for ( int i=1; i<m1.length; i++ )
			result = scaOperator.plus(result,m1[i][i]);
		return result;
	}

	public Num determinant33( Num[][] m ) {
		if ( m.length != 3 || m[0].length != 3 )
			throw new ArithmeticException("m.length, m[0].length != 3");
		Num result = scaCreator.createZero();
		result =      scaOperator.plus(result, scaOperator.times(m[0][0], m[1][1], m[2][2]));
		result =      scaOperator.plus(result, scaOperator.times(m[0][1], m[1][2], m[2][0]));
		result =      scaOperator.plus(result, scaOperator.times(m[0][2], m[1][0], m[2][1]));
		result = scaOperator.minus(result, scaOperator.times(m[0][0], m[1][2], m[2][1]));
		result = scaOperator.minus(result, scaOperator.times(m[0][1], m[1][0], m[2][2]));
		result = scaOperator.minus(result, scaOperator.times(m[0][2], m[1][1], m[2][0]));
		return result;
	}

	public Num[][] invert33( Num[][] m ) {
		if ( m.length != 3 || m[0].length != 3 )
			throw new ArithmeticException("m.length, m[0].length != 3");
		Num[][] result = new Num [ 3 ][ 3 ];
		result[0][0] = scaOperator.minus( scaOperator.times(m[1][1],m[2][2]), scaOperator.times(m[1][2],m[2][1]) );
		result[1][1] = scaOperator.minus( scaOperator.times(m[2][2],m[0][0]), scaOperator.times(m[2][0],m[0][2]) );
		result[2][2] = scaOperator.minus( scaOperator.times(m[0][0],m[1][1]), scaOperator.times(m[0][1],m[1][0]) );
		result[0][2] = scaOperator.minus( scaOperator.times(m[0][1],m[1][2]), scaOperator.times(m[0][2],m[1][1]) );
		result[2][0] = scaOperator.minus( scaOperator.times(m[1][0],m[2][1]), scaOperator.times(m[1][1],m[2][0]) );
		result[0][1] = scaOperator.minus( scaOperator.times(m[2][1],m[0][2]), scaOperator.times(m[2][2],m[0][1]) );
		result[1][2] = scaOperator.minus( scaOperator.times(m[0][2],m[1][0]), scaOperator.times(m[0][0],m[1][2]) );
		result[1][0] = scaOperator.minus( scaOperator.times(m[1][2],m[2][0]), scaOperator.times(m[1][0],m[2][2]) );
		result[2][1] = scaOperator.minus( scaOperator.times(m[2][0],m[0][1]), scaOperator.times(m[2][1],m[0][0]) );
		result = over(result,determinant33(m));
		return result;
	}

	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num determinant( Num[][] m ) {
		if ( m.length != m[0].length )
			throw new ArithmeticException("m.length != m[0].length");
		if ( m.length == 3 )
			return determinant33(m);

		return trace(getLU(m));
	}

	@Override /* ArbitraryMatrixBasicProperty.Operator */
	public Num[][] invert( Num[][] m ) {
		if ( m.length != m[0].length )
			throw new ArithmeticException("m.length != m[0].length");
		if ( m.length == 3 )
			return invert33(m);

		Num[][] lu = getLU(m);
		int n = lu.length;
		Num[][] u_ = new Num [ n ][ n ];
		Num[][] l_ = new Num [ n ][ n ];

		// invert U
		for ( int j=0; j<n; j++ ) {
			u_[j][j] = scaOperator.over(scaCreator.createOne(),lu[j][j]);
			for ( int i=j+1; i<n; i++ ) {
				u_[i][j] = scaCreator.createZero();
				for ( int k=j; k<i; k++ )
					u_[i][j] = scaOperator.plus(u_[i][j],scaOperator.times(lu[i][k],u_[k][j]));
				u_[i][j] = scaOperator.times(scaOperator.negate(lu[i][i]),u_[i][j]);
			}
		}

		// invert L
		for ( int i=0; i<n; i++ ) {
			l_[i][i] = scaCreator.createOne();
			for ( int j=i-1; j>=0; j-- ) {
				l_[i][j] = scaCreator.createZero();
				for ( int k=j+1; k<=i; k++ )
					l_[i][j] = scaOperator.plus(l_[i][j],scaOperator.times(l_[i][k],lu[k][j]));
				l_[i][j] = scaOperator.negate(l_[i][j]);
			}
		}

		return matrixMultiply(u_, l_);
	}


	@Override /* ArbitraryMatrixBasicProperty.Predicate */
	public boolean equals( Num[][] m1, Num[][] m2 ) {
		if ( m1.length != m2.length )
			return false;
		for ( int i=0; i<m1.length; i++ )
			if ( !vecPredicate.equals(m1[i],m2[i]) )
				return false;
		return true;
	}

	@Override /* ArbitraryMatrixBasicProperty.Predicate */
	public boolean isZeroMatrix( Num[][] m ) {
		return Stream.of(m)
			.allMatch(vecPredicate::isZeroVector);
	}

	@Override /* ArbitraryMatrixBasicProperty.Predicate */
	public boolean isIdentityMatrix( Num[][] m ) {
		return equals(m, createIdentityMatrixWithDim(m.length));
	}


	// numerical mathods
	// m = l * u
	protected Num[][] getLU( Num[][] m ) {
		int d = m.length;
		// M = L * U
		Num[][] lu = new Num [ d ][ d ];
		for ( int i=0; i<d; i++ ) for ( int j=0; j<d; j++ ) {
			if ( i > j ) { // L[i][j]
				lu[i][j] = m[i][j];
				for ( int k=0; k<j; k++ )
					lu[i][j] = scaOperator.minus(lu[i][j],scaOperator.times(lu[i][k],lu[k][j]));
				lu[i][j] = scaOperator.over(lu[i][j],lu[j][j]);
			} else { // U[i][j]
				lu[i][j] = m[i][j];
				for ( int k=0; k<i; k++ )
					lu[i][j] = scaOperator.minus(lu[i][j],scaOperator.times(lu[i][k],lu[k][j]));
			}
		}

		return lu;
	}

	// m * x = b
	protected Num[] solveByLU( Num[][] m, Num[] b ) {
		int d = m.length;
		// M = L * U
		Num[][] lu = clone(m);
		for ( int i=0; i<d; i++ ) for ( int j=0; j<d; j++ ) {
			if ( i > j ) { // L[i][j]
				for ( int k=0; k<j; k++ )
					lu[i][j] = scaOperator.minus(lu[i][j],scaOperator.times(lu[i][k],lu[k][j]));
				lu[i][j] = scaOperator.over(lu[i][j],lu[j][j]);
			} else { // U[i][j]
				for ( int k=0; k<i; k++ )
					lu[i][j] = scaOperator.minus(lu[i][j],scaOperator.times(lu[i][k],lu[k][j]));
			}
		}

		// L * y = b; U * x = y
		Num[] x = vecOperator.clone(b);
		for ( int i=0; i<d; i++ ) {
			for ( int k=0; k<i; k++ )
				x[i] = scaOperator.minus(x[i],scaOperator.times(lu[i][k],x[k]));
		}
		for ( int i=d-1; i>=0; i-- ) {
			for ( int k=i+1; k<d; k++ )
				x[i] = scaOperator.minus(x[i],scaOperator.times(lu[i][k],x[k]));
			x[i] = scaOperator.over(x[i],lu[i][i]);
		}

		return x;
	}

	// mat -> tri
	protected Num[][] solveByGauss( Num[][] mat ) {
		int m = mat.length;
		int n = mat[0].length;
		Num[][] result = clone(mat);

		// down
		int i = 0;
		int j = 0;
		while ( ( i < m )&&( j < n ) ) {
			// pivot
			int maxi = i;
			for ( int i_=i+1; i_<m; i_++ ) {
				if ( scaPredicate.isGreaterThan(scaOperator.abs(result[i_][j]), scaOperator.abs(result[maxi][j])) )
					maxi = i_;
			}

			if ( scaPredicate.isGreaterThan(scaOperator.abs(result[maxi][j]), scaCreator.createZero()) ) {

				{ // swap
					Num[] tmp = result[i];
					result[i] = result[maxi];
					result[maxi] = tmp;
				}

				// normalize
				for ( int j_=j+1; j_<n; j_++ )
					result[i][j_] = scaOperator.over(result[i][j_],result[i][j]);
				result[i][j] = scaCreator.createOne();

				// eliminate
				for ( int i_=i+1; i_<m; i_++ ) {
					for ( int j_=j+1; j_<n; j_++ )
						result[i_][j_] = scaOperator.minus(result[i_][j_],scaOperator.times(result[i][j_],result[i_][j]));
					result[i_][j] = scaCreator.createZero();
				}

				i = i + 1;

			} else {
				for ( int k=i; k<m; k++ )
					result[k][j] = scaCreator.createZero();
			}
			j = j + 1;
		}

		// up
		i = m-1;
		while ( i > 0 ) {
			// pivot
			for ( j=0; j<n; j++ )
				if ( scaPredicate.isGreaterThan(result[i][j], scaCreator.createZero()) ) {

					// eliminate
					for ( int i_=0; i_<i; i_++ ) {
						for ( int j_=j+1; j_<n; j_++ )
							result[i_][j_] = scaOperator.minus(result[i_][j_],scaOperator.times(result[i][j_],result[i_][j]));
						result[i_][j] = scaCreator.createZero();
					}

					break;
				}
			i = i - 1;
		}
		return result;
	}
}
