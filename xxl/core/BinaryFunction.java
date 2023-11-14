package xxl.core;

public abstract class BinaryFunction extends Function
{
	protected Content _arg1;
	protected Content _arg2;

	public BinaryFunction(Content arg1, Content arg2, String name)
	{
		super(name);
		_arg1 = arg1;
		_arg2 = arg2;
		_arg1.addFuncCell(this);
		_arg2.addFuncCell(this);
	}

	public String toString()
	{
		return "=" + _name + "(" + this.toFuncArg() + ")";
	}



	public String toFuncArg()
	{
		return _arg1.toFuncArg() + "," + _arg2.toFuncArg();
	}

	public String showCont()
	{
		return value().toString() + toString();
	}
}