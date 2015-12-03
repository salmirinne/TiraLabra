package impl;

import java.util.Arrays;

/**
 * Jump Point Search algoritmin toteutus
 */
public class JumpPointSearch extends PathFinding {
	
	public JumpPointSearch(Grid grid) {
		super(grid, "JPS");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void findPath() {
		
		long startTime = System.currentTimeMillis();
		iterations = 0;
		
		BinaryHeap<Node> openQueue = new BinaryHeap<Node>();
		
		// Alustetaan l‰htˆsolmu ja lis‰t‰‰n se ensimm‰iseksi tutkittavaksi solmuksi
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
			for (Node neighbour : getNeighbours(current)) {
				
				if (neighbour != null && !neighbour.closed) {
					/* Pyrit‰‰n hypp‰‰m‰‰n ruudukossa mahdollisimman pitk‰ matka eteenp‰in kulkusuuntaan n‰hden
					 * (ks. jump-metodi)
					 */
					Node jumpPoint = jump(neighbour.x, neighbour.y, neighbour.x - current.x, neighbour.y - current.y);
					if (jumpPoint == null || jumpPoint.closed) 
						continue;
					
					// Alustetaan tarvittaessa heuristinen et‰isyys maaliin
					if (jumpPoint.heuristic == 0 ) computeHeuristic(jumpPoint);
					
					int dx = Math.abs(jumpPoint.x - current.x);
					int dy = Math.abs(jumpPoint.y - current.y);
					
					/*  Octile distance (lyhin mahdollinen kuljettu et‰isyys kahden solmun v‰lill‰, 
					 *  miss‰ diagonaalinen siirtym‰ sqrt(2); muuten 1
					 */
					float octile = (dx + dy) + (SQRT2 - 2) * Math.min(dx, dy);
					float cost = current.cost + octile;
					
					/* Lis‰t‰‰n tarvittaessa naapuri tarkasteltavien solmujen joukkoon tai p‰ivitet‰‰n 
					 * sen et‰isyysarviota maaliin
					 */
					if (!jumpPoint.opened || jumpPoint.cost > cost) {
						jumpPoint.cost = cost;
						jumpPoint.previous = current;
						if (!jumpPoint.opened) {
							openQueue.insert(jumpPoint);
							jumpPoint.opened = true;
						} else {
							openQueue.decreaseKey(jumpPoint);
						}
					}
					
				}
				
			}
			
		}
		time = System.currentTimeMillis() - startTime;
		System.out.println(debug());
	}
	
	
	/**
	 * Pyrit‰‰n eliminoimaan ruudukon symmetrisyytt‰ mik‰ tarkoittaa, ett‰ yritet‰‰n
	 * hyp‰t‰ ruudukossa kulkusuuntaa pitkin esimerkiksi esteeseen asti. Nyt voidaan olettaa,
	 * ett‰ lyhin polku hyppypisteen ja tarkastellun naapurin v‰lill‰ on yksik‰sitteinen
	 * eik‰ muita saman suorakulmion sis‰lt‰mi‰ polkuja tarvitse tarkastella, kun siirryt‰‰n 
	 * kulkusuuntaan n‰hden seuraavaan suorakulmioon
	 * 
	 * @param x Annetun solmun x-koordinaatti
	 * @param y Annetun solmun y-koordinaatti
	 * @param dx Kulkusuunta x-akselilla
	 * @param dy Kulkusuunta y-akselilla
	 * 
	 * @return Solmu, johon hyp‰t‰‰n
	 */
	public Node jump(int x, int y, int dx, int dy) {
		if (!grid.isWalkable(x, y)) 
			return null;
		if (grid.getNode(x, y).equals(grid.getEnd())) {
			return grid.getEnd();}
		// Diagonaalinen hyppy
		if (dx != 0 && dy != 0) {
			if ((grid.isWalkable(x - dx, y + dy) && !grid.isWalkable(x - dx, y)) ||
		        (grid.isWalkable(x + dx, y - dy) && !grid.isWalkable(x, y - dy)) || 
		         (!grid.isWalkable(x + dx, y) && !grid.isWalkable(x, y + dy))) {
					return grid.getNode(x, y);
		        }
			/* Tarkastellaan rekursiivisesti vaaka- ja pystysuuntaan siirtyminen aina, 
			 * kun siirryt‰‰n diagonaalisesti
			 */
			if (jump(x + dx, y, dx, 0) != null || jump(x, y + dy, 0, dy) != null) {
				return grid.getNode(x, y);
			}
		} else {
			// Hypp‰‰minen vaakasuuntaan
			if (dx != 0) {
				if ((grid.isWalkable(x + dx, y + 1) && !grid.isWalkable(x, y + 1)) ||
			       (grid.isWalkable(x + dx, y - 1) && !grid.isWalkable(x, y - 1))) {
						return grid.getNode(x, y);
			    }
			// Hypp‰‰minen pystysuuntaan
			} else {
				if((grid.isWalkable(x + 1, y + dy) && !grid.isWalkable(x + 1, y)) ||
			       (grid.isWalkable(x - 1, y + dy) && !grid.isWalkable(x - 1, y))) {
						return grid.getNode(x, y);
				}
			}
			
		}
		// Jatketaan hypp‰‰mist‰ eteenp‰in rekursiivisesti
		return jump(x + dx, y + dy, dx, dy);
	}
	
