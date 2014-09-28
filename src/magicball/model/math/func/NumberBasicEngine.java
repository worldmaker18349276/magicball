package magicball.model.math.func;

import magicball.model.*;
import magicball.model.math.*;


public class NumberBasicEngine implements NumberEngine
{
	protected double epsilon;

	public NumberBasicEngine( double eps ) {
		this.epsilon = eps;
	}

	public NumberBasicEngine clone() {
		return new NumberBasicEngine(this.epsilon);
	}

	public double eps() {
		return this.epsilon;
	}

	// scalar ( Number )
	public Number number( double n ) {
		return (Double) n;
	}

	public Number number0() {
		return number(0.0);
	}

	public Number number1() {
		return number(1.0);
	}

	public double doubleValue( Number n ) {
		return n.doubleValue();
	}

	public boolean equals( Number n1, double n2 ) {
		return Math.abs(doubleValue(n1)-n2) < this.epsilon;
	}

	public boolean equals( Number n1, Number n2 ) {
		return Math.abs(doubleValue(n1)-doubleValue(n2)) < this.epsilon;
	}

	public boolean greaterThan( Number n1, Number n2 ) {
		return (doubleValue(n1)-doubleValue(n2)) > this.epsilon;
	}

	public boolean lessThan( Number n1, Number n2 ) {
		return (doubleValue(n1)-doubleValue(n2)) < -this.epsilon;
	}

	public Number negate( Number n ) {
		return number(-doubleValue(n));
	}

	public Number add( Number n1, Number n2 ) {
		return number( doubleValue(n1) + doubleValue(n2) );
	}

	public Number add( Number... ns ) {
		Number result = ns[0];
		for ( int i=1; i<ns.length; i++ )
			result = add(result,ns[i]);
		return result;
	}

	public Number subtract( Number n1, Number n2 ) {
		return number( doubleValue(n1) - doubleValue(n2) );
	}

	public Number multiply( Number n1, Number n2 ) {
		return number( doubleValue(n1) * doubleValue(n2) );
	}

	public Number multiply( Number... ns ) {
		Number result = ns[0];
		for ( int i=1; i<ns.length; i++ )
			result = multiply(result,ns[i]);
		return result;
	}

	public Number dividedBy( Number n1, Number n2 ) {
		return number( doubleValue(n1) / doubleValue(n2) );
	}

	public Number pow( Number n1, int n2 ) {
		return number( Math.pow(doubleValue(n1),n2) );
	}

	public Number pow( Number n1, Number n2 ) {
		return number( Math.pow(doubleValue(n1),doubleValue(n2)) );
	}

	public Number sqrt( Number n ) {
		return number(Math.sqrt(doubleValue(n)));
	}



	// vector ( Number[] )
	public Number[] vector( double... ns ) {
		Number[] vec = new Double [ ns.length ];
		for ( int i=0; i<vec.length; i++ )
			vec[i] = number(ns[i]);
		return vec;
	}

	public Number[] vector0( int d ) {
		Number[] vec = new Double [ d ];
		for ( int i=0; i<vec.length; i++ )
			vec[i] = number0();
		return vec;
	}

	public double[] doubleValue( Number[] v ) {
		double[] result = new double [ v.length ];
		for ( int i=0; i<result.length; i++ )
			result[i] = doubleValue(v[i]);
		return result;
	}

	public boolean equals( Number[] v1, double[] v2 ) {
		for ( int i=0; i<v1.length; i++ )
		 if ( !equals(v1[i],v2[i]) )
		 	return false;
		 return true;
	}

	public boolean equals( Number[] v1, Number[] v2 ) {
		for ( int i=0; i<v1.length; i++ )
		 if ( !equals(v1[i],v2[i]) )
		 	return false;
		 return true;
	}

	public Number[] negate( Number[] v ) {
		Number[] result = new Number [ v.length ];
		for ( int i=0; i<result.length; i++ )
			result[i] = negate(v[i]);
		return result;
	}

	public Number[] add( Number[] v1, Number[] v2 ) {
		Number[] result = new Double [ v1.length ];
		for ( int i=0; i<result.length; i++ )
			result[i] = add(v1[i],v2[i]);
		return result;
	}

	public Number[] add( Number[]... vs ) {
		Number [] result = vs[0];
		for ( int i=1; i<vs.length; i++ )
			result = add(result,vs[i]);
		return result;
	}

