package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class Graph
 * @author Ignacio Joaqu�n Dotta
 */
public class Graph {
	
	/*
	 * Observaci�n: a fines pr�cticos, y como se indic� en clase,
	 * se descuidan algunos detalles de la implementaci�n.
	 */
	
	private static Logger logger;
	
	private ArrayList<Integer> nodes;
	private HashMap<Integer, HashSet<Integer>> edges;
	
	/**
	 * Crea un grafo vac�o.
	 */
	public Graph() {
		nodes = new ArrayList<Integer>();
		edges = new HashMap<Integer, HashSet<Integer>>();
		
		if (logger == null) {
			
			logger = Logger.getLogger(Graph_old.class.getName());
			
			Handler hnd = new ConsoleHandler();
			hnd.setLevel(Level.FINE);
			logger.addHandler(hnd);
			
			logger.setLevel(Level.ALL);
			
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
		if (nodes.contains(node)) {
			logger.info("El nodo " + node + " ya pertenece al grafo. ");
		}
		else {
			nodes.add(node);
			edges.put(node, new HashSet<Integer>());
			
			logger.fine("Se a�adi� el nodo " + node);
		}
	}
	
	/**
	 * Crea una arco entre los nodos con r�tulo node1 y node2, si estos pertenecen al grafo
	 * y si a�n no exist�a un arco entre ellos.
	 * @param node1 R�tulo del nodo de origen.
	 * @param node2 R�tulo del nodo de destino.
	 */
	public void addEdge(int node1, int node2) {
		HashSet<Integer> endNodes;
		
		if (nodes.contains(node1) && nodes.contains(node2)) {
			endNodes = edges.get(node1);
			if (endNodes.contains(node2)) {
				logger.info("El arco (" + node1 + ", " + node2 + ") ya existe. ");
			}
			else {
				endNodes.add(node2);
				logger.fine("El arco (" + node1 + ", " + node2 + ") fue a�adido. ");
			}
		}
		else {
			logger.warning("Uno o ambos nodos no pertenecen al grafo. ");
		}
	}
	
	/**
	 * Remueve el nodo con r�tulo node si este existe.
	 * @param node R�tulo del nodo a eliminar.
	 */
	public void removeNode(int node) {
		int index = nodes.indexOf(node);
		
		if (index >= 0) {
			
			edges.remove(node);
			
			for (HashSet<Integer> end : edges.values()) {
				if (end.contains(node)) {
					end.remove(node);
				}
			}
			
			nodes.remove(index);
			
			logger.fine("El nodo " + node + " fue removido. ");
		}
		else {
			logger.info("El nodo " + node + " no pertenece al grafo. ");
		}
	}
	
	/**
	 * Remueve el arco con origen y destino en los arcos con r�tulos node1 y node2, respectivamente,
	 * si �stos pertenecen al grafo y el arco existe.
	 * @param node1 R�tulo del nodo de origen.
	 * @param node2 R�tulo del nodo de destino.
	 */
	public void removeEdge(int node1, int node2) {
		if (nodes.contains(node1) && nodes.contains(node2)) {
			boolean removed = edges.get(node1).remove(node2);
			if (removed) {
				logger.fine("El arco (" + node1 + ", " + node2 + ") fue removido. ");
			}
			else {
				logger.info("El arco (" + node1 + ", " + node2 + ") no existe. ");
			}
		}
		else {
			logger.warning("Uno o ambos nodos no pertenecen al grafo. ");
		}
	}
	
}
