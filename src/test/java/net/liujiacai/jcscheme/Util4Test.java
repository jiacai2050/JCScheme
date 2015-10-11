package net.liujiacai.jcscheme;

import net.liujiacai.jcscheme.type.SObject;

public class Util4Test {
	public static SObject parseAndEval(String src) {
		return Parser.parse(Parser.tokenize(src)).eval();
	}
}
