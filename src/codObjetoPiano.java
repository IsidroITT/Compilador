
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author kobayashi
 */
public class codObjetoPiano {

    // Variables de la clase
    private static String codigoPiano = "";
    private static Map<String, String> diccionarioNotaPCA = new HashMap<>();
    private static Map<String, Integer> diccionarioFiguraDelay = new HashMap<>();
    private static Map<String, String> diccionarioNotasClave = new HashMap<>();

    /**
     * Constructor que inicializa los diccionarios
     */
    public codObjetoPiano() {
        generarDiccionarioFiguraDelay();
        generarDiccionarioNotasPCA();
        generarDiccionarioNotasClave();
    }

    /**
     * @param funcionesxd Son las funciones que se agregaran a la generacionde
     * cofigo
     * @param nombreArchivo Es el nombre del archivo que sera generado
     *
     * Funcion que permite generar el codigo objeto para nuestro piano
     */
    public static void generateCodigoPiano(String funcionesxd, String nombreArchivo, String funcionesLlamar, int tempoPiano) {
        String ruta = "/home/kobayashi/Desktop/Semestre7/4-Automatas2/todoxd/Compilador-main/src/objetoPiano/" + nombreArchivo + "Piano.ino";
        codigoPiano = "#include <Wire.h>\n"
                + "#include <Adafruit_PWMServoDriver.h>\n"
                + "\n"
                + "Adafruit_PWMServoDriver pca1 = Adafruit_PWMServoDriver(0x40);\n"
                + "Adafruit_PWMServoDriver pca2 = Adafruit_PWMServoDriver(0x41);\n"
                + "\n"
                + "//Definir el puerto en el que están conectados los servos correspondientes a las notas\n"
                + "#define G3 0\n"
                + "#define A3 1\n"
                + "#define B3 2\n"
                + "#define C4 3\n"
                + "#define D4 4\n"
                + "#define E4 5\n"
                + "#define F4 6\n"
                + "#define G4 7\n"
                + "#define A4 8\n"
                + "#define B4 9\n"
                + "#define C5 10\n"
                + "#define D5 11\n"
                + "\n"
                + "#define E5 12\n"
                + "#define F5 13\n"
                + "#define G5 14\n"
                + "#define A5 15\n"
                + "#define C6 16\n"
                //                + "//Notas pertenecientes al pca1\n"
                //                + "#define C3 15\n"
                //                + "#define E3 14\n"
                //                + "#define G3 13\n"
                //                + "#define B3 12\n"
                //                + "#define D4 11\n"
                //                + "#define F4 5\n"
                //                + "#define A4 4\n"
                //                + "#define C5 3\n"
                //                + "#define E5 2\n"
                //                + "#define G5 1\n"
                //                + "#define B5 0\n"
                //                + "//Notas pertenecientes al pca2\n"
                //                + "#define D3 0\n"
                //                + "#define F3 1\n"
                //                + "#define A3 2\n"
                //                + "#define C4 3\n"
                //                + "#define E4 4\n"
                //                + "#define G4 5\n"
                //                + "#define B4 11\n"
                //                + "#define D5 12\n"
                //                + "#define F5 13\n"
                //                + "#define A5 14\n"
                //                + "#define C6 15"
                + "\n"
                + "void setup() {\n"
                + "  Serial.begin(9600);\n"
                + "  pca1.begin();\n"
                + "  pca2.begin();\n"
                + "  pca1.setPWMFreq(60);\n"
                + "  pca2.setPWMFreq(60);\n"
                + "}\n"
                + "\n"
                + "void loop() {\n"
                + "  inicializar (); //inicializar todos los servos a 20, dar un delay de 2 segundos antes de empezar a tocar\n"
                + "  //Canción de la tormenta\n"
                + funcionesxd
                + "  delay(10000); //Delay de 10 segundos antes de repetir la pieza\n"
                + "}\n"
                + "\n"
                + "void inicializar (){\n"
                + "  pca1.setPWM(0,0,anguloaPulso(20));\n"
                + "  pca1.setPWM(1,0,anguloaPulso(20));\n"
                + "  pca1.setPWM(2,0,anguloaPulso(20));\n"
                + "  pca1.setPWM(3,0,anguloaPulso(20));\n"
                + "  pca1.setPWM(4,0,anguloaPulso(20));\n"
                + "  pca1.setPWM(5,0,anguloaPulso(20));\n"
                + "  pca1.setPWM(6,0,anguloaPulso(20));\n"
                + "  pca1.setPWM(7,0,anguloaPulso(20));\n"
                + "  pca1.setPWM(8,0,anguloaPulso(20));\n"
                + "  pca1.setPWM(9,0,anguloaPulso(20));\n"
                + "  pca1.setPWM(10,0,anguloaPulso(20));\n"
                + "  pca1.setPWM(11,0,anguloaPulso(20));\n"
                + "  pca1.setPWM(12,0,anguloaPulso(20));\n"
                + "  pca1.setPWM(13,0,anguloaPulso(20));\n"
                + "  pca1.setPWM(14,0,anguloaPulso(20));\n"
                + "  pca1.setPWM(15,0,anguloaPulso(20));\n"
                + "\n"
                + "  pca2.setPWM(0,0,anguloaPulso(20));\n"
                + "  pca2.setPWM(1,0,anguloaPulso(20));\n"
                + "  pca2.setPWM(2,0,anguloaPulso(20));\n"
                + "  pca2.setPWM(3,0,anguloaPulso(20));\n"
                + "  pca2.setPWM(4,0,anguloaPulso(20));\n"
                + "  pca2.setPWM(5,0,anguloaPulso(20));\n"
                + "\n"
                + "  delay(2000);\n"
                + "}\n"
                + "\n"
                + "//Método que nos permite utilizar \"ángulos\" en lugar de pulsos\n"
                + "int anguloaPulso(int angulo){\n"
                + "  int pulso = map(angulo,0,180,100,580);\n"
                + "  return pulso;\n"
                + "}\n"
                + "\n"
                + "//Método para tocar una nota, se le indica qué placa es, el puerto del servomotor y el delay\n"
                + "void tocarNota(Adafruit_PWMServoDriver placa,int nota, int duracion){\n"
                + "	placa.setPWM(nota,0,anguloaPulso(0));\n"
                + "	int duracionCalculada = duracion * (60.0 / " + tempoPiano + ");\n"
                + "     delay(duracionCalculada);\n"
                + "     placa.setPWM(nota,0,anguloaPulso(20));\n"
                + "}";

        try {
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(codigoPiano);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param entrada Compas que se convertira en funcion
     *
     * Funcion para generar las funciones de las notas del piano
     */
    public static String generarFuncionesPiano(String entrada) {
        String[] elementos = entrada.replaceAll("\\[|\\]", "").split(",\\s*");
        String funcionGenerada = "";
        String ultimoPCAUtilizado = "";
        String ultimaNotaUtilizada = "";

        for (String elemento : elementos) {
            // Normalizar cadena
            if (elemento.contains(" ")) {
                elemento = elemento.replace(" ", "");
            }
            if (elemento.contains("\t")) {
                elemento = elemento.replace("\t", "");
            }

            // Separar elementos
            String[] partes = elemento.split("\\.");

            // Reemplazar notas de clave xd
            if (diccionarioNotasClave.containsKey(partes[0])) {
                partes[0] = diccionarioNotasClave.get(partes[0]);
            }

            String nota = partes[0];
            String figura = partes[1];

            String valorPCA = diccionarioNotaPCA.get(nota);
            Integer valorDelay = diccionarioFiguraDelay.get(figura);

            String funcionDeTocarNota = "tocarNota(" + valorPCA + "," + nota + "," + valorDelay + ");\n";
            if (ultimoPCAUtilizado.equals(valorPCA) && ultimaNotaUtilizada.equals(nota)) {
                funcionDeTocarNota = "  delay(50); //Este delay lo puse aquí porque se vuelven locos los servos y no regresa a la posición inicial si la siguiente nota a tocar es la misma\n" + funcionDeTocarNota;
            }
            ultimoPCAUtilizado = valorPCA;
            ultimaNotaUtilizada = nota;

            funcionGenerada += funcionDeTocarNota + "\n";
        }
        return funcionGenerada;
    }

    /**
     * @param texto Es el texto que se va a analizar
     *
     * Funcion para repetir los bloques de codigo de los ciclos de repeticion
     */
    public static String convertirAduino(String texto) {
        // Buscar la palabra clave 'rep(' en el texto
        int index = texto.indexOf("rep(");
        if (index != -1) {
            // Encontrar el número entre paréntesis después de 'rep('
            int numeroRepeticiones = Character.getNumericValue(texto.charAt(index + 4));

            // Encontrar el bloque a repetir
            int inicioBloque = texto.indexOf("{", index);
            int finBloque = encontrarFinBloque(texto, inicioBloque);

            String bloque = texto.substring(inicioBloque + 1, finBloque);

            // Crear el bloque repetido
            StringBuilder bloquesRepeticion = new StringBuilder();
            for (int i = 0; i < numeroRepeticiones; i++) {
                bloquesRepeticion.append(bloque).append("\n");
            }

            // Reemplazar el bloque original con el bloque repetido
            texto = texto.substring(0, index) + bloquesRepeticion + texto.substring(finBloque + 1);
        }

        return texto;
    }

    /**
     * @param texto
     * @param inicio
     *
     * Funcion para encontrar los bloques de codigo que se van a repetirw
     */
    public static int encontrarFinBloque(String texto, int inicio) {
        int contador = 1;
        int index = inicio + 1;

        while (contador > 0 && index < texto.length()) {
            char c = texto.charAt(index);
            if (c == '{') {
                contador++;
            } else if (c == '}') {
                contador--;
            }
            index++;
        }

        return index - 1;
    }

    /**
     *
     */
    public static String procesarRepeticiones(String entrada) {
        Pattern pattern = Pattern.compile("rep\\((\\d+)\\)\\s*\\{(.*?)\\}", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(entrada);

        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            int repeticiones = Integer.parseInt(matcher.group(1));
            String contenidoRepeticion = matcher.group(2).trim();

            StringBuilder bloquesRepeticion = new StringBuilder();
            for (int i = 0; i < repeticiones; i++) {
                bloquesRepeticion.append(contenidoRepeticion).append("\n");
            }

            matcher.appendReplacement(sb, Matcher.quoteReplacement(bloquesRepeticion.toString()));
        }

        matcher.appendTail(sb);
        return sb.toString();
    }

    //==========================================================================
    // Generacion de diccionarios
    /**
     * Funcion para generar el diccionario que relaciona notas con el PCA al que
     * estan conectadas
     */
    private static void generarDiccionarioNotasPCA() {
        diccionarioNotaPCA.put("C3", "pca1");
        diccionarioNotaPCA.put("D3", "pca1");
        diccionarioNotaPCA.put("E3", "pca1");
        diccionarioNotaPCA.put("F3", "pca1");
        diccionarioNotaPCA.put("G3", "pca1");
        diccionarioNotaPCA.put("A3", "pca1");
        diccionarioNotaPCA.put("B3", "pca1");
        diccionarioNotaPCA.put("C4", "pca1");
        diccionarioNotaPCA.put("D4", "pca1");
        diccionarioNotaPCA.put("E4", "pca1");
        diccionarioNotaPCA.put("F4", "pca1");
        diccionarioNotaPCA.put("G4", "pca1");
        diccionarioNotaPCA.put("A4", "pca1");
        diccionarioNotaPCA.put("B4", "pca1");
        diccionarioNotaPCA.put("C5", "pca1");
        diccionarioNotaPCA.put("D5", "pca1");

        diccionarioNotaPCA.put("E5", "pca1");
        diccionarioNotaPCA.put("F5", "pca1");
        diccionarioNotaPCA.put("G5", "pca1");
        diccionarioNotaPCA.put("A5", "pca1");
        diccionarioNotaPCA.put("B5", "pca1");
        diccionarioNotaPCA.put("C6", "pca1");
    }

    /**
     * Funcion para generar el diccionario que relaciona figuras con su
     * respectivo delay en la reproduccion de notas
     */
    private static void generarDiccionarioFiguraDelay() {
        diccionarioFiguraDelay.put("P-r", 2000);
        diccionarioFiguraDelay.put("P-b", 1000);
        diccionarioFiguraDelay.put("P-n", 500);
        diccionarioFiguraDelay.put("P-c", 250);
        diccionarioFiguraDelay.put("P-s", 125);
        diccionarioFiguraDelay.put("P-f", 63);
        diccionarioFiguraDelay.put("P-sf", 31);
    }

    /**
     * Funcion para generar las notas de la clave
     */
    private static void generarDiccionarioNotasClave() {
        diccionarioNotasClave.put("A", "A3");
        diccionarioNotasClave.put("B", "B3");
        diccionarioNotasClave.put("C", "C4");
        diccionarioNotasClave.put("D", "D4");
        diccionarioNotasClave.put("E", "E4");
        diccionarioNotasClave.put("F", "F4");
        diccionarioNotasClave.put("G", "G4");
    }
}
