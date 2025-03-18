parser grammar Prev25Parser;

@header {

	package compiler.phase.synan;
	
	import java.util.*;
	import compiler.common.report.*;
	import compiler.phase.lexan.*;
	import compiler.phase.abstr.*;

}

@members {

	private Location loc(Token     tok) { return new Location((LexAn.LocLogToken)tok); }
	private Location loc(Locatable tok) { return new Location(tok); }
	private Location loc(Token     tok1, Token     tok2) { return new Location((LexAn.LocLogToken)tok1, (LexAn.LocLogToken)tok2); }
	private Location loc(Token     tok1, Locatable loc2) { return new Location((LexAn.LocLogToken)tok1, loc2); }
	private Location loc(Locatable loc1, Token     tok2) { return new Location(loc1, (LexAn.LocLogToken)tok2); }
	private Location loc(Locatable loc1, Locatable loc2) { return new Location(loc1, loc2); }

}

options{
    tokenVocab=Prev25Lexer;
}

source returns [ AST.Nodes<AST.FullDefn> ast ]
	: defs EOF { $ast = new AST.Nodes<AST.FullDefn> ($defs.ast); }
	;
defs returns [ List<AST.FullDefn> ast ]
	: d=defs def { $ast = $d.ast; $ast.addLast($def.ast); }
	| def { $ast = new ArrayList<AST.FullDefn>(); $ast.addLast($def.ast); }
	;
def returns [ AST.FullDefn ast]
	: TYP IDENTIFIER ASSIGNMENT type { $ast = new AST.TypDefn(loc($TYP, $type.ast), $IDENTIFIER.getText(), $type.ast); } // SYN: 2
	| VAR IDENTIFIER COLON type { $ast = new AST.VarDefn(loc($VAR, $type.ast), $IDENTIFIER.getText(), $type.ast); } // SYN: 3
	| FUN IDENTIFIER LPAR args1 RPAR COLON type impl=implementation[$FUN, $IDENTIFIER, $args1.ast, $type.ast] { $ast = $implementation.ast; }
	;
args1 returns [ List<AST.ParDefn> ast ]
	: args { $ast = $args.ast; }
	| { $ast = new ArrayList<AST.ParDefn>(); }
	;
implementation [ Token fun, Token id, List<AST.ParDefn> impl_args, AST.Type impl_type] returns [ AST.FunDefn ast ] // SYN: 5
	: ASSIGNMENT statements { $ast = new AST.DefFunDefn(loc(fun, $statements.ast.get($statements.ast.size() - 1)), id.getText(), impl_args, impl_type, $statements.ast ); }
	| { $ast = new AST.ExtFunDefn(loc(fun, impl_type), id.getText(), impl_args, impl_type); }
	;
args returns [ List<AST.ParDefn> ast ] // function args
	: arg args2 { $ast = $args2.ast; $ast.add(0, $arg.ast); }
	;
args2 returns [ List<AST.ParDefn> ast ]
	: COMMA args { $ast = $args.ast; }
	| { $ast = new ArrayList<AST.ParDefn>(); }
	;
arg returns [ AST.ParDefn ast ]
	: IDENTIFIER COLON type { $ast = new AST.ParDefn(loc($IDENTIFIER, $type.ast), $IDENTIFIER.getText(), $type.ast); }
	;

statements returns [ List<AST.Stmt> ast ]
	: s=statements COMMA statement { $ast = $s.ast; $ast.addLast($statement.ast); }
	| statement { $ast = new ArrayList<AST.Stmt>(); $ast.addLast($statement.ast); }
	;
empty_statements returns [ List<AST.Stmt> ast ]
	: statements COMMA statement { $ast = $statements.ast; $ast.addLast($statement.ast); }
	| statement { $ast = new ArrayList<AST.Stmt>(); $ast.addLast($statement.ast); }
	| { $ast = new ArrayList<AST.Stmt>(); }
	;
statement returns [ AST.Stmt ast ]
	: expr expr_extension [$expr.ast] { $ast = $expr_extension.ast; }
	| RETURN expr { $ast = new AST.ReturnStmt(loc($RETURN, $expr.ast), $expr.ast); }
	| WHILE expr DO statements END { $ast = new AST.WhileStmt(loc($WHILE, $END), $expr.ast, $statements.ast); }
	| IF expr THEN empty_statements e=else [ $IF, $expr.ast, $empty_statements.ast ] { $ast = $e.ast; }
	| LET defs IN statements END { $ast = new AST.LetStmt(loc($LET, $END), $defs.ast, $statements.ast); }
	;
