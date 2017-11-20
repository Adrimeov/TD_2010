
import java.util.ArrayList;

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
     * @param  None
     * @return None
     */
    public void moveUp() {
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

            if(grandPapa != null){
                this.parent = grandPapa;
                grandPapa.enfants.remove(parentCourant);
                grandPapa.enfants.add(this);
            }
            else
                this.parent = null;
        }
    }

    public ArrayList<Node> delete() {
        // à compléter
        return null;
    }

    public void print(String tabulation) {
        // à compléter
    }
    
    public Node findValue(int valeur) {
        // à compléter
        return null;
    }
    
    public ArrayList<Integer> getElementsSorted() {
    	// à compléter
    	
    	return null;
    }
}
