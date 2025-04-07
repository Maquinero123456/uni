

package practicas_eldom.gui.visualizers;

import java.awt.Component;

/**
 *
 */
public interface Visualizer {
	public String 		getCategory();
	public String 		getTitle();
	public Component 	getPanel();
	public boolean		isCategory();
}
