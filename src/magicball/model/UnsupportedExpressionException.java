package magicball.model;


public class UnsupportedExpressionException extends RuntimeException
{
	protected Class<?> expression;

	public UnsupportedExpressionException( Class<?> exp ) {
		super("unsupported expression: "+exp.toString());
		this.expression = exp;
	}
}
