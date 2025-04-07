package Utilidades.modbus;

import CommTransport.CommTransport;
import Utilidades.threadpool.DefaultExecutorSupplier;
import modbus.Const_Modbus;
import modbus.Modbus;
import practicas_eldom.config.MB_Registers.TSwitchState;

public class ModBus_Communications {
	
	public static void writeCoil(int SlaveAddress, int ModBusRegister, TSwitchState coilState, CommTransport sn_Transport) {
		
		String[] args = {Integer.toString(SlaveAddress), String.valueOf(Const_Modbus.WRITE_COIL), 
				Integer.toString(ModBusRegister), 
				TSwitchState.ToNumberString(coilState)};
		
		//Iniciamos Comunicación
		InitModbusComunication(args, sn_Transport);	
	}
	
	public static int[] readCoilInput(int SlaveAddress, int numeroDeSensores, CommTransport sn_Transport) {
		
		String[] args = {Integer.toString(SlaveAddress), String.valueOf(Const_Modbus.READ_COILS), String.valueOf(0), String.valueOf(numeroDeSensores)};
		int[] salidas = new int[3];
		//Iniciamos Comunicación
		InitModbusComunication(args, sn_Transport, salidas);
		return salidas;
	}
	
	public static void InitModbusComunication(String[] args, CommTransport sCon) {
		
		//Iniciamos Comunicación
		
		DefaultExecutorSupplier.getInstance().forBackgroundTasks().execute(new Runnable() {

			@Override
			public void run() {
				Modbus.InitComunication(args, sCon);
			}
			
		});	
	}
	
public static void InitModbusComunication(String[] args, CommTransport sCon, int[] values) {
		
		//Iniciamos Comunicación
		
		DefaultExecutorSupplier.getInstance().forBackgroundTasks().execute(new Runnable() {

			@Override
			public void run() {
				Modbus.InitComunication(args, sCon, values);
			}
			
		});	
	}
}
