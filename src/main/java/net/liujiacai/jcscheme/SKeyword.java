package net.liujiacai.jcscheme;

import java.util.ArrayList;
import java.util.List;

import net.liujiacai.jcscheme.type.SBool;
import net.liujiacai.jcscheme.type.SFunction;
import net.liujiacai.jcscheme.type.SNumber;
import net.liujiacai.jcscheme.type.SObject;

public class SKeyword {
	/**
	 * JCScheme 中关键字的语义逻辑
	 */

	public static SObject ifProcessor(List<SExpression> children) {
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

	public static SObject defProcessor(List<SExpression> children) {
		// def 关键字默认返回 null
		String key = children.get(1).getValue();
		String unknownVal = children.get(2).getValue();
		if (unknownVal.startsWith(Constants.START_TOKEN)) {
			List<SExpression> funcExps = children.get(2).getChildren();
			// 是否为函数定义
			if (Constants.LAMBDA.equals(funcExps.get(0).getValue())) {
				SFunction func = (SFunction) lambdaProcessor(funcExps);
				SScope.env.put(key, func);
			} else {
				SScope.env.put(key, children.get(2).eval());
			}
		} else {
			SNumber val = new SNumber(Integer.valueOf(unknownVal));
			SScope.env.put(key, val);
		}
		return null;
	}

	public static SObject lambdaProcessor(List<SExpression> children) {

		SExpression funcArgsExp = children.get(1);
		SExpression funcBodyExp = children.get(2);

		List<SExpression> args = funcArgsExp.getChildren();
		// SExpression 最后一个为右括号，忽略
		args = args.subList(0, args.size() - 1);
		List<String> params = new ArrayList<>();
		for (SExpression e : args) {
			params.add(e.getValue());
		}
		SFunction func = new SFunction(params, funcBodyExp);
		return func;
	}
}
