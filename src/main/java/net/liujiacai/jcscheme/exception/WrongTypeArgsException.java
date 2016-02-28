package net.liujiacai.jcscheme.exception;

public class WrongTypeArgsException extends Exception {

    private String methodName;
    private String expected;
    private String actual;

    public WrongTypeArgsException(String methodName, String actual, String expected) {
        this.methodName = methodName;
        this.actual = actual;
        this.expected = expected;

    }

    @Override
    public String toString() {
        return methodName + " need " + actual + " , but passed in " + expected;
    }
}

