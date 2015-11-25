package impl;

import java.util.function.BiFunction;

/**
 * Lyhimmän polunetsinnän käyttämä heuristiikkafunktio
 */
public enum Heuristic {
	
	/**
	 * Lyhin etäisyys maalisolmuun suorinta tietä (linnuntietä)
	 */
	EUCLIDEAN((n1, n2) -> {
		int dx = Math.abs(n1.x - n2.x);
		int dy = Math.abs(n1.y - n2.y);
		return (float)Math.sqrt(dx * dx + dy * dy);
	}),
	
	/**
	 * Lyhin etäisyys maalisolmuun kulkemalla ylös/alas tai vasemmalle/oikealle
	 */
	MANHATTAN((n1, n2) -> {
		int dx = Math.abs(n1.x - n2.x);
		int dy = Math.abs(n1.y - n2.y);
		return (float)(dx + dy);
	}),
	
	/**
	 * Lyhin etäisyys maalisolmuun siten, että matka jokaiseen naapurisolmuun on yhtä suuri;
	 * vrt. kuninkaan liikkuminen shakkilaudalla
	 */
	CHEBYSHEV((n1, n2) -> {
		int dx = Math.abs(n1.x - n2.x);
		int dy = Math.abs(n1.y - n2.y);
		return (float)(Math.max(dx, dy));
	}),
	
	/**
	 * Heuristiikkafunktiota ei oteta huomioon (Dijkstran algoritmi)
	 */
	DIJKSTRA((n1, n2) -> {
		return 0f;
	});
	
	private BiFunction<Node, Node, Float> heuristic;
	
	private Heuristic(BiFunction<Node, Node, Float> heuristic) {
		this.heuristic = heuristic;
	}

	/**
	 * Palauttaa heuristiikkafunktion tuloksen
	 * 
	 * @param n1 Ensimmäinen solmu
	 * @param n2 Toinen solmu
	 * @return Heuristiikkafunktion tulos annettujen solmujen välillä
	 */
	public float getHeuristic(Node n1, Node n2) {
		return heuristic.apply(n1, n2);
	}

}
