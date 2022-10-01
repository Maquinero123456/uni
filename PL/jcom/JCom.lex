%%

%unicode
%standalone

%state LINE
%state MLINE
%state DAST

%%
<YYINITIAL> {
    \"[_a-zA-Z0-9*/ ]+\" {

    }

    \/\/ {
        
        yybegin(LINE);
    }

    \/\* {
        
        yybegin(MLINE);
    }

    \/\*\* {
        
        yybegin(DAST);
    }
 }

<LINE> {
    [^\t \n] {
        JCom.linea++;
    }

    \n {
        
        yybegin(YYINITIAL);
    }
 }

<MLINE> {
    \*\/ {
        
        yybegin(YYINITIAL);
    }

    [^\t\s\n\r] {
        JCom.lineaMultiple++;
    }
 }

<DAST> {
    \*\/ {
        
        yybegin(YYINITIAL);
    }


    "*"?[^\t\s\n\r] {
        JCom.dobleAsterisco++;
    }
 }

[^] {
    
 } 
