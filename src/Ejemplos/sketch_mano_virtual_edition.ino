#include <Servo.h>

#define buzzer 13

Servo servPulgar;
Servo servIndice;
Servo servMedio;
Servo servAnular;
Servo servChico;
/*
0 = Pulgar
1 = Indice
2 = Medio
3 = Anular
4 = Chico
5 = todos
*/
boolean abriendo[6] = {false,false,false,false,false,false};
const int maxAbierto[5] = {10,10,10,5,140};
const int minAbierto[5] = {150,170,170,160,10};
int dedo_a_mover;
char c='\0';
String palabra;
boolean mostrar_menu;

void setup() {
  Serial.begin(9600);
  pinMode( buzzer , OUTPUT);
  servPulgar.attach(6);
  servIndice.attach(5);
  servMedio.attach(4);
  servAnular.attach(3);
  servChico.attach(2);

  servPulgar.write(maxAbierto[0]);
  servIndice.write(maxAbierto[1]);  
  servMedio.write(maxAbierto[2]);
  servAnular.write(maxAbierto[3]);
  servChico.write(maxAbierto[4]);
  palabra=""; dedo_a_mover=0; mostrar_menu=true;
}

void loop() {
  delay(1000);
  activar_dedo();
}

void activar_dedo(){
  if(mostrar_menu==true){
    Serial.println("Ingrese el dedo que quiere mover:");
    Serial.println("1 - PULGAR");
    Serial.println("2 - INDICE");
    Serial.println("3 - MEDIO");
    Serial.println("4 - ANULAR");
    Serial.println("5 - CHICO");
    Serial.println("6 - TODOS");
    mostrar_menu=false;
  }
  
  delay(50);
  while(Serial.available()){
    delay(5);
    c = Serial.read();
    palabra += c;
  }
  dedo_a_mover = palabra.toInt();
  if(dedo_a_mover == 0){
    return;
  }
  switch(dedo_a_mover){
    case 1: mover_dedo(servPulgar,0);
      break;
    case 2: mover_dedo(servIndice,1);
      break;
    case 3: mover_dedo(servMedio,2);
      break;
    case 4: mover_dedo(servAnular,3);
      break;
    case 5: mover_dedo(servChico,4);
      break;
    case 6: 
      mover_todos();
      break;
    default: Serial.println("Ingrese un número del 1 al 6");
  }
  palabra=""; dedo_a_mover=0; mostrar_menu=true;
}


void mover_dedo(Servo dedo, int posArr){
  // Cambia el angulo del servo que se pasa como parámetro utilizando un if ternario
  // Si abriendo es verdadero, coloca el servo en su máximo angulo
  // Si abriendo es falso, coloca el servo en su angulo mínimo
  dedo.write(abriendo[posArr]==true?maxAbierto[posArr]:minAbierto[posArr]);
  // Cambiamos el estado al contrario (si está abriendo pasa a cerrando, y viceversa)
  abriendo[posArr]= !abriendo[posArr];
  // Ejecutamos delay de 1 seg
  delay(1000);
}//cerrar

void mover_todos(){
  // Cambiamos a todos los dedos al estado de la mano (abriendo o cerrando)
  for(int i=0;i<5;i++){
      abriendo[i] = abriendo[5];
  }
  // Movemos todos los dedos, si la mano va a abrirse y alguno ya estaba abierto
  // este no se va a mover
  mover_dedo(servPulgar,0);
  mover_dedo(servIndice,1);
  mover_dedo(servMedio,2);
  mover_dedo(servAnular,3);
  mover_dedo(servChico,4);
  // Cambiamos el estado al contrario (si está abriendo pasa a cerrando, y viceversa)
  abriendo[5] = !abriendo[5];
}
