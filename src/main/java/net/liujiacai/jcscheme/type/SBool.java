package net.liujiacai.jcscheme.type;

public class SBool extends SObject{
	private boolean value;

	public boolean getValue() {
		return value;
	}

	public SBool(boolean value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}
	

	// 以下为该类型提供的内置函数
	public static SObject equalTo(SObject... args) {
		int before = ((SNumber) args[0]).getValue();
		boolean ret = true;
		int i = 1;
		for (; i < args.length && ret; i++) {
			int current = ((SNumber) args[i]).getValue();
			if (before != current) {
				ret = false;
			}
			before = current;
		}
		if (i == args.length && ret) {
			return new SBool(ret);
		} else {
			return new SBool(false);
		}
	}

	public static SObject lessThan(SObject... args) {
		int before = ((SNumber) args[0]).getValue();
		boolean ret = true;
		int i = 1;
		for (; i < args.length && ret; i++) {
			int current = ((SNumber) args[i]).getValue();
			if (before > current) {
				ret = false;
			}
			before = current;
		}
		if (i == args.length && ret) {
			return new SBool(ret);
		} else {
			return new SBool(false);
		}
	}

	public static SObject greatThan(SObject... args) {

		int before = ((SNumber) args[0]).getValue();
		boolean ret = true;
		int i = 1;
		for (; i < args.length && ret; i++) {
			int current = ((SNumber) args[i]).getValue();
			if (before < current) {
				ret = false;
			}
			before = current;
		}
		if ((i == args.length) && ret) {
			return new SBool(ret);
		} else {
			return new SBool(false);
		}
	}


}
