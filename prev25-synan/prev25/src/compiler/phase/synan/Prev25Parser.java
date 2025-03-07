// Generated from Prev25Parser.g4 by ANTLR 4.13.2


	package compiler.phase.synan;
	
	import java.util.*;
	import compiler.common.report.*;
	import compiler.phase.lexan.*;


import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"doclint:missing", "all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class Prev25Parser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		INTCONST=1, CHARCONST=2, STRCONST=3, PLUS=4, MINUS=5, AND=6, OR=7, EQ=8, 
		NEQ=9, LTE=10, GTE=11, LT=12, GT=13, STAR=14, DIV=15, MOD=16, NOT=17, 
		DOT=18, POW=19, ASSIGNMENT=20, COLON=21, COMMA=22, LBRACE=23, RBRACE=24, 
		LPAR=25, RPAR=26, LBRACKET=27, RBRACKET=28, BOOL=29, CHAR=30, DO=31, ELSE=32, 
		END=33, FALSE=34, FUN=35, IF=36, IN=37, INT=38, LET=39, NULL=40, RETURN=41, 
		SIZEOF=42, THEN=43, TRUE=44, TYP=45, VAR=46, VOID=47, WHILE=48, IDENTIFIER=49, 
		COMMENT=50, WHITESPACE=51, ERROR=52;
	public static final int
		RULE_source = 0, RULE_defs = 1, RULE_def = 2, RULE_args1 = 3, RULE_implementation = 4, 
		RULE_args = 5, RULE_args2 = 6, RULE_arg = 7, RULE_statements = 8, RULE_empty_statements = 9, 
		RULE_statement = 10, RULE_expr_extension = 11, RULE_else = 12, RULE_type = 13, 
		RULE_types = 14, RULE_types2 = 15, RULE_expr = 16, RULE_expr2 = 17, RULE_expr3 = 18, 
		RULE_expr4 = 19, RULE_expr5 = 20, RULE_expr6 = 21, RULE_expr7 = 22, RULE_exprs_in = 23, 
		RULE_exprs = 24, RULE_exprs2 = 25, RULE_terminals = 26, RULE_multiplicative_ops = 27, 
		RULE_additive_ops = 28, RULE_comparitive_ops = 29, RULE_prefix = 30;
	private static String[] makeRuleNames() {
		return new String[] {
			"source", "defs", "def", "args1", "implementation", "args", "args2", 
			"arg", "statements", "empty_statements", "statement", "expr_extension", 
			"else", "type", "types", "types2", "expr", "expr2", "expr3", "expr4", 
			"expr5", "expr6", "expr7", "exprs_in", "exprs", "exprs2", "terminals", 
			"multiplicative_ops", "additive_ops", "comparitive_ops", "prefix"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, "'+'", "'-'", "'&'", "'|'", "'=='", "'!='", "'<='", 
			"'>='", "'<'", "'>'", "'*'", "'/'", "'%'", "'!'", "'.'", "'^'", "'='", 
			"':'", "','", "'{'", "'}'", "'('", "')'", "'['", "']'", "'bool'", "'char'", 
			"'do'", "'else'", "'end'", "'false'", "'fun'", "'if'", "'in'", "'int'", 
			"'let'", "'null'", "'return'", "'sizeof'", "'then'", "'true'", "'typ'", 
			"'var'", "'void'", "'while'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "INTCONST", "CHARCONST", "STRCONST", "PLUS", "MINUS", "AND", "OR", 
			"EQ", "NEQ", "LTE", "GTE", "LT", "GT", "STAR", "DIV", "MOD", "NOT", "DOT", 
			"POW", "ASSIGNMENT", "COLON", "COMMA", "LBRACE", "RBRACE", "LPAR", "RPAR", 
			"LBRACKET", "RBRACKET", "BOOL", "CHAR", "DO", "ELSE", "END", "FALSE", 
			"FUN", "IF", "IN", "INT", "LET", "NULL", "RETURN", "SIZEOF", "THEN", 
			"TRUE", "TYP", "VAR", "VOID", "WHILE", "IDENTIFIER", "COMMENT", "WHITESPACE", 
			"ERROR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Prev25Parser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }



		private Location loc(Token     tok) { return new Location((LexAn.LocLogToken)tok); }
		private Location loc(Token     tok1, Token     tok2) { return new Location((LexAn.LocLogToken)tok1, (LexAn.LocLogToken)tok2); }
		private Location loc(Token     tok1, Locatable loc2) { return new Location((LexAn.LocLogToken)tok1, loc2); }
		private Location loc(Locatable loc1, Token     tok2) { return new Location(loc1, (LexAn.LocLogToken)tok2); }
		private Location loc(Locatable loc1, Locatable loc2) { return new Location(loc1, loc2); }


	public Prev25Parser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SourceContext extends ParserRuleContext {
		public DefsContext defs() {
			return getRuleContext(DefsContext.class,0);
		}
		public TerminalNode EOF() { return getToken(Prev25Parser.EOF, 0); }
		public SourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_source; }
	}

	public final SourceContext source() throws RecognitionException {
		SourceContext _localctx = new SourceContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_source);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			defs();
			setState(63);
			match(EOF);
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

	@SuppressWarnings("CheckReturnValue")
	public static class DefsContext extends ParserRuleContext {
		public DefContext def() {
			return getRuleContext(DefContext.class,0);
		}
		public DefsContext defs() {
			return getRuleContext(DefsContext.class,0);
		}
		public DefsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defs; }
	}

	public final DefsContext defs() throws RecognitionException {
		DefsContext _localctx = new DefsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_defs);
		try {
			setState(69);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(65);
				def();
				setState(66);
				defs();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(68);
				def();
				}
				break;
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

	@SuppressWarnings("CheckReturnValue")
	public static class DefContext extends ParserRuleContext {
		public TerminalNode TYP() { return getToken(Prev25Parser.TYP, 0); }
		public TerminalNode IDENTIFIER() { return getToken(Prev25Parser.IDENTIFIER, 0); }
		public TerminalNode ASSIGNMENT() { return getToken(Prev25Parser.ASSIGNMENT, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode VAR() { return getToken(Prev25Parser.VAR, 0); }
		public TerminalNode COLON() { return getToken(Prev25Parser.COLON, 0); }
		public TerminalNode FUN() { return getToken(Prev25Parser.FUN, 0); }
		public TerminalNode LPAR() { return getToken(Prev25Parser.LPAR, 0); }
		public Args1Context args1() {
			return getRuleContext(Args1Context.class,0);
		}
		public TerminalNode RPAR() { return getToken(Prev25Parser.RPAR, 0); }
		public ImplementationContext implementation() {
			return getRuleContext(ImplementationContext.class,0);
		}
		public DefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_def; }
	}

	public final DefContext def() throws RecognitionException {
		DefContext _localctx = new DefContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_def);
		try {
			setState(88);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYP:
				enterOuterAlt(_localctx, 1);
				{
				setState(71);
				match(TYP);
				setState(72);
				match(IDENTIFIER);
				setState(73);
				match(ASSIGNMENT);
				setState(74);
				type();
				}
				break;
			case VAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(75);
				match(VAR);
				setState(76);
				match(IDENTIFIER);
				setState(77);
				match(COLON);
				setState(78);
				type();
				}
				break;
			case FUN:
				enterOuterAlt(_localctx, 3);
				{
				setState(79);
				match(FUN);
				setState(80);
				match(IDENTIFIER);
				setState(81);
				match(LPAR);
				setState(82);
				args1();
				setState(83);
				match(RPAR);
				setState(84);
				match(COLON);
				setState(85);
				type();
				setState(86);
				implementation();
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

	@SuppressWarnings("CheckReturnValue")
	public static class Args1Context extends ParserRuleContext {
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public Args1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args1; }
	}

	public final Args1Context args1() throws RecognitionException {
		Args1Context _localctx = new Args1Context(_ctx, getState());
		enterRule(_localctx, 6, RULE_args1);
		try {
			setState(92);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(90);
				args();
				}
				break;
			case RPAR:
				enterOuterAlt(_localctx, 2);
				{
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

	@SuppressWarnings("CheckReturnValue")
	public static class ImplementationContext extends ParserRuleContext {
		public TerminalNode ASSIGNMENT() { return getToken(Prev25Parser.ASSIGNMENT, 0); }
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public ImplementationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_implementation; }
	}

	public final ImplementationContext implementation() throws RecognitionException {
		ImplementationContext _localctx = new ImplementationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_implementation);
		try {
			setState(97);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ASSIGNMENT:
				enterOuterAlt(_localctx, 1);
				{
				setState(94);
				match(ASSIGNMENT);
				setState(95);
				statements();
				}
				break;
			case EOF:
			case FUN:
			case IN:
			case TYP:
			case VAR:
				enterOuterAlt(_localctx, 2);
				{
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

	@SuppressWarnings("CheckReturnValue")
	public static class ArgsContext extends ParserRuleContext {
		public ArgContext arg() {
			return getRuleContext(ArgContext.class,0);
		}
		public Args2Context args2() {
			return getRuleContext(Args2Context.class,0);
		}
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_args);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			arg();
			setState(100);
			args2();
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

	@SuppressWarnings("CheckReturnValue")
	public static class Args2Context extends ParserRuleContext {
		public TerminalNode COMMA() { return getToken(Prev25Parser.COMMA, 0); }
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public Args2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args2; }
	}

	public final Args2Context args2() throws RecognitionException {
		Args2Context _localctx = new Args2Context(_ctx, getState());
		enterRule(_localctx, 12, RULE_args2);
		try {
			setState(105);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case COMMA:
				enterOuterAlt(_localctx, 1);
				{
				setState(102);
				match(COMMA);
				setState(103);
				args();
				}
				break;
			case GT:
			case RBRACE:
			case RPAR:
				enterOuterAlt(_localctx, 2);
				{
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

	@SuppressWarnings("CheckReturnValue")
	public static class ArgContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(Prev25Parser.IDENTIFIER, 0); }
		public TerminalNode COLON() { return getToken(Prev25Parser.COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arg; }
	}

	public final ArgContext arg() throws RecognitionException {
		ArgContext _localctx = new ArgContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_arg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			match(IDENTIFIER);
			setState(108);
			match(COLON);
			setState(109);
			type();
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

	@SuppressWarnings("CheckReturnValue")
	public static class StatementsContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(Prev25Parser.COMMA, 0); }
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public StatementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statements; }
	}

	public final StatementsContext statements() throws RecognitionException {
		StatementsContext _localctx = new StatementsContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_statements);
		try {
			setState(116);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(111);
				statement();
				setState(112);
				match(COMMA);
				setState(113);
				statements();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(115);
				statement();
				}
				break;
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

	@SuppressWarnings("CheckReturnValue")
	public static class Empty_statementsContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(Prev25Parser.COMMA, 0); }
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public Empty_statementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_empty_statements; }
	}

	public final Empty_statementsContext empty_statements() throws RecognitionException {
		Empty_statementsContext _localctx = new Empty_statementsContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_empty_statements);
		try {
			setState(124);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(118);
				statement();
				setState(119);
				match(COMMA);
				setState(120);
				statements();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(122);
				statement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				}
				break;
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

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Expr_extensionContext expr_extension() {
			return getRuleContext(Expr_extensionContext.class,0);
		}
		public TerminalNode RETURN() { return getToken(Prev25Parser.RETURN, 0); }
		public TerminalNode WHILE() { return getToken(Prev25Parser.WHILE, 0); }
		public TerminalNode DO() { return getToken(Prev25Parser.DO, 0); }
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public TerminalNode END() { return getToken(Prev25Parser.END, 0); }
		public TerminalNode IF() { return getToken(Prev25Parser.IF, 0); }
		public TerminalNode THEN() { return getToken(Prev25Parser.THEN, 0); }
		public Empty_statementsContext empty_statements() {
			return getRuleContext(Empty_statementsContext.class,0);
		}
		public ElseContext else_() {
			return getRuleContext(ElseContext.class,0);
		}
		public TerminalNode LET() { return getToken(Prev25Parser.LET, 0); }
		public DefsContext defs() {
			return getRuleContext(DefsContext.class,0);
		}
		public TerminalNode IN() { return getToken(Prev25Parser.IN, 0); }
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_statement);
		try {
			setState(150);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTCONST:
			case CHARCONST:
			case STRCONST:
			case PLUS:
			case MINUS:
			case NOT:
			case POW:
			case LBRACE:
			case LPAR:
			case FALSE:
			case NULL:
			case SIZEOF:
			case TRUE:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(126);
				expr(0);
				setState(127);
				expr_extension();
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 2);
				{
				setState(129);
				match(RETURN);
				setState(130);
				expr(0);
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 3);
				{
				setState(131);
				match(WHILE);
				setState(132);
				expr(0);
				setState(133);
				match(DO);
				setState(134);
				statements();
				setState(135);
				match(END);
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 4);
				{
				setState(137);
				match(IF);
				setState(138);
				expr(0);
				setState(139);
				match(THEN);
				setState(140);
				empty_statements();
				setState(141);
				else_();
				setState(142);
				match(END);
				}
				break;
			case LET:
				enterOuterAlt(_localctx, 5);
				{
				setState(144);
				match(LET);
				setState(145);
				defs();
				setState(146);
				match(IN);
				setState(147);
				statements();
				setState(148);
				match(END);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Expr_extensionContext extends ParserRuleContext {
		public TerminalNode ASSIGNMENT() { return getToken(Prev25Parser.ASSIGNMENT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Expr_extensionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_extension; }
	}

	public final Expr_extensionContext expr_extension() throws RecognitionException {
		Expr_extensionContext _localctx = new Expr_extensionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_expr_extension);
		try {
			setState(155);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ASSIGNMENT:
				enterOuterAlt(_localctx, 1);
				{
				setState(152);
				match(ASSIGNMENT);
				setState(153);
				expr(0);
				}
				break;
			case EOF:
			case COMMA:
			case ELSE:
			case END:
			case FUN:
			case IN:
			case TYP:
			case VAR:
				enterOuterAlt(_localctx, 2);
				{
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

	@SuppressWarnings("CheckReturnValue")
	public static class ElseContext extends ParserRuleContext {
		public TerminalNode ELSE() { return getToken(Prev25Parser.ELSE, 0); }
		public Empty_statementsContext empty_statements() {
			return getRuleContext(Empty_statementsContext.class,0);
		}
		public ElseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_else; }
	}

	public final ElseContext else_() throws RecognitionException {
		ElseContext _localctx = new ElseContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_else);
		try {
			setState(160);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ELSE:
				enterOuterAlt(_localctx, 1);
				{
				setState(157);
				match(ELSE);
				setState(158);
				empty_statements();
				}
				break;
			case END:
				enterOuterAlt(_localctx, 2);
				{
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

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(Prev25Parser.INT, 0); }
		public TerminalNode CHAR() { return getToken(Prev25Parser.CHAR, 0); }
		public TerminalNode BOOL() { return getToken(Prev25Parser.BOOL, 0); }
		public TerminalNode VOID() { return getToken(Prev25Parser.VOID, 0); }
		public TerminalNode IDENTIFIER() { return getToken(Prev25Parser.IDENTIFIER, 0); }
		public TerminalNode LBRACKET() { return getToken(Prev25Parser.LBRACKET, 0); }
		public TerminalNode INTCONST() { return getToken(Prev25Parser.INTCONST, 0); }
		public TerminalNode RBRACKET() { return getToken(Prev25Parser.RBRACKET, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode POW() { return getToken(Prev25Parser.POW, 0); }
		public TerminalNode LT() { return getToken(Prev25Parser.LT, 0); }
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public TerminalNode GT() { return getToken(Prev25Parser.GT, 0); }
		public TerminalNode LBRACE() { return getToken(Prev25Parser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(Prev25Parser.RBRACE, 0); }
		public TerminalNode LPAR() { return getToken(Prev25Parser.LPAR, 0); }
		public TypesContext types() {
			return getRuleContext(TypesContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(Prev25Parser.RPAR, 0); }
		public TerminalNode COLON() { return getToken(Prev25Parser.COLON, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_type);
		try {
			setState(187);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(162);
				match(INT);
				}
				break;
			case CHAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(163);
				match(CHAR);
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 3);
				{
				setState(164);
				match(BOOL);
				}
				break;
			case VOID:
				enterOuterAlt(_localctx, 4);
				{
				setState(165);
				match(VOID);
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 5);
				{
				setState(166);
				match(IDENTIFIER);
				}
				break;
			case LBRACKET:
				enterOuterAlt(_localctx, 6);
				{
				setState(167);
				match(LBRACKET);
				setState(168);
				match(INTCONST);
				setState(169);
				match(RBRACKET);
				setState(170);
				type();
				}
				break;
			case POW:
				enterOuterAlt(_localctx, 7);
				{
				setState(171);
				match(POW);
				setState(172);
				type();
				}
				break;
			case LT:
				enterOuterAlt(_localctx, 8);
				{
				setState(173);
				match(LT);
				setState(174);
				args();
				setState(175);
				match(GT);
				}
				break;
			case LBRACE:
				enterOuterAlt(_localctx, 9);
				{
				setState(177);
				match(LBRACE);
				setState(178);
				args();
				setState(179);
				match(RBRACE);
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 10);
				{
				setState(181);
				match(LPAR);
				setState(182);
				types();
				setState(183);
				match(RPAR);
				setState(184);
				match(COLON);
				setState(185);
				type();
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

	@SuppressWarnings("CheckReturnValue")
	public static class TypesContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public Types2Context types2() {
			return getRuleContext(Types2Context.class,0);
		}
		public TypesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_types; }
	}

	public final TypesContext types() throws RecognitionException {
		TypesContext _localctx = new TypesContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_types);
		try {
			setState(193);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LT:
			case POW:
			case LBRACE:
			case LPAR:
			case LBRACKET:
			case BOOL:
			case CHAR:
			case INT:
			case VOID:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(189);
				type();
				setState(190);
				types2();
				}
				break;
			case RPAR:
				enterOuterAlt(_localctx, 2);
				{
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

	@SuppressWarnings("CheckReturnValue")
	public static class Types2Context extends ParserRuleContext {
		public TerminalNode COMMA() { return getToken(Prev25Parser.COMMA, 0); }
		public TypesContext types() {
			return getRuleContext(TypesContext.class,0);
		}
		public Types2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_types2; }
	}

	public final Types2Context types2() throws RecognitionException {
		Types2Context _localctx = new Types2Context(_ctx, getState());
		enterRule(_localctx, 30, RULE_types2);
		try {
			setState(198);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case COMMA:
				enterOuterAlt(_localctx, 1);
				{
				setState(195);
				match(COMMA);
				setState(196);
				types();
				}
				break;
			case RPAR:
				enterOuterAlt(_localctx, 2);
				{
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

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public Expr2Context expr2() {
			return getRuleContext(Expr2Context.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode OR() { return getToken(Prev25Parser.OR, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 32;
		enterRecursionRule(_localctx, 32, RULE_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(201);
			expr2(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(208);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExprContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_expr);
					setState(203);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(204);
					match(OR);
					setState(205);
					expr2(0);
					}
					} 
				}
				setState(210);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Expr2Context extends ParserRuleContext {
		public Expr3Context expr3() {
			return getRuleContext(Expr3Context.class,0);
		}
		public Expr2Context expr2() {
			return getRuleContext(Expr2Context.class,0);
		}
		public TerminalNode AND() { return getToken(Prev25Parser.AND, 0); }
		public Expr2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr2; }
	}

	public final Expr2Context expr2() throws RecognitionException {
		return expr2(0);
	}

	private Expr2Context expr2(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Expr2Context _localctx = new Expr2Context(_ctx, _parentState);
		Expr2Context _prevctx = _localctx;
		int _startState = 34;
		enterRecursionRule(_localctx, 34, RULE_expr2, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(212);
			expr3();
			}
			_ctx.stop = _input.LT(-1);
			setState(219);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Expr2Context(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_expr2);
					setState(214);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(215);
					match(AND);
					setState(216);
					expr3();
					}
					} 
				}
				setState(221);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Expr3Context extends ParserRuleContext {
		public List<Expr4Context> expr4() {
			return getRuleContexts(Expr4Context.class);
		}
		public Expr4Context expr4(int i) {
			return getRuleContext(Expr4Context.class,i);
		}
		public Comparitive_opsContext comparitive_ops() {
			return getRuleContext(Comparitive_opsContext.class,0);
		}
		public Expr3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr3; }
	}

	public final Expr3Context expr3() throws RecognitionException {
		Expr3Context _localctx = new Expr3Context(_ctx, getState());
		enterRule(_localctx, 36, RULE_expr3);
		try {
			setState(227);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(222);
				expr4(0);
				setState(223);
				comparitive_ops();
				setState(224);
				expr4(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(226);
				expr4(0);
				}
				break;
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

	@SuppressWarnings("CheckReturnValue")
	public static class Expr4Context extends ParserRuleContext {
		public Expr5Context expr5() {
			return getRuleContext(Expr5Context.class,0);
		}
		public Expr4Context expr4() {
			return getRuleContext(Expr4Context.class,0);
		}
		public Additive_opsContext additive_ops() {
			return getRuleContext(Additive_opsContext.class,0);
		}
		public Expr4Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr4; }
	}

	public final Expr4Context expr4() throws RecognitionException {
		return expr4(0);
	}

	private Expr4Context expr4(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Expr4Context _localctx = new Expr4Context(_ctx, _parentState);
		Expr4Context _prevctx = _localctx;
		int _startState = 38;
		enterRecursionRule(_localctx, 38, RULE_expr4, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(230);
			expr5(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(238);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Expr4Context(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_expr4);
					setState(232);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(233);
					additive_ops();
					setState(234);
					expr5(0);
					}
					} 
				}
				setState(240);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Expr5Context extends ParserRuleContext {
		public Expr6Context expr6() {
			return getRuleContext(Expr6Context.class,0);
		}
		public Expr5Context expr5() {
			return getRuleContext(Expr5Context.class,0);
		}
		public Multiplicative_opsContext multiplicative_ops() {
			return getRuleContext(Multiplicative_opsContext.class,0);
		}
		public Expr5Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr5; }
	}

	public final Expr5Context expr5() throws RecognitionException {
		return expr5(0);
	}

	private Expr5Context expr5(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Expr5Context _localctx = new Expr5Context(_ctx, _parentState);
		Expr5Context _prevctx = _localctx;
		int _startState = 40;
		enterRecursionRule(_localctx, 40, RULE_expr5, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(242);
			expr6();
			}
			_ctx.stop = _input.LT(-1);
			setState(250);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Expr5Context(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_expr5);
					setState(244);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(245);
					multiplicative_ops();
					setState(246);
					expr6();
					}
					} 
				}
				setState(252);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Expr6Context extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(Prev25Parser.PLUS, 0); }
		public Expr6Context expr6() {
			return getRuleContext(Expr6Context.class,0);
		}
		public TerminalNode MINUS() { return getToken(Prev25Parser.MINUS, 0); }
		public TerminalNode NOT() { return getToken(Prev25Parser.NOT, 0); }
		public TerminalNode POW() { return getToken(Prev25Parser.POW, 0); }
		public Expr7Context expr7() {
			return getRuleContext(Expr7Context.class,0);
		}
		public Expr6Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr6; }
	}

	public final Expr6Context expr6() throws RecognitionException {
		Expr6Context _localctx = new Expr6Context(_ctx, getState());
		enterRule(_localctx, 42, RULE_expr6);
		try {
			setState(262);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PLUS:
				enterOuterAlt(_localctx, 1);
				{
				setState(253);
				match(PLUS);
				setState(254);
				expr6();
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(255);
				match(MINUS);
				setState(256);
				expr6();
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 3);
				{
				setState(257);
				match(NOT);
				setState(258);
				expr6();
				}
				break;
			case POW:
				enterOuterAlt(_localctx, 4);
				{
				setState(259);
				match(POW);
				setState(260);
				expr6();
				}
				break;
			case INTCONST:
			case CHARCONST:
			case STRCONST:
			case LBRACE:
			case LPAR:
			case FALSE:
			case NULL:
			case SIZEOF:
			case TRUE:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 5);
				{
				setState(261);
				expr7(0);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Expr7Context extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(Prev25Parser.LBRACE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode COLON() { return getToken(Prev25Parser.COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(Prev25Parser.RBRACE, 0); }
		public TerminalNode LPAR() { return getToken(Prev25Parser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(Prev25Parser.RPAR, 0); }
		public TerminalNode SIZEOF() { return getToken(Prev25Parser.SIZEOF, 0); }
		public TerminalsContext terminals() {
			return getRuleContext(TerminalsContext.class,0);
		}
		public Expr7Context expr7() {
			return getRuleContext(Expr7Context.class,0);
		}
		public TerminalNode POW() { return getToken(Prev25Parser.POW, 0); }
		public TerminalNode LBRACKET() { return getToken(Prev25Parser.LBRACKET, 0); }
		public TerminalNode RBRACKET() { return getToken(Prev25Parser.RBRACKET, 0); }
		public TerminalNode DOT() { return getToken(Prev25Parser.DOT, 0); }
		public TerminalNode IDENTIFIER() { return getToken(Prev25Parser.IDENTIFIER, 0); }
		public Exprs_inContext exprs_in() {
			return getRuleContext(Exprs_inContext.class,0);
		}
		public Expr7Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr7; }
	}

	public final Expr7Context expr7() throws RecognitionException {
		return expr7(0);
	}

	private Expr7Context expr7(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Expr7Context _localctx = new Expr7Context(_ctx, _parentState);
		Expr7Context _prevctx = _localctx;
		int _startState = 44;
		enterRecursionRule(_localctx, 44, RULE_expr7, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(278);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACE:
				{
				setState(265);
				match(LBRACE);
				setState(266);
				expr(0);
				setState(267);
				match(COLON);
				setState(268);
				type();
				setState(269);
				match(RBRACE);
				}
				break;
			case LPAR:
				{
				setState(271);
				match(LPAR);
				setState(272);
				expr(0);
				setState(273);
				match(RPAR);
				}
				break;
			case SIZEOF:
				{
				setState(275);
				match(SIZEOF);
				setState(276);
				type();
				}
				break;
			case INTCONST:
			case CHARCONST:
			case STRCONST:
			case FALSE:
			case NULL:
			case TRUE:
			case IDENTIFIER:
				{
				setState(277);
				terminals();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(297);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(295);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
					case 1:
						{
						_localctx = new Expr7Context(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr7);
						setState(280);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(281);
						match(POW);
						}
						break;
					case 2:
						{
						_localctx = new Expr7Context(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr7);
						setState(282);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(283);
						match(LBRACKET);
						setState(284);
						expr(0);
						setState(285);
						match(RBRACKET);
						}
						break;
					case 3:
						{
						_localctx = new Expr7Context(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr7);
						setState(287);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(288);
						match(DOT);
						setState(289);
						match(IDENTIFIER);
						}
						break;
					case 4:
						{
						_localctx = new Expr7Context(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr7);
						setState(290);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(291);
						match(LPAR);
						setState(292);
						exprs_in();
						setState(293);
						match(RPAR);
						}
						break;
					}
					} 
				}
				setState(299);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Exprs_inContext extends ParserRuleContext {
		public ExprsContext exprs() {
			return getRuleContext(ExprsContext.class,0);
		}
		public Exprs_inContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprs_in; }
	}

	public final Exprs_inContext exprs_in() throws RecognitionException {
		Exprs_inContext _localctx = new Exprs_inContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_exprs_in);
		try {
			setState(302);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTCONST:
			case CHARCONST:
			case STRCONST:
			case PLUS:
			case MINUS:
			case NOT:
			case POW:
			case LBRACE:
			case LPAR:
			case FALSE:
			case NULL:
			case SIZEOF:
			case TRUE:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(300);
				exprs();
				}
				break;
			case RPAR:
				enterOuterAlt(_localctx, 2);
				{
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

	@SuppressWarnings("CheckReturnValue")
	public static class ExprsContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Exprs2Context exprs2() {
			return getRuleContext(Exprs2Context.class,0);
		}
		public ExprsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprs; }
	}

	public final ExprsContext exprs() throws RecognitionException {
		ExprsContext _localctx = new ExprsContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_exprs);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(304);
			expr(0);
			setState(305);
			exprs2();
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

	@SuppressWarnings("CheckReturnValue")
	public static class Exprs2Context extends ParserRuleContext {
		public TerminalNode COMMA() { return getToken(Prev25Parser.COMMA, 0); }
		public ExprsContext exprs() {
			return getRuleContext(ExprsContext.class,0);
		}
		public Exprs2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprs2; }
	}

	public final Exprs2Context exprs2() throws RecognitionException {
		Exprs2Context _localctx = new Exprs2Context(_ctx, getState());
		enterRule(_localctx, 50, RULE_exprs2);
		try {
			setState(310);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case COMMA:
				enterOuterAlt(_localctx, 1);
				{
				setState(307);
				match(COMMA);
				setState(308);
				exprs();
				}
				break;
			case RPAR:
				enterOuterAlt(_localctx, 2);
				{
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

	@SuppressWarnings("CheckReturnValue")
	public static class TerminalsContext extends ParserRuleContext {
		public TerminalNode INTCONST() { return getToken(Prev25Parser.INTCONST, 0); }
		public TerminalNode STRCONST() { return getToken(Prev25Parser.STRCONST, 0); }
		public TerminalNode CHARCONST() { return getToken(Prev25Parser.CHARCONST, 0); }
		public TerminalNode TRUE() { return getToken(Prev25Parser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(Prev25Parser.FALSE, 0); }
		public TerminalNode NULL() { return getToken(Prev25Parser.NULL, 0); }
		public TerminalNode IDENTIFIER() { return getToken(Prev25Parser.IDENTIFIER, 0); }
		public TerminalsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_terminals; }
	}

	public final TerminalsContext terminals() throws RecognitionException {
		TerminalsContext _localctx = new TerminalsContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_terminals);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(312);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 581658830962702L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	@SuppressWarnings("CheckReturnValue")
	public static class Multiplicative_opsContext extends ParserRuleContext {
		public TerminalNode STAR() { return getToken(Prev25Parser.STAR, 0); }
		public TerminalNode DIV() { return getToken(Prev25Parser.DIV, 0); }
		public TerminalNode MOD() { return getToken(Prev25Parser.MOD, 0); }
		public Multiplicative_opsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicative_ops; }
	}

	public final Multiplicative_opsContext multiplicative_ops() throws RecognitionException {
		Multiplicative_opsContext _localctx = new Multiplicative_opsContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_multiplicative_ops);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(314);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 114688L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	@SuppressWarnings("CheckReturnValue")
	public static class Additive_opsContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(Prev25Parser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(Prev25Parser.MINUS, 0); }
		public Additive_opsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additive_ops; }
	}

	public final Additive_opsContext additive_ops() throws RecognitionException {
		Additive_opsContext _localctx = new Additive_opsContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_additive_ops);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(316);
			_la = _input.LA(1);
			if ( !(_la==PLUS || _la==MINUS) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	@SuppressWarnings("CheckReturnValue")
	public static class Comparitive_opsContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(Prev25Parser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(Prev25Parser.NEQ, 0); }
		public TerminalNode LTE() { return getToken(Prev25Parser.LTE, 0); }
		public TerminalNode GTE() { return getToken(Prev25Parser.GTE, 0); }
		public TerminalNode LT() { return getToken(Prev25Parser.LT, 0); }
		public TerminalNode GT() { return getToken(Prev25Parser.GT, 0); }
		public Comparitive_opsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparitive_ops; }
	}

	public final Comparitive_opsContext comparitive_ops() throws RecognitionException {
		Comparitive_opsContext _localctx = new Comparitive_opsContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_comparitive_ops);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(318);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 16128L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	@SuppressWarnings("CheckReturnValue")
	public static class PrefixContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(Prev25Parser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(Prev25Parser.MINUS, 0); }
		public TerminalNode NOT() { return getToken(Prev25Parser.NOT, 0); }
		public PrefixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prefix; }
	}

	public final PrefixContext prefix() throws RecognitionException {
		PrefixContext _localctx = new PrefixContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_prefix);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(320);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 131120L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 16:
			return expr_sempred((ExprContext)_localctx, predIndex);
		case 17:
			return expr2_sempred((Expr2Context)_localctx, predIndex);
		case 19:
			return expr4_sempred((Expr4Context)_localctx, predIndex);
		case 20:
			return expr5_sempred((Expr5Context)_localctx, predIndex);
		case 22:
			return expr7_sempred((Expr7Context)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean expr2_sempred(Expr2Context _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean expr4_sempred(Expr4Context _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean expr5_sempred(Expr5Context _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean expr7_sempred(Expr7Context _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return precpred(_ctx, 8);
		case 5:
			return precpred(_ctx, 5);
		case 6:
			return precpred(_ctx, 3);
		case 7:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u00014\u0143\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0003\u0001F\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0003\u0002Y\b\u0002\u0001\u0003\u0001\u0003"+
		"\u0003\u0003]\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004"+
		"b\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0003\u0006j\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\bu\b\b\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t}\b\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u0097\b\n\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0003\u000b\u009c\b\u000b\u0001\f\u0001\f\u0001\f\u0003"+
		"\f\u00a1\b\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003"+
		"\r\u00bc\b\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e"+
		"\u00c2\b\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u00c7\b"+
		"\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0005\u0010\u00cf\b\u0010\n\u0010\f\u0010\u00d2\t\u0010\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0005\u0011"+
		"\u00da\b\u0011\n\u0011\f\u0011\u00dd\t\u0011\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u00e4\b\u0012\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0005"+
		"\u0013\u00ed\b\u0013\n\u0013\f\u0013\u00f0\t\u0013\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0005\u0014"+
		"\u00f9\b\u0014\n\u0014\f\u0014\u00fc\t\u0014\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0003\u0015\u0107\b\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u0117"+
		"\b\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0005\u0016\u0128\b\u0016\n"+
		"\u0016\f\u0016\u012b\t\u0016\u0001\u0017\u0001\u0017\u0003\u0017\u012f"+
		"\b\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0003\u0019\u0137\b\u0019\u0001\u001a\u0001\u001a\u0001\u001b\u0001"+
		"\u001b\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d\u0001\u001e\u0001"+
		"\u001e\u0001\u001e\u0000\u0005 \"&(,\u001f\u0000\u0002\u0004\u0006\b\n"+
		"\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.0246"+
		"8:<\u0000\u0005\u0005\u0000\u0001\u0003\"\"((,,11\u0001\u0000\u000e\u0010"+
		"\u0001\u0000\u0004\u0005\u0001\u0000\b\r\u0002\u0000\u0004\u0005\u0011"+
		"\u0011\u014f\u0000>\u0001\u0000\u0000\u0000\u0002E\u0001\u0000\u0000\u0000"+
		"\u0004X\u0001\u0000\u0000\u0000\u0006\\\u0001\u0000\u0000\u0000\ba\u0001"+
		"\u0000\u0000\u0000\nc\u0001\u0000\u0000\u0000\fi\u0001\u0000\u0000\u0000"+
		"\u000ek\u0001\u0000\u0000\u0000\u0010t\u0001\u0000\u0000\u0000\u0012|"+
		"\u0001\u0000\u0000\u0000\u0014\u0096\u0001\u0000\u0000\u0000\u0016\u009b"+
		"\u0001\u0000\u0000\u0000\u0018\u00a0\u0001\u0000\u0000\u0000\u001a\u00bb"+
		"\u0001\u0000\u0000\u0000\u001c\u00c1\u0001\u0000\u0000\u0000\u001e\u00c6"+
		"\u0001\u0000\u0000\u0000 \u00c8\u0001\u0000\u0000\u0000\"\u00d3\u0001"+
		"\u0000\u0000\u0000$\u00e3\u0001\u0000\u0000\u0000&\u00e5\u0001\u0000\u0000"+
		"\u0000(\u00f1\u0001\u0000\u0000\u0000*\u0106\u0001\u0000\u0000\u0000,"+
		"\u0116\u0001\u0000\u0000\u0000.\u012e\u0001\u0000\u0000\u00000\u0130\u0001"+
		"\u0000\u0000\u00002\u0136\u0001\u0000\u0000\u00004\u0138\u0001\u0000\u0000"+
		"\u00006\u013a\u0001\u0000\u0000\u00008\u013c\u0001\u0000\u0000\u0000:"+
		"\u013e\u0001\u0000\u0000\u0000<\u0140\u0001\u0000\u0000\u0000>?\u0003"+
		"\u0002\u0001\u0000?@\u0005\u0000\u0000\u0001@\u0001\u0001\u0000\u0000"+
		"\u0000AB\u0003\u0004\u0002\u0000BC\u0003\u0002\u0001\u0000CF\u0001\u0000"+
		"\u0000\u0000DF\u0003\u0004\u0002\u0000EA\u0001\u0000\u0000\u0000ED\u0001"+
		"\u0000\u0000\u0000F\u0003\u0001\u0000\u0000\u0000GH\u0005-\u0000\u0000"+
		"HI\u00051\u0000\u0000IJ\u0005\u0014\u0000\u0000JY\u0003\u001a\r\u0000"+
		"KL\u0005.\u0000\u0000LM\u00051\u0000\u0000MN\u0005\u0015\u0000\u0000N"+
		"Y\u0003\u001a\r\u0000OP\u0005#\u0000\u0000PQ\u00051\u0000\u0000QR\u0005"+
		"\u0019\u0000\u0000RS\u0003\u0006\u0003\u0000ST\u0005\u001a\u0000\u0000"+
		"TU\u0005\u0015\u0000\u0000UV\u0003\u001a\r\u0000VW\u0003\b\u0004\u0000"+
		"WY\u0001\u0000\u0000\u0000XG\u0001\u0000\u0000\u0000XK\u0001\u0000\u0000"+
		"\u0000XO\u0001\u0000\u0000\u0000Y\u0005\u0001\u0000\u0000\u0000Z]\u0003"+
		"\n\u0005\u0000[]\u0001\u0000\u0000\u0000\\Z\u0001\u0000\u0000\u0000\\"+
		"[\u0001\u0000\u0000\u0000]\u0007\u0001\u0000\u0000\u0000^_\u0005\u0014"+
		"\u0000\u0000_b\u0003\u0010\b\u0000`b\u0001\u0000\u0000\u0000a^\u0001\u0000"+
		"\u0000\u0000a`\u0001\u0000\u0000\u0000b\t\u0001\u0000\u0000\u0000cd\u0003"+
		"\u000e\u0007\u0000de\u0003\f\u0006\u0000e\u000b\u0001\u0000\u0000\u0000"+
		"fg\u0005\u0016\u0000\u0000gj\u0003\n\u0005\u0000hj\u0001\u0000\u0000\u0000"+
		"if\u0001\u0000\u0000\u0000ih\u0001\u0000\u0000\u0000j\r\u0001\u0000\u0000"+
		"\u0000kl\u00051\u0000\u0000lm\u0005\u0015\u0000\u0000mn\u0003\u001a\r"+
		"\u0000n\u000f\u0001\u0000\u0000\u0000op\u0003\u0014\n\u0000pq\u0005\u0016"+
		"\u0000\u0000qr\u0003\u0010\b\u0000ru\u0001\u0000\u0000\u0000su\u0003\u0014"+
		"\n\u0000to\u0001\u0000\u0000\u0000ts\u0001\u0000\u0000\u0000u\u0011\u0001"+
		"\u0000\u0000\u0000vw\u0003\u0014\n\u0000wx\u0005\u0016\u0000\u0000xy\u0003"+
		"\u0010\b\u0000y}\u0001\u0000\u0000\u0000z}\u0003\u0014\n\u0000{}\u0001"+
		"\u0000\u0000\u0000|v\u0001\u0000\u0000\u0000|z\u0001\u0000\u0000\u0000"+
		"|{\u0001\u0000\u0000\u0000}\u0013\u0001\u0000\u0000\u0000~\u007f\u0003"+
		" \u0010\u0000\u007f\u0080\u0003\u0016\u000b\u0000\u0080\u0097\u0001\u0000"+
		"\u0000\u0000\u0081\u0082\u0005)\u0000\u0000\u0082\u0097\u0003 \u0010\u0000"+
		"\u0083\u0084\u00050\u0000\u0000\u0084\u0085\u0003 \u0010\u0000\u0085\u0086"+
		"\u0005\u001f\u0000\u0000\u0086\u0087\u0003\u0010\b\u0000\u0087\u0088\u0005"+
		"!\u0000\u0000\u0088\u0097\u0001\u0000\u0000\u0000\u0089\u008a\u0005$\u0000"+
		"\u0000\u008a\u008b\u0003 \u0010\u0000\u008b\u008c\u0005+\u0000\u0000\u008c"+
		"\u008d\u0003\u0012\t\u0000\u008d\u008e\u0003\u0018\f\u0000\u008e\u008f"+
		"\u0005!\u0000\u0000\u008f\u0097\u0001\u0000\u0000\u0000\u0090\u0091\u0005"+
		"\'\u0000\u0000\u0091\u0092\u0003\u0002\u0001\u0000\u0092\u0093\u0005%"+
		"\u0000\u0000\u0093\u0094\u0003\u0010\b\u0000\u0094\u0095\u0005!\u0000"+
		"\u0000\u0095\u0097\u0001\u0000\u0000\u0000\u0096~\u0001\u0000\u0000\u0000"+
		"\u0096\u0081\u0001\u0000\u0000\u0000\u0096\u0083\u0001\u0000\u0000\u0000"+
		"\u0096\u0089\u0001\u0000\u0000\u0000\u0096\u0090\u0001\u0000\u0000\u0000"+
		"\u0097\u0015\u0001\u0000\u0000\u0000\u0098\u0099\u0005\u0014\u0000\u0000"+
		"\u0099\u009c\u0003 \u0010\u0000\u009a\u009c\u0001\u0000\u0000\u0000\u009b"+
		"\u0098\u0001\u0000\u0000\u0000\u009b\u009a\u0001\u0000\u0000\u0000\u009c"+
		"\u0017\u0001\u0000\u0000\u0000\u009d\u009e\u0005 \u0000\u0000\u009e\u00a1"+
		"\u0003\u0012\t\u0000\u009f\u00a1\u0001\u0000\u0000\u0000\u00a0\u009d\u0001"+
		"\u0000\u0000\u0000\u00a0\u009f\u0001\u0000\u0000\u0000\u00a1\u0019\u0001"+
		"\u0000\u0000\u0000\u00a2\u00bc\u0005&\u0000\u0000\u00a3\u00bc\u0005\u001e"+
		"\u0000\u0000\u00a4\u00bc\u0005\u001d\u0000\u0000\u00a5\u00bc\u0005/\u0000"+
		"\u0000\u00a6\u00bc\u00051\u0000\u0000\u00a7\u00a8\u0005\u001b\u0000\u0000"+
		"\u00a8\u00a9\u0005\u0001\u0000\u0000\u00a9\u00aa\u0005\u001c\u0000\u0000"+
		"\u00aa\u00bc\u0003\u001a\r\u0000\u00ab\u00ac\u0005\u0013\u0000\u0000\u00ac"+
		"\u00bc\u0003\u001a\r\u0000\u00ad\u00ae\u0005\f\u0000\u0000\u00ae\u00af"+
		"\u0003\n\u0005\u0000\u00af\u00b0\u0005\r\u0000\u0000\u00b0\u00bc\u0001"+
		"\u0000\u0000\u0000\u00b1\u00b2\u0005\u0017\u0000\u0000\u00b2\u00b3\u0003"+
		"\n\u0005\u0000\u00b3\u00b4\u0005\u0018\u0000\u0000\u00b4\u00bc\u0001\u0000"+
		"\u0000\u0000\u00b5\u00b6\u0005\u0019\u0000\u0000\u00b6\u00b7\u0003\u001c"+
		"\u000e\u0000\u00b7\u00b8\u0005\u001a\u0000\u0000\u00b8\u00b9\u0005\u0015"+
		"\u0000\u0000\u00b9\u00ba\u0003\u001a\r\u0000\u00ba\u00bc\u0001\u0000\u0000"+
		"\u0000\u00bb\u00a2\u0001\u0000\u0000\u0000\u00bb\u00a3\u0001\u0000\u0000"+
		"\u0000\u00bb\u00a4\u0001\u0000\u0000\u0000\u00bb\u00a5\u0001\u0000\u0000"+
		"\u0000\u00bb\u00a6\u0001\u0000\u0000\u0000\u00bb\u00a7\u0001\u0000\u0000"+
		"\u0000\u00bb\u00ab\u0001\u0000\u0000\u0000\u00bb\u00ad\u0001\u0000\u0000"+
		"\u0000\u00bb\u00b1\u0001\u0000\u0000\u0000\u00bb\u00b5\u0001\u0000\u0000"+
		"\u0000\u00bc\u001b\u0001\u0000\u0000\u0000\u00bd\u00be\u0003\u001a\r\u0000"+
		"\u00be\u00bf\u0003\u001e\u000f\u0000\u00bf\u00c2\u0001\u0000\u0000\u0000"+
		"\u00c0\u00c2\u0001\u0000\u0000\u0000\u00c1\u00bd\u0001\u0000\u0000\u0000"+
		"\u00c1\u00c0\u0001\u0000\u0000\u0000\u00c2\u001d\u0001\u0000\u0000\u0000"+
		"\u00c3\u00c4\u0005\u0016\u0000\u0000\u00c4\u00c7\u0003\u001c\u000e\u0000"+
		"\u00c5\u00c7\u0001\u0000\u0000\u0000\u00c6\u00c3\u0001\u0000\u0000\u0000"+
		"\u00c6\u00c5\u0001\u0000\u0000\u0000\u00c7\u001f\u0001\u0000\u0000\u0000"+
		"\u00c8\u00c9\u0006\u0010\uffff\uffff\u0000\u00c9\u00ca\u0003\"\u0011\u0000"+
		"\u00ca\u00d0\u0001\u0000\u0000\u0000\u00cb\u00cc\n\u0002\u0000\u0000\u00cc"+
		"\u00cd\u0005\u0007\u0000\u0000\u00cd\u00cf\u0003\"\u0011\u0000\u00ce\u00cb"+
		"\u0001\u0000\u0000\u0000\u00cf\u00d2\u0001\u0000\u0000\u0000\u00d0\u00ce"+
		"\u0001\u0000\u0000\u0000\u00d0\u00d1\u0001\u0000\u0000\u0000\u00d1!\u0001"+
		"\u0000\u0000\u0000\u00d2\u00d0\u0001\u0000\u0000\u0000\u00d3\u00d4\u0006"+
		"\u0011\uffff\uffff\u0000\u00d4\u00d5\u0003$\u0012\u0000\u00d5\u00db\u0001"+
		"\u0000\u0000\u0000\u00d6\u00d7\n\u0002\u0000\u0000\u00d7\u00d8\u0005\u0006"+
		"\u0000\u0000\u00d8\u00da\u0003$\u0012\u0000\u00d9\u00d6\u0001\u0000\u0000"+
		"\u0000\u00da\u00dd\u0001\u0000\u0000\u0000\u00db\u00d9\u0001\u0000\u0000"+
		"\u0000\u00db\u00dc\u0001\u0000\u0000\u0000\u00dc#\u0001\u0000\u0000\u0000"+
		"\u00dd\u00db\u0001\u0000\u0000\u0000\u00de\u00df\u0003&\u0013\u0000\u00df"+
		"\u00e0\u0003:\u001d\u0000\u00e0\u00e1\u0003&\u0013\u0000\u00e1\u00e4\u0001"+
		"\u0000\u0000\u0000\u00e2\u00e4\u0003&\u0013\u0000\u00e3\u00de\u0001\u0000"+
		"\u0000\u0000\u00e3\u00e2\u0001\u0000\u0000\u0000\u00e4%\u0001\u0000\u0000"+
		"\u0000\u00e5\u00e6\u0006\u0013\uffff\uffff\u0000\u00e6\u00e7\u0003(\u0014"+
		"\u0000\u00e7\u00ee\u0001\u0000\u0000\u0000\u00e8\u00e9\n\u0002\u0000\u0000"+
		"\u00e9\u00ea\u00038\u001c\u0000\u00ea\u00eb\u0003(\u0014\u0000\u00eb\u00ed"+
		"\u0001\u0000\u0000\u0000\u00ec\u00e8\u0001\u0000\u0000\u0000\u00ed\u00f0"+
		"\u0001\u0000\u0000\u0000\u00ee\u00ec\u0001\u0000\u0000\u0000\u00ee\u00ef"+
		"\u0001\u0000\u0000\u0000\u00ef\'\u0001\u0000\u0000\u0000\u00f0\u00ee\u0001"+
		"\u0000\u0000\u0000\u00f1\u00f2\u0006\u0014\uffff\uffff\u0000\u00f2\u00f3"+
		"\u0003*\u0015\u0000\u00f3\u00fa\u0001\u0000\u0000\u0000\u00f4\u00f5\n"+
		"\u0002\u0000\u0000\u00f5\u00f6\u00036\u001b\u0000\u00f6\u00f7\u0003*\u0015"+
		"\u0000\u00f7\u00f9\u0001\u0000\u0000\u0000\u00f8\u00f4\u0001\u0000\u0000"+
		"\u0000\u00f9\u00fc\u0001\u0000\u0000\u0000\u00fa\u00f8\u0001\u0000\u0000"+
		"\u0000\u00fa\u00fb\u0001\u0000\u0000\u0000\u00fb)\u0001\u0000\u0000\u0000"+
		"\u00fc\u00fa\u0001\u0000\u0000\u0000\u00fd\u00fe\u0005\u0004\u0000\u0000"+
		"\u00fe\u0107\u0003*\u0015\u0000\u00ff\u0100\u0005\u0005\u0000\u0000\u0100"+
		"\u0107\u0003*\u0015\u0000\u0101\u0102\u0005\u0011\u0000\u0000\u0102\u0107"+
		"\u0003*\u0015\u0000\u0103\u0104\u0005\u0013\u0000\u0000\u0104\u0107\u0003"+
		"*\u0015\u0000\u0105\u0107\u0003,\u0016\u0000\u0106\u00fd\u0001\u0000\u0000"+
		"\u0000\u0106\u00ff\u0001\u0000\u0000\u0000\u0106\u0101\u0001\u0000\u0000"+
		"\u0000\u0106\u0103\u0001\u0000\u0000\u0000\u0106\u0105\u0001\u0000\u0000"+
		"\u0000\u0107+\u0001\u0000\u0000\u0000\u0108\u0109\u0006\u0016\uffff\uffff"+
		"\u0000\u0109\u010a\u0005\u0017\u0000\u0000\u010a\u010b\u0003 \u0010\u0000"+
		"\u010b\u010c\u0005\u0015\u0000\u0000\u010c\u010d\u0003\u001a\r\u0000\u010d"+
		"\u010e\u0005\u0018\u0000\u0000\u010e\u0117\u0001\u0000\u0000\u0000\u010f"+
		"\u0110\u0005\u0019\u0000\u0000\u0110\u0111\u0003 \u0010\u0000\u0111\u0112"+
		"\u0005\u001a\u0000\u0000\u0112\u0117\u0001\u0000\u0000\u0000\u0113\u0114"+
		"\u0005*\u0000\u0000\u0114\u0117\u0003\u001a\r\u0000\u0115\u0117\u0003"+
		"4\u001a\u0000\u0116\u0108\u0001\u0000\u0000\u0000\u0116\u010f\u0001\u0000"+
		"\u0000\u0000\u0116\u0113\u0001\u0000\u0000\u0000\u0116\u0115\u0001\u0000"+
		"\u0000\u0000\u0117\u0129\u0001\u0000\u0000\u0000\u0118\u0119\n\b\u0000"+
		"\u0000\u0119\u0128\u0005\u0013\u0000\u0000\u011a\u011b\n\u0005\u0000\u0000"+
		"\u011b\u011c\u0005\u001b\u0000\u0000\u011c\u011d\u0003 \u0010\u0000\u011d"+
		"\u011e\u0005\u001c\u0000\u0000\u011e\u0128\u0001\u0000\u0000\u0000\u011f"+
		"\u0120\n\u0003\u0000\u0000\u0120\u0121\u0005\u0012\u0000\u0000\u0121\u0128"+
		"\u00051\u0000\u0000\u0122\u0123\n\u0002\u0000\u0000\u0123\u0124\u0005"+
		"\u0019\u0000\u0000\u0124\u0125\u0003.\u0017\u0000\u0125\u0126\u0005\u001a"+
		"\u0000\u0000\u0126\u0128\u0001\u0000\u0000\u0000\u0127\u0118\u0001\u0000"+
		"\u0000\u0000\u0127\u011a\u0001\u0000\u0000\u0000\u0127\u011f\u0001\u0000"+
		"\u0000\u0000\u0127\u0122\u0001\u0000\u0000\u0000\u0128\u012b\u0001\u0000"+
		"\u0000\u0000\u0129\u0127\u0001\u0000\u0000\u0000\u0129\u012a\u0001\u0000"+
		"\u0000\u0000\u012a-\u0001\u0000\u0000\u0000\u012b\u0129\u0001\u0000\u0000"+
		"\u0000\u012c\u012f\u00030\u0018\u0000\u012d\u012f\u0001\u0000\u0000\u0000"+
		"\u012e\u012c\u0001\u0000\u0000\u0000\u012e\u012d\u0001\u0000\u0000\u0000"+
		"\u012f/\u0001\u0000\u0000\u0000\u0130\u0131\u0003 \u0010\u0000\u0131\u0132"+
		"\u00032\u0019\u0000\u01321\u0001\u0000\u0000\u0000\u0133\u0134\u0005\u0016"+
		"\u0000\u0000\u0134\u0137\u00030\u0018\u0000\u0135\u0137\u0001\u0000\u0000"+
		"\u0000\u0136\u0133\u0001\u0000\u0000\u0000\u0136\u0135\u0001\u0000\u0000"+
		"\u0000\u01373\u0001\u0000\u0000\u0000\u0138\u0139\u0007\u0000\u0000\u0000"+
		"\u01395\u0001\u0000\u0000\u0000\u013a\u013b\u0007\u0001\u0000\u0000\u013b"+
		"7\u0001\u0000\u0000\u0000\u013c\u013d\u0007\u0002\u0000\u0000\u013d9\u0001"+
		"\u0000\u0000\u0000\u013e\u013f\u0007\u0003\u0000\u0000\u013f;\u0001\u0000"+
		"\u0000\u0000\u0140\u0141\u0007\u0004\u0000\u0000\u0141=\u0001\u0000\u0000"+
		"\u0000\u0018EX\\ait|\u0096\u009b\u00a0\u00bb\u00c1\u00c6\u00d0\u00db\u00e3"+
		"\u00ee\u00fa\u0106\u0116\u0127\u0129\u012e\u0136";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}