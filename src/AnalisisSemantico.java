/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kobayashi
 */
import compilerTools.ErrorLSSL;
import compilerTools.Token;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalisisSemantico {

    /*
    * Metodo para extraer los elementos del compas para evaluarlos por separado
    * @param Entrada
     */
    public static String[] extraerElementosCorchetesCompas(String Entrada) {
        List<String> elementosFiguraNota = new ArrayList<>();

        Pattern patron = Pattern.compile("\\[([^\\]]+)\\]");
        Matcher comparador = patron.matcher(Entrada);

        while (comparador.find()) {
            String elementosEnCorchetesFN = comparador.group(1);
            elementosFiguraNota.add(elementosEnCorchetesFN);
        }

        return elementosFiguraNota.toArray(new String[0]);
    }

    /*
      Metodo que genera el diccionario con las figuras y sus valores en datos double
     */
    public static Map<String, Double> crearDiccionarioFiguraValor() {
        Map<String, Double> diccionarioFiguraValor = new HashMap<>();
        diccionarioFiguraValor.put("r", 1.0);
        diccionarioFiguraValor.put("b", 0.5);
        diccionarioFiguraValor.put("n", 0.25);
        diccionarioFiguraValor.put("c", 0.125);
        diccionarioFiguraValor.put("s", 0.0625);
        diccionarioFiguraValor.put("f", 0.03125);
        diccionarioFiguraValor.put("sf", 0.015625);
        diccionarioFiguraValor.put("sir", 1.0);
        diccionarioFiguraValor.put("sib", 0.5);
        diccionarioFiguraValor.put("sin", 0.25);
        diccionarioFiguraValor.put("sic", 0.125);
        diccionarioFiguraValor.put("sis", 0.0625);
        diccionarioFiguraValor.put("sif", 0.03125);
        diccionarioFiguraValor.put("sisf", 0.015625);
        diccionarioFiguraValor.put("P-r", 1.0);
        diccionarioFiguraValor.put("P-b", 0.5);
        diccionarioFiguraValor.put("P-n", 0.25);
        diccionarioFiguraValor.put("P-c", 0.125);
        diccionarioFiguraValor.put("P-s", 0.0625);
        diccionarioFiguraValor.put("P-f", 0.03125);
        diccionarioFiguraValor.put("P-sf", 0.015625);

        return diccionarioFiguraValor;
    }

    public static double sumaValoresCompas(String elementoFN, Map<String, Double> valorDiccionarioFN) {
        String[] FiguraNota = elementoFN.split(",");
        double sumaCompas = 0.0;

        for (String FN : FiguraNota) {
            String[] notaValor = FN.trim().split("\\.");
            if (notaValor.length == 2) {
                String caracterNota = notaValor[1].trim();
                Double valorNota = valorDiccionarioFN.get(caracterNota);
                if (valorNota != null) {
                    sumaCompas += valorNota;
                }
            }
        }

        return sumaCompas;
    }

    public static double calcularCompas(String compasEvaluar) {
        Pattern patron = Pattern.compile("compas\\s*=\\s*(\\d+\\s*[/]\\s*\\d+)");
        Matcher comparador = patron.matcher(compasEvaluar);

        if (comparador.find()) {
            String compasExpresion = comparador.group(1);
            String[] elementosCompas = compasExpresion.split("/");
            if (elementosCompas.length == 2) {
                double numerador = Double.parseDouble(elementosCompas[0].trim());
                double denominador = Double.parseDouble(elementosCompas[1].trim());
                return numerador / denominador;
            }
        }

        return 0.0;
    }

    public static boolean validarTamanoCompas(String compasEvaluar) {
        Pattern patron = Pattern.compile("compas\\s*=\\s*(\\d+\\s*[/]\\s*\\d+)");
        Matcher comparador = patron.matcher(compasEvaluar);

        if (comparador.find()) {
            String compasExpresion = comparador.group(1);
            String[] elementosCompas = compasExpresion.split("/");

            if (elementosCompas.length == 2) {
                int numerador = Integer.parseInt(elementosCompas[0].trim());
                int denominador = Integer.parseInt(elementosCompas[1].trim());
                return numerador > 10 || denominador > 10;
            }
        }
        return false;
    }

    public static boolean validarTempo(String input) {
        Pattern patron = Pattern.compile("tempo\\s*=\\s*(\\d+)");
        Matcher comparador = patron.matcher(input);

        if (comparador.find()) {
            int valorTempo = Integer.parseInt(comparador.group(1).trim());

            return valorTempo < 40 || valorTempo > 208;
        }

        return false;
    }

    public static String[] extractParenthesizedElements(String input) {
        List<String> elements = new ArrayList<>();

        Pattern pattern = Pattern.compile("\\((.*?)\\)");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String element = matcher.group(1);
            elements.add(element);
        }

        return elements.toArray(new String[elements.size()]);
    }

    // Metodos de pertenencia de compas y tempo
    public static void verificarTexto(String texto, ArrayList<ErrorLSSL> errorsSemantics) {
        String[] palabrasClave = {"compas =", "tempo ="};
        Stack<String> pila = new Stack<>();
        int lineasAnalizadas = 0;

        String[] lineas = texto.split("\\n");

        for (String linea : lineas) {
            // Ignorar líneas vacías y comentarios
            if (!linea.trim().isEmpty() && !linea.trim().startsWith("//")) {
                lineasAnalizadas++;
            }

            for (String palabraClave : palabrasClave) {
                if (linea.startsWith("//")) {
                    continue;
                }
                if (linea.contains(palabraClave)) {
                    pila.push(palabraClave);
                }
            }

            if (lineasAnalizadas >= 5) {
                break;
            }
        }

        if (!pila.contains("compas =") && !pila.contains("tempo =")) {
            errorsSemantics.add(new ErrorLSSL(105, "Error semantico {}: Faltan ambas sentencias (tempo y compas) en las primeras 5 líneas. ", new Token("{", "}", 1, 1)));
        } else {
            if (!pila.contains("compas =")) {
                errorsSemantics.add(new ErrorLSSL(106, "Error semantico {}: Falta 'compas' en las primeras 5 líneas. ", new Token("{", "}", 1, 1)));
            }
            if (!pila.contains("tempo =")) {
                errorsSemantics.add(new ErrorLSSL(107, "Error semantico {}: Falta 'tempo' en las primeras 5 líneas. ", new Token("{", "}", 1, 1)));
            }
        }
    }

    // Variables sin declarar
    public static Stack<ElementoPila> guardarElementosConDolarEnPila(String cadena) {
        Stack<ElementoPila> pila = new Stack<>();
        String[] lineas = cadena.split("\n");

        for (int i = 0; i < lineas.length; i++) {
            String linea = lineas[i].replaceAll(";", "");
            String[] palabras = linea.split("\\s+");

            for (String palabra : palabras) {
                if (palabra.startsWith("$") || (palabra.startsWith("var") && palabra.length() > 4 && palabra.charAt(3) == '$')) {
                    pila.push(new ElementoPila(palabra, i + 1));
                }
            }
        }

        return pila;
    }

    public static void contarRepetidos(Stack<ElementoPila> pila, ArrayList<ErrorLSSL> errorsSemantics, String cadena) {
        Map<String, Integer> conteo = new HashMap<>();

        while (!pila.isEmpty()) {
            ElementoPila elemento = pila.pop();
            conteo.put(elemento.getCadena(), conteo.getOrDefault(elemento.getCadena(), 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : conteo.entrySet()) {
            if (entry.getValue() == 1) {
                String cadenaError = entry.getKey();
                int numeroLinea = encontrarNumeroLinea(cadena, cadenaError);
                if (numeroLinea != -1) {
                    errorsSemantics.add(new ErrorLSSL(108, "Error semantico {}: La sentencia '" + cadenaError + "' es una variable que no está declarada en la línea " + numeroLinea, new Token("{", "}", 1, 1)));
                }
            }
        }
    }

    private static int encontrarNumeroLinea(String cadena, String cadenaError) {
        String[] lineas = cadena.split("\n");
        for (int i = 0; i < lineas.length; i++) {
            if (lineas[i].contains(cadenaError)) {
                return i + 1;
            }
        }
        return -1;
    }

    // Validar clave
    public static String[] buscarClavesEnTexto(String texto) {
        Pattern patron = Pattern.compile("(\\bclave\\([^0-9].*?\\))\\s*\\{([^\\}]*)\\}");
        Matcher matcher = patron.matcher(texto);

        List<ClaveEncontrada> coincidencias = new ArrayList<>();

        int numeroLinea = 1;
        while (matcher.find()) {
            String posibleClave = matcher.group(0);
            coincidencias.add(new ClaveEncontrada(posibleClave, obtenerNumeroLinea(texto, matcher.start())));
        }

        String[] claves = new String[coincidencias.size()];
        for (int i = 0; i < coincidencias.size(); i++) {
            claves[i] = coincidencias.get(i).getClave();
        }

        return claves;
    }

    public static void validarClaves(String[] claves, ArrayList<ErrorLSSL> errorsSemantics, String texto) {
        for (String clave : claves) {
            if (!validarClave(clave)) {
                int numeroLinea = obtenerNumeroLinea(texto, texto.indexOf(clave));
                errorsSemantics.add(new ErrorLSSL(109, "Error semantico {}: La clave contiene numero de octava en las notas \n'" + clave + "'\n no es válida en la línea " + numeroLinea, new Token("{", "}", 1, 1)));
            }
        }
    }

    private static boolean validarClave(String clave) {
        Stack<Character> pila = new Stack<>();
        boolean dentroCorchetes = false;

        for (char c : clave.toCharArray()) {
            if (c == '[') {
                dentroCorchetes = true;
            } else if (c == ']') {
                dentroCorchetes = false;
                pila.clear(); // Reiniciar la pila al salir de los corchetes
            } else if (dentroCorchetes && Character.isDigit(c)) {
                if (!pila.isEmpty() && pila.peek() == '.') {
                    return false; // Si hay un número antes del punto, la clave no es válida
                }
                pila.push(c);
            } else if (c == '.') {
                if (!pila.isEmpty() && Character.isDigit(pila.peek())) {
                    return false; // Si hay un número antes del punto, la clave no es válida
                }
                pila.push(c);
            }
        }

        return true;
    }

    private static int obtenerNumeroLinea(String texto, int indice) {
        String[] lineas = texto.split("\n");
        int contador = 0;
        for (String linea : lineas) {
            contador += linea.length() + 1; // Sumar la longitud de la línea y el carácter de nueva línea
            if (contador > indice) {
                return contador - linea.length(); // Regresar el número de línea
            }
        }
        return -1;
    }

    // No combinar notas de música con notas de piano
    public static String[] obtenerElementosPuntoComa(String[] elementosCorchetes) {
        List<String> elementosPuntoComa = new ArrayList<>();

        for (String elementos : elementosCorchetes) {
            String[] elementosSeparados = elementos.split(",\\s*");

            for (String elemento : elementosSeparados) {
                String[] partes = elemento.split("\\.");
                if (partes.length > 1) {
                    elementosPuntoComa.add(partes[1]);
                }
            }
        }

        return elementosPuntoComa.toArray(new String[0]);
    }

    public static boolean verificarElementosMixtos(String[] elementos) {
        boolean empiezanConP = false;
        boolean noEmpiezanConP = false;

        for (String elemento : elementos) {
            if (elemento.startsWith("P")) {
                empiezanConP = true;
            } else {
                noEmpiezanConP = true;
            }

            // Si se encuentra una mezcla de elementos, retornar true (error)
            if (empiezanConP && noEmpiezanConP) {
                return true;
            }
        }

        // Si no se encontró mezcla, retornar false (sin error)
        return false;
    }

    // Validar valores maximos en las repeticiones
    public static void validarRepeticiones(String entrada, ArrayList<ErrorLSSL> errorsSemantics) {
        Pattern patron = Pattern.compile("rep\\((\\d+)\\)");
        Matcher matcher = patron.matcher(entrada);

        int numeroLinea = 1;
        while (matcher.find()) {
            int valorRepeticion = Integer.parseInt(matcher.group(1));
            if (valorRepeticion < 2 || valorRepeticion > 35) {
                errorsSemantics.add(new ErrorLSSL(110, "Error semantico {}: Valor de repetición inválido (" + valorRepeticion + ") el rango de repetición es de minimo 2 y maximo 35, en la línea " + obtenerNumeroLineaREP(entrada, matcher.start(),valorRepeticion ), new Token("{", "}", 1, 1)));
                return;
            }
        }
    }

    private static int obtenerNumeroLineaREP(String entrada, int indice, int fallo) {
        String[] lineas = entrada.split("\n");
        int contador = 0;
        for (String linea : lineas) {
            contador ++; // Sumar la longitud de la línea y el carácter de nueva línea
            if (linea.contains("rep("+fallo+")")) {
                return contador; // Regresar el número de línea
            }
        }
        return -1;
    }

    public static void main(String[] args) {
    }
}

class ElementoPila {

    private String cadena;
    private int numeroLinea;

    public ElementoPila(String cadena, int numeroLinea) {
        this.cadena = cadena;
        this.numeroLinea = numeroLinea;
    }

    public String getCadena() {
        return cadena;
    }

    public int getNumeroLinea() {
        return numeroLinea;
    }
}

class ClaveEncontrada {

    private final String clave;
    private final int numeroLinea;

    public ClaveEncontrada(String clave, int numeroLinea) {
        this.clave = clave;
        this.numeroLinea = numeroLinea;
    }

    public String getClave() {
        return clave;
    }

    public int getNumeroLinea() {
        return numeroLinea;
    }
}
