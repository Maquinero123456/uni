package practicas_eldom.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import Utilidades.ArrayUtilities;
import jssc.SerialPortList;
import practicas_eldom.ConstantesApp;
import practicas_eldom.comm.serie.CommSerie;
import practicas_eldom.config.ConfigProperties;
import practicas_eldom.config.ConfigurationConnect;
import practicas_eldom.gui.panel.Console;
import practicas_eldom.gui.panel.SerialConfig;
import practicas_eldom.messages.Messages;
import practicas_eldom.messages.MessagesListener;

import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class P02_ElDom_MainWindow {
	
	private 	boolean 				doExitOnRequest = true;
	private 	JCheckBoxMenuItem 		ConsoleSet;	
	private     Console					serialConsole;
	private 	JMenu 					mnMenuSerie;
	private		JLabel 					statusBar_msgLeft;
//	private 	SerialPort 				serialPort;	
	
	private 	JFrame 					window;
	private		CommSerie				commSerie = new CommSerie();
	
	private 	ConfigurationConnect	configConnect;
	
	public P02_ElDom_MainWindow() {		
		
		configConnect = new ConfigurationConnect();
		
		// Make sure we have nice window decorations
		JFrame.setDefaultLookAndFeelDecorated(true);

		Rectangle maxSize = GraphicsEnvironment.getLocalGraphicsEnvironment()
								.getMaximumWindowBounds();
		
		commSerie.setMessagesListener(new MessagesListener() {

			@Override
			public void msglog(String msg) {
				log(msg);
			}
			
		});
		
		window = new JFrame(ConstantesApp.WINDOW_TITLE);
		window.setAlwaysOnTop(true);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);		
		
		window.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {		
				Dimension windowSize = window.getSize();
				
				configConnect.setProperty(ConfigProperties.PROP_WINSIZE, windowSize.width +", "+windowSize.height);		
				
				serialConsoleLocation();	
			}
			@Override
			public void componentMoved(ComponentEvent arg0) {
				Point WinLocation = window.getLocation();
				
				configConnect.setProperty(ConfigProperties.PROP_WINLOCATION, WinLocation.x + ", " + WinLocation.y);
				
				serialConsoleLocation();
			}			
		});
				
		window.setLocationByPlatform(true);
		if (maxSize != null) {
			window.setMaximizedBounds(maxSize);
		}
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setToolTipText("");
		menuBar.setForeground(Color.WHITE);
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.setMargin(new Insets(5, 5, 5, 5));
		menuBar.setBackground(Color.ORANGE);
		window.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu(Messages.ARCHIVO);
		menuBar.add(mnNewMenu);

		JMenuItem ExitItem = new JMenuItem(Messages.SALIR);
		ExitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
		mnNewMenu.add(ExitItem);
		
		//=======================================================
		//             Menú COMUNICACIONES
		//=======================================================
		JMenu mnNewMenu_1 = new JMenu(Messages.COMUNICACIONES);
		mnNewMenu_1.setForeground(Color.BLACK);
		mnNewMenu_1.setBackground(Color.BLACK);
		menuBar.add(mnNewMenu_1);
		
		/********************************
		 * Generación del submenu Serie *
		 ********************************/
		genMenuSerie(mnNewMenu_1);
		
		//Monitor Serie
		ConsoleSet = new JCheckBoxMenuItem(Messages.CONSOLE);
		mnNewMenu_1.add(ConsoleSet);
		
		ConsoleSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent paramActionEvent) {
				AbstractButton aButton = (AbstractButton) paramActionEvent.getSource();
		        boolean selected = aButton.getModel().isSelected();
		        
		        if(selected){
		        	serialConsole = new Console();
		        	serialConsole.addActionListener(new ActionListener() {
		    			public void actionPerformed(ActionEvent e) {
		    				ConsoleMessage(e.getActionCommand());
		    			}
		    		});
		        	serialConsole.setVisible(true);
		        	
		        	serialConsoleLocation();
		        }
		        else{ 
		        	serialConsole.Close();
		        }
		        
		        configConnect.setProperty(ConfigProperties.PROP_SERIALCONSOLE,String.valueOf(selected));
		        configConnect.SaveConfig();
			}
		});
		window.getContentPane().setLayout(new BorderLayout(0, 0));
		
		//-----------------------------------------------------
		//-------------  STATUS BAR
		//-----------------------------------------------------
		JPanel statusBar = new JPanel();
		window.getContentPane().add(statusBar, BorderLayout.SOUTH);
		
		statusBar_msgLeft = new JLabel(" " + "Good Day!", JLabel.LEFT);
		statusBar_msgLeft.setForeground(Color.black);
		statusBar.add(statusBar_msgLeft);
		
		JLabel welcomedate = new JLabel();
        welcomedate.setOpaque(true);//to set the color for jlabel
        welcomedate.setBackground(Color.black);
        welcomedate.setForeground(Color.WHITE);
        statusBar.add(welcomedate);
        
        statusBar.setLayout(new BorderLayout());
        statusBar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        statusBar.setBackground(Color.LIGHT_GRAY);
        statusBar.add(statusBar_msgLeft, BorderLayout.WEST);
        statusBar.add(welcomedate, BorderLayout.EAST);
        
      //display date time to status bar
        Timer timer = new Timer (1000, new ActionListener ()
		{
			public void actionPerformed(ActionEvent e)
		    {	java.util.Date now = new java.util.Date();
            String ss = DateFormat.getDateTimeInstance().format(now);
            welcomedate.setText(ss);
            welcomedate.setToolTipText("Welcome, Today is " + ss);
		    }
		});
		
		timer.start();
		
		statusBar_msgLeft.setText(Messages.PORTSELTOCOMM);
		
		//-----------------------------------------------------
		//-----------------------------------------------------
		
		JPanel panel_1 = new JPanel();
		window.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Selecciona modo de los botones:");
		lblNewLabel.setBounds(10, 11, 187, 14);
		panel_1.add(lblNewLabel);
		
		Map<String, String> valueMap = new LinkedHashMap<>();
        valueMap.put("Modo 1", "*1");
        valueMap.put("Modo 2", "*2");
        valueMap.put("Modo 3", "*3");
		
		JComboBox<String> comboBox = new JComboBox<>(valueMap.keySet().toArray(new String[0]));
		comboBox.setBounds(10, 32, 115, 22);
		// Evento cuando se selecciona un valor en el JComboBox
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedText = (String) comboBox.getSelectedItem(); // Nombre visible
                String valueToSend = valueMap.get(selectedText); // Valor real a enviar
                
                if (valueToSend != null) {
                	commSerie.sendMsg(valueToSend);  // Enviar el valor por el puerto serie
                    System.out.println("Enviado: " + valueToSend);
                }
            }
        });
		panel_1.add(comboBox);
		
	}
	
	public void genMenuSerie(JMenu mnMenu){
		mnMenuSerie = new JMenu(Messages.SERIE);
		
		mnMenuSerie.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				log(Messages.LOOKPORTS + "\n\s");
				SetMenu_SerialPorts(mnMenuSerie);
				SetMenu_PortConfig(mnMenuSerie);				
			}
		});
		
		SetMenu_SerialPorts(mnMenuSerie);
		SetMenu_PortConfig(mnMenuSerie);
		mnMenu.add(mnMenuSerie);

		mnMenu.addSeparator();
		
	}
	
	private void SetMenu_PortConfig(JMenu mnMenu) {
		mnMenu.addSeparator();
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Opciones Conexión Serie");
		mnMenu.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent paramActionEvent) {
				SerialConfig frame = new SerialConfig(configConnect.getProperties(), ConfigProperties.CONFIG_FILE);
				frame.addWindowListener(new WindowAdapter(){
					public void windowClosed(WindowEvent e)
					  {
						window.setAlwaysOnTop(true);
					  }
		
	            });
				window.setAlwaysOnTop(false);
				
		        frame.setVisible(true); //necessary as of 1.3
				frame.setAlwaysOnTop(true);
		        frame.toFront();
			}
		});
	}

	public int SetMenu_SerialPorts(JMenu mnMenu){
		  
		//Obtenemos los puertos Serie disponibles
		
		String[] ports = SerialPortList.getPortNames();
		
		if(ports.length == 0){
			mnMenu.setEnabled(false);
		}
		else
		{		
			mnMenu.setEnabled(true);
			mnMenu.removeAll();
		
			for(String port : ports){
				JRadioButtonMenuItem mntmPrueba = new JRadioButtonMenuItem(port);
				mnMenu.add(mntmPrueba);
				
				mntmPrueba.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					
						if(((JRadioButtonMenuItem)e.getSource()).isSelected()) {
							
							log(Messages.PORTSELTOCOMM+ e.getActionCommand() + ArrayUtilities.CRLF);
							
							statusBar_msgLeft.setText(ConstantesApp.STATUSBAR_MSGLEFT +  e.getActionCommand());
							
							commSerie.jsscOpenPort(e.getActionCommand(), configConnect.getProperties());
							
							//Deselecciona los radioButtons
							for (int i = 0 ; i <  mnMenuSerie.getItemCount()-1; i++) {
								if(((JRadioButtonMenuItem)mnMenuSerie.getItem(i) != ((JRadioButtonMenuItem)e.getSource())) && (mnMenuSerie.getItem(i) != null))									
									((JRadioButtonMenuItem)mnMenuSerie.getItem(i)).setSelected(false);
							}
						}
						else {
							statusBar_msgLeft.setText(ConstantesApp.STATUSBAR_MSGLEFT);
							try {
								commSerie.jsscClosePort();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					}
				});
			}
		}
				
		return ports.length;
	}
	
	
	private void log(String Msg){
		if((serialConsole == null)||(!serialConsole.isVisible())){
			System.out.print(Msg);
		}
		else
			serialConsole.log(Msg);
	}
	
	private void exit() {
		if (doExitOnRequest) {
			//stop();
			System.exit(0);
		} else {
			Toolkit.getDefaultToolkit().beep();
		}
	}


	private void serialConsoleLocation(){
		if(serialConsole != null){
			if(serialConsole.isVisible()){ 
				Dimension windowSize = window.getSize();
				serialConsole.setSize(serialConsole.getWidth(), windowSize.height);
    	
				Point WinLocation = window.getLocation(); 
    	
				serialConsole.setLocation(WinLocation.x + windowSize.width, WinLocation.y);
			}
		}
	}

	
	private void ConsoleMessage(final String message){
		if(message == "Close"){
			ConsoleSet.setState(false);
		}
	}
	
	
	public void start() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				//String s = configApp.getProperty("windowBounds");
				String s = configConnect.getProperty(ConfigProperties.PROP_WINSIZE);
				String l = configConnect.getProperty(ConfigProperties.PROP_WINLOCATION);
				
				window.setVisible(true);
				
				if(s != null){
					String[] r = s.split(", ");
					window.setSize(new Dimension(Integer.parseInt(r[0]),
	                        Integer.parseInt(r[1])));
				} else window.setSize(new Dimension(799, 535)); 
				
				if(l != null){
					String[] r = l.split(", ");

					window.setLocation(Integer.parseInt(r[0]),
	                        Integer.parseInt(r[1]));
				} 
				
				if(Boolean.valueOf(configConnect.getProperty(ConfigProperties.PROP_SERIALCONSOLE))){
					ConsoleSet.doClick();
				}
			}
		});
	}
}
