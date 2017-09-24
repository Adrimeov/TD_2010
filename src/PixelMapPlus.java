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
		// complï¿½ter
		//Transformer tous les pixels en nï¿½gatif
		for(int i = 0 ; i < super.width ; i++)
			for(int j = 0 ; j < super.height ; j++)
				super.imageData[i][j] = super.imageData[i][j].Negative();
	}
	
	/**
	 * Convertit l'image vers une image en noir et blanc
	 */
	public void convertToBWImage()
	{
		// complï¿½ter
		//Verifier si l'image est deja au bon format
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
		// complï¿½ter
		//Verifier si l'image est deja au bon format
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
		// complï¿½ter
		//Verifier si l'image est deja au bon format
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
		// complï¿½ter
		//Verifier si l'image est deja au bon format
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
		// complï¿½ter
		//Si l'angle est un multiple de 2pi radians, ne rien modifier, car aucune rotation
		if((angleRadian % (2*Math.PI))==0)
			return; 
		
		//Creation d'une nouvelle image avec tous les pixels en blanc
		PixelMap nouvelleImage = new PixelMap(super.imageType,super.height,super.width);
		
		//Initialisation de la matrice de rotation
		double matrix1[][] = {{Math.cos(angleRadian),
							-1.0f*(Math.sin(angleRadian)),
							-1.0f*(Math.cos(angleRadian))*(double)x+(Math.sin(angleRadian)*(double)y+(double)x)},
							{Math.sin(angleRadian), 
							Math.cos(angleRadian),
							-1.0f*(Math.sin(angleRadian))*(-1.0f*(double)x)+(Math.cos(angleRadian)*(double)y+(double)y)
							}}; 
		
		//Parcourir tous les pixels de l'image subissant la rotation pour trouver son homologue
		for(int i = 0; i<super.height; i++) {
			for(int j = 0; j<super.width; j++) {
				//Matrice qui sera multipliee par la matrice de rotation
				double matrix2[] = {i,j,1.0f};
				
				//Matrice correspondant a l'emplacement du pixel (i, j) apres sa rotation
				double[] nouvelleCoordonne = {0,0};
				
				//Multuplication matricielle
				for(int k = 0; k<2; k++)
					for(int l = 0; l<3;l++)
						nouvelleCoordonne[k] += matrix1[k][l] * matrix2[l];
				
				//Si le nouvel emplacement est dans l'image, copier le pixel d'origine vers la nouvelle image
				if((int)nouvelleCoordonne[0]>=0 && (int)nouvelleCoordonne[0]<super.height && (int)nouvelleCoordonne[1]>=0 && (int)nouvelleCoordonne[1]<super.width )
					nouvelleImage.imageData[(int)(nouvelleCoordonne[0])][(int)(nouvelleCoordonne[1])] = super.imageData[i][j];
				
				
			}
		}
		//Changer le PixelMap de l'ancienne image par celui apres la rotation
		super.imageData = nouvelleImage.imageData;
		
		//Supprimer les donnees de la nouvelle image
		nouvelleImage.clearData();
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
		
		// complï¿½ter
		//Calcul des ratios de grossissement (< 0 signifie retrecissement)
		float ratioHauteur = (float)h/(float)super.height;
		float ratioLargeur = (float)w/(float)super.width;
		
		//Creation d'une matrice de pixels qui contiendra l'image apres le resize
		AbstractPixel[][] tempo = new AbstractPixel[h][w];
		
		//Pour chacun des pixels de la nouvelle image, trouver celui correspondant dans l'ancienne
		for(int i = 0; i<h;i++)
			for(int j = 0; j<w; j++)
				tempo[i][j] = super.imageData[(int)(i/ratioHauteur)][(int)(j/ratioLargeur)];
	
		
		//Remplacer l'ancienne image par la nouvelle
		super.imageData = tempo; 
		
		//Supression de l'image temporaire
		tempo = null;
		
		//Mettre a jour les dimensions de l'image
		super.height = h;
		super.width = w; 
	}
	
	/**
	 * Insert pm dans l'image a la position row0 col0
	 */
	public void inset(PixelMap pm, int row0, int col0)
	{
		// complï¿½ter
		
		//Pour chaque pixel de l'image a ajouter, modifier le pixel si son indice est valide
		for(int i = row0; i < pm.height + row0 ; i++)
			for(int j = col0; j < pm.width + col0 ; j++)
				if(i >= 0 && i < this.height && j >= 0 && j < this.width)
					this.imageData[i][j] = pm.imageData[i - row0][j - col0];
	}
	
	/**
	 * Decoupe l'image 
	 */
	public void crop(int h, int w)
	{
		// complï¿½ter
		//Creation d'une nouvelle image de la bonne dimensions avec que des pixels blancs
		PixelMap nouvelleImage = new PixelMap(this.imageType, h, w);
		
		//Identifier la section commune aux deux images
		int largeurMin = 0;
		if(w < this.width)
			largeurMin = w;
		else largeurMin = this.width;
		
		int hauteurMin = 0;
		if(h < this.height)
			hauteurMin = h;
		else hauteurMin = this.height;
		
		
		//Parcourir la section commune et transferer les pixels de l'ancienne image vers la nouvelle
		for(int i = 0 ; i < hauteurMin ; i++)
			for(int j = 0 ; j < largeurMin ; j++)
				nouvelleImage.imageData[i][j] = super.imageData[i][j];
		
		//Transferer les donnees de la nouvelle image vers l'anciennce
		super.imageData = nouvelleImage.imageData;
		
		//Mise a jour des nouvelles dimensions de l'objet courant
		super.width = w;
		super.height = h;
	}
	
	/**
	 * Effectue une translation de l'image 
	 */
	public void translate(int rowOffset, int colOffset)
	{
		// complï¿½ter		
		
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
		
		//Si le zoom est de 1x, alors ne rien faire
		if(zoomFactor == 1)
			return;
		
		// complï¿½ter
		//Calcul de la dimension de la partie agrandie
		int hauteur = (int)(this.height / zoomFactor);
		int largeur = (int)(this.width / zoomFactor);
		
		
		//Recentrer le point choisi si image hors limite
		if((x - hauteur/2) < 0)
			x = hauteur / 2;
		else if ((x + hauteur/2) >= this.height)
			x = super.height - hauteur/2;
		
		if((y - largeur/2) < 0)
			y = largeur / 2;
		else if ((y + largeur/2) >= this.width)
			y = super.width - largeur/2;
		
		//Extraire la portion à zoomer
		int hauteurMin = x - hauteur / 2;
		int largeurMin = y - largeur / 2;
		int largeurEnCours = largeurMin;
		int hauteurEnCours = hauteurMin; 
		
		
		//Creation d'une matrice contenant seulement la zone qui devra etre agrandie
		AbstractPixel[][] nouvelleImage = new AbstractPixel[hauteur][largeur];

		//Transferer les pixels de la zone a agrandir vers la nouvelle image
		for(int i = 0 ; i < hauteur ; i++) {
			for(int j = 0 ; j < largeur; j++) {
				nouvelleImage[i][j] = super.imageData[hauteurEnCours][largeurEnCours];
				largeurEnCours++;
			}
			hauteurEnCours++;
			largeurEnCours = largeurMin;
		}
		
		//Remplacer l'image de l'objet courant par la nouvelle creee precedemment
		super.imageData = nouvelleImage;
		
		//Mise a jour et en memoire des tailles des images
		int hauteurOriginal = super.height;
		int largeurOriginal = super.width;
		super.height = hauteur;
		super.width = largeur;
		
		//Effectuer l'agrandrissement de l'image avec la methode existante
		this.resize(largeurOriginal, hauteurOriginal);
		
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
		// complï¿½ter
		
	}

	public void inverser() {
		// complï¿½ter
				
	}
}
