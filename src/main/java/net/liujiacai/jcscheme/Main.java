package net.liujiacai.jcscheme;

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
						"You can find details about MyScheme at http://github.com/jiacai2050/JCScheme",
						"Have Fun, Bye Bye... " };
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
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public void run() {
		String input = null;
		while (true) {
			System.out.print(">> ");
			try {
				input = br.readLine();
				if (!"".equals(input.trim())) {
					String[] tokens = Parser.tokenize(input);
					SExpression program = Parser.parse(tokens);
					// Parser.prettyPrint(program);
					System.out.println(program.eval());
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