	public Number[] subtract( Number[] v1, Number[] v2 ) {
		Number[] result = new Double [ v1.length ];
		for ( int i=0; i<result.length; i++ )
			result[i] = subtract(v1[i],v2[i]);
		return result;
	}

	public Number[] multiply( Number[] v1, Number n2 ) {
		Number[] result = new Double [ v1.length ];
		for ( int i=0; i<result.length; i++ )
			result[i] = multiply(v1[i],n2);
		return result;
	}

	public Number[] dividedBy( Number[] v1, Number n2 ) {
		Number[] result = new Double [ v1.length ];
		for ( int i=0; i<result.length; i++ )
			result[i] = dividedBy(v1[i],n2);
		return result;
	}

	public Number norm( Number[] v ) {
		Number result = number0();
		for ( int i=0; i<v.length; i++ )
			result = add(result,pow(v[i],2));
		return sqrt(result);
	}

	public Number[] normalize( Number[] v ) {
		return dividedBy(v,norm(v));
	}

	public Number dotProduct( Number[] v1, Number[] v2 ) {
		Number result = number0();
			for ( int i=0; i<v1.length; i++ )
				result = add(result,multiply(v1[i],v2[i]));
			return result;
	}

	public Number[] crossProduct( Number[] v1, Number[] v2 ) {
		Number [] result = new Number [ 3 ];
		result[0] = subtract(multiply(v1[1],v2[2]),multiply(v1[2],v2[1]));
		result[1] = subtract(multiply(v1[2],v2[0]),multiply(v1[0],v2[2]));
		result[2] = subtract(multiply(v1[0],v2[1]),multiply(v1[1],v2[0]));
		return result;
	}


	// matrix ( Number[][] )
	public Number[][] matrix( double[][] ns ) {
		Number[][] mat = new Double [ ns.length ][];
		for ( int i=0; i<mat.length; i++ )
			mat[i] = vector(ns[i]);
		return mat;
	}

	public Number[][] matrix0( int d1, int d2 ) {
		Number[][] mat = new Double [ d1 ][];
		for ( int i=0; i<mat.length; i++ )
			mat[i] = vector0(d2);
		return mat;
	}

	public Number[][] matrix1( int d ) {
		Number[][] mat = matrix0(d,d);
		for ( int i=0; i<mat.length; i++ )
			mat[i][i] = number1();
		return mat;
	}

	public double[][] doubleValue( Number[][] m ) {
		double[][] result = new double [ m.length ][];
		for ( int i=0; i<result.length; i++ )
			result[i] = doubleValue(m[i]);
		return result;
	}

	public boolean equals( Number[][] m1, double[][] m2 ) {
		for ( int i=0; i<m1.length; i++ )
		 if ( !equals(m1[i],m2[i]) )
		 	return false;
		 return true;
	}

	public boolean equals( Number[][] m1, Number[][] m2 ) {
		for ( int i=0; i<m1.length; i++ )
		 if ( !equals(m1[i],m2[i]) )
		 	return false;
		 return true;
	}

	public Number[][] negate( Number[][] m ) {
		Number[][] result = new Number [ m.length ][];
		for ( int i=0; i<result.length; i++ )
			result[i] = negate(m[i]);
		return result;
	}

	public Number[][] add( Number[][] m1, Number[][] m2 ) {
		Number[][] result = new Double [ m1.length ][];
		for ( int i=0; i<result.length; i++ )
			result[i] = add(m1[i],m2[i]);
		return result;
	}

	public Number[][] add( Number[][]... ms ) {
		Number[][] result = ms[0];
		for ( int i=1; i<ms.length; i++ )
			result = add(result,ms[i]);
		return result;
	}

	public Number[][] subtract( Number[][] m1, Number[][] m2 ) {
		Number[][] result = new Double [ m1.length ][];
		for ( int i=0; i<result.length; i++ )
			result[i] = subtract(m1[i],m2[i]);
		return result;
	}

	public Number[][] multiply( Number[][] m1, Number n2 ) {
		Number[][] result = new Double [ m1.length ][];
		for ( int i=0; i<result.length; i++ )
			result[i] = multiply(m1[i],n2);
		return result;
	}

	public Number[][] dividedBy( Number[][] m1, Number n2 ) {
		Number[][] result = new Double [ m1.length ][];
		for ( int i=0; i<result.length; i++ )
			result[i] = dividedBy(m1[i],n2);
		return result;
	}

	public Number[][] transpose( Number[][] m1 ) {
		Number [][] result = new Number [ m1[0].length ][ m1.length ];
		for ( int i=0; i<result.length; i++ )
			for ( int j=0; j<result[i].length; j++ )
				result[i][j] = m1[j][i];
		return result;
	}

