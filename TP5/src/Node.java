
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
public class Node {

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
 * Permet de fusion deux arbres binomiaux de meme niveaux.
 *
 * @param  autre; l'arbre a fusionner
 * @return Node: le resultat de la fusion
 */
    public Node fusion(Node autre) throws DifferentOrderTrees {

        if(this.ordre != autre.ordre)
            throw new DifferentOrderTrees();

        Node plusPetit;
        Node plusGrand;
        if( autre.getVal() < valeur){
            plusPetit = autre;
            plusGrand = this;
        }
        else {
            plusPetit = this;
            plusGrand = autre;
        }
        plusPetit.addEnfant(plusGrand);
        plusGrand.parent = plusPetit;
        plusPetit.ordre++;
        return plusPetit;
    }
    /**
     * Permet de d'interchanger deux nodes, c'est-a-dire d'interchanger
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
     * @return ArrayList<Node> : arbres binomaiaux restants apres la supression du noeud
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

    public void print(String tabulation) {
        // à compléter
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
}
