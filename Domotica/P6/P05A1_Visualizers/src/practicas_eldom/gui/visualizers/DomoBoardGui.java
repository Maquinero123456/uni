package practicas_eldom.gui.visualizers;

import java.awt.Component;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;

import javax.swing.JPanel;
import javax.swing.Timer;

import CommTransport.CommTransport;
import Utilidades.modbus.ModBus_Communications;
import eu.hansolo.steelseries.extras.LightBulb;
import eu.hansolo.steelseries.extras.Led;
import practicas_eldom.config.MB_Registers;
import practicas_eldom.config.MB_Registers.TSwitchState;

import javax.swing.JLabel;

import java.awt.Font;

public class DomoBoardGui extends JPanel implements Visualizer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8619767299083215147L;
	
	private			Led					ledBoton1;
	private			Led					ledBoton2;
	private			Led					ledBtnOpt;
	private 		LightBulb 			lightBulb1;
	private 		LightBulb 			lightBulb2;
	private 		MouseAdapter 		ma_lightBulb;
	private final 	String 				category;
	private final 	boolean				isCategory = true;
	private final	CommTransport 		sn_Transport;
	private final 	String				address;
	
//	public App_Connection serialConnection;

	public DomoBoardGui(String category, String address, CommTransport sn_Transport) {
		
		super();
		
		this.category 		= category;
		this.address		= address;
		this.sn_Transport 	= sn_Transport;
		
		this.setLayout(null);
		
		ma_lightBulb = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				ONOFF_Bulb(((LightBulb)e.getComponent()));
			}
		};
		

        //TODO
        Timer updateLedsTimer = new Timer(500, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                	int[] valores = ModBus_Communications.readCoilInput(1, 3 , sn_Transport);
                	MB_Registers.ModBusDiscreteInputRegisters.MB_BOTON_1.setEstado(valores[0]==1);
                	MB_Registers.ModBusDiscreteInputRegisters.MB_BOTON_2.setEstado(valores[1]==1);
                	MB_Registers.ModBusDiscreteInputRegisters.MB_BTN_OPT.setEstado(valores[2]==1);
                	ledBoton1.setLedOn(MB_Registers.ModBusDiscreteInputRegisters.MB_BOTON_1.getEstado());
                	ledBoton2.setLedOn(MB_Registers.ModBusDiscreteInputRegisters.MB_BOTON_2.getEstado());
                	ledBtnOpt.setLedOn(MB_Registers.ModBusDiscreteInputRegisters.MB_BTN_OPT.getEstado());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        updateLedsTimer.start();
		
		
		ledBoton1 = new Led();
		ledBoton1.setToolTipText("Boton1");
		ledBoton1.setBounds(20, 150, 50, 50);
		this.add(ledBoton1);
		JLabel lblBoton1 = new JLabel("Boton 1");
		lblBoton1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblBoton1.setBounds(20, 120, 100, 50);
		add(lblBoton1);
		
		ledBoton2 = new Led();
		ledBoton2.setToolTipText("Boton 2");
		ledBoton2.setBounds(90, 150, 50, 50);
		this.add(ledBoton2);
		JLabel lblBoton2 = new JLabel("Boton 1");
		lblBoton2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblBoton2.setBounds(90, 120, 100, 50);
		add(lblBoton2);
		
		ledBtnOpt = new Led();
		ledBtnOpt.setToolTipText("BtnOpt");
		ledBtnOpt.setBounds(160, 150, 50, 50);
		this.add(ledBtnOpt);
		JLabel lblBtnOpt = new JLabel("Btn Opt");
		lblBtnOpt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblBtnOpt.setBounds(160, 120, 100, 50);
		add(lblBtnOpt);
		
		lightBulb1 = new LightBulb();
		lightBulb1.setToolTipText("REL\u00C9");
		lightBulb1.setGlowColor(Color.YELLOW);
		lightBulb1.setBounds(10, 11, 78, 78);
		lightBulb1.addMouseListener(ma_lightBulb);
		this.add(lightBulb1);
		
		lightBulb2 = new LightBulb();
		lightBulb2.setToolTipText("TRIAC");
		lightBulb2.setGlowColor(Color.RED);
		lightBulb2.setBounds(98, 11, 78, 78);
		lightBulb2.addMouseListener(ma_lightBulb);
		this.add(lightBulb2);
		
		JLabel lblNewLabel = new JLabel("RELÉ");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel.setBounds(18, 92, 63, 24);
		add(lblNewLabel);
		
		JLabel lblRel = new JLabel("TRIAC");
		lblRel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblRel.setBounds(106, 92, 63, 24);
		add(lblRel);
	}
	
	private void ONOFF_Bulb(LightBulb lightBulb){
		TSwitchState vBulb;
		Integer vReg;
		
		lightBulb.setOn(!lightBulb.isOn());
		
		if(lightBulb.isOn()) vBulb = MB_Registers.TSwitchState.ON;
		else vBulb = MB_Registers.TSwitchState.OFF;
		
		if(lightBulb == lightBulb1) vReg = MB_Registers.ModBusRegisters.MB_RELE.getReg();
		else vReg = MB_Registers.ModBusRegisters.MB_TRIAC.getReg();
		
		ModBus_Communications.writeCoil(1, vReg, vBulb , sn_Transport);  
		
	}
	
	private void ONOFF_Led(Led led){
		
		led.setLedOn(!led.isLedOn());
		
	}

	@Override
	public String getCategory() {
		return category;
	}

	@Override
	public String getTitle() {
		return "Address : "+address;
	}

	@Override
	public Component getPanel() {
		return this;
	}

	@Override
	public boolean isCategory() {
		return isCategory;
	}
}
