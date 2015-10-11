package net.liujiacai.jcscheme.type;

import net.liujiacai.jcscheme.Util4Test;

import org.junit.Assert;
import org.junit.Test;

public class SStringTest {
	private String src;

	@Test
	public void testStrEq() {
		src = "(str=? \"1\" \"1\" \"1\")";
		SBool isEq =(SBool)Util4Test.parseAndEval(src);
		Assert.assertTrue(isEq.getValue());
		src = "(str=? \"1\" \"2\")";
		isEq =(SBool)Util4Test.parseAndEval(src);
		Assert.assertFalse(isEq.getValue());

		src = "(def foo \"hello\")";
		Util4Test.parseAndEval(src);
		src = "(def bar \"hello\")";
		Util4Test.parseAndEval(src);
		src = "(str=? foo bar \"hello\")";
		isEq =(SBool)Util4Test.parseAndEval(src);
		Assert.assertTrue(isEq.getValue());
	}
}
