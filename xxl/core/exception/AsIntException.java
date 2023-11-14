package xxl.core.exception;

public class AsIntException extends Exception
{
	private String _str;

	public AsIntException(String str)
	{
		super("Tried to obtain a int from a LiteralString.");
		_str = str;
	}

	public String getStr()
	{
		return _str;
	}
}