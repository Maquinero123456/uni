package practicas_eldom.comm.serie;

import java.util.Properties;

import Utilidades.ConfigUtilities;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import practicas_eldom.config.ConfigProperties;
import practicas_eldom.messages.MessagesListener;

public class CommSerie {
	
	private 	SerialPort 			serialPort;	
	
	private 	MessagesListener	messagesListener = null;

	public void setMessagesListener(MessagesListener msgList) {
		messagesListener = msgList;
	}
	
	public void jsscClosePort() throws Exception {
		if (serialPort != null) {
			try {
				serialPort.removeEventListener();
				if (serialPort.isOpened()) {
					serialPort.closePort();
					if(messagesListener != null)
						messagesListener.msglog("Puerto serie cerrado\n");
				}
			} finally {
				serialPort = null;
			}
		}
	}

	public boolean jsscOpenPort(String port, Properties config) {
		
		serialPort = new SerialPort(port);
		try {			
			serialPort.openPort();
			
			serialPort.purgePort(SerialPort.PURGE_RXCLEAR);
	    
			ConfigUtilities.loadConfig(config, ConfigProperties.CONFIG_FILE);
	    
			serialPort.setParams(Integer.parseInt(config.getProperty("BaudRate")),
	    					Integer.parseInt(config.getProperty("Databits")),
	    					Integer.parseInt(config.getProperty("Stopbits")),
	    					Integer.parseInt(config.getProperty("Parity")));
	    
			serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | 
	                                  SerialPort.FLOWCONTROL_RTSCTS_OUT);

			serialPort.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);
		}
		catch (SerialPortException ex) {
			if(messagesListener != null)
				messagesListener.msglog("There are an error on writing string to port т: " + ex);
		}

		return false;
	}
	
	public void sendMsg(String data) {
		try {
            if (serialPort.isOpened()) {
                serialPort.writeString(data);
                if (messagesListener != null)
                    messagesListener.msglog("---- Enviado: " + data+" ----");
            } else {
                if (messagesListener != null)
                    messagesListener.msglog("---- El puerto no esta abierto ----");
            }
        } catch (SerialPortException ex) {
            if (messagesListener != null)
                messagesListener.msglog("---- Error writing to port: " + ex+" ----");
        }
	}
	
	private class PortReader implements SerialPortEventListener {

	    @Override
	    public void serialEvent(SerialPortEvent event) {
	        if(event.isRXCHAR() && event.getEventValue() > 0) {
	            try {
	                String receivedData = serialPort.readString(event.getEventValue());
	                
	                if(messagesListener != null)
	    				messagesListener.msglog(receivedData);
	                
	            }
	            catch (SerialPortException ex) {
	            	if(messagesListener != null)
	    				messagesListener.msglog("Error in receiving string from COM-port: " + ex);
	            		              
	            }
	        }
	    }

	}
}

