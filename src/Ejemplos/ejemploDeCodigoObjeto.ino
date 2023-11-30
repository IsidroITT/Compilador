#include <Wire.h>
#include <Adafruit_PWMServoDriver.h>

Adafruit_PWMServoDriver pca1 = Adafruit_PWMServoDriver(0x40);
Adafruit_PWMServoDriver pca2 = Adafruit_PWMServoDriver(0x41);

//Definir el puerto en el que están conectados los servos correspondientes a las notas
#define C3 0
#define D3 1
#define E3 2
#define F3 3
#define G3 4
#define A3 5
#define B3 6
#define C4 7
#define D4 8
#define E4 9
#define F4 10
#define G4 11
#define A4 12
#define B4 13
#define C5 14
#define D5 15

#define E5 0
#define F5 1
#define G5 2
#define A5 3
#define B5 4
#define C5 5

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
  //Primer compás
  tocarNota(pca1,D3,500);
  tocarNota(pca1,F3,500);
  tocarNota(pca1,D4,1000);
  //Segundo compás
  tocarNota(pca1,D3,500);
  tocarNota(pca1,F3,500);
  tocarNota(pca1,D4,1000);
  //Tercer compás
  tocarNota(pca1,E4,500);
  tocarNota(pca1,F4,500);
  tocarNota(pca1,E4,500);
  tocarNota(pca1,F4,500);
  //Cuarto compás
  tocarNota(pca1,E4,500);
  tocarNota(pca1,C4,500);
  tocarNota(pca1,A3,500);
    //Primer compás
  tocarNota(pca1,D3,500);
  tocarNota(pca1,F3,500);
  tocarNota(pca1,D4,1000);
  //Segundo compás
  tocarNota(pca1,D3,500);
  tocarNota(pca1,F3,500);
  tocarNota(pca1,D4,1000);
  //Tercer compás
  tocarNota(pca1,E4,500);
  tocarNota(pca1,F4,500);
  tocarNota(pca1,E4,500);
  tocarNota(pca1,F4,500);
  //Cuarto compás
  tocarNota(pca1,E4,500);
  tocarNota(pca1,C4,500);
  tocarNota(pca1,A3,500);
  delay(50); //Este delay lo puse aquí porque se vuelven locos los servos y no regresa a la posición inicial si la siguiente nota a tocar es la misma
  //Quinto compás
  tocarNota(pca1,A3,1000); 
  tocarNota(pca1,D3,1000);
  tocarNota(pca1,F3,500);
  tocarNota(pca1,G3,500);
  //Sexto compás
  tocarNota(pca1,A3,2000);
  //Séptimo compás
  tocarNota(pca1,A3,1000); 
  tocarNota(pca1,D3,1000);
  tocarNota(pca1,F3,500);
  tocarNota(pca1,G3,500);
  //Octavo compás
  tocarNota(pca1,E3,2000);
  //Quinto compás
  tocarNota(pca1,A3,1000); 
  tocarNota(pca1,D3,1000);
  tocarNota(pca1,F3,500);
  tocarNota(pca1,G3,500);
  //Sexto compás
  tocarNota(pca1,A3,2000);
  delay(50); //Este delay lo puse aquí porque se vuelven locos los servos y no regresa a la posición inicial si la siguiente nota a tocar es la misma
  //Séptimo compás
  tocarNota(pca1,A3,1000); 
  tocarNota(pca1,D3,1000);
  tocarNota(pca1,F3,500);
  tocarNota(pca1,G3,500);
  //Octavo compás
  tocarNota(pca1,E3,2000);

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
	delay(duracion);
	placa.setPWM(nota,0,anguloaPulso(20));
}