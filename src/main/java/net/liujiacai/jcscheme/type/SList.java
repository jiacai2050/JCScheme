package net.liujiacai.jcscheme.type;

public class SList extends SObject {
	private SPair pairs;

	public SList(SPair pairs) {
		this.pairs = pairs;
	}

	public SPair getPairs() {
		return pairs;
	}

	public SObject first() {
		if (SNil.getInstance().equals(this)) {
			System.err
					.println("The empty list passed as the first argument to car, is not correct");
			return null;
		}
		return pairs.getFirst();
	}

	public SObject rest() {
		if (SNil.getInstance().equals(this)) {
			System.err
					.println("The empty list passed as the first argument to cdr, is not correct");
			return null;
		}
		SPair pair = (SPair) pairs.getSecond();
		if (pair.getFirst() == null && pair.getSecond() == null) {
			return SNil.getInstance();
		} else {
			return new SList(pair);
		}

	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof SList)) {
			return false;
		}
		SList list = (SList) obj;
		return list.getPairs().toString().equals(this.getPairs().toString());
	}

	@Override
	public String toString() {
		SObject first = pairs.getFirst();
		StringBuffer buffer = null;
		if (first instanceof SNil) {
			buffer = new StringBuffer("(");
		} else {
			buffer = new StringBuffer("(" + first);
		}

		SObject obj = pairs.getSecond();
		while (obj instanceof SPair) {
			SPair pair = (SPair) obj;
			// it is the last pair now, so stop
			if (pair.getFirst() == null && pair.getSecond() == null) {
				break;
			}
			buffer.append(", " + pair.getFirst().toString());
			obj = pair.getSecond();
		}
		buffer.append(")");
		return buffer.toString();
	}

	public static SBool isEmpty(SObject... args) {
		boolean b = false;
		SList list = (SList) args[0];
		if (SNil.getInstance().equals(list)) {
			b = true;
		}
		return new SBool(b);
	}
}
