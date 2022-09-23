

%%

%%
[a-zA-Z]*[aeiouAEIOU]{2}[a-zA-Z]*[aeiouAEIOU]  { 
                return new Yytoken(Yytoken.A, yytext());
           }
[a-zA-Z]+[aeiouAEIOU]{2}  { 
                return new Yytoken(Yytoken.A, yytext());
           }
[a-zA-Z]*[aeiouAEIOU] { 
                return new Yytoken(Yytoken.B, yytext());
           }
[a-zA-Z]*[aeiouAEIOU]{2}[a-zA-Z]*[^aeiouAEIOU,;\n.: ]  { 
                return new Yytoken(Yytoken.C, yytext());
           }
[a-zA-Z]*[^aeiouAEIOU,;\n.: ] { 
                return new Yytoken(Yytoken.D, yytext());
           }
\n         {}
,          {}
;          {}
\.         {}
.          {} 
