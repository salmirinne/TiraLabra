package impl;

/**
 * Kuvastaa yht� ruudukon solmua
 */
public class Node implements Comparable<Node> {
	
	public int x, y;
	
	/**
	 * Kuljettu et�isyys alkusolmusta
	 */
	public float cost;
	
	/**
	 * Heuristinen et�isyys maalisolmuun
	 */
	public float heuristic;
	
	/**
	 * Onko solmuun mahdollista siirty�
	 */
	public boolean blocked;
	
	/**
	 * Onko solmu lis�tty minimikekoon
	 */
	public boolean opened;
	
	/**
	 * Onko solmun tarkastelu p��ttynyt
	 */
	public boolean closed;
	
	/**
	 * Mist� solmusta t�h�n solmuun on tultu
	 */
	public Node previous;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * M��rittelee minimikeon j�rjestyksen t�m�n sek� verrattavan solmun v�lill� kuljetun et�isyyden
	 * ja heuristisen et�isyyden perusteella
	 * 
	 * @param n Verrattava solmu
	 */
	@Override
	public int compareTo(Node n) {
		float local = cost + heuristic;
		float param = n.cost + n.heuristic;
		if (local > param) return 1;
		if (local < param) return -1;
		return 0;
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int result = 7;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Node)) 
			return false;
		Node param = (Node)obj;
		return x == param.x && y == param.y;
	}
	
	@Override
	public String toString() {
		return "x=" + x + " y=" + y + " cost=" + cost + " heuristic=" + heuristic + " blocked=" + blocked;
	}
	
}
