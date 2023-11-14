package xxl.core;

import xxl.core.exception.AsIntException;

public class LiteralError extends Literal
{
	private final String _errorMessage = "#VALUE";

	public String toString()
	{
		return _errorMessage;
	}

	public int asInt() throws AsIntException
	{
		throw new AsIntException(_errorMessage);
	}

	public Literal value()
	{
		return this;
	}
}