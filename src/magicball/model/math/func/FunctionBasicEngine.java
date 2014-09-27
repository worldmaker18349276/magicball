package magicball.model.math.func;

import magicball.model.*;
import magicball.model.math.*;


public class FunctionBasicEngine implements FunctionEngine
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

	public < I, O > O applies( Function<I,O> func, I in ) {
		return func.apply(in);
	}

	public < I, O > java.util.Set<O> appliesAll( Function<I,O> func, java.util.Set<I> ins ) {
		java.util.Set<O> outs = new java.util.HashSet<O>();
		for ( I in : ins )
			outs.add(applies(func,in));
		return outs;
	}

	public < I, O > java.util.Map.Entry<I,O> maps( Function<I,O> func, I in ) {
		return new java.util.AbstractMap.SimpleEntry<I,O>(in,func.apply(in));
	}

	public < I, O > java.util.Map<I,O> mapsAll( Function<I,O> func, java.util.Set<I> ins ) {
		java.util.Map<I,O> outs = new java.util.HashMap<I,O>();
		for ( I in : ins )
			outs.put(in,applies(func,in));
		return outs;
	}


	public < I, O > Function<I,O> equals( Function<I,O> func1, Function<I,O> func2 ) {
		throw new UnsupportedAlgorithmException();
	}
	public < I, O > Function<O,I> invert( Function<I,O> func ) {
		throw new UnsupportedAlgorithmException();
	}
}
