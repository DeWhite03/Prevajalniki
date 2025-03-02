parser grammar Prev25Parser;

@header {

	package compiler.phase.synan;
	
	import java.util.*;
	import compiler.common.report.*;
	import compiler.phase.lexan.*;

}

@members {

	private Location loc(Token     tok) { return new Location((LexAn.LocLogToken)tok); }
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
defs // SYN: 1
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
	: TYP IDENTIFIER ASSIGNMENT type // SYN: 2
	| VAR IDENTIFIER COLON type // SYN: 3
	| FUN IDENTIFIER LPAR args RPAR COLON type implementation // SYN: 4
	;
implementation // SYN: 5
	:ASSIGNMENT statements
	|
	;
// args
// 	: arg COMMA args
// 	| args
// 	;
args // function args
	: arg args2
	|
	;
args2
	: COMMA args
	|
	;
arg // argument
	: IDENTIFIER COLON type
	;
statements // lines of code
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
empty_statements // for if's where statements are not required.
	: statement COMMA statements
	| statement
	|
	;

statement
	: expression expr_extension
	| RETURN expression
	| WHILE expression DO statements END
	| IF expression THEN empty_statements else END
	| LET defs IN statements END
	;
expr_extension
	: ASSIGNMENT expression
	|
	;
else
	: ELSE empty_statements
	| 
	;
type
	: INT | CHAR | BOOL | VOID | IDENTIFIER
	| LBRACKET INTCONST RBRACKET type
	| POW type
	| LT args GT
	| LBRACE args RBRACE
	| LPAR types RPAR COLON type
	;
types
	: type types2
	|
	;
types2
	: COMMA types
	|
	;
expression
	: LPAR expression RPAR
	| LBRACE expression COLON type RBRACE
	| POW expression
	| expression POW
	| expr
	;

expr
	: expr OR expr2
	| expr2
	;
expr2
	: expr2 AND expr3
	| expr3
	;
expr3
	: expr4 comparitive_ops expr4
	| expr4
	;
expr4
	: expr4 additive_ops expr5
	| expr5
	;
expr5
	: expr5 multiplicative_ops expr6
	| expr6
	;
expr6
	: PLUS expr6
	| MINUS expr6
	| NOT expr6
	| POW expr6
	| expr7
	;
expr7
	: expr7 POW
	| terminals LBRACKET terminals RBRACKET
	| SIZEOF type
	| terminals DOT IDENTIFIER
	| terminals LPAR exprs RPAR
	| terminals
	;
exprs
	: expression exprs2
	|
	;
exprs2
	: COMMA exprs
	|
	;
terminals
	: INTCONST 
	| STRCONST 
	| CHARCONST 
	| TRUE 
	| FALSE 
	| NULL
	| IDENTIFIER
	;
multiplicative_ops
	: STAR
	| DIV
	| MOD
	;
additive_ops
	: PLUS | MINUS
	;
comparitive_ops
	: EQ | NEQ | LTE | GTE | LT | GT
	;
prefix
	: PLUS | MINUS | NOT
	;
