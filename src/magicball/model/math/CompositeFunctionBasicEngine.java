package magicball.model.math;

import java.util.function.Function;
import java.util.Optional;

import magicball.model.*;


public class CompositeFunctionBasicEngine extends DefaultCompositeEngine<Func> implements FunctionBasicEngine
{
	// behavior
	@Override
	public < I, O > O applyTo( Func<I,O> func, I in ) {
		for ( Engine<? extends Func> engine : engines ) if ( engine instanceof ArbitraryFunctionBasicProperty.Behavior ) {

			try {
				return ((ArbitraryFunctionBasicProperty.Behavior)engine).<I,O>applyTo(func, in);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}


	// creater
	@Override
	public < I, O > Func<I,O> createFunctionByLambda( Function<I,O> lambda ) {
		for ( Engine<? extends Func> engine : engines ) if ( engine instanceof ArbitraryFunctionBasicProperty.Creator ) {

			try {
				return ((ArbitraryFunctionBasicProperty.Creator)engine).<I,O>createFunctionByLambda(lambda);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public < I > Func<I,I> createIdentityFunction() {
		for ( Engine<? extends Func> engine : engines ) if ( engine instanceof ArbitraryFunctionBasicProperty.Creator ) {

			try {
				return ((ArbitraryFunctionBasicProperty.Creator)engine).<I>createIdentityFunction();
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public < I, O > Func<I,O> createConstantFunctionWithValue( O constant ) {
		for ( Engine<? extends Func> engine : engines ) if ( engine instanceof ArbitraryFunctionBasicProperty.Creator ) {

			try {
				return ((ArbitraryFunctionBasicProperty.Creator)engine).<I,O>createConstantFunctionWithValue(constant);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}


	// attribute
	@Override
	public < I, O > Optional<O> getConstantValueOf( Func<I,O> func ) {
		for ( Engine<? extends Func> engine : engines ) if ( engine instanceof ArbitraryFunctionBasicProperty.Attribute ) {

			try {
				return ((ArbitraryFunctionBasicProperty.Attribute)engine).<I,O>getConstantValueOf(func);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}


	// operator
	@Override
	public < I, M, O > Func<I,O> compose( Func<I,M> func1, Func<M,O> func2 ) {
		for ( Engine<? extends Func> engine : engines ) if ( engine instanceof ArbitraryFunctionBasicProperty.Operator ) {

			try {
				return ((ArbitraryFunctionBasicProperty.Operator)engine).<I,M,O>compose(func1, func2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public < I, O > Optional<Func<O,I>> invert( Func<I,O> func ) {
		for ( Engine<? extends Func> engine : engines ) if ( engine instanceof ArbitraryFunctionBasicProperty.Operator ) {

			try {
				return ((ArbitraryFunctionBasicProperty.Operator)engine).<I,O>invert(func);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public < I > Func<I,Boolean> not( Func<I,Boolean> func ) {
		for ( Engine<? extends Func> engine : engines ) if ( engine instanceof BooleanFunctionBasicProperty.Operator ) {

			try {
				return ((BooleanFunctionBasicProperty.Operator)engine).<I>not(func);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public < I > Func<I,Boolean> not( Func<I,Boolean> func1, Func<I,Boolean> func2 ) {
		for ( Engine<? extends Func> engine : engines ) if ( engine instanceof BooleanFunctionBasicProperty.Operator ) {

			try {
				return ((BooleanFunctionBasicProperty.Operator)engine).<I>not(func1, func2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public < I > Func<I,Boolean> and( Func<I,Boolean> func1, Func<I,Boolean> func2 ) {
		for ( Engine<? extends Func> engine : engines ) if ( engine instanceof BooleanFunctionBasicProperty.Operator ) {

			try {
				return ((BooleanFunctionBasicProperty.Operator)engine).<I>and(func1, func2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	@SuppressWarnings({"unchecked", "varargs"})
	public < I > Func<I,Boolean> and( Func<I,Boolean>... funcs ) {
		for ( Engine<? extends Func> engine : engines ) if ( engine instanceof BooleanFunctionBasicProperty.Operator ) {

			try {
				return ((BooleanFunctionBasicProperty.Operator)engine).<I>and(funcs);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public < I > Func<I,Boolean> or( Func<I,Boolean> func1, Func<I,Boolean> func2 ) {
		for ( Engine<? extends Func> engine : engines ) if ( engine instanceof BooleanFunctionBasicProperty.Operator ) {

			try {
				return ((BooleanFunctionBasicProperty.Operator)engine).<I>or(func1, func2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	@SuppressWarnings({"unchecked", "varargs"})
	public < I > Func<I,Boolean> or( Func<I,Boolean>... funcs ) {
		for ( Engine<? extends Func> engine : engines ) if ( engine instanceof BooleanFunctionBasicProperty.Operator ) {

			try {
				return ((BooleanFunctionBasicProperty.Operator)engine).<I>or(funcs);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public < I > Func<I,Boolean> xor( Func<I,Boolean> func1, Func<I,Boolean> func2 ) {
		for ( Engine<? extends Func> engine : engines ) if ( engine instanceof BooleanFunctionBasicProperty.Operator ) {

			try {
				return ((BooleanFunctionBasicProperty.Operator)engine).<I>xor(func1, func2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	@SuppressWarnings({"unchecked", "varargs"})
	public < I > Func<I,Boolean> xor( Func<I,Boolean>... funcs ) {
		for ( Engine<? extends Func> engine : engines ) if ( engine instanceof BooleanFunctionBasicProperty.Operator ) {

			try {
				return ((BooleanFunctionBasicProperty.Operator)engine).<I>xor(funcs);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}


	// predicate
	@Override
	public < I, O > boolean equals( Func<I,O> func1, Func<I,O> func2 ) {
		for ( Engine<? extends Func> engine : engines ) if ( engine instanceof ArbitraryFunctionBasicProperty.Predicate ) {

			try {
				return ((ArbitraryFunctionBasicProperty.Predicate)engine).<I,O>equals(func1, func2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public < I, O > boolean isInvertible( Func<I,O> func ) {
		for ( Engine<? extends Func> engine : engines ) if ( engine instanceof ArbitraryFunctionBasicProperty.Predicate ) {

			try {
				return ((ArbitraryFunctionBasicProperty.Predicate)engine).<I,O>isInvertible(func);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public < I, O > boolean isIdentityFunction( Func<I,O> func ) {
		for ( Engine<? extends Func> engine : engines ) if ( engine instanceof ArbitraryFunctionBasicProperty.Predicate ) {

			try {
				return ((ArbitraryFunctionBasicProperty.Predicate)engine).<I,O>isIdentityFunction(func);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public < I, O > boolean isConstantFunction( Func<I,O> func ) {
		for ( Engine<? extends Func> engine : engines ) if ( engine instanceof ArbitraryFunctionBasicProperty.Predicate ) {

			try {
				return ((ArbitraryFunctionBasicProperty.Predicate)engine).<I,O>isConstantFunction(func);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public < I, O > boolean isAlwaysEqualTo( Func<I,O> func, O value ) {
		for ( Engine<? extends Func> engine : engines ) if ( engine instanceof ArbitraryFunctionBasicProperty.Predicate ) {

			try {
				return ((ArbitraryFunctionBasicProperty.Predicate)engine).<I,O>isAlwaysEqualTo(func, value);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public < I > boolean isAlwaysTrue( Func<I,Boolean> func ) {
		for ( Engine<? extends Func> engine : engines ) if ( engine instanceof BooleanFunctionBasicProperty.Predicate ) {

			try {
				return ((BooleanFunctionBasicProperty.Predicate)engine).<I>isAlwaysTrue(func);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public < I > boolean isAlwaysFalse( Func<I,Boolean> func ) {
		for ( Engine<? extends Func> engine : engines ) if ( engine instanceof BooleanFunctionBasicProperty.Predicate ) {

			try {
				return ((BooleanFunctionBasicProperty.Predicate)engine).<I>isAlwaysFalse(func);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public < I > boolean implies( Func<I,Boolean> func1, Func<I,Boolean> func2 ) {
		for ( Engine<? extends Func> engine : engines ) if ( engine instanceof BooleanFunctionBasicProperty.Predicate ) {

			try {
				return ((BooleanFunctionBasicProperty.Predicate)engine).<I>implies(func1, func2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
}
