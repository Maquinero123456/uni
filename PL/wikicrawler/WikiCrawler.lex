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
    "https://commons.wikimedia.org/wiki/File:".+\.[fvpsnjeigFVPSNJEIG]+ {
        WikiCrawler.nImg++;
        WikiCrawler.enlacesImagenes.add(yytext());
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
