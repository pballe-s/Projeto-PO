package xxl.core;

import xxl.core.exception.AsIntException;
import xxl.core.exception.AsStringException;

import java.io.Serializable;

public abstract class Content implements Serializable
{
	private static final long serialVersionUID = 7308670846L;

	public abstract String toString();

	abstract Literal value();

	public String asString() throws AsStringException
	{
		return value().asString();
	}

	public abstract void addFuncCell(Function func);

	public int asInt() throws AsIntException
	{
		return value().asInt();
	}

	public abstract String showCont();

	public abstract String toFuncArg();
}