package net.liujiacai.jcscheme.builtin;

import net.liujiacai.jcscheme.util.JCConstants;
import net.liujiacai.jcscheme.exception.WrongNumberArgsException;
import net.liujiacai.jcscheme.type.JCBool;
import net.liujiacai.jcscheme.type.JCList;
import net.liujiacai.jcscheme.type.JCObject;

public class JCListFuncs {

    public static JCBool isNull(JCObject... args) {
        if (args.length != 1) {
            try {
                throw new WrongNumberArgsException(JCConstants.NULL, 1, args.length);
            } catch (WrongNumberArgsException e) {
                e.printStackTrace();
            } finally {
                System.exit(1);
                return null;
            }
        } else {
            JCList list = (JCList) args[0];

            if (list != null && list.toString().equals(JCList.getNullList().toString())) {
                return JCBool.getInstance(true);
            } else {
                return JCBool.getInstance(false);
            }
        }

    }

    public static JCList list(JCObject... args) {

        if (args.length == 0) {
            return JCList.getNullList();
        } else {
            JCList last = new JCList(args[args.length - 1], JCList.getNullList());
            for (int i = args.length - 2; i >= 0; i--) {
                last = new JCList(args[i], last);
            }
            return last;
        }

    }
}
