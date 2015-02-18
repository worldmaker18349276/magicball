package magicball.model.math;

import magicball.model.*;


public class CompositeFunctionBasicEngine extends CompositeEngine<Function> implements FunctionAdvancedEngine
{
	protected java.util.List<Engine<? extends Function>> engines;

	public CompositeFunctionBasicEngine() {
		engines = new java.util.LinkedList<>();
	}

	public CompositeFunctionBasicEngine( java.util.List<Engine<? extends Function>> eng ) {
		engines = eng;
	}

	public CompositeFunctionBasicEngine clone() {
		return new CompositeFunctionBasicEngine(new java.util.LinkedList<>(engines));
	}

	public void add( Engine<? extends Function> engine ) {
		engines.add(engine);
	}

	public void add( int index, Engine<? extends Function> engine ) {
		engines.add(index, engine);
	}


	// creater
	public < I, O > Function<I,O> createFunctionByLambda( java.util.function.Function<I,O> lambda ) {
		for ( Engine<? extends Function> engine : engines ) if ( engine instanceof FunctionBasicCreator ) {

			try {
				return ((FunctionBasicCreator)engine).<I,O>createFunctionByLambda(lambda);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public < I > Function<I,I> createIdentityFunction() {
		for ( Engine<? extends Function> engine : engines ) if ( engine instanceof FunctionBasicCreator ) {

			try {
				return ((FunctionBasicCreator)engine).<I>createIdentityFunction();
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public < I, O > Function<I,O> createConstantFunction( O c ) {
		for ( Engine<? extends Function> engine : engines ) if ( engine instanceof FunctionBasicCreator ) {

			try {
				return ((FunctionBasicCreator)engine).<I,O>createConstantFunction(c);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public < I > Function<I,Boolean> createEqualToFunction( I in ) {
		for ( Engine<? extends Function> engine : engines ) if ( engine instanceof BooleanFunctionCreator ) {

			try {
				return ((BooleanFunctionCreator)engine).<I>createEqualToFunction(in);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Function<Boolean,Boolean> createNotFunction() {
		for ( Engine<? extends Function> engine : engines ) if ( engine instanceof BooleanFunctionCreator ) {

			try {
				return ((BooleanFunctionCreator)engine).createNotFunction();
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Function<Boolean,Function<Boolean,Boolean>> createNotToFunction() {
		for ( Engine<? extends Function> engine : engines ) if ( engine instanceof BooleanFunctionCreator ) {

			try {
				return ((BooleanFunctionCreator)engine).createNotToFunction();
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Function<Boolean,Function<Boolean,Boolean>> createAndFunction() {
		for ( Engine<? extends Function> engine : engines ) if ( engine instanceof BooleanFunctionCreator ) {

			try {
				return ((BooleanFunctionCreator)engine).createAndFunction();
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Function<Boolean,Function<Boolean,Boolean>> createOrFunction() {
		for ( Engine<? extends Function> engine : engines ) if ( engine instanceof BooleanFunctionCreator ) {

			try {
				return ((BooleanFunctionCreator)engine).createOrFunction();
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Function<Boolean,Function<Boolean,Boolean>> createXorFunction() {
		for ( Engine<? extends Function> engine : engines ) if ( engine instanceof BooleanFunctionCreator ) {

			try {
				return ((BooleanFunctionCreator)engine).createXorFunction();
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}


	// attribute
	public < I, O > O applies( Function<I,O> func, I in ) {
		for ( Engine<? extends Function> engine : engines ) if ( engine instanceof FunctionBasicAttribute ) {

			try {
				return ((FunctionBasicAttribute)engine).<I,O>applies(func, in);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public < I1, I2, O > O applies( Function<I1,Function<I2,O>> func, I1 in1, I2 in2 ) {
		for ( Engine<? extends Function> engine : engines ) if ( engine instanceof FunctionBasicAttribute ) {

			try {
				return ((FunctionBasicAttribute)engine).<I1,I2,O>applies(func, in1, in2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}


	// operator
	public < I, M, O > Function<I,O> compose( Function<I,M> func1, Function<M,O> func2 ) {
		for ( Engine<? extends Function> engine : engines ) if ( engine instanceof FunctionBasicOperator ) {

			try {
				return ((FunctionBasicOperator)engine).<I,M,O>compose(func1, func2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public < I, O > Function<O,I> invert( Function<I,O> func ) {
		for ( Engine<? extends Function> engine : engines ) if ( engine instanceof FunctionBasicOperator ) {

			try {
				return ((FunctionBasicOperator)engine).<I,O>invert(func);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public < I1, I2, O > Function<I2,Function<I1,O>> swap( Function<I1,Function<I2,O>> func ) {
		for ( Engine<? extends Function> engine : engines ) if ( engine instanceof FunctionBasicOperator ) {

			try {
				return ((FunctionBasicOperator)engine).<I1,I2,O>swap(func);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public < I, O > Function<I,O> duplicateInput( Function<I,Function<I,O>> func ) {
		for ( Engine<? extends Function> engine : engines ) if ( engine instanceof FunctionBasicOperator ) {

			try {
				return ((FunctionBasicOperator)engine).<I,O>duplicateInput(func);
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
		for ( Engine<? extends Function> engine : engines ) if ( engine instanceof FunctionBasicPredicate ) {

			try {
				return ((FunctionBasicPredicate)engine).<I,O>equals(func1, func2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public < I, O > boolean isAlwaysEqualTo( Function<I,O> func, O value ) {
		for ( Engine<? extends Function> engine : engines ) if ( engine instanceof FunctionBasicPredicate ) {

			try {
				return ((FunctionBasicPredicate)engine).<I,O>isAlwaysEqualTo(func, value);
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
