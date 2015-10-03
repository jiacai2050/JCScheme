package net.liujiacai.jcscheme.type;


public class SNumber extends SObject{

	private int value;

	public int getValue() {
		return value;
	}
	public SNumber(int value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return String.valueOf(value);
	}
	
}
