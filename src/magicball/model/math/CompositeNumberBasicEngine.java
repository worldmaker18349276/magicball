package magicball.model.math;

import magicball.model.*;


public class CompositeNumberBasicEngine extends DefaultCompositeEngine<Num> implements NumberBasicEngine
{
	@Override
	public Num createNumberByDouble( double n ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof ScalarBasic.Creator ) {

			try {
				return ((ScalarBasic.Creator)engine).createNumberByDouble(n);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num createZero() {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof ScalarBasic.Creator ) {

			try {
				return ((ScalarBasic.Creator)engine).createZero();
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num createOne() {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof ScalarBasic.Creator ) {

			try {
				return ((ScalarBasic.Creator)engine).createOne();
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num createPi() {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof ScalarBasic.Creator ) {

			try {
				return ((ScalarBasic.Creator)engine).createPi();
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num createE() {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof ScalarBasic.Creator ) {

			try {
				return ((ScalarBasic.Creator)engine).createE();
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public double getDoubleValueOf( Num n ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof ScalarBasic.Attribute ) {

			try {
				return ((ScalarBasic.Attribute)engine).getDoubleValueOf(n);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public Num negate( Num n ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof ScalarBasic.Operator ) {

			try {
				return ((ScalarBasic.Operator)engine).negate(n);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num plus( Num n1, Num n2 ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof ScalarBasic.Operator ) {

			try {
				return ((ScalarBasic.Operator)engine).plus(n1,n2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num plus( Num... ns ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof ScalarBasic.Operator ) {

			try {
				return ((ScalarBasic.Operator)engine).plus(ns);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num minus( Num n1, Num n2 ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof ScalarBasic.Operator ) {

			try {
				return ((ScalarBasic.Operator)engine).minus(n1,n2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num times( Num n1, Num n2 ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof ScalarBasic.Operator ) {

			try {
				return ((ScalarBasic.Operator)engine).times(n1,n2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num times( Num... ns ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof ScalarBasic.Operator ) {

			try {
				return ((ScalarBasic.Operator)engine).times(ns);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num over( Num n1, Num n2 ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof ScalarBasic.Operator ) {

			try {
				return ((ScalarBasic.Operator)engine).over(n1,n2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num pow( Num n1, int n2 ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof ScalarBasic.Operator ) {

			try {
				return ((ScalarBasic.Operator)engine).pow(n1,n2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num pow( Num n1, Num n2 ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof ScalarBasic.Operator ) {

			try {
				return ((ScalarBasic.Operator)engine).pow(n1,n2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num sqrt( Num n ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof ScalarBasic.Operator ) {

			try {
				return ((ScalarBasic.Operator)engine).sqrt(n);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num abs( Num n ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof ScalarBasic.Operator ) {

			try {
				return ((ScalarBasic.Operator)engine).abs(n);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num floor( Num n ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof ScalarBasic.Operator ) {

			try {
				return ((ScalarBasic.Operator)engine).floor(n);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num ceil( Num n ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof ScalarBasic.Operator ) {

			try {
				return ((ScalarBasic.Operator)engine).ceil(n);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num max( Num n1, Num n2 ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof ScalarBasic.Operator ) {

			try {
				return ((ScalarBasic.Operator)engine).max(n1,n2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num max( Num... ns ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof ScalarBasic.Operator ) {

			try {
				return ((ScalarBasic.Operator)engine).max(ns);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num min( Num n1, Num n2 ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof ScalarBasic.Operator ) {

			try {
				return ((ScalarBasic.Operator)engine).min(n1,n2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num min( Num... ns ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof ScalarBasic.Operator ) {

			try {
				return ((ScalarBasic.Operator)engine).min(ns);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num exp( Num n ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof ScalarBasic.Operator ) {

			try {
				return ((ScalarBasic.Operator)engine).exp(n);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num ln( Num n ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof ScalarBasic.Operator ) {

			try {
				return ((ScalarBasic.Operator)engine).ln(n);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num sin( Num n ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof ScalarBasic.Operator ) {

			try {
				return ((ScalarBasic.Operator)engine).sin(n);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num cos( Num n ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof ScalarBasic.Operator ) {

			try {
				return ((ScalarBasic.Operator)engine).cos(n);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num tan( Num n ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof ScalarBasic.Operator ) {

			try {
				return ((ScalarBasic.Operator)engine).tan(n);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num asin( Num n ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof ScalarBasic.Operator ) {

			try {
				return ((ScalarBasic.Operator)engine).asin(n);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num acos( Num n ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof ScalarBasic.Operator ) {

			try {
				return ((ScalarBasic.Operator)engine).acos(n);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num atan( Num n ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof ScalarBasic.Operator ) {

			try {
				return ((ScalarBasic.Operator)engine).atan(n);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public boolean equals( Num n1, Num n2 ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof ScalarBasic.Predicate ) {

			try {
				return ((ScalarBasic.Predicate)engine).equals(n1,n2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public boolean isGreaterThan( Num n1, Num n2 ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof ScalarBasic.Predicate ) {

			try {
				return ((ScalarBasic.Predicate)engine).isGreaterThan(n1,n2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public boolean isLessThan( Num n1, Num n2 ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof ScalarBasic.Predicate ) {

			try {
				return ((ScalarBasic.Predicate)engine).isLessThan(n1,n2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public boolean isZero( Num n ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof ScalarBasic.Predicate ) {

			try {
				return ((ScalarBasic.Predicate)engine).isZero(n);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public boolean isOne( Num n ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof ScalarBasic.Predicate ) {

			try {
				return ((ScalarBasic.Predicate)engine).isOne(n);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}


	// vector ( Num[] )
	@Override
	public Num[] createVectorByDoubles( double... ns ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof VectorBasic.Creator ) {

			try {
				return ((VectorBasic.Creator)engine).createVectorByDoubles(ns);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num[] createZeroVectorWithDim( int d ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof VectorBasic.Creator ) {

			try {
				return ((VectorBasic.Creator)engine).createZeroVectorWithDim(d);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public double[] getDoubleValueOf( Num[] v ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof VectorBasic.Attribute ) {

			try {
				return ((VectorBasic.Attribute)engine).getDoubleValueOf(v);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public Num[] clone( Num[] v ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof VectorBasic.Operator ) {

			try {
				return ((VectorBasic.Operator)engine).clone(v);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num[] subvectorOf( Num[] v, int i1, int i2 ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof VectorBasic.Operator ) {

			try {
				return ((VectorBasic.Operator)engine).subvectorOf(v,i1,i2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num[] augmentsWith( Num[] v, Num... ns ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof VectorBasic.Operator ) {

			try {
				return ((VectorBasic.Operator)engine).augmentsWith(v,ns);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public Num[] negate( Num[] v ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof VectorBasic.Operator ) {

			try {
				return ((VectorBasic.Operator)engine).negate(v);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num[] plus( Num[] v1, Num[] v2 ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof VectorBasic.Operator ) {

			try {
				return ((VectorBasic.Operator)engine).plus(v1,v2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num[] plus( Num[]... vs ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof VectorBasic.Operator ) {

			try {
				return ((VectorBasic.Operator)engine).plus(vs);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num[] minus( Num[] v1, Num[] v2 ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof VectorBasic.Operator ) {

			try {
				return ((VectorBasic.Operator)engine).minus(v1,v2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num[] times( Num[] v1, Num n2 ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof VectorBasic.Operator ) {

			try {
				return ((VectorBasic.Operator)engine).times(v1,n2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num[] over( Num[] v1, Num n2 ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof VectorBasic.Operator ) {

			try {
				return ((VectorBasic.Operator)engine).over(v1,n2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num norm( Num[] v ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof VectorBasic.Operator ) {

			try {
				return ((VectorBasic.Operator)engine).norm(v);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num[] normalize( Num[] v ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof VectorBasic.Operator ) {

			try {
				return ((VectorBasic.Operator)engine).normalize(v);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num dotProduct( Num[] v1, Num[] v2 ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof VectorBasic.Operator ) {

			try {
				return ((VectorBasic.Operator)engine).dotProduct(v1,v2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num[] crossProduct( Num[] v1, Num[] v2 ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof VectorBasic.Operator ) {

			try {
				return ((VectorBasic.Operator)engine).crossProduct(v1,v2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public boolean equals( Num[] v1, Num[] v2 ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof VectorBasic.Predicate ) {

			try {
				return ((VectorBasic.Predicate)engine).equals(v1,v2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public boolean isZeroVector( Num[] v ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof VectorBasic.Predicate ) {

			try {
				return ((VectorBasic.Predicate)engine).isZeroVector(v);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}


	// matrix ( Num[][] )
	@Override
	public Num[][] createMatrixByDoubles( double[][] ns ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof MatrixBasic.Creator ) {

			try {
				return ((MatrixBasic.Creator)engine).createMatrixByDoubles(ns);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num[][] createZeroMatrixWithDim( int d1, int d2 ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof MatrixBasic.Creator ) {

			try {
				return ((MatrixBasic.Creator)engine).createZeroMatrixWithDim(d1,d2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num[][] createIdentityMatrixWithDim( int d ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof MatrixBasic.Creator ) {

			try {
				return ((MatrixBasic.Creator)engine).createIdentityMatrixWithDim(d);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public double[][] getDoubleValueOf( Num[][] m ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof MatrixBasic.Attribute ) {

			try {
				return ((MatrixBasic.Attribute)engine).getDoubleValueOf(m);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public Num[][] clone( Num[][] m ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof MatrixBasic.Operator ) {

			try {
				return ((MatrixBasic.Operator)engine).clone(m);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num[][] colVectorOf( Num[] v ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof MatrixBasic.Operator ) {

			try {
				return ((MatrixBasic.Operator)engine).colVectorOf(v);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num[][] rowVectorOf( Num[] v ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof MatrixBasic.Operator ) {

			try {
				return ((MatrixBasic.Operator)engine).rowVectorOf(v);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num[][] submatrixOf( Num[][] m, int i1, int i2, int j1, int j2 ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof MatrixBasic.Operator ) {

			try {
				return ((MatrixBasic.Operator)engine).submatrixOf(m,i1,i2,j1,j2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num[][] augmentsColumnWith( Num[][] m1, Num[][] m2 ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof MatrixBasic.Operator ) {

			try {
				return ((MatrixBasic.Operator)engine).augmentsColumnWith(m1,m2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num[][] augmentsRowWith( Num[][] m1, Num[][] m2 ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof MatrixBasic.Operator ) {

			try {
				return ((MatrixBasic.Operator)engine).augmentsRowWith(m1,m2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public Num[][] negate( Num[][] m ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof MatrixBasic.Operator ) {

			try {
				return ((MatrixBasic.Operator)engine).negate(m);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num[][] plus( Num[][] m1, Num[][] m2 ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof MatrixBasic.Operator ) {

			try {
				return ((MatrixBasic.Operator)engine).plus(m1,m2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num[][] plus( Num[][]... ms ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof MatrixBasic.Operator ) {

			try {
				return ((MatrixBasic.Operator)engine).plus(ms);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num[][] minus( Num[][] m1, Num[][] m2 ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof MatrixBasic.Operator ) {

			try {
				return ((MatrixBasic.Operator)engine).minus(m1,m2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num[][] times( Num[][] m1, Num n2 ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof MatrixBasic.Operator ) {

			try {
				return ((MatrixBasic.Operator)engine).times(m1,n2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num[][] over( Num[][] m1, Num n2 ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof MatrixBasic.Operator ) {

			try {
				return ((MatrixBasic.Operator)engine).over(m1,n2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num[][] transpose( Num[][] m ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof MatrixBasic.Operator ) {

			try {
				return ((MatrixBasic.Operator)engine).transpose(m);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num[][] matrixMultiply( Num[][] m1, Num[][] m2 ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof MatrixBasic.Operator ) {

			try {
				return ((MatrixBasic.Operator)engine).matrixMultiply(m1,m2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num[][] matrixMultiply( Num[][]... ms ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof MatrixBasic.Operator ) {

			try {
				return ((MatrixBasic.Operator)engine).matrixMultiply(ms);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num[][] pow( Num[][] m, int exp ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof MatrixBasic.Operator ) {

			try {
				return ((MatrixBasic.Operator)engine).pow(m,exp);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num[] matrixMultiply( Num[][] m1, Num[] v2 ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof MatrixBasic.Operator ) {

			try {
				return ((MatrixBasic.Operator)engine).matrixMultiply(m1,v2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num[] matrixMultiply( Num[] v1, Num[][] m2 ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof MatrixBasic.Operator ) {

			try {
				return ((MatrixBasic.Operator)engine).matrixMultiply(v1,m2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num trace( Num[][] m1 ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof MatrixBasic.Operator ) {

			try {
				return ((MatrixBasic.Operator)engine).trace(m1);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num determinant( Num[][] m ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof MatrixBasic.Operator ) {

			try {
				return ((MatrixBasic.Operator)engine).determinant(m);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public Num[][] invert( Num[][] m ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof MatrixBasic.Operator ) {

			try {
				return ((MatrixBasic.Operator)engine).invert(m);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public boolean equals( Num[][] m1, Num[][] m2 ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof MatrixBasic.Predicate ) {

			try {
				return ((MatrixBasic.Predicate)engine).equals(m1,m2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public boolean isZeroMatrix( Num[][] m ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof MatrixBasic.Predicate ) {

			try {
				return ((MatrixBasic.Predicate)engine).isZeroMatrix(m);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
	@Override
	public boolean isIdentityMatrix( Num[][] m ) {
		for ( Engine<? extends Num> engine : engines ) if ( engine instanceof MatrixBasic.Predicate ) {

			try {
				return ((MatrixBasic.Predicate)engine).isIdentityMatrix(m);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
}
