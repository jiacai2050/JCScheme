package net.liujiacai.jcscheme.type;

public class JCPair extends JCObject {
	private JCObject first;
	private JCObject second;

	public JCPair(JCObject first, JCObject second) {
		this.first = first;
		this.second = second;
	}

	public JCObject getFirst() {
		return first;
	}

	public JCObject getSecond() {
		return second;
	}

	@Override
	public String toString() {
		return "[" + first + ", " + second + "]";

	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && (obj instanceof JCPair)) {
            JCPair pair = (JCPair) obj;
            return pair.toString().equals(this.toString());
        } else {
            return false;
        }
	}

}
