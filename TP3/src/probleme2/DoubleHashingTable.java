package probleme2;

public class DoubleHashingTable<AnyType>{

    //Attributs
    private int capacite = 2;
    private int nombreElement = 0;
    private int premier = 2;
    AnyType[] data;

    //Méthodes
    public DoubleHashingTable(){
        allocateMemory();
    }


    public void insert(AnyType entree){
        if(nombreElement >= capacite / 2)
            reHash();

        int position = positionnement(entree, 0);
        boolean collision = true;

        for(int i = 0 ; i < capacite && collision ; i++) {
            position = positionnement(entree, i);
            collision = estCollision(position, entree);
        }

        if(!collision) {
            data[position] = entree;
            nombreElement++;
        }
    }


    private int positionnement(AnyType entree, int i){
        int h1 = (entree.hashCode() % capacite);
        int h2 = premier - (entree.hashCode() % premier);

        return (h1 + i * h2) % capacite;
    }

    private boolean estCollision(int position, AnyType entree){
        if(nombreElement == 0 || data[position] == null)
            return false;
        else
            return true;
    }

    private boolean trouveItem(int position, AnyType entree){
        if(nombreElement == 0 || data[position] == null)
            return false;
        else if(data[position].equals(entree))
            return true;

        return false;
    }


    private void allocateMemory(){
        capacite = prochainPremier(2 * capacite + 1);
        data = (AnyType[]) new Object[prochainPremier(capacite)];
        ancienPremier();
    }


    private void reHash(){
        int ancienneTaille = capacite;
        AnyType[] ancien = data;
        allocateMemory();
        nombreElement = 0;

        //Transfere les donnees de l'ancienne table dans la nouvelle
        for (int i = 0; i < ancienneTaille; i++)
            if (ancien[i] != null)
                insert(ancien[i]);
    }


    public int nbreOccurence(AnyType entree){
        int compteur = 0;

        for(int i = 0 ; i < capacite ; i++){
            if(data[i] != null)
                if(data[i].toString().equals(entree.toString()))
                    compteur++;
        }

        return compteur;
    }

    private int prochainPremier(int nombre){
        int prochain = nombre;

        while(!estPremier(prochain))
            prochain++;

        return prochain;
    }


    public AnyType get(AnyType entree){
        boolean estValide = false;
        int position = 0;

        for(int i = 0 ; i < capacite && !estValide ; i++) {
            position = positionnement(entree, i);
            estValide = trouveItem(position, entree);
        }

        if(estValide)
            return data[position];
        else
            return null;
    }


    public int nbElement(){
        return nombreElement;
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
