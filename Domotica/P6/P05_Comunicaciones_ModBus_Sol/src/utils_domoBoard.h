/*
 * utils_domoBoard.h
 *
 *  Created on: 10/03/2015
 *      Author: jctejero
 */

#ifndef UTILS_DOMOBOARD_H_
#define UTILS_DOMOBOARD_H_

/****************************************************************************/
/***        Include files                                                 ***/
/****************************************************************************/
#include	"Arduino.h"
#include	"DomoBoard/domoBoard.h"
#include    "DomoBoard/ModbusDomoboard.h"

/****************************************************************************/
/***        Macro Definitions                                             ***/
/****************************************************************************/
/*
enum PinState {
    OFF = LOW,
    ON = HIGH
};
*/
#define 	ON   				HIGH
#define     OFF					LOW

/****************************************************************************/
/***        Exported Functions                                            ***/
/****************************************************************************/
void Interruptor(void *Sensor);
void interruptor_sal(void *Sensor);
void Pulsado_Soltado(void *Sensor);
void Pulsado_Soltado_sal(void *Sensor);
void conmutador(void *Sensor);
void conmutador_sal(void *Sensor);

void mbInterruptor(void *mbSensor);


#endif /* UTILS_DOMOBOARD_H_ */
