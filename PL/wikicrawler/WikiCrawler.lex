%%

%unicode
%standalone

%{   
    StringBuffer string = new StringBuffer();
    StringBuffer string2 = new StringBuffer();
%}

%state STRING
%state COMILLAS
%state ECHO

%%
<YYINITIAL> {
    a {

    }
}

<STRING> {
    a {

    }

 }

<COMILLAS> {
    a {

    }

 }

<ECHO> {
    a {
        
    }

 }

[^] {

} 
