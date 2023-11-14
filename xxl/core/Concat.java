package xxl.core;

import java.util.List;

import xxl.core.exception.AsStringException;

public class Concat extends IntervalFunction
{
	public Concat(Range range)
	{
		super(range, "CONCAT");
	}

	protected Literal compute()
	{
		List<Cell> cells = _range.getCells();
		String concat = "\'";

		for (Cell c : cells)
		{
			try
			{
				if (c.getContent().value() == null || c.getContent().value().toString() == "#VALUE")
					return new LiteralError();
				concat += c.getContent().value().asString().split("\'")[1];
			}
			catch (AsStringException ex)
			{
				return null;
			}
		}
		return new LiteralString(concat);
	}
}