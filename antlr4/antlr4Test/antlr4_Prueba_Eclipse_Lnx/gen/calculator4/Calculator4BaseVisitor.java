package calculator4;

// Generated from C:\Documents and Settings\mike\Mis documentos\descargas\Dropbox\antlr4\antlr4Test\prueba\src/calculator4/Calculator4.g4 by ANTLR 4.0
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.ParserRuleContext;

public class Calculator4BaseVisitor<T> extends AbstractParseTreeVisitor<T> implements Calculator4Visitor<T> {
	@Override public T visitPARENTESIS(Calculator4Parser.PARENTESISContext ctx) { return visitChildren(ctx); }

	@Override public T visitOPCOCIENTE(Calculator4Parser.OPCOCIENTEContext ctx) { return visitChildren(ctx); }

	@Override public T visitOPSUMA(Calculator4Parser.OPSUMAContext ctx) { return visitChildren(ctx); }

	@Override public T visitUNNUMERO(Calculator4Parser.UNNUMEROContext ctx) { return visitChildren(ctx); }

	@Override public T visitOPRESTA(Calculator4Parser.OPRESTAContext ctx) { return visitChildren(ctx); }

	@Override public T visitExpresion(Calculator4Parser.ExpresionContext ctx) { return visitChildren(ctx); }

	@Override public T visitOPPRODDUCTO(Calculator4Parser.OPPRODDUCTOContext ctx) { return visitChildren(ctx); }
}