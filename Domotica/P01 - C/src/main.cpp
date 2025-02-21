#include "Arduino.h"
//The setup function is called once at startup of the sketch
int boton1 = 3 ;
int boton2 = 10;
int boton3 = 11;
bool estado = 0;

void mostrarEstado(bool estado){
	Serial.print("Estado");
	Serial.print(" a cambiado a ");
	Serial.println(estado);
}

void setup(){
	pinMode(boton1, INPUT);
	pinMode(boton2, INPUT);
	pinMode(boton3, INPUT);
	Serial.begin(115200);
}

// The loop function is called in an endless loop
void loop(){
	if(digitalRead(boton1)==HIGH || digitalRead(boton2)==HIGH || digitalRead(boton3)==HIGH){
		estado=!estado;
		mostrarEstado(estado);
	}
}
