package impl;

/**
 * Kuvastaa yhtä ruudukon solmua
 */
public class Node implements Comparable<Node> {
	
	public int x, y;
	
	/**
	 * Kuljettu etäisyys alkusolmusta
	 */
	public float cost;
	
	/**
	 * Heuristinen etäisyys maalisolmuun
	 */
	public float heuristic;
	
	/**
	 * Onko solmuun mahdollista siirtyä
	 */
	public boolean blocked;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Määrittelee minimikeon järjestyksen tämän sekä verrattavan solmun välillä kuljetun etäisyyden
	 * ja heuristisen etäisyyden perusteella
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
