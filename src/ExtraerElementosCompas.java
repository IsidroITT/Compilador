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

public class ExtraerElementosCompas {
    private static String EntradaCompas;
    
    // Constructor
    public ExtraerElementosCompas(String Entrada) {
        this.EntradaCompas = Entrada;
    }
    
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
    
    public static double calculateCompas(String input) {
        Pattern pattern = Pattern.compile("compas\\s*=\\s*(\\d+\\s*[/]\\s*\\d+)");
        Matcher matcher = pattern.matcher(input);
        
        if (matcher.find()) {
            String compasExpression = matcher.group(1);
            String[] parts = compasExpression.split("/");
            if (parts.length == 2) {
                double numerator = Double.parseDouble(parts[0].trim());
                double denominator = Double.parseDouble(parts[1].trim());
                return numerator / denominator;
            }
        }
        
        return 0.0;
    }

     
    public static void main(String[] args) {
        EntradaCompas = "compas = 3 / 4 ; tempo = 9 7 ; timbre = [ E7 . n , G2 . b , F1 . r ] ; timbrePiano = [ E7 . P-n , G2 . P-b , F1 . P-r ] ; [ F . n , A . b , C . n ] [ B . r ] timbre , [ A2 . b , B4 . b ] [ E4 . n , E4 . n , F4 . n , G4 . n ] timbre [ F . P-n , A . P-b , C . P-n ] [ B . P-r ] timbrePiano , [ A2 . P-b , B4 . P-b ] [ E4 . P-n , E4 . P-n , F4 . P-n , G4 . P-n ] timbrePiano";
        
        Map<String, Double> valueMapping = crearDiccionarioFiguraValor();
        
        String[] elementsInBrackets = extraerElementosCorchetesCompas(EntradaCompas);
        
        double compasValue = calculateCompas(EntradaCompas);
        System.out.println("Valor de compas = " + compasValue);
        
        for (String element : elementsInBrackets) {
            double sum = sumaValoresCompas(element, valueMapping);
            //System.out.println(element + " = " + sum);
            
            if (compasValue > sum) {
                System.out.println("Compas es menor a " + element + " = " + sum);
            }
            else if(compasValue < sum){
                System.out.println("Compas es mayor a " + element + " = " + sum); 
            }
            else{
                System.out.println("Compas es correcto a " + element + " = " + sum);
            }
        }
    }
    
   
}
