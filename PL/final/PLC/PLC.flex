
import java_cup.runtime.*;
%%
   
%cup 

%%   
   
    "=" {return new Symbol(sym.ASIG);}
    ";" {return new Symbol(sym.PYC);}
    "print" {return new Symbol(sym.PRINT);}
    "for" {return new Symbol(sym.FOR);}
    "if" {return new Symbol(sym.IF);}
    "else" {return new Symbol(sym.ELSE);}
    "while" {return new Symbol(sym.WHILE);}
    "do" {return new Symbol(sym.DO);}
    "{"   {return new Symbol(sym.AL);}
    "}"   {return new Symbol(sym.CL);}
    "("   {return new Symbol(sym.AP);}
    ")"   {return new Symbol(sym.CP);}
    "+"    {return new Symbol(sym.MAS);}
    "-"     {return new Symbol(sym.MENOS);}
    "*"     {return new Symbol(sym.POR);}
    "/"     {return new Symbol(sym.DIV);}
    "!"     {return new Symbol(sym.NEGADO);}
    "<"     {return new Symbol(sym.MENOR);}
    ">"     {return new Symbol(sym.MAYOR);}
    ">="    {return new Symbol(sym.MAYORIGUAL);}
    "<="    {return new Symbol(sym.MENORIGUAL);}
    "||"    {return new Symbol(sym.OR);}
    "&&"    {return new Symbol(sym.Y);}
    "=="    {return new Symbol(sym.IGUAL);}
    "!="    {return new Symbol(sym.DISTINTO);}
    [a-zA-Z][a-zA-Z0-9]* {return new Symbol(sym.STRING, yytext());}
    0|[1-9][0-9]*  {return new Symbol(sym.NUM, yytext());}
    \r|\n              {  }   
    \ |\t\f            {  }  
    [^]                { throw new Error("Illegal character <"+yytext()+">"); }
    
