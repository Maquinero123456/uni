/*
 * Gest_Modbus.cpp
 *
 *  Created on: 16/05/2014
 *      Author: jctejero
 *
 *  Modified on: 16/03/2025
 */

/****************************************************************************/
/***        Include files                                                 ***/
/****************************************************************************/
#include	<Arduino.h>
#include	"Gest_Modbus.h"
#include 	"utils_domoBoard.h"
#include	"debuglog.h"

/****************************************************************************/
/***        Variables Locales                                             ***/
/****************************************************************************/
/* First step MBS: create an instance */
ModbusSlave mbs;

/***************************************************
 * Definición Bancos de registros usados en ModBus *
 ***************************************************/
//uint16_t	Cregs[MB_O_COILS];		//Registros para "Dicrete Output Coils"

/****************************************************************************/
/***                 Definición de funciones                              ***/
/****************************************************************************/
void writecoil(unsigned int addrReg);
void readcoil(unsigned int addrReg);

/****************************************************************************/
/***                 Functions                                            ***/
/****************************************************************************/
void Init_RTU_Modbus()
{
	/* configure modbus communication
	 * 115200 bps, 8E1, two-device network */
	/* Second step MBS: configure */
	/* the Modbus slave configuration parameters */
	const unsigned char 	SLAVE 	= ADDR_SLAVE;		//Address SLAVE
	const long 				BAUD 	= SERIAL_BPS;
	const char 				PARITY 	= SERIAL_PARITY;
	const char 				TXENPIN = 0; //EN_485;

	//Inicialmente configuramos 485 para recibir
	/*
	if(TXENPIN != 0)
		digitalWrite(EN_485, LOW);
	else digitalWrite(EN_485, HIGH);
	*/

	//Para la conexión 485/ModBus usamos
	Serial485 = &Serial;

	//We configure the ModBus Register Banks
	mbs.set_BankCoils(Cregs, MB_O_COILS);
	mbs.set_DigitalBankCoils(ICregs, MB_I_COILS);

	mbs.configure(SLAVE,BAUD,PARITY,TXENPIN);
}

/*
 *
 */

 void writecoil(){

	for (int addrReg = 0; addrReg < MB_O_COILS; addrReg++) {		
		switch (addrReg) {
		case MB_RELE:
			mbDomoboard.setActuator(mbDomoboard.RELE.actuator, Cregs[MB_RELE] != 0x00);
			break;

		case MB_TRIAC:
			mbDomoboard.setActuator(mbDomoboard.TRIAC.actuator, Cregs[MB_TRIAC] != 0x00);
			break;	
		}
	}

}

void readcoil(){
	for(int addrReg = 0; addReg<MB_I_COILS; addrReg++){
		switch (addrReg){
		case MB_BOTON_1:
			mbDomoboard.leerSensor(mbDomoboard.BOTON1);
			break;

		case MB_BOTON_2:
			mbDomoboard.leerSensor(mbDomoboard.BOTON2);
			break;	
		
		case MB_BTN_OPT:
			mbDomoboard.leerSensor(mbDomoboard.MB_BTN_OPT);
			break;
		}	
		}
	}


void RTU_ModBus()
{
	if(mbs.update()){
		writecoil();
		readcoil();
	}
}

