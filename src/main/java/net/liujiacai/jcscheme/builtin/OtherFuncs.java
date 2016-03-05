package net.liujiacai.jcscheme.builtin;

import net.liujiacai.jcscheme.JCTruck;
import net.liujiacai.jcscheme.type.JCObject;

public class OtherFuncs {


    public static JCObject print(JCTruck... args) {
        for (JCTruck arg: args) {
            System.out.println(arg.getRealValue());
        }
        return null;
    }



}
