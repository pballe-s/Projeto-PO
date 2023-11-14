package xxl.core;

import xxl.core.exception.AsIntException;


public class Add extends BinaryFunction
{

	public Add(Content arg1, Content arg2)
	{
		super(arg1, arg2, "ADD");
	}

	protected Literal compute()
	{
		int i;
		
		try
		{
			if (_arg1.value() == null || _arg2.value() == null || _arg1.value().toString() == "#VALUE" || _arg2.value().toString() == "#VALUE")
				return new LiteralError();
			i = _arg1.value().asInt() + _arg2.value().asInt();
		}
		catch(AsIntException ex)
		{
			return null;
		}
		return new LiteralInt(i);
	}
}