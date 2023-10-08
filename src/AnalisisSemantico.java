/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kobayashi
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    
    // AUN NO FUNCIONA, VUELVA CUANDO FUNCIONE
    public static boolean validateClave(String clave) {
        String regex = "[A-Za-z]+(\\^\\d+)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(clave);

        return matcher.matches();
    }

    public static String[] extractParenthesizedElements(String input) {
        List<String> elements = new ArrayList<>();

        Pattern pattern = Pattern.compile("\\((.*?)\\)");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String element = matcher.group(1);
            elements.add(element);
        }

        return elements.toArray(new String[0]);
    }

    public static void main(String[] args) {
        String input = "compas = 4 / 4 ;tempo = 208 ;var timbre = [ F1 . r ] ;var timbrePiano = [ E7 . P-n , G2 . P-b , F1 . P-n ] ;[ F . n , A . b , C . n ][ B . r ]timbre , [ A2 . b , B4 . b ][ E4 . n , E4 . n , F4 . n , G4 . n ]timbre[ F . P-n , A . P-b , C . P-n ][ B . P-r ]timbrePiano , [ A2 . P-b , B4 . P-b ][ E4 . P-n , E4 . P-n , F4 . P-n , G4 . P-n ]timbrePianoclave ( G ^ 2 )clave ( G ^ 2 )compas = 4 / 4 ;tempo = 208 ;var timbre = [ F1 . r ] ;var timbrePiano = [ E7 . P-n , G2 . P-b , F1 . P-n ] ;[ F . n , A . b , C . n ][ B . r ]timbre , [ A2 . b , B4 . b ][ E4 . n , E4 . n , F4 . n , G4 . n ]timbre[ F . P-n , A . P-b , C . P-n ][ B . P-r ]timbrePiano , [ A2 . P-b , B4 . P-b ][ E4 . P-n , E4 . P-n , F4 . P-n , G4 . P-n ]timbrePianoclave ( G ^ 2 )clave ( G ^ 2 )";

        String[] lines = input.split(";");

        List<String> elementosEntreParentesis = new ArrayList<>();

        for (String line : lines) {
            String[] tokens = line.split("\\s+|,");

            for (String token : tokens) {
                if (validateClave(token.trim())) {
                    System.out.println("Clave válida: " + token);
                } else {
                    System.out.println("Clave no válida: " + token);
                }

                // Buscar elementos entre paréntesis que cumplan con el patrón de clave
                String[] parenthesizedElements = extractParenthesizedElements(token.trim());
                for (String element : parenthesizedElements) {
                    if (validateClave(element)) {
                        elementosEntreParentesis.add(element);
                    }
                }
            }
        }

        System.out.println("Elementos entre paréntesis válidos: " + elementosEntreParentesis);
    }

}
