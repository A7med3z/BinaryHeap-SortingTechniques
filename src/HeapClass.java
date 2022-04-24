public class HeapClass {
    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap(10001);
        int a = 5000;
        Sorting quickSort = new QuickSort(new ArrayGenerator(a).generate());
        Sorting insertionSort = new InsertionSort(new ArrayGenerator(a).generate());
        Sorting mergeSort = new MergeSort(new ArrayGenerator(a).generate());
        maxHeap.heapSort(new ArrayGenerator(a).generate());
        quickSort.sort();
        insertionSort.sort();
        mergeSort.sort();
    }
}
