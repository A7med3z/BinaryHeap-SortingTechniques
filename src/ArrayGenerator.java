public class ArrayGenerator {

    int n;

    public ArrayGenerator(int n) {
        this.n = n;
    }

    int[] generate() {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * Math.max(400, 10 * n));
        }
        return arr;
    }
}
