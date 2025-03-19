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

void Interruptor(void *Sensor)
{
	if(((ptsSensor)Sensor)->valor_Df == ((ptsSensor)Sensor)->valor){
		Serial.print(((ptsSensor)Sensor)->name);
		Serial.print(" : Interruptor --> ");
		if(((ptsSensor)Sensor)->Aux == OFF){
			((ptsSensor)Sensor)->Aux = ON;
			Serial.println("ON");
		}else{
			((ptsSensor)Sensor)->Aux = OFF;
			Serial.println("OFF");
		}
	}
}

void Pulsado_Soltado(void *Sensor){
	ptsSensor sensor = reinterpret_cast<ptsSensor>(Sensor);

	Serial.print(sensor->name);
	if(sensor->valor_Df == sensor->valor){
		Serial.println(" --> Soltado");
	}else{
		Serial.println(" --> Pulsado");
	}
}

void conmutador(void *Sensor)
{
	static bool valor = OFF;

	ptsSensor sensor = reinterpret_cast<ptsSensor>(Sensor);

	if(sensor->valor_Df == sensor->valor){
		Serial.print("Conmutador --> ");
		if(valor == OFF){
			valor = ON;
			Serial.println("ON");
		}else{
			valor = OFF;
			Serial.println("OFF");
		}
	}

	sensor->Aux = valor;
}

void conmutador_sal(void *Sensor)
{
	conmutador(Sensor);

	//Actualiza Actuadores
	DomoBoard::setActuator(&domoboard.RELE, ((ptsSensor)Sensor)->Aux);
}

void Interruptor_triac(void *Sensor){
	Interruptor(Sensor);
	if(((ptsSensor)Sensor)->valor_Df == ((ptsSensor)Sensor)->valor){
		DomoBoard::setActuator(((ptsSensor)Sensor)->actuadores.peek(0b0), ((ptsSensor)Sensor)->Aux);
	}
}

void Pulsado_Soltado_Triac(void *Sensor){
	Interruptor(Sensor);
	if(((ptsSensor)Sensor)->valor_Df == ((ptsSensor)Sensor)->valor){
		DomoBoard::setActuator(((ptsSensor)Sensor)->actuadores.peek(0b0), ((ptsSensor)Sensor)->Aux);
	}
}

void conmutador_triac_rele(void *Sensor){
	conmutador(Sensor);
	DomoBoard::setActuator(((ptsSensor)Sensor)->actuadores.peek(0b0), ((ptsSensor)Sensor)->Aux);
}

void conmutador_triac_rele_simultaneo(void *Sensor){
	conmutador(Sensor);
	DomoBoard::setAllActuators(((ptsSensor)Sensor)->actuadores.peek(0b0), ((ptsSensor)Sensor)->Aux);
}




