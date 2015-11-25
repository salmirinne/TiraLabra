package impl;

/**
 * A* algoritmin toteutus
 */
public class AStar {
	
	private static final float SQRT2 = (float)Math.sqrt(2);

	private Grid grid;
	private Heuristic heuristic;
	private long time;
	private int iterations;

	public AStar(Grid grid) {
		this.grid = grid;
		this.heuristic = Heuristic.EUCLIDEAN;
	}

	/**
	 * Asettaa A*-algoritmin k‰ytt‰m‰n heuristiikkafunktion
	 * 
	 * @param heuristic Heuristiikkafunktio
	 */
	public void setHeuristic(Heuristic heuristic) {
		this.heuristic = heuristic;
	}
	
	/**
	 * Asettaa parametriin heuristiikkafunktion antaman et‰isyyden maalisolmuun
	 * 
	 * @param node Asettaa parametriin heuristiikkafunktion antaman et‰isyyden maalisolmuun
	 */
	public void computeHeuristic(Node node) {
		node.heuristic = heuristic.getHeuristic(node, grid.getEnd());
	}
	
	/**
	 * P‰ivitt‰‰ kuljetun et‰isyyden solmuun sen edelt‰j‰st‰ siten, ett‰ matka v‰li-ilmansuuntiin 
	 * on sqrt(2); muuten 1
	 * 
	 * @param node Mihin solmuun tullaan
	 * @param previous Mist‰ solmusta tultiin
	 */
	private void updateCost(Node node, Node previous) {
		if (node.x - previous.x == 0 || node.y - previous.y == 0)
			node.cost = previous.cost + 1;
		else	
			node.cost = previous.cost + SQRT2;
	}
	
	/**
	 * Palauttaa polunetsint‰‰n kuluneen ajan
	 * 
	 * @return Polunetsint‰‰n kulunut aika
	 */
	public long getTime() {
		return time;
	}
	
	/**
	 * Etsii lyhimm‰n polun ruudukossa alkusolmusta maalisolmuun
	 */
	public void findPath() {
		
		long startTime = System.currentTimeMillis();
		iterations = 0;
		
		BinaryHeap<Node> openQueue = new BinaryHeap<Node>();
		
		// Alustetaan l‰htˆsolmu ja lis‰t‰‰n se ensimm‰iseksi tutkittavaksi solmuksi
		Node start = grid.getStart();
		start.cost = 0;
		computeHeuristic(start);
		
		openQueue.insert(start);
		start.opened = true;
		
		while (!openQueue.isEmpty()) {
			
			Node current = openQueue.delete();
			
			// Lopetetaan jos loppusolmu on tarkastettu
			if (grid.getEnd().closed) {
				time = System.currentTimeMillis() - startTime;
				System.out.println("Found path.. Total iterations=" 
								   + iterations + " Time taken=" + time 
								   + "ms Path length=" + grid.getEnd().cost 
								   + " Heuristic=" + heuristic
								   + " Diagonal=" + grid.isDiagonalAllowed());
								   	
				return;
			}
			
			current.closed = true;
			iterations++;
			
			// Otetaan tarkasteluun naapurisolmut
			for (Node neighbour : grid.getNeighbours(current)) {
				
				if (!neighbour.closed) {
					
					// Alustetaan naapurin et‰isyydet mik‰li siihen tullaan ensimm‰ist‰ kertaa
					if (neighbour.cost == 0) updateCost(neighbour, current);
					if (neighbour.heuristic == 0 ) computeHeuristic(neighbour);
					
					// Lis‰t‰‰n tarvittaessa naapuri tarkasteltavien solmujen joukkoon
					if (!neighbour.opened) {
						openQueue.insert(neighbour);
						neighbour.opened = true;
						neighbour.previous = current;
					}
					
					/* P‰ivitet‰‰n naapurisolmun edelt‰j‰ksi nykyinen solmu jos siihen p‰‰see lyhyemp‰‰
					 * polkua pitkin k‰ytt‰en nykyist‰ solmua ja p‰ivitet‰‰n naapurin muuttunutta
					 * et‰isyysarviota minimikeossa
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
		System.out.println("Path not found.. Total iterations=" 
						   + iterations + " Time taken=" + time 
						   + "ms Path length=" + grid.getEnd().cost 
						   + " Heuristic=" + heuristic
						   + " Diagonal=" + grid.isDiagonalAllowed());
	}
	
}
