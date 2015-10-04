package net.liujiacai.jcscheme;

import java.util.HashMap;
import java.util.Map;

import net.liujiacai.jcscheme.type.SObject;

public class SScope {
	// 全局环境
	public static Map<String, SObject> env = new HashMap<String, SObject>();

	public static Map<String, String> builtinFuncs = new HashMap<String, String>();
	public static Map<String, String> builtinKeywords = new HashMap<String, String>();

	static {
		builtinFuncs.put(Constants.ADD,
				"net.liujiacai.jcscheme.type.SNumber.add");
		builtinFuncs.put(Constants.SUB,
				"net.liujiacai.jcscheme.type.SNumber.sub");
		builtinFuncs.put(Constants.MUL,
				"net.liujiacai.jcscheme.type.SNumber.mul");
		builtinFuncs.put(Constants.DIV,
				"net.liujiacai.jcscheme.type.SNumber.div");
		builtinFuncs.put(Constants.GT,
				"net.liujiacai.jcscheme.type.SBool.greatThan");
		builtinFuncs.put(Constants.LT,
				"net.liujiacai.jcscheme.type.SBool.lessThan");
		builtinFuncs.put(Constants.EQ,
				"net.liujiacai.jcscheme.type.SBool.equalTo");

		builtinKeywords.put(Constants.IF,
				"net.liujiacai.jcscheme.SKeyword.ifProcessor");
		builtinKeywords.put(Constants.DEF,
				"net.liujiacai.jcscheme.SKeyword.defProcessor");
		builtinKeywords.put(Constants.LAMBDA,
				"net.liujiacai.jcscheme.SKeyword.lambdaProcessor");
	}
}
