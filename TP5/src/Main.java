import java.util.ArrayList;
public class Main
{
    public static void main(String[] args)
    {
        testMonceau();

    }

    public static void testNode
    {
        //Creation d'un tableau de 10 nodes
        ArrayList<Node> nodes = new ArrayList<>();
        for(int i = 0; i<10; i++){
            nodes.add(new Node(i));
        }
        //Ajout des enfants a tout les noeuds
        for(int i = 0; i<10; i++){
            nodes.get(i).addEnfant(nodes.get(i));
        }
        //Suprimme les enfants des 5 derniers noeuds
        for(int i = 5; i<10; i++){
            nodes.get(i).removeEnfants(nodes.get(i).getEnfants());
        }

    }


    public static void testMonceau(){

        //Création de monceaux
        Monceau monceau1 = new Monceau();
        Monceau monceau2 = new Monceau();





        //Test insertion dans le monceau
        int nombresElements = 15;
        for(int i = nombresElements - 1 ; i >= 0 ; i--) {
            monceau1.insert(i);
            monceau2.insert(2 * i);
        }


        //Test fusion de monceaux
        monceau1.fusion(monceau2);


        //Test supression
        int aRetirer = 15;
        System.out.println("Arbres avec l'élément " + aRetirer);
        monceau1.print();

        monceau1.delete(aRetirer);
        System.out.println("Arbres sans  l'élément " + aRetirer);
        monceau1.print();
    }
}
