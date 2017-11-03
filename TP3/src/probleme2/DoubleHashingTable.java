package probleme2;

public class DoubleHashingTable<AnyType>{

    //Attributs
    private final int CAPACITE_INTIALE = 11;
    private int capacite = 0;
    private int premier = 2;
    AnyType[] data;

    //Méthodes
    public void DoubleHashingTable(){
        allocateMemory(CAPACITE_INTIALE);
    }

    public void insert(AnyType entree){
        int h1 = (entree.hashCode() % capacite);
        int h2 = premier - (entree.hashCode() & premier);
        int position = h1, i = 0;

        while(estCollision(h1)) {
            position = (h1 + i * h2) % capacite;
        }

        data[position] = entree;
    }


    private boolean estCollision(int position){
        if(!data[position].equals(null))
            return true;
        else
            return false;

    }


    private void allocateMemory(int taille){
        data = (AnyType[]) new Object[taille];
        ancienPremier();
    }


    public AnyType get(AnyType entree){

        return null;
    }


    public int nbElement(){
        return capacite;
    }

    private void ancienPremier(){
        for(int i = capacite - 1 ; i > 0 ; i--)
                if(estPremier(i))
                    premier = i;
    }


    private boolean estPremier(int number){
        if(number < 2)
            return false;

        if(number == 2)
            return true;

        if(number % 2 == 0)
            return false;

        for(int i=3; (i*i)<=number; i+=2)
            if(number % i == 0 )
                return false;

        return true;
    }


}
