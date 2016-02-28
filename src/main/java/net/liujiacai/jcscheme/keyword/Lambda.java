package net.liujiacai.jcscheme.keyword;

import net.liujiacai.jcscheme.JCEnvironment;
import net.liujiacai.jcscheme.JCExpression;
import net.liujiacai.jcscheme.exception.IllegalExpressionException;
import net.liujiacai.jcscheme.type.JCFunction;
import net.liujiacai.jcscheme.type.JCObject;
import net.liujiacai.jcscheme.util.JCConstants;

import java.util.ArrayList;
import java.util.List;

public class Lambda {
    public static boolean isLambda(JCExpression exp) {
        List<JCExpression> children = exp.getChildren();
        if (children.size() > 0) {
            if (children.get(0).getValue().equals(JCConstants.LAMBDA)) {
                if (children.size() >= 4) {
                    return true;
                } else {
                    try {
                        throw new IllegalExpressionException("illegal LAMBDA exp. (lambda (param) (action1)" +
                                " (action2) ... )");
                    } catch (IllegalExpressionException e) {
                        e.printStackTrace();
                        System.exit(1);
                    }
                }

            }
        }
        return false;
    }

    public static JCExpression getParamExp(JCExpression exp) {
        List<JCExpression> children = exp.getChildren();
        return children.get(1);
    }

    public static List<JCExpression> getBodyExps(JCExpression exp) {
        List<JCExpression> children = exp.getChildren();
        return children.subList(2, children.size() - 1);
    }

    public static List<String> getParamList(JCExpression paramExp) {
        List<JCExpression> children = paramExp.getChildren();
        List<JCExpression> paramExps = children.subList(0, children.size() - 1);

        List<String> params = new ArrayList<>();
        for (JCExpression param : paramExps) {
            params.add(param.getValue());
        }
        return params;

    }

    public static JCObject apply(JCExpression exp, JCEnvironment env) {

        JCFunction func = new JCFunction(getParamList(getParamExp(exp)), getBodyExps(exp), new JCEnvironment(
                env));
        return func;
    }
}

