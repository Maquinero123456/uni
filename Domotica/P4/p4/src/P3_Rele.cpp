#include <Arduino.h>
#include "debuglog.h"
#include "cfg_loop.h"
#include "types.h"
#include "QueueList.h"  
#include "domoBoard.h"
#include "config_practicas.h"

/*******************************************************/
/***                    Variables                    ***/
/*******************************************************/
QueueList<void_callback_f> _loop_callbacks;
#define 	HEADER_BYTE     '#'  	// Byte de cabecera

/*********************************************************************/
/***                    Prototipos de funciones                    ***/
/*********************************************************************/
void main_loop();

void epdRegisterLoop(void_callback_f callback) {
    _loop_callbacks.push(callback);
}

void setup() {

    config_practica4_apt_2();

    Serial.begin(115200);

    epdRegisterLoop(leeSensores);

    DEBUGLNF("P04");
}

void loop() {
    EXECUTELOOP(){
		UPDATELOOP();
		readCommand();
		main_loop();

	}
}

void main_loop(){
	// Call registered loop callbacks

	for (uint8_t i = 0; i < _loop_callbacks.count(); i++) {
		(_loop_callbacks.peek(i))();
	}

}


void foundCommand(){
	switch(cmdSerial[1]){
		case '1':
			config_practica1_apt_1();
			Serial.println("Practica 1 Apartado 1 Seleccionado");
			break;
		case '2':
			config_practica1_apt_2();
			Serial.println("Practica 1 Apartado 2 Seleccionado");
			break;
		case '3':
			config_practica1_apt_3();
			Serial.println("Practica 1 Apartado 3 Seleccionado");
			break;
		case '4':
			config_practica3_apt_2();
			Serial.println("Practica 3 Seleccionado");
			break;
		case '5':
			config_practica4_apt_2();
			Serial.println("Practica 4 Apartado 2 Seleccionado");
			break;
		case '6':
			config_practica4_apt_3();
			Serial.println("Practica 4 Apartado 3 Seleccionado");
			break;
		case '7':
			config_practica4_apt_4();
			Serial.println("Practica 4 Apartado 4 Seleccionado");
			break;
		case '8':
			config_practica4_apt_5();
			Serial.println("Practica 4 Apartado 5 Seleccionado");
			break;	
		}
		
}

void readCommand(){

	while(Serial.available()){
		byte c = Serial.read();

		// Desplazar los bytes en el buffer para mantener los últimos leídos
		for (uint8_t i = 0; i < sizeof(cmdSerial) - 1; i++) {
			cmdSerial[i] = cmdSerial[i + 1];
		}
		
		cmdSerial[sizeof(cmdSerial) - 1] = c; // Insertar el nuevo byte

		// Verificar si comando encontrado
		if ((cmdSerial[0] == HEADER_BYTE)) {
		
			//Comprueba si el comando es correcto
			if(cmdSerial[1] == '1' || cmdSerial[1] == '2' || cmdSerial[1] == '3'){
				foundCommand();
			}
		}
	}

}
