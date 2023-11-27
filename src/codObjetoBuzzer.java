
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author kobayashi
 */
public class codObjetoBuzzer {

    String codigoEjemplo = "";

    public void generateCodigoBuzzer(String notasxd, String nombreArchivo, int tempo) {
        String ruta = "/home/kobayashi/Desktop/Semestre 7/4-Automatas2/todoxd/Compilador-main/src/objetoBuzzer/"+nombreArchivo+"Buzzer.ino";
        codigoEjemplo = "#define NOTE_B0  31\n"
                + "#define NOTE_C1  33\n"
                + "#define NOTE_CS1 35\n"
                + "#define NOTE_D1  37\n"
                + "#define NOTE_DS1 39\n"
                + "#define NOTE_E1  41\n"
                + "#define NOTE_F1  44\n"
                + "#define NOTE_FS1 46\n"
                + "#define NOTE_G1  49\n"
                + "#define NOTE_GS1 52\n"
                + "#define NOTE_A1  55\n"
                + "#define NOTE_AS1 58\n"
                + "#define NOTE_B1  62\n"
                + "#define NOTE_C2  65\n"
                + "#define NOTE_CS2 69\n"
                + "#define NOTE_D2  73\n"
                + "#define NOTE_DS2 78\n"
                + "#define NOTE_E2  82\n"
                + "#define NOTE_F2  87\n"
                + "#define NOTE_FS2 93\n"
                + "#define NOTE_G2  98\n"
                + "#define NOTE_GS2 104\n"
                + "#define NOTE_A2  110\n"
                + "#define NOTE_AS2 117\n"
                + "#define NOTE_B2  123\n"
                + "#define NOTE_C3  131\n"
                + "#define NOTE_CS3 139\n"
                + "#define NOTE_D3  147\n"
                + "#define NOTE_DS3 156\n"
                + "#define NOTE_E3  165\n"
                + "#define NOTE_F3  175\n"
                + "#define NOTE_FS3 185\n"
                + "#define NOTE_G3  196\n"
                + "#define NOTE_GS3 208\n"
                + "#define NOTE_A3  220\n"
                + "#define NOTE_AS3 233\n"
                + "#define NOTE_B3  247\n"
                + "#define NOTE_C4  262\n"
                + "#define NOTE_CS4 277\n"
                + "#define NOTE_D4  294\n"
                + "#define NOTE_DS4 311\n"
                + "#define NOTE_E4  330\n"
                + "#define NOTE_F4  349\n"
                + "#define NOTE_FS4 370\n"
                + "#define NOTE_G4  392\n"
                + "#define NOTE_GS4 415\n"
                + "#define NOTE_A4  440\n"
                + "#define NOTE_AS4 466\n"
                + "#define NOTE_B4  494\n"
                + "#define NOTE_C5  523\n"
                + "#define NOTE_CS5 554\n"
                + "#define NOTE_D5  587\n"
                + "#define NOTE_DS5 622\n"
                + "#define NOTE_E5  659\n"
                + "#define NOTE_F5  698\n"
                + "#define NOTE_FS5 740\n"
                + "#define NOTE_G5  784\n"
                + "#define NOTE_GS5 831\n"
                + "#define NOTE_A5  880\n"
                + "#define NOTE_AS5 932\n"
                + "#define NOTE_B5  988\n"
                + "#define NOTE_C6  1047\n"
                + "#define NOTE_CS6 1109\n"
                + "#define NOTE_D6  1175\n"
                + "#define NOTE_DS6 1245\n"
                + "#define NOTE_E6  1319\n"
                + "#define NOTE_F6  1397\n"
                + "#define NOTE_FS6 1480\n"
                + "#define NOTE_G6  1568\n"
                + "#define NOTE_GS6 1661\n"
                + "#define NOTE_A6  1760\n"
                + "#define NOTE_AS6 1865\n"
                + "#define NOTE_B6  1976\n"
                + "#define NOTE_C7  2093\n"
                + "#define NOTE_CS7 2217\n"
                + "#define NOTE_D7  2349\n"
                + "#define NOTE_DS7 2489\n"
                + "#define NOTE_E7  2637\n"
                + "#define NOTE_F7  2794\n"
                + "#define NOTE_FS7 2960\n"
                + "#define NOTE_G7  3136\n"
                + "#define NOTE_GS7 3322\n"
                + "#define NOTE_A7  3520\n"
                + "#define NOTE_AS7 3729\n"
                + "#define NOTE_B7  3951\n"
                + "#define NOTE_C8  4186\n"
                + "#define NOTE_CS8 4435\n"
                + "#define NOTE_D8  4699\n"
                + "#define NOTE_DS8 4978\n"
                + "#define REST      0\n"
                + "\n"
                + "\n"
                + "// Cambia esto para hacer la canción más lenta o más rápida\n"
                + "int tempo = "+tempo+";\n"
                + "\n"
                + "// Cambia esto al pin que desees utilizar\n"
                + "int buzzer = 19;\n"
                + "\n"
                + "// Notas de la melodía seguidas por la duración.\n"
                + "// un 4 significa una negra, 8 dieciseisava, 16 corchea, y así sucesivamente\n"
                + "// ¡¡los números negativos se usan para representar notas con puntillo,\n"
                + "// entonces -4 significa una negra con puntillo, es decir, una negra más una dieciseisava!!\n"
                + "// Este código usa PROGMEM para ajustar la melodía a la memoria flash ya que era demasiado larga\n"
                + "// para ajustarse en la SRAM. Puede que no funcione en otras arquitecturas de Arduino que no sean AVR\n"
                + "\n"
                + "const int melody[] PROGMEM = {\n"
                + "\n"
                + "  // At Doom's Gate (E1M1)\n"
                + "  // Score available at https://musescore.com/pieridot/doom\n"
                + "\n"
                + notasxd
                + "};\n"
                + "\n"
                + "\n"
                + "// sizeof devuelve el número de bytes, cada valor int está compuesto por dos bytes (16 bits)\n"
                + "// hay dos valores por nota (tono y duración), por lo que para cada nota hay cuatro bytes\n"
                + "int notes = sizeof(melody) / sizeof(melody[0]) / 2;\n"
                + "\n"
                + "// esto calcula la duración de una nota entera en milisegundos\n"
                + "int wholenote = (60000 * 4) / tempo;\n"
                + "\n"
                + "int divider = 0, noteDuration = 0;\n"
                + "\n"
                + "void setup() {\n"
                + "  // iterar sobre las notas de la melodía.\n"
                + "// Recuerda, el array es el doble del número de notas (notas + duraciones)\n"
                + "  for (int thisNote = 0; thisNote < notes * 2; thisNote = thisNote + 2) {\n"
                + "\n"
                + "    // calcula la duración de cada nota\n"
                + "    divider = pgm_read_word_near(melody+thisNote + 1);\n"
                + "    if (divider > 0) {\n"
                + "      // nota regular, simplemente procede\n"
                + "      noteDuration = (wholenote) / divider;\n"
                + "    } else if (divider < 0) {\n"
                + "      // ¡¡las notas con puntillo se representan con duraciones negativas!!\n"
                + "      noteDuration = (wholenote) / abs(divider);\n"
                + "      noteDuration *= 1.5; // aumenta la duración a la mitad para notas con puntillo\n"
                + "    }\n"
                + "\n"
                + "    // solo reproducimos la nota durante el 90% de la duración, dejando un 10% como pausa\n"
                + "    tone(buzzer, pgm_read_word_near(melody+thisNote), noteDuration * 0.9);\n"
                + "\n"
                + "    // Espera la duración especificada antes de reproducir la siguiente nota.\n"
                + "    delay(noteDuration);\n"
                + "\n"
                + "    // detiene la generación de la forma de onda antes de la siguiente nota.\n"
                + "    noTone(buzzer);\n"
                + "  }\n"
                + "}\n"
                + "\n"
                + "void loop() {\n"
                + "  // no es necesario repetir la melodía.\n"
                + "}\n"
                + "";

        try {
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(codigoEjemplo);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
