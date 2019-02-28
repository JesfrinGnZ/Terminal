//------------------>1era area<--------------------------    

package backend.analizadoresTerminal;
import java_cup.runtime.*;
import static backendd.analizadoresTerminal.sym.*;

%% //------------------>2da area<--------------------------    

%public
%class AnalizadorLexicoTerminal
%cup
%cupdebug
%unicode
%line
%column

LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [ \t\f]
SignosEspeciales =["."|"@"|"#"|"+"|"-"|"_"]
Identifier = ({SignosEspeciales}+|[:jletterdigit:]+)+

%{
  StringBuilder string = new StringBuilder();
  
  private Symbol symbol(int type) {
    return new Symbol(type, yyline+1, yycolumn+1);
  }

  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline+1, yycolumn+1, value);
  }


  private void error(String message) {
    System.out.println("Error en linea line "+(yyline+1)+", columna "+(yycolumn+1)+" : "+message);
  }
%}

%% //------------------>3er area<--------------------------    
<YYINITIAL> {

	"pwd"             { return symbol(PWD);}
				
	"ls"	{ return symbol(LS);}

	"cd"	{ return symbol(CD);}

	"touch"	{ return symbol(TOUCH);}

	"mkdir"	{ return symbol(MKDIR);}

	"mv"	{ return symbol(MV);}

	"cp"	{ return symbol(CP);}

	"rm"	{ return symbol(RM);}
	
	"rmdir"	{ return symbol(RMDIR);}

	"chmod"			{ return symbol(CHMOD);}

        "&&"    { return symbol(CONCATENACION);}

        "-l"    {return symbol(MOSTRAR_ARCHIVOS_L);}

        "-a"    {return symbol(MOSTRAR_ARCHIVOS_A);}

        "-la"   {return symbol(MOSTRAR_ARCHIVOS_LA);}

        "-al"   {return symbol(MOSTRAR_ARCHIVOS_AL);}

        "exit" {return symbol(EXIT);}

	{Identifier}	{ return symbol(IDENTIFICADOR,yytext());}

	(["/"]+{Identifier}["/"]*)+ { return symbol(FORMATO_DIRECCION_ABSOLUTA,yytext());}

        {Identifier}(["/"]+|(["/"]+{Identifier}["/"]*)+)  { return symbol(FORMATO_DIRECCION__RELATIVA,yytext());}
	    
	{WhiteSpace} 	{/* ignoramos */}

}

[^]                     {System.out.println("Simbolo invalido");}
