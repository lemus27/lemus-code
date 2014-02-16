package calculator4;

import java.util.HashMap;
import java.util.Map;

public class EvalVisitor extends Calculator4BaseVisitor<Double> {
	boolean DEBUG = false;

	EvalVisitor() {
		if (DEBUG == true) {
			System.out.println("Objeto  Eval visitor Creado");
		}
	}

	@Override
	public Double visitOPSUMA(Calculator4Parser.OPSUMAContext ctx) {
		double result = 0;
		double child_Val = 0;
		int numChild = 0;
		if (DEBUG == true) {
			System.out.println("metodo OPSUMa Empezando");
		}
		numChild = ctx.getChildCount();
		if (DEBUG == true) {
			System.out.println("metodo OPSUMa:numChild: " + numChild);
		}
		if (DEBUG == true) {
			for (int i = 0; i < numChild; i++) {
				System.out.println("metodo OPSUMa:operando:" + i + ":"
						+ ctx.getChild(i).getText());
			}
		}
		for (int i = 0; i < 1000; i++) {
			try {
				if (DEBUG == true) {
					System.out.println("metodo OPSUMa:operando:" + i + ":"
							+ ctx.getChild(i).getText());
				}
				child_Val = visit(ctx.expResta(i));

			} catch (NullPointerException e) {
				return result;
			}
			result = result + child_Val;
			if (DEBUG == true) {
				System.out
						.println("metodo OPSUMa:operando:" + i + ":" + result);
			}
			if (DEBUG == true) {
				System.out.println("resultado:suma: " + result);
			}
		}
		return result;
	}

	@Override
	public Double visitOPRESTA(Calculator4Parser.OPRESTAContext ctx) {
		double result = 0;
		double child_Val = 0;
		int numChild = 0;
		if (DEBUG == true) {
			System.out.println("metodo visitOPRESTA Empezando");
		}
		numChild = ctx.getChildCount();

		if (DEBUG == true) {
			System.out.println("metodo visit OPRESTA :numChild: " + numChild);
		}
		if (DEBUG == true) {
			for (int i = 0; i < numChild; i++) {
				System.out.println("metodo OPRESTA :operando:" + i + ":"
						+ ctx.getChild(i).getText());
			}
		}
		for (int i = 0; i < 1000; i++) {

			try {

				child_Val = visit(ctx.expProducto(i));
				if (i == 0) {
					result = child_Val;
					child_Val = 0;
				}
			} catch (NullPointerException e) {
				return result;
			}
			result = result - child_Val;
			if (DEBUG == true) {
				System.out.println("metodo OPRESTA :operando:" + i + ":"
						+ result);
			}
			if (DEBUG == true) {
				System.out.println("resultado: OPRESTA : " + result);
			}
		}
		return result;
	}

	@Override
	public Double visitOPPRODDUCTO(Calculator4Parser.OPPRODDUCTOContext ctx) {
		if (DEBUG == true) {
			System.out.println("metodo visitOPPRODDUCTO Empezando");
		}
		double result = 0;
		double child_Val = 0;
		int numChild = ctx.getChildCount();

		if (DEBUG == true) {
			System.out.println("metodo visit OPPRODDUCTO :numChild: "
					+ numChild);
		}
		if (DEBUG == true) {
			for (int i = 0; i < numChild; i++) {
				System.out.println("metodo OPPRODDUCTO :operando:" + i + ":"
						+ ctx.getChild(i).getText());
			}
		}
		for (int i = 0; i < 1000; i++) {

			try {

				child_Val = visit(ctx.expCociente(i));
				if (i == 0) {
					result = child_Val;
					child_Val = 0;
					continue;
				}
			} catch (NullPointerException e) {
				return result;
			}
			result = result * child_Val;
			if (DEBUG == true) {
				System.out.println("metodo OPPRODDUCTO :operando:" + i + ":"
						+ result);
			}
			if (DEBUG == true) {
				System.out.println("resultado: OPPRODDUCTO: " + result);
			}

		}
		return result;
	}

	@Override
	public Double visitOPCOCIENTE(Calculator4Parser.OPCOCIENTEContext ctx) {
		double result = 0;
		double child_Val = 0;
		int numChild = 0;
		if (DEBUG == true) {
			System.out.println("metodo visitOPCOCIENTE Empezando");
		}
		numChild = ctx.getChildCount();

		if (DEBUG == true) {
			System.out
					.println("metodo visit OPCOCIENTE: numChild: " + numChild);
		}
		if (DEBUG == true) {
			for (int i = 0; i < numChild; i++) {
				System.out.println("metodo OPCOCIENTE :operando:" + i + ":"
						+ ctx.getChild(i).getText());
			}
		}
		for (int i = 0; i < 1000; i++) {
			try {

				child_Val = visit(ctx.expBase(i));
				if (i == 0) {
					result = child_Val;
					child_Val = 0;
					continue;
				}
			} catch (NullPointerException e) {
				return result;
			}
			result = result / child_Val;
			if (DEBUG == true) {
				System.out.println("metodo OPCOCIENTE :operando:" + i + ":"
						+ result);
			}
			if (DEBUG == true) {
				System.out.println("resultado: OPCOCIENTE: " + result);
			}
		}
		return result;
	}

	/** UNNUMERO */
	// Calculator4Parser.UNNUMEROContext
	// ctx):.
	// "UNNUMEROContext"
	// es
	// el
	// contexto
	// de
	// la
	// regla
	// etiquetada
	// con
	// UNNUMERO
	@Override
	public Double visitUNNUMERO(Calculator4Parser.UNNUMEROContext ctx) {
		if (DEBUG == true) {
			System.out.println("ctx.NUMERO().getText(): "
					+ ctx.NUMERO().getText());
		}
		// ctx.Numero:. "NUMERO"
		// es el terminal de la
		// gramatica que
		// representa los
		// digitos
		return Double.valueOf(ctx.NUMERO().getText());

	}

	/** PARENT_AB! expresion PARENT_CE! */
	// Calculator4Parser.PARENTESISContext
	// ctx):.
	// "PARENTESISContext"
	// es
	// el
	// contexto
	// de
	// la
	// regla
	// etiquetada
	// con
	// PARENTESIS
	@Override
	public Double visitPARENTESIS(Calculator4Parser.PARENTESISContext ctx) {
		if (DEBUG == true) {
			System.out.println("ctx.expresion(): " + ctx.expresion());
		}
		// ctx.expresion:. "expresion" es el nodo
		// que va entre parentesis
		return visit(ctx.expresion());
	}
}