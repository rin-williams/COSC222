import java.util.Arrays;

/**
 * Note that an array of integers are expected
 * 
 * Remember to site sources if you use code from online!
 */
public class IntSort {

	//you may use this to check that your test cases are correct
    public static int[] sort(int[] arr){
    	int[] arrCopy = arr.clone();
        Arrays.sort(arrCopy);
        return arrCopy;
    }

    public static int[] InsertionSort(int[] orgArr){
        //TODO: implement insertion sort as described at https://en.wikipedia.org/wiki/Insertion_sort
    	int[] arr = orgArr.clone();
    	
    	// SORT HERE //
    	for (int i = 1; i < arr.length; i++) {
            int j = i;
            while ((j > 0) && (arr[j-1] > arr[j])) {
                int temp = arr[j];
                arr[j] = arr[j-1];
                arr[j-1] = temp;
                j--;
            }
        }
    	// return arr sorted
        return arr;
    }
    
    // assumes values in array range from [0,numBuckets]
    public static int[] CountingSort(int[] array, int numBuckets){
    //TODO: implement counting sort as described at any of the following links:
	//http://www.personal.kent.edu/~rmuhamma/Algorithms/MyAlgorithms/Sorting/countingSort.htm
	//https://en.wikipedia.org/wiki/Counting_sort
    	// Ensure not to alter the original array
    	
    	//ini vars to match wiki's psuedo so its easier
    	int[] input = array.clone();
    	int k = numBuckets;
    	
    	//begin code
    	
    	int[] count = new int[k + 1];
    	int[] output = new int[input.length];
    	
    	for (int i = 0; i < input.length; i++) {
    		int j = input[i];
    		count[j] = count[j] + 1;
    	}
    	for (int i = 1; i <= k; i++) {
    		count[i] = count[i] + count[i - 1];
    	}
    	for (int i = input.length - 1; i >= 0; i--) {
    		int j = input[i];
    		count[j] = count[j] - 1;
    		output[count[j]] = input[i];
    		
    	}
    	
    	return output;
    }
    //Data structure and sorting algorithms are visualized at https://cmps-people.ok.ubc.ca/ylucet/DS/Algorithms.html
	//in particular see insertion and counting sort

}
