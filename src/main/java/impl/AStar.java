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
					
					// Alustetaan naapurin et�isyydet mik�li siihen tullaan ensimm�ist� kertaa
					if (neighbour.cost == 0) updateCost(neighbour, current);
					if (neighbour.heuristic == 0 ) computeHeuristic(neighbour);
					
					// Lis�t��n tarvittaessa naapuri tarkasteltavien solmujen joukkoon
					if (!neighbour.opened) {
						openQueue.insert(neighbour);
						neighbour.opened = true;
						neighbour.previous = current;
					}
					
					/* P�ivitet��n naapurisolmun edelt�j�ksi nykyinen solmu jos siihen p��see lyhyemp��
					 * polkua pitkin k�ytt�en nykyist� solmua ja p�ivitet��n naapurin muuttunutta
					 * et�isyysarviota minimikeossa
					 */
					if (neighbour.previous.cost >= current.cost) {
						neighbour.previous = current;
						updateCost(neighbour, current);
						openQueue.decreaseKey(neighbour);
					}
					
				}
				
			}
			
		}
		time = System.currentTimeMillis() - startTime;
		System.out.println(debug());
	}
	
}
