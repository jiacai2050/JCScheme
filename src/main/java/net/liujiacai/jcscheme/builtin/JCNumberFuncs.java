package net.liujiacai.jcscheme.builtin;

import net.liujiacai.jcscheme.JCTruck;
import net.liujiacai.jcscheme.type.JCBool;
import net.liujiacai.jcscheme.type.JCNumber;

public class JCNumberFuncs {

    public static JCNumber add(JCTruck... args) {
        int sum = 0;
        for (JCTruck arg : args) {
            JCNumber num = (JCNumber) arg.getRealValue();
            sum += num.getValue();
        }
        return new JCNumber(sum);
    }

    public static JCNumber sub(JCTruck... args) {
        int left = ((JCNumber) args[0].getRealValue()).getValue();
        for (int i = 1; i < args.length; i++) {
            left -= ((JCNumber) args[i].getRealValue()).getValue();
        }
        return new JCNumber(left);
    }

    public static JCNumber mul(JCTruck... args) {
        int product = ((JCNumber) args[0].getRealValue()).getValue();
        for (int i = 1; i < args.length; i++) {
            product *= ((JCNumber) args[i].getRealValue()).getValue();
        }
        return new JCNumber(product);
    }

    public static JCNumber div(JCTruck... args) {
        int quotient = ((JCNumber) args[0].getRealValue()).getValue();
        for (int i = 1; i < args.length; i++) {
            quotient *= ((JCNumber) args[i].getRealValue()).getValue();
        }
        return new JCNumber(quotient);
    }

    public static JCBool equalTo(JCTruck... args) {
        int before = ((JCNumber) args[0].getRealValue()).getValue();

        for (int i = 1; i < args.length; i++) {
            int current = ((JCNumber) args[i].getRealValue()).getValue();
            if (before != current) {
                return JCBool.getInstance(false);
            }
            before = current;
        }
        return JCBool.getInstance(true);
    }

    public static JCBool lessThan(JCTruck... args) {
        int before = ((JCNumber) args[0].getRealValue()).getValue();

        for (int i = 1; i < args.length; i++) {
            int current = ((JCNumber) args[i].getRealValue()).getValue();
            if (before > current) {
                return JCBool.getInstance(false);
            }
            before = current;
        }
        return JCBool.getInstance(true);
    }

    public static JCBool greatThan(JCTruck... args) {

        int before = ((JCNumber) args[0].getRealValue()).getValue();

        for (int i = 1; i < args.length; i++) {
            int current = ((JCNumber) args[i].getRealValue()).getValue();
            if (before < current) {
                return JCBool.getInstance(false);
            }
            before = current;
        }
        return JCBool.getInstance(true);
    }
}
