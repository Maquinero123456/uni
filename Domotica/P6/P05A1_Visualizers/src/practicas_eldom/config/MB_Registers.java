package practicas_eldom.config;

public class MB_Registers {
	
	public enum ModBusRegisters {
		
		//Discrete Output Coils
		MB_RELE(0),
		MB_TRIAC(1),
		MB_O_COILS(2);
		
		int reg;
		
		ModBusRegisters(int rg){
			reg = rg;
		}
		
		public int getReg(){
			return reg;
		}
	}
	
	public enum ModBusDiscreteInputRegisters {
	    
	    // Discrete Input Registers
	    MB_BOTON_1(0),
	    MB_BOTON_2(1),
	    MB_BTN_OPT(2);
	    
	    private int reg;         // Registro Modbus
	    private boolean estado;  // Estado del registro (true/false)
	    
	    // Constructor
	    ModBusDiscreteInputRegisters(int rg){
	        this.reg = rg;
	        this.estado = false; // Inicializamos el estado como false por defecto
	    }
	    
	    // Método para obtener el número del registro
	    public int getReg() {
	        return reg;
	    }
	    
	    // Método para obtener el estado actual del registro
	    public boolean getEstado() {
	        return estado;
	    }
	    
	    // Método para actualizar el estado del registro
	    public void setEstado(boolean estado) {
	        this.estado = estado;
	    }
	    
	    // Método para cambiar el estado (toggle)
	    public void toggleEstado() {
	        this.estado = !this.estado;
	    }

	    // Método que devuelve un texto descriptivo del estado
	    public String getEstadoString() {
	        return estado ? "Activo" : "Inactivo";
	    }
	}

	
	public enum TSwitchState { 
		OFF, 
		ON,  
		TOGGLE;
		
		public static String ToNumberString(TSwitchState x) {
	        switch(x) {
	        case OFF:
	            return "0";
	        case ON:
	        	return "1";
	        case TOGGLE:
	        	return "2";
	        default:
	        	return "0";
	        }
	    }
		
	}
	
}
