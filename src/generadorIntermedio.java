/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kobayashi
 */
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class generadorIntermedio {

    private static int saltos = 0;

    public static void setSaltos(int saltos) {
        generadorIntermedio.saltos = saltos;
    }

    
    //--------------------------------------------------------------------------
    //CodigoTempo
    public static String codigoIntermedioTempo(String entradaTempo) {
        // Divisor y dividendo en la regla de tres
        double divisorReglaTres = 225.0;
        double dividendoReglaTres = 110.0;

        // Obtener el valor original
        int valorOriginal = obtenerValorOriginal(entradaTempo);

        return aplicarReglaDeTres("tempo", valorOriginal, dividendoReglaTres, divisorReglaTres);
    }
    
    public static int codigoBuzzerTempo(String entradaTempo) {
        // Divisor y dividendo en la regla de tres
        double divisorReglaTres = 225.0;
        double dividendoReglaTres = 110.0;

        // Obtener el valor original
        int valorOriginal = obtenerValorOriginal(entradaTempo);

        return codigoTempoOptimizado(valorOriginal, divisorReglaTres,dividendoReglaTres);
    }

    private static int obtenerValorOriginal(String entrada) {
        String[] partes = entrada.split("=");
        String valorStr = partes[1].trim();
        return Integer.parseInt(valorStr);
    }

    private static String aplicarReglaDeTres(String variable, int valorOriginal, double dividendoReglaTres, double divisorReglaTres) {
        // Generar la cadena de salida
        String cadenaSalida = "(t1, *, " + valorOriginal + ", " + divisorReglaTres + ")\n";
        cadenaSalida += "(t2, /, t1, " + dividendoReglaTres + ")\n";
        cadenaSalida += "(" + variable + ",round, ,t2)";
        return cadenaSalida;
    }

    private static int codigoTempoOptimizado(int valorOriginal, double divisor, double dividendo) {
        return (int) ((valorOriginal * divisor) / dividendo);
    }
    //--------------------------------------------------------------------------

    //--------------------------------------------------------------------------
    // Notas
    public static String codigoIntermedioNotas(String cadena) {
        Map<String, Integer> diccionarioFiguraValor = new HashMap<>();
        diccionarioFiguraValor.put("r", 1);
        diccionarioFiguraValor.put("b", 2);
        diccionarioFiguraValor.put("n", 4);
        diccionarioFiguraValor.put("c", 8);
        diccionarioFiguraValor.put("s", 16);
        diccionarioFiguraValor.put("f", 32);
        diccionarioFiguraValor.put("sf", 64);
        diccionarioFiguraValor.put("sir", 1);
        diccionarioFiguraValor.put("sib", 2);
        diccionarioFiguraValor.put("sin", 4);
        diccionarioFiguraValor.put("sic", 8);
        diccionarioFiguraValor.put("sis", 16);
        diccionarioFiguraValor.put("sif", 32);
        diccionarioFiguraValor.put("sisf", 64);
        diccionarioFiguraValor.put("P-r", 1);
        diccionarioFiguraValor.put("P-b", 2);
        diccionarioFiguraValor.put("P-n", 4);
        diccionarioFiguraValor.put("P-c", 8);
        diccionarioFiguraValor.put("P-s", 16);
        diccionarioFiguraValor.put("P-f", 32);
        diccionarioFiguraValor.put("P-sf", 64);
        // Eliminar corchetes iniciales y finales
        cadena = cadena.substring(1, cadena.length() - 2);

        if (cadena.contains("[") || cadena.contains("]") || cadena.contains(" ")) {
            cadena = cadena.replaceAll("\\[|\\]", "");
        }
        String[] notas = cadena.split(",");
        StringBuilder resultado = new StringBuilder();

        for (String nota : notas) {
            String[] partes = nota.trim().split("\\.");
            if (partes.length == 2) {
                String notaNombre = partes[0].trim();
                String figura = partes[1].trim();
                int valor = diccionarioFiguraValor.get(figura);
                resultado.append("NOTE_" + notaNombre + ", " + valor + ", ");
            }
        }

        // Eliminar la coma final y espacio
        resultado.deleteCharAt(resultado.length() - 1);
        resultado.deleteCharAt(resultado.length() - 1);

        return resultado.toString();
    }
    //--------------------------------------------------------------------------

    //--------------------------------------------------------------------------
    // Metodos de tratamiento de variables
    public static void agregarVariablesAlMapa(String entrada, Map<String, String> variables) {
        // Crear una expresión regular para encontrar las variables con el formato dado
        Pattern pattern = Pattern.compile("var\\s+\\$(\\w+)\\s*=\\s*\\[(.*?)]");

        Matcher matcher = pattern.matcher(entrada);

        while (matcher.find()) {
            String nombreVariable = matcher.group(1);
            String valorVariable = matcher.group(2);
            variables.put(nombreVariable, "[" + valorVariable + "]");
        }
    }

    public static String reemplazarVariables(String texto, Map<String, String> variables) {
        // Crear una expresión regular para encontrar variables entre llaves {}
        Pattern pattern = Pattern.compile("\\b(\\w+)\\b");
        Matcher matcher = pattern.matcher(texto);
        StringBuffer resultado = new StringBuffer();

        // Realizar el reemplazo de variables
        while (matcher.find()) {
            String variable = matcher.group(1);
            if (variables.containsKey(variable)) {
                String valor = variables.get(variable);
                matcher.appendReplacement(resultado, valor);
            }
        }
        matcher.appendTail(resultado);

        return resultado.toString();
    }

    public static boolean cadenaPerteneceAlMapa(String cadena, Map<String, String> variables) {
        if (cadena.contains(" ")) {
            cadena = cadena.replace(" ", "");
        }
        if (cadena.contains("$")) {
            cadena = cadena.replace("$", "");
        }
        if (cadena.contains("\t")) {
            cadena = cadena.replace("\t", "");
        }
        return variables.containsKey(cadena);
    }
    //--------------------------------------------------------------------------

    //--------------------------------------------------------------------------
    // LIMPIAR DECLARACIONES DE VARIABLES
    public static String eliminarLineasVar(String[] lineas) {
        StringBuilder textoSinVar = new StringBuilder();

        for (String linea : lineas) {
            if (!linea.trim().startsWith("var")) {
                textoSinVar.append(linea).append("\n");
            }
        }

        return textoSinVar.toString();
    }
    //--------------------------------------------------------------------------

    //--------------------------------------------------------------------------
    // Ajustar clave
    public static String agregarCuatroAntesPunto(String texto) {
        // Usamos expresiones regulares para buscar y reemplazar los puntos precedidos por un número
        return texto.replaceAll("(\\d)([a-zA-Z])\\.", "$14.");
    }
    //--------------------------------------------------------------------------

    //--------------------------------------------------------------------------
    // Salos de funcion
    public static String renombrarFuncion(String texto) {
        Pattern pattern = Pattern.compile("fn\\s+#(\\w+)\\(\\)\\{");
        Matcher matcher = pattern.matcher(texto);

        StringBuffer resultado = new StringBuffer();

        while (matcher.find()) {
            String nombreFuncion = matcher.group(1);
            String reemplazo = "lbl Fn" + nombreFuncion + ":";
            matcher.appendReplacement(resultado, reemplazo);
        }

        matcher.appendTail(resultado);
        return resultado.toString();
    }

    public static String llamarFuncion(String texto) {
        Pattern pattern = Pattern.compile("(?<!fn\\s+)#(\\w+)\\(\\)\\{");
        Matcher matcher = pattern.matcher(texto);

        StringBuffer resultado = new StringBuffer();

        while (matcher.find()) {
            String nombreFuncion = matcher.group(1);
            String reemplazo = "lbl Fn" + nombreFuncion + ":(){";
            matcher.appendReplacement(resultado, reemplazo);
        }

        matcher.appendTail(resultado);
        return resultado.toString();
    }
    //--------------------------------------------------------------------------

    //--------------------------------------------------------------------------
    // IF's
    public static String remplazarIF(String texto) {
        // Reemplazo para 'clave(G^2)'
        String output1 = texto.replaceAll("clave\\(G\\^2\\)", "IF TRUE GOTO L" + saltos + "\nL" + saltos + ":\n ");
        saltos++;
        // Modificación en la expresión regular para evitar coincidencias con 'clave(G^2)'
        return output1.replaceAll("clave\\((?!G\\^2\\))([A-G])\\^([0-9])\\)", "IF FALSE GOTO L" + saltos + "\nL" + saltos + ":\n");
    }
    //--------------------------------------------------------------------------

    //--------------------------------------------------------------------------
    // Generar repeticiones
    // Validar valores maximos en las repeticiones
    public static String repsIf(String entrada) {
        Pattern patron = Pattern.compile("rep\\((\\d+)\\)");
        Matcher matcher = patron.matcher(entrada);
        String repIntermedio = "";

        while (matcher.find()) {
            saltos++;
            int valorRepeticion = Integer.parseInt(matcher.group(1));
            repIntermedio = "(A, = , ," + valorRepeticion + ")\n"
                    + "L"+saltos+" IF A > 0 GOTO L" + (saltos + 1) + " \nL" + (saltos + 1) + ":\n";
            saltos++;
            return repIntermedio;
        }
        return repIntermedio;
    }
    //--------------------------------------------------------------------------
}
