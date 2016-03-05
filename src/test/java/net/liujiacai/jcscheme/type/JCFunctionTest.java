package net.liujiacai.jcscheme.type;

import net.liujiacai.jcscheme.JCEnvironment;
import net.liujiacai.jcscheme.Util4Test;
import net.liujiacai.jcscheme.util.EnvUtil;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class JCFunctionTest {

	private String src;
    private static JCEnvironment rootEnv;


	@BeforeClass
	public static void initEnv() {
		rootEnv = EnvUtil.init();
	}

	@Test 
	public void shouldReturn1GivenF2() {
		Util4Test.parseAndEval("(def a1 1)", rootEnv);
		Util4Test.parseAndEval("(def f1 (lambda () a1))", rootEnv);
		Util4Test.parseAndEval("(def f2 (lambda () (def a1 2) (f1)))", rootEnv);
		Assert.assertEquals("1", Util4Test.parseAndEval("(f2)", rootEnv).toString());
		
	}
	@Test
	public void shouldReturnXGivenAdderParamList() {
		src = "(def adder (lambda (x) (lambda (y) (+ x y))))";
		Util4Test.parseAndEval(src, rootEnv);

		List<String> paramList = ((JCFunction) rootEnv.findVariable("adder").getRealValue()).getParam();
		Assert.assertArrayEquals(new String[] { "x" },
				paramList.toArray(new String[paramList.size()]));
	}
	@Test
	public void shouldReturn4GivenAdd2_2() {
		src = "(def adder (lambda (x) (lambda (y) (+ x y))))";
		Util4Test.parseAndEval(src, rootEnv);
		src = "(def add2 (adder 2))";
		Util4Test.parseAndEval(src, rootEnv);
		src = "(add2 2)";
		Assert.assertEquals("4", Util4Test.parseAndEval(src, rootEnv).toString());

		Set<String> variables = rootEnv.getEnv().keySet();
		Assert.assertTrue("adder is not in ENV", variables.contains("adder"));
		Assert.assertTrue("add2 is not in ENV", variables.contains("add2"));
		Assert.assertFalse("x is in ENV", variables.contains("x"));
	}
	
	@Test
	public void shouldReturn5GivenAdder_2_3() {
		src = "(def adder (lambda(x y) (+ x y)))";
		Util4Test.parseAndEval(src, rootEnv);
		src = "((adder 2) 3)";
		Assert.assertEquals("5", Util4Test.parseAndEval(src, rootEnv).toString());
	}
}
