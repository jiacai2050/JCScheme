package net.liujiacai.jcscheme.builtin;

import net.liujiacai.jcscheme.type.JCBool;
import net.liujiacai.jcscheme.type.JCObject;

public class JCBoolFuncs {

    public static JCBool and(JCObject... args) {
        for (JCObject arg : args) {
            JCBool bool = (JCBool) arg;
            if (! bool.getValue() ) {
                return JCBool.getInstance(false);
            }
        }
        return JCBool.getInstance(true);
    }


    public static JCBool or(JCObject... args) {
        for (JCObject arg : args) {
            JCBool bool = (JCBool) arg;
            if ( bool.getValue() ) {
                return JCBool.getInstance(true);
            }
        }
        return JCBool.getInstance(false);
    }

    public static JCBool not(JCObject arg) {
        JCBool bool = (JCBool) arg;
        if (bool.getValue()) {
            return JCBool.getInstance(false);
        } else {
            return JCBool.getInstance(true);
        }
    }

}
