#include <Arduino.h>

int boton = 3;
bool valorActual = 0;

void setup() {
  pinMode(boton, INPUT);
  Serial.begin(115200);
}

void loop() {
  while(digitalRead(boton)!=valorActual){
    valorActual = !valorActual;
    Serial.print("Valor actual:");
    Serial.println(valorActual);
  }
}

