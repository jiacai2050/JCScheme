package net.liujiacai.jcscheme.type;

public class SString extends SObject {
	private String value;

	public String getValue() {
		return value;
	}

	public SString(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}

	// func below used for builtins via reflection
	public static SObject eq(SObject... args) {
		String first = ((SString) args[0]).getValue();
		boolean ret = true;
		int i = 1;
		for (; i < args.length && ret; i++) {
			String current = ((SString) args[i]).getValue();
			if (!current.equals(first)) {
				ret = false;
			}
			first = current;
		}
		if (i == args.length && ret) {
			return new SBool(ret);
		} else {
			return new SBool(false);
		}
	}
}
