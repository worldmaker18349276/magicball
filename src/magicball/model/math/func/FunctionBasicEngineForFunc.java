package magicball.model.math.func;

import magicball.model.math.*;


public class FunctionBasicEngineForFunc implements FunctionBasicEngine
{

	public < I, M, O > Function<I,O> compose( final Function<I,M> func1, final Function<M,O> func2 ) {
		return new Function<I,O>() {
			public O apply( I in ) {
				return func2.apply(func1.apply(in));
			}
		};
	}

	public < I > Function<I,I> createIdentityFunction() {
		return new Function<I,I>() {
			public I apply( I in ) {
				return in;
			}
		};
	}
}
