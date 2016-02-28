package net.liujiacai.jcscheme.builtin;

import net.liujiacai.jcscheme.type.JCBool;
import net.liujiacai.jcscheme.type.JCObject;
import net.liujiacai.jcscheme.type.JCString;

public class JCStringFuncs {

    public static JCBool equalTo(JCObject... args) {
        String before = ((JCString) args[0]).getValue();
        for (int i = 1; i < args.length; i++) {
            String current = ((JCString) args[i]).getValue();
            if (!current.equals(before)) {
                return JCBool.getInstance(false);
            }
            before = current;
        }
        return JCBool.getInstance(true);
    }
}
