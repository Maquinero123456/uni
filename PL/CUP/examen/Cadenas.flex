
import java_cup.runtime.*;
%%

/*  Declaraciones */   
   
%cup 

%%   

/* Expresiones y reglas */
   
    "+"                { return new Symbol(sym.MAS); }
    "-"								 { return new Symbol(sym.MENOS); }
    "="                { return new Symbol(sym.IGUAL); }
    "("                { return new Symbol(sym.AP); }
    ")"                { return new Symbol(sym.CP); }
    ";"                { return new Symbol(sym.PYC); }
    "."                { return new Symbol(sym.PUNTO); }
    ","                { return new Symbol(sym.COMA); }
    "print"            { return new Symbol(sym.PRINT); }
    "substr"           { return new Symbol(sym.SUBSTR); }
    "size"             { return new Symbol(sym.SIZE); }
    \"[^\"\r\n]*\"     { return new Symbol(sym.CADENA, new String(yytext().substring(1,yytext().length()-1))); }
    [A-Za-z][A-Za-z0-9_]* { return new Symbol(sym.ID, new String( yytext() )); }    
    0|[1-9][0-9]*      { return new Symbol(sym.NUMERO, new Integer(yytext()) ); }
    \r|\n              {  }   
    \ |\t\f            {  }  
    [^]                { throw new Error("Illegal character <"+yytext()+">"); }
    
