package net.liujiacai.jcscheme.type;

import net.liujiacai.jcscheme.JCEnvironment;
import net.liujiacai.jcscheme.Util4Test;
import net.liujiacai.jcscheme.util.EnvUtil;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class JCStringTest {
    private static JCEnvironment rootEnv;
    private String src;

    @BeforeClass
    public static void initEnv() {
        rootEnv = EnvUtil.init();
    }

    @Test
    public void shouldReturnTrueGivenEq_1_1_1() {
        src = "(str=? \"1\" \"1\" \"1\")";
        JCBool isEq = (JCBool) Util4Test.parseAndEval(src, rootEnv);
        Assert.assertTrue(isEq.getValue());

    }
    @Test
    public void shouldReturnFalseGiveEq_1_2() {
        src = "(str=? \"1\" \"2\")";
        JCBool isEq = (JCBool) Util4Test.parseAndEval(src, rootEnv);
        Assert.assertFalse(isEq.getValue());

    }
    @Test
    public void shouldReturnTrueGivenFooBar() {
        src = "(def foo \"hello\")";
        Util4Test.parseAndEval(src, rootEnv);
        src = "(def bar \"hello\")";
        Util4Test.parseAndEval(src, rootEnv);
        src = "(str=? foo bar \"hello\")";
        JCBool isEq = (JCBool) Util4Test.parseAndEval(src, rootEnv);
        Assert.assertTrue(isEq.getValue());
    }
}
