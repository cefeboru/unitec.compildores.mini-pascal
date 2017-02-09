package org.unitec.compiladores.jflex;
%%

%class PascalFlexer
%debug
%public
%unicode
%line
%column
%integer
%caseless




CabeceraPrograma    = program[ ]* {Identificador}
Identificador       = {Letra} ({Letra} | {Digito})*
Begin               = Begin
End                 = End\.
CaracterSubrayado   = _
LetraMayuscula      = [A-Z]
LetraMinuscula      = [a-z]
Letra               = {LetraMayuscula} | {LetraMinuscula} | {CaracterSubrayado}
Digito              = [0-9]
Signo               = [+-]*
Caracter            = .
LiteralEntero       = {Signo}{Digito}+
LiteralBoolean      = true | false
LiteralCaracter     =  '{Caracter}'
LiteralString       =  '{Caracter}+'
Literal             = {LiteralEntero}|{LiteralBoolean}|{LiteralCaracter}|{LiteralString}

InicioComentario    = \{
FinComentario       = \}
WhiteSpace          = {LineTerminator} | [ \t\f]
LineTerminator      = \r|\n|\r\n



%state COMMENT


%%
<YYINITIAL> {
    {InicioComentario}      { yybegin(COMMENT);System.out.println("Moving to state comment"); }
    {WhiteSpace}            {}
    {LineTerminator}        {}
    {CabeceraPrograma}      {}
    {Begin}                 {}
    {End}                   {}
    {Literal}               {}
    {LiteralEntero}         {}
    {LiteralCaracter}       {}
    {LiteralString}         {}
    .                       {}
}

<COMMENT> {
    {Digito}  |
    {Letra}   |
    {Digito}                  { /* ignore */ }
    {InicioComentario}        { System.out.println("Comments can't be nested"); }
    {FinComentario}           { yybegin(YYINITIAL); System.out.println("Moving to initial state");}
}