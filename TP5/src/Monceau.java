
import java.util.ArrayList;
import java.util.Collections;

public class Monceau {
    ArrayList<Node> arbres;
    
    public Monceau() {
        arbres = new ArrayList<Node>();
    }


    /**
     * Permet fusionner deux monceaux ensemble
     *
     * @param  autre : monceau avec lequel faire la fusion
     * @return void
     */
    public void fusion(Monceau autre) throws DifferentOrderTrees {
        // à compléter
        int ordreMax = Math.max(ordreMax(), autre.ordreMax());
        ArrayList<Node> monceauFusion = new ArrayList<Node>();
        ArrayList<Node> trouve = new ArrayList<>();

        for(int i = 0 ; i <= ordreMax ; i++){
            Node recherche = null;

            //Rechercher parmis les monceau en cours
            recherche = find(i);
            if(recherche != null)
                trouve.add(recherche);

            //Rechercher parmis les monceau en parametre
            recherche = autre.find(i);
            if(recherche != null)
                trouve.add(recherche);

            switch(trouve.size()){
                case 0 :
                    //On ne fait rien
                    break;

                case 1 :
                    //On l'ajoute au monceau final
                    monceauFusion.add(trouve.get(0));
                    break;

                case 3 :
                    //On ajoute le dernier au monceau final
                    monceauFusion.add(trouve.remove(2));

                case 2 :
                    //On fusionne les deux nodes et le resultat demeure dans le vecteur comme retenue
                    Node pourFusion1 = trouve.remove(0);
                    Node pourFusion2 = trouve.remove(0);
                    trouve.add(pourFusion1.fusion(pourFusion2));
                    break;
            }
        }

        //Remettre la retenue final dans le monceau fusionne
        if(trouve.size() == 1)
            monceauFusion.add(trouve.remove(0));

        //Remplacer l'arbre courant par celui fusionne
        arbres = monceauFusion;
    }

    /**
     * Permet de connaitre l'ordre du node le plus grand du monceau
     *
     * @return int : le plus grand ordre du monceau
     */
    private int ordreMax(){
        int ordreMax = 0;
        for(Node element : arbres)
            if(element.ordre > ordreMax)
                ordreMax = element.ordre;

        return ordreMax;
    }

    /**
     * Permet d'insérer une valeur dans le monceau
     *
     * @param  ordre : ordre du node a trouver
     * @return node : node avec l'ordre recherche
     */
     private Node find(int ordre){
         for(Node element : arbres)
             if(element.ordre == ordre)
                 return element;

         return null;
     }

    /**
     * Permet d'insérer une valeur dans le monceau
     *
     * @param  val : valeur à insérer
     * @return void
     */
    public void insert(int val) {
        // à compléter


    }

    /**
     * Permet de supprimer un noeud avec la valeur passée en paramètre
     *
     * @param  val : valeur du noeud à supprimer
     * @return boolean : si le monceau possède a ete supprimer
     */
    public boolean delete (int val) {
        // à compléter
        
        return false;
    }


    /**
     * Permet d'afficher les éléments du monceau
     *
     * @return void
     */
    public void print() {
        // à compléter
        //Trier les nodes avant de les afficher
        Collections.sort(arbres);

        for(Node element : arbres)
            element.print(" ");
    }
}
