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




Program             = Program
CabeceraPrograma    = program[ ]* {IdPrograma}
Identificador       = {Letra} ({Letra} | {Digito})*
Begin               = Begin
End                 = End\.
CaracterSubrayado   = _
LetraMayuscula      = [A-Z]
LetraMinuscula      = [a-z]
Letra               = {LetraMayuscula} | {LetraMinuscula} | {CaracterSubrayado}
Digito              = [0-9]

InicioComentario    = \{
FinComentario       = \}
WhiteSpace          = {LineTerminator} | [ \t\f]
LineTerminator      = \r|\n|\r\n



%state COMMENT


%%
<YYINITIAL> {
    {InicioComentario}      { yybegin(COMMENT);System.out.println("Moving to state comment"); }
    {WhiteSpace}            { /*ignore*/}
    {LineTerminator}        {}
    {Program}               {}
    {Begin}                 {}
    {End}                   {}
    .                       {}
}

<COMMENT> {
    {Digito}  |
    {Letra}   |
    {Digito}                  { /* ignore */ }
    {InicioComentario}        { System.out.println("Comments can't be nested"); }
    {FinComentario}           { yybegin(YYINITIAL); System.out.println("Moving to initial state");}
}