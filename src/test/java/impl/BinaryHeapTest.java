package impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class BinaryHeapTest {
	
	private static final int HEAP_TEST_SIZE = 50;
	
	private BinaryHeap<Object> binaryHeap;
	private List<Integer> orderedValues;

	@Before
	public void setUp() {
		this.binaryHeap = new BinaryHeap<Object>();
		this.orderedValues = new ArrayList<>(HEAP_TEST_SIZE);
	}
	
	private void buildHeap() {
		Random random = new Random();
		for (int i = 0; i < HEAP_TEST_SIZE; i++) {
			Node node = new Node();
			int value = random.nextInt(1000);
			node.value = value;
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
		Node node = new Node();
		node.value = 1;
		binaryHeap.insert(node);
		assertEquals(1, binaryHeap.min().value);
	}
	
	@Test
	public void testInsertOrdering() {
		buildHeap();
		assertEquals((int)orderedValues.get(0), binaryHeap.min().value);
	}
	
	@Test
	public void testDelete() {
		Node node = new Node();
		node.value = 1;
		binaryHeap.insert(node);
		binaryHeap.delete();
		assertEquals(true, binaryHeap.isEmpty());
	}
	
	@Test
	public void testDeleteAndHeapify() {
		buildHeap();
		for (int i = 0; i < HEAP_TEST_SIZE; i++) {
			assertEquals((int)orderedValues.get(i), binaryHeap.delete().value);
		}
		assertEquals(true, binaryHeap.isEmpty());
	}
	
}
