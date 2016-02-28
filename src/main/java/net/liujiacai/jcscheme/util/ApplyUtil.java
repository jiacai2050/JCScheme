package net.liujiacai.jcscheme.util;

import net.liujiacai.jcscheme.JCEnvironment;
import net.liujiacai.jcscheme.JCExpression;
import net.liujiacai.jcscheme.exception.IllegalExpressionException;
import net.liujiacai.jcscheme.keyword.Define;
import net.liujiacai.jcscheme.keyword.If;
import net.liujiacai.jcscheme.keyword.Lambda;
import net.liujiacai.jcscheme.type.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class ApplyUtil {

    public static JCObject selfEvalApply(JCExpression exp, JCEnvironment env) {
        /**
         * step for eval a literal
         *  1. is number ?
         *  2. is true or false ?
         *  3. is NIL ?
         *  4. is in current env ?
         *  5. print error
         */
        String unknownVal = exp.getValue();
        if (unknownVal.matches("\\d+")) {
            return new JCNumber(Integer.valueOf(unknownVal));
        } else if (unknownVal.equals(JCConstants.TRUE) || unknownVal.equals(JCConstants.FALSE)) {
            return JCBool.getInstance(Boolean.valueOf(unknownVal));
        } else if (unknownVal.equals(JCConstants.NIL)) {
            return JCList.getNullList();
        }  else if (unknownVal.startsWith("\"")) {
            return new JCString(unknownVal);
        }else {
            JCObject obj = env.findVariable(unknownVal);
            if(null != obj ) {
                return obj;
            } else {
                System.err.println("Error token: " + unknownVal);
                return null;
            }
        }
    }
	public static JCObject keywordApply(String keyword, JCExpression exp, JCEnvironment env) {
		switch (keyword) {
			case JCConstants.IF:
				return If.apply(exp, env);
			case JCConstants.LAMBDA:
				return Lambda.apply(exp, env);
			case JCConstants.DEF:
				return Define.apply(exp, env);
			default:
				return null;
		}
	}

    public static JCObject functionApply(String operator, JCExpression exp, JCEnvironment env) {
        JCObject[] paramObjs = extractFuncParams(exp, env);
        // 2.1  builtin functions
        if (EnvUtil.builtinFuncs.containsKey(operator)) {
            String func = EnvUtil.builtinFuncs.get(operator);
            return ApplyUtil.builtinFuncExecutor(func, paramObjs);
        } else if (JCConstants.START_TOKEN.equals(operator)) {
            // 2.2  anonymous functions
            return callAnonymousFunc(exp, paramObjs, env);
        } else {
            // 2.3  user-defined functions
            JCFunction func = (JCFunction) env.findVariable(operator);
            if(null != func) {
                return func.apply(paramObjs);
            } else {
                try {
                    throw new IllegalExpressionException("Unknown variable: " + operator);
                } catch (IllegalExpressionException e) {
                    e.printStackTrace();
                } finally {
                    System.exit(1);
                    return null;
                }
            }
        }

    }
    private static JCObject[] extractFuncParams(JCExpression exp, JCEnvironment env) {
        List<JCExpression> children = exp.getChildren();

        int length = children.size();
        List<JCExpression> paramExps = children.subList(1, length - 1);
        JCObject[] paramObjs = new JCObject[paramExps.size()];
        int argsIndex = 0;
        for (JCExpression argExp : paramExps) {
            paramObjs[argsIndex ++] = argExp.eval(env);
        }
        return paramObjs;
    }
    private static JCObject callAnonymousFunc(JCExpression exp, JCObject[] param, JCEnvironment env) {
        /**
         * There are 2 cases:
         * 1. immediately-invoked function:
         *      ((lambda (x) x) 2)  ==> return 2
         * 2. currying:
         * 	    (def adder (lambda(x y) (+ x y)))
         *      ((adder 2) 3)        ==> return 5
         */
        List<JCExpression> children = exp.getChildren();
        JCObject unknownObj = children.get(0).eval(env);
        if (unknownObj instanceof JCFunction) {
            JCFunction func = (JCFunction) unknownObj;
            return func.apply(param);
        } else {
            return null;
        }
    }

    private static JCObject builtinFuncExecutor(String fullnameFunc, JCObject... args) {
        int seperatorIndex = fullnameFunc.lastIndexOf(".");
        String funcClass = fullnameFunc.substring(0, seperatorIndex);
        String funcName = fullnameFunc.substring(seperatorIndex + 1,
                fullnameFunc.length());
        try {
            Class<?> clazz = Class.forName(funcClass);
            Method method = clazz.getMethod(funcName, JCObject[].class);
            return ((JCObject) method.invoke(null, new Object[] { args }));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
