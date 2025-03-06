package Utilidades;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class OKCancelPanel extends JPanel 
{
	private JButton OKButton = null;
	private JButton CancelButton = null;
	private JPanel contPanel = null;
	private int posw;
	
	MouseListener mouseListener = new java.awt.event.MouseAdapter()
	{		
		@Override
		public void mouseClicked(MouseEvent e) 
		{
			PanelMouseEvent(e);	
		}		
	};
	
	private void PanelMouseEvent(java.awt.event.MouseEvent e)
	{		
		this.dispatchEvent(e);
	}
	
	public OKCancelPanel(JPanel contPanel)
	{
		this.contPanel = contPanel;
		this.contPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(java.awt.event.ComponentEvent e) 
			{
				//System.out.println("componentResized()"); // TODO Auto-generated Event stub componentResized()
				ResizePanel();
			}
		});
		this.setLayout(null);
		this.setBounds(new Rectangle(0, contPanel.getHeight()-45, contPanel.getWidth(), 45));
		this.add(getOKButton(), null);
		this.add(getCancelButton(), null);
		this.setBorder(BorderFactory.createRaisedBevelBorder());		
	}
	
	public void ResizePanel()
	{
		//System.out.println("Nuevo Ancho :"+contPanel.getWidth());
		this.setBounds(new Rectangle(0, contPanel.getHeight()-45, contPanel.getWidth(), 45));
		posw = ((contPanel.getWidth()/2)-(OKButton.getWidth()))/2;
		OKButton.setBounds(new Rectangle(posw, 13, 85, 21));
		CancelButton.setBounds(new Rectangle(posw+(contPanel.getWidth()/2), 13, 85, 21));

	}
	
	/**
	 * This method initializes OKButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOKButton() {
		if (OKButton == null) {
			OKButton = new JButton();
			OKButton.setBounds(new Rectangle(15, 15, 85, 21));
			OKButton.setText("OK");
			OKButton.setName("OK");
			OKButton.addMouseListener(mouseListener);			
		}
		return OKButton;
	}
	
	/**
	 * This method initializes CancelButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getCancelButton() {
		if (CancelButton == null) {
			CancelButton = new JButton();
			CancelButton.setBounds(new Rectangle(120, 15, 85, 21));
			CancelButton.setText("Cancelar");
			CancelButton.setName("Cancel");
			CancelButton.addMouseListener(mouseListener); 			
		}
		return CancelButton;
	}
}
