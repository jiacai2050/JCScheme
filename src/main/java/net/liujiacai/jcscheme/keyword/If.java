package net.liujiacai.jcscheme.keyword;

import net.liujiacai.jcscheme.util.JCConstants;
import net.liujiacai.jcscheme.JCExpression;
import net.liujiacai.jcscheme.JCEnvironment;
import net.liujiacai.jcscheme.exception.IllegalExpressionException;
import net.liujiacai.jcscheme.type.JCBool;
import net.liujiacai.jcscheme.type.JCObject;

import java.util.List;

public class If {
    public static boolean isIf(JCExpression exp) {
        List<JCExpression> children = exp.getChildren();
        if(children.size() > 0) {
            if (children.get(0).getValue().equals(JCConstants.IF)) {
                if (children.size() >= 4) {
                    return true;
                } else {
                    try {
                        throw new IllegalExpressionException("illegal if exp. (if <cond> <seq> [<alter>])");
                    } catch (IllegalExpressionException e) {
                        e.printStackTrace();
                        System.exit(1);
                    }
                }
            }
        }
        return false;
    }
    public static JCExpression getCondition(JCExpression exp) {
        List<JCExpression> children = exp.getChildren();
        return children.get(1);
    }
    private static JCExpression getConsequence(JCExpression exp) {
        List <JCExpression> children = exp.getChildren();
        return children.get(2);
    }
    private static JCExpression getAlternation(JCExpression exp) {
        List<JCExpression> children = exp.getChildren();
        if (children.size() >= 4) {
            return children.get(3);
        } else {
            return null;
        }
    }

    public static JCObject apply(JCExpression exp, JCEnvironment env) {
        JCBool condition = (JCBool) getCondition(exp).eval(env);

        if (condition.getValue()) {
            return getConsequence(exp).eval(env);
        } else {
            JCExpression alter = getAlternation(exp);
            if (null == alter) {
                return null;
            } else {
                return alter.eval(env);
            }
        }

    }
}
