package net.liujiacai.jcscheme.builtin;

import net.liujiacai.jcscheme.JCTruck;
import net.liujiacai.jcscheme.exception.IllegalExpressionException;
import net.liujiacai.jcscheme.exception.WrongNumberArgsException;
import net.liujiacai.jcscheme.type.JCList;
import net.liujiacai.jcscheme.type.JCObject;
import net.liujiacai.jcscheme.type.JCPair;
import net.liujiacai.jcscheme.util.JCConstants;

public class JCPairFuncs {

    public static JCPair cons(JCTruck... args) {
        if (args.length == 2) {
            JCObject fir = args[0].getRealValue();
            JCObject sec = args[1].getRealValue();
            if (sec instanceof JCList) {
                return new JCList(fir, sec);
            } else {
                return new JCPair(fir, sec);
            }
        } else {
            try {
                throw new IllegalExpressionException("illegal " + JCConstants.CONS +
                        " exp. (cons <first> <second>)");
            } catch (IllegalExpressionException e) {
                e.printStackTrace();

            } finally {
                System.exit(1);
                return null;
            }
        }

    }

    public static JCObject car(JCTruck... args) {
        if (args.length != 1) {
            try {
                throw new WrongNumberArgsException(JCConstants.CAR, 1, args.length);
            } catch (WrongNumberArgsException e) {
                e.printStackTrace();
            } finally {
                System.exit(1);
                return null;
            }
        } else {
            JCPair pair = (JCPair) args[0].getRealValue();
            return pair.getFirst();
        }


    }

    public static JCObject cdr(JCTruck... args) {
        if (args.length != 1) {
            try {
                throw new WrongNumberArgsException(JCConstants.CDR, 1, args.length);
            } catch (WrongNumberArgsException e) {
                e.printStackTrace();
            } finally {
                System.exit(1);
                return null;
            }
        } else {
            JCPair pair = (JCPair) args[0].getRealValue();
            return pair.getSecond();
        }

    }

}