	public Number[][] matrixMultiply( Number[][] m1, Number[][] m2 ) {
		Number [][] result = new Number [ m1.length ][ m2[0].length ];
		for ( int i=0; i<result.length; i++ )
			for ( int j=0; j<result[i].length; j++ ) {
				result[i][j] = number0();
				for ( int k=0; k<m2.length; k++ )
					result[i][j] = add(result[i][j],multiply(m1[i][k],m2[k][j]));
			}
		return result;
	}

	public Number[][] matrixMultiply( Number[][]... ms ) {
		Number [][] result = ms[0];
		for ( int i=1; i<ms.length; i++ )
			result = matrixMultiply(result,ms[i]);
		return result;
	}

	public Number[][] pow( Number[][] m, int exp ) {
		Number [][] result = m;
		for ( int i=1; i<exp; i++ )
			result = matrixMultiply(result,m);
		return result;
	}

	public Number[] matrixMultiply( Number[][] m1, Number[] v2 ) {
		Number [] result = new Number [ m1.length ];
		for ( int i=0; i<result.length; i++ ) {
			result[i] = number0();
			for ( int k=0; k<m1[i].length; k++ )
				result[i] = add(result[i],multiply(m1[i][k],v2[k]));
		}
		return result;
	}

	public Number[] matrixMultiply( Number[] v1, Number[][] m2 ) {
		Number [] result = new Number [ m2[0].length ];
		for ( int j=0; j<result.length; j++ ) {
			result[j] = number0();
			for ( int k=0; k<m2.length; k++ )
				result[j] = add(result[j],multiply(v1[k],m2[k][j]));
		}
		return result;
	}

	public Number trace( Number[][] m1 ) {
		Number result = m1[0][0];
		for ( int i=1; i<m1.length; i++ )
			result = add(result,m1[i][i]);
		return result;
	}

	public Number determinant33( Number[][] m1 ) {
		Number result =          multiply(multiply( m1[0][0], m1[1][1]), m1[2][2] );
		result =      add(result,multiply(multiply( m1[0][1], m1[1][2]), m1[2][0] ));
		result =      add(result,multiply(multiply( m1[0][2], m1[1][0]), m1[2][1] ));
		result = subtract(result,multiply(multiply( m1[0][0], m1[1][2]), m1[2][1] ));
		result = subtract(result,multiply(multiply( m1[0][1], m1[1][0]), m1[2][2] ));
		result = subtract(result,multiply(multiply( m1[0][2], m1[1][1]), m1[2][0] ));
		return result;
	}

	public Number[][] invert33( Number[][] m ) {
		Number [][] result = new Number [ 3 ][ 3 ];
		result[0][0] = subtract( multiply(m[1][1],m[2][2]), multiply(m[1][2],m[2][1]) );
		result[1][1] = subtract( multiply(m[2][2],m[0][0]), multiply(m[2][0],m[0][2]) );
		result[2][2] = subtract( multiply(m[0][0],m[1][1]), multiply(m[0][1],m[1][0]) );
		result[0][2] = subtract( multiply(m[0][1],m[1][2]), multiply(m[0][2],m[1][1]) );
		result[2][0] = subtract( multiply(m[1][0],m[2][1]), multiply(m[1][1],m[2][0]) );
		result[0][1] = subtract( multiply(m[2][1],m[0][2]), multiply(m[2][2],m[0][1]) );
		result[1][2] = subtract( multiply(m[0][2],m[1][0]), multiply(m[0][0],m[1][2]) );
		result[1][0] = subtract( multiply(m[1][2],m[2][0]), multiply(m[1][0],m[2][2]) );
		result[2][1] = subtract( multiply(m[2][0],m[0][1]), multiply(m[2][1],m[0][0]) );
		result = dividedBy(result,determinant33(m));
		return result;
	}

	public Number determinant( Number[][] m ) {
		return trace(getLU(m));
	}

