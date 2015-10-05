package net.liujiacai.jcscheme.type;

import net.liujiacai.jcscheme.Constants;

public class SNil extends SList {
	private String nil;
	private static SNil inst = null;

	private SNil(String nil) {
		super(new SPair(null, null));
		this.nil = nil;
		
	}

	public static SNil getInstance() {
		if (inst == null) {
			inst = new SNil(Constants.NIL);
		}
		return inst;
	}

	@Override
	public String toString() {
		return nil;
	}
}