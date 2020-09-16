package Graph;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;

/**
 * Class Graph
 * @author Ignacio Joaqu�n Dotta - LU 126269
 */
public class Graph {
	
	private static Logger logger;
	
	private boolean[][] adj;
	private HashMap<Integer, Integer> matrix_index;
	
	/**
	 * Crea un grafo vac�o.
	 */
	public Graph() {
		adj = new boolean[10][10];
		matrix_index = new HashMap<Integer, Integer>();
		
		if (logger == null) {
			
			logger = Logger.getLogger(Graph.class.getName());
			
			Handler hnd = new ConsoleHandler();
			hnd.setLevel(Level.FINE);
			logger.addHandler(hnd);
			
			logger.setLevel(Level.WARNING);
			
			Logger rootLoger = logger.getParent();
			for (Handler h : rootLoger.getHandlers())
				h.setLevel(Level.OFF);
		}
	}
	
	/**
	 * A�ade un nodo con r�tulo node al grafo.
	 * @param node R�tulo del nodo.
	 */
	public void addNode(int node) {
		Integer index = matrix_index.get(node);
		
		if (index == null) {
			index = matrix_index.size();
			matrix_index.put(node, index);
			
			if (adj.length <= index)
				resize();
			
			logger.fine("Se a�adi� el nodo " + node + ". ");
		}
		else {
			logger.info("El nodo " + node + " ya se encuentra en el grafo. ");
		}
	}
	
	/**
	 * Crea una nueva matriz de mayor tama�o.
	 * Por simplicidad se ignora la posible fragmentaci�n interna producida por
	 * removeNode.
	 */
	private void resize() {
		boolean [][] old_adj = adj;
		int new_size = old_adj.length * 2;
		adj = new boolean[new_size][new_size];
		
		logger.warning("La matriz de adyacencias aument� su tama�o (" + old_adj.length + " -> " + new_size + ")");
		
		for (int i = 0; i < old_adj.length; i++)
			for (int j = 0; j < old_adj.length; j++)
				adj[i][j] = old_adj[i][j];
	}
	
	/**
	 * Crea una arco entre los nodos con r�tulo node1 y node2, si estos pertenecen al grafo
	 * y si a�n no exist�a un arco entre ellos.
	 * @param node1 R�tulo del nodo de origen.
	 * @param node2 R�tulo del nodo de destino.
	 */
	public void addEdge(int node1, int node2) {
		Integer i1, i2;
		i1 = matrix_index.get(node1);
		i2 = matrix_index.get(node2);
		
		if (i1 != null && i2 != null) {
			
			if (adj[i1][i2]) {
				logger.info("Ya existe un arco (" + node1 + ", " + node2 + "). ");
			}
			else {
				adj[i1][i2] = true;
				logger.fine("Se a�adi� un arco (" + node1 + ", " + node2 + "). ");
			}
		}
		else {
			logger.info("Al menos uno de los nodos no pertenece al grafo. ");
		}
	}
	
	/**
	 * Remueve el nodo con r�tulo node si este existe.
	 * @param node R�tulo del nodo a eliminar.
	 */
	public void removeNode(int node) {
		Integer index = matrix_index.remove(node);
		
		if (index == null) {
			logger.warning("El nodo no pertenece al grafo. ");
		}
		else {
			
			//Anular arcos incidentes.
			for (int i = 0; i < adj.length; i++) {
				adj[index][i] = adj[i][index] = false;
			}
			
			logger.fine("El nodo " + node + " ha sido removido. ");
		}
	}

	/**
	 * Remueve el arco con origen y destino en los arcos con r�tulos node1 y node2, respectivamente,
	 * si �stos pertenecen al grafo y el arco existe.
	 * @param node1 R�tulo del nodo de origen.
	 * @param node2 R�tulo del nodo de destino.
	 */
	public void removeEdge(int node1, int node2) {
		Integer i1, i2;
		i1 = matrix_index.get(node1);
		i2 = matrix_index.get(node2);
		
		if (i1 != null && i2 != null) {
			if (adj[i1][i2]) {
				adj[i1][i2] = false;
				logger.fine("Se removi� el arco (" + node1 + ", " + node2 + "). ");
			}
			else
				logger.warning("El arco (" + node1 + ", " + node2 + ") no existe. ");
		}
		else {
			logger.info("Al menos uno de los nodos no pertenece al grafo. ");
		}
	}
	
}
