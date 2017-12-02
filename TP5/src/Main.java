import java.util.ArrayList;
public class Main
{
    public static void main(String[] args)
    {
        try {
            testNode();
            testMonceau();
        }
        catch (DifferentOrderTrees e){
            System.out.println("Erreur de fusion : ordre different");
        }
    }

    public static void testNode()
    {
        //Creation d'un tableau de 10 nodes
        ArrayList<Node> nodes = new ArrayList<>();
        for(int i = 0; i<10; i++) {
            nodes.add(new Node(i));
        }

        try {
            Node fusion = nodes.get(0).fusion(nodes.get(1));
            Node fusion2 = nodes.get(2).fusion(nodes.get(3));
            Node fusionA4 = fusion.fusion(fusion2);

            Node fusionB = nodes.get(4).fusion(nodes.get(5));
            Node fusion2B = nodes.get(6).fusion(nodes.get(7));
            Node fusionB4 = fusionB.fusion(fusion2B);

            Node fusionA8 = fusionA4.fusion(fusionB4);

            //Affichage arbre d'ordre 2
            System.out.println("Affichage arbre ordre 2 :");
            fusionA4.print(" -> ");
            ArrayList<Integer> fusion4 = fusionA4.getElementsSorted();
            System.out.println("Affichage des elements de l'arbre en ordre :");
            for(Integer entier : fusion4)
                System.out.print(entier + " ");
            System.out.println();

            //Tester la methode find\\
            System.out.println("\nTeste methode find :");
            for(int i = 0 ; i < 6 ; i++) {
                int aTrouve = i;
                Node trouve = fusionA4.findValue(aTrouve);
                if (trouve != null)
                    System.out.println("Noeud " + aTrouve + " : " + trouve.getVal());
                else
                    System.out.println("Noeud " + aTrouve + " : non trouve");
            }

            System.out.println("\nAffichage arbre ordre 3 :");
            fusionA8.print(" -> ");
        }
        catch(DifferentOrderTrees e){ System.out.println("exception");}

    }


    public static void testMonceau() throws DifferentOrderTrees {

        //Création de monceaux
        Monceau monceau1 = new Monceau();
        Monceau monceau2 = new Monceau();




        //Test insertion dans le monceau
        int nombresElements = 15;
        for(int i = nombresElements - 1 ; i >= 0 ; i--) {
            monceau1.insert(2*i);
            monceau2.insert(2*i + 1);
        }


        //Test fusion de monceaux
        System.out.println("\nMonceau 1 :");
        monceau1.print();
        System.out.println("Monceau 2 :");
        monceau2.print();
        System.out.println("\nAffichage monceau 1 fusione avec 2 :");
        monceau1.fusion(monceau2);
        monceau1.print();


        //Test supression
        System.out.println("\nTeste de delete sur Monceau :");
        int aRetirer = 15;
        System.out.println("Monceau avec l'élément " + aRetirer);
        monceau2.print();

        if(monceau2.delete(aRetirer))
            System.out.println("L'element " + aRetirer + " a bien ete retire");
        else
            System.out.println("L'element " + aRetirer + " n'est pas present");

        System.out.println("\nMonceau sans l'élément " + aRetirer);
        monceau2.print();

        System.out.println("\nTest de delete sur Monceau d'un nombre inexistant :");
        if(monceau2.delete(aRetirer))
            System.out.println("Erreur : le nombre a deja ete retire");
        else
            System.out.println("Le nombre " + aRetirer + " n'est pas present dans le monceau");
    }
}
