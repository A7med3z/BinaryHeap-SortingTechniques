public class InsertionSort implements Sorting {

    private final int[] array;

    public InsertionSort(int[] array) {
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
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int pos = i;
            while (pos > 0 && temp < array[pos - 1]) {
                array[pos] = array[pos - 1];
                pos -= 1;
            }
            array[pos] = temp;
        }
        long end = System.nanoTime();
        System.out.print("Sorted: [");
        for (int i = 0;i < array.length - 1;i++) {
            System.out.print(array[i] + ",");
        }
        System.out.print(array[array.length - 1] + "]\n");
        System.out.println("Insertion sort execution time = " + (end - start) + " ns");
        System.out.println("------------------------------------------------------------------------");
    }
}
