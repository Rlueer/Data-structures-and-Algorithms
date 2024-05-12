public class MergeSort extends SortAlgorithm {
	
	public MergeSort(int input_array[]) {
		super(input_array);
	}
	
	private void merge(int[] leftArray, int[] rightArray, int[] mergedArray) {
        int leftLength = leftArray.length;
        int rightLength = rightArray.length;

        int i = 0, j = 0, k = 0;

        // Merge leftArray and rightArray into mergedArray
        while (i < leftLength && j < rightLength) {
            if (leftArray[i] <= rightArray[j]) {
                mergedArray[k++] = leftArray[i++];
            } else {
                mergedArray[k++] = rightArray[j++];
            }
            comparison_counter++; // Count comparison when elements are compared
        }

        // Copy remaining elements of leftArray, if any
        while (i < leftLength) {
            mergedArray[k++] = leftArray[i++];
        }

        // Copy remaining elements of rightArray, if any
        while (j < rightLength) {
            mergedArray[k++] = rightArray[j++];
        }
    }

    private void sort(int[] array) {
        int n = array.length;
        if (n < 2) {
            return; // Array of size 1 is already sorted
        }

        int mid = n / 2;
        int[] leftArray = new int[mid];
        int[] rightArray = new int[n - mid];

        // Divide the array into left and right subarrays
        System.arraycopy(array, 0, leftArray, 0, mid);
        System.arraycopy(array, mid, rightArray, 0, n - mid);

        // Recursively sort the left and right subarrays
        sort(leftArray);
        sort(rightArray);

        // Merge the sorted subarrays
        merge(leftArray, rightArray, array);
    }
    
    @Override
    public void sort() {
    	sort(arr);
    }
    
    @Override
    public void print() {
    	System.out.print("Merge Sort\t=>\t");
    	super.print();
    }
}
