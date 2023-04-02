import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ParallelQuickSort {

    private static final ForkJoinPool forkJoinPool = new ForkJoinPool();

    public static void parallelQuickSort(int[] arr) {
        ParallelQuickSortTask task = new ParallelQuickSortTask(arr, 0, arr.length - 1);
        forkJoinPool.invoke(task);
    }

    private static class ParallelQuickSortTask extends RecursiveAction {

        private static final int THRESHOLD = 1000;

        private final int[] arr;
        private final int left;
        private final int right;

        public ParallelQuickSortTask(int[] arr, int left, int right) {
            this.arr = arr;
            this.left = left;
            this.right = right;
        }

        @Override
        protected void compute() {
            if (left >= right) {
                return;
            }
            if (right - left < THRESHOLD) {
                Arrays.sort(arr, left, right + 1);
                return;
            }
            int pivotIndex = partition(arr, left, right);
            ParallelQuickSortTask leftTask = new ParallelQuickSortTask(arr, left, pivotIndex - 1);
            ParallelQuickSortTask rightTask = new ParallelQuickSortTask(arr, pivotIndex + 1, right);
            invokeAll(leftTask, rightTask);
        }

        private int partition(int[] arr, int left, int right) {
            int pivot = arr[right];
            int i = left;
            for (int j = left; j < right; j++) {
                if (arr[j] < pivot) {
                    swap(arr, i, j);
                    i++;
                }
            }
            swap(arr, i, right);
            return i;
        }

        private void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
