package xxl.core;

public abstract class Literal extends Content
{
	Literal value()
	{
		return this;
	}

	public String showCont()
	{
		return toString();
	}

	public String toFuncArg()
	{
		return toString();
	}

	public void addFuncCell(Function func)
	{}
}