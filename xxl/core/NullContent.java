package xxl.core;

public class NullContent extends Content{

    @Override
    public String toString() {
        return "";
    }

    @Override
    Literal value(){
        return null;
    }

    @Override
    public String showCont(){
        return "";
    }

    @Override
    public String toFuncArg() {
        return "";
    }

	public void addFuncCell(Function func)
	{}
}