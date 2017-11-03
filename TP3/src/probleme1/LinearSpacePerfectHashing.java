package probleme1;

import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList;
import java.util.Set;

public class LinearSpacePerfectHashing<AnyType>
{
	static int p = 46337;

	QuadraticSpacePerfectHashing<AnyType>[] data;
	int a, b;

	LinearSpacePerfectHashing()
	{
		a=b=0; data = null;
	}

	LinearSpacePerfectHashing(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	public void SetArray(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	@SuppressWarnings("unchecked")
	private void AllocateMemory(ArrayList<AnyType> array)
	{
		Random generator = new Random( System.nanoTime() );

		if(array == null || array.size() == 0|| array.size() >= p)
		{
			data = null;
			return;
		}
		if(array.size() == 1)
		{
			data = new QuadraticSpacePerfectHashing[1];
			data[0] = new QuadraticSpacePerfectHashing(array);
			a = b = 0;
			return;
		}

		data = new QuadraticSpacePerfectHashing[array.size()];
		HashMap<Integer, ArrayList<AnyType>> dictionnaire = new HashMap<>();
		genererAleatoireAB();

		for(AnyType element : array)
		{
			int hashcode = element.hashCode();
			Integer position = Math.abs(((a * element.hashCode() + b) % p) % (array.size()));
			if(dictionnaire.containsKey(position))
			{
				dictionnaire.get(position).add(element);
			}
			else
			{
				ArrayList<AnyType> innerDictio = new ArrayList<>();
				innerDictio.add(element);
				dictionnaire.put(position, innerDictio);
			}
		}
		Set<Integer> setClees = dictionnaire.keySet();
		for(Integer element:setClees)
		{
			data[element] = new QuadraticSpacePerfectHashing<>(dictionnaire.get(element));
		}
		return;
		// A completer
	}

	public int Size()
	{
		if( data == null ) return 0;
		int size = 0;
		for(int i=0; i<data.length; ++i)
		{
			size += (data[i] == null ? 1 : data[i].Size());
		}
		return size;
	}

	public boolean containsKey(int key)
	{
		if(!(key >= data.length || key<0))
		{
			if(data[key] != null)
				return true;
		}
		return false;
	}
	
	public int getKey (AnyType x) {
		if(data.length != 0 && !x.equals(null))
			return ((a * x.hashCode() + b) % p) % (data.length);
		return -1;
	}
	
	public boolean containsValue (AnyType x) {
		Integer clee = getKey(x);
		if(containsKey(clee))
			return data[clee].containsValue(x);
		return false;
	}
	
	public void remove (AnyType x) {
		if(containsValue(x))
		{
			data[getKey(x)].remove(x);
		}
	}

	public String toString () {
		String result = "";
		
		for(int i = 0; i<data.length; i++)
			{

				result+= "["+(i+1)+"] -> ";
				if(containsKey(i))
				{
					result+= data[i].toString();
				}
				result+= "\n";
			}

		return result; 
	}

	public void makeEmpty ()
	{
		data = null;


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
	
}
