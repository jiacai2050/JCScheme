package net.liujiacai.jcscheme.type;

import net.liujiacai.jcscheme.JCEnvironment;
import net.liujiacai.jcscheme.Util4Test;
import net.liujiacai.jcscheme.util.EnvUtil;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class JCBoolTest {

    private static JCEnvironment rootEnv;
    private String src;

    @BeforeClass
    public static void initEnv() {
        rootEnv = EnvUtil.init();
    }

    @Test
    public void shouldReturnFalseGiven1gt2() {
        src = "(> 1 2)";
        Assert.assertFalse(((JCBool) Util4Test.parseAndEval(src, rootEnv)).getValue());
    }

    @Test
    public void shouldReturnFalseGiven1eq2() {
        src = "(= 1 2)";
        Assert.assertFalse(((JCBool) Util4Test.parseAndEval(src, rootEnv)).getValue());
    }

    @Test
    public void shouldReturnTrueGiven2gt1() {
        src = "(> 2 1)";
        Assert.assertTrue(((JCBool) Util4Test.parseAndEval(src, rootEnv)).getValue());
    }

}
