/*
 * P04_Triac.cpp
 *
 *  Created on: 17/03/2015
 *      Author: jctejero
 * 
 * Modified on: 17/03/2025
 *
 * description:
 * 	Práctica 5. Comunicaciones ModBus
 ***************************************************************************/

/***********************************************************/
/***                    Include files                    ***/
/***********************************************************/
#include <Arduino.h>
#include "Gest_Modbus.h"
#include "debuglog.h"
#include "cfg_loop.h"
#include "QueueList.h"  
#include "DomoBoard/domoBoard.h"
#include "config_practicas.h"

/*******************************************************/
/***                    Variables                    ***/
/*******************************************************/
QueueList<void_callback_f> _loop_callbacks;

/*********************************************************************/
/***                    Prototipos de funciones                    ***/
/*********************************************************************/
void main_loop();

void epdRegisterLoop(void_callback_f callback) {
    _loop_callbacks.push(callback);
}

void setup() {

    //Iniciamos Modbus en Modo RTU
	Init_RTU_Modbus();

	//Configuramos la práctica
	config_practica5_apt_4();

	//Registramos la lectura de sensores
    epdRegisterLoop(leeSensoresmb);

	//Registramos comprobación comunicaciones ModBus
	epdRegisterLoop(RTU_ModBus);

    DEBUGLNF("P05 Comunicaciones MODBUS Solución");
}

void loop() {
    EXECUTELOOP(){
		UPDATELOOP();

		main_loop();

	}
}

void main_loop(){
	// llamada a las funciones registradas
	for (uint8_t i = 0; i < _loop_callbacks.count(); i++) {
		(_loop_callbacks.peek(i))();
	}
}
