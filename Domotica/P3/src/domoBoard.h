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

/****************************************************************************/
/***        Type Definitions                                              ***/
/****************************************************************************/
typedef enum
{
	S_DIGITAL,
	S_ANALOGICO
}teSensor;

typedef enum
{
	A_DIGITAL,
	A_ANALOGICO
}teActuador;

typedef struct
{
	byte		pin;			//Pin asignado al sensor
	int	    	valor;			//Valor leido
	int			valor_Df;		//Valor Sensor por defecto
	bool		Activo;			//Activar/Desactivas sensor
	teSensor	eSensor;		//Tipos de sensor ANALÓGICO/DIGITAL
	int			Aux;			//Variables para ser usadas en la gestión del sensor
	String		name;
}tsSensor;

typedef struct
{
	byte		pin;			//Pin asignado al sensor
	int	    	valor;			//Ultimo valor escrito
	bool		Activo;			//Activar/Desactivas sensor
	teActuador	eActuador;		//Tipos de sensor ANALÓGICO/DIGITAL
	int			Aux;			//Variables para ser usadas en la gestión del sensor
	String		name;
}tsActuador;

typedef void (*TSensorEvent)(tsSensor *);
typedef void (*TActuadorEvent)(tsActuador *);

typedef struct
{
	tsSensor			sSensor;
	TSensorEvent		SensorEvent;	//Evento para aplicaci�n asociada
}ptsSensor;

typedef struct
{
	tsActuador			sActuador;
	TActuadorEvent		ActuadorEvent;	//Evento para aplicaci�n asociada
}ptsActuador;

/****************************************************************************/
/***        Macro Definitions                                             ***/
/****************************************************************************/
#define 	BUTTON1_P  			3   	   	// Pulsador 1
#define 	BUTTON2_P  			10        	// Pulsador 2
#define		RELE_INTERRUPTOR_P	4			// Rele interruptor
#define 	BTN_OPT_P    		11 	    	// Entrada Optocoplada

/****************************************************************************/
/***        Exported Class                                                ***/
/****************************************************************************/
class DomoBoard
{
private:


public:

	DomoBoard(); 						//Constructor

	ptsSensor		BOTON1;
	ptsSensor		BOTON2;
	ptsSensor 		BTN_OPT;
	ptsActuador		RELE_INTERRUPTOR;

	void 	leerAllSensor(void);
	static  void	leerSensor(ptsSensor *Sensor);
	static  void	activarActuador(ptsActuador *Actuador, int valor);
};

extern DomoBoard domoboard;


#endif 