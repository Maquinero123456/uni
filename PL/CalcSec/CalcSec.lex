%%

%{
    int result = 0;
    int parentesis = 0;
%}

%state PARENTESIS, MULT, DIV
%int
%%

<YYINITIAL> {
    [+-]?[0-9]+ {
        result+=Integer.parseInt(yytext());
    }
    
    \* {
        yybegin(MULT);
    }

    \/ {
        yybegin(DIV);
    }

    \( {
        yybegin(PARENTESIS)
    }

    ; {
        System.out.println(result+";");
        result=0;
    }
}


<MULT> {
    -?[0-9]+ {
        result*=Integer.parseInt(yytext());
        yybegin(YYINITIAL);
    }
}

<DIV> {
    -?[0-9]+ {
        result/=Integer.parseInt(yytext());
        yybegin(YYINITIAL);
    }
}

<PARENTESIS> {
    
}

[^] {

}