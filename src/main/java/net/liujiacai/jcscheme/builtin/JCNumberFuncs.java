package net.liujiacai.jcscheme.builtin;

import net.liujiacai.jcscheme.type.JCBool;
import net.liujiacai.jcscheme.type.JCNumber;
import net.liujiacai.jcscheme.type.JCObject;

public class JCNumberFuncs {

    public static JCNumber add(JCObject... args) {
        int sum = 0;
        for (JCObject arg : args) {
            JCNumber num = (JCNumber) arg;
            sum += num.getValue();
        }
        return new JCNumber(sum);
    }

    public static JCNumber sub(JCObject... args) {
        int left = ((JCNumber) args[0]).getValue();
        for (int i = 1; i < args.length; i++) {
            left -= ((JCNumber) args[i]).getValue();
        }
        return new JCNumber(left);
    }

    public static JCNumber mul(JCObject... args) {
        int product = ((JCNumber) args[0]).getValue();
        for (int i = 1; i < args.length; i++) {
            product *= ((JCNumber) args[i]).getValue();
        }
        return new JCNumber(product);
    }

    public static JCNumber div(JCObject... args) {
        int quotient = ((JCNumber) args[0]).getValue();
        for (int i = 1; i < args.length; i++) {
            quotient *= ((JCNumber) args[i]).getValue();
        }
        return new JCNumber(quotient);
    }

    public static JCBool equalTo(JCObject... args) {
        int before = ((JCNumber) args[0]).getValue();

        for (int i = 1; i < args.length; i++) {
            int current = ((JCNumber) args[i]).getValue();
            if (before != current) {
                return JCBool.getInstance(false);
            }
            before = current;
        }
        return JCBool.getInstance(true);
    }

    public static JCBool lessThan(JCObject... args) {
        int before = ((JCNumber) args[0]).getValue();

        for (int i = 1; i < args.length; i++) {
            int current = ((JCNumber) args[i]).getValue();
            if (before > current) {
                return JCBool.getInstance(false);
            }
            before = current;
        }
        return JCBool.getInstance(true);
    }

    public static JCBool greatThan(JCObject... args) {

        int before = ((JCNumber) args[0]).getValue();

        for (int i = 1; i < args.length; i++) {
            int current = ((JCNumber) args[i]).getValue();
            if (before < current) {
                return JCBool.getInstance(false);
            }
            before = current;
        }
        return JCBool.getInstance(true);
    }
}
