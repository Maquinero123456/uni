%%

%{   
     TablaSimbolos tb = new TablaSimbolos();
     StringBuffer string = new StringBuffer();
     StringBuffer string2 = new StringBuffer();
%}

%state STRING

%%
<YYINITIAL> {
     [a-zA-Z_][a-zA-Z_0-9]*/= {
          string.append(yytext()); 
     }
     =\"? {
          string2.setLength(0); yybegin(STRING); 
     }
     echo {
          string.append(yytext()); string2.setLength(0); yybegin(STRING);
     }
     \n|;|" " {
          tb.put(string.toString(), string2.toString());
          string.setLength(0);
     }
     
}

<STRING> {
     [a-zA-Z, ]+ {
          string2.append(yytext());
     }

     \\\"\$[a-zA-Z_0-9]+\\\" {
          System.out.println(yytext());
          string2.append(yytext());
     }

     \$[a-zA-z_0-9]+ {
          string2.append(tb.get(yytext()));
     }
     \n|\"|; {
          tb.put(string.toString(), string2.toString());
          string.setLength(0);
          yybegin(YYINITIAL);
     }
     . {

     }
 }

. {
} 