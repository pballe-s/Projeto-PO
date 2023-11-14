package xxl.core;

import java.util.List;

import xxl.core.exception.AsIntException;

public class Average extends IntervalFunction
{
	public Average(Range range)
	{
		super(range, "AVERAGE");
	}

	protected Literal compute()
	{
		List<Cell> cells = _range.getCells();
		int acumulator = 0;

		for (Cell c : cells)
		{
			try
			{
				if (c.getContent().value() == null || c.getContent().value().toString() == "#VALUE")
					return new LiteralError();
				acumulator += c.getContent().value().asInt();
			}
			catch(AsIntException ex)
			{
				return null;
			}
		}
		return new LiteralInt(acumulator / cells.size());
	}
}