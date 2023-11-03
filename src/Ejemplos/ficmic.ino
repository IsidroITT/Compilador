#include <Servo.h>

// Define los pines para los servos y los LEDs
Servo servoMoverDerecha;
Servo ser

void setup() {
  servo1.attach(servo1Pin);
  servo2.attach(servo2Pin);

  pinMode(led1Pin, OUTPUT);
  pinMode(led2Pin, OUTPUT);

  // Inicializa los LEDs apagadvoMoverIzquierda;
Servo sTecla1;
Servo sTecla2;
Servo sTecla3;
Servo sTecla4;

int servoTecla1 = 9;
int servoTecla2 = 10;
int servoTecla3 = 11;
int servoTecla4 = 12;

int led1Pin = 2;
int led2Pin = 3;
int led3Pin = 4;
int led4Pin = 5;
int led5Pin = 6;os
  digitalWrite(led1Pin, LOW);
  digitalWrite(led2Pin, LOW);
}

void loop() {
  moveServoWithLED(servo1, led1Pin, 90); // Mueve el servo 1 a 90 grados
  delay(1000);
  moveServoWithLED(servo2, led2Pin, 45); // Mueve el servo 2 a 45 grados
  delay(1000);

  // Continúa con el movimiento de otros servos aquí

  // Enciende otros LEDs al final del movimiento de los servos si es necesario
}

void moveServoWithLED(Servo servo, int ledPin, int angle) {
  digitalWrite(ledPin, HIGH); // Enciende el LED indicador
  delay(500); // Tiempo para indicar el movimiento
  servo.write(angle); // Mueve el servo
  delay(500); // Espera a que el servo llegue a la posición deseada
  digitalWrite(ledPin, LOW); // Apaga el LED indicador
}

