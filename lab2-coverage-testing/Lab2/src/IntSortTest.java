import org.junit.Test;
import static org.junit.Assert.*;

public class IntSortTest {
	
	@Test
	public void testInsertionSortTest() {
		assertArrayEquals(new int[] {1,2,3,4,5}, IntSort.InsertionSort(new int[] {5,4,3,2,1}));
	}
	@Test
	public void testInsertionSortNullArray() {
		assertArrayEquals(new int[] {}, IntSort.InsertionSort(new int[] {}));
	}
	@Test
	public void testInsertionSortDuplicateArray() {
		assertArrayEquals(new int[] {1,2,3,3,5}, IntSort.InsertionSort(new int[] {5,3,3,2,1}));
	}
	@Test
	public void testCountingSortNullTest() {
		assertArrayEquals(new int[] {}, IntSort.CountingSort(new int[] {}, 5));
	}
	@Test
	public void testCountingSortDuplicateTest() {
		assertArrayEquals(new int[] {1,2,3,4,5,5}, IntSort.CountingSort(new int[] {5,4,3,2,5,1}, 6)); 
	}
	@Test
	public void testCountingSortOneIntTest() {
		assertArrayEquals(new int[] {5}, IntSort.CountingSort(new int[] {5}, 6)); 
	
	}
	

}