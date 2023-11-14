package xxl.core;

import java.util.ArrayList;

import xxl.core.exception.AsIntException;

public class Min extends IntervalFunction
{
	public Min(Range range)
	{
		super(range, "MIN");
	}

	public Literal compute()
	{
		ArrayList<Cell> cells = (ArrayList<Cell>)_range.getCells();
		int j;
		LiteralInt i = null;

		for (Cell c : cells)
		{
			try
			{
				j = c.value().asInt();
				if ( i == null ||  j < i.asInt())
				{
					i = (LiteralInt)c.value();
				}
			}
			catch(AsIntException e)
			{
				continue;
			}
		}
		if (i == null)
			return new LiteralError();
		return i;
	}
}