package magicball.model;


public class IllegalOperationException extends Exception
{
	public IllegalOperationException() {
		super("illegal operation on thid puzzle");
	}
}
