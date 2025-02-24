#include "Arduino.h"

#define p1 3
#define p2 10
#define octo 11
byte modo = 0;

//Aux
bool est1 = 0;
bool est2 = 0;
bool est3 = 0;

//Apartado 1 y 2
bool estado1 = 0;
bool estado2 = 0;
bool estado3 = 1;

//Apartado 3
bool conmutador = 0;

//Apartado 1
void pulsado(int boton){
	Serial.print("Boton ");
	Serial.print(boton);
	Serial.println(" --> Pulsado");
}

void soltado(int boton){
	Serial.print("Boton ");
	Serial.print(boton);
	Serial.println(" --> Soltado");
}

//Apartado 2
void mostrarEstado(int boton, bool estado){
	Serial.print("Estado ");
	Serial.print(boton);
	Serial.print(" a cambiado a ");
	Serial.println(estado);
}

//Apartado 3
void mostrarConmutador(){
	Serial.print("Conmutador");
	Serial.print(" a cambiado a ");
	Serial.println(conmutador);
}

void setup(){
	pinMode(p1, INPUT);
	pinMode(p2, INPUT);
	pinMode(octo, INPUT);
	Serial.begin(115200);
}

// The loop function is called in an endless loop
void loop(){
	if(Serial.available()>0){
		modo = Serial.read();
		if(modo == 2){
			conmutador = 0;
		}else{
			estado1 = 0;
			estado2 = 0;
			estado3 = 0;
		}

		Serial.print("Modo: ");
		Serial.println(modo);
	}
	switch(modo){
		case 0:
			if(digitalRead(p1)!=est1){
				est1=!est1;
				if(est1==1){
					pulsado(1);
				}else{
					soltado(1);
				}
			}

			if(digitalRead(p2)!=est2){
				est2=!est2;
				if(est2==1){
					pulsado(2);
				}else{
					soltado(2);
				}
			}
			if(digitalRead(octo)!=est3){
				est3=!est3;
				if(est3==1){
					pulsado(3);
				}else{
					soltado(3);
				}
			}

			break;
		case 1:
			if(digitalRead(p1)!=est1){
				if(digitalRead(p1)==HIGH){
					estado1=!estado1;
					mostrarEstado(1, estado1);
				}
				est1=!est1;
			}

			if(digitalRead(p2)!=est2){
				if(digitalRead(p2)==HIGH){
					estado2=!estado2;
					mostrarEstado(2, estado2);
				}
				est2=!est2;
			}

			/*if(digitalRead(octo)!=est3){
				if(digitalRead(octo)==HIGH){
					estado3=!estado3;
					mostrarEstado(3, estado3);
				}
				est3=!est3;
			}*/

			break;
		case 2:
			if(digitalRead(p1)!=est1){
				if(digitalRead(p1)==HIGH){
					conmutador=!conmutador;
					mostrarConmutador();
				}
				est1=!est1;
			}

			if(digitalRead(p2)!=est2){
				if(digitalRead(p2)==HIGH){
					conmutador=!conmutador;
					mostrarConmutador();
				}
				est2=!est2;
			}

			/*if(digitalRead(octo)!=est3){
				if(digitalRead(octo)==HIGH){
					conmutador=!conmutador;
					mostrarConmutador();
				}
				est3=!est3;
			}*/
			break;
	}
}
