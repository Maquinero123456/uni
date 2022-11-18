import java_cup.runtime.*;

%%
%cup
%%

\+ {return new Symbol(sym.PLUS);}
\- {return new Symbol(sym.MINUS);}
\* {return new Symbol(sym.TIMES);}
\/ {return new Symbol(sym.DIVIDE);}
\( {return new Symbol(sym.LPAREN);}
\) {return new Symbol(sym.RPAREN);}
[0-9]+ {return new Symbol(sym.NUMERO, yytext());}
\r|\n {return new Symbol(sym.EOLN);}
\ |\t {}
[^] {throw new RuntimeException("Caracter no valido "+yytext());}
