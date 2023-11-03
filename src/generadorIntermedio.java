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

    public static void main(String[] args) {
        //----------------------------------------------------------------------
        // Convertir tempo
        String entradaTempo = "tempo = 89";
        System.out.println(codigoIntermedioTempo(entradaTempo));
        //----------------------------------------------------------------------

        //----------------------------------------------------------------------
        // Notas a sonido
        String entradaNotas = "[ F . n , A . b , C . n ]";
        System.out.println(codigoIntermedioNotas(entradaNotas));
        //----------------------------------------------------------------------
//        // Divisor y dividendo en la regla de tres
//        double divisorReglaTres = 225.0;
//        double dividendoReglaTres = 110.0;
//        
//        // Obtener el valor original
//        int valorOriginal = obtenerValorOriginal(entradaTempo);
//        
//        // Aplicar la regla de tres
//        int valorTransformado = (int)codigoTempoOptimizado(valorOriginal, divisorReglaTres, dividendoReglaTres);
//        
//        // Llamar a la función y obtener la cadena de salida
//        String salidaTempo = aplicarReglaDeTres("tempo", valorOriginal, dividendoReglaTres, divisorReglaTres);
//
//        // Imprimir la cadena de salida
//        System.out.println(salidaTempo);
        //System.out.println("int tempo = "+valorTransformado+";");

//        System.out.println("float t1 = " + valorOriginal + " * " + divisorReglaTres +";");
//        System.out.println("float t2 = t1 / "+dividendoReglaTres+";");
//
//        // Resultado final
//        System.out.println("int tempo = round(t2);");
//        
//        // Generar la cadena de salida
//        String salidaTempo = generarCadenaDeSalida("tempo", valorTransformado);
//        System.out.println("Cadena de salida: " + salidaTempo);
        //----------------------------------------------------------------------
//        // Definir el texto de entrada
//        String entrada = "var timbre = [G2.b, F4.b];\n// Notas\n\t\\inicio;\n\t\trep(3) {\n"
//                + "		   	[E4.n, E4.n, F4.n, G4.n ], timbre;\n"
//                + "		}";
//
//        // Definir un mapa para almacenar las variables
//        Map<String, String> variables = new HashMap<>();
//        variables.put("timbre", "[G2.b, F4.b]");
//
//        // Realizar el reemplazo de variables
//        String salida = reemplazarVariables(entrada, variables);
//
//        // Imprimir el resultado
//        System.out.println(salida);
//
//        double numeroDecimal = 204.55;
//        int numeroEntero = (int) Math.round(numeroDecimal);
//
//        System.out.println("Número decimal: " + numeroDecimal);
//        System.out.println("Número entero: " + numeroEntero);
//        
//        String entrada2 = "var timbrePiano = [ F1 . P-r ] ";
//
//        Map<String, String> variables2 = new HashMap<>();
//
//        agregarVariablesAlMapa(entrada2, variables2);
//
//        // Imprimir el mapa de variables
//        for (Map.Entry<String, String> entry : variables.entrySet()) {
//            System.out.println("Variable: " + entry.getKey() + " = " + entry.getValue());
//        }
    }

    //--------------------------------------------------------------------------
    //CodigoTempo
    public static String codigoIntermedioTempo(String entradaTempo) {
        // Divisor y dividendo en la regla de tres
        double divisorReglaTres = 225.0;
        double dividendoReglaTres = 110.0;

        // Obtener el valor original
        int valorOriginal = obtenerValorOriginal(entradaTempo);

        // Aplicar la regla de tres
        int valorTransformado = (int) codigoTempoOptimizado(valorOriginal, divisorReglaTres, dividendoReglaTres);

        return aplicarReglaDeTres("tempo", valorOriginal, dividendoReglaTres, divisorReglaTres);
    }

    private static int obtenerValorOriginal(String entrada) {
        String[] partes = entrada.split("=");
        String valorStr = partes[1].trim();
        return Integer.parseInt(valorStr);
    }

    private static double codigoTempoOptimizado(int valorOriginal, double divisor, double dividendo) {
        return (valorOriginal * divisor) / dividendo;
    }

    private static String generarCadenaDeSalida(String variable, double valorTransformado) {
        return "int " + variable + " = " + (int) Math.round(valorTransformado) + ";";
    }

    private static String aplicarReglaDeTres(String variable, int valorOriginal, double dividendoReglaTres, double divisorReglaTres) {
        // Calcular t1
        double t1 = valorOriginal * divisorReglaTres;

        // Calcular t2
        double t2 = t1 / dividendoReglaTres;

        // Generar la cadena de salida
        String cadenaSalida = "float t1 = " + valorOriginal + " * " + divisorReglaTres + ";\n";
        cadenaSalida += "float t2 = t1 / " + dividendoReglaTres + ";\n";
        cadenaSalida += "int " + variable + " = round(t2);";

        return cadenaSalida;
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
        
        if(cadena.contains("[") || cadena.contains("]") || cadena.contains(" ")){
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
//
//    public static Map<String, Integer> crearDiccionario() {
//        
//
//        return diccionarioFiguraValor;
//    }
    //--------------------------------------------------------------------------

    //--------------------------------------------------------------------------
    // Agregar variables al mapa de variables
    public static void agregarVariablesAlMapa(String entrada, Map<String, String> variables) {
        // Crear una expresión regular para encontrar las variables
        Pattern pattern = Pattern.compile("var\\s+(\\w+)\\s*=\\s*\\[(.*?)\\]");

        Matcher matcher = pattern.matcher(entrada);

        while (matcher.find()) {
            String nombreVariable = matcher.group(1);
            String valorVariable = matcher.group(2);
            variables.put(nombreVariable, "[" + valorVariable + "]");
        }
    }
    //--------------------------------------------------------------------------

    public static String reemplazarVariables(String texto, Map<String, String> variables) {
        // Crear una expresión regular para encontrar variables entre llaves {}
        Pattern pattern = Pattern.compile("\\b(\\w+)\\b");
        Matcher matcher = pattern.matcher(texto);
        StringBuffer resultado = new StringBuffer();

        // Realizar el reemplazo de variables
        while (matcher.find()) {
            String variable = matcher.group(1);
            System.out.println("VARIABLE: " + variable);
            if (variables.containsKey(variable)) {
                String valor = variables.get(variable);
                System.out.println("VALOR: " + valor);
                matcher.appendReplacement(resultado, valor);
            }
        }
        matcher.appendTail(resultado);

        return resultado.toString();
    }

//    public static String triploTempo(String entrada) {
//        Map<String, Integer> tablaSimbolos = new HashMap<>();
//        String[] tokens = entrada.split(" ");
//
//        // Evaluamos la cantidad de tokens en el array
//        if (tokens.length == 3 && tokens[1].equals("=")) {
//            String variable = tokens[0];
//            String valorStr = tokens[2];
//
//            if (esNumero(valorStr)) {
//                int valor = Integer.parseInt(valorStr);
//                tablaSimbolos.put(variable, valor);
//                String codigoIntermedio = "=, " + variable + " , " + valor;
//                return "Triplo: " + codigoIntermedio;
//            } else {
//                return "Error: El valor no es un número válido.";
//            }
//        } else {
//            return "Error: Formato de entrada incorrecto.";
//        }
//    }
    public static boolean esNumero(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //--------------------------------------------------------------------------
    // Codigo de compas
    public static String generarCodigoIntermedio(String entrada) {
        String[] tokens = entrada.split("=");

        if (tokens.length == 2) {
            String variable = tokens[0].trim();
            String expresion = tokens[1].trim();
            String tempVariable = "t1"; // Variable temporal

            // Generar el primer triplo
            String triplo1 = tempVariable + " = " + expresion;

            // Generar el segundo triplo
            String triplo2 = variable + " = " + tempVariable;

            return triplo1 + "\n" + triplo2;
        } else {
            return "Error: Formato de entrada incorrecto.";
        }
    }
    //--------------------------------------------------------------------------
}
