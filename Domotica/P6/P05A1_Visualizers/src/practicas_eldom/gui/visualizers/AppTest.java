package practicas_eldom.gui.visualizers;

import java.awt.Component;

import javax.swing.JPanel;

import CommTransport.CommTransport;
import Utilidades.modbus.ModBus_Communications;
import practicas_eldom.config.MB_Registers;

import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class AppTest extends JPanel implements Visualizer {
	private final 	String 					category;
	private final 	boolean					isCategory = false;
	@SuppressWarnings("unused")
	private final	CommTransport 			sn_Transport;
	
	public AppTest(String category, CommTransport sn_Transport) {
		
		super();
		
		this.category = category;
		this.sn_Transport = sn_Transport;
		
		setLayout(null);
		
		JButton btnNewButton = new JButton("ON RELÉ");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ModBus_Communications.writeCoil(1, MB_Registers.ModBusRegisters.MB_RELE.getReg(), 
						MB_Registers.TSwitchState.ON , sn_Transport);   
			}
		});
		btnNewButton.setBounds(83, 112, 144, 23);
		add(btnNewButton);
		
		JButton btnOnTriac = new JButton("ON TRIAC");
		btnOnTriac.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ModBus_Communications.writeCoil(1, MB_Registers.ModBusRegisters.MB_TRIAC.getReg(), 
						MB_Registers.TSwitchState.ON , sn_Transport);   
			}
		});
		btnOnTriac.setBounds(260, 112, 144, 23);
		add(btnOnTriac);
		
		JButton btnOffTriac = new JButton("OFF TRIAC");
		btnOffTriac.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ModBus_Communications.writeCoil(1, MB_Registers.ModBusRegisters.MB_TRIAC.getReg(), 
						MB_Registers.TSwitchState.OFF , sn_Transport);   
			}
		});
		btnOffTriac.setBounds(260, 148, 144, 23);
		add(btnOffTriac);
		
		JButton btnOffRel = new JButton("OFF RELÉ");
		btnOffRel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ModBus_Communications.writeCoil(1, MB_Registers.ModBusRegisters.MB_RELE.getReg(), 
						MB_Registers.TSwitchState.OFF , sn_Transport);   
			}
		});
		btnOffRel.setBounds(83, 148, 144, 23);
		add(btnOffRel);
	}

	@Override
	public String getCategory() {
		return category;
	}

	@Override
	public String getTitle() {
		return "** Test **";
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
