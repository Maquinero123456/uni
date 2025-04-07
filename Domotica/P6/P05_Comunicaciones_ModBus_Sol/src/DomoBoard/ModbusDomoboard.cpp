/*
 * ModbusDomoboard.cpp
 *
 *  Created on: 11 mar. 2020
 *      Author: jctejero
 *
 *  Modified on: 17/03/2025
 */

#include "ModbusDomoboard.h"
#include "debuglog.h"

/****************************************************************************/
/***        Variables Locales                                             ***/
/****************************************************************************/

/***************************************************
 * Definición Bancos de registros usados en ModBus *
 ***************************************************/
uint16_t	Cregs[MB_O_COILS];		//Registros para "Dicrete Output Coils"
uint16_t	ICregs[MB_I_COILS];		//Registros para "Discrete Input Coils"

ModbusDomoboard mbDomoboard;

ModbusDomoboard::ModbusDomoboard():DomoBoard() {
	//*****  Initialize ModBus Sensors  ****

	//Initialize BOTON1 for ModBus
	BOTON1.Sensor = &(DomoBoard::BOTON1);
	BOTON1.Aux = LOW;
	BOTON1.mbRegs = ICregs[MB_BOTON_1];
	listmbSensors.push(&BOTON1);

	//Initialize BOTON1 for ModBus
	BOTON2.Sensor = &(DomoBoard::BOTON2);
	BOTON2.Aux = LOW;
	BOTON2.mbRegs = ICregs[MB_BOTON_2];
	listmbSensors.push(&BOTON2);


	//Initialize BOTON1 for ModBus
	BTN_OPT.Sensor = &(DomoBoard::BTN_OPT);
	BTN_OPT.mbRegs = &ICregs[MB_BTN_OPT];
	listmbSensors.push(&BTN_OPT);

	RELE.actuator = &(DomoBoard::RELE);
	RELE.mbRegs = &Cregs[MB_RELE];

	TRIAC.actuator = &(DomoBoard::TRIAC);
	TRIAC.mbRegs = &Cregs[MB_TRIAC];
}

void ModbusDomoboard::leerAllSensor(void){
	for(uint8_t i = 0; i < listmbSensors.count(); i++){
		leerSensor(listmbSensors.peek(i));
	}
}

void ModbusDomoboard::leerSensor(TpmbSensor Sensor){

	DomoBoard::leerSensor(Sensor->Sensor);

	if(Sensor->Aux != Sensor->Sensor->valor ){
		//Estado Sensor ha cambiado
		Sensor->Aux = Sensor->Sensor->valor;

		if(Sensor->mbSensorEvent != NULL){
			Sensor->mbSensorEvent(&(Sensor->Sensor));
		}
	}
	ICregs[Sensor->mbRegs]= Sensor->Aux;
}

void	ModbusDomoboard::Clear_SensorsConfiguration(){
	DomoBoard::Clear_SensorsConfiguration();

	for(uint8_t i = 0; i < listmbSensors.count(); i++){
		listmbSensors.peek(i)->mbActuators.clear();
		listmbSensors.peek(i)->mbSensorEvent = NULL;
	}	

}


void  ModbusDomoboard::setmbActuator(TmbActuator *Actuator, TStateDigitalDev val){
	bool newVal = (bool)val;

	if(val == TOGGLE){		
		newVal = (*Actuator->mbRegs) > 0 ? false : true;
	}

	if(*(Actuator->mbRegs) != newVal){
		*(Actuator->mbRegs) = newVal;

		setActuator(Actuator->actuator, *(Actuator->mbRegs));

	}
}

void ModbusDomoboard::manager_mbActuators(TmbActuators *Actuators, TStateDigitalDev val){
	TpmbActuator Actuator;
	for(int n = 0; n < Actuators->count(); n++){
		Actuator = Actuators->peek(n);

		setmbActuator(Actuator, val);
	}

}

void leeSensoresmb(void){
	mbDomoboard.leerAllSensor();
}
