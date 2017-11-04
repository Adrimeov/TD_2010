package probleme2;

public class HashMapTest 
{

   public static void main(String[] args) 
   {
      MyHashMap<Integer, String> mhmap = new MyHashMap<Integer, String>();
      mhmap.put(25, "patate");
      mhmap.put(15, "chou-rave");
      mhmap.put(10, "patate");
      mhmap.put(25, "chou-rave");
      
      System.out.println( mhmap.get(25) );
      System.out.println( mhmap.get(10) );

      //Test du nombre d'occurences
      String nom = "patate";
      System.out.println("Nombre d'occurence de " + nom + " : "+ mhmap.nbreOccurence(nom));
      nom = "chou-rave";
      System.out.println("Nombre d'occurence de " + nom + " : "+ mhmap.nbreOccurence(nom));
   }

}
