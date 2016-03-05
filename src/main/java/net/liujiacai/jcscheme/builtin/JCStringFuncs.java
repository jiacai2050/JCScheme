package net.liujiacai.jcscheme.builtin;

import net.liujiacai.jcscheme.JCTruck;
import net.liujiacai.jcscheme.type.JCBool;
import net.liujiacai.jcscheme.type.JCString;

public class JCStringFuncs {

    public static JCBool equalTo(JCTruck... args) {
        String before = ((JCString) args[0].getRealValue()).getValue();
        for (int i = 1; i < args.length; i++) {
            String current = ((JCString) args[i].getRealValue()).getValue();
            if (!current.equals(before)) {
                return JCBool.getInstance(false);
            }
            before = current;
        }
        return JCBool.getInstance(true);
    }
}
