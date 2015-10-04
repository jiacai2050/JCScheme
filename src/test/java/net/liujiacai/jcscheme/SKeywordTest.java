package net.liujiacai.jcscheme;

import net.liujiacai.jcscheme.type.SNumber;

import org.junit.Assert;
import org.junit.Test;

public class SKeywordTest {

	@Test
	public void ifProcessorTest() {
		String src = "(if (> 1 2) 1 2)";
		SExpression program = Parser.parse(Parser.tokenize(src));
		Assert.assertEquals(2, ((SNumber)program.eval()).getValue());
	}
}
