package probleme1;

import java.util.ArrayList;
import java.util.Random;

public class QuadraticSpacePerfectHashing<AnyType> 
{
	static int p = 46337;

	int a, b;
	AnyType[] items;

	QuadraticSpacePerfectHashing()
	{
		a=b=0; items = null;
	}

	QuadraticSpacePerfectHashing(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	public void SetArray(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	public int Size()
	{
		if( items == null ) return 0;

		return items.length;
	}

	public boolean containsKey(int key)
	{
		// A completer
		if(key < items.length && key >= 0)
			if(items[key]!= null)
				return true;

		return false;
	}

	public boolean containsValue(AnyType x )
	{
		// A completer
		if(containsKey(getKey(x)))
			return true;
		else
			return false;
	}

	public void remove (AnyType x) {
		// A completer
		if(containsValue(x))
			items[getKey(x)] = null;
	}

	public int getKey (AnyType x) {
		// A completer
		if(x.equals(null))
			return -1;

		if(items.length != 0)
			return ((a * x.hashCode() + b) % p) % items.length;
		else return -1;
	}

	@SuppressWarnings("unchecked")
	private void AllocateMemory(ArrayList<AnyType> array)
	{
		if(array == null || array.size() == 0 || (array.size() * array.size()) >= p)
		{
			// A completer
			items = null;
			return;
		}

		if(array.size() == 1)
		{
			items = (AnyType[]) new Object[1];
			items[0] = array.get(0);
			a = b = 0;
			return;
		}
		a = b = 0;

		// A completer
		boolean estValide = false;
		ArrayList<Integer> indice = new ArrayList<Integer>();

		while(!estValide){
			indice = new ArrayList<Integer>();
			estValide = true;
			genererAleatoireAB();

			for (AnyType element : array) {
				Integer position = Math.abs(((a * element.hashCode() + b) % p) % (array.size() * array.size()));

				if (!indice.contains(position))
					indice.add(position);
				else {
					estValide = false;
					indice = null;
					break;
				}
			}
		}

		items = (AnyType[]) new Object[array.size() * array.size()];

		for(int i = 0 ; i < array.size() ; i++)
			items[indice.get(i)] = array.get(i);

		return;
	}

	private void genererAleatoireAB(){
		Random generator = new Random( System.nanoTime() );

		do{
			a = generator.nextInt(p);
		}while(a <= 0);

		do{
			b = generator.nextInt(p);
		}while(b < 0);

	}
	
	public String toString () {
		String result = "";
		
		// A completer
		for(AnyType element : items){
			if(element != null) {
				result += "(" + getKey(element) + ", " + element.toString() + "), ";
			}
		}

		result = result.substring(0, result.length() - 2);
		result += ".";

		return result; 
	}

	public void makeEmpty () {
		   // A completer
		   items = null;
   	}
}
