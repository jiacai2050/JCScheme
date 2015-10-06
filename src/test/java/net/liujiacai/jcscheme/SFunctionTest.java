package net.liujiacai.jcscheme;

import java.util.List;
import java.util.Map;
import java.util.Set;

import net.liujiacai.jcscheme.type.SFunction;
import net.liujiacai.jcscheme.type.SObject;

import org.junit.Assert;
import org.junit.Test;

public class SFunctionTest {

	private String src;
	private SExpression program;

	@Test
	public void testClosure() {
		src = "(def adder (lambda (x) (lambda (y) (+ x y))))";
		program = Parser.parse(Parser.tokenize(src));
		program.eval();
		Map<String, SObject> env = SScope.current.getEnv();
		List<String> paramList = ((SFunction) env.get("adder")).getParam();
		Assert.assertArrayEquals(new String[] { "x" },
				paramList.toArray(new String[paramList.size()]));
	}
	@Test
	public void testClosureInvoke() {
		src = "(def adder (lambda (x) (lambda (y) (+ x y))))";
		program = Parser.parse(Parser.tokenize(src));
		program.eval();
		src = "(def add2 (adder 2))";
		program = Parser.parse(Parser.tokenize(src));
		program.eval();
		src = "(add2 2)";
		program = Parser.parse(Parser.tokenize(src));
		Assert.assertEquals("4", program.eval().toString());
		src = "(def add2_2 (adder 2))";
		program = Parser.parse(Parser.tokenize(src));
		program.eval();
		src = "(add2_2 3)";
		program = Parser.parse(Parser.tokenize(src));
		Assert.assertEquals("5", program.eval().toString());
		Set<String> variables = SScope.current.getEnv().keySet();
		Assert.assertTrue("adder is not in ENV", variables.contains("adder"));
		Assert.assertTrue("add2_2 is not in ENV", variables.contains("add2_2"));
		Assert.assertTrue("add2 is not in ENV", variables.contains("add2"));
		Assert.assertFalse("x is in ENV", variables.contains("x"));
	}
	
	@Test
	public void testCurryingInvoke() {
		src = "(def adder (lambda(x y) (+ x y)))";
		program = Parser.parse(Parser.tokenize(src));
		program.eval();
		src = "((adder 2) 3)";
		program = Parser.parse(Parser.tokenize(src));
		SObject ret = program.eval();
		Assert.assertEquals("5", ret.toString());
		
	}
}
