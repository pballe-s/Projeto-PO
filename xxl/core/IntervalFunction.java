package xxl.core;

public abstract class IntervalFunction extends Function
{

	protected Range _range;

	public IntervalFunction(Range range, String name)
	{
		super(name);
		_range = range;
		int row = range.getBeginRow(), col = range.getBeginColumn();
		for (Cell c : _range.getCells())
		{
			if (c == null)
				c = new Cell(row, col, new NullContent());
			c.addFunc(this);
			if (row == range.getEndRow())
				col++;
			if (col == range.getEndColumn())
				row++;
		}
	}

	public String toString() 
	{
		return "=" + _name + "(" + this.toFuncArg() + ")";
	}

	public String showCont()
	{
		if (value() == null)
			update();
		return value().toString() + toString();
	}

	public String toFuncArg()
	{
		return _range.toString();
	}
}