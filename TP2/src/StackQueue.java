import java.util.Stack;

public class StackQueue<AnyType> implements Queue<AnyType>
{
	private int size = 0; // Nombre d'éléments dans la file.
	private Stack<AnyType> inStack;
	private Stack<AnyType> outStack;
   
	@SuppressWarnings("unchecked")
	public StackQueue()
	{
		inStack = new Stack<AnyType>();
		outStack = new Stack<AnyType>();
        // À compléter
	}
	
	// Indique si la file est vide.
	public boolean empty() 
	{ 
		return size == 0; 
	}
	
	// Retourne la taille de la file.
	public int size() 
	{ 
		return size; 
	}
	
	// Retourne l'élément en tête de file.
	// Retourne null si la file est vide.
	// Complexité asymptotique: O(1) (ammorti)
	public AnyType peek() {

		if (inStack.empty() && outStack.empty())
			return null;

		else if(!outStack.empty()){
			return outStack.peek();
		}
		else{
			while(!inStack.empty()){
				outStack.push(inStack.pop());
			}
			return outStack.peek();
		}
	}
	
	// Retire l'élément en tête de file.
	// Complexité asymptotique: O(1) (ammorti)
	public void pop() throws EmptyQueueException
	{
		if(peek() == null)
			throw  new EmptyQueueException();
		else {
			outStack.pop();
			size--;
		}
	}
	
	// Ajoute un élément a la fin de la file.
	// Complexité asymptotique: O(1)
	public void push(AnyType item)
	{
		inStack.push(item);
		size++;
	}
}

