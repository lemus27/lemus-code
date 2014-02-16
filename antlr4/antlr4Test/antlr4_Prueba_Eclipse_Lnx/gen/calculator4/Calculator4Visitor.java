// Generated from C:\Documents and Settings\mike\Mis documentos\descargas\Dropbox\antlr4\antlr4Test\prueba\src/calculator4/Calculator4.g4 by ANTLR 4.0
package calculator4;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface Calculator4Visitor<T> extends ParseTreeVisitor<T> {
	T visitPARENTESIS(Calculator4Parser.PARENTESISContext ctx);

	T visitOPCOCIENTE(Calculator4Parser.OPCOCIENTEContext ctx);

	T visitOPSUMA(Calculator4Parser.OPSUMAContext ctx);

	T visitUNNUMERO(Calculator4Parser.UNNUMEROContext ctx);

	T visitOPRESTA(Calculator4Parser.OPRESTAContext ctx);

	T visitExpresion(Calculator4Parser.ExpresionContext ctx);

	T visitOPPRODDUCTO(Calculator4Parser.OPPRODDUCTOContext ctx);
}