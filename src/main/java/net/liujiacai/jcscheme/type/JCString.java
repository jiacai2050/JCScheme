package net.liujiacai.jcscheme.type;

public class JCString extends JCObject {
	private String value;

	public String getValue() {
		return value;
	}

	public JCString(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}


}
