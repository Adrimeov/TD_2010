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

    private Node<T> insert(Node<T> node, T elem)
    {
        // À compléter
    }

    private Node<T> balanceRightRight(Node<T> node)
    {
        // À compléter
    }

    private Node<T> balanceRightLeft(Node<T> node)
    {
        // À compléter
    }

    private Node<T> balanceLeftLeft(Node<T> node)
    {
        // À compléter
    }

    private Node<T> balanceLeftRight(Node<T> node)
    {
        // À compléter
    }
}