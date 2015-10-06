package net.liujiacai.jcscheme.type;

import java.util.Arrays;
import java.util.List;

import net.liujiacai.jcscheme.SExpression;
import net.liujiacai.jcscheme.SScope;

public class SFunction extends SObject {

	private List<String> param;
	private List<SExpression> body;
	private SScope scope;

	public List<String> getParam() {
		return param;
	}

	public SFunction(List<String> param, List<SExpression> body, SScope scope) {
		this.param = param;
		this.body = body;
		this.scope = scope;
	}

	public SObject apply(SObject... args) {
		SScope originScope = SScope.current;
		SScope.current = scope;
		SObject ret = null;
		for (int i = 0; i < args.length; i++) {
			SScope.current.getEnv().put(param.get(i), args[i]);
		}
		if (args.length < param.size()) {
			List<String> subParam = param.subList(args.length, param.size());
			ret = new SFunction(subParam, body, new SScope(SScope.current));
		} else {
			int bodySize = body.size();
			for (int i = 0; i < bodySize - 1; i++) {
				body.get(i).eval();
			}
			// only return last exp
			ret = body.get(bodySize - 1).eval();
		}
		// GC will clean unused scope 
		SScope.current = originScope;
		return ret;
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
