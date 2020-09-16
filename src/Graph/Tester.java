package Graph;

public class Tester {
	
	public static void main(String[] args) {
		Graph g = new Graph();
		
		//Añadir nodos
		for (int i = -5; i < 10; i++)
			g.addNode(i);
		
		//Añadir arcos
		g.addEdge(1, 1);
		g.addEdge(1, 1);
		g.addEdge(2, 8);
		g.addEdge(10, 15);
		g.addEdge(2, 9);
		g.addEdge(1, 2);
		g.addEdge(2, -3);
		g.addEdge(-4, 1);
		g.addEdge(-3, -4);
		
		//Remover nodos pares
		g.removeNode(1);
		g.removeNode(6);
		g.removeNode(6);
		g.removeNode(6);
		g.removeNode(-3);
		g.removeNode(-586);
		
		//Remover arcos
		g.removeEdge(1, 1);
		g.removeEdge(1, 1);
		g.removeEdge(2, 8);
		g.removeEdge(10, 15);
		g.removeEdge(-4, 1);
		g.removeEdge(-3, -4);
		g.removeEdge(10, 15);
		
	}
	
}
