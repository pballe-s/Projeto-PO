package xxl.core.exception;

public class AsStringException extends Exception
{
	private int _integer;

	public AsStringException(int integer)
	{
		super("Tried to obtain a String from a LiteralInt.");
		_integer = integer;
	}

	public int getInt()
	{
		return _integer;
	}
}