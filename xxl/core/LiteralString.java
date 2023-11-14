package xxl.core;

import xxl.core.exception.AsIntException;

public class LiteralString extends Literal
{
	public String _value;

	public LiteralString(String value)
	{
		_value = value;
	}
	
	public String toString()
	{
		return _value;
	}

	public String asString()
	{
		return _value;
	}

	public int asInt() throws AsIntException
	{
		throw new AsIntException(_value);
	}
}