package net.liujiacai.jcscheme;

public class Parser {
	public static String[] tokenize(String src) {
		src = src.replaceAll("\\(", " ( ").replaceAll("\\)", " ) ");
		String[] tokens = src.trim().split("\\s+");
		return tokens;
	}

	public static SExpression parse(String[] tokens) {
		SExpression root = new SExpression("", null);
		SExpression parent = root;
		for (String token : tokens) {
			SExpression newNode = new SExpression(token, parent);
			parent.addChild(newNode);
			switch (token) {
			case Constants.START_TOKEN:
				parent = newNode;
				break;
			case Constants.END_TOKEN:
				parent = parent.getParent();
				break;
			default:
			}
		}
		return root;
	}

	public static void prettyPrint(SExpression exps) {
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

	private static void iter(int indentLevel, SExpression exp,
			StringBuffer buffer) {
		String body = exp.getValue();
		String padding = "";
		switch (exp.getValue()) {
		case Constants.START_TOKEN:
			padding = fillBlanks(indentLevel);
			indentLevel++;
			break;
		case Constants.END_TOKEN:
			indentLevel--;
			padding = fillBlanks(indentLevel);
			break;
		default:
			padding = fillBlanks(indentLevel);
			break;
		}
		buffer.append(padding + body + "\n");
		for (SExpression child : exp.getChildren()) {
			iter(indentLevel, child, buffer);
		}
	}
}