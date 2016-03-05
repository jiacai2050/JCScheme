package net.liujiacai.jcscheme;

import net.liujiacai.jcscheme.util.EnvUtil;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class NormalOrderTest {
    private String src;
    private static JCEnvironment rootEnv;

    @BeforeClass
    public static void initEnv() {
        rootEnv = EnvUtil.init();
    }
    @Test
    public void shouldReturn0() {
        src = "(def (p) (p))";
        Util4Test.parseAndEval(src, rootEnv);
        src = "(def (test x y) (if (= x 0) 0 y))";
        Util4Test.parseAndEval(src, rootEnv);
        src = "(test 0 (p))";
        Assert.assertEquals("0", Util4Test.parseAndEval(src, rootEnv).toString());
    }
}
