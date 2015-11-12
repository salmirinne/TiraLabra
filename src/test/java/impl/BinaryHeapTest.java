package impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class BinaryHeapTest {
	
	private static final int HEAP_TEST_SIZE = 50;
	
	private BinaryHeap<Integer> binaryHeap;
	private List<Integer> orderedValues;

	@Before
	public void setUp() {
		this.binaryHeap = new BinaryHeap<Integer>();
		this.orderedValues = new ArrayList<>(HEAP_TEST_SIZE);
	}
	
	private void buildHeap() {
		Random random = new Random();
		for (int i = 0; i < HEAP_TEST_SIZE; i++) {
			int value = random.nextInt(1000);
			Integer node = new Integer(value);
			binaryHeap.insert(node);
			orderedValues.add(value);
		}
		orderedValues.sort((v1, v2) -> v1.compareTo(v2));
	}
	
	@Test
	public void testInitialisation() {
		assertEquals(true, binaryHeap.isEmpty());
	}
	
	@Test
	public void testInsert() {
		Integer node = new Integer(1);
		binaryHeap.insert(node);
		assertEquals(new Integer(1), binaryHeap.min());
	}
	
	@Test
	public void testInsertOrdering() {
		buildHeap();
		assertEquals(orderedValues.get(0), binaryHeap.min());
	}
	
	@Test
	public void testDelete() {
		Integer node = new Integer(1);
		binaryHeap.insert(node);
		binaryHeap.delete();
		assertEquals(true, binaryHeap.isEmpty());
	}
	
	@Test
	public void testDeleteAndHeapify() {
		buildHeap();
		for (int i = 0; i < HEAP_TEST_SIZE; i++) {
			assertEquals(orderedValues.get(i), binaryHeap.delete());
		}
		assertEquals(true, binaryHeap.isEmpty());
	}
	
}
