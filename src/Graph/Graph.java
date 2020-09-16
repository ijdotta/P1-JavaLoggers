package Graph;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;

/**
 * Class Graph
 * @author Ignacio Joaquín Dotta - LU 126269
 */
public class Graph {
	
	private static Logger logger;
	
	private boolean[][] adj;
	private HashMap<Integer, Integer> matrix_index;
	
	/**
	 * Crea un grafo vacío.
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
	 * Añade un nodo con rótulo node al grafo.
	 * @param node Rótulo del nodo.
	 */
	public void addNode(int node) {
		Integer index = matrix_index.get(node);
		
		if (index == null) {
			index = matrix_index.size();
			matrix_index.put(node, index);
			
			if (adj.length <= index)
				resize();
			
			logger.fine("Se añadió el nodo " + node + ". ");
		}
		else {
			logger.info("El nodo " + node + " ya se encuentra en el grafo. ");
		}
	}
	
	/**
	 * Crea una nueva matriz de mayor tamaño.
	 * Por simplicidad se ignora la posible fragmentación interna producida por
	 * removeNode.
	 */
	private void resize() {
		boolean [][] old_adj = adj;
		int new_size = old_adj.length * 2;
		adj = new boolean[new_size][new_size];
		
		logger.warning("La matriz de adyacencias aumentó su tamaño (" + old_adj.length + " -> " + new_size + ")");
		
		for (int i = 0; i < old_adj.length; i++)
			for (int j = 0; j < old_adj.length; j++)
				adj[i][j] = old_adj[i][j];
	}
	
	/**
	 * Crea una arco entre los nodos con rótulo node1 y node2, si estos pertenecen al grafo
	 * y si aún no existía un arco entre ellos.
	 * @param node1 Rótulo del nodo de origen.
	 * @param node2 Rótulo del nodo de destino.
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
				logger.fine("Se añadió un arco (" + node1 + ", " + node2 + "). ");
			}
		}
		else {
			logger.info("Al menos uno de los nodos no pertenece al grafo. ");
		}
	}
	
	/**
	 * Remueve el nodo con rótulo node si este existe.
	 * @param node Rótulo del nodo a eliminar.
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
	 * Remueve el arco con origen y destino en los arcos con rótulos node1 y node2, respectivamente,
	 * si éstos pertenecen al grafo y el arco existe.
	 * @param node1 Rótulo del nodo de origen.
	 * @param node2 Rótulo del nodo de destino.
	 */
	public void removeEdge(int node1, int node2) {
		Integer i1, i2;
		i1 = matrix_index.get(node1);
		i2 = matrix_index.get(node2);
		
		if (i1 != null && i2 != null) {
			if (adj[i1][i2]) {
				adj[i1][i2] = false;
				logger.fine("Se removió el arco (" + node1 + ", " + node2 + "). ");
			}
			else
				logger.warning("El arco (" + node1 + ", " + node2 + ") no existe. ");
		}
		else {
			logger.info("Al menos uno de los nodos no pertenece al grafo. ");
		}
	}
	
}
