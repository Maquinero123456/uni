import java_cup.runtime.*;
%%   
%cup 
%%      
\+    		                { return new Symbol(sym.MAS); }
\-    		                { return new Symbol(sym.MENOS); }
\*    		                { return new Symbol(sym.POR); }
\/    		                { return new Symbol(sym.DIV); }
\(    		                { return new Symbol(sym.AP); }
\)    		                { return new Symbol(sym.CP); }
0|[1-9][0-9]*				{ return new Symbol(sym.NUMERO, yytext() ); }
[a-zA-Z][a-zA-Z0-9]*		{ return new Symbol(sym.IDENT, yytext() ); }
.|\n						{  }   
