package net.liujiacai.jcscheme.builtin;

import net.liujiacai.jcscheme.JCTruck;
import net.liujiacai.jcscheme.exception.WrongNumberArgsException;
import net.liujiacai.jcscheme.type.JCBool;
import net.liujiacai.jcscheme.util.JCConstants;

public class JCBoolFuncs {

    public static JCBool and(JCTruck... args) {
        for (JCTruck arg : args) {
            JCBool bool = (JCBool) arg.getRealValue();
            if (!bool.getValue()) {
                return JCBool.getInstance(false);
            }
        }
        return JCBool.getInstance(true);
    }


    public static JCBool or(JCTruck... args) {
        for (JCTruck arg : args) {
            JCBool bool = (JCBool) arg.getRealValue();
            if (bool.getValue()) {
                return JCBool.getInstance(true);
            }
        }
        return JCBool.getInstance(false);
    }

    public static JCBool not(JCTruck... args) {
        if (args.length != 1) {
            try {
                throw new WrongNumberArgsException(JCConstants.NOT, 1, args.length);
            } catch (WrongNumberArgsException e) {
                e.printStackTrace();
            } finally {
                System.exit(1);
                return null;
            }
        } else {
            JCBool bool = (JCBool) args[0].getRealValue();
            if (bool.getValue()) {
                return JCBool.getInstance(false);
            } else {
                return JCBool.getInstance(true);
            }
        }

    }

}
