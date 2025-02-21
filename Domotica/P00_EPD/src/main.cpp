#include <Arduino.h>

int boton = 3;
int valorActual = 0;

void setup() {
  pinMode(boton, INPUT);
  Serial.begin(115200);
}

void loop() {
  while(digitalRead(boton)!=valorActual){
    valorActual = digitalRead(boton);
    Serial.print("Valor actual:");
    Serial.println(valorActual);
  }
}

