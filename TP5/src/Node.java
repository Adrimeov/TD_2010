
import java.util.ArrayList;
import java.util.Collections;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author maitr
 */
public class Node implements Comparable<Node> {

    public int ordre;
    public Node parent;

    private int valeur;
    private ArrayList<Node> enfants;

    public Node(int valeur) {
        this.valeur = valeur;
        ordre = 0;
        this.parent = null;
        enfants = new ArrayList<Node>();
    }

    public Node(int valeur, Node parent) {
        ordre = 0;
        this.valeur = valeur;
        this.parent = parent;
        enfants = new ArrayList<Node>();
    }

    public int getVal() {
        return valeur;
    }

    public ArrayList<Node> getEnfants() {
        return enfants;
    }

    public void addEnfant(Node enfant) { enfants.add(enfant);}


    public boolean removeEnfant(Node enfant) {
        if (enfants.contains(enfant)) {
            enfants.remove(enfant);
            return true;
        }
        return false;
    }

    public void removeEnfants(ArrayList<Node> enfants) {
        this.enfants.removeAll(enfants);
    }

/**
 * Permet de fusioner deux arbres binomiaux de meme niveaux.
 *
 * @param  autre : l'arbre a fusionner
 * @return Node : le resultat de la fusion
 */
    public Node fusion(Node autre) throws DifferentOrderTrees {

        if(this.ordre != autre.ordre)
            throw new DifferentOrderTrees();

        //Clone des nodes afin de ne pas modifier ceux recus
        Node thisClone = this.clone();
        Node autreClone = autre.clone();

        Node plusPetit;
        Node plusGrand;
        if( autre.valeur < thisClone.valeur){
            plusPetit = autreClone;
            plusGrand = thisClone;
        }
        else {
            plusPetit = thisClone;
            plusGrand = autreClone;
        }
        plusPetit.addEnfant(plusGrand);
        plusGrand.parent = plusPetit;
        plusPetit.ordre++;
        return plusPetit;
    }
    /**
     * Permet d'interchanger deux nodes, c'est-a-dire d'interchanger
     * le node courant avec son parent.
     *
     * @return None
     */
    private void moveUp() {
        // à compléter
        Node parentCourant = parent;
        ArrayList<Node> enfantCourant = enfants;

        if(parent != null) {
            Node grandPapa = parent.parent;

            for( Node element: enfants){
                element.parent = parentCourant;
            }
            //on enleve le noeud courant de la liste denfant de son parents
            parentCourant.enfants.remove(this);
            //ajoute le noeud parents dans la liste denfant de lobjet courant
            parentCourant.enfants.add(parentCourant);
            //on change le pere du pere courant pour lobjet courant
            parentCourant.parent = this;
            //On interchange les ordres
            int temp = this.ordre;
            this.ordre = parentCourant.ordre;
            parentCourant.ordre = temp;
            //On associe les enfants du noeuds courant a l'ancien parent
            ArrayList<Node> listeTemp = parentCourant.enfants;
            parentCourant.enfants = this.enfants;
            //On associe les nouveaux enfants au noeud courant
            this.enfants = listeTemp;

            if(grandPapa != null){
                this.parent = grandPapa;
                grandPapa.enfants.remove(parentCourant);
                grandPapa.enfants.add(this);
            }
            else
                this.parent = null;
        }
    }


    /**
     * Permet de supprimer le noeud courant de l'arbre binomial
     *
     * @return ArrayList<Node> : arbres binomiaux restants apres la supression du noeud
     */
    public ArrayList<Node> delete() {
        // à compléter
        while(parent != null)
            moveUp();

        ArrayList<Node> noeudsRestants = new ArrayList<>();
        for(Node element : enfants){
            element.parent = null; //Ajustement du parent des noeuds
            noeudsRestants.add(element);
        }

        return noeudsRestants;
    }

    /**
     * Permet d'afficher l'arbre binomial
     *
     * @return void
     */
    public void print(String tabulation) {
        // à compléter
        print(tabulation, 1, true);
    }

    /**
     * Permet d'afficher chacun des noeuds de facon recursive
     *
     * @param tabulation : separateur entre les elements
     * @param profondeur : hauteur du noeud dans l'arboresence + 1
     * @param premierLigne : si le noeud est le premier de sa ligne dans l'affichage
     *
     * @return void
     */
    private void print(String tabulation, int profondeur, boolean premierLigne){
        //Definition du format d'affichage des nombres
        int nombreAffichageInt = 2; //Nombre de chiffre des nombres
        String format = "%" + nombreAffichageInt + "d";

        //Definition de l'espace avant le premier nombre sur la ligne
        String avantLigne = "";
        for(int i = 0 ; i < tabulation.length() + nombreAffichageInt ; i++)
            avantLigne += " ";

        //Ajouter une tabulation
        if(!premierLigne)
            System.out.print(tabulation);

        //Si le noeud est une feuille
        if(enfants.size() == 0) {
            System.out.printf(format, valeur);
            System.out.println();
        }
        else {
            //Affichage du noeud courant
            System.out.printf(format, valeur);

            for (int i = 0; i < enfants.size(); i++) {
                //Ajouter des tabulations si l'enfant en cours n'est pas le premier
                if (i != 0) {
                    for(int j = 0 ; j < profondeur ; j++)
                        System.out.print(avantLigne);
                    enfants.get(i).print(tabulation, profondeur + 1, true);
                }
                else
                    enfants.get(i).print(tabulation, profondeur + 1, false);
            }
        }
    }


    /**
     * Permet de retrouver un noeud avec la valeur passée en paramètre
     *
     * @param  valeur : valeur du noeud à trouver
     * @return Node : noeud ayant la valeur recherchée
     */
    public Node findValue(int valeur) {
        // à compléter
        //Si le noeud courant a la valeur recherchée, le retourner
        if(this.valeur == valeur)
            return this;

        //Explorer chacun des enfants du noeud
        for(Node element : enfants){
            //Rechercher le noeud enfant seulement lorsque la valeur peut s'y retrouver
            if(element.valeur <= valeur) {
                Node trouve = element.findValue(valeur);
                if (trouve != null)
                    return trouve; //Valeur a été trouvée
            }
        }

        //La valeur n'a pas été trouvée
        return null;
    }


    /**
     * Permet de retourner la liste des valeurs en ordre croissant
     *
     * @return ArrayList<Integer> : liste des valeurs du sous-arbre
     */
    public ArrayList<Integer> getElementsSorted() {
    	// à compléter
        ArrayList<Integer> liste = new ArrayList<>();

        //Ajouter le noeud courant
        liste.add(valeur);

        //Ajouter les valeurs de chacun des enfants
        ArrayList<Integer> listeEnfant;
        for (Node element : enfants) {
            listeEnfant = element.getElementsSorted();
            for (Integer valeur : listeEnfant)
                liste.add(valeur);
        }

        Collections.sort(liste);

    	return liste;
    }


    /**
     * Permet de comparer deux node ensemble selon leur ordre
     *
     * @param node : node de comparaison
     * @return int : resultat de la comparaison
     */
    public int compareTo(Node node){ return ordre - node.ordre; }

    /**
     * Permet de cloner un node est ses enfants
     *
     * @return Node : resultat du clonage
     */
    public Node clone(){
        Node clone = new Node(valeur);
        clone.ordre = ordre;

        for(Node element : enfants)
            clone.addEnfant(element.clone());

        return clone;
    }
}
