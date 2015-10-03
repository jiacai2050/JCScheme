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
}
