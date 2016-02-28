package net.liujiacai.jcscheme.type;


public class JCNumber extends JCObject {

	private int value;

	public int getValue() {
		return value;
	}
	public JCNumber(int value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return String.valueOf(value);
	}

}
