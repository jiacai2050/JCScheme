package net.liujiacai.jcscheme;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Arrays;

public class JCParserTest {

    private String src;

    @Test
    public void shouldReturnList_1_2GivenList_1_2() {
        src = "(list 1 2)";

        Assert.assertEquals("[(, list, 1, 2, )]",
                Arrays.toString(JCParser.tokenize(src)));
    }
}
