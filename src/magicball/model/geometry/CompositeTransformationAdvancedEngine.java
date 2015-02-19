package magicball.model.geometry;

import magicball.model.math.Function;
import magicball.model.*;


public class CompositeTransformationAdvancedEngine extends DefaultCompositeEngine<Transformation> implements TransformationAdvancedEngine
{
	// creater
	public Transformation createTransformationByFunction( Function<Number[],Number[]> func ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof TransformationBasicCreator ) {

			try {
				return ((TransformationBasicCreator)engine).createTransformationByFunction(func);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Transformation createIdentityTransformation() {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof TransformationBasicCreator ) {

			try {
				return ((TransformationBasicCreator)engine).createIdentityTransformation();
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Transformation createAffineTransformationByAugmentedMatrix( Number[][] mat ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationCreator ) {

			try {
				return ((AffineTransformationCreator)engine).createAffineTransformationByAugmentedMatrix(mat);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Transformation createAffineTransformationByMatrixAndVector( Number[][] mat, Number[] vec ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationCreator ) {

			try {
				return ((AffineTransformationCreator)engine).createAffineTransformationByMatrixAndVector(mat,vec);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Transformation createLinearTransformationByMatrix( Number[][] mat ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationCreator ) {

			try {
				return ((AffineTransformationCreator)engine).createLinearTransformationByMatrix(mat);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Transformation createRotationByVector( Number[] rvec ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationCreator ) {

			try {
				return ((AffineTransformationCreator)engine).createRotationByVector(rvec);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Transformation createReflectionByVector( Number[] fvec ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationCreator ) {

			try {
				return ((AffineTransformationCreator)engine).createReflectionByVector(fvec);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Transformation createTranslationByVector( Number[] sh ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationCreator ) {

			try {
				return ((AffineTransformationCreator)engine).createTranslationByVector(sh);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Transformation createScalingByFactor( Number factor ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationCreator ) {

			try {
				return ((AffineTransformationCreator)engine).createScalingByFactor(factor);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Transformation createShearingByOffsets( Number a, Number b ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationCreator ) {

			try {
				return ((AffineTransformationCreator)engine).createShearingByOffsets(a,b);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}



	// attribute
	public Number[] applies( Transformation trans, Number[] point ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof TransformationBasicAttribute ) {

			try {
				return ((TransformationBasicAttribute)engine).applies(trans,point);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Function<Number[],Number[]> getTransformationFunction( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof TransformationBasicAttribute ) {

			try {
				return ((TransformationBasicAttribute)engine).getTransformationFunction(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number[][] getTransformationMatrix( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAttribute ) {

			try {
				return ((AffineTransformationAttribute)engine).getTransformationMatrix(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number[] getRotationVector( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAttribute ) {

			try {
				return ((AffineTransformationAttribute)engine).getRotationVector(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number[] getReflectionVector( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAttribute ) {

			try {
				return ((AffineTransformationAttribute)engine).getReflectionVector(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number[] getTranslationVector( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAttribute ) {

			try {
				return ((AffineTransformationAttribute)engine).getTranslationVector(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}


	// operator
	public Transformation compose( Transformation... trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof TransformationBasicOperator ) {

			try {
				return ((TransformationBasicOperator)engine).compose(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Transformation pow( Transformation trans, int exp ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof TransformationBasicOperator ) {

			try {
				return ((TransformationBasicOperator)engine).pow(trans,exp);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Transformation dividedBy( Transformation trans, Number divisor ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof TransformationBasicOperator ) {

			try {
				return ((TransformationBasicOperator)engine).dividedBy(trans,divisor);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Transformation invert( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof TransformationBasicOperator ) {

			try {
				return ((TransformationBasicOperator)engine).invert(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Transformation transformsBy( Transformation t, Transformation p ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof TransformationBasicOperator ) {

			try {
				return ((TransformationBasicOperator)engine).transformsBy(t,p);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}



	// predicate
	public boolean isIdentity( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof TransformationBasicPredicate ) {

			try {
				return ((TransformationBasicPredicate)engine).isIdentity(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public boolean equals( Transformation trans1, Transformation trans2 ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof TransformationBasicPredicate ) {

			try {
				return ((TransformationBasicPredicate)engine).equals(trans1,trans2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public boolean isAffine( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationPredicate ) {

			try {
				return ((AffineTransformationPredicate)engine).isAffine(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public boolean isLinear( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationPredicate ) {

			try {
				return ((AffineTransformationPredicate)engine).isLinear(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public boolean isSimilar( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationPredicate ) {

			try {
				return ((AffineTransformationPredicate)engine).isSimilar(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public boolean isIsometric( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationPredicate ) {

			try {
				return ((AffineTransformationPredicate)engine).isIsometric(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public boolean isRigid( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationPredicate ) {

			try {
				return ((AffineTransformationPredicate)engine).isRigid(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public boolean isTranslation( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationPredicate ) {

			try {
				return ((AffineTransformationPredicate)engine).isTranslation(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
}
