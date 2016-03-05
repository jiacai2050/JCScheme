package net.liujiacai.jcscheme;

import java.util.HashMap;
import java.util.Map;

public class JCEnvironment {
	private JCEnvironment parent;
	private Map<String, JCTruck> env;

	public JCEnvironment getParent() {
		return parent;
	}

	public Map<String, JCTruck> getEnv() {
		return env;
	}

	public JCTruck findVariable(String var) {
		if (env.containsKey(var)) {
			return env.get(var);
		} else {
			JCEnvironment p = this.getParent();
			while (p != null) {
				Map<String, JCTruck> subEnv = p.getEnv();
				if (subEnv.containsKey(var)) {
					return subEnv.get(var);
				}
				p = p.getParent();
			}
			return null;
		}
	}

	public void addVariable(String var, JCTruck val) {
        getEnv().put(var, val);
    }

	public JCEnvironment(JCEnvironment parent) {
		this.parent = parent;
		this.env = new HashMap<String, JCTruck>();
	}

}
