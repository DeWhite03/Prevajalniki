lexer grammar Prev25Lexer;

@header {
    package compiler.phase.lexan;

    import compiler.common.report.*;
}

@members {
    @Override
    public LexAn.LocLogToken nextToken() {
        return (LexAn.LocLogToken) super.nextToken();
    }
}
fragment STRCHAR: HEX 
                | ~[\u0000-\u001F\u007F-\uFFFF"\\] 
                | '\\\\' 
                | '\\"';
fragment HEX    : '\\0x'[0-9A-Fa-f][0-9A-Fa-f];
INTCONST        : [0-9]+;
CHARCONST       : '\''HEX'\''
                | '\''~['\u0000-\u001F\u007F-\uFFFF\\]'\''
                | '\'\\\\\''
                | '\'\\\'\'';
STRCONST        : '"'(STRCHAR)+'"';
PLUS            : '+';
MINUS           : '-';
AND             : '&';
OR              : '|';
EQ              : '==';
NEQ             : '!=';
LTE             : '<=';
GTE             : '>=';
LT              : '<';
GT              : '>';
STAR            : '*';
DIV             : '/';
MOD             : '%';
NOT             : '!';
DOT             : '.';
POW             : '^';
ASSIGNMENT      : '=';
COLON           : ':';
COMMA           : ',';
LBRACE          : '{';
RBRACE          : '}';
LPAR            : '(';
RPAR            : ')';
LBRACKET        : '[';
RBRACKET        : ']';
BOOL            : 'bool';
CHAR            : 'char';
DO              : 'do';
ELSE            : 'else';
END             : 'end';
FALSE           : 'false';
FUN             : 'fun';
IF              : 'if';
IN              : 'in';
INT             : 'int';
LET             : 'let';
NULL            : 'null';
RETURN          : 'return';
SIZEOF          : 'sizeof';
THEN            : 'then';
TRUE            : 'true';
TYPE            : 'typ';
VAR             : 'var';
VOID            : 'void';
WHILE           : 'while';
IDENTIFIER      : [a-zA-Z_][0-9a-zA-Z_]*;
COMMENT         : '#'~[\r\n]*;
WHITESPACE      : [ \n\r\t]+ -> skip ;
ERROR           : . ;
