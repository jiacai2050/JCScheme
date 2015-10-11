package net.liujiacai.jcscheme;

import net.liujiacai.jcscheme.type.SBool;

import org.junit.Assert;
import org.junit.Test;

public class SListTest {

	private String src;

	@Test
	public void testCarFor0Param() {
		src = "(car (list))";
		Assert.assertNull(Util4Test.parseAndEval(src));
	}

	@Test
	public void testCarFor3Param() {
		src = "(car (list 1 2 3))";
		Assert.assertEquals("1", Util4Test.parseAndEval(src).toString());
	}

	@Test
	public void testCdrFor0Param() {
		src = "(cdr (list))";
		Assert.assertNull(Util4Test.parseAndEval(src));
	}

	@Test
	public void testCdrFor1Param() {
		src = "(cdr (list 1))";
		Assert.assertEquals(Constants.NIL, Util4Test.parseAndEval(src)
				.toString());
	}

	@Test
	public void testCdrFor3Param() {
		src = "(cdr (list 1 2 3))";
		Assert.assertEquals("(2, 3)", Util4Test.parseAndEval(src).toString());
	}

	@Test
	public void testEmptyFor3List() {
		src = "(empty? (list 1 2 3))";
		Assert.assertFalse(((SBool) Util4Test.parseAndEval(src)).getValue());
	}

	@Test
	public void testEmptyFor0List() {
		src = "(empty? (list))";
		Assert.assertTrue(((SBool) Util4Test.parseAndEval(src)).getValue());
	}

	@Test
	public void testEmptyForNilList() {
		String src = "(empty? nil)";
		Assert.assertTrue(((SBool) Util4Test.parseAndEval(src)).getValue());
	}

}
