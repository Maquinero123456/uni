/*
 * cfg_loop.h
 *
 *  Created on: 1 mar 2023
 *      Author: jctejero
 */

#ifndef CFG_LOOP_H_
#define CFG_LOOP_H_

#include "Arduino.h"

/************** Scheduler ******************/
#define time_base_loop		10			// Time cycle in milliseconds
#define num_phases			100			// Number of phases

uint16_t phase_loop=0;
uint32_t tmr_loop = 0;

#define EXECUTELOOP()	if((millis()-tmr_loop) >= time_base_loop )
#define UPDATELOOP()	tmr_loop = millis();	\
						phase_loop = (phase_loop + 1) % num_phases


#define	LOOP_x10ms(n)	if (!(phase_loop % n))
#define	LOOP_x100ms		if (!(phase_loop % 10))
#define	LOOP_x1s		if (!(phase_loop % 100))



#endif /* CFG_LOOP_H_ */
