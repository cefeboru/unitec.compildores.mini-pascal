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
LiteralCaracter             =	'[^']'
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
Begin                       =   begin
End                         =   end
For                         =   for
To                          =   to
Do                          =   do
While                       =   while
Repeat                      =   repeat
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
    {If}                            {return symbol(sym.If);}
    {Else}                          {return symbol(sym.Else);}
    {Then}                          {return symbol(sym.Then);}
    {For}                           {return symbol(sym.For);}
    {To}                            {return symbol(sym.To);}
    {Do}                            {return symbol(sym.Do);}
    {While}                         {return symbol(sym.While);}
    {Repeat}                        {return symbol(sym.Repeat);}
    {Until}                         {return symbol(sym.Until);}
    {Coma}                          {return symbol(sym.Coma); }
    {Punto}                         {return symbol(sym.Punto);}
    {PuntoComa}                     {return symbol(sym.PuntoComa);}
    {DosPuntosIgual}                {return symbol(sym.DosPuntosIgual);}
    {DosPuntos}                     {return symbol(sym.DosPuntos);}
    {BracketAbrir}                  {return symbol(sym.BracketAbrir);}        
    {BracketCerrar}                 {return symbol(sym.BracketCerrar);}
    {OperadorMayorIgual}            {return symbol(sym.OperadorMayorIgual,yytext());}
    {OperadorMenorIgual}            {return symbol(sym.OperadorMenorIgual,yytext());}
    {OperadorIgual}                 {return symbol(sym.OperadorIgual,yytext());}
    {OperadorMayor}                 {return symbol(sym.OperadorMayor,yytext());}
    {OperadorMenor}                 {return symbol(sym.OperadorMenor,yytext());}
    {OperadorAnd}                   {return symbol(sym.OperadorAnd,yytext());}
    {OperadorOr}                    {return symbol(sym.OperadorOr,yytext());}
    {OperadorNot}                   {return symbol(sym.OperadorNot,yytext());}
    {OperadorSuma}                  {return symbol(sym.OperadorSuma,yytext());}
    {OperadorResta}                 {return symbol(sym.OperadorResta,yytext());}
    {OperadorMultiplicacion}        {return symbol(sym.OperadorMultiplicacion,yytext());}
    {OperadorMod}                   {return symbol(sym.OperadorMod,yytext());}
    {OperadorDivision}              {return symbol(sym.OperadorDivision,yytext());}
    {OperadorDivisionSpecial}       {return symbol(sym.OperadorDivisionSpecial,yytext());}
    {Tipo}                          {return symbol(sym.Tipo,yytext());}
    {TipoChar}                      {return symbol(sym.TipoChar,yytext());}
    {TipoInteger}                   {return symbol(sym.TipoInteger,yytext());}
    {TipoBoolean}                   {return symbol(sym.TipoBoolean,yytext());}
    {TipoString}                    {return symbol(sym.TipoString,yytext());}
    {LiteralCaracter}               {System.out.println(yytext().charAt(1));return symbol(sym.LiteralCaracter, yytext().charAt(1));}
    {LiteralEntero}                 {return symbol(sym.LiteralEntero, yytext());}
    {LiteralBoolean}                {return symbol(sym.LiteralBoolean,yytext());}
    {ParentesisAbrir}               {return symbol(sym.ParentesisAbrir);}
    {ParentesisCerrar}              {return symbol(sym.ParentesisCerrar);}
    {Var}                           {return symbol(sym.Var);}
    {Array}                         {return symbol(sym.Array);}
    {Of}                            {return symbol(sym.Of);}
    {Begin}                         {return symbol(sym.Begin);}
    {End}                           {return symbol(sym.End);}
    {Write}                         {return symbol(sym.Write);}
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
