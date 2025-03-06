// Do not remove the include below
#include 	"P1_DomoBoard_Pulsadores.h"
#include	"config_practicas.h"

#define 	LOOP_TMP		10		//miliseconds
#define 	HEADER_BYTE     '#'  	// Byte de cabecera

byte 	cmdSerial[2];

void readCommand();

//The setup function is called once at startup of the sketch
void setup()
{
	config_practica1_apt_1();

	Serial.begin(115200);

	Serial.println("P1 DomoBoard Pulsadores");

	//Serial.flush();
}

// The loop function is called in an endless loop
void loop()
{
	static uint32_t tmp_loop = 0;

	if((millis()-tmp_loop) > LOOP_TMP){
		tmp_loop = millis();

		domoboard.leerAllSensor();

		readCommand();
	}
}

void foundCommand(){
	switch(cmdSerial[1]){
		case '1':
			config_practica1_apt_1();
			Serial.println("Apartado 1 Seleccionado");
			break;
		case '2':
			config_practica1_apt_2();
			Serial.println("Apartado 2 Seleccionado");
			break;
		case '3':
			config_practica1_apt_3();
			Serial.println("Apartado 3 Seleccionado");
			break;
	}
}

void readCommand(){

	while(Serial.available()){
		byte c = Serial.read();

		// Desplazar los bytes en el buffer para mantener los últimos leídos
		for (uint8_t i = 0; i < sizeof(cmdSerial) - 1; i++) {
			cmdSerial[i] = cmdSerial[i + 1];
		}
		
		cmdSerial[sizeof(cmdSerial) - 1] = c; // Insertar el nuevo byte

		// Verificar si comando encontrado
		if ((cmdSerial[0] == HEADER_BYTE)) {
		
			//Comprueba si el comando es correcto
			if(cmdSerial[1] == '1' || cmdSerial[1] == '2' || cmdSerial[1] == '3'){
				foundCommand();
			}
		}
	}

}
