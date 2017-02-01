package org.unitec.compiladores.jflex;
%%

%class Lexer
%unicode
%line
%column
%integer
/* %standalone */

LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \t\f]
Letter         = [a-zA-Z]
Number         = [0-9]
OpenTag        = <
CloseTag       = >
OpenClosingTag = <//
Title          = {Letter}|{Number}|{WhiteSpace}

%%

<YYINITIAL> {
    {WhiteSpace}    { /*ignore*/ }
}