%%

%{
    StringBuffer string = new StringBuffer();
    float desc;
    int cant;
    float precio;
%}

%state TICKET

%%

<YYINITIAL> {
    \- {
        string.setLength(0);
        desc = 0;
        cant = 1;
        precio = 0;
        yybegin(TICKET);
    }

    \n {

    }

    . {

    }

    
}


<TICKET> {
    [a-zA-Z][a-zA-Z\s]+ {
        Ticket.numItemsDistintos++;
    }

    [0-9]+/% {
        desc = Float.parseFloat(yytext())/100;
    }

    [0-9]+\.[0-9]+/% {
        desc = Float.parseFloat(yytext())/100;
    }

    [1-9][0-9]*"uds" {
        cant = Integer.parseInt(yytext().substring(0, yytext().length()-3));
    }

    [1-9][0-9]* {
        cant = Integer.parseInt(yytext());
    }

    [0-9]+\.?[0-9]*\n {
        if(cant==0){
            cant=1;
        }
        Ticket.numItems+=cant;
        precio = Float.parseFloat(yytext());
        Ticket.totalCompra+=cant*(precio-precio*desc);
        yybegin(YYINITIAL);

    }

    [0-9]+\.?[0-9]*/("E"\n) {
        if(cant==0){
            cant=1;
        }
        Ticket.numItems+=cant;
        precio = Float.parseFloat(yytext());
        Ticket.totalCompra+=cant*(precio-precio*desc);
        yybegin(YYINITIAL);
    }

    \n {
        yybegin(YYINITIAL);
    }

    . {

    }
}

[^] {

}