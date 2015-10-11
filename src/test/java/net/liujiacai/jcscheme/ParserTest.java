package net.liujiacai.jcscheme;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;

public class ParserTest {

	private String src;

	@Test
	public void testTokenize() {
		src = "(list 1 2)";

		Assert.assertEquals("[(, list, 1, 2, )]",
				Arrays.toString(Parser.tokenize(src)));
	}
}
