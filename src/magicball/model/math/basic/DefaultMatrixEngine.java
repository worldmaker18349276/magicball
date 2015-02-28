package magicball.model.math.basic;

import magicball.model.*;
import magicball.model.math.*;


public class DefaultMatrixEngine implements MatrixEngine, SpecEngine<Number,Number>
{
	private ScalarEngine scaEngine;
	private VectorEngine vecEngine;

	public DefaultMatrixEngine() {
		super();
	}

	public DefaultMatrixEngine( ScalarEngine scaEng, VectorEngine vecEng ) {
		super();
		setEngine(scaEng);
		setEngine(vecEng);
	}

	public void setEngine( ScalarEngine scaEng ) {
		scaEngine = scaEng;
	}

	public void setEngine( VectorEngine vecEng ) {
		vecEngine = vecEng;
	}

	// matrix ( Number[][] )
	@Override
	public Number[][] matrix( double[][] ns ) {
		Number[][] mat = new Number [ ns.length ][];
		for ( int i=0; i<mat.length; i++ )
			mat[i] = vecEngine.vector(ns[i]);
		return mat;
	}

	@Override
	public Number[][] matrix0( int d1, int d2 ) {
		Number[][] mat = new Number [ d1 ][];
		for ( int i=0; i<mat.length; i++ )
			mat[i] = vecEngine.vector0(d2);
		return mat;
	}

	@Override
	public Number[][] matrix1( int d ) {
		Number[][] mat = matrix0(d,d);
		for ( int i=0; i<mat.length; i++ )
			mat[i][i] = scaEngine.number1();
		return mat;
	}

	@Override
	public Number[][] clone( Number[][] m ) {
		Number[][] mat = new Number [ m.length ][];
		for ( int i=0; i<mat.length; i++ )
			mat[i] = vecEngine.clone(m[i]);
		return mat;
	}

	@Override
	public Number[][] colVector( Number[] v ) {
		Number[][] mat = new Number [ v.length ][ 1 ];
		for ( int i=0; i<mat.length; i++ )
			mat[i][0] = v[i];
		return mat;
	}

	@Override
	public Number[][] rowVector( Number[] v ) {
		Number[][] mat = new Number [ 1 ][];
		mat[0] = v;
		return mat;
	}

	@Override
	public Number[][] submatrix( Number[][] m, int i1, int i2, int j1, int j2 ) {
		Number[][] m_ = new Number [ i2-i1 ][];
		for ( int i=i1; i<i2; i++ )
			m_[i-i1] = vecEngine.subvector(m[i], j1, j2);
		return m_;
	}

	@Override
	public Number[][] augmentCol( Number[][] m1, Number[][] m2 ) {
		Number[][] m12 = new Number [ m1.length+m2.length ][];
		for ( int i=0; i<m1.length; i++ )
			m12[i] = m1[i];
		for ( int i=0; i<m2.length; i++ )
			m12[i+m1.length] = m2[i];
		return m12;
	}

	@Override
	public Number[][] augmentRow( Number[][] m1, Number[][] m2 ) {
		Number[][] m12 = new Number [ m1.length ][];
		for ( int i=0; i<m12.length; i++ )
			m12[i] = vecEngine.augment(m1[i],m2[i]);
		return m12;
	}

	@Override
	public double[][] doubleValue( Number[][] m ) {
		double[][] result = new double [ m.length ][];
		for ( int i=0; i<result.length; i++ )
			result[i] = vecEngine.doubleValue(m[i]);
		return result;
	}

	@Override
	public boolean equals( Number[][] m1, Number[][] m2 ) {
		if ( m1.length != m2.length )
			return false;
		for ( int i=0; i<m1.length; i++ )
			if ( !vecEngine.equals(m1[i],m2[i]) )
				return false;
		return true;
	}

	@Override
	public Number[][] negate( Number[][] m ) {
		Number[][] result = new Number [ m.length ][];
		for ( int i=0; i<result.length; i++ )
			result[i] = vecEngine.negate(m[i]);
		return result;
	}

	@Override
	public Number[][] add( Number[][] m1, Number[][] m2 ) {
		if ( m1.length != m2.length )
			throw new ArithmeticException("m1.length != m2.length");
		Number[][] result = new Number [ m1.length ][];
		for ( int i=0; i<result.length; i++ )
			result[i] = vecEngine.add(m1[i],m2[i]);
		return result;
	}

