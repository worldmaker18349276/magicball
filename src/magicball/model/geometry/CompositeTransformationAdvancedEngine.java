package magicball.model.geometry;

import java.util.Optional;

import magicball.model.math.Func;
import magicball.model.math.Num;
import magicball.model.*;


public class CompositeTransformationAdvancedEngine extends DefaultCompositeEngine<Transformation> implements TransformationAdvancedEngine
{
	// behavior
	@Override
	public Num[] applyTo( Transformation trans, Num[] point ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof ArbitraryTransformationBasicProperty.Behavior ) {

			try {
				return ((ArbitraryTransformationBasicProperty.Behavior)engine).applyTo(trans,point);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}


	// creater
	@Override
	public Transformation createTransformationByFunction( Func<Num[],Num[]> func ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof ArbitraryTransformationBasicProperty.Creator ) {

			try {
				return ((ArbitraryTransformationBasicProperty.Creator)engine).createTransformationByFunction(func);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
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

	@Override
	public Transformation createAffineTransformationByAugmentedMatrix( Num[][] mat ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvancedProperty.Creator ) {

			try {
				return ((AffineTransformationAdvancedProperty.Creator)engine).createAffineTransformationByAugmentedMatrix(mat);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public Transformation createAffineTransformationByMatrixAndVector( Num[][] mat, Num[] vec ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvancedProperty.Creator ) {

			try {
				return ((AffineTransformationAdvancedProperty.Creator)engine).createAffineTransformationByMatrixAndVector(mat,vec);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public Transformation createLinearTransformationByMatrix( Num[][] mat ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvancedProperty.Creator ) {

			try {
				return ((AffineTransformationAdvancedProperty.Creator)engine).createLinearTransformationByMatrix(mat);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public Transformation createRotationByVector( Num[] rvec ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvancedProperty.Creator ) {

			try {
				return ((AffineTransformationAdvancedProperty.Creator)engine).createRotationByVector(rvec);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public Transformation createReflectionByVector( Num[] fvec ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvancedProperty.Creator ) {

			try {
				return ((AffineTransformationAdvancedProperty.Creator)engine).createReflectionByVector(fvec);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public Transformation createTranslationByVector( Num[] sh ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvancedProperty.Creator ) {

			try {
				return ((AffineTransformationAdvancedProperty.Creator)engine).createTranslationByVector(sh);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public Transformation createScalingByFactor( Num factor ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvancedProperty.Creator ) {

			try {
				return ((AffineTransformationAdvancedProperty.Creator)engine).createScalingByFactor(factor);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public Transformation createShearingByOffsets( Num a, Num b ) {
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
	@Override
	public Func<Num[],Num[]> getTransformationFunctionOf( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof ArbitraryTransformationBasicProperty.Attribute ) {

			try {
				return ((ArbitraryTransformationBasicProperty.Attribute)engine).getTransformationFunctionOf(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public Optional<Num[][]> getTransformationMatrixOf( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvancedProperty.Attribute ) {

			try {
				return ((AffineTransformationAdvancedProperty.Attribute)engine).getTransformationMatrixOf(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public Optional<Num[]> getRotationVectorOf( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvancedProperty.Attribute ) {

			try {
				return ((AffineTransformationAdvancedProperty.Attribute)engine).getRotationVectorOf(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public Optional<Num[]> getReflectionVectorOf( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvancedProperty.Attribute ) {

			try {
				return ((AffineTransformationAdvancedProperty.Attribute)engine).getReflectionVectorOf(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public Optional<Num[]> getTranslationVectorOf( Transformation trans ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof AffineTransformationAdvancedProperty.Attribute ) {

			try {
				return ((AffineTransformationAdvancedProperty.Attribute)engine).getTranslationVectorOf(trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}


	// operator
	@Override
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

	@Override
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

	@Override
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

	@Override
	public Transformation pow( Transformation trans, int exp ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof ArbitraryTransformationAdvancedProperty.Operator ) {

			try {
				return ((ArbitraryTransformationAdvancedProperty.Operator)engine).pow(trans,exp);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public Transformation dividedBy( Transformation trans, Num divisor ) {
		for ( Engine<? extends Transformation> engine : engines ) if ( engine instanceof ArbitraryTransformationAdvancedProperty.Operator ) {

			try {
				return ((ArbitraryTransformationAdvancedProperty.Operator)engine).dividedBy(trans,divisor);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}



	// predicate
	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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
