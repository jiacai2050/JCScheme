package net.liujiacai.jcscheme.keyword;

import net.liujiacai.jcscheme.JCEnvironment;
import net.liujiacai.jcscheme.JCExpression;
import net.liujiacai.jcscheme.JCTruck;
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

            // construct lambda expression by itself
            JCExpression lambdaExp = new JCExpression(JCConstants.START_TOKEN, null);
            List<JCExpression> lambdaChildren = lambdaExp.getChildren();

            // add param expression
            lambdaChildren.add(new JCExpression(JCConstants.LAMBDA, lambdaExp));
            lambdaChildren.add(buildFuncParamExp(lambdaExp, varExp));

            // add body expression
            List<JCExpression> funcBody = Lambda.getBodyExps(exp);
            for (JCExpression bodyExp : funcBody) {
                lambdaChildren.add(bodyExp);
            }

            lambdaChildren.add(new JCExpression(")", lambdaExp));

            List<String> nameAndParams = Lambda.getParamList(varExp);
            String funcName = nameAndParams.get(0);
            env.addVariable(funcName, new JCTruck(lambdaExp, env));

        } else {
            env.addVariable(varExp.getValue(), new JCTruck(getValue(exp), env));
        }

        return null;
    }

    private static JCExpression buildFuncParamExp(JCExpression lambdaExp, JCExpression varExp) {
        JCExpression paramExp = new JCExpression(JCConstants.START_TOKEN, lambdaExp);
        List<String> nameAndParams = Lambda.getParamList(varExp);
        List<String> params = nameAndParams.subList(1, nameAndParams.size());
        for (String param : params) {
            paramExp.getChildren().add(new JCExpression(param, paramExp));
        }
        paramExp.getChildren().add(new JCExpression(")", paramExp));

        return paramExp;
    }

}
