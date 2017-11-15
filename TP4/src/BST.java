import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.lang.Comparable;
import java.lang.Math;

///////////////////////////////////////////////////////////////////////////////
//
// Main Class File:  BST.java
// File:             BST.java
// Semester:         Automne 2017
//
// Author:           Jean-Frederic Fontaine 1856632
//                   Simon Turcotte         1838092
//Description:
//              Implémentation d'un arbre binaire de recherche
//////////////////////////// 80 columns wide //////////////////////////////////

public class BST<T extends Comparable<T>>
{
    protected class Node<T>
    {
        T val; // Valeur du noeud
        Node<T> right; // fils droite
        Node<T> left; // fils gauche

        public Node(T val)
        {
            this.val = val;
        }
    }

    protected Node<T> root = null; // Racine de l'arbre


    /**
     * Permet de connaitre si l'arbre est un arbre binaire de recherche valide
     *
     * @return boolean : si l'arbre est un arbre binaire de recherche valide
     */
    public boolean isValid() { return isValid(root); }


    /**
     * Permet de connaitre si l'arbre est un arbre binaire de recherche valide
     *
     * @param  node la racine de l'arbre à vérifier
     * @return boolean : si l'arbre est un arbre binaire de recherche valide
     */
    private boolean isValid(Node<T> node)
    {
        //Le noeud et vide, alors celui-ci est vlide
        if (node == null) {
            return true;
        }

        //Vérifier la validité des sous-arbres de gauche et de droite
        boolean isLeftValid = node.left == null || node.left.val.compareTo(node.val) < 0 && isValid(node.left);
        boolean isRightValid = node.right == null || node.right.val.compareTo(node.val) > 0 && isValid(node.right);

        //Le noeud courant est valide si ses sous arbres le sont
        return isLeftValid && isRightValid;
    }


    /**
     * Permet de connaître la hauteur de l'arbre
     *
     * @return int : la hauteur de l'arbre
     */
    public int getHeight() { return root == null ? 0 : getHeight(root); }


    /**
     * Permet de la connaître la hauteur de l'arbre dont le noeud est la racine
     *
     * @param node la racine de l'arbre à analyser
     * @return int : la hauteur de l'arbre dont le noeud est la racine
     */
    protected int getHeight(Node<T> node)
    {
        //Si la racine n'existe pas, ce n'est pas un arbre valide
        if(node == null)
            return -1;
        else
            //Retourner la hauteur maximale des deux sous arbres
            return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }


    /**
     * Permet d'insérer un élément dans l'arbre
     *
     * @param elem l'élément à insérer
     * @return void
     */
	public void insert(T elem) { root = insert(root, elem); }

	private Node<T> insert(Node<T> node, T elem)
    {
		//Si le noeuds est courant est null, créer le noeud et le retourner
        if(node == null)
            return new Node<>(elem);

        //Sinon, trouver la position du prochain noeud à analyser
        int comparaison = elem.compareTo(node.val);

        if(comparaison < 0)
            node.left = insert(node.left, elem);
        else if(comparaison > 0)
            node.right = insert(node.right, elem);
        else {
            //Rien, car on ne veut pas de doublon
        }

        return node;
	}


    /**
     * Permet de savoir si un élément est présent dans l'arbre
     *
     * @param elem l'élément dont on désire connaître la présence
     * @return boolean : si l'élément est présent dans l'arbre
     */
    public boolean contains(T elem) { return contains(root, elem); }


    /**
     * Permet de savoir si un élément est présent dans l'arbre reçu en paramètre
     *
     * @param node racinde de l'arbre à analyser
     * @param elem l'élément dont on désire connaître la présence
     * @return boolean : si l'élément est présent dans l'arbre
     */
    private boolean contains(Node<T> node, T elem)
    {
        //Le noeud ne peut contenir l'élément s'il est null
        if(node == null)
            return false;
        else if(node.val == elem) //Le noeud courant possède l'élément
            return true;
        else if(elem.compareTo(node.val) < 0)
            return contains(node.left, elem); //Analyser le noeud enfant de gauche
        else
            return contains(node.right, elem); //Analyser le noeud enfant de droite
    }

    /**
     * Permet d'obtenir une liste des éléments de l'arbre en pré-ordre
     *
     * @return ArrayList<T> : liste des éléments en pré-order
     */
    public ArrayList<T> traversePreOrder()
	{
		ArrayList<T> list = new ArrayList<T>();
		traversePreOrder(root, list);
		return list;
	}


    /**
     * Permet d'obtenir une liste des éléments de l'arbre en pré-ordre
     *
     * @param node racinde de l'arbre à parcourir
     * @param list liste à remplir
     * @return void
     */
    private void traversePreOrder(Node<T> node, ArrayList<T> list)
    {
        if( node != null )
        {
            list.add(node.val);
            traversePreOrder( node.left, list);
            traversePreOrder( node.right, list);
        }
    }


    /**
     * Permet d'obtenir une liste des éléments de l'arbre en post-ordre
     *
     * @return ArrayList<T> : liste des éléments en post-order
     */
    public ArrayList<T> traversePostOrder()
	{
		ArrayList<T> list = new ArrayList<T>();
		traversePostOrder(root, list);
		return list;
	}


    /**
     * Permet d'obtenir une liste des éléments de l'arbre en post-ordre
     *
     * @param node racinde de l'arbre à parcourir
     * @param list liste à remplir
     * @return void
     */
	private void traversePostOrder(Node<T> node, ArrayList<T> list)
	{
        if( node != null )
        {
            traversePostOrder(node.left, list);
            traversePostOrder( node.right, list);
            list.add(node.val);
        }
	}


    /**
     * Permet d'obtenir une liste des éléments de l'arbre en ordre
     *
     * @return ArrayList<T> : liste des éléments en order
     */
    public ArrayList<T> traverseInOrder()
    {
        ArrayList<T> list = new ArrayList<T>();
        traverseInOrder(root, list);
        return list;
    }

    /**
     * Permet d'obtenir une liste des éléments de l'arbre en ordre
     *
     * @param node racinde de l'arbre à parcourir
     * @param list liste à remplir
     * @return void
     */
    private void traverseInOrder(Node<T> node, ArrayList<T> list)
    {
        if( node != null )
        {
            traverseInOrder(node.left, list);
            list.add(node.val);
            traverseInOrder( node.right, list);
        }
    }


    /**
     * Permet d'obtenir une liste des éléments de l'arbre en ordre inverse
     *
     * @return ArrayList<T> : liste des éléments en ordre inverse
     */
    public ArrayList<T> traverseReverseOrder()
    {
        ArrayList<T> list = new ArrayList<T>();
        traverseReverseOrder(root, list);
        return list;
    }


    /**
     * Permet d'obtenir une liste des éléments de l'arbre ordre inverse
     *
     * @param node racinde de l'arbre à parcourir
     * @param list liste à remplir
     * @return void
     */
    private void traverseReverseOrder(Node<T> node, ArrayList<T> list)
    {
        if( node != null )
        {
            traverseReverseOrder( node.right, list);
            list.add(node.val);
            traverseReverseOrder(node.left, list);
        }
    }


    /**
     * Permet d'obtenir une liste des éléments de l'arbre par niveau
     *
     * @return ArrayList<T> : liste des éléments parcourues par niveau
     */
    public ArrayList<T> traverseLevelOrder()
	{
		ArrayList<T> list = new ArrayList<T>();
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node<T> tempo = queue.poll();
            if(tempo != null) {
                queue.add(tempo.left);
                queue.add(tempo.right);
                list.add(tempo.val);
            }

        }

		return list;
	}
}

