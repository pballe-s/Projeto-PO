package xxl.core;

public class Reference extends Content
{
	private int _row;
	private int _column;
	private Spreadsheet _sheet;

	public Reference(int row, int column, Spreadsheet sheet)
	{
		_row = row;
		_column = column;
		_sheet = sheet;
	}

	public String toString()
	{
		return "=" + Integer.toString(_row) + ";" + Integer.toString(_column);
	}

	public String toFuncArg()
	{
		return Integer.toString(_row) + ";" + Integer.toString(_column);
	}

	Literal value()
	{
		Cell[][] cells = _sheet.getMatrizCells();
		return cells[_row - 1][_column - 1].getContent().value();
	}

	public String showCont()
	{
		try {
			return value().toString() + toString();
		} catch(NullPointerException npe) {
			return "#VALUE" + toString();
		}
	}

	public void addFuncCell(Function func)
	{
		if (_sheet.getMatrizCells()[_row - 1][_column - 1] == null)
			_sheet.getMatrizCells()[_row - 1][_column - 1] = new Cell(_row, _column, new NullContent());
		_sheet.getMatrizCells()[_row - 1][_column - 1].addFunc(func);
	}
}

