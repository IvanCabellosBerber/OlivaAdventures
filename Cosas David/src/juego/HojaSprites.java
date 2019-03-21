package juego;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HojaSprites {
	private final int ancho;
	private final int alto;
	public final int[] pixeles; //Array de numeros enteros por el que pasaremos todos los pixeles de los graficos
	
	
	public HojaSprites(final String ruta, final int ancho, final int alto) {
		this.ancho = ancho;
		this.alto = alto;
		
		pixeles = new int[ancho * alto]; //Array que tendra mismo tamaño que pixeles nuestra imagen
		
		BufferedImage imagen;
		
		try {
			imagen = ImageIO.read(HojaSprites.class.getResource(ruta)); /*Creamos una imagen y le atribuimos el valor de una ruta en
			la cual esta la imagen, debemos volcar los valores de la imagen dentro del array de pixeles, cada int tendra atribuido
			el color de pixel correspondiente.*/
			
			imagen.getRGB(0, 0, ancho, alto, pixeles, 0, ancho);
			/*Consructor complto (Posicion X inicial, Posicion Y inicial, ancho, alto, array a utilizar para guardar la informacion,
			 offset (hasta que punto corregir la posicion), scansize (tamaño de escaneo, hasta donde queremos leer de cada linea
			 horizontal antes de pasar a la siguiente linea).*/
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public int obtenAncho() {
		return ancho;
	}
}
