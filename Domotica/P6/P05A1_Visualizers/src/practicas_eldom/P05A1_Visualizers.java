package practicas_eldom;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import practicas_eldom.gui.COMSerie_MainWindow;


//import Utilidades.FileUtilities;

public class P05A1_Visualizers
{
	private static void createAndShowGUI() 
	{
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        //Create and set up the window.
        COMSerie_MainWindow MainWindowGui = new COMSerie_MainWindow();
        
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
