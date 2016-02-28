package net.liujiacai.jcscheme;

import net.liujiacai.jcscheme.type.JCObject;

import java.util.HashMap;
import java.util.Map;

public class JCEnvironment {
	private JCEnvironment parent;
	private Map<String, JCObject> env;

	public JCEnvironment getParent() {
		return parent;
	}

	public Map<String, JCObject> getEnv() {
		return env;
	}

	public JCObject findVariable(String var) {
		if (env.containsKey(var)) {
			return env.get(var);
		} else {
			JCEnvironment p = this.getParent();
			while (p != null) {
				Map<String, JCObject> subEnv = p.getEnv();
				if (subEnv.containsKey(var)) {
					return subEnv.get(var);
				}
				p = p.getParent();
			}
			return null;
		}
	}

	public void addVariable(String var, JCObject val) {
        getEnv().put(var, val);
    }

	public JCEnvironment(JCEnvironment parent) {
		this.parent = parent;
		this.env = new HashMap<String, JCObject>();
	}

}
