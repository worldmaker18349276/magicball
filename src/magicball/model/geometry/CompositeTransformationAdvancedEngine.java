package magicball.model.geometry;

import magicball.model.math.Func;
import magicball.model.*;


public class CompositeTransformationAdvancedEngine extends DefaultCompositeEngine<Transformation> implements TransformationBasicEngine
{
	// creater
	public Transformation createTransformationByFunction( Function<Number[],Number[]> func ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof TransformationBasic.Creator ) {

			try {
				return ((TransformationBasic.Creator)engine).createTransformationByFunction(func);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Transformation createIdentityTransformation() {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof TransformationBasic.Creator ) {

			try {
				return ((TransformationBasic.Creator)engine).createIdentityTransformation();
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Transformation createAffineTransformationByAugmentedMatrix( Number[][] mat ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvanced.Creator ) {

			try {
				return ((AffineTransformationAdvanced.Creator)engine).createAffineTransformationByAugmentedMatrix(mat);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Transformation createAffineTransformationByMatrixAndVector( Number[][] mat, Number[] vec ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvanced.Creator ) {

			try {
				return ((AffineTransformationAdvanced.Creator)engine).createAffineTransformationByMatrixAndVector(mat,vec);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Transformation createLinearTransformationByMatrix( Number[][] mat ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvanced.Creator ) {

			try {
				return ((AffineTransformationAdvanced.Creator)engine).createLinearTransformationByMatrix(mat);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Transformation createRotationByVector( Number[] rvec ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvanced.Creator ) {

			try {
				return ((AffineTransformationAdvanced.Creator)engine).createRotationByVector(rvec);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Transformation createReflectionByVector( Number[] fvec ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvanced.Creator ) {

			try {
				return ((AffineTransformationAdvanced.Creator)engine).createReflectionByVector(fvec);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Transformation createTranslationByVector( Number[] sh ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvanced.Creator ) {

			try {
				return ((AffineTransformationAdvanced.Creator)engine).createTranslationByVector(sh);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Transformation createScalingByFactor( Number factor ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvanced.Creator ) {

			try {
				return ((AffineTransformationAdvanced.Creator)engine).createScalingByFactor(factor);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Transformation createShearingByOffsets( Number a, Number b ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvanced.Creator ) {

			try {
				return ((AffineTransformationAdvanced.Creator)engine).createShearingByOffsets(a,b);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}



	// attribute
	public Number[] applyTo( Transformation trans, Number[] point ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof TransformationBasic.Attribute ) {

			try {
				return ((TransformationBasic.Attribute)engine).applyTo(trans,point);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Function<Number[],Number[]> getTransformationFunction( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof TransformationBasic.Attribute ) {

			try {
				return ((TransformationBasic.Attribute)engine).getTransformationFunction(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number[][] getTransformationMatrix( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvanced.Attribute ) {

			try {
				return ((AffineTransformationAdvanced.Attribute)engine).getTransformationMatrix(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number[] getRotationVector( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvanced.Attribute ) {

			try {
				return ((AffineTransformationAdvanced.Attribute)engine).getRotationVector(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number[] getReflectionVector( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvanced.Attribute ) {

			try {
				return ((AffineTransformationAdvanced.Attribute)engine).getReflectionVector(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Number[] getTranslationVector( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvanced.Attribute ) {

			try {
				return ((AffineTransformationAdvanced.Attribute)engine).getTranslationVector(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}


	// operator
	public Transformation compose( Transformation... trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof TransformationBasic.Operator ) {

			try {
				return ((TransformationBasic.Operator)engine).compose(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Transformation pow( Transformation trans, int exp ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof TransformationBasic.Operator ) {

			try {
				return ((TransformationBasic.Operator)engine).pow(trans,exp);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Transformation dividedBy( Transformation trans, Number divisor ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof TransformationBasic.Operator ) {

			try {
				return ((TransformationBasic.Operator)engine).dividedBy(trans,divisor);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Transformation invert( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof TransformationBasic.Operator ) {

			try {
				return ((TransformationBasic.Operator)engine).invert(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Transformation transformsBy( Transformation t, Transformation p ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof TransformationBasic.Operator ) {

			try {
				return ((TransformationBasic.Operator)engine).transformsBy(t,p);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}



	// predicate
	public boolean isIdentity( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof TransformationBasic.Predicate ) {

			try {
				return ((TransformationBasic.Predicate)engine).isIdentity(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public boolean equals( Transformation trans1, Transformation trans2 ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof TransformationBasic.Predicate ) {

			try {
				return ((TransformationBasic.Predicate)engine).equals(trans1,trans2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public boolean isAffine( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvanced.Predicate ) {

			try {
				return ((AffineTransformationAdvanced.Predicate)engine).isAffine(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public boolean isLinear( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvanced.Predicate ) {

			try {
				return ((AffineTransformationAdvanced.Predicate)engine).isLinear(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public boolean isSimilar( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvanced.Predicate ) {

			try {
				return ((AffineTransformationAdvanced.Predicate)engine).isSimilar(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public boolean isIsometric( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvanced.Predicate ) {

			try {
				return ((AffineTransformationAdvanced.Predicate)engine).isIsometric(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public boolean isRigid( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvanced.Predicate ) {

			try {
				return ((AffineTransformationAdvanced.Predicate)engine).isRigid(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public boolean isTranslation( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvanced.Predicate ) {

			try {
				return ((AffineTransformationAdvanced.Predicate)engine).isTranslation(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
}
