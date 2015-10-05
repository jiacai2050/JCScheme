package net.liujiacai.jcscheme;

import net.liujiacai.jcscheme.type.SBool;

import org.junit.Assert;
import org.junit.Test;

public class SListTest {

	private String src;
	private SExpression program;

	@Test
	public void testCarFor0Param() {
		src = "(car (list))";
		program = Parser.parse(Parser.tokenize(src));
		Assert.assertNull(program.eval());
	}

	@Test
	public void testCarFor3Param() {
		src = "(car (list 1 2 3))";
		program = Parser.parse(Parser.tokenize(src));
		Assert.assertEquals("1", program.eval().toString());
	}

	@Test
	public void testCdrFor0Param() {
		src = "(cdr (list))";
		program = Parser.parse(Parser.tokenize(src));
		Assert.assertNull(program.eval());
	}

	@Test
	public void testCdrFor1Param() {
		src = "(cdr (list 1))";
		program = Parser.parse(Parser.tokenize(src));
		Assert.assertEquals(Constants.NIL, program.eval().toString());
	}

	@Test
	public void testCdrFor3Param() {
		src = "(cdr (list 1 2 3))";
		program = Parser.parse(Parser.tokenize(src));
		Assert.assertEquals("(2, 3)", program.eval().toString());
	}

	@Test
	public void testEmptyFor3List() {
		String src = "(empty? (list 1 2 3))";
		SExpression program = Parser.parse(Parser.tokenize(src));
		Assert.assertFalse(((SBool) program.eval()).getValue());
	}

	@Test
	public void testEmptyFor0List() {
		String src = "(empty? (list))";
		SExpression program = Parser.parse(Parser.tokenize(src));
		Assert.assertTrue(((SBool) program.eval()).getValue());
	}
	@Test
	public void testEmptyForNilList() {
		String src = "(empty? nil)";
		SExpression program = Parser.parse(Parser.tokenize(src));
		Assert.assertTrue(((SBool) program.eval()).getValue());
	}

}
