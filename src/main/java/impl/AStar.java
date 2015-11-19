package impl;

import java.util.ArrayList;
import java.util.List;

/**
 * A* algoritmin toteutus
 */
public class AStar {

	private Grid grid;
	private Heuristic heuristic;

	public AStar(Grid grid) {
		this.grid = grid;
	}

	/**
	 * Asettaa A*-algoritmin käyttämän heuristiikkafunktion
	 * 
	 * @param heuristic Heuristiikkafunktio
	 */
	public void setHeuristic(Heuristic heuristic) {
		this.heuristic = heuristic;
	}
	
	/**
	 * Asettaa parametriin heuristiikkafunktion antaman etäisyyden maalisolmuun
	 * 
	 * @param node Asettaa parametriin heuristiikkafunktion antaman etäisyyden maalisolmuun
	 */
	public void computeHeuristic(Node node) {
		node.heuristic = heuristic.getHeuristic(node, grid.getEnd());
	}
	
	/*
	 * Etsii lyhimmän polun ruudukossa alkusolmusta maalisolmuun
	 */
	public void findPath() {
		
		BinaryHeap<Node> openQueue = new BinaryHeap<Node>();
		
		// TODO: tehokkaampi contains(..) toteutus kuin O(n)
		List<Node> closedList = new ArrayList<>();
		
		Node start = grid.getStart();
		start.cost = 0;
		computeHeuristic(start);
		
		openQueue.insert(start);
		
		while (!openQueue.isEmpty()) {
			
			Node current = openQueue.delete();
			if (current.equals(grid.getEnd())) {
				System.out.println("Found path..");
				printPath(current);
				return;
			}
			
			System.out.println("Evaluating node " + current);
			
			closedList.add(current);
			
			for (Node node : grid.getNeighbours(current)) {
				
				if (!closedList.contains(node)) {
					
					if (node.cost == 0) node.cost = current.cost + 1;
					if (node.heuristic == 0 ) computeHeuristic(node);
					
					if (!openQueue.contains(node)) {
						openQueue.insert(node);
						node.previous = current;
					}
					
				}
				
			}
			
		}
		
		System.out.println("Path not found");
		
	}

	/**
	 * Tällä hetkellä apumetodi A* lyhimmän polun etsinnän testaamiseen
	 */
	private void printPath(Node current) {
		System.out.println("Path length: " + (current.cost - 2));
		while (current.compareTo(grid.getStart()) != 0) {
			System.out.println(current);
			current = current.previous;
		}
		System.out.println(current);
	}
	
}
