package magicball.model;


public interface Engine < E >
{
	@SuppressWarnings({"unchecked"})
	default public E cast( Object func ) {
		try {
			return (E) func;
		} catch ( ClassCastException e ) {
			throw new UnsupportedExpressionException(func.getClass());
		}
	}
}
