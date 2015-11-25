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
	
	/**
	 * Onko solmu lisätty minimikekoon
	 */
	public boolean opened;
	
	/**
	 * Onko solmun tarkastelu päättynyt
	 */
	public boolean closed;
	
	/**
	 * Mistä solmusta tähän solmuun on tultu
	 */
	public Node previous;

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
