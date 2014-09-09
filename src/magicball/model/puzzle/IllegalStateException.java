package magicball.model.puzzle;


public class IllegalStateException extends Exception
{
	public IllegalStateException() {
		super("illegal puzzle state");
	}
}
