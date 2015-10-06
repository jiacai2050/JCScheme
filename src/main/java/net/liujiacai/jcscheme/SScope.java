package net.liujiacai.jcscheme;

import java.util.HashMap;
import java.util.Map;

import net.liujiacai.jcscheme.type.SObject;

public class SScope {
	private SScope parent;
	private Map<String, SObject> env;

	public SScope getParent() {
		return parent;
	}

	public Map<String, SObject> getEnv() {
		return env;
	}

	public SObject findVariable(String var) {
		if (env.containsKey(var)) {
			return env.get(var);
		} else {
			SScope p = this.getParent();
			while (p != null) {
				Map<String, SObject> subEnv = p.getEnv();
				if (subEnv.containsKey(var)) {
					return subEnv.get(var);
				}
				p = p.getParent();
			}
			return null;
		}
	}

	public static SScope current = null;
	public static Map<String, String> builtinFuncs = new HashMap<String, String>();
	public static Map<String, String> builtinKeywords = new HashMap<String, String>();

	static {
		current = new SScope(null);

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
		builtinFuncs.put(Constants.empty,
				"net.liujiacai.jcscheme.type.SList.isEmpty");
		builtinFuncs
				.put(Constants.CAR, "net.liujiacai.jcscheme.type.SPair.car");
		builtinFuncs
				.put(Constants.CDR, "net.liujiacai.jcscheme.type.SPair.cdr");

		builtinKeywords.put(Constants.IF,
				"net.liujiacai.jcscheme.SKeyword.ifProcessor");
		builtinKeywords.put(Constants.DEF,
				"net.liujiacai.jcscheme.SKeyword.defProcessor");
		builtinKeywords.put(Constants.LAMBDA,
				"net.liujiacai.jcscheme.SKeyword.lambdaProcessor");
		builtinKeywords.put(Constants.CONS,
				"net.liujiacai.jcscheme.SKeyword.consProcessor");
		builtinKeywords.put(Constants.LIST,
				"net.liujiacai.jcscheme.SKeyword.listProcessor");
	}

	public SScope(SScope parent) {
		this.parent = parent;
		this.env = new HashMap<String, SObject>();
	}

}
