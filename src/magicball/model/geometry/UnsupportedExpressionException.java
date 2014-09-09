package magicball.model.geometry;


// physical puzzle abstraction layer
public class UnsupportedExpressionException extends RuntimeException
{
	public UnsupportedExpressionException() {
		super("unsupported expression");
	}
}
