

%%

%%
[0-9] { 
                return new Yytoken(Yytoken.TOKEN_CTE_ENTERO, yytext());
           }
[1-9][0-9]+ { 
                return new Yytoken(Yytoken.TOKEN_CTE_ENTERO, yytext());
           }
0[0-7]+ { 
                return new Yytoken(Yytoken.TOKEN_CTE_ENTERO, yytext());
           }
[0-9][lL] { 
                return new Yytoken(Yytoken.TOKEN_CTE_ENTERO_LARGO, yytext());
           }
[1-9][0-9]+[lL] { 
                return new Yytoken(Yytoken.TOKEN_CTE_ENTERO_LARGO, yytext());
           }
0[0-7]+[lL] { 
                return new Yytoken(Yytoken.TOKEN_CTE_ENTERO_LARGO, yytext());
           }
0x[0-9abcdefABCDEF]+ { 
                return new Yytoken(Yytoken.TOKEN_CTE_ENTERO, yytext());
           }
0x[0-9abcdefABCDEF]+[lL] { 
                return new Yytoken(Yytoken.TOKEN_CTE_ENTERO_LARGO, yytext());
           }
[0-9]+\.([eE][+-]?[0-9]+)?[dD]? { 
                return new Yytoken(Yytoken.TOKEN_CTE_REAL_LARGO, yytext());
           }
\.[0-9]+([eE][+-]?[0-9]+)?[dD]? { 
                return new Yytoken(Yytoken.TOKEN_CTE_REAL_LARGO, yytext());
           }
[0-9]+\.[0-9]+([eE][+-]?[0-9]+)?[dD]? { 
                return new Yytoken(Yytoken.TOKEN_CTE_REAL_LARGO, yytext());
           }
[0-9]+\.([eE][+-]?[0-9]+)?[fF] { 
                return new Yytoken(Yytoken.TOKEN_CTE_REAL_CORTO, yytext());
           }
\.[0-9]+([eE][+-]?[0-9]+)?[fF] { 
                return new Yytoken(Yytoken.TOKEN_CTE_REAL_CORTO, yytext());
           }
[0-9]+\.[0-9]+([eE][+-]?[0-9]+)?[fF] { 
                return new Yytoken(Yytoken.TOKEN_CTE_REAL_CORTO, yytext());
           }
[0-9]+[eE][+-]?[0-9]+[dD]? { 
                return new Yytoken(Yytoken.TOKEN_CTE_REAL_LARGO, yytext());
           }
[0-9]+[eE][+-]?[0-9]+[fF]? { 
                return new Yytoken(Yytoken.TOKEN_CTE_REAL_CORTO, yytext());
           }
[0-9]+[dD] { 
                return new Yytoken(Yytoken.TOKEN_CTE_REAL_LARGO, yytext());
           }
[0-9]+[fF] { 
                return new Yytoken(Yytoken.TOKEN_CTE_REAL_CORTO, yytext());
           }
0[0-7]*[89]+[0-7]* { 
                return new Yytoken(Yytoken.TOKEN_ERROR, yytext());
           }
0?[0-9]*x[0-9a-zA-Z]*[lL]? { 
                return new Yytoken(Yytoken.TOKEN_ERROR, yytext());
           }
[0-9]*[eE][+-]?[0-9]+\.[dD]? { 
                return new Yytoken(Yytoken.TOKEN_ERROR, yytext());
           }
[0-9]*([+-]*)?[0-9.]*[eE][0-9.]*([+-]*)?[0-9]*[dD]?[fF]? { 
                return new Yytoken(Yytoken.TOKEN_ERROR, yytext());
           }
\.+      {
               return new Yytoken(Yytoken.TOKEN_ERROR, yytext());
}
\.([+-]*)?[eE]([+-]*)?[0-9]+[dD]?[fF]?     {
               return new Yytoken(Yytoken.TOKEN_ERROR, yytext());
}
[eE]+      {
               return new Yytoken(Yytoken.TOKEN_ERROR, yytext());
}
\n         {}
.          {} 