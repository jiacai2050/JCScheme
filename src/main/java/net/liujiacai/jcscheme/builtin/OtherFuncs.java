package net.liujiacai.jcscheme.builtin;

import net.liujiacai.jcscheme.type.JCObject;

public class OtherFuncs {


    public static JCObject print(JCObject... args) {
        for (JCObject arg: args) {
            System.out.println(arg);
        }
        return null;
    }



}