expr_extension [ AST.Expr e ] returns [ AST.Stmt ast ] 
	: ASSIGNMENT expr { $ast = new AST.AssignStmt(loc(e, $expr.ast), e, $expr.ast); }
	| { $ast = new AST.ExprStmt(loc((Locatable) e), e); }
	;
else [ Token start, AST.Expr cond, List<AST.Stmt> stmts ] returns [ AST.Stmt ast ]
	: ELSE empty_statements END { $ast = new AST.IfThenElseStmt(loc(start, $END), cond, stmts, $empty_statements.ast); }
	| END { $ast = new AST.IfThenStmt(loc(start, $END), cond, stmts); }
	;
type returns [ AST.Type ast ]
	: INT { $ast = new AST.AtomType(loc($INT), AST.AtomType.Type.INT); }
	| CHAR { $ast = new AST.AtomType(loc($CHAR), AST.AtomType.Type.CHAR); }
	| BOOL { $ast = new AST.AtomType(loc($BOOL), AST.AtomType.Type.BOOL); }
	| VOID { $ast = new AST.AtomType(loc($VOID), AST.AtomType.Type.VOID); }
	| IDENTIFIER { $ast = new AST.NameType(loc($IDENTIFIER), $IDENTIFIER.getText()); }
	| LBRACKET INTCONST RBRACKET type { $ast = new AST.ArrType(loc($LBRACKET, $type.ast), $type.ast, $INTCONST.getText()); }
	| POW type { $ast = new AST.PtrType(loc($POW, $type.ast), $type.ast); }
	| LT comps GT { $ast = new AST.StrType(loc($LT, $GT), $comps.ast); } // TODO: fix comps returning only ParDefn
	| LBRACE comps RBRACE { $ast = new AST.UniType(loc($LBRACE, $RBRACE), $comps.ast); }
	| LPAR types RPAR COLON type { $ast = new AST.FunType(loc($LPAR, $type.ast), $types.ast, $type.ast); }
	;
comps returns [ List<AST.CompDefn> ast ] // function comps
	: comp comps2 { $ast = $comps2.ast; $ast.add(0, $comp.ast); }
	;
comps2 returns [ List<AST.CompDefn> ast ]
	: COMMA comps { $ast = $comps.ast; }
	| { $ast = new ArrayList<AST.CompDefn>(); }
	;
comp returns [ AST.CompDefn ast ]
	: IDENTIFIER COLON type { $ast = new AST.CompDefn(loc($IDENTIFIER, $type.ast), $IDENTIFIER.getText(), $type.ast); }
	;
types returns [ List<AST.Type> ast ]
	: type types2 { $ast = $types2.ast; $ast.add(0, $type.ast); }
	| { $ast = new ArrayList<AST.Type>(); }
	;
types2 returns [ List<AST.Type> ast ]
	: COMMA types { $ast = $types.ast; }
	| { $ast = new ArrayList<AST.Type>(); }
	;
expr returns [ AST.Expr ast ]
	: e=expr OR expr2 { $ast = new AST.BinExpr(loc($e.ast, $expr2.ast), AST.BinExpr.Oper.OR, $e.ast, $expr2.ast); }
	| expr2 { $ast = $expr2.ast; }
	;
expr2 returns [ AST.Expr ast ]
	: e=expr2 AND expr3 { $ast = new AST.BinExpr(loc($e.ast, $expr3.ast), AST.BinExpr.Oper.AND, $e.ast, $expr3.ast); }
	| expr3 { $ast = $expr3.ast; }
	;
expr3 returns [ AST.Expr ast ]
	: e1=expr4 comparitive_ops e2=expr4 { $ast = new AST.BinExpr(loc($e1.ast, $e2.ast), $comparitive_ops.ast, $e1.ast, $e2.ast); }
	| expr4 { $ast = $expr4.ast; }
	;
expr4 returns [ AST.Expr ast ]
	: e1=expr4 additive_ops e2=expr5 { $ast = new AST.BinExpr(loc($e1.ast, $e2.ast), $additive_ops.ast, $e1.ast, $e2.ast); }
	| expr5 { $ast = $expr5.ast; }
	;
expr5 returns [ AST.Expr ast ]
	: e1=expr5 multiplicative_ops e2=expr6 { $ast = new AST.BinExpr(loc($e1.ast, $e2.ast), $multiplicative_ops.ast, $e1.ast, $e2.ast); }
	| expr6 { $ast = $expr6.ast; }
	;
