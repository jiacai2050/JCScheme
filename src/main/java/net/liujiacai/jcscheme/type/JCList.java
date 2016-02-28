package net.liujiacai.jcscheme.type;

public class JCList extends JCPair {

    private static final JCList NIL = new JCList(null, null);
    public  static JCList getNullList() {
        return NIL;
    }

    public JCList(JCObject first, JCObject second) {
        super(first, second);
    }

    @Override
    public String toString() {
        JCObject first = this.getFirst();
        StringBuffer buffer;
        if (first == null) {
            buffer = new StringBuffer("(");
        } else {
            buffer = new StringBuffer("(" + first);
        }

        JCPair rest = (JCPair) this.getSecond();
        while (rest != null && rest.getFirst() != null) {
            buffer.append(", " + rest.getFirst().toString());
            rest = (JCPair) rest.getSecond();
        }
        buffer.append(")");
        return buffer.toString();
    }
}
