package net.liujiacai.jcscheme;

import net.liujiacai.jcscheme.type.SBool;
import net.liujiacai.jcscheme.type.SNumber;
import net.liujiacai.jcscheme.type.SObject;

public class Op {
	// internal methods
	public static final String ADD = "+";
	public static final String SUB = "-";
	public static final String MUL = "*";
	public static final String DIV = "/";

	// internal predicts
	public static final String GT = ">";
	public static final String LT = "<";
	public static final String EQ = "=";

	// internal syntax sugar
	public static final String DEF = "def";
	public static final String IF = "if";

	public static SObject equal(SObject... args) {
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

	public static SObject add(SObject... args) {
		int sum = 0;
		for (SObject a : args) {
			sum += ((SNumber) a).getValue();
		}
		return new SNumber(sum);
	}

	public static SObject sub(SObject... args) {
		int total = ((SNumber) args[0]).getValue();
		for (int i = 1; i < args.length; i++) {
			total -= ((SNumber) args[i]).getValue();
		}
		return new SNumber(total);
	}

	public static SObject mul(SObject... args) {
		int product = 1;
		for (SObject a : args) {
			product *= ((SNumber) a).getValue();
		}
		return new SNumber(product);
	}

	public static SObject div(SObject... args) {
		int total = ((SNumber) args[0]).getValue();
		for (int i = 1; i < args.length; i++) {
			total /= ((SNumber) args[i]).getValue();
		}
		return new SNumber(total);
	}
}
