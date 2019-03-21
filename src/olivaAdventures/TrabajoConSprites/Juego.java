package olivaAdventures.TrabajoConSprites;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.BorderLayout;

//Runnable obliga a crear el metodo run(), saltara un error en Juego debido a que cuando implementamos una interfaz, estamos obligados a escribir todos los metodos de esa interfaz.
public class Juego extends Canvas implements Runnable{ //Canvas = Superficie especializada de Java para dibujar con pocos recursos del sistema
	
	//A�adimos un identificador de serie por defecto (Java debe saber que se utiliza esta clase aunque se modifique
	private static final long serialVersionUID=1;
	private static JFrame ventana;
	private static final int ANCHO = 800;
	private static final int ALTO = 800;
	private static final String NOMBRE = "Oliva Adventures";
	private static int aps = 0;
	private static int fps = 0;
	private static Thread thread;
	//Necesitamos un Boolean para controlar si el juego esta ejecutandose o no.
	//Se esta usando la variable "enFuncionamiento" en los dos threads, lo cual es peligroso, por ello usaremos la palabra reservable "volatile", la variable no se usara a la vez en ambos threads
	private static volatile boolean enFuncionamiento = false; //Lo dejamos en falso por defecto
	
	//Procedemos a hacer el constructor
	private Juego() {
		setPreferredSize(new Dimension(ANCHO, ALTO)); //Fijamos el tama�o de la ventana
		ventana = new JFrame(NOMBRE);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Al cerrar la ventana, la aplicacion no continuara en segundo plano
		ventana.setResizable(false); //El usuario no podra arrastrar los bordes para hacer la ventana mas grande o peque�a
		ventana.setLayout(new BorderLayout()); //A�adirle un dise�o a la ventana
		ventana.add(this, BorderLayout.CENTER); //ventana.add(Esta clase, darle la organizacion y centrar la ventana)
		ventana.pack(); //Comando para que todo el contenido se ajuste al tama�o de la ventana
		ventana.setLocationRelativeTo(null); //Fijar la ventana en el centro del escritorio, escribiendo otra cosa se fijaria al centro de esa cosa
		ventana.setVisible(true);
	}
	
	public static void main(String[] args) {
		
		Juego Juego = new Juego(); //Primera instancia de nuestro juego
		Juego.iniciar();
		
	}
	//Tras colocar el volatile en "enFuncionamiento", colocamos synchronized en iniciar() y detener()
	private synchronized void iniciar() {
		
		enFuncionamiento = true;
		
		thread = new Thread(this, "Graficos"); //Se iniciara el thread desde esta clase, le ponemos tambien un nombre que sirva de identificador.
											   //Graficos porque es el thread que se ocupara de ellos
		thread.start();
		
	}
	
	public void run() { //Lo que escribamos aqui dentro es lo que se ejecutara en el segundo thread
		
		final int NS_POR_SEGUNDO = 1000000000; //9 Ceros, equivalencia de nanosegundos por segundo
		final byte APS_OBJETIVO = 60; //Actualizaciones por segundo objetivo, cuanto mas bajo el numero, menos veces se actualiza el juego y menos carga en el equipo.
									  // En un futuro intentar bajar el numero sin generar problemas de rendimiento, un numero fluido y con buena funcionalidad seria alrededor de 30
		
		final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO; //Descubrir cuantos nanosegundos deben pasar para cumplir el objetivo de actualizar 60 veces por segundo
		
		long referenciaActualizacion = System.nanoTime(); //Se atribuira una cantidad de tiempo en nanosegundos. nanoTime es un metodo fiable, no depende del sistema operativo, sino del
														  //Procesador, mide en nanosegundos tomando como referencia los ciclos del reloj del procesador.
		long referenciaContador = System.nanoTime();
		
		double tiempoTranscurrido;
		
		double delta = 0; //Cantidad de tiempo transcurrido hasta una actualizacion
		
		while (enFuncionamiento = true) {
			final long inicioBucle = System.nanoTime();
			tiempoTranscurrido = inicioBucle - referenciaActualizacion;
			referenciaActualizacion = inicioBucle; //Cada vuelta del bucle, restamos diferencias con inicioBucle, haciendo mediciones precisas.
			delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;
			
				while (delta >= 1) {
					actualizar(); //Actualiza el estado del juego
					delta--;
				}
			
			mostrar();    //Redibuja los graficos que se ven en pantalla
			
			if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO) { //Se toma el tiempo preciso de nanoTime y se resta a la diferencia de contador, si es mas de 1 segundo, se realiza
																		   //una actualizacion al contador
				
				ventana.setTitle(NOMBRE + " || APS: "+aps+" ||FPS: "+fps); //Se mostrara cuantos APS y FPS hay y luego se reiniciara a 0 de cara a la siguiente actualizacion
				aps = 0;
				fps = 0;
				referenciaContador = System.nanoTime(); //Vuelve a tomar el contador, cada vez que el bucle vuelva a iniciarse tendremos medicion precisa de tiempo.
				
			}
		}
	}
	
	private void actualizar() { //Todo lo necesario para actualizar las variables del juego
		
		aps++; //Sumamos a APS uno cada vez que actualice
	}
	
	private void mostrar() { //Todos los metodos para ir redibujando los graficos
		
		fps++; //Sumamos a FPS uno cada vez que se ejecute mostrar
	}
	
	private synchronized void detener() {
		
		enFuncionamiento = false;
		//Escribimos un thread.join(), el cual debemos rodear con un bloque de try / catch
		try {
			thread.join(); //Preferible a thread.stop, con join no se para el thread inmediatamente, primero deja que acabe de ejecutar el "proceso" en el que se encuentra
		} 
		
		catch (InterruptedException e) {

			e.printStackTrace(); //Mostrar el error por consola
		}
	}

}
