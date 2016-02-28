package net.liujiacai.jcscheme;

import net.liujiacai.jcscheme.type.JCObject;
import net.liujiacai.jcscheme.util.EnvUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        ReadPrintLoop worker = new ReadPrintLoop();
        final Future<?> future = executor.submit(worker);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                future.cancel(true);
                String[] byebye = {
                        "Thanks for your tryout!",
                        "Find more about JCScheme at http://github.com/jiacai2050/JCScheme",
                        "Have Fun, Bye Bye... "};
                System.out.println();
                System.out.println();
                for (String msg : byebye) {
                    System.out.println(msg);
                }
            }
        });
    }
}

class ReadPrintLoop implements Runnable {
    BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
    JCEnvironment rootEnv = EnvUtil.init();

    public void run() {
        String src;
        while (true) {
            System.out.print(">> ");
            try {
                src = console.readLine();
                if (!"".equals(src.trim())) {
                    String[] tokens = JCParser.tokenize(src);
                    JCExpression program = JCParser.parse(tokens);
                    // JCParser.prettyPrint(program);
                    JCObject val = program.eval(rootEnv);
                    if (val != null) {
                        System.out.println(val);
                    }
                } else {
                    System.err.println("empty cmd !");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }
}
