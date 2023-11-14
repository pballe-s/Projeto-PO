package xxl.core.exception;

public class ImpossibleRangeException extends Exception
{
	private String _range;

	public ImpossibleRangeException(String range)
	{
		super(range);
		_range = range;
	}

	public String getMessage()
	{
		return _range;
	}
}