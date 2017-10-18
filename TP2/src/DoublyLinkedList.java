public class DoublyLinkedList<AnyType>
{
    // Un noeud de la liste.
    @SuppressWarnings("hiding")
    private class Node<AnyType>
    {
        private AnyType value;
        private Node prev;
        private Node next;

        public Node(AnyType value, Node prev, Node next)
        {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }

        public void setPrev(Node prev) { this.prev = prev; }

        public Node<AnyType> getPrev() { return this.prev; }

        public void setNext(Node next)
        {
            this.next = next;
        }

        public Node<AnyType> getNext()
        {
            return next;
        }

        public AnyType getValue()
        {
            return value;
        }
    }

    private int size = 0;		 // Nombre d'éléments dans la liste.
    private Node<AnyType> front; // Premier élément de la liste.
    private Node<AnyType> back;  // Dernier élément de la liste.

    // Indique si la liste est vide.
    public boolean empty()
    {
        return size == 0;
    }

    // Retourne la taille de la liste.
    public int size()
    {
        return size;
    }

    // Retourne l'élément à la fin de la liste.
    // Retourne null si la liste est vide.
    // Complexité asymptotique: O(1)
    public AnyType peekBack()
    {
        // À compléter
        if(empty())
            return null;
        else
            return back.getValue();
    }

    // Retourne l'élément au début de la liste.
    // Retourne null si la liste est vide.
    // Complexité asymptotique: O(1)
    public AnyType peekFront()
    {
        // À compléter
        if(empty())
            return null;
        else
            return front.getValue();
    }

    // Retourne le noeud à l'indice donné.
    // Complexité asymptotique: O(n)
    private Node<AnyType> getNodeAt(int index)
    {
        // À compléter
        Node<AnyType> noeud = front;

        //Si l'élément est au début ou à la fin
        if(index == 0)
            return front;
        else if(index == size - 1)
            return back;

        int indexEnCours = 0;

        //Choisir entre parcourir du debut ou de la fin
        if(index > size / 2) {
            noeud = back;
            for (indexEnCours = size - 1; indexEnCours != index; indexEnCours--)
                noeud = noeud.prev;
        }
        else
            for(indexEnCours = 0 ; indexEnCours != index ; indexEnCours++)
                noeud = noeud.next;

        return noeud;
    }

    // Retourne l'élément à l'indice donné.
    // Complexité asymptotique: O(n)
    public AnyType getAt(int index) throws IndexOutOfBoundsException
    {
        // À compléter
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index invalide");

        if(empty())
            return null;
        else
            return (getNodeAt(index)).getValue();
    }

    // Retire l'élément à la fin de la liste.
    // Complexité asymptotique: O(1)
    public void popBack() throws EmptyListException
    {
        // À compléter
        if(empty())
            throw new EmptyListException("Liste vide");

        if(size == 1){
            front = null;
            back = null;
        }
        else {
            back = back.getPrev();
            back.setNext(null);
        }
        //Ajuster la taille de la liste
        size--;
    }

    // Retire l'élément au début de la liste.
    // Complexité asymptotique: O(1)
    public void popFront() throws EmptyListException
    {
        // À compléter
        if(empty())
            throw new EmptyListException("Liste vide");

        if(size == 1){
            front = null;
            back = null;
        }
        else {
            front = front.getNext();
            front.setPrev(null);
        }
        //Ajuster la taille de la liste
        size--;
    }

    // Retire l'élément à l'indice donné.
    // Complexité asymptotique: O(n)
    public void removeAt(int index) throws IndexOutOfBoundsException
    {
        // À compléter
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index invalide");

        //Trouver l'element a enlever
        Node<AnyType> noeud = getNodeAt(index);

        //Ajuster l'element de debut ou de fin du tableau selon le cas
        if(size == 1){ //Un seul element
            front = null;
            back = null;
        }
        else if(index == 0) { //L'element est au debut de la liste
            front = noeud.getNext();
            front.setPrev(null);
        }
        else if(index == size - 1) { //L'element est a la fin de la liste
            back = noeud.getPrev();
            back.setNext(null);
        }
        else { //L'element est au milieu de la liste
            (noeud.getPrev()).setNext(noeud.getNext());
            (noeud.getNext()).setPrev(noeud.getPrev());
        }
        //Ajuster la taille de la liste
        size--;
    }

    // Ajoute un élément à la fin de la liste.
    // Complexité asymptotique: O(1)
    public void pushBack(AnyType item)
    {
        // À compléter
        //Creer le nouveau node a ajouter
        Node<AnyType> nouveau = new Node<>(item, null, null);

        if(!empty()) {
            //Ajuster le dernier noeud
            nouveau.setPrev(back);
            back.setNext(nouveau);
            back = nouveau;
        }
        else { //Si la liste est vide
            front = nouveau;
            back = nouveau;
        }
        //Ajuster la taille de la liste
        size++;
    }

    // Ajoute un élément au début de la liste.
    // Complexité asymptotique: O(1)
    public void pushFront(AnyType item)
    {
        // À compléter
        Node<AnyType> nouveau = new Node<>(item, null, null);
        if(!empty()) {
            //Ajuster le premier noeud
            nouveau.setNext(front);
            front.setPrev(nouveau);
            front = nouveau;
        }
        else { //Si la liste est vide
            front = nouveau;
            back = nouveau;
        }
        //Ajuster la taille de la liste
        size++;
    }

    // Ajoute un élément à l'indice donné.
    // Complexité asymtotique: O(n)
    public void insertAt(AnyType item, int index) throws IndexOutOfBoundsException
    {
        // À compléter
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index invalide");

        //Trouver le noeud a la position a inserer
        Node<AnyType> ancienNoeud = getNodeAt(index);

        Node<AnyType> nouveauNoeud = null;

        if(index != 0){
            //Creation du nouveau noeud
            nouveauNoeud = new Node<>(item, ancienNoeud.getPrev(), ancienNoeud);

            //Mettre a jour le noeud avant le nouveau
            (nouveauNoeud.getPrev()).setNext(nouveauNoeud);
        }
        else { //Si l'insertion se fait au debut de la liste
            //Creation du nouveau noeud
            nouveauNoeud = new Node<>(item, null, ancienNoeud);

            //Mettre a jour le premier noeud de la liste
            front = nouveauNoeud;
        }

        //Mettre a jour l'ancien noeud
        ancienNoeud.setPrev(nouveauNoeud);

        //Mettre a jour la taille de la liste
        size++;
    }
}