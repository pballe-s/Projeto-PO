package xxl.core.exception;

public class IncorrectIntervalFunctionException extends Exception {
    private String _func;
    public IncorrectIntervalFunctionException(String functionName){
        _func = functionName;
    }
    
}
