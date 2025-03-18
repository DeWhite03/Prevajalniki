// Generated from Prev25Parser.g4 by ANTLR 4.13.2


	package compiler.phase.synan;
	
	import java.util.*;
	import compiler.common.report.*;
	import compiler.phase.lexan.*;
	import compiler.phase.abstr.*;


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
		RULE_comps = 14, RULE_comps2 = 15, RULE_comp = 16, RULE_types = 17, RULE_types2 = 18, 
		RULE_expr = 19, RULE_expr2 = 20, RULE_expr3 = 21, RULE_expr4 = 22, RULE_expr5 = 23, 
		RULE_expr6 = 24, RULE_expr7 = 25, RULE_exprs_in = 26, RULE_exprs = 27, 
		RULE_exprs2 = 28, RULE_terminals = 29, RULE_multiplicative_ops = 30, RULE_additive_ops = 31, 
		RULE_comparitive_ops = 32;
	private static String[] makeRuleNames() {
		return new String[] {
			"source", "defs", "def", "args1", "implementation", "args", "args2", 
			"arg", "statements", "empty_statements", "statement", "expr_extension", 
			"else", "type", "comps", "comps2", "comp", "types", "types2", "expr", 
			"expr2", "expr3", "expr4", "expr5", "expr6", "expr7", "exprs_in", "exprs", 
			"exprs2", "terminals", "multiplicative_ops", "additive_ops", "comparitive_ops"
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
		private Location loc(Locatable tok) { return new Location(tok); }
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
		public AST.Nodes<AST.FullDefn> ast;
		public DefsContext defs;
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
			setState(66);
			((SourceContext)_localctx).defs = defs(0);
			setState(67);
			match(EOF);
			 ((SourceContext)_localctx).ast =  new AST.Nodes<AST.FullDefn> (((SourceContext)_localctx).defs.ast); 
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
		public List<AST.FullDefn> ast;
		public DefsContext d;
		public DefContext def;
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
		return defs(0);
	}

	private DefsContext defs(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		DefsContext _localctx = new DefsContext(_ctx, _parentState);
		DefsContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_defs, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(71);
			((DefsContext)_localctx).def = def();
			 ((DefsContext)_localctx).ast =  new ArrayList<AST.FullDefn>(); _localctx.ast.addLast(((DefsContext)_localctx).def.ast); 
			}
			_ctx.stop = _input.LT(-1);
			setState(80);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new DefsContext(_parentctx, _parentState);
					_localctx.d = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_defs);
					setState(74);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(75);
					((DefsContext)_localctx).def = def();
					 ((DefsContext)_localctx).ast =  ((DefsContext)_localctx).d.ast; _localctx.ast.addLast(((DefsContext)_localctx).def.ast); 
					}
					} 
				}
				setState(82);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
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
	public static class DefContext extends ParserRuleContext {
		public AST.FullDefn ast;
		public Token TYP;
		public Token IDENTIFIER;
		public TypeContext type;
		public Token VAR;
		public Token FUN;
		public Args1Context args1;
		public ImplementationContext impl;
		public ImplementationContext implementation;
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
			setState(105);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYP:
				enterOuterAlt(_localctx, 1);
				{
				setState(83);
				((DefContext)_localctx).TYP = match(TYP);
				setState(84);
				((DefContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				setState(85);
				match(ASSIGNMENT);
				setState(86);
				((DefContext)_localctx).type = type();
				 ((DefContext)_localctx).ast =  new AST.TypDefn(loc(((DefContext)_localctx).TYP, ((DefContext)_localctx).type.ast), ((DefContext)_localctx).IDENTIFIER.getText(), ((DefContext)_localctx).type.ast); 
				}
				break;
			case VAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(89);
				((DefContext)_localctx).VAR = match(VAR);
				setState(90);
				((DefContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				setState(91);
				match(COLON);
				setState(92);
				((DefContext)_localctx).type = type();
				 ((DefContext)_localctx).ast =  new AST.VarDefn(loc(((DefContext)_localctx).VAR, ((DefContext)_localctx).type.ast), ((DefContext)_localctx).IDENTIFIER.getText(), ((DefContext)_localctx).type.ast); 
				}
				break;
			case FUN:
				enterOuterAlt(_localctx, 3);
				{
				setState(95);
				((DefContext)_localctx).FUN = match(FUN);
				setState(96);
				((DefContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				setState(97);
				match(LPAR);
				setState(98);
				((DefContext)_localctx).args1 = args1();
				setState(99);
				match(RPAR);
				setState(100);
				match(COLON);
				setState(101);
				((DefContext)_localctx).type = type();
				setState(102);
				((DefContext)_localctx).impl = ((DefContext)_localctx).implementation = implementation(((DefContext)_localctx).FUN, ((DefContext)_localctx).IDENTIFIER, ((DefContext)_localctx).args1.ast, ((DefContext)_localctx).type.ast);
				 ((DefContext)_localctx).ast =  ((DefContext)_localctx).implementation.ast; 
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
		public List<AST.ParDefn> ast;
		public ArgsContext args;
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
			setState(111);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(107);
				((Args1Context)_localctx).args = args();
				 ((Args1Context)_localctx).ast =  ((Args1Context)_localctx).args.ast; 
				}
				break;
			case RPAR:
				enterOuterAlt(_localctx, 2);
				{
				 ((Args1Context)_localctx).ast =  new ArrayList<AST.ParDefn>(); 
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
		public Token fun;
		public Token id;
		public List<AST.ParDefn> impl_args;
		public AST.Type impl_type;
		public AST.FunDefn ast;
		public StatementsContext statements;
		public TerminalNode ASSIGNMENT() { return getToken(Prev25Parser.ASSIGNMENT, 0); }
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public ImplementationContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ImplementationContext(ParserRuleContext parent, int invokingState, Token fun, Token id, List<AST.ParDefn> impl_args, AST.Type impl_type) {
			super(parent, invokingState);
			this.fun = fun;
			this.id = id;
			this.impl_args = impl_args;
			this.impl_type = impl_type;
		}
		@Override public int getRuleIndex() { return RULE_implementation; }
	}

	public final ImplementationContext implementation(Token fun,Token id,List<AST.ParDefn> impl_args,AST.Type impl_type) throws RecognitionException {
		ImplementationContext _localctx = new ImplementationContext(_ctx, getState(), fun, id, impl_args, impl_type);
		enterRule(_localctx, 8, RULE_implementation);
		try {
			setState(118);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(113);
				match(ASSIGNMENT);
				setState(114);
				((ImplementationContext)_localctx).statements = statements(0);
				 ((ImplementationContext)_localctx).ast =  new AST.DefFunDefn(loc(fun, ((ImplementationContext)_localctx).statements.ast.get(((ImplementationContext)_localctx).statements.ast.size() - 1)), id.getText(), impl_args, impl_type, ((ImplementationContext)_localctx).statements.ast ); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				 ((ImplementationContext)_localctx).ast =  new AST.ExtFunDefn(loc(fun, impl_type), id.getText(), impl_args, impl_type); 
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
	public static class ArgsContext extends ParserRuleContext {
		public List<AST.ParDefn> ast;
		public ArgContext arg;
		public Args2Context args2;
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
			setState(120);
			((ArgsContext)_localctx).arg = arg();
			setState(121);
			((ArgsContext)_localctx).args2 = args2();
			 ((ArgsContext)_localctx).ast =  ((ArgsContext)_localctx).args2.ast; _localctx.ast.add(0, ((ArgsContext)_localctx).arg.ast); 
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
		public List<AST.ParDefn> ast;
		public ArgsContext args;
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
			setState(129);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case COMMA:
				enterOuterAlt(_localctx, 1);
				{
				setState(124);
				match(COMMA);
				setState(125);
				((Args2Context)_localctx).args = args();
				 ((Args2Context)_localctx).ast =  ((Args2Context)_localctx).args.ast; 
				}
				break;
			case RPAR:
				enterOuterAlt(_localctx, 2);
				{
				 ((Args2Context)_localctx).ast =  new ArrayList<AST.ParDefn>(); 
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
		public AST.ParDefn ast;
		public Token IDENTIFIER;
		public TypeContext type;
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
			setState(131);
			((ArgContext)_localctx).IDENTIFIER = match(IDENTIFIER);
			setState(132);
			match(COLON);
			setState(133);
			((ArgContext)_localctx).type = type();
			 ((ArgContext)_localctx).ast =  new AST.ParDefn(loc(((ArgContext)_localctx).IDENTIFIER, ((ArgContext)_localctx).type.ast), ((ArgContext)_localctx).IDENTIFIER.getText(), ((ArgContext)_localctx).type.ast); 
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
		public List<AST.Stmt> ast;
		public StatementsContext s;
		public StatementContext statement;
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
		return statements(0);
	}

	private StatementsContext statements(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		StatementsContext _localctx = new StatementsContext(_ctx, _parentState);
		StatementsContext _prevctx = _localctx;
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_statements, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(137);
			((StatementsContext)_localctx).statement = statement();
			 ((StatementsContext)_localctx).ast =  new ArrayList<AST.Stmt>(); _localctx.ast.addLast(((StatementsContext)_localctx).statement.ast); 
			}
			_ctx.stop = _input.LT(-1);
			setState(147);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new StatementsContext(_parentctx, _parentState);
					_localctx.s = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_statements);
					setState(140);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(141);
					match(COMMA);
					setState(142);
					((StatementsContext)_localctx).statement = statement();
					 ((StatementsContext)_localctx).ast =  ((StatementsContext)_localctx).s.ast; _localctx.ast.addLast(((StatementsContext)_localctx).statement.ast); 
					}
					} 
				}
				setState(149);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
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
	public static class Empty_statementsContext extends ParserRuleContext {
		public List<AST.Stmt> ast;
		public StatementsContext statements;
		public StatementContext statement;
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(Prev25Parser.COMMA, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
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
			setState(159);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(150);
				((Empty_statementsContext)_localctx).statements = statements(0);
				setState(151);
				match(COMMA);
				setState(152);
				((Empty_statementsContext)_localctx).statement = statement();
				 ((Empty_statementsContext)_localctx).ast =  ((Empty_statementsContext)_localctx).statements.ast; _localctx.ast.addLast(((Empty_statementsContext)_localctx).statement.ast); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(155);
				((Empty_statementsContext)_localctx).statement = statement();
				 ((Empty_statementsContext)_localctx).ast =  new ArrayList<AST.Stmt>(); _localctx.ast.addLast(((Empty_statementsContext)_localctx).statement.ast); 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				 ((Empty_statementsContext)_localctx).ast =  new ArrayList<AST.Stmt>(); 
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
		public AST.Stmt ast;
		public ExprContext expr;
		public Expr_extensionContext expr_extension;
		public Token RETURN;
		public Token WHILE;
		public StatementsContext statements;
		public Token END;
		public Token IF;
		public Empty_statementsContext empty_statements;
		public ElseContext e;
		public Token LET;
		public DefsContext defs;
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
			setState(190);
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
				setState(161);
				((StatementContext)_localctx).expr = expr(0);
				setState(162);
				((StatementContext)_localctx).expr_extension = expr_extension(((StatementContext)_localctx).expr.ast);
				 ((StatementContext)_localctx).ast =  ((StatementContext)_localctx).expr_extension.ast; 
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 2);
				{
				setState(165);
				((StatementContext)_localctx).RETURN = match(RETURN);
				setState(166);
				((StatementContext)_localctx).expr = expr(0);
				 ((StatementContext)_localctx).ast =  new AST.ReturnStmt(loc(((StatementContext)_localctx).RETURN, ((StatementContext)_localctx).expr.ast), ((StatementContext)_localctx).expr.ast); 
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 3);
				{
				setState(169);
				((StatementContext)_localctx).WHILE = match(WHILE);
				setState(170);
				((StatementContext)_localctx).expr = expr(0);
				setState(171);
				match(DO);
				setState(172);
				((StatementContext)_localctx).statements = statements(0);
				setState(173);
				((StatementContext)_localctx).END = match(END);
				 ((StatementContext)_localctx).ast =  new AST.WhileStmt(loc(((StatementContext)_localctx).WHILE, ((StatementContext)_localctx).END), ((StatementContext)_localctx).expr.ast, ((StatementContext)_localctx).statements.ast); 
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 4);
				{
				setState(176);
				((StatementContext)_localctx).IF = match(IF);
				setState(177);
				((StatementContext)_localctx).expr = expr(0);
				setState(178);
				match(THEN);
				setState(179);
				((StatementContext)_localctx).empty_statements = empty_statements();
				setState(180);
				((StatementContext)_localctx).e = else_( ((StatementContext)_localctx).IF, ((StatementContext)_localctx).expr.ast, ((StatementContext)_localctx).empty_statements.ast );
				 ((StatementContext)_localctx).ast =  ((StatementContext)_localctx).e.ast; 
				}
				break;
			case LET:
				enterOuterAlt(_localctx, 5);
				{
				setState(183);
				((StatementContext)_localctx).LET = match(LET);
				setState(184);
				((StatementContext)_localctx).defs = defs(0);
				setState(185);
				match(IN);
				setState(186);
				((StatementContext)_localctx).statements = statements(0);
				setState(187);
				((StatementContext)_localctx).END = match(END);
				 ((StatementContext)_localctx).ast =  new AST.LetStmt(loc(((StatementContext)_localctx).LET, ((StatementContext)_localctx).END), ((StatementContext)_localctx).defs.ast, ((StatementContext)_localctx).statements.ast); 
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
		public AST.Expr e;
		public AST.Stmt ast;
		public ExprContext expr;
		public TerminalNode ASSIGNMENT() { return getToken(Prev25Parser.ASSIGNMENT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Expr_extensionContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public Expr_extensionContext(ParserRuleContext parent, int invokingState, AST.Expr e) {
			super(parent, invokingState);
			this.e = e;
		}
		@Override public int getRuleIndex() { return RULE_expr_extension; }
	}

	public final Expr_extensionContext expr_extension(AST.Expr e) throws RecognitionException {
		Expr_extensionContext _localctx = new Expr_extensionContext(_ctx, getState(), e);
		enterRule(_localctx, 22, RULE_expr_extension);
		try {
			setState(197);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(192);
				match(ASSIGNMENT);
				setState(193);
				((Expr_extensionContext)_localctx).expr = expr(0);
				 ((Expr_extensionContext)_localctx).ast =  new AST.AssignStmt(loc(e, ((Expr_extensionContext)_localctx).expr.ast), e, ((Expr_extensionContext)_localctx).expr.ast); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				 ((Expr_extensionContext)_localctx).ast =  new AST.ExprStmt(loc((Locatable) e), e); 
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
	public static class ElseContext extends ParserRuleContext {
		public Token start;
		public AST.Expr cond;
		public List<AST.Stmt> stmts;
		public AST.Stmt ast;
		public Empty_statementsContext empty_statements;
		public Token END;
		public TerminalNode ELSE() { return getToken(Prev25Parser.ELSE, 0); }
		public Empty_statementsContext empty_statements() {
			return getRuleContext(Empty_statementsContext.class,0);
		}
		public TerminalNode END() { return getToken(Prev25Parser.END, 0); }
		public ElseContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ElseContext(ParserRuleContext parent, int invokingState, Token start, AST.Expr cond, List<AST.Stmt> stmts) {
			super(parent, invokingState);
			this.start = start;
			this.cond = cond;
			this.stmts = stmts;
		}
		@Override public int getRuleIndex() { return RULE_else; }
	}

	public final ElseContext else_(Token start,AST.Expr cond,List<AST.Stmt> stmts) throws RecognitionException {
		ElseContext _localctx = new ElseContext(_ctx, getState(), start, cond, stmts);
		enterRule(_localctx, 24, RULE_else);
		try {
			setState(206);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ELSE:
				enterOuterAlt(_localctx, 1);
				{
				setState(199);
				match(ELSE);
				setState(200);
				((ElseContext)_localctx).empty_statements = empty_statements();
				setState(201);
				((ElseContext)_localctx).END = match(END);
				 ((ElseContext)_localctx).ast =  new AST.IfThenElseStmt(loc(start, ((ElseContext)_localctx).END), cond, stmts, ((ElseContext)_localctx).empty_statements.ast); 
				}
				break;
			case END:
				enterOuterAlt(_localctx, 2);
				{
				setState(204);
				((ElseContext)_localctx).END = match(END);
				 ((ElseContext)_localctx).ast =  new AST.IfThenStmt(loc(start, ((ElseContext)_localctx).END), cond, stmts); 
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
		public AST.Type ast;
		public Token INT;
		public Token CHAR;
		public Token BOOL;
		public Token VOID;
		public Token IDENTIFIER;
		public Token LBRACKET;
		public Token INTCONST;
		public TypeContext type;
		public Token POW;
		public Token LT;
		public CompsContext comps;
		public Token GT;
		public Token LBRACE;
		public Token RBRACE;
		public Token LPAR;
		public TypesContext types;
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
		public CompsContext comps() {
			return getRuleContext(CompsContext.class,0);
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
			setState(245);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(208);
				((TypeContext)_localctx).INT = match(INT);
				 ((TypeContext)_localctx).ast =  new AST.AtomType(loc(((TypeContext)_localctx).INT), AST.AtomType.Type.INT); 
				}
				break;
			case CHAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(210);
				((TypeContext)_localctx).CHAR = match(CHAR);
				 ((TypeContext)_localctx).ast =  new AST.AtomType(loc(((TypeContext)_localctx).CHAR), AST.AtomType.Type.CHAR); 
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 3);
				{
				setState(212);
				((TypeContext)_localctx).BOOL = match(BOOL);
				 ((TypeContext)_localctx).ast =  new AST.AtomType(loc(((TypeContext)_localctx).BOOL), AST.AtomType.Type.BOOL); 
				}
				break;
			case VOID:
				enterOuterAlt(_localctx, 4);
				{
				setState(214);
				((TypeContext)_localctx).VOID = match(VOID);
				 ((TypeContext)_localctx).ast =  new AST.AtomType(loc(((TypeContext)_localctx).VOID), AST.AtomType.Type.VOID); 
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 5);
				{
				setState(216);
				((TypeContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				 ((TypeContext)_localctx).ast =  new AST.NameType(loc(((TypeContext)_localctx).IDENTIFIER), ((TypeContext)_localctx).IDENTIFIER.getText()); 
				}
				break;
			case LBRACKET:
				enterOuterAlt(_localctx, 6);
				{
				setState(218);
				((TypeContext)_localctx).LBRACKET = match(LBRACKET);
				setState(219);
				((TypeContext)_localctx).INTCONST = match(INTCONST);
				setState(220);
				match(RBRACKET);
				setState(221);
				((TypeContext)_localctx).type = type();
				 ((TypeContext)_localctx).ast =  new AST.ArrType(loc(((TypeContext)_localctx).LBRACKET, ((TypeContext)_localctx).type.ast), ((TypeContext)_localctx).type.ast, ((TypeContext)_localctx).INTCONST.getText()); 
				}
				break;
			case POW:
				enterOuterAlt(_localctx, 7);
				{
				setState(224);
				((TypeContext)_localctx).POW = match(POW);
				setState(225);
				((TypeContext)_localctx).type = type();
				 ((TypeContext)_localctx).ast =  new AST.PtrType(loc(((TypeContext)_localctx).POW, ((TypeContext)_localctx).type.ast), ((TypeContext)_localctx).type.ast); 
				}
				break;
			case LT:
				enterOuterAlt(_localctx, 8);
				{
				setState(228);
				((TypeContext)_localctx).LT = match(LT);
				setState(229);
				((TypeContext)_localctx).comps = comps();
				setState(230);
				((TypeContext)_localctx).GT = match(GT);
				 ((TypeContext)_localctx).ast =  new AST.StrType(loc(((TypeContext)_localctx).LT, ((TypeContext)_localctx).GT), ((TypeContext)_localctx).comps.ast); 
				}
				break;
			case LBRACE:
				enterOuterAlt(_localctx, 9);
				{
				setState(233);
				((TypeContext)_localctx).LBRACE = match(LBRACE);
				setState(234);
				((TypeContext)_localctx).comps = comps();
				setState(235);
				((TypeContext)_localctx).RBRACE = match(RBRACE);
				 ((TypeContext)_localctx).ast =  new AST.UniType(loc(((TypeContext)_localctx).LBRACE, ((TypeContext)_localctx).RBRACE), ((TypeContext)_localctx).comps.ast); 
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 10);
				{
				setState(238);
				((TypeContext)_localctx).LPAR = match(LPAR);
				setState(239);
				((TypeContext)_localctx).types = types();
				setState(240);
				match(RPAR);
				setState(241);
				match(COLON);
				setState(242);
				((TypeContext)_localctx).type = type();
				 ((TypeContext)_localctx).ast =  new AST.FunType(loc(((TypeContext)_localctx).LPAR, ((TypeContext)_localctx).type.ast), ((TypeContext)_localctx).types.ast, ((TypeContext)_localctx).type.ast); 
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
	public static class CompsContext extends ParserRuleContext {
		public List<AST.CompDefn> ast;
		public CompContext comp;
		public Comps2Context comps2;
		public CompContext comp() {
			return getRuleContext(CompContext.class,0);
		}
		public Comps2Context comps2() {
			return getRuleContext(Comps2Context.class,0);
		}
		public CompsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comps; }
	}

	public final CompsContext comps() throws RecognitionException {
		CompsContext _localctx = new CompsContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_comps);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			((CompsContext)_localctx).comp = comp();
			setState(248);
			((CompsContext)_localctx).comps2 = comps2();
			 ((CompsContext)_localctx).ast =  ((CompsContext)_localctx).comps2.ast; _localctx.ast.add(0, ((CompsContext)_localctx).comp.ast); 
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
	public static class Comps2Context extends ParserRuleContext {
		public List<AST.CompDefn> ast;
		public CompsContext comps;
		public TerminalNode COMMA() { return getToken(Prev25Parser.COMMA, 0); }
		public CompsContext comps() {
			return getRuleContext(CompsContext.class,0);
		}
		public Comps2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comps2; }
	}

	public final Comps2Context comps2() throws RecognitionException {
		Comps2Context _localctx = new Comps2Context(_ctx, getState());
		enterRule(_localctx, 30, RULE_comps2);
		try {
			setState(256);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case COMMA:
				enterOuterAlt(_localctx, 1);
				{
				setState(251);
				match(COMMA);
				setState(252);
				((Comps2Context)_localctx).comps = comps();
				 ((Comps2Context)_localctx).ast =  ((Comps2Context)_localctx).comps.ast; 
				}
				break;
			case GT:
			case RBRACE:
				enterOuterAlt(_localctx, 2);
				{
				 ((Comps2Context)_localctx).ast =  new ArrayList<AST.CompDefn>(); 
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
	public static class CompContext extends ParserRuleContext {
		public AST.CompDefn ast;
		public Token IDENTIFIER;
		public TypeContext type;
		public TerminalNode IDENTIFIER() { return getToken(Prev25Parser.IDENTIFIER, 0); }
		public TerminalNode COLON() { return getToken(Prev25Parser.COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public CompContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comp; }
	}

	public final CompContext comp() throws RecognitionException {
		CompContext _localctx = new CompContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_comp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(258);
			((CompContext)_localctx).IDENTIFIER = match(IDENTIFIER);
			setState(259);
			match(COLON);
			setState(260);
			((CompContext)_localctx).type = type();
			 ((CompContext)_localctx).ast =  new AST.CompDefn(loc(((CompContext)_localctx).IDENTIFIER, ((CompContext)_localctx).type.ast), ((CompContext)_localctx).IDENTIFIER.getText(), ((CompContext)_localctx).type.ast); 
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
		public List<AST.Type> ast;
		public TypeContext type;
		public Types2Context types2;
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
		enterRule(_localctx, 34, RULE_types);
		try {
			setState(268);
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
				setState(263);
				((TypesContext)_localctx).type = type();
				setState(264);
				((TypesContext)_localctx).types2 = types2();
				 ((TypesContext)_localctx).ast =  ((TypesContext)_localctx).types2.ast; _localctx.ast.add(0, ((TypesContext)_localctx).type.ast); 
				}
				break;
			case RPAR:
				enterOuterAlt(_localctx, 2);
				{
				 ((TypesContext)_localctx).ast =  new ArrayList<AST.Type>(); 
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
		public List<AST.Type> ast;
		public TypesContext types;
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
		enterRule(_localctx, 36, RULE_types2);
		try {
			setState(275);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case COMMA:
				enterOuterAlt(_localctx, 1);
				{
				setState(270);
				match(COMMA);
				setState(271);
				((Types2Context)_localctx).types = types();
				 ((Types2Context)_localctx).ast =  ((Types2Context)_localctx).types.ast; 
				}
				break;
			case RPAR:
				enterOuterAlt(_localctx, 2);
				{
				 ((Types2Context)_localctx).ast =  new ArrayList<AST.Type>(); 
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
		public AST.Expr ast;
		public ExprContext e;
		public Expr2Context expr2;
		public Expr2Context expr2() {
			return getRuleContext(Expr2Context.class,0);
		}
		public TerminalNode OR() { return getToken(Prev25Parser.OR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
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
		int _startState = 38;
		enterRecursionRule(_localctx, 38, RULE_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(278);
			((ExprContext)_localctx).expr2 = expr2(0);
			 ((ExprContext)_localctx).ast =  ((ExprContext)_localctx).expr2.ast; 
			}
			_ctx.stop = _input.LT(-1);
			setState(288);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExprContext(_parentctx, _parentState);
					_localctx.e = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_expr);
					setState(281);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(282);
					match(OR);
					setState(283);
					((ExprContext)_localctx).expr2 = expr2(0);
					 ((ExprContext)_localctx).ast =  new AST.BinExpr(loc(((ExprContext)_localctx).e.ast, ((ExprContext)_localctx).expr2.ast), AST.BinExpr.Oper.OR, ((ExprContext)_localctx).e.ast, ((ExprContext)_localctx).expr2.ast); 
					}
					} 
				}
				setState(290);
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
	public static class Expr2Context extends ParserRuleContext {
		public AST.Expr ast;
		public Expr2Context e;
		public Expr3Context expr3;
		public Expr3Context expr3() {
			return getRuleContext(Expr3Context.class,0);
		}
		public TerminalNode AND() { return getToken(Prev25Parser.AND, 0); }
		public Expr2Context expr2() {
			return getRuleContext(Expr2Context.class,0);
		}
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
		int _startState = 40;
		enterRecursionRule(_localctx, 40, RULE_expr2, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(292);
			((Expr2Context)_localctx).expr3 = expr3();
			 ((Expr2Context)_localctx).ast =  ((Expr2Context)_localctx).expr3.ast; 
			}
			_ctx.stop = _input.LT(-1);
			setState(302);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Expr2Context(_parentctx, _parentState);
					_localctx.e = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_expr2);
					setState(295);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(296);
					match(AND);
					setState(297);
					((Expr2Context)_localctx).expr3 = expr3();
					 ((Expr2Context)_localctx).ast =  new AST.BinExpr(loc(((Expr2Context)_localctx).e.ast, ((Expr2Context)_localctx).expr3.ast), AST.BinExpr.Oper.AND, ((Expr2Context)_localctx).e.ast, ((Expr2Context)_localctx).expr3.ast); 
					}
					} 
				}
				setState(304);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
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
		public AST.Expr ast;
		public Expr4Context e1;
		public Comparitive_opsContext comparitive_ops;
		public Expr4Context e2;
		public Expr4Context expr4;
		public Comparitive_opsContext comparitive_ops() {
			return getRuleContext(Comparitive_opsContext.class,0);
		}
		public List<Expr4Context> expr4() {
			return getRuleContexts(Expr4Context.class);
		}
		public Expr4Context expr4(int i) {
			return getRuleContext(Expr4Context.class,i);
		}
		public Expr3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr3; }
	}

	public final Expr3Context expr3() throws RecognitionException {
		Expr3Context _localctx = new Expr3Context(_ctx, getState());
		enterRule(_localctx, 42, RULE_expr3);
		try {
			setState(313);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(305);
				((Expr3Context)_localctx).e1 = expr4(0);
				setState(306);
				((Expr3Context)_localctx).comparitive_ops = comparitive_ops();
				setState(307);
				((Expr3Context)_localctx).e2 = expr4(0);
				 ((Expr3Context)_localctx).ast =  new AST.BinExpr(loc(((Expr3Context)_localctx).e1.ast, ((Expr3Context)_localctx).e2.ast), ((Expr3Context)_localctx).comparitive_ops.ast, ((Expr3Context)_localctx).e1.ast, ((Expr3Context)_localctx).e2.ast); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(310);
				((Expr3Context)_localctx).expr4 = expr4(0);
				 ((Expr3Context)_localctx).ast =  ((Expr3Context)_localctx).expr4.ast; 
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
		public AST.Expr ast;
		public Expr4Context e1;
		public Expr5Context expr5;
		public Additive_opsContext additive_ops;
		public Expr5Context e2;
		public Expr5Context expr5() {
			return getRuleContext(Expr5Context.class,0);
		}
		public Additive_opsContext additive_ops() {
			return getRuleContext(Additive_opsContext.class,0);
		}
		public Expr4Context expr4() {
			return getRuleContext(Expr4Context.class,0);
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
		int _startState = 44;
		enterRecursionRule(_localctx, 44, RULE_expr4, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(316);
			((Expr4Context)_localctx).expr5 = expr5(0);
			 ((Expr4Context)_localctx).ast =  ((Expr4Context)_localctx).expr5.ast; 
			}
			_ctx.stop = _input.LT(-1);
			setState(326);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Expr4Context(_parentctx, _parentState);
					_localctx.e1 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_expr4);
					setState(319);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(320);
					((Expr4Context)_localctx).additive_ops = additive_ops();
					setState(321);
					((Expr4Context)_localctx).e2 = ((Expr4Context)_localctx).expr5 = expr5(0);
					 ((Expr4Context)_localctx).ast =  new AST.BinExpr(loc(((Expr4Context)_localctx).e1.ast, ((Expr4Context)_localctx).e2.ast), ((Expr4Context)_localctx).additive_ops.ast, ((Expr4Context)_localctx).e1.ast, ((Expr4Context)_localctx).e2.ast); 
					}
					} 
				}
				setState(328);
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
	public static class Expr5Context extends ParserRuleContext {
		public AST.Expr ast;
		public Expr5Context e1;
		public Expr6Context expr6;
		public Multiplicative_opsContext multiplicative_ops;
		public Expr6Context e2;
		public Expr6Context expr6() {
			return getRuleContext(Expr6Context.class,0);
		}
		public Multiplicative_opsContext multiplicative_ops() {
			return getRuleContext(Multiplicative_opsContext.class,0);
		}
		public Expr5Context expr5() {
			return getRuleContext(Expr5Context.class,0);
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
		int _startState = 46;
		enterRecursionRule(_localctx, 46, RULE_expr5, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(330);
			((Expr5Context)_localctx).expr6 = expr6();
			 ((Expr5Context)_localctx).ast =  ((Expr5Context)_localctx).expr6.ast; 
			}
			_ctx.stop = _input.LT(-1);
			setState(340);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Expr5Context(_parentctx, _parentState);
					_localctx.e1 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_expr5);
					setState(333);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(334);
					((Expr5Context)_localctx).multiplicative_ops = multiplicative_ops();
					setState(335);
					((Expr5Context)_localctx).e2 = ((Expr5Context)_localctx).expr6 = expr6();
					 ((Expr5Context)_localctx).ast =  new AST.BinExpr(loc(((Expr5Context)_localctx).e1.ast, ((Expr5Context)_localctx).e2.ast), ((Expr5Context)_localctx).multiplicative_ops.ast, ((Expr5Context)_localctx).e1.ast, ((Expr5Context)_localctx).e2.ast); 
					}
					} 
				}
				setState(342);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
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
		public AST.Expr ast;
		public Token PLUS;
		public Expr6Context expr6;
		public Token MINUS;
		public Token NOT;
		public Token POW;
		public Expr7Context expr7;
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
		enterRule(_localctx, 48, RULE_expr6);
		try {
			setState(362);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PLUS:
				enterOuterAlt(_localctx, 1);
				{
				setState(343);
				((Expr6Context)_localctx).PLUS = match(PLUS);
				setState(344);
				((Expr6Context)_localctx).expr6 = expr6();
				 ((Expr6Context)_localctx).ast =  new AST.PfxExpr(loc(((Expr6Context)_localctx).PLUS, ((Expr6Context)_localctx).expr6.ast), AST.PfxExpr.Oper.ADD, ((Expr6Context)_localctx).expr6.ast); 
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(347);
				((Expr6Context)_localctx).MINUS = match(MINUS);
				setState(348);
				((Expr6Context)_localctx).expr6 = expr6();
				 ((Expr6Context)_localctx).ast =  new AST.PfxExpr(loc(((Expr6Context)_localctx).MINUS, ((Expr6Context)_localctx).expr6.ast), AST.PfxExpr.Oper.SUB, ((Expr6Context)_localctx).expr6.ast); 
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 3);
				{
				setState(351);
				((Expr6Context)_localctx).NOT = match(NOT);
				setState(352);
				((Expr6Context)_localctx).expr6 = expr6();
				 ((Expr6Context)_localctx).ast =  new AST.PfxExpr(loc(((Expr6Context)_localctx).NOT, ((Expr6Context)_localctx).expr6.ast), AST.PfxExpr.Oper.NOT, ((Expr6Context)_localctx).expr6.ast); 
				}
				break;
			case POW:
				enterOuterAlt(_localctx, 4);
				{
				setState(355);
				((Expr6Context)_localctx).POW = match(POW);
				setState(356);
				((Expr6Context)_localctx).expr6 = expr6();
				 ((Expr6Context)_localctx).ast =  new AST.PfxExpr(loc(((Expr6Context)_localctx).POW, ((Expr6Context)_localctx).expr6.ast), AST.PfxExpr.Oper.PTR, ((Expr6Context)_localctx).expr6.ast); 
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
				setState(359);
				((Expr6Context)_localctx).expr7 = expr7(0);
				 ((Expr6Context)_localctx).ast =  ((Expr6Context)_localctx).expr7.ast; 
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
		public AST.Expr ast;
		public Expr7Context e;
		public Token LBRACE;
		public ExprContext expr;
		public TypeContext type;
		public Token RBRACE;
		public Token LPAR;
		public Token RPAR;
		public Token SIZEOF;
		public TerminalsContext terminals;
		public Token POW;
		public Token RBRACKET;
		public Token IDENTIFIER;
		public Exprs_inContext exprs_in;
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
		public TerminalNode POW() { return getToken(Prev25Parser.POW, 0); }
		public Expr7Context expr7() {
			return getRuleContext(Expr7Context.class,0);
		}
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
		int _startState = 50;
		enterRecursionRule(_localctx, 50, RULE_expr7, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(384);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACE:
				{
				setState(365);
				((Expr7Context)_localctx).LBRACE = match(LBRACE);
				setState(366);
				((Expr7Context)_localctx).expr = expr(0);
				setState(367);
				match(COLON);
				setState(368);
				((Expr7Context)_localctx).type = type();
				setState(369);
				((Expr7Context)_localctx).RBRACE = match(RBRACE);
				 ((Expr7Context)_localctx).ast =  new AST.CastExpr(loc(((Expr7Context)_localctx).LBRACE, ((Expr7Context)_localctx).RBRACE), ((Expr7Context)_localctx).type.ast, ((Expr7Context)_localctx).expr.ast); 
				}
				break;
			case LPAR:
				{
				setState(372);
				((Expr7Context)_localctx).LPAR = match(LPAR);
				setState(373);
				((Expr7Context)_localctx).expr = expr(0);
				setState(374);
				((Expr7Context)_localctx).RPAR = match(RPAR);
				 ((Expr7Context)_localctx).ast =  ((Expr7Context)_localctx).expr.ast; _localctx.ast.relocate(loc(((Expr7Context)_localctx).LPAR, ((Expr7Context)_localctx).RPAR)); 
				}
				break;
			case SIZEOF:
				{
				setState(377);
				((Expr7Context)_localctx).SIZEOF = match(SIZEOF);
				setState(378);
				((Expr7Context)_localctx).type = type();
				 ((Expr7Context)_localctx).ast =  new AST.SizeExpr(loc(((Expr7Context)_localctx).SIZEOF, ((Expr7Context)_localctx).type.ast), ((Expr7Context)_localctx).type.ast); 
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
				setState(381);
				((Expr7Context)_localctx).terminals = terminals();
				 ((Expr7Context)_localctx).ast =  ((Expr7Context)_localctx).terminals.ast; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(407);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(405);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
					case 1:
						{
						_localctx = new Expr7Context(_parentctx, _parentState);
						_localctx.e = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr7);
						setState(386);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(387);
						((Expr7Context)_localctx).POW = match(POW);
						 ((Expr7Context)_localctx).ast =  new AST.SfxExpr(loc(((Expr7Context)_localctx).e.ast, ((Expr7Context)_localctx).POW), AST.SfxExpr.Oper.PTR, ((Expr7Context)_localctx).e.ast); 
						}
						break;
					case 2:
						{
						_localctx = new Expr7Context(_parentctx, _parentState);
						_localctx.e = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr7);
						setState(389);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(390);
						match(LBRACKET);
						setState(391);
						((Expr7Context)_localctx).expr = expr(0);
						setState(392);
						((Expr7Context)_localctx).RBRACKET = match(RBRACKET);
						 ((Expr7Context)_localctx).ast =  new AST.ArrExpr(loc(((Expr7Context)_localctx).e.ast, ((Expr7Context)_localctx).RBRACKET), ((Expr7Context)_localctx).e.ast, ((Expr7Context)_localctx).expr.ast); 
						}
						break;
					case 3:
						{
						_localctx = new Expr7Context(_parentctx, _parentState);
						_localctx.e = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr7);
						setState(395);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(396);
						match(DOT);
						setState(397);
						((Expr7Context)_localctx).IDENTIFIER = match(IDENTIFIER);
						 ((Expr7Context)_localctx).ast =  new AST.CompExpr(loc(((Expr7Context)_localctx).e.ast, ((Expr7Context)_localctx).IDENTIFIER), ((Expr7Context)_localctx).e.ast, ((Expr7Context)_localctx).IDENTIFIER.getText()); 
						}
						break;
					case 4:
						{
						_localctx = new Expr7Context(_parentctx, _parentState);
						_localctx.e = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr7);
						setState(399);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(400);
						((Expr7Context)_localctx).LPAR = match(LPAR);
						setState(401);
						((Expr7Context)_localctx).exprs_in = exprs_in();
						setState(402);
						((Expr7Context)_localctx).RPAR = match(RPAR);
						 ((Expr7Context)_localctx).ast =  new AST.CallExpr(loc(((Expr7Context)_localctx).e.ast, ((Expr7Context)_localctx).RPAR), ((Expr7Context)_localctx).e.ast, ((Expr7Context)_localctx).exprs_in.ast); 
						}
						break;
					}
					} 
				}
				setState(409);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
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
		public List<AST.Expr> ast;
		public ExprsContext exprs;
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
		enterRule(_localctx, 52, RULE_exprs_in);
		try {
			setState(414);
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
				setState(410);
				((Exprs_inContext)_localctx).exprs = exprs();
				 ((Exprs_inContext)_localctx).ast =  ((Exprs_inContext)_localctx).exprs.ast; 
				}
				break;
			case RPAR:
				enterOuterAlt(_localctx, 2);
				{
				 ((Exprs_inContext)_localctx).ast =  new ArrayList<AST.Expr>(); 
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
		public List<AST.Expr> ast;
		public ExprContext expr;
		public Exprs2Context exprs2;
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
		enterRule(_localctx, 54, RULE_exprs);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(416);
			((ExprsContext)_localctx).expr = expr(0);
			setState(417);
			((ExprsContext)_localctx).exprs2 = exprs2();
			 ((ExprsContext)_localctx).ast =  ((ExprsContext)_localctx).exprs2.ast; _localctx.ast.add(0, ((ExprsContext)_localctx).expr.ast); 
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
		public List<AST.Expr> ast;
		public ExprsContext exprs;
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
		enterRule(_localctx, 56, RULE_exprs2);
		try {
			setState(425);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case COMMA:
				enterOuterAlt(_localctx, 1);
				{
				setState(420);
				match(COMMA);
				setState(421);
				((Exprs2Context)_localctx).exprs = exprs();
				 ((Exprs2Context)_localctx).ast =  ((Exprs2Context)_localctx).exprs.ast; 
				}
				break;
			case RPAR:
				enterOuterAlt(_localctx, 2);
				{
				 ((Exprs2Context)_localctx).ast =  new ArrayList<AST.Expr>(); 
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
		public AST.Expr ast;
		public Token INTCONST;
		public Token STRCONST;
		public Token CHARCONST;
		public Token TRUE;
		public Token FALSE;
		public Token NULL;
		public Token IDENTIFIER;
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
		enterRule(_localctx, 58, RULE_terminals);
		try {
			setState(441);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTCONST:
				enterOuterAlt(_localctx, 1);
				{
				setState(427);
				((TerminalsContext)_localctx).INTCONST = match(INTCONST);
				 ((TerminalsContext)_localctx).ast =  new AST.AtomExpr(loc(((TerminalsContext)_localctx).INTCONST), AST.AtomExpr.Type.INT, ((TerminalsContext)_localctx).INTCONST.getText()); 
				}
				break;
			case STRCONST:
				enterOuterAlt(_localctx, 2);
				{
				setState(429);
				((TerminalsContext)_localctx).STRCONST = match(STRCONST);
				 ((TerminalsContext)_localctx).ast =  new AST.AtomExpr(loc(((TerminalsContext)_localctx).STRCONST), AST.AtomExpr.Type.STR, ((TerminalsContext)_localctx).STRCONST.getText()); 
				}
				break;
			case CHARCONST:
				enterOuterAlt(_localctx, 3);
				{
				setState(431);
				((TerminalsContext)_localctx).CHARCONST = match(CHARCONST);
				 ((TerminalsContext)_localctx).ast =  new AST.AtomExpr(loc(((TerminalsContext)_localctx).CHARCONST), AST.AtomExpr.Type.CHAR, ((TerminalsContext)_localctx).CHARCONST.getText()); 
				}
				break;
			case TRUE:
				enterOuterAlt(_localctx, 4);
				{
				setState(433);
				((TerminalsContext)_localctx).TRUE = match(TRUE);
				 ((TerminalsContext)_localctx).ast =  new AST.AtomExpr(loc(((TerminalsContext)_localctx).TRUE), AST.AtomExpr.Type.BOOL, ((TerminalsContext)_localctx).TRUE.getText()); 
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 5);
				{
				setState(435);
				((TerminalsContext)_localctx).FALSE = match(FALSE);
				 ((TerminalsContext)_localctx).ast =  new AST.AtomExpr(loc(((TerminalsContext)_localctx).FALSE), AST.AtomExpr.Type.BOOL, ((TerminalsContext)_localctx).FALSE.getText()); 
				}
				break;
			case NULL:
				enterOuterAlt(_localctx, 6);
				{
				setState(437);
				((TerminalsContext)_localctx).NULL = match(NULL);
				 ((TerminalsContext)_localctx).ast =  new AST.AtomExpr(loc(((TerminalsContext)_localctx).NULL), AST.AtomExpr.Type.PTR, "0"); 
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 7);
				{
				setState(439);
				((TerminalsContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				 ((TerminalsContext)_localctx).ast =  new AST.NameExpr(loc(((TerminalsContext)_localctx).IDENTIFIER), ((TerminalsContext)_localctx).IDENTIFIER.getText()); 
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
	public static class Multiplicative_opsContext extends ParserRuleContext {
		public AST.BinExpr.Oper ast;
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
		enterRule(_localctx, 60, RULE_multiplicative_ops);
		try {
			setState(449);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(443);
				match(STAR);
				 ((Multiplicative_opsContext)_localctx).ast =  AST.BinExpr.Oper.MUL; 
				}
				break;
			case DIV:
				enterOuterAlt(_localctx, 2);
				{
				setState(445);
				match(DIV);
				 ((Multiplicative_opsContext)_localctx).ast =  AST.BinExpr.Oper.DIV; 
				}
				break;
			case MOD:
				enterOuterAlt(_localctx, 3);
				{
				setState(447);
				match(MOD);
				 ((Multiplicative_opsContext)_localctx).ast =  AST.BinExpr.Oper.MOD; 
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
	public static class Additive_opsContext extends ParserRuleContext {
		public AST.BinExpr.Oper ast;
		public TerminalNode PLUS() { return getToken(Prev25Parser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(Prev25Parser.MINUS, 0); }
		public Additive_opsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additive_ops; }
	}

	public final Additive_opsContext additive_ops() throws RecognitionException {
		Additive_opsContext _localctx = new Additive_opsContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_additive_ops);
		try {
			setState(455);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PLUS:
				enterOuterAlt(_localctx, 1);
				{
				setState(451);
				match(PLUS);
				 ((Additive_opsContext)_localctx).ast =  AST.BinExpr.Oper.ADD; 
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(453);
				match(MINUS);
				 ((Additive_opsContext)_localctx).ast =  AST.BinExpr.Oper.SUB; 
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
	public static class Comparitive_opsContext extends ParserRuleContext {
		public AST.BinExpr.Oper ast;
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
		enterRule(_localctx, 64, RULE_comparitive_ops);
		try {
			setState(469);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EQ:
				enterOuterAlt(_localctx, 1);
				{
				setState(457);
				match(EQ);
				 ((Comparitive_opsContext)_localctx).ast =  AST.BinExpr.Oper.EQU; 
				}
				break;
			case NEQ:
				enterOuterAlt(_localctx, 2);
				{
				setState(459);
				match(NEQ);
				 ((Comparitive_opsContext)_localctx).ast =  AST.BinExpr.Oper.NEQ; 
				}
				break;
			case LTE:
				enterOuterAlt(_localctx, 3);
				{
				setState(461);
				match(LTE);
				 ((Comparitive_opsContext)_localctx).ast =  AST.BinExpr.Oper.LEQ; 
				}
				break;
			case GTE:
				enterOuterAlt(_localctx, 4);
				{
				setState(463);
				match(GTE);
				 ((Comparitive_opsContext)_localctx).ast =  AST.BinExpr.Oper.GEQ; 
				}
				break;
			case LT:
				enterOuterAlt(_localctx, 5);
				{
				setState(465);
				match(LT);
				 ((Comparitive_opsContext)_localctx).ast =  AST.BinExpr.Oper.LTH; 
				}
				break;
			case GT:
				enterOuterAlt(_localctx, 6);
				{
				setState(467);
				match(GT);
				 ((Comparitive_opsContext)_localctx).ast =  AST.BinExpr.Oper.GTH; 
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return defs_sempred((DefsContext)_localctx, predIndex);
		case 8:
			return statements_sempred((StatementsContext)_localctx, predIndex);
		case 19:
			return expr_sempred((ExprContext)_localctx, predIndex);
		case 20:
			return expr2_sempred((Expr2Context)_localctx, predIndex);
		case 22:
			return expr4_sempred((Expr4Context)_localctx, predIndex);
		case 23:
			return expr5_sempred((Expr5Context)_localctx, predIndex);
		case 25:
			return expr7_sempred((Expr7Context)_localctx, predIndex);
		}
		return true;
	}
	private boolean defs_sempred(DefsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean statements_sempred(StatementsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean expr2_sempred(Expr2Context _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean expr4_sempred(Expr4Context _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean expr5_sempred(Expr5Context _localctx, int predIndex) {
		switch (predIndex) {
		case 5:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean expr7_sempred(Expr7Context _localctx, int predIndex) {
		switch (predIndex) {
		case 6:
			return precpred(_ctx, 8);
		case 7:
			return precpred(_ctx, 5);
		case 8:
			return precpred(_ctx, 3);
		case 9:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u00014\u01d8\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001O\b\u0001\n\u0001\f\u0001"+
		"R\t\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002"+
		"j\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003"+
		"p\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0003\u0004w\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006"+
		"\u0082\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0005\b\u0092\b\b\n\b\f\b\u0095\t\b\u0001\t\u0001\t\u0001\t\u0001\t"+
		"\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t\u00a0\b\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0003\n\u00bf\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0003\u000b\u00c6\b\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0003\f\u00cf\b\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u00f6\b\r\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0003\u000f\u0101\b\u000f\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0003\u0011\u010d\b\u0011\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u0114\b\u0012\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0005\u0013\u011f\b\u0013\n\u0013\f\u0013\u0122"+
		"\t\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0005\u0014\u012d\b\u0014\n"+
		"\u0014\f\u0014\u0130\t\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u013a"+
		"\b\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0005\u0016\u0145\b\u0016\n"+
		"\u0016\f\u0016\u0148\t\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0005"+
		"\u0017\u0153\b\u0017\n\u0017\f\u0017\u0156\t\u0017\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0003\u0018"+
		"\u016b\b\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u0181\b\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0005\u0019\u0196\b\u0019\n\u0019\f\u0019\u0199\t\u0019\u0001\u001a\u0001"+
		"\u001a\u0001\u001a\u0001\u001a\u0003\u001a\u019f\b\u001a\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0003\u001c\u01aa\b\u001c\u0001\u001d\u0001\u001d\u0001"+
		"\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001"+
		"\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0003"+
		"\u001d\u01ba\b\u001d\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001"+
		"\u001e\u0001\u001e\u0003\u001e\u01c2\b\u001e\u0001\u001f\u0001\u001f\u0001"+
		"\u001f\u0001\u001f\u0003\u001f\u01c8\b\u001f\u0001 \u0001 \u0001 \u0001"+
		" \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0003 \u01d6"+
		"\b \u0001 \u0000\u0007\u0002\u0010&(,.2!\u0000\u0002\u0004\u0006\b\n\f"+
		"\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:"+
		"<>@\u0000\u0000\u01f1\u0000B\u0001\u0000\u0000\u0000\u0002F\u0001\u0000"+
		"\u0000\u0000\u0004i\u0001\u0000\u0000\u0000\u0006o\u0001\u0000\u0000\u0000"+
		"\bv\u0001\u0000\u0000\u0000\nx\u0001\u0000\u0000\u0000\f\u0081\u0001\u0000"+
		"\u0000\u0000\u000e\u0083\u0001\u0000\u0000\u0000\u0010\u0088\u0001\u0000"+
		"\u0000\u0000\u0012\u009f\u0001\u0000\u0000\u0000\u0014\u00be\u0001\u0000"+
		"\u0000\u0000\u0016\u00c5\u0001\u0000\u0000\u0000\u0018\u00ce\u0001\u0000"+
		"\u0000\u0000\u001a\u00f5\u0001\u0000\u0000\u0000\u001c\u00f7\u0001\u0000"+
		"\u0000\u0000\u001e\u0100\u0001\u0000\u0000\u0000 \u0102\u0001\u0000\u0000"+
		"\u0000\"\u010c\u0001\u0000\u0000\u0000$\u0113\u0001\u0000\u0000\u0000"+
		"&\u0115\u0001\u0000\u0000\u0000(\u0123\u0001\u0000\u0000\u0000*\u0139"+
		"\u0001\u0000\u0000\u0000,\u013b\u0001\u0000\u0000\u0000.\u0149\u0001\u0000"+
		"\u0000\u00000\u016a\u0001\u0000\u0000\u00002\u0180\u0001\u0000\u0000\u0000"+
		"4\u019e\u0001\u0000\u0000\u00006\u01a0\u0001\u0000\u0000\u00008\u01a9"+
		"\u0001\u0000\u0000\u0000:\u01b9\u0001\u0000\u0000\u0000<\u01c1\u0001\u0000"+
		"\u0000\u0000>\u01c7\u0001\u0000\u0000\u0000@\u01d5\u0001\u0000\u0000\u0000"+
		"BC\u0003\u0002\u0001\u0000CD\u0005\u0000\u0000\u0001DE\u0006\u0000\uffff"+
		"\uffff\u0000E\u0001\u0001\u0000\u0000\u0000FG\u0006\u0001\uffff\uffff"+
		"\u0000GH\u0003\u0004\u0002\u0000HI\u0006\u0001\uffff\uffff\u0000IP\u0001"+
		"\u0000\u0000\u0000JK\n\u0002\u0000\u0000KL\u0003\u0004\u0002\u0000LM\u0006"+
		"\u0001\uffff\uffff\u0000MO\u0001\u0000\u0000\u0000NJ\u0001\u0000\u0000"+
		"\u0000OR\u0001\u0000\u0000\u0000PN\u0001\u0000\u0000\u0000PQ\u0001\u0000"+
		"\u0000\u0000Q\u0003\u0001\u0000\u0000\u0000RP\u0001\u0000\u0000\u0000"+
		"ST\u0005-\u0000\u0000TU\u00051\u0000\u0000UV\u0005\u0014\u0000\u0000V"+
		"W\u0003\u001a\r\u0000WX\u0006\u0002\uffff\uffff\u0000Xj\u0001\u0000\u0000"+
		"\u0000YZ\u0005.\u0000\u0000Z[\u00051\u0000\u0000[\\\u0005\u0015\u0000"+
		"\u0000\\]\u0003\u001a\r\u0000]^\u0006\u0002\uffff\uffff\u0000^j\u0001"+
		"\u0000\u0000\u0000_`\u0005#\u0000\u0000`a\u00051\u0000\u0000ab\u0005\u0019"+
		"\u0000\u0000bc\u0003\u0006\u0003\u0000cd\u0005\u001a\u0000\u0000de\u0005"+
		"\u0015\u0000\u0000ef\u0003\u001a\r\u0000fg\u0003\b\u0004\u0000gh\u0006"+
		"\u0002\uffff\uffff\u0000hj\u0001\u0000\u0000\u0000iS\u0001\u0000\u0000"+
		"\u0000iY\u0001\u0000\u0000\u0000i_\u0001\u0000\u0000\u0000j\u0005\u0001"+
		"\u0000\u0000\u0000kl\u0003\n\u0005\u0000lm\u0006\u0003\uffff\uffff\u0000"+
		"mp\u0001\u0000\u0000\u0000np\u0006\u0003\uffff\uffff\u0000ok\u0001\u0000"+
		"\u0000\u0000on\u0001\u0000\u0000\u0000p\u0007\u0001\u0000\u0000\u0000"+
		"qr\u0005\u0014\u0000\u0000rs\u0003\u0010\b\u0000st\u0006\u0004\uffff\uffff"+
		"\u0000tw\u0001\u0000\u0000\u0000uw\u0006\u0004\uffff\uffff\u0000vq\u0001"+
		"\u0000\u0000\u0000vu\u0001\u0000\u0000\u0000w\t\u0001\u0000\u0000\u0000"+
		"xy\u0003\u000e\u0007\u0000yz\u0003\f\u0006\u0000z{\u0006\u0005\uffff\uffff"+
		"\u0000{\u000b\u0001\u0000\u0000\u0000|}\u0005\u0016\u0000\u0000}~\u0003"+
		"\n\u0005\u0000~\u007f\u0006\u0006\uffff\uffff\u0000\u007f\u0082\u0001"+
		"\u0000\u0000\u0000\u0080\u0082\u0006\u0006\uffff\uffff\u0000\u0081|\u0001"+
		"\u0000\u0000\u0000\u0081\u0080\u0001\u0000\u0000\u0000\u0082\r\u0001\u0000"+
		"\u0000\u0000\u0083\u0084\u00051\u0000\u0000\u0084\u0085\u0005\u0015\u0000"+
		"\u0000\u0085\u0086\u0003\u001a\r\u0000\u0086\u0087\u0006\u0007\uffff\uffff"+
		"\u0000\u0087\u000f\u0001\u0000\u0000\u0000\u0088\u0089\u0006\b\uffff\uffff"+
		"\u0000\u0089\u008a\u0003\u0014\n\u0000\u008a\u008b\u0006\b\uffff\uffff"+
		"\u0000\u008b\u0093\u0001\u0000\u0000\u0000\u008c\u008d\n\u0002\u0000\u0000"+
		"\u008d\u008e\u0005\u0016\u0000\u0000\u008e\u008f\u0003\u0014\n\u0000\u008f"+
		"\u0090\u0006\b\uffff\uffff\u0000\u0090\u0092\u0001\u0000\u0000\u0000\u0091"+
		"\u008c\u0001\u0000\u0000\u0000\u0092\u0095\u0001\u0000\u0000\u0000\u0093"+
		"\u0091\u0001\u0000\u0000\u0000\u0093\u0094\u0001\u0000\u0000\u0000\u0094"+
		"\u0011\u0001\u0000\u0000\u0000\u0095\u0093\u0001\u0000\u0000\u0000\u0096"+
		"\u0097\u0003\u0010\b\u0000\u0097\u0098\u0005\u0016\u0000\u0000\u0098\u0099"+
		"\u0003\u0014\n\u0000\u0099\u009a\u0006\t\uffff\uffff\u0000\u009a\u00a0"+
		"\u0001\u0000\u0000\u0000\u009b\u009c\u0003\u0014\n\u0000\u009c\u009d\u0006"+
		"\t\uffff\uffff\u0000\u009d\u00a0\u0001\u0000\u0000\u0000\u009e\u00a0\u0006"+
		"\t\uffff\uffff\u0000\u009f\u0096\u0001\u0000\u0000\u0000\u009f\u009b\u0001"+
		"\u0000\u0000\u0000\u009f\u009e\u0001\u0000\u0000\u0000\u00a0\u0013\u0001"+
		"\u0000\u0000\u0000\u00a1\u00a2\u0003&\u0013\u0000\u00a2\u00a3\u0003\u0016"+
		"\u000b\u0000\u00a3\u00a4\u0006\n\uffff\uffff\u0000\u00a4\u00bf\u0001\u0000"+
		"\u0000\u0000\u00a5\u00a6\u0005)\u0000\u0000\u00a6\u00a7\u0003&\u0013\u0000"+
		"\u00a7\u00a8\u0006\n\uffff\uffff\u0000\u00a8\u00bf\u0001\u0000\u0000\u0000"+
		"\u00a9\u00aa\u00050\u0000\u0000\u00aa\u00ab\u0003&\u0013\u0000\u00ab\u00ac"+
		"\u0005\u001f\u0000\u0000\u00ac\u00ad\u0003\u0010\b\u0000\u00ad\u00ae\u0005"+
		"!\u0000\u0000\u00ae\u00af\u0006\n\uffff\uffff\u0000\u00af\u00bf\u0001"+
		"\u0000\u0000\u0000\u00b0\u00b1\u0005$\u0000\u0000\u00b1\u00b2\u0003&\u0013"+
		"\u0000\u00b2\u00b3\u0005+\u0000\u0000\u00b3\u00b4\u0003\u0012\t\u0000"+
		"\u00b4\u00b5\u0003\u0018\f\u0000\u00b5\u00b6\u0006\n\uffff\uffff\u0000"+
		"\u00b6\u00bf\u0001\u0000\u0000\u0000\u00b7\u00b8\u0005\'\u0000\u0000\u00b8"+
		"\u00b9\u0003\u0002\u0001\u0000\u00b9\u00ba\u0005%\u0000\u0000\u00ba\u00bb"+
		"\u0003\u0010\b\u0000\u00bb\u00bc\u0005!\u0000\u0000\u00bc\u00bd\u0006"+
		"\n\uffff\uffff\u0000\u00bd\u00bf\u0001\u0000\u0000\u0000\u00be\u00a1\u0001"+
		"\u0000\u0000\u0000\u00be\u00a5\u0001\u0000\u0000\u0000\u00be\u00a9\u0001"+
		"\u0000\u0000\u0000\u00be\u00b0\u0001\u0000\u0000\u0000\u00be\u00b7\u0001"+
		"\u0000\u0000\u0000\u00bf\u0015\u0001\u0000\u0000\u0000\u00c0\u00c1\u0005"+
		"\u0014\u0000\u0000\u00c1\u00c2\u0003&\u0013\u0000\u00c2\u00c3\u0006\u000b"+
		"\uffff\uffff\u0000\u00c3\u00c6\u0001\u0000\u0000\u0000\u00c4\u00c6\u0006"+
		"\u000b\uffff\uffff\u0000\u00c5\u00c0\u0001\u0000\u0000\u0000\u00c5\u00c4"+
		"\u0001\u0000\u0000\u0000\u00c6\u0017\u0001\u0000\u0000\u0000\u00c7\u00c8"+
		"\u0005 \u0000\u0000\u00c8\u00c9\u0003\u0012\t\u0000\u00c9\u00ca\u0005"+
		"!\u0000\u0000\u00ca\u00cb\u0006\f\uffff\uffff\u0000\u00cb\u00cf\u0001"+
		"\u0000\u0000\u0000\u00cc\u00cd\u0005!\u0000\u0000\u00cd\u00cf\u0006\f"+
		"\uffff\uffff\u0000\u00ce\u00c7\u0001\u0000\u0000\u0000\u00ce\u00cc\u0001"+
		"\u0000\u0000\u0000\u00cf\u0019\u0001\u0000\u0000\u0000\u00d0\u00d1\u0005"+
		"&\u0000\u0000\u00d1\u00f6\u0006\r\uffff\uffff\u0000\u00d2\u00d3\u0005"+
		"\u001e\u0000\u0000\u00d3\u00f6\u0006\r\uffff\uffff\u0000\u00d4\u00d5\u0005"+
		"\u001d\u0000\u0000\u00d5\u00f6\u0006\r\uffff\uffff\u0000\u00d6\u00d7\u0005"+
		"/\u0000\u0000\u00d7\u00f6\u0006\r\uffff\uffff\u0000\u00d8\u00d9\u0005"+
		"1\u0000\u0000\u00d9\u00f6\u0006\r\uffff\uffff\u0000\u00da\u00db\u0005"+
		"\u001b\u0000\u0000\u00db\u00dc\u0005\u0001\u0000\u0000\u00dc\u00dd\u0005"+
		"\u001c\u0000\u0000\u00dd\u00de\u0003\u001a\r\u0000\u00de\u00df\u0006\r"+
		"\uffff\uffff\u0000\u00df\u00f6\u0001\u0000\u0000\u0000\u00e0\u00e1\u0005"+
		"\u0013\u0000\u0000\u00e1\u00e2\u0003\u001a\r\u0000\u00e2\u00e3\u0006\r"+
		"\uffff\uffff\u0000\u00e3\u00f6\u0001\u0000\u0000\u0000\u00e4\u00e5\u0005"+
		"\f\u0000\u0000\u00e5\u00e6\u0003\u001c\u000e\u0000\u00e6\u00e7\u0005\r"+
		"\u0000\u0000\u00e7\u00e8\u0006\r\uffff\uffff\u0000\u00e8\u00f6\u0001\u0000"+
		"\u0000\u0000\u00e9\u00ea\u0005\u0017\u0000\u0000\u00ea\u00eb\u0003\u001c"+
		"\u000e\u0000\u00eb\u00ec\u0005\u0018\u0000\u0000\u00ec\u00ed\u0006\r\uffff"+
		"\uffff\u0000\u00ed\u00f6\u0001\u0000\u0000\u0000\u00ee\u00ef\u0005\u0019"+
		"\u0000\u0000\u00ef\u00f0\u0003\"\u0011\u0000\u00f0\u00f1\u0005\u001a\u0000"+
		"\u0000\u00f1\u00f2\u0005\u0015\u0000\u0000\u00f2\u00f3\u0003\u001a\r\u0000"+
		"\u00f3\u00f4\u0006\r\uffff\uffff\u0000\u00f4\u00f6\u0001\u0000\u0000\u0000"+
		"\u00f5\u00d0\u0001\u0000\u0000\u0000\u00f5\u00d2\u0001\u0000\u0000\u0000"+
		"\u00f5\u00d4\u0001\u0000\u0000\u0000\u00f5\u00d6\u0001\u0000\u0000\u0000"+
		"\u00f5\u00d8\u0001\u0000\u0000\u0000\u00f5\u00da\u0001\u0000\u0000\u0000"+
		"\u00f5\u00e0\u0001\u0000\u0000\u0000\u00f5\u00e4\u0001\u0000\u0000\u0000"+
		"\u00f5\u00e9\u0001\u0000\u0000\u0000\u00f5\u00ee\u0001\u0000\u0000\u0000"+
		"\u00f6\u001b\u0001\u0000\u0000\u0000\u00f7\u00f8\u0003 \u0010\u0000\u00f8"+
		"\u00f9\u0003\u001e\u000f\u0000\u00f9\u00fa\u0006\u000e\uffff\uffff\u0000"+
		"\u00fa\u001d\u0001\u0000\u0000\u0000\u00fb\u00fc\u0005\u0016\u0000\u0000"+
		"\u00fc\u00fd\u0003\u001c\u000e\u0000\u00fd\u00fe\u0006\u000f\uffff\uffff"+
		"\u0000\u00fe\u0101\u0001\u0000\u0000\u0000\u00ff\u0101\u0006\u000f\uffff"+
		"\uffff\u0000\u0100\u00fb\u0001\u0000\u0000\u0000\u0100\u00ff\u0001\u0000"+
		"\u0000\u0000\u0101\u001f\u0001\u0000\u0000\u0000\u0102\u0103\u00051\u0000"+
		"\u0000\u0103\u0104\u0005\u0015\u0000\u0000\u0104\u0105\u0003\u001a\r\u0000"+
		"\u0105\u0106\u0006\u0010\uffff\uffff\u0000\u0106!\u0001\u0000\u0000\u0000"+
		"\u0107\u0108\u0003\u001a\r\u0000\u0108\u0109\u0003$\u0012\u0000\u0109"+
		"\u010a\u0006\u0011\uffff\uffff\u0000\u010a\u010d\u0001\u0000\u0000\u0000"+
		"\u010b\u010d\u0006\u0011\uffff\uffff\u0000\u010c\u0107\u0001\u0000\u0000"+
		"\u0000\u010c\u010b\u0001\u0000\u0000\u0000\u010d#\u0001\u0000\u0000\u0000"+
		"\u010e\u010f\u0005\u0016\u0000\u0000\u010f\u0110\u0003\"\u0011\u0000\u0110"+
		"\u0111\u0006\u0012\uffff\uffff\u0000\u0111\u0114\u0001\u0000\u0000\u0000"+
		"\u0112\u0114\u0006\u0012\uffff\uffff\u0000\u0113\u010e\u0001\u0000\u0000"+
		"\u0000\u0113\u0112\u0001\u0000\u0000\u0000\u0114%\u0001\u0000\u0000\u0000"+
		"\u0115\u0116\u0006\u0013\uffff\uffff\u0000\u0116\u0117\u0003(\u0014\u0000"+
		"\u0117\u0118\u0006\u0013\uffff\uffff\u0000\u0118\u0120\u0001\u0000\u0000"+
		"\u0000\u0119\u011a\n\u0002\u0000\u0000\u011a\u011b\u0005\u0007\u0000\u0000"+
		"\u011b\u011c\u0003(\u0014\u0000\u011c\u011d\u0006\u0013\uffff\uffff\u0000"+
		"\u011d\u011f\u0001\u0000\u0000\u0000\u011e\u0119\u0001\u0000\u0000\u0000"+
		"\u011f\u0122\u0001\u0000\u0000\u0000\u0120\u011e\u0001\u0000\u0000\u0000"+
		"\u0120\u0121\u0001\u0000\u0000\u0000\u0121\'\u0001\u0000\u0000\u0000\u0122"+
		"\u0120\u0001\u0000\u0000\u0000\u0123\u0124\u0006\u0014\uffff\uffff\u0000"+
		"\u0124\u0125\u0003*\u0015\u0000\u0125\u0126\u0006\u0014\uffff\uffff\u0000"+
		"\u0126\u012e\u0001\u0000\u0000\u0000\u0127\u0128\n\u0002\u0000\u0000\u0128"+
		"\u0129\u0005\u0006\u0000\u0000\u0129\u012a\u0003*\u0015\u0000\u012a\u012b"+
		"\u0006\u0014\uffff\uffff\u0000\u012b\u012d\u0001\u0000\u0000\u0000\u012c"+
		"\u0127\u0001\u0000\u0000\u0000\u012d\u0130\u0001\u0000\u0000\u0000\u012e"+
		"\u012c\u0001\u0000\u0000\u0000\u012e\u012f\u0001\u0000\u0000\u0000\u012f"+
		")\u0001\u0000\u0000\u0000\u0130\u012e\u0001\u0000\u0000\u0000\u0131\u0132"+
		"\u0003,\u0016\u0000\u0132\u0133\u0003@ \u0000\u0133\u0134\u0003,\u0016"+
		"\u0000\u0134\u0135\u0006\u0015\uffff\uffff\u0000\u0135\u013a\u0001\u0000"+
		"\u0000\u0000\u0136\u0137\u0003,\u0016\u0000\u0137\u0138\u0006\u0015\uffff"+
		"\uffff\u0000\u0138\u013a\u0001\u0000\u0000\u0000\u0139\u0131\u0001\u0000"+
		"\u0000\u0000\u0139\u0136\u0001\u0000\u0000\u0000\u013a+\u0001\u0000\u0000"+
		"\u0000\u013b\u013c\u0006\u0016\uffff\uffff\u0000\u013c\u013d\u0003.\u0017"+
		"\u0000\u013d\u013e\u0006\u0016\uffff\uffff\u0000\u013e\u0146\u0001\u0000"+
		"\u0000\u0000\u013f\u0140\n\u0002\u0000\u0000\u0140\u0141\u0003>\u001f"+
		"\u0000\u0141\u0142\u0003.\u0017\u0000\u0142\u0143\u0006\u0016\uffff\uffff"+
		"\u0000\u0143\u0145\u0001\u0000\u0000\u0000\u0144\u013f\u0001\u0000\u0000"+
		"\u0000\u0145\u0148\u0001\u0000\u0000\u0000\u0146\u0144\u0001\u0000\u0000"+
		"\u0000\u0146\u0147\u0001\u0000\u0000\u0000\u0147-\u0001\u0000\u0000\u0000"+
		"\u0148\u0146\u0001\u0000\u0000\u0000\u0149\u014a\u0006\u0017\uffff\uffff"+
		"\u0000\u014a\u014b\u00030\u0018\u0000\u014b\u014c\u0006\u0017\uffff\uffff"+
		"\u0000\u014c\u0154\u0001\u0000\u0000\u0000\u014d\u014e\n\u0002\u0000\u0000"+
		"\u014e\u014f\u0003<\u001e\u0000\u014f\u0150\u00030\u0018\u0000\u0150\u0151"+
		"\u0006\u0017\uffff\uffff\u0000\u0151\u0153\u0001\u0000\u0000\u0000\u0152"+
		"\u014d\u0001\u0000\u0000\u0000\u0153\u0156\u0001\u0000\u0000\u0000\u0154"+
		"\u0152\u0001\u0000\u0000\u0000\u0154\u0155\u0001\u0000\u0000\u0000\u0155"+
		"/\u0001\u0000\u0000\u0000\u0156\u0154\u0001\u0000\u0000\u0000\u0157\u0158"+
		"\u0005\u0004\u0000\u0000\u0158\u0159\u00030\u0018\u0000\u0159\u015a\u0006"+
		"\u0018\uffff\uffff\u0000\u015a\u016b\u0001\u0000\u0000\u0000\u015b\u015c"+
		"\u0005\u0005\u0000\u0000\u015c\u015d\u00030\u0018\u0000\u015d\u015e\u0006"+
		"\u0018\uffff\uffff\u0000\u015e\u016b\u0001\u0000\u0000\u0000\u015f\u0160"+
		"\u0005\u0011\u0000\u0000\u0160\u0161\u00030\u0018\u0000\u0161\u0162\u0006"+
		"\u0018\uffff\uffff\u0000\u0162\u016b\u0001\u0000\u0000\u0000\u0163\u0164"+
		"\u0005\u0013\u0000\u0000\u0164\u0165\u00030\u0018\u0000\u0165\u0166\u0006"+
		"\u0018\uffff\uffff\u0000\u0166\u016b\u0001\u0000\u0000\u0000\u0167\u0168"+
		"\u00032\u0019\u0000\u0168\u0169\u0006\u0018\uffff\uffff\u0000\u0169\u016b"+
		"\u0001\u0000\u0000\u0000\u016a\u0157\u0001\u0000\u0000\u0000\u016a\u015b"+
		"\u0001\u0000\u0000\u0000\u016a\u015f\u0001\u0000\u0000\u0000\u016a\u0163"+
		"\u0001\u0000\u0000\u0000\u016a\u0167\u0001\u0000\u0000\u0000\u016b1\u0001"+
		"\u0000\u0000\u0000\u016c\u016d\u0006\u0019\uffff\uffff\u0000\u016d\u016e"+
		"\u0005\u0017\u0000\u0000\u016e\u016f\u0003&\u0013\u0000\u016f\u0170\u0005"+
		"\u0015\u0000\u0000\u0170\u0171\u0003\u001a\r\u0000\u0171\u0172\u0005\u0018"+
		"\u0000\u0000\u0172\u0173\u0006\u0019\uffff\uffff\u0000\u0173\u0181\u0001"+
		"\u0000\u0000\u0000\u0174\u0175\u0005\u0019\u0000\u0000\u0175\u0176\u0003"+
		"&\u0013\u0000\u0176\u0177\u0005\u001a\u0000\u0000\u0177\u0178\u0006\u0019"+
		"\uffff\uffff\u0000\u0178\u0181\u0001\u0000\u0000\u0000\u0179\u017a\u0005"+
		"*\u0000\u0000\u017a\u017b\u0003\u001a\r\u0000\u017b\u017c\u0006\u0019"+
		"\uffff\uffff\u0000\u017c\u0181\u0001\u0000\u0000\u0000\u017d\u017e\u0003"+
		":\u001d\u0000\u017e\u017f\u0006\u0019\uffff\uffff\u0000\u017f\u0181\u0001"+
		"\u0000\u0000\u0000\u0180\u016c\u0001\u0000\u0000\u0000\u0180\u0174\u0001"+
		"\u0000\u0000\u0000\u0180\u0179\u0001\u0000\u0000\u0000\u0180\u017d\u0001"+
		"\u0000\u0000\u0000\u0181\u0197\u0001\u0000\u0000\u0000\u0182\u0183\n\b"+
		"\u0000\u0000\u0183\u0184\u0005\u0013\u0000\u0000\u0184\u0196\u0006\u0019"+
		"\uffff\uffff\u0000\u0185\u0186\n\u0005\u0000\u0000\u0186\u0187\u0005\u001b"+
		"\u0000\u0000\u0187\u0188\u0003&\u0013\u0000\u0188\u0189\u0005\u001c\u0000"+
		"\u0000\u0189\u018a\u0006\u0019\uffff\uffff\u0000\u018a\u0196\u0001\u0000"+
		"\u0000\u0000\u018b\u018c\n\u0003\u0000\u0000\u018c\u018d\u0005\u0012\u0000"+
		"\u0000\u018d\u018e\u00051\u0000\u0000\u018e\u0196\u0006\u0019\uffff\uffff"+
		"\u0000\u018f\u0190\n\u0002\u0000\u0000\u0190\u0191\u0005\u0019\u0000\u0000"+
		"\u0191\u0192\u00034\u001a\u0000\u0192\u0193\u0005\u001a\u0000\u0000\u0193"+
		"\u0194\u0006\u0019\uffff\uffff\u0000\u0194\u0196\u0001\u0000\u0000\u0000"+
		"\u0195\u0182\u0001\u0000\u0000\u0000\u0195\u0185\u0001\u0000\u0000\u0000"+
		"\u0195\u018b\u0001\u0000\u0000\u0000\u0195\u018f\u0001\u0000\u0000\u0000"+
		"\u0196\u0199\u0001\u0000\u0000\u0000\u0197\u0195\u0001\u0000\u0000\u0000"+
		"\u0197\u0198\u0001\u0000\u0000\u0000\u01983\u0001\u0000\u0000\u0000\u0199"+
		"\u0197\u0001\u0000\u0000\u0000\u019a\u019b\u00036\u001b\u0000\u019b\u019c"+
		"\u0006\u001a\uffff\uffff\u0000\u019c\u019f\u0001\u0000\u0000\u0000\u019d"+
		"\u019f\u0006\u001a\uffff\uffff\u0000\u019e\u019a\u0001\u0000\u0000\u0000"+
		"\u019e\u019d\u0001\u0000\u0000\u0000\u019f5\u0001\u0000\u0000\u0000\u01a0"+
		"\u01a1\u0003&\u0013\u0000\u01a1\u01a2\u00038\u001c\u0000\u01a2\u01a3\u0006"+
		"\u001b\uffff\uffff\u0000\u01a37\u0001\u0000\u0000\u0000\u01a4\u01a5\u0005"+
		"\u0016\u0000\u0000\u01a5\u01a6\u00036\u001b\u0000\u01a6\u01a7\u0006\u001c"+
		"\uffff\uffff\u0000\u01a7\u01aa\u0001\u0000\u0000\u0000\u01a8\u01aa\u0006"+
		"\u001c\uffff\uffff\u0000\u01a9\u01a4\u0001\u0000\u0000\u0000\u01a9\u01a8"+
		"\u0001\u0000\u0000\u0000\u01aa9\u0001\u0000\u0000\u0000\u01ab\u01ac\u0005"+
		"\u0001\u0000\u0000\u01ac\u01ba\u0006\u001d\uffff\uffff\u0000\u01ad\u01ae"+
		"\u0005\u0003\u0000\u0000\u01ae\u01ba\u0006\u001d\uffff\uffff\u0000\u01af"+
		"\u01b0\u0005\u0002\u0000\u0000\u01b0\u01ba\u0006\u001d\uffff\uffff\u0000"+
		"\u01b1\u01b2\u0005,\u0000\u0000\u01b2\u01ba\u0006\u001d\uffff\uffff\u0000"+
		"\u01b3\u01b4\u0005\"\u0000\u0000\u01b4\u01ba\u0006\u001d\uffff\uffff\u0000"+
		"\u01b5\u01b6\u0005(\u0000\u0000\u01b6\u01ba\u0006\u001d\uffff\uffff\u0000"+
		"\u01b7\u01b8\u00051\u0000\u0000\u01b8\u01ba\u0006\u001d\uffff\uffff\u0000"+
		"\u01b9\u01ab\u0001\u0000\u0000\u0000\u01b9\u01ad\u0001\u0000\u0000\u0000"+
		"\u01b9\u01af\u0001\u0000\u0000\u0000\u01b9\u01b1\u0001\u0000\u0000\u0000"+
		"\u01b9\u01b3\u0001\u0000\u0000\u0000\u01b9\u01b5\u0001\u0000\u0000\u0000"+
		"\u01b9\u01b7\u0001\u0000\u0000\u0000\u01ba;\u0001\u0000\u0000\u0000\u01bb"+
		"\u01bc\u0005\u000e\u0000\u0000\u01bc\u01c2\u0006\u001e\uffff\uffff\u0000"+
		"\u01bd\u01be\u0005\u000f\u0000\u0000\u01be\u01c2\u0006\u001e\uffff\uffff"+
		"\u0000\u01bf\u01c0\u0005\u0010\u0000\u0000\u01c0\u01c2\u0006\u001e\uffff"+
		"\uffff\u0000\u01c1\u01bb\u0001\u0000\u0000\u0000\u01c1\u01bd\u0001\u0000"+
		"\u0000\u0000\u01c1\u01bf\u0001\u0000\u0000\u0000\u01c2=\u0001\u0000\u0000"+
		"\u0000\u01c3\u01c4\u0005\u0004\u0000\u0000\u01c4\u01c8\u0006\u001f\uffff"+
		"\uffff\u0000\u01c5\u01c6\u0005\u0005\u0000\u0000\u01c6\u01c8\u0006\u001f"+
		"\uffff\uffff\u0000\u01c7\u01c3\u0001\u0000\u0000\u0000\u01c7\u01c5\u0001"+
		"\u0000\u0000\u0000\u01c8?\u0001\u0000\u0000\u0000\u01c9\u01ca\u0005\b"+
		"\u0000\u0000\u01ca\u01d6\u0006 \uffff\uffff\u0000\u01cb\u01cc\u0005\t"+
		"\u0000\u0000\u01cc\u01d6\u0006 \uffff\uffff\u0000\u01cd\u01ce\u0005\n"+
		"\u0000\u0000\u01ce\u01d6\u0006 \uffff\uffff\u0000\u01cf\u01d0\u0005\u000b"+
		"\u0000\u0000\u01d0\u01d6\u0006 \uffff\uffff\u0000\u01d1\u01d2\u0005\f"+
		"\u0000\u0000\u01d2\u01d6\u0006 \uffff\uffff\u0000\u01d3\u01d4\u0005\r"+
		"\u0000\u0000\u01d4\u01d6\u0006 \uffff\uffff\u0000\u01d5\u01c9\u0001\u0000"+
		"\u0000\u0000\u01d5\u01cb\u0001\u0000\u0000\u0000\u01d5\u01cd\u0001\u0000"+
		"\u0000\u0000\u01d5\u01cf\u0001\u0000\u0000\u0000\u01d5\u01d1\u0001\u0000"+
		"\u0000\u0000\u01d5\u01d3\u0001\u0000\u0000\u0000\u01d6A\u0001\u0000\u0000"+
		"\u0000\u001dPiov\u0081\u0093\u009f\u00be\u00c5\u00ce\u00f5\u0100\u010c"+
		"\u0113\u0120\u012e\u0139\u0146\u0154\u016a\u0180\u0195\u0197\u019e\u01a9"+
		"\u01b9\u01c1\u01c7\u01d5";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}