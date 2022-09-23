

%%

%%
[a-zA-Z0-9]+ { 
                return new Yytoken(Yytoken.word, yytext());
           }
[,.:;]        {
               return new Yytoken(Yytoken.caracter, yytext());
           }
\n         {
               return new Yytoken(Yytoken.EOLN, yytext());
           }
.          {
              return new Yytoken(Yytoken.caracter, yytext());
} 
