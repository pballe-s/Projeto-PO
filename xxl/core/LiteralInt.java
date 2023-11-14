package xxl.core;

import xxl.core.exception.AsStringException;

public class LiteralInt extends Literal
{
	private int _value;

	public LiteralInt(int value)
	{
		_value = value;
	}

	public String toString()
	{
		return Integer.toString(_value);
	}

	public String asString() throws AsStringException
	{
		throw new AsStringException(_value);
	}

	public int asInt()
	{
		return _value;
	}
}