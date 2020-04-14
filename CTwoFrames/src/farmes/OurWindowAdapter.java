package farmes;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class OurWindowAdapter extends WindowAdapter {  
	
	public void windowClosing (WindowEvent wE) {
		System.exit (0);
	}
} 