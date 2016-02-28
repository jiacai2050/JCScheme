package net.liujiacai.jcscheme;

import net.liujiacai.jcscheme.util.JCConstants;

public class JCParser {
	public static String[] tokenize(String src) {
		src = src.replaceAll("\\(", " ( ").replaceAll("\\)", " ) ");
		String[] tokens = src.trim().split("\\s+");
		return tokens;
	}

	public static JCExpression parse(String[] tokens) {

		JCExpression parent = null;
		JCExpression root = null;
		for (String token : tokens) {
			JCExpression newNode = new JCExpression(token, parent);
            if ( root == null ) {
                root = newNode;
            } else {
                parent.addChild(newNode);
            }

			switch (token) {
			case JCConstants.START_TOKEN:
				parent = newNode;
				break;
			case JCConstants.END_TOKEN:
				parent = parent.getParent();
				break;
			default:
			}
		}
		return root;
	}

	public static void prettyPrint(JCExpression exps) {
		StringBuffer buffer = new StringBuffer();
		iter(0, exps, buffer);
		System.out.println(buffer.toString());
	}

	private static String fillBlanks(int level) {
		String blank = "";
		for (int i = 0; i < level; i++) {
			blank += "  ";
		}
		return blank;
	}

	private static void iter(int indentLevel, JCExpression exp,
			StringBuffer buffer) {
		String body = exp.getValue();
		String padding = "";
		switch (exp.getValue()) {
		case JCConstants.START_TOKEN:
			padding = fillBlanks(indentLevel);
			indentLevel++;
			break;
		case JCConstants.END_TOKEN:
			indentLevel--;
			padding = fillBlanks(indentLevel);
			break;
		default:
			padding = fillBlanks(indentLevel);
			break;
		}
		buffer.append(padding + body + "\n");
		for (JCExpression child : exp.getChildren()) {
			iter(indentLevel, child, buffer);
		}
	}
}