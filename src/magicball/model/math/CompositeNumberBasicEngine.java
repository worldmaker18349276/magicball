package magicball.model.math;

import magicball.model.*;


public class CompositeNumberBasicEngine extends CompositeEngine<Number> implements NumberBasicEngine
{
	protected java.util.List<Engine<? extends Number>> engines;

	public CompositeNumberBasicEngine() {
		engines = new java.util.LinkedList<>();
	}

	public CompositeNumberBasicEngine( java.util.List<Engine<? extends Number>> eng ) {
		engines = eng;
	}

	public CompositeNumberBasicEngine clone() {
		return new CompositeNumberBasicEngine(new java.util.LinkedList<>(engines));
	}

	public void add( Engine<? extends Number> engine ) {
		engines.add(engine);
	}

	public void add( int index, Engine<? extends Number> engine ) {
		engines.add(index, engine);
	}


	// scalar ( Number )
	public Number number( double n ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof ScalarEngine ) {

			try {
				return ((ScalarEngine)engine).number(n);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number number0() {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof ScalarEngine ) {

			try {
				return ((ScalarEngine)engine).number0();
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number number1() {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof ScalarEngine ) {

			try {
				return ((ScalarEngine)engine).number1();
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number pi() {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof ScalarEngine ) {

			try {
				return ((ScalarEngine)engine).pi();
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number e() {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof ScalarEngine ) {

			try {
				return ((ScalarEngine)engine).e();
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public double doubleValue( Number n ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof ScalarEngine ) {

			try {
				return ((ScalarEngine)engine).doubleValue(n);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public boolean equals( Number n1, Number n2 ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof ScalarEngine ) {

			try {
				return ((ScalarEngine)engine).equals(n1,n2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public boolean greaterThan( Number n1, Number n2 ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof ScalarEngine ) {

			try {
				return ((ScalarEngine)engine).greaterThan(n1,n2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public boolean lessThan( Number n1, Number n2 ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof ScalarEngine ) {

			try {
				return ((ScalarEngine)engine).lessThan(n1,n2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number negate( Number n ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof ScalarEngine ) {

			try {
				return ((ScalarEngine)engine).negate(n);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number add( Number n1, Number n2 ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof ScalarEngine ) {

			try {
				return ((ScalarEngine)engine).add(n1,n2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number add( Number... ns ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof ScalarEngine ) {

			try {
				return ((ScalarEngine)engine).add(ns);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number subtract( Number n1, Number n2 ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof ScalarEngine ) {

			try {
				return ((ScalarEngine)engine).subtract(n1,n2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number multiply( Number n1, Number n2 ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof ScalarEngine ) {

			try {
				return ((ScalarEngine)engine).multiply(n1,n2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number multiply( Number... ns ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof ScalarEngine ) {

			try {
				return ((ScalarEngine)engine).multiply(ns);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number dividedBy( Number n1, Number n2 ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof ScalarEngine ) {

			try {
				return ((ScalarEngine)engine).dividedBy(n1,n2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number pow( Number n1, int n2 ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof ScalarEngine ) {

			try {
				return ((ScalarEngine)engine).pow(n1,n2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number pow( Number n1, Number n2 ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof ScalarEngine ) {

			try {
				return ((ScalarEngine)engine).pow(n1,n2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number sqrt( Number n ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof ScalarEngine ) {

			try {
				return ((ScalarEngine)engine).sqrt(n);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number abs( Number n ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof ScalarEngine ) {

			try {
				return ((ScalarEngine)engine).abs(n);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number floor( Number n ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof ScalarEngine ) {

			try {
				return ((ScalarEngine)engine).floor(n);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number ceil( Number n ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof ScalarEngine ) {

			try {
				return ((ScalarEngine)engine).ceil(n);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number max( Number n1, Number n2 ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof ScalarEngine ) {

			try {
				return ((ScalarEngine)engine).max(n1,n2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number min( Number n1, Number n2 ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof ScalarEngine ) {

			try {
				return ((ScalarEngine)engine).min(n1,n2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number exp( Number n ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof ScalarEngine ) {

			try {
				return ((ScalarEngine)engine).exp(n);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number ln( Number n ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof ScalarEngine ) {

			try {
				return ((ScalarEngine)engine).ln(n);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number sin( Number n ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof ScalarEngine ) {

			try {
				return ((ScalarEngine)engine).sin(n);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number cos( Number n ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof ScalarEngine ) {

			try {
				return ((ScalarEngine)engine).cos(n);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number tan( Number n ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof ScalarEngine ) {

			try {
				return ((ScalarEngine)engine).tan(n);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number asin( Number n ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof ScalarEngine ) {

			try {
				return ((ScalarEngine)engine).asin(n);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number acos( Number n ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof ScalarEngine ) {

			try {
				return ((ScalarEngine)engine).acos(n);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number atan( Number n ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof ScalarEngine ) {

			try {
				return ((ScalarEngine)engine).atan(n);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}


	// vector ( Number[] )
	public Number[] vector( double... ns ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof VectorEngine ) {

			try {
				return ((VectorEngine)engine).vector(ns);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number[] vector0( int d ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof VectorEngine ) {

			try {
				return ((VectorEngine)engine).vector0(d);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public double[] doubleValue( Number[] v ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof VectorEngine ) {

			try {
				return ((VectorEngine)engine).doubleValue(v);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}


	public boolean equals( Number[] v1, Number[] v2 ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof VectorEngine ) {

			try {
				return ((VectorEngine)engine).equals(v1,v2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number[] negate( Number[] v ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof VectorEngine ) {

			try {
				return ((VectorEngine)engine).negate(v);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number[] add( Number[] v1, Number[] v2 ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof VectorEngine ) {

			try {
				return ((VectorEngine)engine).add(v1,v2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number[] add( Number[]... vs ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof VectorEngine ) {

			try {
				return ((VectorEngine)engine).add(vs);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number[] subtract( Number[] v1, Number[] v2 ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof VectorEngine ) {

			try {
				return ((VectorEngine)engine).subtract(v1,v2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number[] multiply( Number[] v1, Number n2 ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof VectorEngine ) {

			try {
				return ((VectorEngine)engine).multiply(v1,n2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number[] dividedBy( Number[] v1, Number n2 ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof VectorEngine ) {

			try {
				return ((VectorEngine)engine).dividedBy(v1,n2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}


	public Number norm( Number[] v ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof VectorEngine ) {

			try {
				return ((VectorEngine)engine).norm(v);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number[] normalize( Number[] v ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof VectorEngine ) {

			try {
				return ((VectorEngine)engine).normalize(v);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}


	public Number dotProduct( Number[] v1, Number[] v2 ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof VectorEngine ) {

			try {
				return ((VectorEngine)engine).dotProduct(v1,v2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number[] crossProduct( Number[] v1, Number[] v2 ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof VectorEngine ) {

			try {
				return ((VectorEngine)engine).crossProduct(v1,v2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}



	// matrix ( Number[][] )
	public Number[][] matrix( double[][] ns ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof MatrixEngine ) {

			try {
				return ((MatrixEngine)engine).matrix(ns);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number[][] matrix0( int d1, int d2 ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof MatrixEngine ) {

			try {
				return ((MatrixEngine)engine).matrix0(d1,d2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number[][] matrix1( int d ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof MatrixEngine ) {

			try {
				return ((MatrixEngine)engine).matrix1(d);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public double[][] doubleValue( Number[][] m ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof MatrixEngine ) {

			try {
				return ((MatrixEngine)engine).doubleValue(m);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public boolean equals( Number[][] m1, Number[][] m2 ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof MatrixEngine ) {

			try {
				return ((MatrixEngine)engine).equals(m1,m2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number[][] negate( Number[][] m ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof MatrixEngine ) {

			try {
				return ((MatrixEngine)engine).negate(m);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number[][] add( Number[][] m1, Number[][] m2 ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof MatrixEngine ) {

			try {
				return ((MatrixEngine)engine).add(m1,m2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number[][] add( Number[][]... ms ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof MatrixEngine ) {

			try {
				return ((MatrixEngine)engine).add(ms);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number[][] subtract( Number[][] m1, Number[][] m2 ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof MatrixEngine ) {

			try {
				return ((MatrixEngine)engine).subtract(m1,m2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number[][] multiply( Number[][] m1, Number n2 ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof MatrixEngine ) {

			try {
				return ((MatrixEngine)engine).multiply(m1,n2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number[][] dividedBy( Number[][] m1, Number n2 ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof MatrixEngine ) {

			try {
				return ((MatrixEngine)engine).dividedBy(m1,n2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number[][] transpose( Number[][] m1 ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof MatrixEngine ) {

			try {
				return ((MatrixEngine)engine).transpose(m1);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number[][] matrixMultiply( Number[][] m1, Number[][] m2 ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof MatrixEngine ) {

			try {
				return ((MatrixEngine)engine).matrixMultiply(m1,m2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number[][] matrixMultiply( Number[][]... ms ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof MatrixEngine ) {

			try {
				return ((MatrixEngine)engine).matrixMultiply(ms);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number[][] pow( Number[][] m, int exp ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof MatrixEngine ) {

			try {
				return ((MatrixEngine)engine).pow(m,exp);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number[] matrixMultiply( Number[][] m1, Number[] v2 ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof MatrixEngine ) {

			try {
				return ((MatrixEngine)engine).matrixMultiply(m1,v2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number[] matrixMultiply( Number[] v1, Number[][] m2 ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof MatrixEngine ) {

			try {
				return ((MatrixEngine)engine).matrixMultiply(v1,m2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number trace( Number[][] m1 ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof MatrixEngine ) {

			try {
				return ((MatrixEngine)engine).trace(m1);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number determinant33( Number[][] m1 ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof MatrixEngine ) {

			try {
				return ((MatrixEngine)engine).determinant33(m1);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number[][] invert33( Number[][] m ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof MatrixEngine ) {

			try {
				return ((MatrixEngine)engine).invert33(m);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number determinant( Number[][] m1 ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof MatrixEngine ) {

			try {
				return ((MatrixEngine)engine).determinant(m1);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number[][] invert( Number[][] m ) {
		for ( Engine<? extends Number> engine : engines ) if ( engine instanceof MatrixEngine ) {

			try {
				return ((MatrixEngine)engine).invert(m);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

}
