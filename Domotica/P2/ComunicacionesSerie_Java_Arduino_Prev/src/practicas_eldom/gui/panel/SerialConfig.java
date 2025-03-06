package practicas_eldom.gui.panel;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComboBox;

import Utilidades.OKCancelPanel;
import Utilidades.ConfigUtilities;

import java.awt.Font;
import java.util.Properties;

import javax.swing.DefaultComboBoxModel;


@SuppressWarnings("serial")
public class SerialConfig extends JDialog {
	private JPanel MainPanel = null;  //  @jve:decl-index=0:visual-constraint="10,55"
	private OKCancelPanel okCancelPanel = null;
	@SuppressWarnings("rawtypes")
	private JComboBox cb_BaudRate;
	@SuppressWarnings("rawtypes")
	private JComboBox cb_DataBits;
	@SuppressWarnings("rawtypes")
	private JComboBox cb_StopBits;
	@SuppressWarnings("rawtypes")
	private JComboBox cb_Parity;
	
	private String	fileName;
	
	public Properties configApp;
	

	public SerialConfig(Properties config, String File) {
		this.configApp = config;
		this.fileName = File;
		this.setTitle("Configurar Conexión Puerto Serie");		
		this.setContentPane(getMainPanel());
		
		cb_BaudRate.setSelectedIndex(findItem(cb_BaudRate, configApp.getProperty("BaudRate")));
		cb_DataBits.setSelectedIndex(findItem(cb_DataBits, configApp.getProperty("Databits")));
		cb_StopBits.setSelectedIndex(findItem(cb_StopBits, configApp.getProperty("Stopbits")));
		cb_Parity.setSelectedIndex(findItem(cb_Parity, configApp.getProperty("Parity")));
		//cb_Parity.setSelectedIndex(Integer.parseInt(configApp.getProperty("Parity")));
		
		
        this.setSize(new Dimension(342, 267));
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int X = (screenSize.width - this.getWidth())/2;
        int Y = (screenSize.height - this.getHeight())/2;
        this.setLocation(X, Y);
        
        this.setModal(true);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private JPanel getMainPanel() 
	{
		if (MainPanel == null) 
		{
			MainPanel = new JPanel();
			MainPanel.setLayout(null);			
			MainPanel.setSize(new Dimension(532, 165));			
			MainPanel.add(getOKCancelPanel(), null);
			
			cb_BaudRate = new JComboBox();
			cb_BaudRate.setModel(new DefaultComboBoxModel(new String[] {"110", "300", "600", "1200", "2400", "4800", "9600", "14400", "19200", "38400", "56000", "57600", "115200", "128000", "256000"}));
			cb_BaudRate.setSelectedIndex(5);
			cb_BaudRate.setBounds(190, 25, 102, 20);
			MainPanel.add(cb_BaudRate);
			
			cb_DataBits = new JComboBox();
			cb_DataBits.setModel(new DefaultComboBoxModel(new String[] {"5", "6", "7", "8"}));
			cb_DataBits.setBounds(190, 62, 102, 20);
			MainPanel.add(cb_DataBits);
			
			cb_StopBits = new JComboBox();
			cb_StopBits.setModel(new DefaultComboBoxModel(new String[] {"1", "1.5", "2"}));
			cb_StopBits.setBounds(190, 101, 102, 20);
			MainPanel.add(cb_StopBits);
			
			cb_Parity = new JComboBox();
			cb_Parity.setModel(new DefaultComboBoxModel(new String[] {"None", "Par", "Impar"}));
			cb_Parity.setBounds(190, 140, 102, 20);
			MainPanel.add(cb_Parity);
			
			JLabel lblNewLabel = new JLabel("Baud Rate");
			lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
			lblNewLabel.setBounds(27, 28, 111, 17);
			MainPanel.add(lblNewLabel);
			
			JLabel lblAtaBits = new JLabel("Data Bits");
			lblAtaBits.setFont(new Font("Times New Roman", Font.BOLD, 16));
			lblAtaBits.setBounds(27, 65, 111, 17);
			MainPanel.add(lblAtaBits);
			
			JLabel lblStopBits = new JLabel("Stop Bits");
			lblStopBits.setFont(new Font("Times New Roman", Font.BOLD, 16));
			lblStopBits.setBounds(27, 104, 111, 17);
			MainPanel.add(lblStopBits);
			
			JLabel lblParityBits = new JLabel("Parity Bits");
			lblParityBits.setFont(new Font("Times New Roman", Font.BOLD, 16));
			lblParityBits.setBounds(27, 143, 111, 17);
			MainPanel.add(lblParityBits);			
		}
		return MainPanel;
	}
	
	/**
	 * This method initializes OKButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private OKCancelPanel getOKCancelPanel() {
		if (okCancelPanel == null) {
			okCancelPanel = new OKCancelPanel(MainPanel);
			okCancelPanel.addMouseListener(new java.awt.event.MouseAdapter() 
			{   				
				public void mouseClicked(java.awt.event.MouseEvent e) 
				{	
					if (e.getComponent().getName() == "OK")
					{	
						writeConfig();						
						closeFrame();						
					}
					else if (e.getComponent().getName() == "Cancel")
						closeFrame();					
				}
			});			
		}
		return okCancelPanel;
	}
	
	private void closeFrame()
	{
		this.dispose();
	}
	
	private void writeConfig(){
		configApp.setProperty("BaudRate", cb_BaudRate.getSelectedItem().toString());
		configApp.setProperty("Databits", cb_DataBits.getSelectedItem().toString());
		configApp.setProperty("Stopbits", cb_StopBits.getSelectedItem().toString());
		//configApp.setProperty("Parity", cb_Parity.getSelectedItem().toString());
		String Parity = String.valueOf(cb_Parity.getSelectedIndex());
		configApp.setProperty("Parity", Parity);
		ConfigUtilities.saveConfig(configApp, fileName);
	}
	
	private int findItem(@SuppressWarnings("rawtypes") JComboBox model, String val){
		int size = model.getItemCount();
		for(int i=0;i<size;i++){
			if(model.getItemAt(i).equals(val)){
				return  i;
			}
		}
		return -1;
	}
}
