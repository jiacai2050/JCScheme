package net.liujiacai.jcscheme.keyword;

import net.liujiacai.jcscheme.JCEnvironment;
import net.liujiacai.jcscheme.Util4Test;
import net.liujiacai.jcscheme.util.EnvUtil;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class IfTest {

	private String src;
	private static JCEnvironment rootEnv;


	@BeforeClass
	public static void initEnv() {
		rootEnv = EnvUtil.init();
	}
	@Test
	public void shouldReturn2GiveIf1gt2_1_2() {
		src = "(if (> 1 2) 1 2)";
		Assert.assertEquals("2", Util4Test.parseAndEval(src, rootEnv).toString());
	}

}
