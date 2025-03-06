/*
 * utils_domoBoard.cpp
 *
 *  Created on: 10/03/2015
 *      Author: jctejero
 */

/****************************************************************************/
/***        Include files                                                 ***/
/****************************************************************************/
#include "utils_domoBoard.h"
#include "HardwareSerial.h"

void Interruptor(tsSensor *Sensor)
{
	if(Sensor->valor_Df == Sensor->valor){
		Serial.print(Sensor->name);
		Serial.print(" : Interruptor --> ");
		if(Sensor->Aux == OFF){
			Sensor->Aux = ON;
			Serial.println("ON");
		}else{
			Sensor->Aux = OFF;
			Serial.println("OFF");
		}
	}
}

void Pulsado_Soltado(tsSensor *Sensor){
	Serial.print(Sensor->name);
	if(Sensor->valor_Df == Sensor->valor){
		Serial.println(" --> Soltado");
	}else{
		Serial.println(" --> Pulsado");
	}
}

void conmutador(tsSensor *Sensor)
{
	static int valor = OFF;

	if(Sensor->valor_Df == Sensor->valor){
		Serial.print("Conmutador --> ");
		if(valor == OFF){
			valor = ON;
			Serial.println("ON");
		}else{
			valor = OFF;
			Serial.println("OFF");
		}
	}
}





