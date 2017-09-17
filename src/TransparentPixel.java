/**
 * Classe de pixel transparent
 * @author :
 * @date : 
 */

public class TransparentPixel extends AbstractPixel
{
	public int[] rgba; // donnees de l'image
	
	/**
	 * Constructeur par defaut (pixel blanc)
	 */
	TransparentPixel()
	{
		rgba = new int[4];
		rgba[0] = 255;
		rgba[1] = 255;
		rgba[2] = 255;
		rgba[3] = 255;
	}
	
	/**
	 * Assigne une valeur au pixel
	 * @param rgb: valeurs a assigner 
	 */
	TransparentPixel(int[] rgba)
	{
		// complÃ©ter
		//Allouer l'espace mémoire du tableau
		this.rgba = new int[4];
		
		//Insérer les attrbuts dans le tableau
		this.rgba[0] = rgba[0];
		this.rgba[1] = rgba[1];
		this.rgba[2] = rgba[2];
		this.rgba[3] = rgba[3];
	}
	
	/**
	 * Renvoie un pixel copie de type noir et blanc
	 */
	public BWPixel toBWPixel()
	{
		// complÃ©ter
		
		//Caluler la moyenne
		int moyenne = (rgba[0] + rgba[1] + rgba[2]) / 3;
		
		//Conversion vers BW
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
		// complÃ©ter
		//Calculer la moyenne
		int moyenne = (rgba[0] + rgba[1] + rgba[2]) / 3;
		
		//Conversion vers GrayPixel
		return new GrayPixel(moyenne);
	}
	
	/**
	 * Renvoie un pixel copie de type couleurs
	 */
	public ColorPixel toColorPixel()
	{
		// complÃ©ter
		//Création du tableau pour créer un pixel Color
		int rgb[] = {rgba[0], rgba[1], rgba[2]};
		
		//Création du pixel Color
		return new ColorPixel(rgb);
	}
	
	/**
	 * Renvoie le negatif du pixel (255-pixel)
	 */
	public TransparentPixel Negative()
	{
		// complÃ©ter
		//Transformation vers négatif
		int rgba_negative[] = {rgba[0] - 255, rgba[1] - 255, rgba[2] - 255, rgba[3]};
		
		//Création du pixel négatif
		return new TransparentPixel(rgba_negative);
	}
	
	public TransparentPixel toTransparentPixel()
	{
		// complÃ©ter
		//Retourner un nouveau pixel identique à l'objet courant
		return new TransparentPixel(rgba);
	}
	
	public void setAlpha(int alpha)
	{
		rgba[3] = alpha;
	}
	
	/**
	 * Convertit le pixel en String (sert a ecrire dans un fichier 
	 * (avec un espace supplÃ©mentaire en fin)s
	 */
	public String toString()
	{
		return  ((Integer)rgba[0]).toString() + " " + 
				((Integer)rgba[1]).toString() + " " +
				((Integer)rgba[2]).toString() + " " +
				((Integer)rgba[3]).toString() + " ";
	}
	
	public int compareTo(AbstractPixel p) {
		if (rgba[0] < ((TransparentPixel) p).rgba[0]
				&& rgba[1] < ((TransparentPixel) p).rgba[1]
				&& rgba[2] < ((TransparentPixel) p).rgba[2]
				&& rgba[3] < ((TransparentPixel) p).rgba[3]) {
			return -1;
		} else {
			if (rgba[0] == ((TransparentPixel) p).rgba[0]
					&& rgba[1] == ((TransparentPixel) p).rgba[1]
					&& rgba[2] == ((TransparentPixel) p).rgba[2]
					&& rgba[3] == ((TransparentPixel) p).rgba[3]) {
				return 0;
			} else {
				return 1;
			}
		}
	}
	
}
