package xxl.core;

import java.util.ArrayList;
import java.io.Serializable;

public class CutBuffer implements Serializable
{
	private static final long serialVersionUID = 202311234567L;

	private ArrayList<Cell> _cells;
	private String _orientacao;

	public CutBuffer(ArrayList<Cell> cells, String orientacao){
		_cells = cells;
		_orientacao = orientacao;
	}

	public ArrayList<Cell> getCutBuffer()
	{
		return _cells;
	}

	public String getOrientacao()
	{
		return _orientacao;
	}
}