	@Override
	public Number[][] add( Number[][]... ms ) {
		Number[][] result = ms[0];
		for ( int i=1; i<ms.length; i++ )
			result = add(result,ms[i]);
		return result;
	}

	@Override
	public Number[][] subtract( Number[][] m1, Number[][] m2 ) {
		if ( m1.length != m2.length )
			throw new ArithmeticException("m1.length != m2.length");
		Number[][] result = new Number [ m1.length ][];
		for ( int i=0; i<result.length; i++ )
			result[i] = vecEngine.subtract(m1[i],m2[i]);
		return result;
	}

	@Override
	public Number[][] multiply( Number[][] m1, Number n2 ) {
		Number[][] result = new Number [ m1.length ][];
		for ( int i=0; i<result.length; i++ )
			result[i] = vecEngine.multiply(m1[i],n2);
		return result;
	}

	@Override
	public Number[][] dividedBy( Number[][] m1, Number n2 ) {
		Number[][] result = new Number [ m1.length ][];
		for ( int i=0; i<result.length; i++ )
			result[i] = vecEngine.dividedBy(m1[i],n2);
		return result;
	}

	@Override
	public Number[][] transpose( Number[][] m1 ) {
		Number[][] result = new Number [ m1[0].length ][ m1.length ];
		for ( int i=0; i<result.length; i++ )
			for ( int j=0; j<result[i].length; j++ )
				result[i][j] = m1[j][i];
		return result;
	}

	@Override
	public Number[][] matrixMultiply( Number[][] m1, Number[][] m2 ) {
		if ( m1[0].length != m2.length )
			throw new ArithmeticException("m1[0].length != m2.length");
		Number[][] result = new Number [ m1.length ][ m2[0].length ];
		for ( int i=0; i<result.length; i++ )
			for ( int j=0; j<result[i].length; j++ ) {
				result[i][j] = scaEngine.number0();
				for ( int k=0; k<m2.length; k++ )
					result[i][j] = scaEngine.add(result[i][j],scaEngine.multiply(m1[i][k],m2[k][j]));
			}
		return result;
	}

	@Override
	public Number[][] matrixMultiply( Number[][]... ms ) {
		Number[][] result = ms[0];
		for ( int i=1; i<ms.length; i++ )
			result = matrixMultiply(result,ms[i]);
		return result;
	}

	@Override
	public Number[][] pow( Number[][] m, int exp ) {
		Number[][] result = m;
		for ( int i=1; i<exp; i++ )
			result = matrixMultiply(result,m);
		return result;
	}

	@Override
	public Number[] matrixMultiply( Number[][] m1, Number[] v2 ) {
		if ( m1[0].length != v2.length )
			throw new ArithmeticException("m1[0].length != v2.length");
		Number[] result = new Number [ m1.length ];
		for ( int i=0; i<result.length; i++ ) {
			result[i] = scaEngine.number0();
			for ( int k=0; k<m1[i].length; k++ )
				result[i] = scaEngine.add(result[i],scaEngine.multiply(m1[i][k],v2[k]));
		}
		return result;
	}

	@Override
	public Number[] matrixMultiply( Number[] v1, Number[][] m2 ) {
		if ( v1.length != m2.length )
			throw new ArithmeticException("v1.length != m2.length");
		Number [] result = new Number [ m2[0].length ];
		for ( int j=0; j<result.length; j++ ) {
			result[j] = scaEngine.number0();
			for ( int k=0; k<m2.length; k++ )
				result[j] = scaEngine.add(result[j],scaEngine.multiply(v1[k],m2[k][j]));
		}
		return result;
	}

	@Override
	public Number trace( Number[][] m1 ) {
		if ( m1.length != m1[0].length )
			throw new ArithmeticException("m1.length != m1[0].length");
		Number result = m1[0][0];
		for ( int i=1; i<m1.length; i++ )
			result = scaEngine.add(result,m1[i][i]);
		return result;
	}

