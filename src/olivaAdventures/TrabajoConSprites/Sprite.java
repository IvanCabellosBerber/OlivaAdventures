package olivaAdventures.TrabajoConSprites;

public final class Sprite { //Marcada como final porque no se extendera

	private int lado; //La medicion de cada lado de los cuadrados del Sprite
	private int x; //Coordenadas eje horizontal
	private int y; //Coordenadas eje vertical
	
	public int[] pixeles; //array para guardar toda la coleccion del Sprite
	private final HojaSprites hoja;
	
	//Constructor de la clase
	public Sprite(final int lado, final int columna, final int fila, final HojaSprites hoja) {
		this.lado = lado;
		pixeles = new int[lado * lado];
		this.x = columna * lado;
		this.y = fila * lado;
		this.hoja = hoja;
		
		//Variables temporales de bucle, no confundir con variables principales de clase
		for (int Y = 0; Y < lado; Y++) {/*Ordenamos al bucle que incremente hasta que Y sea de igual tama�o que lado.
		Empezamos con Y  no con X poque interesa pinar la pantalla de izquierda a derecha y de arriba a abajo*/
			for (int X = 0; X < lado; X++) {
				//Se podria usar array Bidimensional, pero el proceso seria mas lento y menos agil al ejecuar el juego
				pixeles[X + Y * lado] = hoja.pixeles[(X + this.x) + (Y + this.y) * hoja.obtenAncho()];
				/*Copiamos el valor del array de pixeles de la hoja de Sprites al valor del array de pixeles del Sprite individual,
				le damos los valores x e y del bucle for que es el que realmente cuenta los pixeles y le sumamos this.x y this.y*/
			}
			
		}
	}
}
