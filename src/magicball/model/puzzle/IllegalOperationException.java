package magicball.model.puzzle;


// physical puzzle abstraction layer
public class IllegalOperationException extends Exception
{
	public IllegalOperationException() {
		super("illegal operation on thid puzzle");
	}
}
