package net.liujiacai.jcscheme;

import java.util.ArrayList;
import java.util.List;

import net.liujiacai.jcscheme.type.SBool;
import net.liujiacai.jcscheme.type.SFunction;
import net.liujiacai.jcscheme.type.SNumber;
import net.liujiacai.jcscheme.type.SObject;

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
			 * 在进行符号求值时，按照下面步骤： 1. 是否为数字 2. 是否为true false 3. 是否在全局环境中 4. 报错
			 */
			if (this.value.matches("\\d+")) {
				return new SNumber(Integer.valueOf(this.value));
			} else if (this.value.equals("true") || this.value.equals("false")) {
				return new SBool(Boolean.valueOf(this.value));
			} else if (SScope.env.containsKey(this.value)) {
				return SScope.env.get(this.value);
			} else {
				System.err.println("Error token: " + this.value);
				return null;
			}
		} else if (length == 1) {
			return children.get(0).eval();
		} else {
			String op = children.get(0).getValue();
			// 1. 处理语言内置关键字
			if (SScope.builtinKeywords.containsKey(op)) {
				String kwProcessor = SScope.builtinKeywords.get(op);
				return Util.builtinKeywordExecutor(kwProcessor, children);
			}
			// 2. 处理语言内置函数
			// children 第一个为操作符， 最后一个为右括号 )，所以需要去掉
			List<SExpression> expParams = children.subList(1, length - 1);
			List<SObject> objParams = new ArrayList<>();
			for (SExpression param : expParams) {
				objParams.add(param.eval());
			}
			SObject[] param = objParams.toArray(new SObject[length - 2]);
			SObject ret = null;
			if (SScope.builtinFuncs.containsKey(op)) {
				String func = SScope.builtinFuncs.get(op);
				return Util.builtinFuncExecutor(func, param);
			} else if (Constants.START_TOKEN.equals(op)) {
				// 3. 处理匿名函数调用
				List<SExpression> anonymousFuncExps = children.get(0).getChildren();
				if (Constants.LAMBDA.equals(anonymousFuncExps.get(0).getValue())) {
					SFunction func = (SFunction) SKeyword.lambdaProcessor(anonymousFuncExps);
					return func.apply(param);
				} else {
					return null;
				}
			} else {
				// 4. 处理用户自定义函数
				if (SScope.env.containsKey(op)) {
					ret = ((SFunction) SScope.env.get(op)).apply(param);
				} else {
					System.out.println("Unsupported Operator: " + op);
				}
			}
			return ret;
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
