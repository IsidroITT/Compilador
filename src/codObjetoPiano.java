
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

    public static void main(String[] args) {
        String texto = "// Declaraciones iniciales\n"
                + "compas = 4/4;\n"
                + "tempo = 208;\n"
                + "\n"
                + "// Variables para buzzer\n"
                + "var $timbre = [G2.b, F4.b,];\n"
                + "var $timbreFuncion = [G2.n, F4.n, A3.n, B3.n,];\n"
                + "\n"
                + "// Funciones dentro del lenguaje\n"
                + "fn #miFuncioncita(){\n"
                + "	$timbreFuncion;\n"
                + "    	clave(F^2){\n"
                + "           	[A.b, A.b,];\n"
                + "           	[A.r,];\n"
                + "     	};\n"
                + "	$timbreFuncion;\n"
                + "     	[A2.b, A4.b,];\n"
                + "     	rep(3){\n"
                + "	   	[A4.n, A4.n, A4.n, A4.n,]; \n"
                + "		$timbre;\n"
                + "	};\n"
                + "};\n"
                + "\n"
                + "// Notas\n"
                + "	\\inicio;\n"
                + "		$timbre;\n"
                + "	    	clave(G^2){\n"
                + "            	[F.b, B.b,];\n"
                + "            	[B.r,];\n"
                + "        	};\n"
                + "		#miFuncioncita();\n"
                + "        	$timbre;\n"
                + "        	[A3.b, B4.b,];\n"
                + "        	rep(3){\n"
                + "		   	[E4.n, E4.n, F4.n, G4.n,]; \n"
                + "			$timbre;\n"
                + "		};\n"
                + "	\\fin;\n"
                + "	\n"
                + "\n"
                + "// Variables para piano\n"
                + "var $timbrePiano = [F3.P-r,];\n"
                + "\n"
                + "// Notas del piano\n"
                + "piano{\n"
                + "	clave(G^2) {\n"
                + "        	[F.P-n, A.P-b, C.P-n,];\n"
                + "      	[B.P-r,];\n"
                + "   	};\n"
                + "   	$timbrePiano;\n"
                + "    	[A3.P-b, B4.P-b,];\n"
                + "	rep(4) {\n"
                + "		[E4.P-n, E4.P-n, F4.P-n, G4.P-n,];\n"
                + "		$timbrePiano;\n"
                + "	};\n"
                + "};";

        String resultado = procesarRepeticiones(texto);
        System.out.println(resultado);
    }

    /**
     * @param funcionesxd Son las funciones que se agregaran a la generacionde
     * cofigo
     * @param nombreArchivo Es el nombre del archivo que sera generado
     *
     * Funcion que permite generar el codigo objeto para nuestro piano
     */
    public static void generateCodigoPiano(String funcionesxd, String nombreArchivo, String funcionesLlamar) {
        String ruta = "/home/kobayashi/Desktop/Semestre 7/4-Automatas2/todoxd/Compilador-main/src/objetoPiano/" + nombreArchivo + "Piano.ino";
        codigoPiano = "#include <wire.h>\n"
                + "#include <Adafruit_PWMServoDriver.h>\n"
                + "\n"
                + "Adafruit_PWMServoDriver pca1 = Adafruit_PWMServoDriver(0x40);\n"
                + "Adafruit_PWMServoDriver pca2 = Adafruit_PWMServoDriver(0x41);\n"
                + "Adafruit_PWMServoDriver pca2 = Adafruit_PWMServoDriver(0x42);\n"
                + "\n"
                + "#define C3 0\n"
                + "#define C#3 1\n"
                + "#define D3 2\n"
                + "#define D#3 3\n"
                + "#define E3 4\n"
                + "#define F3 5\n"
                + "#define F#3 6\n"
                + "#define G3 7\n"
                + "#define G#3 8\n"
                + "#define A3  9\n"
                + "#define A#3 10\n"
                + "#define B3  11\n"
                + "#define C4  12\n"
                + "#define C#4 13\n"
                + "#define D4  14\n"
                + "#define D#4 15\n"
                + "\n"
                + "#define E4  0\n"
                + "#define F4  1\n"
                + "#define F#4 2\n"
                + "#define G4  3\n"
                + "#define G#4 4\n"
                + "#define A4  5\n"
                + "#define A#4 6\n"
                + "#define B4  7\n"
                + "#define C5  8\n"
                + "#define C#5 9\n"
                + "#define D5  10\n"
                + "#define D#5 11\n"
                + "#define E5  12\n"
                + "#define F5  13\n"
                + "#define F#5 14\n"
                + "#define G5  15\n"
                + "\n"
                + "#define G#5 0\n"
                + "#define A5  1\n"
                + "#define A#5 2\n"
                + "#define B5  3\n"
                + "#define C5  4\n"
                + "\n"
                + "\n"
                + "void setup(){\n"
                + "	Serial.begin(9600);\n"
                + "	pca1.begin();\n"
                + "	pca2.begin();\n"
                + "	pca3.begin();\n"
                + "	pca1.setPWMFreq(60);\n"
                + "	pca2.setPWMFreq(60);\n"
                + "	pca3.setPWMFreq(60);\n"
                + "}\n"
                + "\n"
                + "void loop(){\n"
                + "	//inicializar los servos\n"
                + "	for(int i=0;i<=15;i++){\n"
                + "		pca1.setPWM(i,0,90);\n"
                + "		pca2.setPWM(i,0,90);\n"
                + "		pca3.setPWM(i,0,90);\n"
                + "	}\n"
                + "	//notas\n"
                + funcionesxd
                + "\n"
                + "}\n"
                + "\n"
                + "//Método para tocar una nota, se le indica qué placa es, el puerto del servomotor y el delay\n"
                + "void tocarNota(Adafruit_PWMServoDriver placa,int nota, int duracion){\n"
                + "	placa.setPWM(nota,0,140);\n"
                + "	delay(duracion);\n"
                + "	pca1.setPWM(nota,0,90);\n"
                + "}\n"
                + "\n"
                + funcionesLlamar;

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

            funcionGenerada += "tocarNota(" + valorPCA + "," + nota + "," + valorDelay + ");";
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
