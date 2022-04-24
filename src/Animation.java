import java.awt.*;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;

class HeapSortWorker extends JPanel {
    private final static int BAR_WIDTH = 30;
    private final static int BAR_HEIGHT_MAX = 400;
    private final boolean everySwap;
    private int[] items;

    public HeapSortWorker(int[] items, boolean everySwap) {
        this.items = items;
        this.everySwap = everySwap;
    }

    private static void createAndShowGUI() {
        HeapSortWorker heapSort = new HeapSortWorker(new ArrayGenerator(50).generate(), true);
        JButton generate = new JButton("Generate Data");
        generate.addActionListener((e) -> heapSort.setItems(new ArrayGenerator(50).generate()));
        JButton sort = new JButton("Sort Data");
        sort.addActionListener((e) -> heapSort.sort());
        JPanel bottom = new JPanel();
        bottom.add(generate);
        bottom.add(sort);
        JFrame frame = new JFrame("Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(heapSort, BorderLayout.CENTER);
        frame.add(bottom, BorderLayout.PAGE_END);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        EventQueue.invokeLater(() -> createAndShowGUI());
    }

    public void setItems(int[] items) {
        this.items = items;
        repaint();
    }

    public void sort() {
        new SortWorker(items).execute();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < items.length; i++) {
            int x = i * BAR_WIDTH;
            int y = getHeight() - items[i];
            g.setColor(Color.RED);
            g.fillRect(x, y, BAR_WIDTH, items[i]);
            g.setColor(Color.BLUE);
            g.drawString("" + items[i], x, y);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(items.length * BAR_WIDTH, BAR_HEIGHT_MAX + 20);
    }

    class SortWorker extends SwingWorker<Void, int[]> {
        private final int[] items;
        MaxHeap maxHeap = new MaxHeap(1001);

        public SortWorker(int[] unsortedItems) {
            items = Arrays.copyOf(unsortedItems, unsortedItems.length);
        }

        @Override
        protected Void doInBackground() {
            heapSort(items);
            return null;
        }

        int[] heapSort(int[] array) {
            int size = items.length;
            int[] sorted = new int[size];
            maxHeap.buildHeap(array);
            publish(Arrays.copyOf(maxHeap.H_array(), items.length));
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
            for (int i = 0; i < array.length; i++) {
                maxHeap.Swap(1, size);
                sorted[i] = maxHeap.H_array()[size];
                System.out.println(maxHeap.H_array()[size]);
                maxHeap.H_array()[size] = 0;
                maxHeap.heapifyDown(1);
                if (everySwap) {
                    publish(Arrays.copyOf(maxHeap.H_array(), items.length - i));
                    try {
                        Thread.sleep(300);
                    } catch (Exception e) {
                    }
                    int[] a = Arrays.copyOfRange(sorted, 0, i + 1);
                    publish(Arrays.copyOf(a, a.length));
                    try {
                        Thread.sleep(300);
                    } catch (Exception e) {
                    }
                }
                size -= 1;
            }
            return sorted;
        }

        @Override
        protected void process(List<int[]> list) {
            int[] items = list.get(list.size() - 1);
            setItems(items);
        }

        @Override
        protected void done() {
        }
    }
}