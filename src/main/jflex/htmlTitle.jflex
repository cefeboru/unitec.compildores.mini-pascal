package org.unitec.compiladores.jflex;
%%

%class Lexer
%unicode
%line
%column
%integer


LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \t\f]
Letter         = [a-zA-Z]
Number         = [0-9]
OpenTag        = <
CloseTag       = >
OpenClosingTag = <//
TitleTag          = {OpenTag}title{CloseTag}({Letter}|{Number}|{WhiteSpace})*{OpenClosingTag}title{CloseTag}



%%

<YYINITIAL> {
    {WhiteSpace}    { /*ignore*/ }
    {TitleTag}      { System.out.println(yytext());}
    .               { /*ignore*/}
}