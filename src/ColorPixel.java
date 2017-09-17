/**
 * Classe de pixel en couleurs 
 * @author :
 * @date : 
 */

public class ColorPixel extends AbstractPixel
{
	public int[] rgb; // donnees de l'image
	
	/**
	 * Constructeur par defaut (pixel blanc)
	 */
	ColorPixel()
	{
		rgb = new int[3];
		rgb[0] = 255;
		rgb[1] = 255;
		rgb[2] = 255;
	}
	
	/**
	 * Assigne une valeur au pixel
	 * @param rgb: valeurs a assigner 
	 */
	ColorPixel(int[] rgb)
	{
		// complÃ©ter
		
		//Allouer l'espace mémoire du tableau
		this.rgb = new int[3];
		
		//Insérer les attrbuts dans le tableau
		this.rgb[0] = rgb[0];
		this.rgb[1] = rgb[1];
		this.rgb[2] = rgb[2];
	
	}
	
	/**
	 * Renvoie un pixel copie de type noir et blanc
	 */
	public BWPixel toBWPixel()
	{
		// complÃ©ter
		//Calcul pour transformation vers PixelBW
		int moyenne = (rgb[0] + rgb[1] + rgb[2]) / 3;
		
		
		//Transformer le pixel en BW selon la moyenne
		if(moyenne <= 127)
			return new BWPixel(false);
		else
			return new BWPixel(true);
		
	}
	
	/**
	 * Renvoie un pixel copie de type tons de gris
	 */
	public GrayPixel toGrayPixel()
	{
		//Calcul pour transformation vers GrayPixel
		int moyenne = (rgb[0] + rgb[1] + rgb[2]) / 3;
		
		//Création d'un GrayPixel
		return new GrayPixel(moyenne);
	}
	
	/**
	 * Renvoie un pixel copie de type couleurs
	 */
	public ColorPixel toColorPixel()
	{
		// complÃ©ter
		//Création d'un nouveau pixel identique à l'objet courant
		return new ColorPixel(rgb); 
	}
	
	public TransparentPixel toTransparentPixel()
	{
		// complÃ©ter
		//Création du tableu permettant la création d'un TransparentPixel
		int rgba[] = {rgb[0], rgb[1], rgb[2], 255};
		return new TransparentPixel(rgba);
		
	}
	
	/**
	 * Renvoie le negatif du pixel (255-pixel)
	 */
	public AbstractPixel Negative()
	{
		// complÃ©ter
		//Création du tableu permettant l'inversion d'un TransparentPixel
		int rgb_negative[] = {255 - rgb[0], 255 - rgb[1], 255 - rgb[2]};
		
		return new ColorPixel(rgb_negative);
	}
	
	public void setAlpha(int alpha)
	{
		//ne fait rien
	}
	
	/**
	 * Convertit le pixel en String (sert a ecrire dans un fichier 
	 * (avec un espace supplÃ©mentaire en fin)s
	 */
	public String toString()
	{
		return  ((Integer)rgb[0]).toString() + " " + 
				((Integer)rgb[1]).toString() + " " +
				((Integer)rgb[2]).toString() + " ";
	}
	
	public int compareTo(AbstractPixel p) {
		if (rgb[0] < ((ColorPixel) p).rgb[0]
				&& rgb[1] < ((ColorPixel) p).rgb[1]
				&& rgb[2] < ((ColorPixel) p).rgb[2]) {
			return -1;
		} else {
			if (rgb[0] == ((ColorPixel) p).rgb[0]
					&& rgb[1] == ((ColorPixel) p).rgb[1]
					&& rgb[2] == ((ColorPixel) p).rgb[2]) {
				return 0;
			} else {
				return 1;
			}
		}
	}

}