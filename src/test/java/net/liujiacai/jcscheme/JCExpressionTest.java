package net.liujiacai.jcscheme;

import net.liujiacai.jcscheme.util.EnvUtil;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class JCExpressionTest {

    private static JCEnvironment rootEnv;
    private String src;
    private JCExpression program;

    @BeforeClass
    public static void initEnv() {
        rootEnv = EnvUtil.init();
    }

    @Test
    public void shouldReturnNullListGivenNil() {
        src = "nil";
        program = JCParser.parse(JCParser.tokenize(src));
        Assert.assertEquals("()", program.eval(rootEnv).toString());
    }
}
