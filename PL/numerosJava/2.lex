

%%

%%
[0-9] { 
                return new Yytoken(Yytoken.TOKEN_CTE_ENTERO, yytext());
           }
[1-9][0-9]* { 
                return new Yytoken(Yytoken.TOKEN_CTE_ENTERO, yytext());
           }
[1-9][0-9]*[lL] { 
                return new Yytoken(Yytoken.TOKEN_CTE_ENTERO_LARGO, yytext());
           }
0[0-7]* { 
                return new Yytoken(Yytoken.TOKEN_CTE_ENTERO, yytext());
           }
0[0-7]*[lL] { 
                return new Yytoken(Yytoken.TOKEN_CTE_ENTERO_LARGO, yytext());
           }
0[xX][0-9aAbBcCdDfF]+ { 
                return new Yytoken(Yytoken.TOKEN_CTE_ENTERO, yytext());
           }
0[xX][0-9aAbBcCdDfF]+[lL] { 
                return new Yytoken(Yytoken.TOKEN_CTE_ENTERO_LARGO, yytext());
           }
[1-9][0-9]*([Ee][+-])?[0-9]*\.[0-9]+([Ee][+-])?[0-9]*[fF] { 
                return new Yytoken(Yytoken.TOKEN_CTE_REAL_CORTO, yytext());
           }
[1-9][0-9]*[Ee][+-][0-9]+ { 
                return new Yytoken(Yytoken.TOKEN_CTE_REAL_LARGO, yytext());
           }
[1-9][0-9]*([Ee][+-])?[0-9]*\.[0-9]*([Ee][+-])?[0-9]*[dD]? { 
                return new Yytoken(Yytoken.TOKEN_CTE_REAL_LARGO, yytext());
           }
[a-zA-Z0-9]+    {
                return new Yytoken(Yytoken.TOKEN_ERROR, yytext());
            }
[1-9][^xX][0-7]+[lL]? {
                return new Yytoken(Yytoken.TOKEN_ERROR, yytext());
            }
0[0-9]+[lL]? {
                return new Yytoken(Yytoken.TOKEN_ERROR, yytext());
            }
0[xX][lL]? {
                return new Yytoken(Yytoken.TOKEN_ERROR, yytext());
            }
\n         {}
.          {} 
