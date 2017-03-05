package org.unitec.compiladores;

import java_cup.runtime.*;

%%

%class PascalFlexer
%unicode
%line
%column
%integer
%caseless
%cup
%public

%{
    
      StringBuffer string = new StringBuffer();

      private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
      }
      private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
      }
%}

CabeceraPrograma    =   program{WhiteSpace}+{Identificador} ( \({Identificador}(, {Identificador})* \) )?
Comentario          =   \{{Caracter}*\}  |  \(\*{Caracter}*\*\) 
Literal             =   {LiteralEntero}|{LiteralBoolean}|{LiteralCaracter}|{LiteralString}
TipoArray           =   array\[{TipoIndice}\] of {TipoDato}
TipoDato            =   {TipoInteger}|{TipoChar}|{TipoString}
DefinicionTipos     =   type{WhiteSpace}+{DefinicionTipo}(;{DefinicionTipo})*;
DefinicionTipo      =   {Identificador} = {TipoDato}
ExpresionWrite      =   write{WhiteSpace}*\(({LiteralString}|{LiteralCaracter}) (, {Identificador})? \)
ExpresionWriteLn    =   writeln{WhiteSpace}*\({LiteralString}|{LiteralCaracter}\)
ExpresionRead       =   read{WhiteSpace}*\({Identificador}\)
DeclaracionVariables=   var {Identificador} (,{Identificador})*:
TipoIndice          =   {TipoPredefinido} | {TipoSubRango}
TipoSubRango        =   {TipoDato}\.\.{TipoDato}
TipoPredefinido     =   char | integer | boolean

Array                       =	array
Begin                       =	begin
BracketAbrir                =	\[
BracketCerrar               =	\]
Caracter                    =	.
CaracterSubrayado           =   _
Coma                        =	,
Digito                      =	[0-9]
DosPuntos                   =	:
DosPuntosIgual              =	:=
End                         =	end
Punto                       =   \.
OperadorIgual               =	=
Identificador               =	{Letra}({Letra}|{Digito})*
LetraMayuscula              =   [A-Z]
LetraMinuscula              =   [a-z]
Letra                       =   {LetraMayuscula} | {LetraMinuscula} | {CaracterSubrayado}
LineTerminator              =	\r|\n|\r\n
LiteralBoolean              =	true | false
LiteralCaracter             =	'[^']'
LiteralEntero               =	[+-]*{WhiteSpace}*{Digito}+
LiteralString               =	'[^']*'
LlaveAbrir                  =	\{
LlaveCerrar                 =	\}
Of                          =	of
ParentesisAbrir             =	\(
ParentesisCerrar            =	\)
Programa                    =	program{WhiteSpace}+
PuntoComa                   =	;
PuntoPunto                  =	\.\.
Tipo                        =	type
TipoChar                    =	char
TipoInteger                 =	integer
TipoString                  =	string | string\[{LiteralEntero}+\]
TipoBoolean                 =   boolean
Var                         =	var
WhiteSpace                  =	{LineTerminator} | [ \t\f]
Write                       =	write
WriteLn                     =	writeln
Read                        =   read
OperadorDiferente           =   <>
OperadorMayor               =   >
OperadorMenor               =   <
OperadorMayorIgual          =   >=
OperadorMenorIgual          =   <=
OperadorAnd                 =   and
OperadorOr                  =   or
OperadorNot                 =   not
OperadorMas                 =   \+
OperadorMenos               =   \-
OperadorSuma                =   [+-]
OperadorMultiplicacion      =   [/*]

%state COMMENT
%state STRING_LITERAL

%%
<YYINITIAL> {
    {WhiteSpace}                    {}
    {LlaveAbrir}                    {yybegin(COMMENT);}
    {LlaveCerrar}                   {return new Symbol(sym.LlaveCerrar);}
    {Programa}                      {return new Symbol(sym.Programa);}
    {Coma}                          {return new Symbol(sym.Coma); }
    {Punto}                         {return new Symbol(sym.Punto);}
    {PuntoComa}                     {return new Symbol(sym.PuntoComa);}
    {DosPuntosIgual}                {return new Symbol(sym.DosPuntosIgual);}
    {DosPuntos}                     {return new Symbol(sym.DosPuntos);}
    {BracketAbrir}                  {return new Symbol(sym.BracketAbrir);}        
    {BracketCerrar}                 {return new Symbol(sym.BracketCerrar);}
    {OperadorIgual}                 {return new Symbol(sym.OperadorIgual);}
    {OperadorMayorIgual}            {return new Symbol(sym.OperadorMayorIgual);}
    {OperadorMenorIgual}            {return new Symbol(sym.OperadorMenorIgual);}
    {OperadorMayor}                 {return new Symbol(sym.OperadorMayor);}
    {OperadorMenor}                 {return new Symbol(sym.OperadorMenor);}
    {OperadorAnd}                   {return new Symbol(sym.OperadorAnd);}
    {OperadorOr}                    {return new Symbol(sym.OperadorOr);}
    {OperadorNot}                   {return new Symbol(sym.OperadorNot);}
    {OperadorMas}                   {return new Symbol(sym.OperadorMas);}
    {OperadorSuma}                  {return new Symbol(sym.OperadorSuma);}
    {OperadorMultiplicacion}        {return new Symbol(sym.OperadorMultiplicacion);}
    {Tipo}                          {return new Symbol(sym.Tipo);}
    {TipoChar}                      {return new Symbol(sym.TipoChar);}
    {TipoInteger}                   {return new Symbol(sym.TipoInteger);}
    {TipoBoolean}                   {return new Symbol(sym.TipoBoolean);}
    {TipoString}                    {return new Symbol(sym.TipoString);}
    {LiteralCaracter}               {return new Symbol(sym.LiteralCaracter);}
    {LiteralString}                 {return new Symbol(sym.LiteralString);}
    {LiteralEntero}                 {return new Symbol(sym.LiteralEntero);}
    {LiteralBoolean}                {return new Symbol(sym.LiteralBoolean);}
    {ParentesisAbrir}               {return new Symbol(sym.ParentesisAbrir);}
    {ParentesisCerrar}              {return new Symbol(sym.ParentesisCerrar);}
    {Var}                           {return new Symbol(sym.Var);}
    {Array}                         {return new Symbol(sym.Array);}
    {Of}                            {return new Symbol(sym.Of);}
    {Begin}                         {return new Symbol(sym.Begin);}
    {End}                           {return new Symbol(sym.End);}
    {Write}                         {return new Symbol(sym.Write});}
    {WriteLn}                       {return new Symbol(sym.WriteLn);}
    {Read}                          {return new Symbol(sym.Read);}
    {Identificador}                 {return new Symbol(sym.Identificador);}
    
    .                               {throw new Error("Illegal character <"+yytext()+">");}
}

<COMMENT> {
    {LlaveCerrar}   {yybegin(YYINITIAL);}
    .               {}
    
}
