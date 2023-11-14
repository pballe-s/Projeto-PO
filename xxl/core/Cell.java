package xxl.core;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class Cell implements Serializable{
	private static final long serialVersionUID = 80085797949L;
    private int _row;
    private int _column;
    private Content _content;
	private List<Subscriber> _funcDepend;

    public Cell(int row, int column, Content content){
        _row = row;
        _column = column;
        _content = content;
		_funcDepend = new ArrayList<Subscriber>();
    }
    public String toString(){
			return "" + _row + ";" + _column + "|" + _content.showCont();
        
    }
    void setContent(Content c)
	{
        _content = c;
		notifyFuncs();
    }
    Literal value(){
        return _content.value();
    }

	public int getRow()
	{
		return _row;
	}

	public int getColumn()
	{
		return _column;
	}

	public Content getContent()
	{
		return _content;
	}

	public void notifyFuncs()
	{
		if (_funcDepend != null)
		{
			for (Subscriber s : _funcDepend)
			{
				s.update();
			}
		}
		
	}

	public void addFunc(Subscriber s)
	{
		_funcDepend.add(s);
	}

	public void remFunc(Subscriber s)
	{
		_funcDepend.remove(s);
	}
}