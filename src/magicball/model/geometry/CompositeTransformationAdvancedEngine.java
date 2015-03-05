package magicball.model.geometry;

import magicball.model.math.Func;
import magicball.model.*;


public class CompositeTransformationAdvancedEngine extends DefaultCompositeEngine<Transformation> implements TransformationBasicEngine
{
	// creater
	public Transformation createTransformationByFunction( Function<Number[],Number[]> func ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof ArbitraryTransformationBasicProperty.Creator ) {

			try {
				return ((ArbitraryTransformationBasicProperty.Creator)engine).createTransformationByFunction(func);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Transformation createIdentityTransformation() {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof ArbitraryTransformationBasicProperty.Creator ) {

			try {
				return ((ArbitraryTransformationBasicProperty.Creator)engine).createIdentityTransformation();
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Transformation createAffineTransformationByAugmentedMatrix( Number[][] mat ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvancedProperty.Creator ) {

			try {
				return ((AffineTransformationAdvancedProperty.Creator)engine).createAffineTransformationByAugmentedMatrix(mat);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Transformation createAffineTransformationByMatrixAndVector( Number[][] mat, Number[] vec ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvancedProperty.Creator ) {

			try {
				return ((AffineTransformationAdvancedProperty.Creator)engine).createAffineTransformationByMatrixAndVector(mat,vec);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Transformation createLinearTransformationByMatrix( Number[][] mat ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvancedProperty.Creator ) {

			try {
				return ((AffineTransformationAdvancedProperty.Creator)engine).createLinearTransformationByMatrix(mat);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Transformation createRotationByVector( Number[] rvec ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvancedProperty.Creator ) {

			try {
				return ((AffineTransformationAdvancedProperty.Creator)engine).createRotationByVector(rvec);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Transformation createReflectionByVector( Number[] fvec ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvancedProperty.Creator ) {

			try {
				return ((AffineTransformationAdvancedProperty.Creator)engine).createReflectionByVector(fvec);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Transformation createTranslationByVector( Number[] sh ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvancedProperty.Creator ) {

			try {
				return ((AffineTransformationAdvancedProperty.Creator)engine).createTranslationByVector(sh);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Transformation createScalingByFactor( Number factor ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvancedProperty.Creator ) {

			try {
				return ((AffineTransformationAdvancedProperty.Creator)engine).createScalingByFactor(factor);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Transformation createShearingByOffsets( Number a, Number b ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvancedProperty.Creator ) {

			try {
				return ((AffineTransformationAdvancedProperty.Creator)engine).createShearingByOffsets(a,b);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}



	// attribute
	public Number[] applyTo( Transformation trans, Number[] point ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof ArbitraryTransformationBasicProperty.Attribute ) {

			try {
				return ((ArbitraryTransformationBasicProperty.Attribute)engine).applyTo(trans,point);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Function<Number[],Number[]> getTransformationFunction( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof ArbitraryTransformationBasicProperty.Attribute ) {

			try {
				return ((ArbitraryTransformationBasicProperty.Attribute)engine).getTransformationFunction(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number[][] getTransformationMatrix( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvancedProperty.Attribute ) {

			try {
				return ((AffineTransformationAdvancedProperty.Attribute)engine).getTransformationMatrix(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number[] getRotationVector( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvancedProperty.Attribute ) {

			try {
				return ((AffineTransformationAdvancedProperty.Attribute)engine).getRotationVector(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number[] getReflectionVector( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvancedProperty.Attribute ) {

			try {
				return ((AffineTransformationAdvancedProperty.Attribute)engine).getReflectionVector(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number[] getTranslationVector( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvancedProperty.Attribute ) {

			try {
				return ((AffineTransformationAdvancedProperty.Attribute)engine).getTranslationVector(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}


	// operator
	public Transformation compose( Transformation... trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof ArbitraryTransformationBasicProperty.Operator ) {

			try {
				return ((ArbitraryTransformationBasicProperty.Operator)engine).compose(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Transformation pow( Transformation trans, int exp ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof ArbitraryTransformationBasicProperty.Operator ) {

			try {
				return ((ArbitraryTransformationBasicProperty.Operator)engine).pow(trans,exp);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Transformation dividedBy( Transformation trans, Number divisor ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof ArbitraryTransformationBasicProperty.Operator ) {

			try {
				return ((ArbitraryTransformationBasicProperty.Operator)engine).dividedBy(trans,divisor);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Transformation invert( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof ArbitraryTransformationBasicProperty.Operator ) {

			try {
				return ((ArbitraryTransformationBasicProperty.Operator)engine).invert(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Transformation transformsBy( Transformation t, Transformation p ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof ArbitraryTransformationBasicProperty.Operator ) {

			try {
				return ((ArbitraryTransformationBasicProperty.Operator)engine).transformsBy(t,p);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}



	// predicate
	public boolean isIdentity( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof ArbitraryTransformationBasicProperty.Predicate ) {

			try {
				return ((ArbitraryTransformationBasicProperty.Predicate)engine).isIdentity(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public boolean equals( Transformation trans1, Transformation trans2 ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof ArbitraryTransformationBasicProperty.Predicate ) {

			try {
				return ((ArbitraryTransformationBasicProperty.Predicate)engine).equals(trans1,trans2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public boolean isAffine( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvancedProperty.Predicate ) {

			try {
				return ((AffineTransformationAdvancedProperty.Predicate)engine).isAffine(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public boolean isLinear( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvancedProperty.Predicate ) {

			try {
				return ((AffineTransformationAdvancedProperty.Predicate)engine).isLinear(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public boolean isSimilar( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvancedProperty.Predicate ) {

			try {
				return ((AffineTransformationAdvancedProperty.Predicate)engine).isSimilar(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public boolean isIsometric( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvancedProperty.Predicate ) {

			try {
				return ((AffineTransformationAdvancedProperty.Predicate)engine).isIsometric(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public boolean isRigid( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvancedProperty.Predicate ) {

			try {
				return ((AffineTransformationAdvancedProperty.Predicate)engine).isRigid(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public boolean isTranslation( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvancedProperty.Predicate ) {

			try {
				return ((AffineTransformationAdvancedProperty.Predicate)engine).isTranslation(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
}
