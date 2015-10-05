package net.liujiacai.jcscheme;

import org.junit.Assert;
import org.junit.Test;

public class SExpressionTest {

	private String src;
	private SExpression program;

	@Test
	public void testEvalForNil() {
		src = "nil";
		program = Parser.parse(Parser.tokenize(src));
		Assert.assertEquals(Constants.NIL, program.eval().toString());
	}
}
