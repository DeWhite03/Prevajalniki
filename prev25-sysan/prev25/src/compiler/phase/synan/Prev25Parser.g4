parser grammar Prev25Parser;

@header {

	package compiler.phase.synan;
	
	import java.util.*;
	import compiler.common.report.*;
	import compiler.phase.lexan.*;

}

@members {

	private Location loc(Token tok) { return new Location((LexAn.LocLogToken)tok); }
	private Location loc(Token     tok1, Token     tok2) { return new Location((LexAn.LocLogToken)tok1, (LexAn.LocLogToken)tok2); }
	private Location loc(Token     tok1, Locatable loc2) { return new Location((LexAn.LocLogToken)tok1, loc2); }
	private Location loc(Locatable loc1, Token     tok2) { return new Location(loc1, (LexAn.LocLogToken)tok2); }
	private Location loc(Locatable loc1, Locatable loc2) { return new Location(loc1, loc2); }

}

options{
    tokenVocab=Prev25Lexer;
}

source
	: defs EOF
	;
defs
	: def defs
	| def
	;
// defs 
// 	: def defs2
// 	;
// defs2 // defined to keep it LL1
// 	: defs
// 	| 
// 	;
def
	: TYP IDENTIFIER ASSIGNMENT type
	| VAR IDENTIFIER COLON type
	| FUN IDENTIFIER LPAR args RPAR COLON type implementation
	;
implementation
	:ASSIGNMENT statements
	|
	;
// args
// 	: arg COMMA args
// 	| args
// 	;
args
	: arg args2
	;
args2
	: COMMA args
	|
	;
arg
	: IDENTIFIER COLON type
	;
statements
	: statement COMMA statements
	| statement
	;
// statements
// 	: statement statements2
// 	;
// statements2
// 	: COMMA statements
// 	|
// 	;
statement
	: expression expr_extension
	| RETURN expression
	| WHILE expression DO statements END
	| IF expression THEN statements else END
	| LET defs IN statements END
	;
expr_extension
	: ASSIGNMENT expression
	|
	;
else
	: ELSE statements 
	| 
	;
type
	: INT | CHAR | BOOL | VOID | IDENTIFIER
	| LBRACKET INTCONST RBRACKET type
	| POW type
	| LT args GT
	| LBRACE args RBRACE
	| LPAR type RPAR
	;

expression
	: INTCONST | STRCONST | CHARCONST | TRUE | FALSE | NULL
	;
// 	| prefix_operators expression
// 	| expression LBRACKET expression RBRACKET
// 	| expression DOT IDENTIFIER
// 	| expression POWER | POWER expression
// 	| expression LPAR exprs RPAR
// 	| SIZEOF type
// 	| LBRACE expression COLON type RBRACE
// 	| LPAR expression RPAR
// 	| IDENTIFIER
// 	| expression2
// 	;
// expression5
		
// 	: expression5 LPAR DOT DOT DOT RPAR expression6
// 	| expression5 LBRACKET DOT RBRACKET expression6
// 	| expression5 POWER expression6
// 	| expression5 DOT expression6
// expression2
// 	: expression2 PLUS expression3
// 	| expression2 MINUS expression3
// 	| expression2 NOT expression3
// 	| expression2 POWER expression3
// 	| expression3
// 	;
// expression3
// 	: expression3 MUL expression4
// 	| expression3 DIV expression4
// 	| expression3 MOD expression4
// 	| expression4
// 	;

// exprs
// 	: expression exprs2
// 	;
// exprs2
// 	: COMMA exprs
// 	|
// 	;
// prefix_operators
// 	: PLUS | MINUS | NOT
// 	;