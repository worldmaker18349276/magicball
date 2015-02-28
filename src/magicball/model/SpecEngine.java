package magicball.model;


public interface SpecEngine < O, E extends O > extends Engine<O>
{
	@SuppressWarnings({"unchecked"})
	default public E cast( O func ) {
		try {
			return (E) func;
		} catch ( ClassCastException e ) {
			throw new UnsupportedExpressionException(func.getClass());
		}
	}
}