	public Number[][] invert( Number[][] m ) {
		double[][] lu = doubleValue(getLU(m));
		int n = lu.length;
		double[][] u_ = new double [ n ][ n ];
		double[][] l_ = new double [ n ][ n ];

		// invert U
		for ( int j=0; j<n; j++ ) {
			u_[j][j] = 1/lu[j][j];
			for ( int i=j+1; i<n; i++ ) {
				u_[i][j] = 0;
				for ( int k=j; k<i; k++ )
					u_[i][j] = u_[i][j] + lu[i][k] * u_[k][j];
				u_[i][j] = -lu[i][i] * u_[i][j];
			}
		}

		// invert L
		for ( int i=0; i<n; i++ ) {
			l_[i][i] = 1;
			for ( int j=i-1; j>=0; j-- ) {
				l_[i][j] = 0;
				for ( int k=j+1; k<=i; k++ )
					l_[i][j] = l_[i][j] + l_[i][k] * lu[k][j];
				l_[i][j] = -l_[i][j];
			}
		}

		return matrixMultiply(matrix(u_),matrix(l_));
	}


	// numerical mathods
	// m = l * u
	protected Number[][] getLU( Number[][] m ) {
		int d = m.length;
		// M = L * U
		double[][] lu = new double [ d ][ d ];
		for ( int i=0; i<d; i++ ) for ( int j=0; j<d; j++ ) {
			if ( i > j ) { // L[i][j]
				lu[i][j] = doubleValue(m[i][j]);
				for ( int k=0; k<j; k++ )
					lu[i][j] = lu[i][j] - lu[i][k]*lu[k][j];
				lu[i][j] = lu[i][j]/lu[j][j];
			} else { // U[i][j]
				lu[i][j] = doubleValue(m[i][j]);
				for ( int k=0; k<i; k++ )
					lu[i][j] = lu[i][j] - lu[i][k]*lu[k][j];
			}
		}

		return matrix(lu);
	}

	// m * x = b
	protected Number[] solveByLU( Number[][] m, Number[] b ) {
		int d = m.length;
		// M = L * U
		double[][] lu = doubleValue(m);
		for ( int i=0; i<d; i++ ) for ( int j=0; j<d; j++ ) {
			if ( i > j ) { // L[i][j]
				for ( int k=0; k<j; k++ )
					lu[i][j] = lu[i][j] - lu[i][k]*lu[k][j];
				lu[i][j] = lu[i][j]/lu[j][j];
			} else { // U[i][j]
				for ( int k=0; k<i; k++ )
					lu[i][j] = lu[i][j] - lu[i][k]*lu[k][j];
			}
		}

		// L * y = b; U * x = y
		double[] x = doubleValue(b);
		for ( int i=0; i<d; i++ ) {
			for ( int k=0; k<i; k++ )
				x[i] = x[i] - lu[i][k]*x[k];
		}
		for ( int i=d-1; i>=0; i-- ) {
			for ( int k=i+1; k<d; k++ )
				x[i] = x[i] - lu[i][k]*x[k];
			x[i] = x[i]/lu[i][i];
		}

		return vector(x);
	}

	// mat -> tri
	protected Number[][] solveByGauss( Number[][] mat ) {
		int m = mat.length;
		int n = mat[0].length;
		double[][] result = doubleValue(mat);

		// down
		int i = 0;
		int j = 0;
		while ( ( i < m )&&( j < n ) ) {
			// pivot
			int maxi = i;
			for ( int i_=i+1; i_<m; i_++ ) {
				if ( Math.abs(result[i_][j]) > Math.abs(result[maxi][j]) )
					maxi = i_;
			}

			if ( Math.abs(result[maxi][j]) > epsilon ) {

				{ // swap
					double[] tmp = result[i];
					result[i] = result[maxi];
					result[maxi] = tmp;
				}

				// normalize
				for ( int j_=j+1; j_<n; j_++ )
					result[i][j_] = result[i][j_]/result[i][j];
				result[i][j] = 1.0;

				// eliminate
				for ( int i_=i+1; i_<m; i_++ ) {
					for ( int j_=j+1; j_<n; j_++ )
						result[i_][j_] = result[i_][j_] - result[i][j_]*result[i_][j];
					result[i_][j] = 0.0;
				}

				i = i + 1;

			} else {
				for ( int k=i; k<m; k++ )
					result[k][j] = 0.0;
			}
			j = j + 1;
		}

		// up
		i = m-1;
		while ( i > 0 ) {
			// pivot
			for ( j=0; j<n; j++ )
				if ( result[i][j] > epsilon ) {

					// eliminate
					for ( int i_=0; i_<i; i_++ ) {
						for ( int j_=j+1; j_<n; j_++ )
							result[i_][j_] = result[i_][j_] - result[i][j_]*result[i_][j];
						result[i_][j] = 0.0;
					}

					break;
				}
			i = i - 1;
		}
		return matrix(result);
	}
}
