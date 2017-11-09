import java.lang.Comparable;

public class AvlTree<T extends Comparable<T>> extends BST<T>
{
    public boolean isBalanced() { return isBalanced(root); }

    private boolean isBalanced(Node<T> node)
    {
        if (node == null) {
            return true;
        }

        boolean childsBalanced = isBalanced(node.left)
                              && isBalanced(node.right);

        int heightDiff = Math.abs(getHeight(node.left) - getHeight(node.right));

        return childsBalanced && heightDiff <= 1;
    }

    public void insert(T elem) { root = insert(root, elem); }
    //Param :
    //  Node<T>: un noeud qui peut contenerir un type generique
    //  T      : Element generique a joindre lors de la creation du noeud
    //
    //Description:
    //          Cette methode permet d'ajouter un nouveau noeud dans larbre.
    //          -Si l'arbre est vide on creer une nouvelle racine avec l'elem.
    //          -Sinon on compare la valeur a ajouter avec la valeur de la racine
    //          -On descend ensuite larbre en cherchant sa poistion idale selon
    //           les principes de placement de noeud d'arbre binaire.
    //          -on verifie ensuite si l'arbre est balance ou non
    //          -selon le type de debalancement on applique les rotations ncesessaire
    private Node<T> insert(Node<T> node, T elem)
    {
        if( node == null )
            return new Node<T>(elem);

        int compareResult = elem.compareTo( node.val );

        if( compareResult < 0 )
        {
            node.left = insert(node.left, elem);

            if( !isBalanced(node) )
                if( elem.compareTo( node.left.val ) < 0 )
                    node = balanceLeftLeft( node ); // premier cas de figure
                else
                    node = balanceLeftRight( node ); // second cas de figure
        }
        else if( compareResult > 0 )
        {
            node.right = insert(node.right, elem);

            if( !isBalanced(node))
                if( elem.compareTo( node.right.val ) > 0 )
                    node = balanceRightRight( node );  // premier cas de figure
                else
                    node = balanceRightLeft( node );  // second cas de figure
        }

        return node;
    }
    //Param :
    //  Node<T>: un noeud qui peut contenerir un type generique
    //Description:
    //          Rotation double vers la droite.
    //
    //

    private Node<T> balanceRightRight(Node<T> node)
    {
        Node<T> k2 = node.right;


        node.right = k2.left;
        k2.left = node;


        return k2;
    }

    private Node<T> balanceRightLeft(Node<T> node)
    {
        node.right = balanceLeftLeft( node.right );
        return balanceRightRight( node );
    }

    private Node<T> balanceLeftLeft(Node<T> node)
    {
        Node<T> k1 = node.left;

        node.left = k1.right;
        k1.right = node;

        return k1;
    }

    private Node<T> balanceLeftRight(Node<T> node)
    {
        node.left = balanceRightRight( node.left );
        return balanceLeftLeft( node);

    }
}