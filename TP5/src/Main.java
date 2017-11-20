import java.util.ArrayList;
public class Main
{
    public static void main(String[] args)
    {
        testMonceau();
        testNode();


    }

    public static void testNode()
    {
        //Creation d'un tableau de 10 nodes
        ArrayList<Node> nodes = new ArrayList<>();
        for(int i = 0; i<10; i++) {
            nodes.add(new Node(i));
        }


       //Node fusion = nodes.get(0).fusion(nodes.get(1));
        System.out.println(nodes.get(1).ordre);
        try {
            Node fusion = nodes.get(0).fusion(nodes.get(1));
            Node fusion2 = nodes.get(2).fusion(nodes.get(3));
            Node fusionA4 = fusion.fusion(fusion2);
            //nodes.get(2).moveUp();
            System.out.println(fusion.ordre);

            //Affichage arbre d'ordre 2
            ArrayList<Integer> fusion4 = fusionA4.getElementsSorted();
            for(Integer entier : fusion4)
                System.out.print(entier + " ");
            System.out.println();

            //Tester la methode find
            int aTrouve = 3;
            Node trouve = fusionA4.findValue(aTrouve);
            if(trouve != null)
                System.out.println("Noeud " + aTrouve + " : " + trouve.getVal());
            else
                System.out.println("Noeud " + aTrouve + " non trouve");

            //Affichage arbre d'ordre 2
            ArrayList<Node> restants = trouve.delete();
            int i = 0;

        }
        catch(DifferentOrderTrees e){ System.out.println("exeption");}

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
