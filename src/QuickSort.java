public class QuickSort implements Sorting {

    private final int[] array;

    public QuickSort(int[] array) {
        this.array = array;
    }

    @Override
    public void sort() {
        System.out.print("Unsorted: [");
        for (int i = 0;i < array.length - 1;i++) {
            System.out.print(array[i] + ",");
        }
        System.out.print(array[array.length - 1] + "]\n");
        long start = System.nanoTime();
        quickSort(0, array.length - 1);
        long end = System.nanoTime();
        System.out.print("Sorted: [");
        for (int i = 0;i < array.length - 1;i++) {
            System.out.print(array[i] + ",");
        }
        System.out.print(array[array.length - 1] + "]\n");
        System.out.println("Quick sort execution time = " + (end - start) + " ns");
        System.out.println("------------------------------------------------------------------------");
    }

    private void quickSort(int left, int right) {
        int pivot, j, temp, i;
        if (left < right) {
            pivot = left;
            i = left;
            j = right;
            while (i < j) {
                while (array[i] < array[pivot] && i < right)
                    i += 1;
                while (array[j] >= array[pivot] && j > left)
                    j -= 1;
                if (i < j) {
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
            temp = array[pivot];
            array[pivot] = array[j];
            array[j] = temp;
            quickSort(left, j - 1);
            quickSort(j + 1, right);
        }
    }
}