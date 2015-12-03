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
		
		// Alustetaan lähtösolmu ja lisätään se ensimmäiseksi tutkittavaksi solmuksi
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
					
					// Alustetaan naapurin etäisyydet mikäli siihen tullaan ensimmäistä kertaa
					if (neighbour.cost == 0) updateCost(neighbour, current);
					if (neighbour.heuristic == 0 ) computeHeuristic(neighbour);
					
					// Lisätään tarvittaessa naapuri tarkasteltavien solmujen joukkoon
					if (!neighbour.opened) {
						openQueue.insert(neighbour);
						neighbour.opened = true;
						neighbour.previous = current;
					}
					
					/* Päivitetään naapurisolmun edeltäjäksi nykyinen solmu jos siihen pääsee lyhyempää
					 * polkua pitkin käyttäen nykyistä solmua ja päivitetään naapurin muuttunutta
					 * etäisyysarviota minimikeossa
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
