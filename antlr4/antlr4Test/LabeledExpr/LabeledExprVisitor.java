// Generated from LabeledExpr\LabeledExpr.g4 by ANTLR 4.0
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface LabeledExprVisitor<T> extends ParseTreeVisitor<T> {
	T visitId(LabeledExprParser.IdContext ctx);

	T visitAssign(LabeledExprParser.AssignContext ctx);

	T visitProg(LabeledExprParser.ProgContext ctx);

	T visitBlank(LabeledExprParser.BlankContext ctx);

	T visitPrintExpr(LabeledExprParser.PrintExprContext ctx);

	T visitInt(LabeledExprParser.IntContext ctx);

	T visitAddSub(LabeledExprParser.AddSubContext ctx);

	T visitParens(LabeledExprParser.ParensContext ctx);

	T visitMulDiv(LabeledExprParser.MulDivContext ctx);
}