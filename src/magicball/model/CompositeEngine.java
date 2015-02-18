package magicball.model;

import java.lang.reflect.*;


public abstract class CompositeEngine < E > implements Engine<E>
{
	public abstract CompositeEngine<E> clone();

	public abstract void add( Engine<? extends E> engine );
	public abstract void add( int index, Engine<? extends E> engine );


	// public Object call( Method method, Object... args ) throws Throwable {
	// 	for ( Engine<? extends E> engine : engines ) if ( method.getDeclaringClass().isInstance(engine) ) {

	// 		try {
	// 			return method.invoke(engine, args);
	// 		} catch ( InvocationTargetException e ) {
	// 			try {
	// 				throw e.getTargetException();
	// 			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e2 ) {
	// 				continue;
	// 			}
	// 		}

	// 	}
	// 	throw new UnsupportedAlgorithmException();
	// }
}
