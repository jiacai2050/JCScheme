package net.liujiacai.jcscheme;

import java.util.ArrayList;
import java.util.List;

import net.liujiacai.jcscheme.type.SBool;
import net.liujiacai.jcscheme.type.SFunction;
import net.liujiacai.jcscheme.type.SNil;
import net.liujiacai.jcscheme.type.SNumber;
import net.liujiacai.jcscheme.type.SObject;
import net.liujiacai.jcscheme.type.SString;

public class SExpression {
	private String value;
	private SExpression parent;
	private List<SExpression> children;

	public SExpression(String value, SExpression parent) {
		this.value = value;
		this.parent = parent;
		this.children = new ArrayList<>();
	}

	public boolean addChild(SExpression child) {
		return this.children.add(child);
	}

	public List<SExpression> getChildren() {
		return children;
	}

	public SExpression getParent() {
		return this.parent;
	}

	public String getValue() {
		return value;
	}

	public SObject eval() {
		int length = children.size();
		if (length == 0) {
			/**
			 * step for eval a literal 
			 *  1. is number ?
			 *  2. is true or false ?
			 *  3. is NIL ?
			 *  4. is in current scope ? 
			 *  5. print error
			 */
			if (this.value.matches("\\d+")) {
				return new SNumber(Integer.valueOf(this.value));
			} else if (this.value.equals(Constants.TRUE) || this.value.equals(Constants.FALSE)) {
				return new SBool(Boolean.valueOf(this.value));
			} else if (this.value.equals(Constants.NIL)) {
				return SNil.getInstance();
			}  else if (this.value.startsWith("\"")) {
				return new SString(this.value);
			}else {
				SObject obj = SScope.current.findVariable(this.value);
				if(null != obj ) {
					return obj;
				} else {
					System.err.println("Error token: " + this.value);
					return null;
				}
			}
		} else if (length == 1) {
			return children.get(0).eval();
		} else {
			String op = children.get(0).getValue();
			// 1. process builtin keywords
			if (SScope.builtinKeywords.containsKey(op)) {
				String kwProcessor = SScope.builtinKeywords.get(op);
				return Util.builtinKeywordExecutor(kwProcessor, children);
			}
			// 2. process functions
			List<SExpression> argsExps = children.subList(1, length - 1);
			SObject[] argsSObjs = new SObject[length - 2];
			int argsIndex = 0; 
			for (SExpression exp : argsExps) {
				argsSObjs[argsIndex ++] = exp.eval();
			}
			SObject ret = null;
			// 2.1 process builtin functions
			if (SScope.builtinFuncs.containsKey(op)) {
				String func = SScope.builtinFuncs.get(op);
				return Util.builtinFuncExecutor(func, argsSObjs);
			} else if (Constants.START_TOKEN.equals(op)) {
				// 2.2 process anonymous functions
				return callAnonymousFun(argsSObjs);
			} else {
				// 2.3 process user-defined functions
				SFunction func = (SFunction)SScope.current.findVariable(op);
				if(null != func) {
					ret = func.apply(argsSObjs);
				} else {
					System.err.println("Unsupported Operator: " + op);
				}
			}
			return ret;
		}
	}
	private SObject callAnonymousFun(SObject[] param) {
		/**
		 * There are 2 cases:
		 * 1. immediately-invoked function: 
		 *      ((lambda (x) x) 2)  ==> return 2
		 * 2. currying:
		 * 	    (def adder (lambda(x y) (+ x y)))
		 *      ((adder 2) 3)        ==> return 5 		 
		 */
		SObject unknownObj = children.get(0).eval();
		if (unknownObj instanceof SFunction) {
			SFunction func = (SFunction) unknownObj;
			return func.apply(param);
		} else {
			return null;
		}
	}
	@Override
	public String toString() {
		if (0 == children.size()) {
			return value;
		} else {
			StringBuffer displayBuffer = new StringBuffer(value + " ");
			for (SExpression child : children) {
				displayBuffer.append(child.toString() + " ");
			}
			return displayBuffer.toString();
		}
	}
}
