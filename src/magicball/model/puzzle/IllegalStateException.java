package magicball.model.puzzle;


// physical puzzle abstraction layer
public class IllegalStateException extends Exception
{
	public IllegalStateException() {
		super("illegal puzzle state");
	}
}
