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
//caracteresEspeciales = [!|@|#|$|%|&|_|+|-|~|`|"|'|<|>|?|:];
/*DigitoEscala = [1-8]*/
Digito = [0-9]*
/*Clave = {Letra}{Digito}*/ //G16 es un error sintáctico o semántico??
Nota = {Letra}{Digito}
Identificador = {Letras}({Letras}|{Numeros})*
%%

/* Comentarios o espacios en blanco */
{Comentario} { return textColor(yychar, yylength(), new Color(98, 114, 164)); }
{EspacioEnBlanco} { /* Ignorar */ }

/* Números */
/*{DigitoEscala}  { return token(yytext(), "TOKEN_DIGITO_ESCALA", yyline, yycolumn); }*/
{Digito}  { return textColor(yychar, yylength(), new Color(139, 233, 253)); }

/* Notas */
{Nota} { return textColor(yychar, yylength(), new Color(80, 250, 123)); }
/*{Clave} { return textColor(yychar, yylength(), new ColorRGB(255, 184, 108)); }*/
"*" { return textColor(yychar, yylength(), new Color(255, 85, 85)); }
"#" { return textColor(yychar, yylength(), new Color(255, 85, 85)); }
"-" { return textColor(yychar, yylength(), new Color(255, 85, 85)); }

/* Encabezado */
"clave" { return textColor(yychar, yylength(), new Color(241, 250, 140)); }
"compas" { return textColor(yychar, yylength(), new Color(241, 250, 140)); }
"tempo" { return textColor(yychar, yylength(), new Color(241, 250, 140)); }
"piano" { return textColor(yychar, yylength(), new Color(241, 250, 140)); }
"led" { return textColor(yychar, yylength(), new Color(241, 250, 140)); }
"rep" { return textColor(yychar, yylength(), new Color(241, 250, 140)); }
"var" { return textColor(yychar, yylength(), new Color(241, 250, 140)); }

/* Secciones */
\\"inicio" { return textColor(yychar, yylength(), new Color(189, 147, 249)); }
\\"fin" { return textColor(yychar, yylength(), new Color(189, 147, 249)); }

/* Identificador  */
"$"{Identificador} { return textColor(yychar, yylength(), new Color(80, 250, 123)); }

/* Figuras */
"r" { return textColor(yychar, yylength(), new Color(255, 121, 198)); }
"b" { return textColor(yychar, yylength(), new Color(255, 121, 198)); }
"n" { return textColor(yychar, yylength(), new Color(255, 121, 198)); }
"c" { return textColor(yychar, yylength(), new Color(255, 121, 198)); }
"s" { return textColor(yychar, yylength(), new Color(255, 121, 198)); }
"f" { return textColor(yychar, yylength(), new Color(255, 121, 198)); }
"sf" { return textColor(yychar, yylength(), new Color(255, 121, 198)); }

/* Silencio figura */
"sir" { return textColor(yychar, yylength(), new Color(255, 121, 198)); }
"sib" { return textColor(yychar, yylength(), new Color(255, 121, 198)); }
"sin" { return textColor(yychar, yylength(), new Color(255, 121, 198)); }
"sic" { return textColor(yychar, yylength(), new Color(255, 121, 198)); }
"sis" { return textColor(yychar, yylength(), new Color(255, 121, 198)); }
"sif" { return textColor(yychar, yylength(), new Color(255, 121, 198)); }
"sisf" { return textColor(yychar, yylength(), new Color(255, 121, 198)); }

/* Figuras piano */
"P-r" { return textColor(yychar, yylength(), new Color(255, 101, 198)); }
"P-b" { return textColor(yychar, yylength(), new Color(255, 101, 198)); }
"P-n" { return textColor(yychar, yylength(), new Color(255, 101, 198)); }
"P-c" { return textColor(yychar, yylength(), new Color(255, 101, 198)); }
"P-s" { return textColor(yychar, yylength(), new Color(255, 101, 198)); }
"P-f" { return textColor(yychar, yylength(), new Color(255, 101, 198)); }
"P-sf" { return textColor(yychar, yylength(), new Color(255, 101, 198)); }

/* Figuras LEDS */
"L-r" { return textColor(yychar, yylength(), new Color(255, 101, 198)); }
"L-b" { return textColor(yychar, yylength(), new Color(255, 101, 198)); }
"L-n" { return textColor(yychar, yylength(), new Color(255, 101, 198)); }
"L-c" { return textColor(yychar, yylength(), new Color(255, 101, 198)); }
"L-s" { return textColor(yychar, yylength(), new Color(255, 101, 198)); }
"L-f" { return textColor(yychar, yylength(), new Color(255, 101, 198)); }
"L-sf" { return textColor(yychar, yylength(), new Color(255, 101, 198)); }

/* Compases */
"/" { return textColor(yychar, yylength(), new Color(255, 0, 255)); }
/*"|"	 { return token(yytext(), "TOKEN_DIVISOR_COMPAS", yyline, yycolumn); }*/
"{" { return textColor(yychar, yylength(), new Color(255, 184, 108)); }
"}" { return textColor(yychar, yylength(), new Color(255, 184, 108)); }
"[" { return textColor(yychar, yylength(), new Color(189, 147, 249)); }
"]" { return textColor(yychar, yylength(), new Color(189, 147, 249)); }
"(" { return textColor(yychar, yylength(), new Color(255, 85, 85)); }
")" { return textColor(yychar, yylength(), new Color(255, 85, 85)); }
"=" { return textColor(yychar, yylength(), new Color(80, 250, 123)); }
"," { return textColor(yychar, yylength(), new Color(80, 250, 123)); }
"." { return textColor(yychar, yylength(), new Color(80, 250, 123)); }
"^" { return textColor(yychar, yylength(), new Color(80, 250, 123)); }
";" { return textColor(yychar, yylength(), new Color(255, 255, 0)); }

/* ERRORES */
. { return textColor(yychar, yylength(), new Color(255, 0, 0)); }
\\{AlfaErrores}+ { return textColor(yychar, yylength(), new Color(255, 0, 0)); }
{Identificador} { return textColor(yychar, yylength(), new Color(255, 0, 0)); }
