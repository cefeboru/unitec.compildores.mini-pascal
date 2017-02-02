
package org.unitec.compiladores.jflex;
%%

%class FlexerWordsA
%public
%unicode
%line
%column
%integer

%{
    int wordsWithA = 0;
%}

%eof{
    System.out.format("Found %d words starting with A", wordsWithA);
%eof}

LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \t\f]
Letter         = [a-zA-Z]
Number         = [0-9]
WordA           = [aA]({Letter}|{Number})*
Word            = ({Letter}|{Number})*

%%

<YYINITIAL> {
    {WordA}         {
                        System.out.format("Line %d word %s \n",yyline+1, yytext());
                        wordsWithA++;
                    }
    {Word}          {}
    {WhiteSpace}    {}
    .               {/*ignore*/}
}