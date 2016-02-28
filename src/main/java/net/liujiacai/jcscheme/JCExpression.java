package net.liujiacai.jcscheme;

import net.liujiacai.jcscheme.type.JCObject;
import net.liujiacai.jcscheme.util.ApplyUtil;
import net.liujiacai.jcscheme.util.EnvUtil;

import java.util.ArrayList;
import java.util.List;

public class JCExpression {
	private String value;
	private JCExpression parent;
	private List<JCExpression> children;

	public JCExpression(String value, JCExpression parent) {
		this.value = value;
		this.parent = parent;
		this.children = new ArrayList<>();
	}

	public boolean addChild(JCExpression child) {
		return this.children.add(child);
	}

	public List<JCExpression> getChildren() {
		return children;
	}

	public JCExpression getParent() {
		return this.parent;
	}

	public String getValue() {
		return value;
	}

	private boolean isSelfEval() {
        return children.size() == 0;
    }

    public JCObject eval(JCEnvironment env) {

		if (isSelfEval()) {
            return ApplyUtil.selfEvalApply(this, env);
		} else {
			String operator = children.get(0).getValue();
            if (EnvUtil.builtinKeywords.contains(operator)) {
                return ApplyUtil.keywordApply(operator, this, env);
            } else {
                return ApplyUtil.functionApply(operator, this, env);
            }
		}
	}

	@Override
	public String toString() {
		if (0 == children.size()) {
			return value;
		} else {
			StringBuffer displayBuffer = new StringBuffer(value + " ");
			for (JCExpression child : children) {
				displayBuffer.append(child.toString() + " ");
			}
			return displayBuffer.toString();
		}
	}
}
