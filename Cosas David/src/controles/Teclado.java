package controles;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener {
	
	private final static int numeroTeclas = 120; //Implementamos un array de booleans con 120 huecos (numero general de teclas en teclado)
	private boolean[] Teclas = new boolean[numeroTeclas];
	
	public boolean izquierda;
	public boolean derecha;
	
	public void actualizar() { //llama al metodo en "juego"
		
		izquierda = Teclas[KeyEvent.VK_A]; //Si queremos usar las felchas de direccion: [KeyEvent.VK_LEFT], [KeyEvent.VK_RIGHT]
		derecha = Teclas[KeyEvent.VK_D];
	}

	public void keyTyped(KeyEvent e) {
	
	}

	public void keyPressed(KeyEvent e) {
	
	}

	public void keyReleased(KeyEvent e) {
	
	}

}
