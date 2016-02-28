package net.liujiacai.jcscheme.exception;

public class WrongNumberArgsException extends Exception {

    private String methodName;
    private int expected;
    private int actual;

    public WrongNumberArgsException(String methodName, int actual, int expected) {
        this.methodName = methodName;
        this.actual = actual;
        this.expected = expected;

    }

    @Override
    public String toString() {
        return methodName + " need " + actual + " params, but passed in " + expected;
    }
}

