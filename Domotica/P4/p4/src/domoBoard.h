/*
 * domoBoard.h
 *
 *  Created on: 09/03/2015
 *      Author: jctejero
 */

#ifndef DOMOBOARD_H_
#define DOMOBOARD_H_

/****************************************************************************/
/***        Include files                                                 ***/
/****************************************************************************/
#include	"Arduino.h"
#include	"types.h"
#include 	"QueueList.h"


/****************************************************************************/
/***        Type Definitions                                              ***/
/****************************************************************************/


typedef enum
{
	S_DIGITAL,
	S_ANALOGICO
}teSensor;

typedef struct
{
	byte	pin;
	bool	estado;
}tsActuator;

typedef struct
{
	byte					pin;			//Pin asignado al sensor
	int	    				valor;			//Valor leido
	int						valor_Df;		//Valor Sensor por defecto
	bool					Activo;			//Activar/Desactivas sensor
	teSensor				eSensor;		//Tipos de sensor ANALÓGICO/DIGITAL
	int						Aux;			//Variables para ser usadas en la gestión del sensor
	String					name;
	QueueList<tsActuator *>	actuadores;
	TNotifyEvent			SensorEvent;
}tsSensor, *ptsSensor;

/****************************************************************************/
/***        Macro Definitions                                             ***/
/****************************************************************************/
#define 	BUTTON1_P  				3   	   	// Pulsador 1
#define 	BUTTON2_P  				10        	// Pulsador 2
#define 	BTN_OPT_P		    	11 	    	// Entrada Optocoplada

#define 	RELE_P					4			// RELE 1
#define 	TRIAC_P					7			// Triac

/****************************************************************************/
/***        Exported Class                                                ***/
/****************************************************************************/
class DomoBoard
{
private:


public:

	DomoBoard(); 						//Constructor

	//Sensores
	tsSensor		BOTON1;
	tsSensor		BOTON2;
	tsSensor 		BTN_OPT;

	//Salidas
	tsActuator		RELE;
	tsActuator		TRIAC;

	void 			leerAllSensor(void);
	static  void 	leerSensor(ptsSensor Sensor);
	static  void	setActuator(tsActuator *Actuator, bool val);
	static 	void	setAllActuators(ptsSensor *Sensor);
};

/*********************************************************************/
/***                    Prototipos de funciones                    ***/
/*********************************************************************/
void leeSensores(void);

/****************************************************************/
/***                    Exported Variables                    ***/
/****************************************************************/
extern DomoBoard domoboard;


#endif /* DOMOBOARD_H_ */
