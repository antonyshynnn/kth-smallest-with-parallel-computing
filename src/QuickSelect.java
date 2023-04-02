public class QuickSelect {

    public static int quickSelect(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k > arr.length) {
            return -1;
        }
        return quickSelect(arr, 0, arr.length - 1, k);
    }

    private static int quickSelect(int[] arr, int left, int right, int k) {
        int pivotIndex = partition(arr, left, right);
        if (k - 1 == pivotIndex) {
            return arr[pivotIndex];
        } else if (k - 1 < pivotIndex) {
            return quickSelect(arr, left, pivotIndex - 1, k);
        } else {
            return quickSelect(arr, pivotIndex + 1, right, k);
        }
    }

    private static int partition(int[] arr, int left, int right) {
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

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
