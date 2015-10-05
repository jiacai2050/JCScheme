package net.liujiacai.jcscheme.type;

public class SPair extends SObject {
	SObject first;
	SObject second;

	public SPair(SObject first, SObject second) {
		this.first = first;
		this.second = second;
	}

	public SObject getFirst() {
		return first;
	}

	public SObject getSecond() {
		return second;
	}

	@Override
	public String toString() {
		return "[" + first + ", " + second + "]";

	}

	public static SObject car(SObject... args) {
		if (args[0] instanceof SPair) {
			SPair pair = (SPair) args[0];
			return pair.getFirst();
		} else {
			SList list = (SList) args[0];
			return list.first();
		}
	}

	public static SObject cdr(SObject... args) {
		if (args[0] instanceof SPair) {
			SPair pair = (SPair) args[0];
			return pair.getSecond();
		} else {
			SList list = (SList) args[0];
			return list.rest();
		}
	}
}
