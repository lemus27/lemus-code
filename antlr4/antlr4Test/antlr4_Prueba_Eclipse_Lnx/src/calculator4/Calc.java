package calculator4;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;

public class Calc {

	public static void main(String[] args) throws Exception {
		boolean DEBUG = false;
		double result = 0;
		boolean bSalir = false;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in));
			String entrada = null;
			do {
				System.out.print("\n? ");
				entrada = in.readLine();
				if (entrada.startsWith("salir")) {
					bSalir = true;
				} else {
					ANTLRInputStream input = new ANTLRInputStream(entrada);
					Calculator4Lexer lexer = new Calculator4Lexer(input);
					CommonTokenStream tokens = new CommonTokenStream(lexer);
					Calculator4Parser parser = new Calculator4Parser(tokens);
					ParseTree tree = parser.expresion();
					EvalVisitor eval = new EvalVisitor();
					result = eval.visit(tree);
					if (DEBUG == true) {
						System.out.println(tree.toStringTree(parser));
						// print tree as text
					}
					System.out.println("Resultado: " + result);
				}
			} while (!bSalir);
		} catch (RecognitionException e) {
			System.out.println(e + " Algo Paso");
		}

	}
}
