package impl;

/**
 * A* algoritmin toteutus
 */
public class AStar extends PathFinding {

	public AStar(Grid grid) {
		super(grid, "A*");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void findPath() {
		
		long startTime = System.currentTimeMillis();
		iterations = 0;
		
		BinaryHeap<Node> openQueue = new BinaryHeap<Node>();
		
		// Alustetaan l�ht�solmu ja lis�t��n se ensimm�iseksi tutkittavaksi solmuksi
		Node start = grid.getStart();
		start.cost = 0;
		
		openQueue.insert(start);
		start.opened = true;
		
		while (!openQueue.isEmpty()) {
			
			Node current = openQueue.delete();
			current.closed = true;
			
			// Lopetetaan jos loppusolmu on tarkastettu
			if (grid.getEnd().closed) {
				time = System.currentTimeMillis() - startTime;
				System.out.println(debug());
				return;
			}
			
			iterations++;
			
			// Otetaan tarkasteluun naapurisolmut
			for (Node neighbour : grid.getNeighbours(current)) {
				
				if (!neighbour.closed) {
					
					// Alustetaan tarvittaessa heuristinen et�isyys maaliin
					if (neighbour.heuristic == 0 ) computeHeuristic(neighbour);
					
					float cost = current.cost + getCost(current, neighbour);
					
					/* Lis�t��n tarvittaessa naapuri tarkasteltavien solmujen joukkoon tai p�ivitet��n 
					 * sen et�isyysarviota maaliin
					 */
					if (!neighbour.opened || neighbour.cost > cost) {
						neighbour.cost = cost;
						neighbour.previous = current;
						if (!neighbour.opened) {
							openQueue.insert(neighbour);
							neighbour.opened = true;
						} else {
							openQueue.decreaseKey(neighbour);
						}
					}
					
				}
				
			}
			
		}
		time = System.currentTimeMillis() - startTime;
		System.out.println(debug());
	}
	
	private float getCost(Node node, Node previous) {
		return (node.x - previous.x == 0 || node.y - previous.y == 0) ? 1f : SQRT2;
	}
	
}
