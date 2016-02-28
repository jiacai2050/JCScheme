package net.liujiacai.jcscheme.util;

import net.liujiacai.jcscheme.JCEnvironment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EnvUtil {

    public static Map<String, String> builtinFuncs;

    public static Set<String> builtinKeywords;

    static {
        builtinFuncs = new HashMap<>();
        builtinKeywords  = new HashSet<>();

        // number
        builtinFuncs.put(JCConstants.ADD, "net.liujiacai.jcscheme.builtin.JCNumberFuncs.add");
        builtinFuncs.put(JCConstants.SUB, "net.liujiacai.jcscheme.builtin.JCNumberFuncs.sub");
        builtinFuncs.put(JCConstants.MUL, "net.liujiacai.jcscheme.builtin.JCNumberFuncs.mul");
        builtinFuncs.put(JCConstants.DIV, "net.liujiacai.jcscheme.builtin.JCNumberFuncs.div");
        builtinFuncs.put(JCConstants.EQ, "net.liujiacai.jcscheme.builtin.JCNumberFuncs.equalTo");
        builtinFuncs.put(JCConstants.GT, "net.liujiacai.jcscheme.builtin.JCNumberFuncs.greatThan");
        builtinFuncs.put(JCConstants.LT, "net.liujiacai.jcscheme.builtin.JCNumberFuncs.lessThan");
        // pair list
        builtinFuncs.put(JCConstants.CONS, "net.liujiacai.jcscheme.builtin.JCPairFuncs.cons");
        builtinFuncs.put(JCConstants.CAR, "net.liujiacai.jcscheme.builtin.JCPairFuncs.car");
        builtinFuncs.put(JCConstants.CDR, "net.liujiacai.jcscheme.builtin.JCPairFuncs.cdr");
        builtinFuncs.put(JCConstants.LIST, "net.liujiacai.jcscheme.builtin.JCListFuncs.list");
        builtinFuncs.put(JCConstants.NULL, "net.liujiacai.jcscheme.builtin.JCListFuncs.isNull");
        // string
        builtinFuncs.put(JCConstants.STR_EQ, "net.liujiacai.jcscheme.builtin.JCStringFuncs.equalTo");
        // bool
        builtinFuncs.put(JCConstants.AND, "net.liujiacai.jcscheme.builtin.JCBoolFuncs.and");
        builtinFuncs.put(JCConstants.OR, "net.liujiacai.jcscheme.builtin.JCBoolFuncs.or");
        builtinFuncs.put(JCConstants.NOT, "net.liujiacai.jcscheme.builtin.JCBoolFuncs.not");
        // helper
        builtinFuncs.put(JCConstants.PRINT, "net.liujiacai.jcscheme.builtin.OtherFuncs.print");

        // keyword
        builtinKeywords.add(JCConstants.DEF);
        builtinKeywords.add(JCConstants.LAMBDA);
        builtinKeywords.add(JCConstants.IF);
    }

    public static JCEnvironment init() {

        JCEnvironment rootEnv = new JCEnvironment(null);

        return rootEnv;
    }
}
