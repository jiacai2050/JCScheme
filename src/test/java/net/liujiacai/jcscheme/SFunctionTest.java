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
	@Test 
	public void testApply() {
		Util4Test.parseAndEval("(def a1 1)");
		Util4Test.parseAndEval("(def f1 (lambda () a1))");
		Util4Test.parseAndEval("(def f2 (lambda () (def a1 2) (f1)))");
		Assert.assertEquals("1", Util4Test.parseAndEval("(f2)").toString());;
		
	}
	@Test
	public void testClosure() {
		src = "(def adder (lambda (x) (lambda (y) (+ x y))))";
		Util4Test.parseAndEval(src);
		Map<String, SObject> env = SScope.current.getEnv();
		List<String> paramList = ((SFunction) env.get("adder")).getParam();
		Assert.assertArrayEquals(new String[] { "x" },
				paramList.toArray(new String[paramList.size()]));
	}
	@Test
	public void testClosureInvoke() {
		src = "(def adder (lambda (x) (lambda (y) (+ x y))))";
		Util4Test.parseAndEval(src);
		src = "(def add2 (adder 2))";
		Util4Test.parseAndEval(src);
		src = "(add2 2)";
		Assert.assertEquals("4", Util4Test.parseAndEval(src).toString()); 

		src = "(def add2_2 (adder 2))";
		Util4Test.parseAndEval(src);
		src = "(add2_2 3)";
		Assert.assertEquals("5", Util4Test.parseAndEval(src).toString()); 

		Set<String> variables = SScope.current.getEnv().keySet();
		Assert.assertTrue("adder is not in ENV", variables.contains("adder"));
		Assert.assertTrue("add2_2 is not in ENV", variables.contains("add2_2"));
		Assert.assertTrue("add2 is not in ENV", variables.contains("add2"));
		Assert.assertFalse("x is in ENV", variables.contains("x"));
	}
	
	@Test
	public void testCurryingInvoke() {
		src = "(def adder (lambda(x y) (+ x y)))";
		Util4Test.parseAndEval(src);
		src = "((adder 2) 3)";
		Assert.assertEquals("5", Util4Test.parseAndEval(src).toString()); 
	}
}
