import java.util.ArrayList;
///////////////////////////////////////////////////////////////////////////////
//
// Main Class File:  BibliothequeMain.java
// File:             BibliothequeBst.java
// Semester:         Automne 2017
//
// Author:           Jean-Frederic Fontaine 1856632
//                   Simon Turcotte         1838092
//Description:
//            Implémentation de l'interface IBibliotheque
//            à l'aide d'un arbre de recherche binaire.
//////////////////////////// 80 columns wide //////////////////////////////////

public class BibliothequeBst implements IBibliotheque
{
    private BST<String> livres = new AvlTree<String>();

    // Complexité: O(log(n))
    // Explication:
    // Nous travaillons ici avec un arbre AVL,
    public void ajouterLivre(String livre)
    {
       livres.insert(livre);
    }

    // Complexité: O(log(n))
    // Explication: Nous travaillons avec un arbre équilibré. La recherche d'un
    //              livre se fait donc en n(logn). Effectivement, les strings
    //              sont un type dit integral. Ce type permet donc d'etre
    //              comparé. Il est donc possible de placer les noeuds dans un
    //              ordre logique, ceci permet ensuite de profiter des propriétés
    //              d'équilibrage d'AVL.
    public boolean contientLivre(String livre)
    {
        return livres.contains(livre);
    }

    // Complexité: O(n)
    // Explication: Il ne suffit ici que de parcourir
    //              l'arbre de recherche binaire selon
    //              un parcours en ordre/ascendant.
    public String afficherLivresAlpha()
    {
        ArrayList<String> tempo = livres.traverseInOrder();

        String aRetourner = "";
        for(String elems:tempo)
        {
         aRetourner+=elems+"\n";
        }

        return aRetourner;
    }

    // Complexité: O(n)
    // Explication: Il ne suffit ici que de parcourir
    //              l'arbre de recherche binaire selon
    //              un parcours en ordre inverse/descendant.
    public String afficherLivresAlphaInverse()
    {
        ArrayList<String> tempo = livres.traverseReverseOrder();

        String aRetourner = "";
        for(String elems:tempo)
        {
            aRetourner+=elems +"\n";
        }

        return aRetourner;
    }
}