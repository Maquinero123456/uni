/*
 * Copyright (c) 2008, Swedish Institute of Computer Science.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. Neither the name of the Institute nor the names of its contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE INSTITUTE AND CONTRIBUTORS ``AS IS'' AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE INSTITUTE OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *
 * $Id: SerialConsole.java,v 1.1 2010/11/03 14:53:05 adamdunkels Exp $
 *
 * -----------------------------------------------------------------
 *
 * SerialConsole
 *
 * Authors : Joakim Eriksson, Niclas Finne
 * Created : 4 jul 2008
 * Updated : $Date: 2010/11/03 14:53:05 $
 *           $Revision: 1.1 $
 */

package practicas_eldom.gui.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 */
@SuppressWarnings("serial")
public class Console extends JDialog /*implements Visualizer*/ {

	//private final Collect_MainWindow server;
	//private final String category;
	private JPanel 			mainPanel;
	private JTextArea 		logArea;
	private JTextField 		commandField;
	private String[] 		history 				= new String[50];
	private int 			historyPos 				= 0;
	private int 			historyCount 			= 0;
	private ActionListener 	SerialConsoleListener 	= null;
	private boolean 		resized;
  
	public Console(){
		setType(Type.POPUP);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				resized = false;
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(resized){
				//	Rectangle rectangle = mainPanel.getBounds();
		        //	configApp.setProperty("ConsoleBounds",rectangle.x+", "+rectangle.y+", "+rectangle.width+", "+rectangle.height);
		    	//	ConfigUtilities.saveConfig(configApp, ConstantesApp.CONFIG_FILE);
					performedAction("NewSize");
				}
			}
		});
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				resized = true;
			}
		});
		addWindowListener(new WindowAdapter() {
	
			@Override
			public void windowClosing(WindowEvent e) {
				performedAction("Close");
			}
			
			
		});
		this.setTitle("Mostrar mensajes Puerto Serie y Aplicación");		
		this.setContentPane(getMainPanel());
		this.setSize(new Dimension(372, 470));
		
	}
	
	private void performedAction(String message){
		if (SerialConsoleListener != null){
			ActionEvent evt = new ActionEvent(this, 0, message);
			SerialConsoleListener.actionPerformed(evt);
		}
	}
	
	public void addActionListener(ActionListener actionListener) {
		this.SerialConsoleListener = actionListener;
	}
  
	private JPanel getMainPanel(){
		if (mainPanel == null) 
		{
			mainPanel = new JPanel(new BorderLayout());			
			
			logArea = new JTextArea(4, 30);
		    logArea.setEditable(false);
		    mainPanel.add(new JScrollPane(logArea), BorderLayout.CENTER);
		    
		    JPopupMenu popupMenu = new JPopupMenu();
		    JMenuItem clearItem = new JMenuItem("Clear");
		    clearItem.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		logArea.setText("");
		    	}
		    });
		    popupMenu.add(clearItem);
		    logArea.setComponentPopupMenu(popupMenu);

		    commandField = new JTextField();
		    commandField.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		String command = trim(commandField.getText());
		    		if (command != null) {
		    			try {
		    				int previous = historyCount - 1;
		    				if (previous < 0) previous += history.length;
		    				if (!command.equals(history[previous])) {
		    					history[historyCount] = command;
		    					historyCount = (historyCount + 1) % history.length;
		    				}
		    				historyPos = historyCount;
		    				/*
		            		if (SerialConsole.this.server.sendToNode(command)) {
		              		commandField.setText("");
		            		} else {
		              		addSerialData("*** failed to send command ***");
		            		}
		    				 */
		    			} catch (Exception ex) {
		    				System.err.println("could not send '" + command + "':");
		    				ex.printStackTrace();
		    				JOptionPane.showMessageDialog(mainPanel,
		    						"could not send '" + command + "':\n"
		    								+ ex, "ERROR",
		    								JOptionPane.ERROR_MESSAGE);
		    			}
		    		} else {
		    			commandField.getToolkit().beep();
		    		}
		    	}
		    });
		    
		    commandField.addKeyListener(new KeyAdapter() {
		    	@Override
		        public void keyPressed(KeyEvent e) {
		    		switch (e.getKeyCode()) {
		    			case KeyEvent.VK_UP: {
		    				int nextPos = (historyPos + history.length - 1) % history.length;
		    				if (nextPos == historyCount || history[nextPos] == null) {
		    					commandField.getToolkit().beep();
		    				} else {
		    					String cmd = trim(commandField.getText());
		    					if (cmd != null) {
		    						history[historyPos] = cmd;
		    					}
		    					historyPos = nextPos;
		    					commandField.setText(history[historyPos]);
		    				}
		    				break;
		    			}
		          
		    			case KeyEvent.VK_DOWN: {
		    				int nextPos = (historyPos + 1) % history.length;
		    				if (nextPos == historyCount) {
		    					historyPos = nextPos;
		    					commandField.setText("");
		    				} else if (historyPos == historyCount || history[nextPos] == null) {
		    					commandField.getToolkit().beep();
		    				} else {
		    					String cmd = trim(commandField.getText());
		    					if (cmd != null) {
		    						history[historyPos] = cmd;
		    					}
		    					historyPos = nextPos;
		    					commandField.setText(history[historyPos]);
		    				}
		    				break;
		    			}
		    		}
		        }
		      });
		    
		    mainPanel.add(commandField, BorderLayout.SOUTH);

		}
		
		return mainPanel;
	}

/*
  public void addSerialData(final String text) {
	  SwingUtilities.invokeLater(new Runnable() {
		  public void run() {
			  String current = logArea.getText();
			  int len = current.length();
			  if (len > 4096) {
				  current = current.substring(len - 4096);
			  }
			  current = len > 0 ? (current + text) : text;
			  logArea.setText(current);
			  logArea.setCaretPosition(current.length());
		  }
	  });
  }
*/
	
	public void log(final String text) {
		Document test = logArea.getDocument();
	
		try {
			test.insertString(test.getLength(), text, null);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//addSerialData(text);
	}
  
  public void Close(){
	  this.dispose();
  }

  private String trim(String text) {
    return (text != null) && ((text = text.trim()).length() > 0) ? text : null;
  }

}
