package xxl.core;

import xxl.core.exception.AsIntException;
import xxl.core.exception.AsStringException;
import java.util.List;
import java.util.ArrayList;

// VERIFICAR BOOLEANO NO COMPUTE EM CADA FUNCAO

public abstract class Function extends Content implements Subscriber
{
	protected String _name;
	protected List<Cell> _cellDepend;
	protected Literal _value;

	public Function(String name)
	{
		_name = name;
		_cellDepend = new ArrayList<Cell>();

	}

	public void addFuncCell(Function func)
	{}

	public void update()
	{
		_value = compute();
	}

	protected abstract Literal compute();

	public String asString() throws AsStringException
	{
		return this.value().asString();
	}

	public int asInt() throws AsIntException
	{
		return this.value().asInt();
	}

	public Literal value()
	{
		if (_value == null)
			_value = compute();
		return _value;
	}
}