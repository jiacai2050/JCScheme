package net.liujiacai.jcscheme;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import net.liujiacai.jcscheme.type.SObject;

public class Util {

	public static SObject builtinFuncExecutor(String fullnameFunc, SObject... args) {
		int seperatorIndex = fullnameFunc.lastIndexOf(".");
		String funcClass = fullnameFunc.substring(0, seperatorIndex);
		String funcName = fullnameFunc.substring(seperatorIndex + 1,
				fullnameFunc.length());
		try {
			Class<?> clazz = Class.forName(funcClass);
			Method method = clazz.getMethod(funcName, SObject[].class);
			return ((SObject) method.invoke(null, new Object[] { args }));
			
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
	public static SObject builtinKeywordExecutor(String fullnameFunc, List<SExpression> children) {
		int seperatorIndex = fullnameFunc.lastIndexOf(".");
		String funcClass = fullnameFunc.substring(0, seperatorIndex);
		String funcName = fullnameFunc.substring(seperatorIndex + 1,
				fullnameFunc.length());
		try {
			Class<?> clazz = Class.forName(funcClass);
			Method method = clazz.getMethod(funcName, List.class);
			return ((SObject) method.invoke(null, new Object[] { children }));
			
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
