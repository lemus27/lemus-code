// Generated from MicroCalc.g4 by ANTLR 4.0
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface MicroCalcVisitor<T> extends ParseTreeVisitor<T> {
	T visitPARENTESIS(MicroCalcParser.PARENTESISContext ctx);

	T visitOPCOCIENTE(MicroCalcParser.OPCOCIENTEContext ctx);

	T visitOPSUMA(MicroCalcParser.OPSUMAContext ctx);

	T visitUNNUMERO(MicroCalcParser.UNNUMEROContext ctx);

	T visitOPRESTA(MicroCalcParser.OPRESTAContext ctx);

	T visitExpresion(MicroCalcParser.ExpresionContext ctx);

	T visitOPPRODDUCTO(MicroCalcParser.OPPRODDUCTOContext ctx);
}