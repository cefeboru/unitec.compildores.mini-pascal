package org.unitec.compiladores.jflex;
%%

%class Flexer
%public
/*%debug*/
%unicode
%line
%column
%integer


LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \t\f]
Letter         = [a-zA-Z]
Number         = [0-9]
Content        = ({WhiteSpace}|{Number}|{Letter})*

OpenTitleTag   = <[tT][iI][tT][lL][eE]>
CloseTitleTag  = <\/[tT][iI][tT][lL][eE]>
DocumentTitle  = {OpenTitleTag}{Content}{CloseTitleTag}

%%

<YYINITIAL> {
    {WhiteSpace}     { /*ignore*/ }
    {DocumentTitle}  { System.out.println(yytext());}
    .                { /*ignore*/}
}