#include "Arduino.h"

int boton1 = 3 ;
int boton2 = 10;
int boton3 = 11;
bool estado1 = 0;
bool estado2 = 0;
bool estado3 = 0;

void mostrarEstado(int boton, bool estado){
	Serial.print("Estado ");
	Serial.print(boton);
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
	if(digitalRead(boton1)==HIGH){
		estado1=!estado1;
		mostrarEstado(1, estado1);
	}

	if(digitalRead(boton2)==HIGH){
		estado2=!estado2;
		mostrarEstado(2, estado2);

	}

	if(digitalRead(boton3)==HIGH){
		estado3=!estado3;
		mostrarEstado(3, estado3);
	}
}
