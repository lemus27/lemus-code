package calculator4;

// Generated from C:\Documents and Settings\mike\Mis documentos\descargas\Dropbox\antlr4\antlr4Test\prueba\src/calculator4/Calculator4.g4 by ANTLR 4.0
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface Calculator4Listener extends ParseTreeListener {
	void enterPARENTESIS(Calculator4Parser.PARENTESISContext ctx);
	void exitPARENTESIS(Calculator4Parser.PARENTESISContext ctx);

	void enterOPCOCIENTE(Calculator4Parser.OPCOCIENTEContext ctx);
	void exitOPCOCIENTE(Calculator4Parser.OPCOCIENTEContext ctx);

	void enterOPSUMA(Calculator4Parser.OPSUMAContext ctx);
	void exitOPSUMA(Calculator4Parser.OPSUMAContext ctx);

	void enterUNNUMERO(Calculator4Parser.UNNUMEROContext ctx);
	void exitUNNUMERO(Calculator4Parser.UNNUMEROContext ctx);

	void enterOPRESTA(Calculator4Parser.OPRESTAContext ctx);
	void exitOPRESTA(Calculator4Parser.OPRESTAContext ctx);

	void enterExpresion(Calculator4Parser.ExpresionContext ctx);
	void exitExpresion(Calculator4Parser.ExpresionContext ctx);

	void enterOPPRODDUCTO(Calculator4Parser.OPPRODDUCTOContext ctx);
	void exitOPPRODDUCTO(Calculator4Parser.OPPRODDUCTOContext ctx);
}