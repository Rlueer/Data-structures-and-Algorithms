public class QuickSort extends SortAlgorithm {

	public QuickSort(int input_array[]) {
		super(input_array);
	}
	
    private int partition(int low, int high) {
        // Choosing the last element as the pivot
        int pivot = arr[high];
        int i = low - 1; // Index of smaller element

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(i, j);
            }
            comparison_counter++;
        }

        swap(i + 1, high); // Place pivot in correct position
        return i + 1;
    }

    private void sort(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);

            // Recursively sort elements before and after partition
            sort(low, pi - 1);
            sort(pi + 1, high);
        }
    }

    @Override
    public void sort() {
    	sort(0, arr.length - 1);
    }

    @Override
    public void print() {
    	System.out.print("Quick Sort\t=>\t");
    	super.print();
    }
}
