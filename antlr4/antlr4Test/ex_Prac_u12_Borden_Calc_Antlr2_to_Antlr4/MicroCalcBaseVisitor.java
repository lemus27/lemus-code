// Generated from MicroCalc.g4 by ANTLR 4.0
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.ParserRuleContext;

public class MicroCalcBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements MicroCalcVisitor<T> {
	@Override public T visitPARENTESIS(MicroCalcParser.PARENTESISContext ctx) { return visitChildren(ctx); }

	@Override public T visitOPCOCIENTE(MicroCalcParser.OPCOCIENTEContext ctx) { return visitChildren(ctx); }

	@Override public T visitOPSUMA(MicroCalcParser.OPSUMAContext ctx) { return visitChildren(ctx); }

	@Override public T visitUNNUMERO(MicroCalcParser.UNNUMEROContext ctx) { return visitChildren(ctx); }

	@Override public T visitOPRESTA(MicroCalcParser.OPRESTAContext ctx) { return visitChildren(ctx); }

	@Override public T visitExpresion(MicroCalcParser.ExpresionContext ctx) { return visitChildren(ctx); }

	@Override public T visitOPPRODDUCTO(MicroCalcParser.OPPRODDUCTOContext ctx) { return visitChildren(ctx); }
}