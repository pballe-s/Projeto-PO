package xxl.core.exception;

public class IncorrectBinaryFunctionException extends Exception {
    private String _func;
    public IncorrectBinaryFunctionException(String functionName){
        _func = functionName;
    }
}
