// Generated from LabeledExpr2\LabeledExpr.g4 by ANTLR 4.0

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.ErrorNode;

public class LabeledExprBaseListener implements LabeledExprListener {
	@Override public void enterId(LabeledExprParser.IdContext ctx) { }
	@Override public void exitId(LabeledExprParser.IdContext ctx) { }

	@Override public void enterAssign(LabeledExprParser.AssignContext ctx) { }
	@Override public void exitAssign(LabeledExprParser.AssignContext ctx) { }

	@Override public void enterProg(LabeledExprParser.ProgContext ctx) { }
	@Override public void exitProg(LabeledExprParser.ProgContext ctx) { }

	@Override public void enterBlank(LabeledExprParser.BlankContext ctx) { }
	@Override public void exitBlank(LabeledExprParser.BlankContext ctx) { }

	@Override public void enterPrintExpr(LabeledExprParser.PrintExprContext ctx) { }
	@Override public void exitPrintExpr(LabeledExprParser.PrintExprContext ctx) { }

	@Override public void enterInt(LabeledExprParser.IntContext ctx) { }
	@Override public void exitInt(LabeledExprParser.IntContext ctx) { }

	@Override public void enterAddSub(LabeledExprParser.AddSubContext ctx) { }
	@Override public void exitAddSub(LabeledExprParser.AddSubContext ctx) { }

	@Override public void enterParens(LabeledExprParser.ParensContext ctx) { }
	@Override public void exitParens(LabeledExprParser.ParensContext ctx) { }

	@Override public void enterMulDiv(LabeledExprParser.MulDivContext ctx) { }
	@Override public void exitMulDiv(LabeledExprParser.MulDivContext ctx) { }

	@Override public void enterEveryRule(ParserRuleContext ctx) { }
	@Override public void exitEveryRule(ParserRuleContext ctx) { }
	@Override public void visitTerminal(TerminalNode node) { }
	@Override public void visitErrorNode(ErrorNode node) { }
}