	@Override
	public Number determinant33( Number[][] m ) {
		if ( m.length != 3 || m[0].length != 3 )
			throw new ArithmeticException("m.length, m[0].length != 3");
		Number result = scaEngine.number0();
		result =      scaEngine.add(result, scaEngine.multiply(m[0][0], m[1][1], m[2][2]));
		result =      scaEngine.add(result, scaEngine.multiply(m[0][1], m[1][2], m[2][0]));
		result =      scaEngine.add(result, scaEngine.multiply(m[0][2], m[1][0], m[2][1]));
		result = scaEngine.subtract(result, scaEngine.multiply(m[0][0], m[1][2], m[2][1]));
		result = scaEngine.subtract(result, scaEngine.multiply(m[0][1], m[1][0], m[2][2]));
		result = scaEngine.subtract(result, scaEngine.multiply(m[0][2], m[1][1], m[2][0]));
		return result;
	}

	@Override
	public Number[][] invert33( Number[][] m ) {
		if ( m.length != 3 || m[0].length != 3 )
			throw new ArithmeticException("m.length, m[0].length != 3");
		Number[][] result = new Number [ 3 ][ 3 ];
		result[0][0] = scaEngine.subtract( scaEngine.multiply(m[1][1],m[2][2]), scaEngine.multiply(m[1][2],m[2][1]) );
		result[1][1] = scaEngine.subtract( scaEngine.multiply(m[2][2],m[0][0]), scaEngine.multiply(m[2][0],m[0][2]) );
		result[2][2] = scaEngine.subtract( scaEngine.multiply(m[0][0],m[1][1]), scaEngine.multiply(m[0][1],m[1][0]) );
		result[0][2] = scaEngine.subtract( scaEngine.multiply(m[0][1],m[1][2]), scaEngine.multiply(m[0][2],m[1][1]) );
		result[2][0] = scaEngine.subtract( scaEngine.multiply(m[1][0],m[2][1]), scaEngine.multiply(m[1][1],m[2][0]) );
		result[0][1] = scaEngine.subtract( scaEngine.multiply(m[2][1],m[0][2]), scaEngine.multiply(m[2][2],m[0][1]) );
		result[1][2] = scaEngine.subtract( scaEngine.multiply(m[0][2],m[1][0]), scaEngine.multiply(m[0][0],m[1][2]) );
		result[1][0] = scaEngine.subtract( scaEngine.multiply(m[1][2],m[2][0]), scaEngine.multiply(m[1][0],m[2][2]) );
		result[2][1] = scaEngine.subtract( scaEngine.multiply(m[2][0],m[0][1]), scaEngine.multiply(m[2][1],m[0][0]) );
		result = dividedBy(result,determinant33(m));
		return result;
	}

	@Override
	public Number determinant( Number[][] m ) {
		if ( m.length != m[0].length )
			throw new ArithmeticException("m.length != m[0].length");
		return trace(getLU(m));
	}

	@Override
	public Number[][] invert( Number[][] m ) {
		if ( m.length != m[0].length )
			throw new ArithmeticException("m.length != m[0].length");
		Number[][] lu = getLU(m);
		int n = lu.length;
		Number[][] u_ = new Number [ n ][ n ];
		Number[][] l_ = new Number [ n ][ n ];

		// invert U
		for ( int j=0; j<n; j++ ) {
			u_[j][j] = scaEngine.dividedBy(scaEngine.number1(),lu[j][j]);
			for ( int i=j+1; i<n; i++ ) {
				u_[i][j] = scaEngine.number0();
				for ( int k=j; k<i; k++ )
					u_[i][j] = scaEngine.add(u_[i][j],scaEngine.multiply(lu[i][k],u_[k][j]));
				u_[i][j] = scaEngine.multiply(scaEngine.negate(lu[i][i]),u_[i][j]);
			}
		}

		// invert L
		for ( int i=0; i<n; i++ ) {
			l_[i][i] = scaEngine.number1();
			for ( int j=i-1; j>=0; j-- ) {
				l_[i][j] = scaEngine.number0();
				for ( int k=j+1; k<=i; k++ )
					l_[i][j] = scaEngine.add(l_[i][j],scaEngine.multiply(l_[i][k],lu[k][j]));
				l_[i][j] = scaEngine.negate(l_[i][j]);
			}
		}

		return matrixMultiply(u_, l_);
	}


