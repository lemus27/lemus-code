// Generated from LabeledExpr2\LabeledExpr.g4 by ANTLR 4.0
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface LabeledExprListener extends ParseTreeListener {
	void enterId(LabeledExprParser.IdContext ctx);
	void exitId(LabeledExprParser.IdContext ctx);

	void enterAssign(LabeledExprParser.AssignContext ctx);
	void exitAssign(LabeledExprParser.AssignContext ctx);

	void enterProg(LabeledExprParser.ProgContext ctx);
	void exitProg(LabeledExprParser.ProgContext ctx);

	void enterBlank(LabeledExprParser.BlankContext ctx);
	void exitBlank(LabeledExprParser.BlankContext ctx);

	void enterPrintExpr(LabeledExprParser.PrintExprContext ctx);
	void exitPrintExpr(LabeledExprParser.PrintExprContext ctx);

	void enterInt(LabeledExprParser.IntContext ctx);
	void exitInt(LabeledExprParser.IntContext ctx);

	void enterAddSub(LabeledExprParser.AddSubContext ctx);
	void exitAddSub(LabeledExprParser.AddSubContext ctx);

	void enterParens(LabeledExprParser.ParensContext ctx);
	void exitParens(LabeledExprParser.ParensContext ctx);

	void enterMulDiv(LabeledExprParser.MulDivContext ctx);
	void exitMulDiv(LabeledExprParser.MulDivContext ctx);
}