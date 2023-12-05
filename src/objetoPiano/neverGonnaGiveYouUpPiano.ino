#include <Wire.h>
#include <Adafruit_PWMServoDriver.h>

Adafruit_PWMServoDriver pca1 = Adafruit_PWMServoDriver(0x40);
Adafruit_PWMServoDriver pca2 = Adafruit_PWMServoDriver(0x41);

//Definir el puerto en el que están conectados los servos correspondientes a las notas
#define G3 0
#define A3 1
#define B3 2
#define C4 3
#define D4 4
#define E4 5
#define F4 6
#define G4 7
#define A4 8
#define B4 9
#define C5 10
#define D5 11

#define E5 12
#define F5 13
#define G5 14
#define A5 15
#define C6 16

void setup() {
  Serial.begin(9600);
  pca1.begin();
  pca2.begin();
  pca1.setPWMFreq(60);
  pca2.setPWMFreq(60);
}

void loop() {
  inicializar (); //inicializar todos los servos a 20, dar un delay de 2 segundos antes de empezar a tocar
  //Canción de la tormenta
  delay(10000); //Delay de 10 segundos antes de repetir la pieza
}

void inicializar (){
  pca1.setPWM(0,0,anguloaPulso(20));
  pca1.setPWM(1,0,anguloaPulso(20));
  pca1.setPWM(2,0,anguloaPulso(20));
  pca1.setPWM(3,0,anguloaPulso(20));
  pca1.setPWM(4,0,anguloaPulso(20));
  pca1.setPWM(5,0,anguloaPulso(20));
  pca1.setPWM(6,0,anguloaPulso(20));
  pca1.setPWM(7,0,anguloaPulso(20));
  pca1.setPWM(8,0,anguloaPulso(20));
  pca1.setPWM(9,0,anguloaPulso(20));
  pca1.setPWM(10,0,anguloaPulso(20));
  pca1.setPWM(11,0,anguloaPulso(20));
  pca1.setPWM(12,0,anguloaPulso(20));
  pca1.setPWM(13,0,anguloaPulso(20));
  pca1.setPWM(14,0,anguloaPulso(20));
  pca1.setPWM(15,0,anguloaPulso(20));

  pca2.setPWM(0,0,anguloaPulso(20));
  pca2.setPWM(1,0,anguloaPulso(20));
  pca2.setPWM(2,0,anguloaPulso(20));
  pca2.setPWM(3,0,anguloaPulso(20));
  pca2.setPWM(4,0,anguloaPulso(20));
  pca2.setPWM(5,0,anguloaPulso(20));

  delay(2000);
}

//Método que nos permite utilizar "ángulos" en lugar de pulsos
int anguloaPulso(int angulo){
  int pulso = map(angulo,0,180,100,580);
  return pulso;
}

//Método para tocar una nota, se le indica qué placa es, el puerto del servomotor y el delay
void tocarNota(Adafruit_PWMServoDriver placa,int nota, int duracion){
	placa.setPWM(nota,0,anguloaPulso(0));
	int duracionCalculada = duracion * (60.0 / 114);
     delay(duracionCalculada);
     placa.setPWM(nota,0,anguloaPulso(20));
}