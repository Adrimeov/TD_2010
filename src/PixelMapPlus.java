import java.awt.PageAttributes.ColorType;

/**
 * Classe PixelMapPlus
 * Image de type noir et blanc, tons de gris ou couleurs
 * Peut lire et ecrire des fichiers PNM
 * Implemente les methodes de ImageOperations
 * @author : 
 * @date   : 
 */

public class PixelMapPlus extends PixelMap implements ImageOperations 
{
	/**
	 * Constructeur creant l'image a partir d'un fichier
	 * @param fileName : Nom du fichier image
	 */
	PixelMapPlus(String fileName)
	{
		super( fileName );
	}
	
	/**
	 * Constructeur copie
	 * @param type : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMapPlus(PixelMap image)
	{
		super(image); 
	}
	
	/**
	 * Constructeur copie (sert a changer de format)
	 * @param type : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMapPlus(ImageType type, PixelMap image)
	{
		super(type, image); 
	}
	
	/**
	 * Constructeur servant a allouer la memoire de l'image
	 * @param type : type d'image (BW/Gray/Color)
	 * @param h : hauteur (height) de l'image 
	 * @param w : largeur (width) de l'image
	 */
	PixelMapPlus(ImageType type, int h, int w)
	{
		super(type, h, w);
	}
	
	/**
	 * Genere le negatif d'une image
	 */
	public void negate()
	{
		// compl�ter
		//Transformer tous les pixels en n�gatif
		for(int i = 0 ; i < super.width ; i++)
			for(int j = 0 ; j < super.height ; j++)
				super.imageData[i][j] = super.imageData[i][j].Negative();
	}
	
	/**
	 * Convertit l'image vers une image en noir et blanc
	 */
	public void convertToBWImage()
	{
		// compl�ter
		//V�rifier si l'image est d�j� au bon format
		if(super.imageType == ImageType.BW)
			return;
			
		//Transformer tous les pixels au nouveau format
		for(int i = 0 ; i < super.width ; i++)
			for(int j = 0 ; j < super.height ; j++)
				super.imageData[i][j] = super.imageData[i][j].toBWPixel();
		
		//Changer le format de l'image
		super.imageType = ImageType.BW;
		
	}
	
	/**
	 * Convertit l'image vers un format de tons de gris
	 */
	public void convertToGrayImage()
	{
		// compl�ter
		//V�rifier si l'image est d�j� au bon format
		if(super.imageType == ImageType.Gray)
			return;
		
		//Transformer tous les pixels au nouveau format
		for(int i = 0 ; i < super.width ; i++)
			for(int j = 0 ; j < super.height ; j++)
				super.imageData[i][j] = super.imageData[i][j].toGrayPixel();
		
		//Changer le format de l'image
		super.imageType = ImageType.Gray;
		
	}
	
	/**
	 * Convertit l'image vers une image en couleurs
	 */
	public void convertToColorImage()
	{
		// compl�ter
		//V�rifier si l'image est d�j� au bon format
		if(super.imageType == ImageType.Color)
			return;
		
		//Transformer tous les pixels au nouveau format
		for(int i = 0 ; i < super.width ; i++)
			for(int j = 0 ; j < super.height ; j++)
				super.imageData[i][j] = super.imageData[i][j].toColorPixel();
		
		//Changer le format de l'image
		super.imageType = ImageType.Color;
		
	}
	
	public void convertToTransparentImage()
	{
		// compl�ter
		//V�rifier si l'image est d�j� au bon format
		if(super.imageType == ImageType.Transparent)
			return;
		
		//Transformer tous les pixels au nouveau format
		for(int i = 0 ; i < super.width ; i++)
			for(int j = 0 ; j < super.height ; j++)
				super.imageData[i][j] = super.imageData[i][j].toTransparentPixel();
		
		//Changer le format de l'image
		super.imageType = ImageType.Transparent;
		
	}
	
	/**
	 * Fait pivoter l'image de 10 degres autour du pixel (row,col)=(0, 0)
	 * dans le sens des aiguilles d'une montre (clockWise == true)
	 * ou dans le sens inverse des aiguilles d'une montre (clockWise == false).
	 * Les pixels vides sont blancs.
	 * @param clockWise : Direction de la rotation 
	 */
	public void rotate(int x, int y, double angleRadian)
	{
		// compl�ter
		
	}
	
	/**
	 * Modifie la longueur et la largeur de l'image 
	 * @param w : nouvelle largeur
	 * @param h : nouvelle hauteur
	 */
	public void resize(int w, int h) throws IllegalArgumentException
	{
		if(w < 0 || h < 0)
			throw new IllegalArgumentException();
		
		// compl�ter
		float ratioHauteur = (float)h/(float)super.height;
		float ratioLargeur = (float)w/(float)super.width;
		
		AbstractPixel[][] tempo = new AbstractPixel[h][w];
		
		for(int i = 0; i<h;i++)
			for(int j = 0; j<w; j++)
				tempo[i][j] = super.imageData[(int)(i/ratioHauteur)][(int)(j/ratioLargeur)];
	
		
		
		super.imageData = tempo; 
		tempo = null; 
		super.height = h;
		super.width = w; 
	}
	
	/**
	 * Insert pm dans l'image a la position row0 col0
	 */
	public void inset(PixelMap pm, int row0, int col0)
	{
		// compl�ter
		
	}
	
	/**
	 * Decoupe l'image 
	 */
	public void crop(int h, int w)
	{
		// compl�ter		
		
	}
	
	/**
	 * Effectue une translation de l'image 
	 */
	public void translate(int rowOffset, int colOffset)
	{
		// compl�ter		
		
	}
	
	/**
	 * Effectue un zoom autour du pixel (x,y) d'un facteur zoomFactor 
	 * @param x : colonne autour de laquelle le zoom sera effectue
	 * @param y : rangee autour de laquelle le zoom sera effectue  
	 * @param zoomFactor : facteur du zoom a effectuer. Doit etre superieur a 1
	 */
	public void zoomIn(int x, int y, double zoomFactor) throws IllegalArgumentException
	{
		if(zoomFactor < 1.0)
			throw new IllegalArgumentException();
		
		// compl�ter
		
	}

	/**
	 * Effectue un remplacement de tout les pixels dont la valeur est entre min et max 
	 * avec newPixel
	 * @param min : La valeur miniale d'un pixel
	 * @param max : La valeur maximale d'un pixel  
	 * @param newPixel : Le pixel qui remplacera l'ancienne couleur 
	 * (sa valeur est entre min et max)
	 */
	public void replaceColor(AbstractPixel min, AbstractPixel max,
			AbstractPixel newPixel) {
		// compl�ter
		
	}

	public void inverser() {
		// compl�ter
				
	}
}
