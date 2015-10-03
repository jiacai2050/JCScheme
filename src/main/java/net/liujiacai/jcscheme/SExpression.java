package net.liujiacai.jcscheme;

import java.util.ArrayList;
import java.util.List;

import net.liujiacai.jcscheme.type.SBool;
import net.liujiacai.jcscheme.type.SFunction;
import net.liujiacai.jcscheme.type.SNumber;
import net.liujiacai.jcscheme.type.SObject;
import net.liujiacai.jcscheme.type.SScope;

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
			 * 在进行符号求值时，按照下面步骤： 1. 是否为数字，否则 2. 是否在全局环境中 3. 是否为true false 4. 报错
			 */
			if (this.value.matches("\\d+")) {
				return new SNumber(Integer.valueOf(this.value));
			} else if (SScope.env.containsKey(this.value)) {
				return SScope.env.get(this.value);
			} else if (this.value.equals("true") || this.value.equals("false")) {
				return new SBool(Boolean.valueOf(this.value));
			} else {
				System.err.println("Error token: " + this.value);
				return null;
			}
		} else if (length == 1) {
			return children.get(0).eval();
		} else {
			String op = children.get(0).getValue();
			// 处理语言内置操作符
			switch (op) {
			case Op.DEF:
				String key = children.get(1).value;
				String unknownVal = children.get(2).value;
				if (unknownVal.startsWith(Constants.START_TOKEN)) {
					List<SExpression> funcExps = children.get(2).getChildren();
					// 是否为函数定义
					if ("lambda".equals(funcExps.get(0).value)) {
						SExpression funcArgsExp = funcExps.get(1);
						SExpression funcBodyExp = funcExps.get(2);

						List<SExpression> args = funcArgsExp.getChildren();
						// SExpression 最后一个为右括号，忽略
						args = args.subList(0, args.size() - 1);
						List<String> params = new ArrayList<>();
						for (SExpression e : args) {
							params.add(e.value);
						}
						SFunction func = new SFunction(params, funcBodyExp);
						SScope.env.put(key, func);
						return null;
					} else {
						SScope.env.put(key, children.get(2).eval());
						return null;
					}
				} else {
					SNumber val = new SNumber(Integer.valueOf(unknownVal));
					SScope.env.put(key, val);
					return null;
				}
			case Op.IF:
				SBool condition = (SBool) children.get(1).eval();
				SExpression trueClause = children.get(2);
				if (condition.getValue()) {
					return trueClause.eval();
				} else {
					if (children.size() == 5) {
						SExpression falseClause = children.get(3);
						return falseClause.eval();
					} else {
						return null;
					}
				}
			}
			// children 第一个为操作符， 最后一个为右括号 )
			List<SExpression> expParams = children.subList(1, length - 1);
			List<SObject> objParams = new ArrayList<>();
			for (SExpression param : expParams) {
				objParams.add(param.eval());
			}
			SObject[] param = objParams.toArray(new SObject[length - 2]);
			SObject ret = null;
			switch (op) {
			case Op.ADD:
				ret = Op.add(param);
				break;
			case Op.SUB:
				ret = Op.sub(param);
				break;
			case Op.MUL:
				ret = Op.mul(param);
				break;
			case Op.DIV:
				ret = Op.div(param);
				break;
			case Op.GT:
				ret = Op.greatThan(param);
				break;
			case Op.LT:
				ret = Op.lessThan(param);
				break;
			case Op.EQ:
				ret = Op.equal(param);
				break;
			default:
				// 用户自定义函数
				if (SScope.env.containsKey(op)) {
					ret = ((SFunction) SScope.env.get(op)).apply(param);
				} else {
					System.out.println("Unsupported Operator: " + op);
				}
				break;
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
