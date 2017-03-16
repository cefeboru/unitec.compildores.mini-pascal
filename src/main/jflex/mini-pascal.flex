package org.unitec.compiladores;

import java_cup.runtime.*;

%%

%class PascalFlexer
%unicode
%debug
%line
%column
%int
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


//Funciones
Write                       =	write
WriteLn                     =	writeln
Read                        =   read

//Tipos de datos
Array                       =	array
Of                          =	of
Tipo                        =	type
Var                         =	var
TipoBoolean                 =   boolean
TipoChar                    =	char
TipoInteger                 =	integer
TipoString                  =	string

/*Literales*/
LiteralBoolean              =	true | false
LiteralCaracter             =	'{Letra}'
LiteralEntero               =	{Digito}+
LiteralString               =	'[^']*'

//Operadores
OperadorIgual               =	=
OperadorDiferente           =   <>
OperadorMayor               =   >
OperadorMenor               =   <
OperadorMayorIgual          =   >=
OperadorMenorIgual          =   <=
OperadorAnd                 =   and
OperadorOr                  =   or
OperadorNot                 =   not
OperadorSuma                =   \+
OperadorResta               =   -
OperadorMultiplicacion      =   \* 
OperadorMod                 =   mod
OperadorDivision            =   \/
OperadorDivisionSpecial     =   div

//Estructuras de control
If                          =   if
Then                        =   then
Else                        =   else
ElseIf                      =   else if
Begin                       =   begin
End                         =   end
For                         =   for
To                          =   to
Do                          =   do
While                       =   while
Until                       =   until

//Otros
Program                     =	program
Procedure                   =   procedure
Function                    =   function
Identificador               =	{Letra}({Letra}|{Digito})*
PuntoComa                   =	;
PuntoPunto                  =	\.\.
WhiteSpace                  =	{LineTerminator} | [ \t\f]
LineTerminator              =	\r|\n|\r\n
ParentesisAbrir             =	\(
ParentesisCerrar            =	\)
LlaveAbrir                  =	\{
LlaveCerrar                 =	\}
BracketAbrir                =	\[
BracketCerrar               =	\]
ComillaSimple               =   '
ComillaDentro               =   \'
Coma                        =	,
Letra                       =   [a-zA-Z_]
Digito                      =	[0-9]
DosPuntos                   =	:
DosPuntosIgual              =	:=
Punto                       =   \.

%state COMMENT
%state COMILLA_SIMPLE

%%
<YYINITIAL> {
    {WhiteSpace}                    {}
    {LlaveAbrir}                    {yybegin(COMMENT);}
    {ComillaSimple}                 {yybegin(COMILLA_SIMPLE);}
    {LlaveCerrar}                   {return symbol(sym.LlaveCerrar);}
    {Program}                       {return symbol(sym.Program);}
    {Procedure}                     {return symbol(sym.Procedure);}
    {Function}                      {return symbol(sym.Function);}
    {Coma}                          {return symbol(sym.Coma); }
    {Punto}                         {return symbol(sym.Punto);}
    {PuntoComa}                     {return symbol(sym.PuntoComa);}
    {DosPuntosIgual}                {return symbol(sym.DosPuntosIgual);}
    {DosPuntos}                     {return symbol(sym.DosPuntos);}
    {BracketAbrir}                  {return symbol(sym.BracketAbrir);}        
    {BracketCerrar}                 {return symbol(sym.BracketCerrar);}
    {OperadorMayorIgual}            {return symbol(sym.OperadorMayorIgual);}
    {OperadorMenorIgual}            {return symbol(sym.OperadorMenorIgual);}
    {OperadorIgual}                 {return symbol(sym.OperadorIgual);}
    {OperadorMayor}                 {return symbol(sym.OperadorMayor);}
    {OperadorMenor}                 {return symbol(sym.OperadorMenor);}
    {OperadorAnd}                   {return symbol(sym.OperadorAnd);}
    {OperadorOr}                    {return symbol(sym.OperadorOr);}
    {OperadorNot}                   {return symbol(sym.OperadorNot);}
    {OperadorSuma}                  {return symbol(sym.OperadorSuma);}
    {OperadorResta}                 {return symbol(sym.OperadorResta);}
    {OperadorMultiplicacion}        {return symbol(sym.OperadorMultiplicacion);}
    {OperadorMod}                   {return symbol(sym.OperadorMod);}
    {OperadorDivision}              {return symbol(sym.OperadorDivision);}
    {OperadorDivisionSpecial}       {return symbol(sym.OperadorDivisionSpecial);}
    {Tipo}                          {return symbol(sym.Tipo);}
    {TipoChar}                      {return symbol(sym.TipoChar);}
    {TipoInteger}                   {return symbol(sym.TipoInteger);}
    {TipoBoolean}                   {return symbol(sym.TipoBoolean);}
    {TipoString}                    {return symbol(sym.TipoString);}
    {LiteralCaracter}               {return symbol(sym.LiteralCaracter);}
    {LiteralString}                 {return symbol(sym.LiteralString);}
    {LiteralEntero}                 {return symbol(sym.LiteralEntero, new Integer(Integer.parseInt(yytext())));}
    {LiteralBoolean}                {return symbol(sym.LiteralBoolean);}
    {ParentesisAbrir}               {return symbol(sym.ParentesisAbrir);}
    {ParentesisCerrar}              {return symbol(sym.ParentesisCerrar);}
    {Var}                           {return symbol(sym.Var);}
    {Array}                         {return symbol(sym.Array);}
    {Of}                            {return symbol(sym.Of);}
    {Begin}                         {return symbol(sym.Begin);}
    {End}                           {return symbol(sym.End);}
    {Write}                         {return symbol(sym.Write);}
    {WriteLn}                       {return symbol(sym.WriteLn);}
    {Read}                          {return symbol(sym.Read);}
    {Identificador}                 {return symbol(sym.Identificador, yytext());} 
    .                               {throw new Error("Illegal character <"+yytext()+">");}
}

<COMMENT> {
    {LlaveCerrar}               {yybegin(YYINITIAL);}
    .                           {}
}

<COMILLA_SIMPLE> {
    {ComillaSimple} 		{yybegin(YYINITIAL);return symbol(sym.LiteralString,string.toString());}
    {ComillaDentro}             {string.append("\'");}
    .                           {string.append(yytext());}
}