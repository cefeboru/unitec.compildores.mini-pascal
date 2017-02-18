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




Identificador       =   {Letra}({Letra}|{Digito})*
CabeceraPrograma    =   program{WhiteSpace}+{Identificador} ( \({Identificador}(, {Identificador})* \) )?
Begin               =   Begin
End                 =   End\.

Programa            =   program{WhiteSpace}+
ParentesisAbrir     =   \(
ParentesisCerrar    =   \)
Coma                =   ,
PuntoComa           =   ;
Tipo                =   type
Array               =   array
Of                  =   of
BracketAbrir        =   \[
BracketCerrar       =   \]
Var                 =   var
Equal               =   =
DosPuntos           =   :
DosPuntosEqual      =   :=
Write               =   write
WriteLn             =   writeln
ComillaSimple       =   '


CaracterSubrayado   =   _
LiteralString       =   '{Caracter}+'
LetraMayuscula      =   [A-Z]
LetraMinuscula      =   [a-z]
Letra               =   {LetraMayuscula} | {LetraMinuscula} | {CaracterSubrayado}
Digito              =   [0-9]
Signo               =   [+-]
Caracter            =   .
Comentario          =   \{{Caracter}*\}  |  \(\*{Caracter}*\*\) 
LiteralEntero       =   {Signo}{Digito}+
LiteralBoolean      =   true | false
LiteralCaracter     =   '{Caracter}?'
Literal             =   {LiteralEntero}|{LiteralBoolean}|{LiteralCaracter}|{LiteralString}
TipoInteger         =   integer
TipoChar            =   char
TipoString          =   string | string\[{LiteralEntero}+\]
TipoArray           =   array\[{TipoIndice}\] of {TipoDato}
TipoIndice          =   {TipoPredefinido} | {TipoSubRango}
TipoSubRango        =   {TipoDato}\.\.{TipoDato}
TipoPredefinido     =   char | integer | boolean
TipoDato            =   {TipoInteger}|{TipoChar}|{TipoString}
DefinicionTipos     =   type{WhiteSpace}+{DefinicionTipo}(;{DefinicionTipo})*;
DefinicionTipo      =   {Identificador} = {TipoDato}
ExpresionWrite      =   write{WhiteSpace}*\(({LiteralString}|{LiteralCaracter}) (, {Identificador})? \)
ExpresionWriteLn    =   writeln{WhiteSpace}*\({LiteralString}|{LiteralCaracter}\)
ExpresionRead       =   read{WhiteSpace}*\({Identificador}\)

DeclaracionVariables=   var {Identificador} (,{Identificador})*:

InicioComentario    = \{
FinComentario       = \}
LineTerminator      = \r|\n|\r\n
WhiteSpace          = {LineTerminator} | [ \t\f]


%state COMMENT
%state asd

%%
<YYINITIAL> {
    {WhiteSpace}            {}
    {DosPuntosEqual}        {}
    {Equal}                 {}
    {DosPuntos}             {}
    {TipoIndice}            {}
    {TipoDato}              {}
    {Literal}               {}
    {Programa}              {}
    {ParentesisAbrir}       {}
    {Identificador}         {}
    {Coma}                  {}
    {ParentesisCerrar}      {}
    {PuntoComa}             {}
    {Tipo}                  {}
    {Array}                 {}
    {BracketAbrir}          {}
    {BracketCerrar}         {}
    {Of}                    {}
    {Begin}                 {}
    {End}                   {}
    .                       {}
}

<COMMENT> {
    {Digito}  |
    {Letra}   |
    {Digito}                  { /* ignore */ }
    {InicioComentario}        { System.out.println("Comments can't be nested"); }
    {FinComentario}           { yybegin(YYINITIAL);}
}