	// numerical mathods
	// m = l * u
	protected Number[][] getLU( Number[][] m ) {
		int d = m.length;
		// M = L * U
		Number[][] lu = new Number [ d ][ d ];
		for ( int i=0; i<d; i++ ) for ( int j=0; j<d; j++ ) {
			if ( i > j ) { // L[i][j]
				lu[i][j] = m[i][j];
				for ( int k=0; k<j; k++ )
					lu[i][j] = scaEngine.subtract(lu[i][j],scaEngine.multiply(lu[i][k],lu[k][j]));
				lu[i][j] = scaEngine.dividedBy(lu[i][j],lu[j][j]);
			} else { // U[i][j]
				lu[i][j] = m[i][j];
				for ( int k=0; k<i; k++ )
					lu[i][j] = scaEngine.subtract(lu[i][j],scaEngine.multiply(lu[i][k],lu[k][j]));
			}
		}

		return lu;
	}

	// m * x = b
	protected Number[] solveByLU( Number[][] m, Number[] b ) {
		int d = m.length;
		// M = L * U
		Number[][] lu = clone(m);
		for ( int i=0; i<d; i++ ) for ( int j=0; j<d; j++ ) {
			if ( i > j ) { // L[i][j]
				for ( int k=0; k<j; k++ )
					lu[i][j] = scaEngine.subtract(lu[i][j],scaEngine.multiply(lu[i][k],lu[k][j]));
				lu[i][j] = scaEngine.dividedBy(lu[i][j],lu[j][j]);
			} else { // U[i][j]
				for ( int k=0; k<i; k++ )
					lu[i][j] = scaEngine.subtract(lu[i][j],scaEngine.multiply(lu[i][k],lu[k][j]));
			}
		}

		// L * y = b; U * x = y
		Number[] x = vecEngine.clone(b);
		for ( int i=0; i<d; i++ ) {
			for ( int k=0; k<i; k++ )
				x[i] = scaEngine.subtract(x[i],scaEngine.multiply(lu[i][k],x[k]));
		}
		for ( int i=d-1; i>=0; i-- ) {
			for ( int k=i+1; k<d; k++ )
				x[i] = scaEngine.subtract(x[i],scaEngine.multiply(lu[i][k],x[k]));
			x[i] = scaEngine.dividedBy(x[i],lu[i][i]);
		}

		return x;
	}

	// mat -> tri
	protected Number[][] solveByGauss( Number[][] mat ) {
		int m = mat.length;
		int n = mat[0].length;
		Number[][] result = clone(mat);

		// down
		int i = 0;
		int j = 0;
		while ( ( i < m )&&( j < n ) ) {
			// pivot
			int maxi = i;
			for ( int i_=i+1; i_<m; i_++ ) {
				if ( scaEngine.greaterThan(scaEngine.abs(result[i_][j]), scaEngine.abs(result[maxi][j])) )
					maxi = i_;
			}

			if ( scaEngine.greaterThan(scaEngine.abs(result[maxi][j]), scaEngine.number0()) ) {

				{ // swap
					Number[] tmp = result[i];
					result[i] = result[maxi];
					result[maxi] = tmp;
				}

				// normalize
				for ( int j_=j+1; j_<n; j_++ )
					result[i][j_] = scaEngine.dividedBy(result[i][j_],result[i][j]);
				result[i][j] = scaEngine.number1();

				// eliminate
				for ( int i_=i+1; i_<m; i_++ ) {
					for ( int j_=j+1; j_<n; j_++ )
						result[i_][j_] = scaEngine.subtract(result[i_][j_],scaEngine.multiply(result[i][j_],result[i_][j]));
					result[i_][j] = scaEngine.number0();
				}

				i = i + 1;

			} else {
				for ( int k=i; k<m; k++ )
					result[k][j] = scaEngine.number0();
			}
			j = j + 1;
		}

		// up
		i = m-1;
		while ( i > 0 ) {
			// pivot
			for ( j=0; j<n; j++ )
				if ( scaEngine.greaterThan(result[i][j], scaEngine.number0()) ) {

					// eliminate
					for ( int i_=0; i_<i; i_++ ) {
						for ( int j_=j+1; j_<n; j_++ )
							result[i_][j_] = scaEngine.subtract(result[i_][j_],scaEngine.multiply(result[i][j_],result[i_][j]));
						result[i_][j] = scaEngine.number0();
					}

					break;
				}
			i = i - 1;
		}
		return result;
	}
}
