package net.liujiacai.jcscheme.type;

import java.util.Arrays;
import java.util.List;

import net.liujiacai.jcscheme.SExpression;
import net.liujiacai.jcscheme.SScope;

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
		StringBuffer buffer = new StringBuffer("Function :\n\tArgs : ");
		buffer.append(Arrays.toString(param.toArray(new String[param.size()])));
		buffer.append("\n\tBody : ");
		buffer.append(body.toString());
		return buffer.toString();
	}
	
}
