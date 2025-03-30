/*
 * config_practicas.cpp
 *
 *  Created on: 10/03/2015
 *      Author: jctejero
 */

/****************************************************************************/
/***        Include files                                                 ***/
/****************************************************************************/
#include	"config_practicas.h"

/****************************************************************************/
/***                 Functions                                            ***/
/****************************************************************************/
void clean_queues(){
	domoboard.BOTON1.actuadores.clear();
	domoboard.BOTON2.actuadores.clear();
	domoboard.BTN_OPT.actuadores.clear();
}

void config_practica1_apt_1(){
	domoboard.BOTON1.SensorEvent = Pulsado_Soltado;

	domoboard.BOTON2.SensorEvent = Pulsado_Soltado;

	domoboard.BTN_OPT.SensorEvent = Pulsado_Soltado;
}

void config_practica1_apt_2(){
	domoboard.BOTON1.SensorEvent = Interruptor;
	domoboard.BOTON1.Aux = OFF;

	domoboard.BOTON2.SensorEvent = Interruptor;
	domoboard.BOTON2.Aux = OFF;

	domoboard.BTN_OPT.SensorEvent = Interruptor;
	domoboard.BTN_OPT.Aux = OFF;
}

void config_practica1_apt_3(){
	domoboard.BOTON1.SensorEvent = conmutador;

	domoboard.BOTON2.SensorEvent = conmutador;

	domoboard.BTN_OPT.SensorEvent = conmutador;
}

void config_practica3_apt_2(){
	domoboard.BOTON1.SensorEvent = conmutador_sal;

	domoboard.BOTON2.SensorEvent = conmutador_sal;

	domoboard.BTN_OPT.SensorEvent = conmutador_sal;
}

void config_practica4_apt_2(){
	clean_queues();
	domoboard.BOTON1.SensorEvent = Interruptor_triac;
	domoboard.BOTON1.actuadores.push(&domoboard.RELE);

	domoboard.BOTON2.SensorEvent = Interruptor_triac;
	domoboard.BOTON2.actuadores.push(&domoboard.TRIAC);

	domoboard.BTN_OPT.SensorEvent = Interruptor_triac;
}

void config_practica4_apt_3(){
	clean_queues();
	domoboard.BOTON1.SensorEvent = Pulsado_Soltado_Triac;
	domoboard.BOTON1.actuadores.push(&domoboard.RELE);

	domoboard.BOTON2.SensorEvent = Pulsado_Soltado_Triac;
	domoboard.BOTON2.actuadores.push(&domoboard.TRIAC);

	domoboard.BTN_OPT.SensorEvent = Pulsado_Soltado_Triac;
}

void config_practica4_apt_4(){
	clean_queues();
	domoboard.BOTON1.SensorEvent = conmutador_triac_rele;
	domoboard.BOTON1.actuadores.push(&domoboard.TRIAC);

	domoboard.BOTON2.SensorEvent = conmutador_triac_rele;
	domoboard.BOTON2.actuadores.push(&domoboard.TRIAC);

	domoboard.BTN_OPT.SensorEvent = conmutador_triac_rele;
	domoboard.BTN_OPT.actuadores.push(&domoboard.RELE);
}

void config_practica4_apt_5(){
	clean_queues();
	domoboard.BOTON1.SensorEvent = conmutador_triac_rele_simultaneo;
	domoboard.BOTON1.actuadores.push(&domoboard.TRIAC);
	domoboard.BOTON1.actuadores.push(&domoboard.RELE);

	domoboard.BOTON2.SensorEvent = conmutador_triac_rele_simultaneo;
	domoboard.BOTON2.actuadores.push(&domoboard.TRIAC);
	domoboard.BOTON2.actuadores.push(&domoboard.RELE);

	domoboard.BTN_OPT.SensorEvent = conmutador_triac_rele_simultaneo;
	domoboard.BTN_OPT.actuadores.push(&domoboard.TRIAC);
	domoboard.BTN_OPT.actuadores.push(&domoboard.RELE);
}
