package impl;

import java.util.Arrays;

/**
 * Minimikeon toteutus
 *
 * @param <T> Lis�tt�v�n solmun tyyppi
 */
public class BinaryHeap<T extends Comparable<T>> {

	private T[] table;
	private int size;
	
	public BinaryHeap() {
		this.table = (T[])new Comparable[16];
	}
	
	/**
	 * Alustava toteutus sille l�ytyyk� keosta annettua elementti� (O(n))
	 * 
	 * @param obj Verrattava elementti
	 * @return true, jos elementti l�ytyy keosta
	 */
	public boolean contains(T obj) {
		for (T t : table) {
			if (t == null) return false;
			if (t.compareTo(obj) == 0) return true;
		}
		return false;
	}
	
	/**
	 * Tarkistaa onko keossa elementtej�
	 * 
	 * @return true, jos keko on tyhj�
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Palauttaa keon pienimm�n arvon omaavan solmun
	 * 
	 * @return Keon pienimm�n arvon omaava solmu
	 */
	public T min() {
		return table[0];
	}
	
	/**
	 * Palauttaa keon pienimm�n arvon omaavan solmun ja poistaa sen keosta
	 * 
	 * @return Pienimm�n arvon omaava solmu tai null, jos keko on tyhj�
	 */
	public T delete() {
		if (isEmpty()) {
			return null;
		}
		T min = min();
		table[0] = table[--size];
		table[size] = null;
		heapify();
		return min;
	}
	
	/**
	 * Lis�� kekoon uuden solmun ja vie sen oikealle paikalleen kekoehdon mukaisesti
	 * 
	 * @param node Lis�tt�v� solmu
	 */
	public void insert(T node) {
		table[size] = node;
		int index = size++;
		// kasvattaa tarvittaessa keon kokoa kaksinkertaistamalla sen
		if (size >= table.length) {
			table = Arrays.copyOf(table, table.length * 2);
		}
		// vaihtaa vanhemman ja lapsen paikkaa mik�li lapsen arvo on pienempi
		while (index > 0 && parent(index).compareTo(table[index]) > 0) {
            swap(index, parentIndex(index));
            index = parentIndex(index);
        }     
	}
	
	/**
	 * Deleten apumetodi, joka yll�pit�� kekoehtoa
	 */
	private void heapify() {
		int i = 0;
		// vasen lapsi on olemassa
		while (leftChildIndex(i) > 0) {
			int smallestIndex = i;
			// vasemmalla lapsella on pienempi arvo
			if (left(i).compareTo(table[i]) < 0) {
				smallestIndex = leftChildIndex(i);
			}
			// oikea lapsi on olemassa ja sill� on pienempi arvo
			if (rightChildIndex(i) > 0 && right(i).compareTo(table[smallestIndex]) < 0) {
				smallestIndex = rightChildIndex(i);
			}
			// jommalla kummalla lapsista on pienempi arvo
			if (smallestIndex != i) {
				swap(i, smallestIndex);
				i = smallestIndex;
			} else {
				break;
			}
		}
	}
	
	private void swap(int index1, int index2) {
		T temp = table[index1];
		table[index1] = table[index2];
		table[index2] = temp;
	}
	
	private T parent(int i) {
		return table[(i - 1) / 2];
	}
	
	private int parentIndex(int i) {
		int index = (i - 1) / 2;
		return index < size ? index : -1;
	}
	
	private T left(int i) {
		return table[2 * i + 1];
	}
	
	private int leftChildIndex(int i) {
		int index = 2 * i + 1;
		return index < size ? index : -1;
	}
	
	private T right(int i) {
		return table[2 * i + 2];
	}
	
	private int rightChildIndex(int i) {
		int index = 2 * i + 2;
		return index < size ? index : -1;
	}
	
}
