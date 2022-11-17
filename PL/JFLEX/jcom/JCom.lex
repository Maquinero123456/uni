%%

%unicode
%standalone

%state LINE
%state MLINE
%state DAST

%%
<YYINITIAL> {

    \".+\" {

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
    [^\t\s\n\r] {
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


    [^\t\s\n\r] {
        JCom.dobleAsterisco++;
    }
 }

[^] {
    
 } 
