import java.util.EmptyStackException;

public class ArrayStack<AnyType>
{
    private static final int INITIAL_SIZE = 10;
    private static final int DEFAULT_RESIZE_FACTOR = 2;

    private int size = 0; // Nombre d'éléments dans la pile.
    private AnyType[] table;

    // Initialisation de la pile.
    public ArrayStack()
    {
        // À compléter
        table = (AnyType[]) new Object[INITIAL_SIZE];
    }

    // Enlève l'élément au sommet de la pile et le renvoie.
    // Complexité asymptotique: O(1)
    public AnyType pop() throws EmptyStackException
    {
        // À compléter
        if(empty())
            throw new EmptyStackException();

        return table[(size--) - 1];
    }

    // Ajoute un élément au sommet de la pile.
    // Augmente la taille de la pile si nécessaire (utiliser la fonction resize définie plus bas).
    // Complexité asymptotique: O(1) (O(N) lorsqu'un redimensionnement est nécessaire)
    public void push(AnyType element)
    {
        // À compléter
        //Augmenter la taille de la table si elle est pleine
        if(table.length == size)
            resize(DEFAULT_RESIZE_FACTOR);

        table[size++] = element;
    }

    // Renvoie l'élément au sommet de la pile sans l'enlever.
    // Retourne null si la pile est vide.
    // Complexité asymptotique: O(1)
    public AnyType peek()
    {
        // À compléter
        if(empty())
            return null;
        else
            return table[size - 1];
    }

    // Renvoie le nombre d'éléments dans la pile.
    public int size() { return size; }

    // Indique si la pile est vide.
    public boolean empty() { return size == 0; }

    // Redimensionne la pile. La capacité est multipliée par un facteur de resizeFactor.
    // Complexité asymptotique: O(N)
    @SuppressWarnings("unchecked")
    private void resize(int resizeFactor)
    {
        // À compléter
        AnyType[] nouveau = (AnyType[]) new Object[size * resizeFactor];

        //Transferer les elements dans le nouveau tableau
        for(int i = 0 ; i < size ; i++)
            nouveau[i] = table[i];

        table = nouveau;
    }
}
