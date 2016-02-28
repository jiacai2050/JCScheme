package net.liujiacai.jcscheme.type;

import net.liujiacai.jcscheme.JCExpression;
import net.liujiacai.jcscheme.JCEnvironment;

import java.util.Arrays;
import java.util.List;

public class JCFunction extends JCObject {

	private List<String> param;
	private List<JCExpression> body;
	private JCEnvironment env;

	public List<String> getParam() {
		return param;
	}

	public JCFunction(List<String> param, List<JCExpression> body, JCEnvironment env) {
		this.param = param;
		this.body = body;
		this.env = env;
	}

	public JCObject apply(JCObject... args) {

		// create new env based on the env when define this function,
		// which accounts for JCScheme as a lexical-scoped language
		JCEnvironment currentEnv = new JCEnvironment(this.env);

		for (int i = 0; i < args.length; i++) {
			currentEnv.addVariable(param.get(i), args[i]);
		}
		if (args.length < param.size()) {
			List<String> subParam = param.subList(args.length, param.size());
			return new JCFunction(subParam, body, currentEnv);
		} else {
			int bodySize = body.size();
			for (int i = 0; i < bodySize - 1; i++) {
				body.get(i).eval(currentEnv);
			}
			// only return last exp
			return body.get(bodySize - 1).eval(currentEnv);
		}
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer("Function :\n\tArgs : ");
		buffer.append(Arrays.toString(param.toArray(new String[param.size()])));
		buffer.append("\n\tBody : ");
		buffer.append(body.toString());
		return buffer.toString();
	}

}
