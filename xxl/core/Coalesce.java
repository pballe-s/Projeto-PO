package xxl.core;

import java.util.List;

import xxl.core.exception.AsStringException;

public class Coalesce extends IntervalFunction
{
	public Coalesce(Range range)
	{
		super(range, "COALESCE");
	}

	protected Literal compute()
	{
		List<Cell> cells = _range.getCells();
		String coalesce = "";

		for (Cell c : cells)
		{
			try
			{
				if (c != null)
				{
					if (c.getContent().value() == null || c.getContent().value().toString() == "#VALUE")
						continue;
					coalesce += c.getContent().value().asString();
					return new LiteralString(coalesce);
				}
			}
			catch (AsStringException ex)
			{
				continue;
			}
		}
		return new LiteralString(coalesce);
	}
}