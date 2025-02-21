#include "Arduino.h"
//The setup function is called once at startup of the sketch
int boton1 = 3 ;
int boton2 = 10;
int boton3 = 11;

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

void setup(){
	pinMode(boton1, INPUT);
	pinMode(boton2, INPUT);
	pinMode(boton3, INPUT);
}

// The loop function is called in an endless loop
void loop(){
	switch(digitalRead(boton1)){
		case HIGH:
			pulsado(boton1);
			break;
		case LOW:
			soltado(boton1);
			break;
	}

	switch(digitalRead(boton2)){
		case HIGH:
			pulsado(boton2);
			break;
		case LOW:
			soltado(boton2);
			break;
	}

	switch(digitalRead(boton3)){
		case HIGH:
			pulsado(boton3);
			break;
		case LOW:
			soltado(boton3);
			break;
	}
}

