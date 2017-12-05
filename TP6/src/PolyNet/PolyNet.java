package PolyNet;

import java.util.HashSet;
import java.util.PriorityQueue;

public class PolyNet {
    private PolyNetNode[] nodes;

    public PolyNet(PolyNetNode[] nodes)
    {
        this.nodes = nodes;
    }

    // MST (using Prim's algorithm).
    public int computeTotalCableLength()
    {
        int totalCableLength = 0;
        PriorityQueue<PolyNetConnection> knownConnections = new PriorityQueue<>();
        HashSet<PolyNetNode> visitedNodes = new HashSet<>();

        //si le graph est vide on sort.
        if(nodes.length == 0)
            return 0;

        //On prend ajoute les arretes du premier node dans la queue
        PolyNetNode premier = nodes[0];
        knownConnections.addAll(premier.getConnections());
        visitedNodes.add(premier);

        //Tant que la queue n'est pas vide on continue a etudier le graph
        while(!knownConnections.isEmpty()){
            PolyNetConnection priorite = knownConnections.peek();
            knownConnections.remove(priorite);
            PolyNetNode nodeConnecter = priorite.getConnectedNode();

            //Si le node vers lequel l'arrete pointe na pas ete etudier
            //on ajoute ses arretes a la queue et on incorpore le poid
            // de larrete qui nous y a mener a la solution. On ajoute
            // le node a la liste des visiter.
            if(!visitedNodes.contains(nodeConnecter)) {
                knownConnections.addAll(nodeConnecter.getConnections());
                knownConnections.remove(priorite);
                visitedNodes.add(nodeConnecter);
                totalCableLength += priorite.getDistance();
            }
        }
        return totalCableLength;
    }
}
