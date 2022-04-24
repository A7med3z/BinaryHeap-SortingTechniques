public class MergeSort implements Sorting {

    private final int[] array;

    public MergeSort(int[] array) {
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
        mergeSort(0, array.length - 1);
        long end = System.nanoTime();
        System.out.print("Sorted: [");
        for (int i = 0;i < array.length - 1;i++) {
            System.out.print(array[i] + ",");
        }
        System.out.print(array[array.length - 1] + "]\n");
        System.out.println("Merge sort execution time = " + (end - start) + " ns");
        System.out.println("------------------------------------------------------------------------");
    }

    private void mergeSort(int low, int high) {
        int mid;
        if (low < high) {
            mid = (low + high) / 2;
            mergeSort(low, mid);
            mergeSort(mid + 1, high);
            merge(low, mid, high);
        }
    }

    private void merge(int low, int mid, int high) {
        int i, l, r, k;
        int[] temp = new int[array.length];
        l = low;
        r = mid + 1;
        i = low;
        while ((l <= mid) && (r <= high)) {
            if (array[l] <= array[r]) {
                temp[i] = array[l];
                l += 1;
            } else {
                temp[i] = array[r];
                r += 1;
            }
            i += 1;
        }
        if (l > mid) {
            for (k = r; k <= high; k++) {
                temp[i] = array[k];
                i += 1;
            }
        } else {
            for (k = l; k <= mid; k++) {
                temp[i] = array[k];
                i += 1;
            }
        }
        for (k = low; k <= high; k++) {
            array[k] = temp[k];
        }
    }
}
