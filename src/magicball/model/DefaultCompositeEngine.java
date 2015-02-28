package magicball.model;


public class DefaultCompositeEngine < O > implements CompositeEngine<O>
{
	protected java.util.List<Engine<O>> engines;

	public DefaultCompositeEngine() {
		engines = new java.util.LinkedList<>();
	}

	@Override
	public void add( Engine<O> engine ) {
		engines.add(0, engine);
	}

	@Override
	public Engine<O> get( Class<? extends Engine<O>> clazz ) {
		return engines.stream()
			.filter(clazz::isInstance)
			.findFirst()
			.orElse(null);
	}


	// public Object call( Method method, Object... args ) throws Throwable {
	// 	for ( Engine<O> engine : engines ) if ( method.getDeclaringClass().isInstance(engine) ) {

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
