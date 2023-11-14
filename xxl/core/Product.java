package xxl.core;

import xxl.core.exception.AsIntException;

public class Product extends IntervalFunction
{
	public Product(Range range)
	{
		super(range, "PRODUCT");
	}

	protected Literal compute()
	{
		int acumulator = 1;

		for (Cell c : _range.getCells())
		{
			try
			{
				if (c.getContent().value() == null || c.getContent().value().toString() == "#VALUE")
					return new LiteralError();
				acumulator = acumulator * c.getContent().value().asInt();
			}
			catch (AsIntException ex)
			{
				return new LiteralError();
			}
		}
		return new LiteralInt(acumulator);
	}
}