

%%

%%
[0-9] { 
                return new Yytoken(Yytoken.TOKEN_CTE_ENTERO, yytext());
           }
[1-9][0-9]+ { 
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
[0-9]*[dD] { 
                return new Yytoken(Yytoken.TOKEN_CTE_REAL_LARGO, yytext());
           }
[0-9]*[fF] { 
                return new Yytoken(Yytoken.TOKEN_CTE_REAL_CORTO, yytext());
           }
[0-9]*[eE][+-]?[0-9][0-9]*[dD]? { 
                return new Yytoken(Yytoken.TOKEN_CTE_REAL_LARGO, yytext());
           }
[0-9]*[eE][+-]?[0-9][0-9]*[fF]? { 
                return new Yytoken(Yytoken.TOKEN_CTE_REAL_CORTO, yytext());
           }
[0-9]*\.[0-9]+[fF] { 
                return new Yytoken(Yytoken.TOKEN_CTE_REAL_CORTO, yytext());
           }
[0-9]+\.[0-9]*[fF] { 
                return new Yytoken(Yytoken.TOKEN_CTE_REAL_CORTO, yytext());
           }
[0-9]*\.[0-9]+[dD]? { 
                return new Yytoken(Yytoken.TOKEN_CTE_REAL_LARGO, yytext());
           }
[0-9]+\.[0-9]*[dD]? { 
                return new Yytoken(Yytoken.TOKEN_CTE_REAL_LARGO, yytext());
           }
[0-9]*[eE][+-]?[0-9][0-9]*\.[0-9]+[fF] { 
                return new Yytoken(Yytoken.TOKEN_CTE_REAL_CORTO, yytext());
           }
[0-9]*\.[0-9]*[eE][+-]?[0-9][0-9]*[fF] { 
                return new Yytoken(Yytoken.TOKEN_CTE_REAL_CORTO, yytext());
           }
[0-9]*[eE][+-]?[0-9][0-9]*\.[0-9]+[dD]? { 
                return new Yytoken(Yytoken.TOKEN_CTE_REAL_LARGO, yytext());
           }
[0-9]*\.[0-9]*[eE][+-]?[0-9][0-9]*[dD]? { 
                return new Yytoken(Yytoken.TOKEN_CTE_REAL_LARGO, yytext());
           }
0[0-7]*[8-9]*[0-7]*[lL]? {
                return new Yytoken(Yytoken.TOKEN_ERROR, yytext());
            }
0+[xX][lL]? {
                return new Yytoken(Yytoken.TOKEN_ERROR, yytext());
            }
[0-9]+[xX][0-9aAbBcCdDfFg-zG-Z]+[lL]? {
                return new Yytoken(Yytoken.TOKEN_ERROR, yytext());
            }
[0-9]+[eE][+-]?[0-9]+\.[dD]?[Ff]? { 
                return new Yytoken(Yytoken.TOKEN_ERROR, yytext());
           }
[0-9]+[eE][+-]{2}[+-]*[0-9]+ { 
                return new Yytoken(Yytoken.TOKEN_ERROR, yytext());
           }
[0-9]+[+-][0-9]*[eE][+-][0-9]+[dD]?[fF]? { 
                return new Yytoken(Yytoken.TOKEN_ERROR, yytext());
           }
[Ee]        {
                return new Yytoken(Yytoken.TOKEN_ERROR, yytext());
          }
\.        {
                return new Yytoken(Yytoken.TOKEN_ERROR, yytext());
          }
\.[eE][+-]?[0-9]+        {
                return new Yytoken(Yytoken.TOKEN_ERROR, yytext());
          }
\n         {}
.          {} 