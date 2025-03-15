/*
 * domoBoard.cpp
 *
 *  Created on: 09/03/2015
 *      Author: jctejero
 */

/****************************************************************************/
/***        Include files                                                 ***/
/****************************************************************************/
#include "domoBoard.h"

// Constructors ////////////////////////////////////////////////////////////////

DomoBoard::DomoBoard()
{
	//Definimos pin's DomoBoard.
	pinMode(BUTTON1_P, INPUT);
	pinMode(BUTTON2_P, INPUT);
	pinMode(BTN_OPT_P, INPUT);      	//Pin Entrada Optocoplada
	pinMode(RELE_INTERRUPTOR_P, OUTPUT);


	//TODO: Leer valor por defecto de los sensores

	BOTON1.sSensor.pin 			= BUTTON1_P;
	BOTON1.sSensor.valor 		= digitalRead(BUTTON1_P);
	BOTON1.sSensor.Activo 		= true;
	BOTON1.sSensor.eSensor  	= S_DIGITAL;
	BOTON1.sSensor.name			= "BOTÓN 1";
	BOTON1.sSensor.valor_Df 	= BOTON1.sSensor.valor;

	BOTON2.sSensor.pin 			= BUTTON2_P;
	BOTON2.sSensor.valor 		= digitalRead(BUTTON2_P);
	BOTON2.sSensor.Activo 		= true;
	BOTON2.sSensor.eSensor  	= S_DIGITAL;
	BOTON2.sSensor.name			= "BOTÓN 2";
	BOTON2.sSensor.valor_Df 	=  BOTON2.sSensor.valor; 

	BTN_OPT.sSensor.pin 		= BTN_OPT_P;
	BTN_OPT.sSensor.valor 		= digitalRead(BTN_OPT_P);
	BTN_OPT.sSensor.Activo 		= true;
	BTN_OPT.sSensor.eSensor 	= S_DIGITAL;
	BTN_OPT.sSensor.name		= "Pulsador Optocoplado";
	BTN_OPT.sSensor.valor_Df	= BTN_OPT.sSensor.valor ;

	RELE_INTERRUPTOR.sActuador.pin = RELE_INTERRUPTOR_P;
	RELE_INTERRUPTOR.sActuador.valor = 0;
	RELE_INTERRUPTOR.sActuador.Activo = true;
	RELE_INTERRUPTOR.sActuador.eActuador = A_DIGITAL;
	RELE_INTERRUPTOR.sActuador.name = "Rele Interruptor";
}

void DomoBoard::leerAllSensor(void){
	leerSensor(&BOTON1);
	leerSensor(&BOTON2);
	leerSensor(&BTN_OPT);
}


void  DomoBoard::leerSensor(ptsSensor *Sensor){
	int valor = 0;

	if(Sensor->sSensor.Activo){
		switch (Sensor->sSensor.eSensor)
		{
		case S_DIGITAL:
			valor = digitalRead(Sensor->sSensor.pin);
			break;

		case S_ANALOGICO:
			valor = analogRead(Sensor->sSensor.pin);
			break;
		}

		if(Sensor->sSensor.valor != valor)
		{
			Sensor->sSensor.valor = valor;

			//Si hay un cambio en el estado del sensor llamamos a la aplicaci�n asociada
			if(Sensor->SensorEvent != NULL)
				Sensor->SensorEvent(&(Sensor->sSensor));
		}
	}
}


void  DomoBoard::activarActuador(ptsActuador *Actuador, int valor){
	if(Actuador->sActuador.Activo){
		Actuador->sActuador.valor = valor;
		switch (Actuador->sActuador.eActuador){
			case S_DIGITAL:
				digitalWrite(Actuador->sActuador.pin, valor);
				break;
			case S_ANALOGICO:
				analogWrite(Actuador->sActuador.pin, valor);
				break;
		}
	}
}


DomoBoard domoboard;

