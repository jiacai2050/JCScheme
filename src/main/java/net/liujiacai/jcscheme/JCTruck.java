package net.liujiacai.jcscheme;

import net.liujiacai.jcscheme.type.JCObject;

public class JCTruck {
    private JCExpression var;
    private JCEnvironment env;

    public JCTruck(JCExpression var, JCEnvironment env) {
        this.var = var;
        this.env = env;
    }

    public JCObject getRealValue() {
        return var.eval(env);
    }
}
