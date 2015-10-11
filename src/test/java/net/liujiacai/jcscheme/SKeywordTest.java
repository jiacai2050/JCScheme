package net.liujiacai.jcscheme;

import org.junit.Assert;
import org.junit.Test;

public class SKeywordTest {

	private String src;

	@Test
	public void testIfProcessor() {
		src = "(if (> 1 2) 1 2)";
		Assert.assertEquals("2", Util4Test.parseAndEval(src).toString());
	}

	@Test
	public void testListProcFor0Param() {
		src = "(list)";
		Assert.assertEquals(Constants.NIL, Util4Test.parseAndEval(src)
				.toString());
	}

	@Test
	public void testListProcFor1Param() {
		src = "(list 1)";
		Assert.assertEquals("(1)", Util4Test.parseAndEval(src).toString());
	}

	@Test
	public void testConsProcFor2Param() {
		src = "(cons 1 2)";
		Assert.assertEquals("[1, 2]", Util4Test.parseAndEval(src).toString());
	}

	@Test
	public void testConsProcFor2NilParam() {
		src = "(cons 1 nil)";
		Assert.assertEquals("(1)", Util4Test.parseAndEval(src).toString());
	}

	@Test
	public void testConsProcForListParam() {
		src = "(cons 1 (list 2))";
		Assert.assertEquals("(1, 2)", Util4Test.parseAndEval(src).toString());
	}
}
