/*
 * domoBoard.cpp
 *
 *  Created on: 09/03/2015
 *      Author: jctejero
 * 
 * 	Modified on: 17/03/2025
 * 
 * 	Descripción:
 * 	Clase que gestiona la placa de prácticas de la asignatura de Domótica.
 */

/****************************************************************************/
/***        Include files                                                 ***/
/****************************************************************************/
#include "domoBoard.h"
#include "debuglog.h"

// Constructors ////////////////////////////////////////////////////////////////

DomoBoard::DomoBoard()
{
	//Definimos pin's DomoBoard.
	pinMode(BUTTON1_P, INPUT);
	pinMode(BUTTON2_P, INPUT);
	pinMode(BTN_OPT_P, INPUT);      	//Pin Entrada Optocoplada

	pinMode(RELE_P, OUTPUT);
	pinMode(TRIAC_P, OUTPUT);

	BOTON1.pin 			= BUTTON1_P;
	BOTON1.valor 		= digitalRead(BUTTON1_P);		//Leer Valor por defecto
	BOTON1.valor_Df 	= BOTON1.valor;
	BOTON1.Activo 		= true;
	BOTON1.eSensor  	= S_DIGITAL;
	BOTON1.name			= F("BOTÓN 1");

	BOTON2.pin 			= BUTTON2_P;
	BOTON2.valor 		= digitalRead(BUTTON2_P);
	BOTON2.valor_Df 	= BOTON2.valor;
	BOTON2.Activo 		= true;
	BOTON2.eSensor  	= S_DIGITAL;
	BOTON2.name			= F("BOTÓN 2");
	
	BTN_OPT.pin 		= BTN_OPT_P;
	BTN_OPT.valor 		= digitalRead(BTN_OPT_P);
	BTN_OPT.valor_Df 	= BTN_OPT.valor;
	BTN_OPT.Activo 		= true;
	BTN_OPT.eSensor  	= S_DIGITAL;
	BTN_OPT.name		= F("Pulsador Optocoplado");


	RELE.pin 			= RELE_P;
	RELE.estado 		= LOW;

	TRIAC.pin 			= TRIAC_P;
	TRIAC.estado 		= LOW;

	listSensors.push(&BOTON1);
	listSensors.push(&BOTON2);
	listSensors.push(&BTN_OPT);
}

void DomoBoard::leerAllSensor(void){
	for(uint8_t i = 0; i < listSensors.count(); i++){
		leerSensor(listSensors.peek(i));
	}
}


void  DomoBoard::leerSensor(ptsSensor Sensor){
	int valor = 0;

	if(Sensor->Activo){
		switch (Sensor->eSensor)
		{
		case S_DIGITAL:
			valor = digitalRead(Sensor->pin);
			break;

		case S_ANALOGICO:
			valor = analogRead(Sensor->pin);
			break;
		}

		if(Sensor->valor != valor)
		{
			Sensor->valor = valor;

			//Si hay un cambio en el estado del sensor llamamos a la aplicación asociada
			if(Sensor->SensorEvent != NULL)
				Sensor->SensorEvent(Sensor);
		}
	}
}

void  DomoBoard::setActuator(tpsActuator Actuator, bool val){
	if(Actuator->estado != val){
		digitalWrite(Actuator->pin, val);
		Actuator->estado = val;
	}
}

void leeSensores(void){
	domoboard.leerAllSensor();
}

void DomoBoard::manageSensorActuators(TManagerActuators *managerActuators, bool val){
	if(managerActuators->count() > 0){
		for(uint8_t i = 0; i < managerActuators->count(); i++){
			DomoBoard::setActuator(managerActuators->peek(i), val);
		}
	}
}

void DomoBoard::Clear_SensorsConfiguration(){
	ptsSensor sensor;
	for(int i = 0; i < listSensors.count(); i++){
		sensor = listSensors.peek(i);
		sensor->managerActuators.clear();
		sensor->SensorEvent = NULL;
	};

}

void DomoBoard::RS485_RxTx(TRX485_rxtx rxtx){
	switch(rxtx){
	case RX485_RX:
		while (!(UCSR0A & (1 << TXC0)));
		digitalWrite(EN_485, LOW);
		break;

	case RX485_TX:
		digitalWrite(EN_485, HIGH);
		delay(1);
		break;
	}
}

DomoBoard domoboard;

