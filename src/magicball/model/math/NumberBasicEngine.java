package magicball.model.math;


public class NumberBasicEngine
{
	protected double epsilon;

	public NumberBasicEngine( double eps ) {
		this.epsilon = eps;
	}

	public NumberBasicEngine clone() {
		return new NumberBasicEngine(this.epsilon);
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

	public boolean equals( Number n1, Number n2 ) {
		return Math.abs(n1.doubleValue()-n2.doubleValue()) < this.epsilon;
	}

	public boolean greaterThan( Number n1, Number n2 ) {
		return (n1.doubleValue()-n2.doubleValue()) > this.epsilon;
	}

	public boolean lessThan( Number n1, Number n2 ) {
		return (n1.doubleValue()-n2.doubleValue()) < -this.epsilon;
	}

	public Number add( Number n1, Number n2 ) {
		return (Double)( n1.doubleValue() + n2.doubleValue() );
	}

	public Number add( Number... ns ) {
		Number result = ns[0];
		for ( int i=1; i<ns.length; i++ )
			result = add(result,ns[i]);
		return result;
	}

	public Number subtract( Number n1, Number n2 ) {
		return (Double)( n1.doubleValue() - n2.doubleValue() );
	}

	public Number multiply( Number n1, Number n2 ) {
		return (Double)( n1.doubleValue() * n2.doubleValue() );
	}

	public Number multiply( Number... ns ) {
		Number result = ns[0];
		for ( int i=1; i<ns.length; i++ )
			result = multiply(result,ns[i]);
		return result;
	}

	public Number dividedBy( Number n1, Number n2 ) {
		return (Double)( n1.doubleValue() / n2.doubleValue() );
	}

	public Number pow( Number n1, Number n2 ) {
		return (Double) Math.pow(n1.doubleValue(),n2.doubleValue());
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

	public boolean equals( Number[] v1, Number[] v2 ) {
		for ( int i=0; i<v1.length; i++ )
		 if ( equals(v1[i],v2[i]) )
		 	return false;
		 return true;
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
			result = add(result,pow(v[i],number(2)));
		return pow(result,number(1/2));
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

	public boolean equals( Number[][] m1, Number[][] m2 ) {
		for ( int i=0; i<m1.length; i++ )
		 if ( equals(m1[i],m2[i]) )
		 	return false;
		 return true;
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
				for ( int k=0; k<result[i].length; k++ )
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
			for ( int k=0; k<m1.length; k++ )
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
		result = subtract(result,multiply(multiply( m1[0][1], m1[1][2]), m1[2][0] ));
		result =      add(result,multiply(multiply( m1[0][2], m1[1][0]), m1[2][1] ));
		return result;
	}

	// numerical mathods
	// m * x = b
	public Number solveByLU( Number[][] m, Number[] b ) {
		int d = m.length;
		// M = L * U
		double[][] lu = new double [ d ][ d ];
		for ( int i=0; i<d; i++ ) for ( int j=0; j<d; j++ ) {
			if ( i > j ) { // L[i][j]
				lu[i][j] = m[i][j].doubleValue();
				for ( int k=0; k<j-1; k++ )
					lu[i][j] = lu[i][j] - lu[i][k]*lu[k][j];
				lu[i][j] = L[i][j]/lu[j][j];
			} else { // U[i][j]
				lu[i][j] = m[i][j].doubleValue();
				for ( int k=0; k<i-1; k++ )
					lu[i][j] = lu[i][j] - lu[i][k]*lu[k][j];
			}
		}

		// L * y = b; U * x = y
		double[] x = new double [ d ];
		for ( int i=0; i<d; i++ ) {
			x[i] = b[i];
			for ( int k=0; k<i-1; k++ )
				x[i] = x[i] - lu[i][k]*x[k];
		}
		for ( int i=d-1; i>=0; i-- ) {
			for ( int k=i+1; k<d; k++ )
				x[i] = x[i] - lu[i][k]*x[k];
			x[i] = x[i]/lu[i][i];
		}

		return vector(x);
	}

	public Number[] axisOfRotationMatrix( Number[][] m ) {
		return normalize(solveByLU(subtract(m,matrix1(3)),vector0(3)));
	}

	public Number angleOfRotationMatrix( Number[][] m ) {
		double tr = trace(m).doubleValue();
		return number(Math.acos((tr-1)/2));
	}

	public Number angleOfRotationMatrix( Number[][] m, Number[] axis ) {
		double tr = trace(m).doubleValue();
		double ang = Math.acos((tr-1)/2);
		if ( Math.abs(axis[2]) > 2*epsilon )
			ang = Math.copysign( ang, (axis[0]*axis[1]*(1-Math.cos(ang))-m[0][1])*axis[2] );
		else if ( Math.abs(axis[1]) > 2*epsilon )
			ang = Math.copysign( ang, (axis[0]*axis[2]*(1-Math.cos(ang))-m[2][0])*axis[1] );
		else
			ang = Math.copysign( ang, (axis[1]*axis[2]*(1-Math.cos(ang))-m[1][2])*axis[0] );
		return number(ang);
	}

	public Number[][] createRotationMatrix( Number[] axis, Number angle ) {
		double cos = Math.cos(angle);
		double sin = Math.sin(angle);
		double versin = 1-cos;

		Number [][] rot = new Number [ 3 ][ 3 ];
		rot[0][0] = cos + axis[0]^2 * versin;
		rot[1][1] = cos + axis[1]^2 * versin;
		rot[2][2] = cos + axis[2]^2 * versin;
		rot[0][1] = axis[0]*axis[1]*versin - axis[2]*sin;
		rot[1][0] = axis[0]*axis[1]*versin + axis[2]*sin;
		rot[1][2] = axis[1]*axis[2]*versin - axis[0]*sin;
		rot[2][1] = axis[1]*axis[2]*versin + axis[0]*sin;
		rot[2][0] = axis[2]*axis[0]*versin - axis[1]*sin;
		rot[0][2] = axis[2]*axis[0]*versin + axis[1]*sin;

		return rot;
	}
}
