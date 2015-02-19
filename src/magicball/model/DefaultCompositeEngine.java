package magicball.model;


public class DefaultCompositeEngine < E > implements CompositeEngine<E>
{
	protected java.util.List<Engine<? extends E>> engines;

	public DefaultCompositeEngine() {
		engines = new java.util.LinkedList<>();
	}

	@Override
	public void add( Engine<? extends E> engine ) {
		engines.add(0, engine);
	}

	@Override
	public Engine<? extends E> get( Class<? extends Engine<? extends E>> clazz ) {
		return engines.stream()
			.filter(clazz::isInstance)
			.findFirst()
			.orElse(null);
	}


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
