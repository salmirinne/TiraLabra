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
	
}
