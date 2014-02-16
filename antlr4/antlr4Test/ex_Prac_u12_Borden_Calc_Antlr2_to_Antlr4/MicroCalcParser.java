// Generated from MicroCalc.g4 by ANTLR 4.0
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MicroCalcParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BLANCO=1, OP_MAS=2, OP_MENOS=3, OP_PRODUCTO=4, OP_COCIENTE=5, PARENT_AB=6, 
		PARENT_CE=7, NUMERO=8;
	public static final String[] tokenNames = {
		"<INVALID>", "BLANCO", "'+'", "'-'", "'*'", "'/'", "'('", "')'", "NUMERO"
	};
	public static final int
		RULE_expresion = 0, RULE_expSuma = 1, RULE_expResta = 2, RULE_expProducto = 3, 
		RULE_expCociente = 4, RULE_expBase = 5;
	public static final String[] ruleNames = {
		"expresion", "expSuma", "expResta", "expProducto", "expCociente", "expBase"
	};

	@Override
	public String getGrammarFileName() { return "MicroCalc.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public MicroCalcParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ExpresionContext extends ParserRuleContext {
		public ExpSumaContext expSuma() {
			return getRuleContext(ExpSumaContext.class,0);
		}
		public ExpresionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expresion; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroCalcVisitor ) return ((MicroCalcVisitor<? extends T>)visitor).visitExpresion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpresionContext expresion() throws RecognitionException {
		ExpresionContext _localctx = new ExpresionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_expresion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(12); expSuma();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpSumaContext extends ParserRuleContext {
		public ExpSumaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expSuma; }
	 
		public ExpSumaContext() { }
		public void copyFrom(ExpSumaContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class OPSUMAContext extends ExpSumaContext {
		public List<ExpRestaContext> expResta() {
			return getRuleContexts(ExpRestaContext.class);
		}
		public List<TerminalNode> OP_MAS() { return getTokens(MicroCalcParser.OP_MAS); }
		public TerminalNode OP_MAS(int i) {
			return getToken(MicroCalcParser.OP_MAS, i);
		}
		public ExpRestaContext expResta(int i) {
			return getRuleContext(ExpRestaContext.class,i);
		}
		public OPSUMAContext(ExpSumaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroCalcVisitor ) return ((MicroCalcVisitor<? extends T>)visitor).visitOPSUMA(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpSumaContext expSuma() throws RecognitionException {
		ExpSumaContext _localctx = new ExpSumaContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_expSuma);
		int _la;
		try {
			_localctx = new OPSUMAContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(14); expResta();
			setState(19);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP_MAS) {
				{
				{
				setState(15); match(OP_MAS);
				setState(16); expResta();
				}
				}
				setState(21);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpRestaContext extends ParserRuleContext {
		public ExpRestaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expResta; }
	 
		public ExpRestaContext() { }
		public void copyFrom(ExpRestaContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class OPRESTAContext extends ExpRestaContext {
		public ExpProductoContext expProducto(int i) {
			return getRuleContext(ExpProductoContext.class,i);
		}
		public List<TerminalNode> OP_MENOS() { return getTokens(MicroCalcParser.OP_MENOS); }
		public TerminalNode OP_MENOS(int i) {
			return getToken(MicroCalcParser.OP_MENOS, i);
		}
		public List<ExpProductoContext> expProducto() {
			return getRuleContexts(ExpProductoContext.class);
		}
		public OPRESTAContext(ExpRestaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroCalcVisitor ) return ((MicroCalcVisitor<? extends T>)visitor).visitOPRESTA(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpRestaContext expResta() throws RecognitionException {
		ExpRestaContext _localctx = new ExpRestaContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_expResta);
		int _la;
		try {
			_localctx = new OPRESTAContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(22); expProducto();
			setState(27);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP_MENOS) {
				{
				{
				setState(23); match(OP_MENOS);
				setState(24); expProducto();
				}
				}
				setState(29);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpProductoContext extends ParserRuleContext {
		public ExpProductoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expProducto; }
	 
		public ExpProductoContext() { }
		public void copyFrom(ExpProductoContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class OPPRODDUCTOContext extends ExpProductoContext {
		public TerminalNode OP_PRODUCTO(int i) {
			return getToken(MicroCalcParser.OP_PRODUCTO, i);
		}
		public List<TerminalNode> OP_PRODUCTO() { return getTokens(MicroCalcParser.OP_PRODUCTO); }
		public List<ExpCocienteContext> expCociente() {
			return getRuleContexts(ExpCocienteContext.class);
		}
		public ExpCocienteContext expCociente(int i) {
			return getRuleContext(ExpCocienteContext.class,i);
		}
		public OPPRODDUCTOContext(ExpProductoContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroCalcVisitor ) return ((MicroCalcVisitor<? extends T>)visitor).visitOPPRODDUCTO(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpProductoContext expProducto() throws RecognitionException {
		ExpProductoContext _localctx = new ExpProductoContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_expProducto);
		int _la;
		try {
			_localctx = new OPPRODDUCTOContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(30); expCociente();
			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP_PRODUCTO) {
				{
				{
				setState(31); match(OP_PRODUCTO);
				setState(32); expCociente();
				}
				}
				setState(37);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpCocienteContext extends ParserRuleContext {
		public ExpCocienteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expCociente; }
	 
		public ExpCocienteContext() { }
		public void copyFrom(ExpCocienteContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class OPCOCIENTEContext extends ExpCocienteContext {
		public List<ExpBaseContext> expBase() {
			return getRuleContexts(ExpBaseContext.class);
		}
		public ExpBaseContext expBase(int i) {
			return getRuleContext(ExpBaseContext.class,i);
		}
		public List<TerminalNode> OP_COCIENTE() { return getTokens(MicroCalcParser.OP_COCIENTE); }
		public TerminalNode OP_COCIENTE(int i) {
			return getToken(MicroCalcParser.OP_COCIENTE, i);
		}
		public OPCOCIENTEContext(ExpCocienteContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroCalcVisitor ) return ((MicroCalcVisitor<? extends T>)visitor).visitOPCOCIENTE(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpCocienteContext expCociente() throws RecognitionException {
		ExpCocienteContext _localctx = new ExpCocienteContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_expCociente);
		int _la;
		try {
			_localctx = new OPCOCIENTEContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(38); expBase();
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP_COCIENTE) {
				{
				{
				setState(39); match(OP_COCIENTE);
				setState(40); expBase();
				}
				}
				setState(45);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpBaseContext extends ParserRuleContext {
		public ExpBaseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expBase; }
	 
		public ExpBaseContext() { }
		public void copyFrom(ExpBaseContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class PARENTESISContext extends ExpBaseContext {
		public TerminalNode PARENT_AB() { return getToken(MicroCalcParser.PARENT_AB, 0); }
		public TerminalNode PARENT_CE() { return getToken(MicroCalcParser.PARENT_CE, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public PARENTESISContext(ExpBaseContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroCalcVisitor ) return ((MicroCalcVisitor<? extends T>)visitor).visitPARENTESIS(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UNNUMEROContext extends ExpBaseContext {
		public TerminalNode NUMERO() { return getToken(MicroCalcParser.NUMERO, 0); }
		public UNNUMEROContext(ExpBaseContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroCalcVisitor ) return ((MicroCalcVisitor<? extends T>)visitor).visitUNNUMERO(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpBaseContext expBase() throws RecognitionException {
		ExpBaseContext _localctx = new ExpBaseContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_expBase);
		try {
			setState(51);
			switch (_input.LA(1)) {
			case NUMERO:
				_localctx = new UNNUMEROContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(46); match(NUMERO);
				}
				break;
			case PARENT_AB:
				_localctx = new PARENTESISContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(47); match(PARENT_AB);
				setState(48); expresion();
				setState(49); match(PARENT_CE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\2\3\n8\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\3\2\3\2\3\3\3"+
		"\3\3\3\7\3\24\n\3\f\3\16\3\27\13\3\3\4\3\4\3\4\7\4\34\n\4\f\4\16\4\37"+
		"\13\4\3\5\3\5\3\5\7\5$\n\5\f\5\16\5\'\13\5\3\6\3\6\3\6\7\6,\n\6\f\6\16"+
		"\6/\13\6\3\7\3\7\3\7\3\7\3\7\5\7\66\n\7\3\7\2\b\2\4\6\b\n\f\2\2\66\2\16"+
		"\3\2\2\2\4\20\3\2\2\2\6\30\3\2\2\2\b \3\2\2\2\n(\3\2\2\2\f\65\3\2\2\2"+
		"\16\17\5\4\3\2\17\3\3\2\2\2\20\25\5\6\4\2\21\22\7\4\2\2\22\24\5\6\4\2"+
		"\23\21\3\2\2\2\24\27\3\2\2\2\25\23\3\2\2\2\25\26\3\2\2\2\26\5\3\2\2\2"+
		"\27\25\3\2\2\2\30\35\5\b\5\2\31\32\7\5\2\2\32\34\5\b\5\2\33\31\3\2\2\2"+
		"\34\37\3\2\2\2\35\33\3\2\2\2\35\36\3\2\2\2\36\7\3\2\2\2\37\35\3\2\2\2"+
		" %\5\n\6\2!\"\7\6\2\2\"$\5\n\6\2#!\3\2\2\2$\'\3\2\2\2%#\3\2\2\2%&\3\2"+
		"\2\2&\t\3\2\2\2\'%\3\2\2\2(-\5\f\7\2)*\7\7\2\2*,\5\f\7\2+)\3\2\2\2,/\3"+
		"\2\2\2-+\3\2\2\2-.\3\2\2\2.\13\3\2\2\2/-\3\2\2\2\60\66\7\n\2\2\61\62\7"+
		"\b\2\2\62\63\5\2\2\2\63\64\7\t\2\2\64\66\3\2\2\2\65\60\3\2\2\2\65\61\3"+
		"\2\2\2\66\r\3\2\2\2\7\25\35%-\65";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}