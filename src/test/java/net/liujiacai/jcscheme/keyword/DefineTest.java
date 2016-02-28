package net.liujiacai.jcscheme.keyword;

import net.liujiacai.jcscheme.JCEnvironment;
import net.liujiacai.jcscheme.Util4Test;
import net.liujiacai.jcscheme.util.EnvUtil;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class DefineTest {

    private static JCEnvironment rootEnv;
    private String src;

    @BeforeClass
    public static void initEnv() {
        rootEnv = EnvUtil.init();
    }

    @Test
    public void shouldReturn2GivenInc_1() {
        src = "(def (inc a) (+ 1 a))";
        Util4Test.parseAndEval(src, rootEnv);
        src = "(inc 1)";
        Assert.assertEquals("2", Util4Test.parseAndEval(src, rootEnv).toString());
    }
    @Test
    public void shouldReturn1GivenFoo() {
        src = "(def (foo) 1)";
        Util4Test.parseAndEval(src, rootEnv);
        src = "(foo)";
        Assert.assertEquals("1", Util4Test.parseAndEval(src, rootEnv).toString());
    }

}
