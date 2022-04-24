public class MaxHeap {
    private final int[] HeapArray;
    private int size;

    public MaxHeap(int heapSize) {
        HeapArray = new int[heapSize];
        this.size = 0;
    }

    private int leftChild(int parent) {
        return 2 * parent;
    }

    private int rightChild(int parent) {
        return 2 * parent + 1;
    }

    private int parent(int child) {
        return child / 2;
    }

    void heapifyDown(int nodePos) {
        if (nodePos > size / 2) return;
        if (HeapArray[nodePos] <= HeapArray[leftChild(nodePos)] || HeapArray[nodePos] <= HeapArray[rightChild(nodePos)]) {
            if (HeapArray[rightChild(nodePos)] <= HeapArray[leftChild(nodePos)]) {
                Swap(nodePos, leftChild(nodePos));
                heapifyDown(leftChild(nodePos));
            } else if (HeapArray[leftChild(nodePos)] <= HeapArray[rightChild(nodePos)]) {
                Swap(nodePos, rightChild(nodePos));
                heapifyDown(rightChild(nodePos));
            }
        }
    }

    void heapifyUp(int nodePos) {
        if (nodePos <= 1) return;
        if (HeapArray[nodePos] >= HeapArray[parent(nodePos)]) {
            Swap(nodePos, parent(nodePos));
            heapifyUp(parent(nodePos));
        }
    }

    void Swap(int v1, int v2) {
        if (v1 == v2) return;
        int temp = HeapArray[v1];
        HeapArray[v1] = HeapArray[v2];
        HeapArray[v2] = temp;
    }

    int[] buildHeap(int[] array) {
        for (int i = 0; i < array.length; i++) {
            maxHeap_insert(array[i]);
        }
        return HeapArray;
    }

    void maxHeap_insert(int value) {
        HeapArray[size + 1] = value;
        heapifyUp(size + 1);
        size += 1;
    }

    void maxHeap_extract() {
        Swap(1, size);
        HeapArray[size] = 0;
        size -= 1;
        heapifyDown(1);
    }

    public int[] H_array() {
        return HeapArray;
    }

    int[] heapSort(int[] array) {
        System.out.print("Unsorted: [");
        for (int i = 0; i < array.length - 1; i++) {
            System.out.print(array[i] + ",");
        }
        System.out.print(array[array.length - 1] + "]\n");
        long start = System.nanoTime();
        int[] sorted = new int[array.length];
        buildHeap(array);
        for (int i = 0; i < array.length; i++) {
            Swap(1, size);
            sorted[array.length - (i + 1)] = HeapArray[size];
            HeapArray[size] = 0;
            size -= 1;
            heapifyDown(1);
        }
        long end = System.nanoTime();
        System.out.print("Sorted: [");
        for (int i = 0; i < array.length - 1; i++) {
            System.out.print(sorted[i] + ",");
        }
        System.out.print(sorted[array.length - 1] + "]\n");
        System.out.println("Heap sort execution time = " + (end - start) + " ns");
        System.out.println("------------------------------------------------------------------------");
        return sorted;
    }

    void printHeap() {
        System.out.print("Heap array: [");
        for (int i = 1; i < size; i++) {
            System.out.print(HeapArray[i] + ",");
        }
        System.out.print(HeapArray[size] + "]\n");
    }
}
