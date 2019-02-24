//------------------>1era area<--------------------------    

package backend.analizadores;
import java_cup.runtime.*;
import static backendd.analizadores.sym.*ASDFASDFASD;

%% //------------------>2da area<--------------------------    

%public
%class AnalizadorLexicoArchivo
%cup
%cupdebug
%unicode
%line
%column

LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [ \t\f]
DIGITO_NATURAL = [1-9]
DIGITO = [0-9]
Identifier = [:jletterdigit:][:jletterdigit:]*


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

	":"             { return symbol(DOS_PUNTOS);}
				
	"direccion"	{ return symbol(DIRECCION);}

	"direccionDePadre"	{ return symbol(DIRECCION_DE_PADRE);}

	"nombre"	{ return symbol(NOMBRE);}

	"permisos"	{ return symbol(PERMISOS);}

	"esFolder"	{ return symbol(ES_FOLDER);}

	"tamano"	{ return symbol(TAMANO);}
	
	"fechaDeCreacion"	{ return symbol(FECHA_DE_CREACION);}

	"true"			{ return symbol(TRUE,yytext());}

	"false" 		{ return symbol(FALSE,yytext());}

        "hora"                  { return symbol(HORA);}
        
        {DIGITO}{DIGITO}(":"){DIGITO}{DIGITO} {return symbol(FORMATO_HORA,yytext());}

        {DIGITO}{DIGITO}*["/"]{DIGITO}{DIGITO}*["/"]{DIGITO}{DIGITO}*	{ return symbol(FORMATO_FECHA,yytext());}	

	{DIGITO_NATURAL}({DIGITO})* { return symbol(ENTERO,new Integer(yytext()));}

	{Identifier}	{ return symbol(IDENTIFICADOR,yytext());}

	["/"]({Identifier}(["/"]{Identifier})*)*  { return symbol(FORMATO_DIRECCION,yytext());}

	["-"|"d"]["-"|"r"]["-"|"w"]["-"|"x"]	{return symbol(FORMATO_PERMISOS,yytext());} 

	
	{WhiteSpace} 	{/* ignoramos */}

}

[^]                     {System.out.println("Simbolo invalido");}