expr6 returns [ AST.Expr ast ]
	: PLUS expr6 { $ast = new AST.PfxExpr(loc($PLUS, $expr6.ast), AST.PfxExpr.Oper.ADD, $expr6.ast); }
	| MINUS expr6 { $ast = new AST.PfxExpr(loc($MINUS, $expr6.ast), AST.PfxExpr.Oper.SUB, $expr6.ast); }
	| NOT expr6 { $ast = new AST.PfxExpr(loc($NOT, $expr6.ast), AST.PfxExpr.Oper.NOT, $expr6.ast); }
	| POW expr6 { $ast = new AST.PfxExpr(loc($POW, $expr6.ast), AST.PfxExpr.Oper.PTR, $expr6.ast); }
	| expr7 { $ast = $expr7.ast; }
	;
expr7 returns [ AST.Expr ast ]
	: e=expr7 POW { $ast = new AST.SfxExpr(loc($e.ast, $POW), AST.SfxExpr.Oper.PTR, $e.ast); }
	| LBRACE expr COLON type RBRACE { $ast = new AST.CastExpr(loc($LBRACE, $RBRACE), $type.ast, $expr.ast); }
	| LPAR expr RPAR { $ast = $expr.ast; }
	| e=expr7 LBRACKET expr RBRACKET { $ast = new AST.ArrExpr(loc($e.ast, $RBRACKET), $e.ast, $expr.ast); }
	| SIZEOF type { $ast = new AST.SizeExpr(loc($SIZEOF, $type.ast), $type.ast); }
	| e=expr7 DOT IDENTIFIER { $ast = new AST.CompExpr(loc($e.ast, $IDENTIFIER), $e.ast, $IDENTIFIER.getText()); }
	| e=expr7 LPAR exprs_in RPAR { $ast = new AST.CallExpr(loc($e.ast, $RPAR), $e.ast, $exprs_in.ast); }
	| terminals { $ast = $terminals.ast; }
	;
exprs_in returns [ List<AST.Expr> ast ]
	: exprs { $ast = $exprs.ast; }
	| { $ast = new ArrayList<AST.Expr>(); }
	;
exprs returns [ List<AST.Expr> ast ]
	: expr exprs2 { $ast = $exprs2.ast; $ast.add(0, $expr.ast); }
	;
exprs2 returns [ List<AST.Expr> ast ]
	: COMMA exprs { $ast = $exprs.ast; }
	| { $ast = new ArrayList<AST.Expr>(); }
	;
terminals returns [ AST.Expr ast ]
	: INTCONST { $ast = new AST.AtomExpr(loc($INTCONST), AST.AtomExpr.Type.INT, $INTCONST.getText()); }
	| STRCONST { $ast = new AST.AtomExpr(loc($STRCONST), AST.AtomExpr.Type.STR, $STRCONST.getText()); }
	| CHARCONST { $ast = new AST.AtomExpr(loc($CHARCONST), AST.AtomExpr.Type.CHAR, $CHARCONST.getText()); }
	| TRUE { $ast = new AST.AtomExpr(loc($TRUE), AST.AtomExpr.Type.BOOL, $TRUE.getText()); }
	| FALSE { $ast = new AST.AtomExpr(loc($FALSE), AST.AtomExpr.Type.BOOL, $FALSE.getText()); }
	| NULL { $ast = new AST.AtomExpr(loc($NULL), AST.AtomExpr.Type.PTR, "0"); }
	| IDENTIFIER { $ast = new AST.NameExpr(loc($IDENTIFIER), $IDENTIFIER.getText()); }
	;
multiplicative_ops returns [ AST.BinExpr.Oper ast ]
	: STAR { $ast = AST.BinExpr.Oper.MUL; }
	| DIV { $ast = AST.BinExpr.Oper.DIV; }
	| MOD { $ast = AST.BinExpr.Oper.MOD; }
	;
additive_ops returns [ AST.BinExpr.Oper ast ]
	: PLUS { $ast = AST.BinExpr.Oper.ADD; }
	| MINUS { $ast = AST.BinExpr.Oper.SUB; }
	;
comparitive_ops returns [ AST.BinExpr.Oper ast ]
	: EQ { $ast = AST.BinExpr.Oper.EQU; }
	| NEQ { $ast = AST.BinExpr.Oper.NEQ; }
	| LTE { $ast = AST.BinExpr.Oper.LEQ; }
	| GTE { $ast = AST.BinExpr.Oper.GEQ; }
	| LT { $ast = AST.BinExpr.Oper.LTH; }
	| GT { $ast = AST.BinExpr.Oper.GTH; }
	;