package net.liujiacai.jcscheme.type;

import java.util.List;

import net.liujiacai.jcscheme.SExpression;

public class SFunction extends SObject{

	List<String> param;
	SExpression body;
	public SFunction(List<String> param, SExpression body) {
		this.param = param;
		this.body = body;
	}

	public SObject apply(SObject... args) {
		for(int i = 0; i< args.length; i ++) {
			SScope.env.put(param.get(i), args[i]);
		}
		SObject ret = body.eval();
		for(int i = 0; i< args.length; i ++) {
			SScope.env.remove(param.get(i));
		}
		return ret;
	}
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer("Function : args [");
		for(String p : param) {
			buffer.append(p + ", ");
		}
		buffer.append("]\n");
		buffer.append("Body :\n");
		buffer.append(body.toString());
		return buffer.toString();
	}
	
}
