package net.liujiacai.jcscheme.keyword;

import net.liujiacai.jcscheme.JCEnvironment;
import net.liujiacai.jcscheme.JCExpression;
import net.liujiacai.jcscheme.type.JCFunction;
import net.liujiacai.jcscheme.type.JCObject;
import net.liujiacai.jcscheme.util.JCConstants;

import java.util.List;

public class Define {

    public static JCExpression getVariable(JCExpression exp) {
        List<JCExpression> children = exp.getChildren();

        return children.get(1);
    }

    public static JCExpression getValue(JCExpression exp) {
        List<JCExpression> children = exp.getChildren();

        return children.get(2);
    }

    public static JCObject apply(JCExpression exp, JCEnvironment env) {
        JCExpression varExp = getVariable(exp);
        if (varExp.getValue().equals(JCConstants.START_TOKEN)) {
            List<String> nameAndParams = Lambda.getParamList(varExp);
            String funcName = nameAndParams.get(0);
            List<String> params = nameAndParams.subList(1, nameAndParams.size());
            List<JCExpression> funcBody = Lambda.getBodyExps(exp);
            env.addVariable(funcName, new JCFunction(params, funcBody, env));

        } else {
            env.addVariable(varExp.getValue(), getValue(exp).eval(env));
        }

        return null;
    }
}
