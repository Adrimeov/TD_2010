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
            nodes.get(2).moveUp();
            System.out.println(fusion.ordre);
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
