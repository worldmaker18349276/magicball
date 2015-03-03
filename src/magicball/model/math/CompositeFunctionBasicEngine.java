package magicball.model.math;

import magicball.model.*;


public class CompositeFunctionBasicEngine extends DefaultCompositeEngine<Function> implements FunctionBasicEngine
{
	// creater
	public < I, O > Function<I,O> createFunctionByLambda( java.util.function.Function<I,O> lambda ) {
		for ( Engine<? extends Function> engine : engines ) if ( engine instanceof FunctionCreator ) {

			try {
				return ((FunctionCreator)engine).<I,O>createFunctionByLambda(lambda);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public < I, O > Function<I,O> createFunctionByDescription( String syntax, String description ) {
		for ( Engine<? extends Function> engine : engines ) if ( engine instanceof FunctionCreator ) {

			try {
				return ((FunctionCreator)engine).<I,O>createFunctionByDescription(syntax,description);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public < I > Function<I,I> createIdentityFunction() {
		for ( Engine<? extends Function> engine : engines ) if ( engine instanceof FunctionCreator ) {

			try {
				return ((FunctionCreator)engine).<I>createIdentityFunction();
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public < I, O > Function<I,O> createConstantFunction( O c ) {
		for ( Engine<? extends Function> engine : engines ) if ( engine instanceof FunctionCreator ) {

			try {
				return ((FunctionCreator)engine).<I,O>createConstantFunction(c);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}


	// attribute
	public < I, O > O applies( Function<I,O> func, I in ) {
		for ( Engine<? extends Function> engine : engines ) if ( engine instanceof FunctionBehavior ) {

			try {
				return ((FunctionBehavior)engine).<I,O>applies(func, in);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}


	// operator
	public < I, M, O > Function<I,O> compose( Function<I,M> func1, Function<M,O> func2 ) {
		for ( Engine<? extends Function> engine : engines ) if ( engine instanceof FunctionOperator ) {

			try {
				return ((FunctionOperator)engine).<I,M,O>compose(func1, func2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public < I, O > Function<O,I> invert( Function<I,O> func ) {
		for ( Engine<? extends Function> engine : engines ) if ( engine instanceof FunctionOperator ) {

			try {
				return ((FunctionOperator)engine).<I,O>invert(func);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public < I > Function<I,Boolean> not( Function<I,Boolean> func ) {
		for ( Engine<? extends Function> engine : engines ) if ( engine instanceof BooleanFunctionOperator ) {

			try {
				return ((BooleanFunctionOperator)engine).<I>not(func);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public < I > Function<I,Boolean> not( Function<I,Boolean> func1, Function<I,Boolean> func2 ) {
		for ( Engine<? extends Function> engine : engines ) if ( engine instanceof BooleanFunctionOperator ) {

			try {
				return ((BooleanFunctionOperator)engine).<I>not(func1, func2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@SuppressWarnings({"unchecked", "varargs"})
	public < I > Function<I,Boolean> and( Function<I,Boolean>... funcs ) {
		for ( Engine<? extends Function> engine : engines ) if ( engine instanceof BooleanFunctionOperator ) {

			try {
				return ((BooleanFunctionOperator)engine).<I>and(funcs);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public < I > Function<I,Boolean> and( Function<I,Boolean> func1, Function<I,Boolean> func2 ) {
		for ( Engine<? extends Function> engine : engines ) if ( engine instanceof BooleanFunctionOperator ) {

			try {
				return ((BooleanFunctionOperator)engine).<I>and(func1, func2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@SuppressWarnings({"unchecked", "varargs"})
	public < I > Function<I,Boolean> or( Function<I,Boolean>... funcs ) {
		for ( Engine<? extends Function> engine : engines ) if ( engine instanceof BooleanFunctionOperator ) {

			try {
				return ((BooleanFunctionOperator)engine).<I>or(funcs);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public < I > Function<I,Boolean> or( Function<I,Boolean> func1, Function<I,Boolean> func2 ) {
		for ( Engine<? extends Function> engine : engines ) if ( engine instanceof BooleanFunctionOperator ) {

			try {
				return ((BooleanFunctionOperator)engine).<I>or(func1, func2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	@SuppressWarnings({"unchecked", "varargs"})
	public < I > Function<I,Boolean> xor( Function<I,Boolean>... funcs ) {
		for ( Engine<? extends Function> engine : engines ) if ( engine instanceof BooleanFunctionOperator ) {

			try {
				return ((BooleanFunctionOperator)engine).<I>xor(funcs);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public < I > Function<I,Boolean> xor( Function<I,Boolean> func1, Function<I,Boolean> func2 ) {
		for ( Engine<? extends Function> engine : engines ) if ( engine instanceof BooleanFunctionOperator ) {

			try {
				return ((BooleanFunctionOperator)engine).<I>xor(func1, func2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}


	// predicate
	public < I, O > boolean equals( Function<I,O> func1, Function<I,O> func2 ) {
		for ( Engine<? extends Function> engine : engines ) if ( engine instanceof FunctionPredicate ) {

			try {
				return ((FunctionPredicate)engine).<I,O>equals(func1, func2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public < I, O > boolean isAlwaysEqualTo( Function<I,O> func, O value ) {
		for ( Engine<? extends Function> engine : engines ) if ( engine instanceof FunctionPredicate ) {

			try {
				return ((FunctionPredicate)engine).<I,O>isAlwaysEqualTo(func, value);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public < I > boolean isAlwaysTrue( Function<I,Boolean> func ) {
		for ( Engine<? extends Function> engine : engines ) if ( engine instanceof BooleanFunctionPredicate ) {

			try {
				return ((BooleanFunctionPredicate)engine).<I>isAlwaysTrue(func);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public < I > boolean isAlwaysFalse( Function<I,Boolean> func ) {
		for ( Engine<? extends Function> engine : engines ) if ( engine instanceof BooleanFunctionPredicate ) {

			try {
				return ((BooleanFunctionPredicate)engine).<I>isAlwaysFalse(func);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public < I > boolean implies( Function<I,Boolean> func1, Function<I,Boolean> func2 ) {
		for ( Engine<? extends Function> engine : engines ) if ( engine instanceof BooleanFunctionPredicate ) {

			try {
				return ((BooleanFunctionPredicate)engine).<I>implies(func1, func2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}
}
