package net.liujiacai.jcscheme;

import net.liujiacai.jcscheme.type.JCObject;

public class Util4Test {
    public static JCObject parseAndEval(String src, JCEnvironment env) {
        return JCParser.parse(JCParser.tokenize(src)).eval(env);
    }
}
