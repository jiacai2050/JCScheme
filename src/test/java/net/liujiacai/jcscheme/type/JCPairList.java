package net.liujiacai.jcscheme.type;

import net.liujiacai.jcscheme.JCEnvironment;
import net.liujiacai.jcscheme.Util4Test;
import net.liujiacai.jcscheme.util.EnvUtil;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class JCPairList {

    private static JCEnvironment rootEnv;
    private String src;

    @BeforeClass
    public static void initEnv() {
        rootEnv = EnvUtil.init();
    }

    @Test
    public void shouldReturnNullGivenCarList() {
        src = "(car (list))";
        Assert.assertNull(Util4Test.parseAndEval(src, rootEnv));
    }

    @Test
    public void shouldReturn1GivenList_1_2_3() {
        src = "(car (list 1 2 3))";
        Assert.assertEquals("1", Util4Test.parseAndEval(src, rootEnv).toString());
    }

    @Test
    public void shouldReturnNullGivenCdrList() {
        src = "(cdr (list))";
        Assert.assertNull(Util4Test.parseAndEval(src, rootEnv));
    }

    @Test
    public void shouldReturnNilGivenCarList_1() {
        src = "(cdr (list 1))";
        Assert.assertEquals("()", Util4Test.parseAndEval(src, rootEnv)
                .toString());
    }

    @Test
    public void shouldReturnList2_3GivenCdrList_1_2_3() {
        src = "(cdr (list 1 2 3))";
        Assert.assertEquals("(2, 3)", Util4Test.parseAndEval(src, rootEnv).toString());
    }

    @Test
    public void shouldReturnFalseGivenIsNullList_1_2_3() {
        src = "(null? (list 1 2 3))";
        Assert.assertFalse(((JCBool) Util4Test.parseAndEval(src, rootEnv)).getValue());
    }

    @Test
    public void shouldReturnTrueGivenisNullList() {
        src = "(null? (list))";
        Assert.assertTrue(((JCBool) Util4Test.parseAndEval(src, rootEnv)).getValue());
    }

    @Test
    public void shouldReturnTrueGivenNIL() {
        String src = "(null? nil)";
        Assert.assertTrue(((JCBool) Util4Test.parseAndEval(src, rootEnv)).getValue());
    }

}
