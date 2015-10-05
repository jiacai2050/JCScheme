package net.liujiacai.jcscheme;

import org.junit.Assert;
import org.junit.Test;

public class SKeywordTest {

	private String src;
	private SExpression program;

	@Test
	public void testIfProcessor() {
		src = "(if (> 1 2) 1 2)";
		program = Parser.parse(Parser.tokenize(src));
		Assert.assertEquals("2", program.eval().toString());
	}
	@Test
	public void testListProcFor0Param() {
		src = "(list)";
		program = Parser.parse(Parser.tokenize(src));
		Assert.assertEquals(Constants.NIL, program.eval().toString());
	}
	@Test
	public void testListProcFor1Param() {
		src = "(list 1)";
		program = Parser.parse(Parser.tokenize(src));
		Assert.assertEquals("(1)", program.eval().toString());
	}
	@Test
	public void testConsProcFor2Param() {
		src = "(cons 1 2)";
		program = Parser.parse(Parser.tokenize(src));
		Assert.assertEquals("[1, 2]", program.eval().toString());
	}
	@Test
	public void testConsProcFor2NilParam() {
		src = "(cons 1 nil)";
		program = Parser.parse(Parser.tokenize(src));
		Assert.assertEquals("(1)", program.eval().toString());
	}
	@Test
	public void testConsProcForListParam() {
		src = "(cons 1 (list 2))";
		program = Parser.parse(Parser.tokenize(src));
		Assert.assertEquals("(1, 2)", program.eval().toString());
	}
}
