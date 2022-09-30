%%

%unicode
%standalone

%{   
    TablaSimbolos tb = new TablaSimbolos();
    StringBuffer string = new StringBuffer();
    StringBuffer string2 = new StringBuffer();
    StringBuffer string3 = new StringBuffer();
    String aux = "";
    String clase = "";
%}

%state VARIABLE
%state VALOR
%state COMILLAS
%state PRINT
%state ASIGNACION
%state COMILLAS2


%%
<YYINITIAL> {    
    "class "[a-zA-Z0-9_]+ {
        System.out.print(yytext()+"_rmj");
    }

    "String" {
        string.setLength(0);
        yybegin(VARIABLE);
    }

    = {
        string2.setLength(0);
        yybegin(VARIABLE);
    }

    [_a-zA-Z][_a-zA-Z0-9]* {
        string.setLength(0);
        string2.setLength(0);
        string.append(yytext());
        yybegin(ASIGNACION);
    }

    "System.out.print" {
        string.setLength(0);
        string.append("print");
        tb.put("print", "(\"");
        yybegin(PRINT);
    }

    "System.out.println" {
        string.setLength(0);
        string.append("print");
        tb.put("print", "ln(\"");
        yybegin(PRINT);
    }
    
    \t*"public static void main(String "[a-zA-Z_0-9]+"[]) {" {
        System.out.println(yytext());
    }

    [ \t]*\{\n {
        System.out.print(yytext());
    }

    [ \t]*\}\n {
        System.out.print(yytext());
    }

    \t+ {
        System.out.print(yytext());
    }

    . {

    }
}

<VARIABLE> {
    [_a-zA-Z][_a-zA-Z0-9]* {
        string.append(yytext());
    }

    = {
        tb.put(string.toString(), "");
        yybegin(VALOR);
    }
 }

<VALOR> {

    [_a-zA-Z][_a-zA-Z0-9]* {
        tb.put(string.toString(), tb.get(string.toString())+tb.get(yytext()));
    }

    \+ {

    }

    = {

    }


    "" {
        tb.put(string.toString(), "");
    }

    \" {
        string2.setLength(0);
        yybegin(COMILLAS);
    }
    
    \;\n? {
        string.setLength(0);
        yybegin(YYINITIAL);
    }
 }

<ASIGNACION> {

    [_a-zA-Z][_a-zA-Z0-9]* {
        string2.append(tb.get(yytext()));
    }

    \+ {

    }

    = {

    }


    "" {
        string2.append("");
    }

    \" {
        yybegin(COMILLAS2);
    }
    
    \;\n? {
        tb.put(string.toString(), string2.toString());
        string.setLength(0);
        yybegin(YYINITIAL);
    }
 }

 <COMILLAS2> {
    ([a-zA-Z0-9+=;* ]|\\\"|\\n|\\\\)+ {
        string2.append(yytext());
    }

    \" {
        yybegin(ASIGNACION);
    }
 }


<COMILLAS> {
    ([a-zA-Z0-9+=* ]|\\\"|\\n|\\\\)+ {
        string2.append(yytext());
    }

    \" {
        tb.put(string.toString(), tb.get(string.toString())+string2.toString());
        if(string.toString().equals("print")){
            yybegin(PRINT);
        }else{
            yybegin(VALOR);
        }
    }
 }

<PRINT> {
    [_a-zA-Z][_a-zA-Z0-9]* {
        tb.put("print", tb.get("print")+tb.get(yytext()));
    }

    \+ {

    }

    \" {
        string2.setLength(0);
        yybegin(COMILLAS);
    }


    ");"\n? {
        System.out.println("System.out.print"+tb.get("print")+"\");");
        yybegin(YYINITIAL);
    }
 }

[^] {
    
} 
