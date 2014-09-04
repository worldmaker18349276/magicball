package magicball.model;


public class IllegalStateException extends Exception
{
	public IllegalStateException() {
		super("illegal puzzle state");
	}
}
