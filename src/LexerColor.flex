 import compilerTools.TextColor;
import java.awt.Color;

%%
%class LexerColor
%type TextColor
%char
%{
    private TextColor textColor(long start, int size, Color color){
        return new TextColor((int) start, size, color);
    }
%}
/* Variables básicas de comentarios y espacios */
TerminadorDeLinea = \r|\n|\r\n
EntradaDeCaracter = [^\r\n]
EspacioEnBlanco = {TerminadorDeLinea} | [ \t\f]
ComentarioTradicional = "/*" [^*] ~"*/" | "/*" "*"+ "/"
FinDeLineaComentario = "//" {EntradaDeCaracter}* {TerminadorDeLinea}?

/* Comentario */
Comentario = {ComentarioTradicional} | {FinDeLineaComentario} 

/* Identificador  */
Letra = [A|B|C|D|E|F|G]
Letras = [A-Za-zÑñ]
Numeros = [0-9]
AlfaErrores = [a-zA-Z]
/*DigitoEscala = [1-8]*/
Digito = [0-9]*
/*Clave = {Letra}{Digito}*/ //G16 es un error sintáctico o semántico??
Nota = {Letra}{Digito}
Identificador = {Letras}({Letras}|{Numeros})*
%%


/* Comentarios o espacios en blanco */
{Comentario} { return textColor(yychar, yylength(), new Color(146, 146, 146)); }
{EspacioEnBlanco} { /* Ignorar */ }

/* Números */
/*{DigitoEscala}  { return token(yytext(), "TOKEN_DIGITO_ESCALA", yyline, yycolumn); }*/
{Digito}  { return textColor(yychar, yylength(), new Color(189, 147, 249)); }

/* Notas */
{Nota} { return textColor(yychar, yylength(), new Color(165, 194, 97)); }
/*{Clave} { return textColor(yychar, yylength(), new Color(165, 194, 97)); }*/
"*" { return textColor(yychar, yylength(), new Color(255, 184, 108)); }
"#" { return textColor(yychar, yylength(), new Color(255, 184, 108)); }
"-" { return textColor(yychar, yylength(), new Color(255, 184, 108)); }

/* Encabezado */
"clave" { return textColor(yychar, yylength(), new Color(255, 255, 0)); }
"compas" { return textColor(yychar, yylength(), new Color(255, 255, 0)); }
"tempo" { return textColor(yychar, yylength(), new Color(255, 255, 0)); }
"piano" { return textColor(yychar, yylength(), new Color(255, 255, 0)); }
"led" { return textColor(yychar, yylength(), new Color(255, 255, 0)); }
"rep" { return textColor(yychar, yylength(), new Color(255, 255, 0)); }
"var" { return textColor(yychar, yylength(), new Color(125, 200, 130)); }
""{Identificador} { return textColor(yychar, yylength(), new Color(255, 255, 0)); }

/* Secciones */
\\"inicio" { return textColor(yychar, yylength(), new Color(0, 0, 255)); }
\\"fin" { return textColor(yychar, yylength(), new Color(0, 0, 255)); }

/* Figuras */
"r" { return textColor(yychar, yylength(), new Color(255, 0, 0)); }
"b" { return textColor(yychar, yylength(), new Color(255, 0, 0)); }
"n" { return textColor(yychar, yylength(), new Color(255, 0, 0)); }
"c" { return textColor(yychar, yylength(), new Color(255, 0, 0)); }
"s" { return textColor(yychar, yylength(), new Color(255, 0, 0)); }
"f" { return textColor(yychar, yylength(), new Color(255, 0, 0)); }
"sf" { return textColor(yychar, yylength(), new Color(255, 0, 0)); }

/* Silencio figura */
"sir" { return textColor(yychar, yylength(), new Color(255, 0, 0)); }
"sib" { return textColor(yychar, yylength(), new Color(255, 0, 0)); }
"sin" { return textColor(yychar, yylength(), new Color(255, 0, 0)); }
"sic" { return textColor(yychar, yylength(), new Color(255, 0, 0)); }
"sis" { return textColor(yychar, yylength(), new Color(255, 0, 0)); }
"sif" { return textColor(yychar, yylength(), new Color(255, 0, 0)); }
"sisf" { return textColor(yychar, yylength(), new Color(255, 0, 0)); }

/* Figuras piano */
"P-r" { return textColor(yychar, yylength(), new Color(0, 255, 0)); }
"P-b" { return textColor(yychar, yylength(), new Color(0, 255, 0)); }
"P-n" { return textColor(yychar, yylength(), new Color(0, 255, 0)); }
"P-c" { return textColor(yychar, yylength(), new Color(0, 255, 0)); }
"P-s" { return textColor(yychar, yylength(), new Color(0, 255, 0)); }
"P-f" { return textColor(yychar, yylength(), new Color(0, 255, 0)); }
"P-sf" { return textColor(yychar, yylength(), new Color(0, 255, 0)); }

/* Figuras LEDS */
"L-r" { return textColor(yychar, yylength(), new Color(255, 128, 0)); }
"L-b" { return textColor(yychar, yylength(), new Color(255, 128, 0)); }
"L-n" { return textColor(yychar, yylength(), new Color(255, 128, 0)); }
"L-c" { return textColor(yychar, yylength(), new Color(255, 128, 0)); }
"L-s" { return textColor(yychar, yylength(), new Color(255, 128, 0)); }
"L-f" { return textColor(yychar, yylength(), new Color(255, 128, 0)); }
"L-sf" { return textColor(yychar, yylength(), new Color(255, 128, 0)); }

/* Compases */
"/" { return textColor(yychar, yylength(), new Color(255, 0, 255)); }
/*"|"	 { return token(yytext(), "TOKEN_DIVISOR_COMPAS", yyline, yycolumn); }*/
"{" { return textColor(yychar, yylength(), new Color(0, 255, 255)); }
"}" { return textColor(yychar, yylength(), new Color(0, 255, 255)); }
"[" { return textColor(yychar, yylength(), new Color(0, 255, 255)); }
"]" { return textColor(yychar, yylength(), new Color(0, 255, 255)); }
"(" { return textColor(yychar, yylength(), new Color(0, 255, 255)); }
")" { return textColor(yychar, yylength(), new Color(0, 255, 255)); }
"=" { return textColor(yychar, yylength(), new Color(255, 255, 0)); }
"," { return textColor(yychar, yylength(), new Color(255, 255, 0)); }
"." { return textColor(yychar, yylength(), new Color(255, 255, 0)); }
"^" { return textColor(yychar, yylength(), new Color(255, 255, 0)); }
";" { return textColor(yychar, yylength(), new Color(255, 255, 0)); }

/* ERRORES */
. { return textColor(yychar, yylength(), new Color(255, 0, 0)); }
\\{AlfaErrores}+ { return textColor(yychar, yylength(), new Color(255, 0, 0)); }