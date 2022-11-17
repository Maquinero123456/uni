%%
%unicode
%standalone

%{
    int tiempoAnterior = -1;
%}

%state TIEMPO, PALABRAS
%%


<YYINITIAL>{
    [1-9][0-9]*\n {
        srt.num_subtitulos++;
    }

    [0-9]+:[0-9]+: {
        yybegin(TIEMPO);
    }
}


<TIEMPO> {
    [0-9]+/, {
        if(tiempoAnterior==-1){
            tiempoAnterior=Integer.parseInt(yytext());
        }else{
            srt.tiempo_total+=Integer.parseInt(yytext()) - tiempoAnterior;
            tiempoAnterior=-1;
            yybegin(PALABRAS);
        }
    }

    "-->" {
        yybegin(YYINITIAL);
    }
}

<PALABRAS> {
    ,[0-9]+[\s]*\n {

    }

    [^\s\t\n\r]+ {
        srt.num_palabras++;
    }

    \n {
        srt.num_lineas++;
    }

    \n+ {
        srt.num_lineas++;
    }

    \n+[0-9]+\n {
        srt.num_lineas++;
        srt.num_subtitulos++;
        yybegin(YYINITIAL);
    }
    
}


[^] {

}