	/**
	 * Palauttaa annetun solmun naapurit, joihin on mahdollista siirty‰ huomioiden kulkusuunta
	 * edellisest‰ solmusta
	 * 
	 * @param node Tarkasteltava solmu
	 * @return Annetun solmun naapurit kulkusuuntaan n‰hden
	 */
	public Node[] getNeighbours(Node node) {
		int i = 0;
		Node[] neighbours = new Node[5];
		if (node.previous != null) {
			int x = node.x;
			int y = node.y;
			// Edellinen solmu ei v‰ltt‰m‰tt‰ ole suoraan vieress‰, joten lasketaan kulkusuunta
			int dx = (x - node.previous.x) / Math.max(Math.abs(x - node.previous.x), 1);
			int dy = (y - node.previous.y) / Math.max(Math.abs(y - node.previous.y), 1);
			// Naapurit diagonaaliseen suuntaan kuljettaessa
			if (dx != 0 && dy != 0) {
				if (grid.isWalkable(x + dx, y)) {
					neighbours[i++] = grid.getNode(x + dx, y);
				}
				if (grid.isWalkable(x, y + dy)) {
					neighbours[i++] =  grid.getNode(x, y + dy);
				}
				if (grid.isWalkable(x + dx, y + dy) && (grid.isWalkable(x, y + dy) || grid.isWalkable(x + dx, y))) {
					neighbours[i++] = grid.getNode(x + dx, y + dy);
				}
				// Pakotetut naapurit
				if (!grid.isWalkable(x - dx, y) && grid.isWalkable(x - dx, y + dy) && grid.isWalkable(x, y + dy)) {
					neighbours[i++] = grid.getNode(x - dx, y + dy);
				}
				if (!grid.isWalkable(x, y - dy) && grid.isWalkable(x + dx, y - dy) && grid.isWalkable(x + dx, y)) {
					neighbours[i++] = grid.getNode(x + dx, y - dy);
				}
			} else {
				// Naapurit pystysuuntaan kuljettaessa
				if (dx == 0) {
					if (grid.isWalkable(x, y + dy)) {
						neighbours[i++] = grid.getNode(x, y + dy);
					}
					// Pakotetut naapurit
					if (!grid.isWalkable(x + 1, y) && grid.isWalkable(x + 1, y + dy) && grid.isWalkable(x, y + dy)) {
						neighbours[i++] = grid.getNode(x + 1, y + dy);
					}
					if (!grid.isWalkable(x - 1, y) && grid.isWalkable(x - 1, y + dy) && grid.isWalkable(x, y + dy)) {
						neighbours[i++] = grid.getNode(x - 1, y + dy);
					}
				// Naapurit vaakasuoraan kuljettaessa	
				} else {
					if (grid.isWalkable(x + dx, y)) {
						neighbours[i++] = grid.getNode(x + dx, y);
					}
					// Pakotetut naapurit
					if (!grid.isWalkable(x, y + 1) && grid.isWalkable(x + dx, y + 1) && grid.isWalkable(x + dx, y)) {
						neighbours[i++] = grid.getNode(x + dx, y + 1);
					}
					if (!grid.isWalkable(x, y - 1) && grid.isWalkable(x + dx, y - 1) && grid.isWalkable(x + dx, y)) {
						neighbours[i++] = grid.getNode(x + dx, y - 1);
					}
				}
			}
		} else {
			return grid.getNeighbours(node);
		}
		return Arrays.copyOf(neighbours, i);
	}
	
}
