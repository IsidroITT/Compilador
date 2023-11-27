#include <wire.h>
#include <Adafruit_PWMServoDriver.h>

Adafruit_PWMServoDriver pca1 = Adafruit_PWMServoDriver(0x40);
Adafruit_PWMServoDriver pca2 = Adafruit_PWMServoDriver(0x41);
Adafruit_PWMServoDriver pca2 = Adafruit_PWMServoDriver(0x42);

#define C3 0
#define C#3 1
#define D3 2
#define D#3 3
#define E3 4
#define F3 5
#define F#3 6
#define G3 7
#define G#3 8
#define A3  9
#define A#3 10
#define B3  11
#define C4  12
#define C#4 13
#define D4  14
#define D#4 15

#define E4  0
#define F4  1
#define F#4 2
#define G4  3
#define G#4 4
#define A4  5
#define A#4 6
#define B4  7
#define C5  8
#define C#5 9
#define D5  10
#define D#5 11
#define E5  12
#define F5  13
#define F#5 14
#define G5  15

#define G#5 0
#define A5  1
#define A#5 2
#define B5  3
#define C5  4


void setup(){
	Serial.begin(9600);
	pca1.begin();
	pca2.begin();
	pca3.begin();
	pca1.setPWMFreq(60);
	pca2.setPWMFreq(60);
	pca3.setPWMFreq(60);
}

void loop(){
	//inicializar los servos
	for(int i=0;i<=15;i++){
		pca1.setPWM(i,0,90);
		pca2.setPWM(i,0,90);
		pca3.setPWM(i,0,90);
	}
	//notas
tocarNota(pca1,F4,500);tocarNota(pca1,A3,1000);tocarNota(pca1,C4,500);tocarNota(pca1,B3,2000);tocarNota(pca1,F3,2000);tocarNota(pca1,A3,1000);tocarNota(pca1,B4,1000);tocarNota(pca1,E4,500);tocarNota(pca1,E4,500);tocarNota(pca1,F4,500);tocarNota(pca1,G4,500);tocarNota(pca1,F3,2000);tocarNota(pca1,E4,500);tocarNota(pca1,E4,500);tocarNota(pca1,F4,500);tocarNota(pca1,G4,500);tocarNota(pca1,F3,2000);
}

//Método para tocar una nota, se le indica qué placa es, el puerto del servomotor y el delay
void tocarNota(Adafruit_PWMServoDriver placa,int nota, int duracion){
	placa.setPWM(nota,0,140);
	delay(duracion);
	pca1.setPWM(nota,0,90);
}

