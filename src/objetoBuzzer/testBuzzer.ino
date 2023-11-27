#define NOTE_B0  31
#define NOTE_C1  33
#define NOTE_CS1 35
#define NOTE_D1  37
#define NOTE_DS1 39
#define NOTE_E1  41
#define NOTE_F1  44
#define NOTE_FS1 46
#define NOTE_G1  49
#define NOTE_GS1 52
#define NOTE_A1  55
#define NOTE_AS1 58
#define NOTE_B1  62
#define NOTE_C2  65
#define NOTE_CS2 69
#define NOTE_D2  73
#define NOTE_DS2 78
#define NOTE_E2  82
#define NOTE_F2  87
#define NOTE_FS2 93
#define NOTE_G2  98
#define NOTE_GS2 104
#define NOTE_A2  110
#define NOTE_AS2 117
#define NOTE_B2  123
#define NOTE_C3  131
#define NOTE_CS3 139
#define NOTE_D3  147
#define NOTE_DS3 156
#define NOTE_E3  165
#define NOTE_F3  175
#define NOTE_FS3 185
#define NOTE_G3  196
#define NOTE_GS3 208
#define NOTE_A3  220
#define NOTE_AS3 233
#define NOTE_B3  247
#define NOTE_C4  262
#define NOTE_CS4 277
#define NOTE_D4  294
#define NOTE_DS4 311
#define NOTE_E4  330
#define NOTE_F4  349
#define NOTE_FS4 370
#define NOTE_G4  392
#define NOTE_GS4 415
#define NOTE_A4  440
#define NOTE_AS4 466
#define NOTE_B4  494
#define NOTE_C5  523
#define NOTE_CS5 554
#define NOTE_D5  587
#define NOTE_DS5 622
#define NOTE_E5  659
#define NOTE_F5  698
#define NOTE_FS5 740
#define NOTE_G5  784
#define NOTE_GS5 831
#define NOTE_A5  880
#define NOTE_AS5 932
#define NOTE_B5  988
#define NOTE_C6  1047
#define NOTE_CS6 1109
#define NOTE_D6  1175
#define NOTE_DS6 1245
#define NOTE_E6  1319
#define NOTE_F6  1397
#define NOTE_FS6 1480
#define NOTE_G6  1568
#define NOTE_GS6 1661
#define NOTE_A6  1760
#define NOTE_AS6 1865
#define NOTE_B6  1976
#define NOTE_C7  2093
#define NOTE_CS7 2217
#define NOTE_D7  2349
#define NOTE_DS7 2489
#define NOTE_E7  2637
#define NOTE_F7  2794
#define NOTE_FS7 2960
#define NOTE_G7  3136
#define NOTE_GS7 3322
#define NOTE_A7  3520
#define NOTE_AS7 3729
#define NOTE_B7  3951
#define NOTE_C8  4186
#define NOTE_CS8 4435
#define NOTE_D8  4699
#define NOTE_DS8 4978
#define REST      0


// Cambia esto para hacer la canción más lenta o más rápida
int tempo = 225;

// Cambia esto al pin que desees utilizar
int buzzer = 19;

// Notas de la melodía seguidas por la duración.
// un 4 significa una negra, 8 dieciseisava, 16 corchea, y así sucesivamente
// ¡¡los números negativos se usan para representar notas con puntillo,
// entonces -4 significa una negra con puntillo, es decir, una negra más una dieciseisava!!
// Este código usa PROGMEM para ajustar la melodía a la memoria flash ya que era demasiado larga
// para ajustarse en la SRAM. Puede que no funcione en otras arquitecturas de Arduino que no sean AVR

const int melody[] PROGMEM = {

  // At Doom's Gate (E1M1)
  // Score available at https://musescore.com/pieridot/doom

NOTE_G2, 4, NOTE_F4, 4, NOTE_A3, 4, NOTE_B3, 4,
NOTE_A3, 2, NOTE_A3, 2,
NOTE_A3, 1,
NOTE_G2, 4, NOTE_F4, 4, NOTE_A3, 4, NOTE_B3, 4,
NOTE_A2, 2, NOTE_A4, 2,
NOTE_A4, 4, NOTE_A4, 4, NOTE_A4, 4, NOTE_A4, 4,
NOTE_G2, 2, NOTE_F4, 2,
NOTE_A4, 4, NOTE_A4, 4, NOTE_A4, 4, NOTE_A4, 4,
NOTE_G2, 2, NOTE_F4, 2,
NOTE_A4, 4, NOTE_A4, 4, NOTE_A4, 4, NOTE_A4, 4,
NOTE_G2, 2, NOTE_F4, 2,
NOTE_G2, 2, NOTE_F4, 2,
NOTE_F4, 2, NOTE_B3, 2,
NOTE_B3, 1,
NOTE_G2, 2, NOTE_F4, 2,
NOTE_A3, 2, NOTE_B4, 2,
NOTE_E4, 4, NOTE_E4, 4, NOTE_F4, 4, NOTE_G4, 4,
NOTE_G2, 2, NOTE_F4, 2,
NOTE_E4, 4, NOTE_E4, 4, NOTE_F4, 4, NOTE_G4, 4,
NOTE_G2, 2, NOTE_F4, 2,
NOTE_E4, 4, NOTE_E4, 4, NOTE_F4, 4, NOTE_G4, 4,
NOTE_G2, 2, NOTE_F4, 2,
NOTE_F4, 4, NOTE_A3, 2, NOTE_C4, 4,
NOTE_B3, 1,
NOTE_F3, 1,
NOTE_A3, 2, NOTE_B4, 2,
NOTE_E4, 4, NOTE_E4, 4, NOTE_F4, 4, NOTE_G4, 4,
NOTE_F3, 1,
NOTE_E4, 4, NOTE_E4, 4, NOTE_F4, 4, NOTE_G4, 4,
NOTE_F3, 1,
};


// sizeof devuelve el número de bytes, cada valor int está compuesto por dos bytes (16 bits)
// hay dos valores por nota (tono y duración), por lo que para cada nota hay cuatro bytes
int notes = sizeof(melody) / sizeof(melody[0]) / 2;

// esto calcula la duración de una nota entera en milisegundos
int wholenote = (60000 * 4) / tempo;

int divider = 0, noteDuration = 0;

void setup() {
  // iterar sobre las notas de la melodía.
// Recuerda, el array es el doble del número de notas (notas + duraciones)
  for (int thisNote = 0; thisNote < notes * 2; thisNote = thisNote + 2) {

    // calcula la duración de cada nota
    divider = pgm_read_word_near(melody+thisNote + 1);
    if (divider > 0) {
      // nota regular, simplemente procede
      noteDuration = (wholenote) / divider;
    } else if (divider < 0) {
      // ¡¡las notas con puntillo se representan con duraciones negativas!!
      noteDuration = (wholenote) / abs(divider);
      noteDuration *= 1.5; // aumenta la duración a la mitad para notas con puntillo
    }

    // solo reproducimos la nota durante el 90% de la duración, dejando un 10% como pausa
    tone(buzzer, pgm_read_word_near(melody+thisNote), noteDuration * 0.9);

    // Espera la duración especificada antes de reproducir la siguiente nota.
    delay(noteDuration);

    // detiene la generación de la forma de onda antes de la siguiente nota.
    noTone(buzzer);
  }
}

void loop() {
  // no es necesario repetir la melodía.
}
