package org.unitec.compiladores.jflex;
%%

%class PascalFlexer
%public
%unicode
%line
%column
%integer
%caseless

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
ComillaSimple               =	'
Digito                      =	[0-9]
DosPuntos                   =	:
DosPuntosIgual              =	:=
Else                        =   else
End                         =	end
Punto                       =   \.
OperadorIgual               =	=
Identificador               =	{Letra}({Letra}|{Digito})*
If                          =   if
LetraMayuscula              =   [A-Z]
LetraMinuscula              =   [a-z]
Letra                       =   {LetraMayuscula} | {LetraMinuscula} | {CaracterSubrayado}
LineTerminator              =	\r|\n|\r\n
LiteralBoolean              =	true | false
LiteralCaracter             =	'{Caracter}?'
LiteralEntero               =	{Digito}+
LiteralString               =	'{Caracter}*'
LlaveAbrir                  =	\{
LlaveCerrar                 =	\}
Of                          =	of
ParentesisAbrir             =	\(
ParentesisCerrar            =	\)
Programa                    =	program
PuntoComa                   =	;
PuntoPunto                  =	\.\.
Then                        =   then
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
OperadorSuma                =   [+-]
OperadorMultiplicacion      =   [*/]

%state COMMENT

%%
<YYINITIAL> {
    {WhiteSpace}                    {}
    {LlaveAbrir}                    {yybegin(COMMENT);}
    {LlaveCerrar}                   {System.out.println("LlaveCerrar: "+yytext());}
    {Programa}                      {System.out.println("Programa: "+yytext());}
    {Coma}                          {System.out.println("Coma: "+yytext());}
    {Punto}                         {System.out.println("Punto: "+yytext());}
    {PuntoComa}                     {System.out.println("PuntoComa: "+yytext());}
    {DosPuntosIgual}                {System.out.println("DosPuntosIgual: "+yytext());}
    {DosPuntos}                     {System.out.println("DosPuntos: "+yytext());}
    {BracketAbrir}                  {System.out.println("BracketAbrir: "+yytext());}        
    {BracketCerrar}                 {System.out.println("BracketCerrar: "+yytext());}
    {OperadorIgual}                 {System.out.println("OperadorIgual: "+yytext());}
    {OperadorMayorIgual}            {System.out.println("OperadorMayorIgual: "+yytext());}
    {OperadorMenorIgual}            {System.out.println("OperadorMenorIgual: "+yytext());}
    {OperadorMayor}                 {System.out.println("OperadorMayor: "+yytext());}
    {OperadorMenor}                 {System.out.println("OperadorMenor: "+yytext());}
    {OperadorAnd}                   {System.out.println("OperadorAnd: "+yytext());}
    {OperadorOr}                    {System.out.println("OperadorOr: "+yytext());}
    {OperadorNot}                   {System.out.println("OperadorNot: "+yytext());}
    {OperadorSuma}                  {System.out.println("OperadorSuma: "+yytext());}
    {OperadorMultiplicacion}        {System.out.println("OperadorMultiplicacion: "+yytext());}
    {Then}                          {System.out.println("Then: "+yytext());}
    {Tipo}                          {System.out.println("Tipo: "+yytext());}
    {TipoChar}                      {System.out.println("TipoChar: "+yytext());}
    {TipoInteger}                   {System.out.println("TipoInteger: "+yytext());}
    {TipoBoolean}                   {System.out.println("TipoBoolean: "+yytext());}
    {TipoString}                    {System.out.println("TipoString: "+yytext());}
    {LiteralCaracter}               {System.out.println("LiteralCaracter: "+yytext());}
    {LiteralString}                 {System.out.println("LiteralString: "+yytext());}
    {LiteralEntero}                 {System.out.println("LiteralEntero: "+yytext());}
    {LiteralBoolean}                {System.out.println("LiteralBoolean: "+yytext());}
    {ParentesisAbrir}               {System.out.println("ParentesisAbrir: "+yytext());}
    {ParentesisCerrar}              {System.out.println("ParentesisCerrar: "+yytext());}
    {If}                            {System.out.println("If: "+yytext());}
    {Else}                          {System.out.println("Else: "+yytext());}
    {Var}                           {System.out.println("Var: "+yytext());}
    {Array}                         {System.out.println("Array: "+yytext());}
    {Of}                            {System.out.println("Of: "+yytext());}
    {Begin}                         {System.out.println("Begin: "+yytext());}
    {End}                           {System.out.println("End: "+yytext());}
    {Write}                         {System.out.println("Write: "+yytext());}
    {WriteLn}                       {System.out.println("WriteLn: "+yytext());}
    {Read}                          {System.out.println("Read: "+yytext());}
    {Identificador}                 {System.out.println("Identificador: "+yytext());}
    
    .                               {System.out.println("Error!");}
}

<COMMENT> {
    {LlaveCerrar}   {yybegin(YYINITIAL);}
    .               {}
    
}
