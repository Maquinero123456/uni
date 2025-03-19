/*
 * debug.h
 *
 *  Created on: 20 nov. 2018
 *      Author: jctejero
 */

#ifndef IOT_UTILS_DEBUGLOG_H_
#define IOT_UTILS_DEBUGLOG_H_

/*********************************************************************
*                          Macros Definitions                        *
**********************************************************************/
//#define RELEASE  // Comment to enable debug output
//#include "Arduino.h"

#define DBG_OUTPUT_PORT Serial

//#define	RELEASE

#ifndef RELEASE
#define DEBUGLOG(...)			{char buf[100];sprintf(buf, __VA_ARGS__);DBG_OUTPUT_PORT.print(buf);}
#define DEBUGLOGLN(...)			{char buf[100];sprintf(buf, __VA_ARGS__);DBG_OUTPUT_PORT.println(buf);}

#define DEBUG(...)   			DBG_OUTPUT_PORT.print(__VA_ARGS__)
#define DEBUGLN(...)    		DBG_OUTPUT_PORT.println(__VA_ARGS__)
#define DEBUGF(fmt)     		DBG_OUTPUT_PORT.print(F(fmt))
#define DEBUGLNF(fmt)   		DBG_OUTPUT_PORT.println(F(fmt)) //Printing text using the F macro


#else
#define DEBUGLOG(...)
#define DEBUGLOGLN(...)
#define DEBUG(...)
#define DEBUGLN(...)
#define DEBUGF(...)
#define DEBUGLNF(...)
#endif




#endif /* IOT_UTILS_DEBUGLOG_H_ */
