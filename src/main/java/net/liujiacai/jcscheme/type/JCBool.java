package net.liujiacai.jcscheme.type;


public class JCBool extends JCObject {
    private static JCBool trueVal = new JCBool(true);
    private static JCBool falseVal = new JCBool(false);
    private boolean value;

    private JCBool(boolean value) {
        this.value = value;
    }

    public static JCBool getInstance(boolean bool) {
        if (bool) {
            return trueVal;
        } else {
            return falseVal;
        }
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
