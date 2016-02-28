package net.liujiacai.jcscheme.type;

import net.liujiacai.jcscheme.JCEnvironment;
import net.liujiacai.jcscheme.Util4Test;
import net.liujiacai.jcscheme.util.EnvUtil;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class JCListTest {

    private static JCEnvironment rootEnv;
    private String src;

    @BeforeClass
    public static void initEnv() {
        rootEnv = EnvUtil.init();
    }


    @Test
    public void shouldReturnNullListGivenList() {
        src = "(list)";
        Assert.assertEquals("()", Util4Test.parseAndEval(src, rootEnv)
                .toString());
    }

    @Test
    public void shouldReturnList1GiveList1() {
        src = "(list 1)";
        Assert.assertEquals("(1)", Util4Test.parseAndEval(src, rootEnv).toString());
    }

    @Test
    public void shouldReturnPair1_2GivenCons_1_2() {
        src = "(cons 1 2)";
        Assert.assertEquals("[1, 2]", Util4Test.parseAndEval(src, rootEnv).toString());
    }

    @Test
    public void shouldReturnList1GivenCons_1_NIL() {
        src = "(cons 1 nil)";
        Assert.assertEquals("(1)", Util4Test.parseAndEval(src, rootEnv).toString());
    }

    @Test
    public void shouldReturnList_1_2GivenCons_1_List_2() {
        src = "(cons 1 (list 2))";
        Assert.assertEquals("(1, 2)", Util4Test.parseAndEval(src, rootEnv).toString());
    }

}
