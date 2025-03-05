package practicas_eldom;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import practicas_eldom.gui.P02_ElDom_MainWindow;


//import Utilidades.FileUtilities;

public class P02_ComunicacionesSerie_Java_Arduino_Prev
{
	private static void createAndShowGUI() 
	{
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        //Create and set up the window.
        P02_ElDom_MainWindow MainWindowGui = new P02_ElDom_MainWindow();
        
        MainWindowGui.start();
    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// Auto-generated method stub
		
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				createAndShowGUI();
			}
		});
		
		say(ConstantesApp.WINDOW_TITLE+"Inicializado");
	}	
	
	
	public static void say(String msg) 
	{
		System.out.println(msg);
	}
}
