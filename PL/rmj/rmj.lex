%%

%unicode
%standalone

%{   
    TablaSimbolos tb = new TablaSimbolos();
    StringBuffer string = new StringBuffer();
    StringBuffer string2 = new StringBuffer();
%}

%state STRING
%state COMILLAS
%state ECHO

%%
<YYINITIAL> {

    "echo" {
        string2.setLength(0);
        yybegin(ECHO);
    }

    [a-zA-Z_][a-zA-Z_0-9]* {
        string.append(yytext());
    }
    = {
        string2.setLength(0); 
        yybegin(STRING); 
    }

    =\" {
        string2.setLength(0); 
        yybegin(COMILLAS); 
    }

    
     
}

<STRING> {
    ([a-zA-Z0-9+=* ]|(\\;))+ {
        string2.append(yytext());
    }

    \$[a-zA-Z0-9_]+ {
        string2.append(tb.get(yytext()));
    }
    
    [\n\";]+ {
        tb.put(string.toString(), string2.toString());
        string.setLength(0);
        yybegin(YYINITIAL);
    }
 }

<COMILLAS> {
    ([a-zA-Z0-9;+=*\n ]|(\\\")|(\\\$))+ {
        string2.append(yytext());
    }

    \$[a-zA-Z_0-9]+ {
        string2.append(tb.get(yytext()));
    }
    
    \" {
        tb.put(string.toString(), string2.toString());
        string.setLength(0);
        yybegin(YYINITIAL);
    }
 }

<ECHO> {
    ([a-zA-Z0-9;+=*, ]|(\\\")|(\\\$))+ {
        string2.append(yytext());
    }

    \" {
        string2.append(yytext());
    }

    \$[a-zA-Z_0-9]+ {
        string2.append(tb.get(yytext()));
    }
    
    \n {
        System.out.println("echo"+string2.toString());
    }
 }

[^] {
} 
