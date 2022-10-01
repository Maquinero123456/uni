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

    \n {

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

    [^\t \n] {
        JCom.lineaMultiple++;
    }

    \n {

    }

    . {
        
    }
 }

<DAST> {
    \*\/ {
        
        yybegin(YYINITIAL);
    }


    "*"?[^\t \n] {
        JCom.dobleAsterisco++;
    }
    
    

    \t {

    }

    \n {

    }

    . {
        
    }
 }

[^] {
    
 } 
