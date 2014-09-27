package magicball.model.math.func;

import magicball.model.*;
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

	public < I, O > Function<I,O> equals( Function<I,O> func1, Function<I,O> func2 ) {
		throw new UnsupportedAlgorithmException();
	}
	public < I, O > Function<O,I> invert( Function<I,O> func ) {
		throw new UnsupportedAlgorithmException();
	}
	public < I, O > O applies( Function<I,O> func, I in ) {
		throw new UnsupportedAlgorithmException();
	}
	public < I, O > java.util.Set<O> appliesAll( Function<I,O> func, java.util.Set<I> ins ) {
		throw new UnsupportedAlgorithmException();
	}
	public < I, O > java.util.Map.Entry<I,O> maps( Function<I,O> func, I in ) {
		throw new UnsupportedAlgorithmException();
	}
	public < I, O > java.util.Map<I,O> mapsAll( Function<I,O> func, java.util.Set<I> ins ) {
		throw new UnsupportedAlgorithmException();
	}
}
