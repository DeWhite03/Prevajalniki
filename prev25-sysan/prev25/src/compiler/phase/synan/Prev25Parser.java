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
		RULE_source = 0, RULE_defs = 1, RULE_def = 2, RULE_implementation = 3, 
		RULE_args = 4, RULE_args2 = 5, RULE_arg = 6, RULE_statements = 7, RULE_statement = 8, 
		RULE_expr_extension = 9, RULE_else = 10, RULE_type = 11, RULE_expression = 12;
	private static String[] makeRuleNames() {
		return new String[] {
			"source", "defs", "def", "implementation", "args", "args2", "arg", "statements", 
			"statement", "expr_extension", "else", "type", "expression"
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



		private Location loc(Token tok) { return new Location((LexAn.LocLogToken)tok); }
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
			setState(26);
			defs();
			setState(27);
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
			setState(33);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(29);
				def();
				setState(30);
				defs();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(32);
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
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
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
			setState(52);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYP:
				enterOuterAlt(_localctx, 1);
				{
				setState(35);
				match(TYP);
				setState(36);
				match(IDENTIFIER);
				setState(37);
				match(ASSIGNMENT);
				setState(38);
				type();
				}
				break;
			case VAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(39);
				match(VAR);
				setState(40);
				match(IDENTIFIER);
				setState(41);
				match(COLON);
				setState(42);
				type();
				}
				break;
			case FUN:
				enterOuterAlt(_localctx, 3);
				{
				setState(43);
				match(FUN);
				setState(44);
				match(IDENTIFIER);
				setState(45);
				match(LPAR);
				setState(46);
				args();
				setState(47);
				match(RPAR);
				setState(48);
				match(COLON);
				setState(49);
				type();
				setState(50);
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
		enterRule(_localctx, 6, RULE_implementation);
		try {
			setState(57);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ASSIGNMENT:
				enterOuterAlt(_localctx, 1);
				{
				setState(54);
				match(ASSIGNMENT);
				setState(55);
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
		enterRule(_localctx, 8, RULE_args);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			arg();
			setState(60);
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
		enterRule(_localctx, 10, RULE_args2);
		try {
			setState(65);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case COMMA:
				enterOuterAlt(_localctx, 1);
				{
				setState(62);
				match(COMMA);
				setState(63);
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
		enterRule(_localctx, 12, RULE_arg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			match(IDENTIFIER);
			setState(68);
			match(COLON);
			setState(69);
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
		enterRule(_localctx, 14, RULE_statements);
		try {
			setState(76);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(71);
				statement();
				setState(72);
				match(COMMA);
				setState(73);
				statements();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(75);
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
	public static class StatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
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
		enterRule(_localctx, 16, RULE_statement);
		try {
			setState(102);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTCONST:
			case CHARCONST:
			case STRCONST:
			case FALSE:
			case NULL:
			case TRUE:
				enterOuterAlt(_localctx, 1);
				{
				setState(78);
				expression();
				setState(79);
				expr_extension();
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 2);
				{
				setState(81);
				match(RETURN);
				setState(82);
				expression();
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 3);
				{
				setState(83);
				match(WHILE);
				setState(84);
				expression();
				setState(85);
				match(DO);
				setState(86);
				statements();
				setState(87);
				match(END);
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 4);
				{
				setState(89);
				match(IF);
				setState(90);
				expression();
				setState(91);
				match(THEN);
				setState(92);
				statements();
				setState(93);
				else_();
				setState(94);
				match(END);
				}
				break;
			case LET:
				enterOuterAlt(_localctx, 5);
				{
				setState(96);
				match(LET);
				setState(97);
				defs();
				setState(98);
				match(IN);
				setState(99);
				statements();
				setState(100);
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
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Expr_extensionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_extension; }
	}

	public final Expr_extensionContext expr_extension() throws RecognitionException {
		Expr_extensionContext _localctx = new Expr_extensionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_expr_extension);
		try {
			setState(107);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ASSIGNMENT:
				enterOuterAlt(_localctx, 1);
				{
				setState(104);
				match(ASSIGNMENT);
				setState(105);
				expression();
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
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public ElseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_else; }
	}

	public final ElseContext else_() throws RecognitionException {
		ElseContext _localctx = new ElseContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_else);
		try {
			setState(112);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ELSE:
				enterOuterAlt(_localctx, 1);
				{
				setState(109);
				match(ELSE);
				setState(110);
				statements();
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
		public TerminalNode RPAR() { return getToken(Prev25Parser.RPAR, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_type);
		try {
			setState(137);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(114);
				match(INT);
				}
				break;
			case CHAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(115);
				match(CHAR);
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 3);
				{
				setState(116);
				match(BOOL);
				}
				break;
			case VOID:
				enterOuterAlt(_localctx, 4);
				{
				setState(117);
				match(VOID);
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 5);
				{
				setState(118);
				match(IDENTIFIER);
				}
				break;
			case LBRACKET:
				enterOuterAlt(_localctx, 6);
				{
				setState(119);
				match(LBRACKET);
				setState(120);
				match(INTCONST);
				setState(121);
				match(RBRACKET);
				setState(122);
				type();
				}
				break;
			case POW:
				enterOuterAlt(_localctx, 7);
				{
				setState(123);
				match(POW);
				setState(124);
				type();
				}
				break;
			case LT:
				enterOuterAlt(_localctx, 8);
				{
				setState(125);
				match(LT);
				setState(126);
				args();
				setState(127);
				match(GT);
				}
				break;
			case LBRACE:
				enterOuterAlt(_localctx, 9);
				{
				setState(129);
				match(LBRACE);
				setState(130);
				args();
				setState(131);
				match(RBRACE);
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 10);
				{
				setState(133);
				match(LPAR);
				setState(134);
				type();
				setState(135);
				match(RPAR);
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
	public static class ExpressionContext extends ParserRuleContext {
		public TerminalNode INTCONST() { return getToken(Prev25Parser.INTCONST, 0); }
		public TerminalNode STRCONST() { return getToken(Prev25Parser.STRCONST, 0); }
		public TerminalNode CHARCONST() { return getToken(Prev25Parser.CHARCONST, 0); }
		public TerminalNode TRUE() { return getToken(Prev25Parser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(Prev25Parser.FALSE, 0); }
		public TerminalNode NULL() { return getToken(Prev25Parser.NULL, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 18708877541390L) != 0)) ) {
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

	public static final String _serializedATN =
		"\u0004\u00014\u008e\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0003\u0001\"\b\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u00025\b\u0002\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0003\u0003:\b\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005B\b\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007M\b\u0007\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\bg\b\b\u0001\t\u0001\t\u0001"+
		"\t\u0003\tl\b\t\u0001\n\u0001\n\u0001\n\u0003\nq\b\n\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u008a\b\u000b\u0001"+
		"\f\u0001\f\u0001\f\u0000\u0000\r\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010"+
		"\u0012\u0014\u0016\u0018\u0000\u0001\u0004\u0000\u0001\u0003\"\"((,,\u0095"+
		"\u0000\u001a\u0001\u0000\u0000\u0000\u0002!\u0001\u0000\u0000\u0000\u0004"+
		"4\u0001\u0000\u0000\u0000\u00069\u0001\u0000\u0000\u0000\b;\u0001\u0000"+
		"\u0000\u0000\nA\u0001\u0000\u0000\u0000\fC\u0001\u0000\u0000\u0000\u000e"+
		"L\u0001\u0000\u0000\u0000\u0010f\u0001\u0000\u0000\u0000\u0012k\u0001"+
		"\u0000\u0000\u0000\u0014p\u0001\u0000\u0000\u0000\u0016\u0089\u0001\u0000"+
		"\u0000\u0000\u0018\u008b\u0001\u0000\u0000\u0000\u001a\u001b\u0003\u0002"+
		"\u0001\u0000\u001b\u001c\u0005\u0000\u0000\u0001\u001c\u0001\u0001\u0000"+
		"\u0000\u0000\u001d\u001e\u0003\u0004\u0002\u0000\u001e\u001f\u0003\u0002"+
		"\u0001\u0000\u001f\"\u0001\u0000\u0000\u0000 \"\u0003\u0004\u0002\u0000"+
		"!\u001d\u0001\u0000\u0000\u0000! \u0001\u0000\u0000\u0000\"\u0003\u0001"+
		"\u0000\u0000\u0000#$\u0005-\u0000\u0000$%\u00051\u0000\u0000%&\u0005\u0014"+
		"\u0000\u0000&5\u0003\u0016\u000b\u0000\'(\u0005.\u0000\u0000()\u00051"+
		"\u0000\u0000)*\u0005\u0015\u0000\u0000*5\u0003\u0016\u000b\u0000+,\u0005"+
		"#\u0000\u0000,-\u00051\u0000\u0000-.\u0005\u0019\u0000\u0000./\u0003\b"+
		"\u0004\u0000/0\u0005\u001a\u0000\u000001\u0005\u0015\u0000\u000012\u0003"+
		"\u0016\u000b\u000023\u0003\u0006\u0003\u000035\u0001\u0000\u0000\u0000"+
		"4#\u0001\u0000\u0000\u00004\'\u0001\u0000\u0000\u00004+\u0001\u0000\u0000"+
		"\u00005\u0005\u0001\u0000\u0000\u000067\u0005\u0014\u0000\u00007:\u0003"+
		"\u000e\u0007\u00008:\u0001\u0000\u0000\u000096\u0001\u0000\u0000\u0000"+
		"98\u0001\u0000\u0000\u0000:\u0007\u0001\u0000\u0000\u0000;<\u0003\f\u0006"+
		"\u0000<=\u0003\n\u0005\u0000=\t\u0001\u0000\u0000\u0000>?\u0005\u0016"+
		"\u0000\u0000?B\u0003\b\u0004\u0000@B\u0001\u0000\u0000\u0000A>\u0001\u0000"+
		"\u0000\u0000A@\u0001\u0000\u0000\u0000B\u000b\u0001\u0000\u0000\u0000"+
		"CD\u00051\u0000\u0000DE\u0005\u0015\u0000\u0000EF\u0003\u0016\u000b\u0000"+
		"F\r\u0001\u0000\u0000\u0000GH\u0003\u0010\b\u0000HI\u0005\u0016\u0000"+
		"\u0000IJ\u0003\u000e\u0007\u0000JM\u0001\u0000\u0000\u0000KM\u0003\u0010"+
		"\b\u0000LG\u0001\u0000\u0000\u0000LK\u0001\u0000\u0000\u0000M\u000f\u0001"+
		"\u0000\u0000\u0000NO\u0003\u0018\f\u0000OP\u0003\u0012\t\u0000Pg\u0001"+
		"\u0000\u0000\u0000QR\u0005)\u0000\u0000Rg\u0003\u0018\f\u0000ST\u0005"+
		"0\u0000\u0000TU\u0003\u0018\f\u0000UV\u0005\u001f\u0000\u0000VW\u0003"+
		"\u000e\u0007\u0000WX\u0005!\u0000\u0000Xg\u0001\u0000\u0000\u0000YZ\u0005"+
		"$\u0000\u0000Z[\u0003\u0018\f\u0000[\\\u0005+\u0000\u0000\\]\u0003\u000e"+
		"\u0007\u0000]^\u0003\u0014\n\u0000^_\u0005!\u0000\u0000_g\u0001\u0000"+
		"\u0000\u0000`a\u0005\'\u0000\u0000ab\u0003\u0002\u0001\u0000bc\u0005%"+
		"\u0000\u0000cd\u0003\u000e\u0007\u0000de\u0005!\u0000\u0000eg\u0001\u0000"+
		"\u0000\u0000fN\u0001\u0000\u0000\u0000fQ\u0001\u0000\u0000\u0000fS\u0001"+
		"\u0000\u0000\u0000fY\u0001\u0000\u0000\u0000f`\u0001\u0000\u0000\u0000"+
		"g\u0011\u0001\u0000\u0000\u0000hi\u0005\u0014\u0000\u0000il\u0003\u0018"+
		"\f\u0000jl\u0001\u0000\u0000\u0000kh\u0001\u0000\u0000\u0000kj\u0001\u0000"+
		"\u0000\u0000l\u0013\u0001\u0000\u0000\u0000mn\u0005 \u0000\u0000nq\u0003"+
		"\u000e\u0007\u0000oq\u0001\u0000\u0000\u0000pm\u0001\u0000\u0000\u0000"+
		"po\u0001\u0000\u0000\u0000q\u0015\u0001\u0000\u0000\u0000r\u008a\u0005"+
		"&\u0000\u0000s\u008a\u0005\u001e\u0000\u0000t\u008a\u0005\u001d\u0000"+
		"\u0000u\u008a\u0005/\u0000\u0000v\u008a\u00051\u0000\u0000wx\u0005\u001b"+
		"\u0000\u0000xy\u0005\u0001\u0000\u0000yz\u0005\u001c\u0000\u0000z\u008a"+
		"\u0003\u0016\u000b\u0000{|\u0005\u0013\u0000\u0000|\u008a\u0003\u0016"+
		"\u000b\u0000}~\u0005\f\u0000\u0000~\u007f\u0003\b\u0004\u0000\u007f\u0080"+
		"\u0005\r\u0000\u0000\u0080\u008a\u0001\u0000\u0000\u0000\u0081\u0082\u0005"+
		"\u0017\u0000\u0000\u0082\u0083\u0003\b\u0004\u0000\u0083\u0084\u0005\u0018"+
		"\u0000\u0000\u0084\u008a\u0001\u0000\u0000\u0000\u0085\u0086\u0005\u0019"+
		"\u0000\u0000\u0086\u0087\u0003\u0016\u000b\u0000\u0087\u0088\u0005\u001a"+
		"\u0000\u0000\u0088\u008a\u0001\u0000\u0000\u0000\u0089r\u0001\u0000\u0000"+
		"\u0000\u0089s\u0001\u0000\u0000\u0000\u0089t\u0001\u0000\u0000\u0000\u0089"+
		"u\u0001\u0000\u0000\u0000\u0089v\u0001\u0000\u0000\u0000\u0089w\u0001"+
		"\u0000\u0000\u0000\u0089{\u0001\u0000\u0000\u0000\u0089}\u0001\u0000\u0000"+
		"\u0000\u0089\u0081\u0001\u0000\u0000\u0000\u0089\u0085\u0001\u0000\u0000"+
		"\u0000\u008a\u0017\u0001\u0000\u0000\u0000\u008b\u008c\u0007\u0000\u0000"+
		"\u0000\u008c\u0019\u0001\u0000\u0000\u0000\t!49ALfkp\u0089";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}