import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) throws IOException {
        int arraySize = 10_000_000;
        System.out.println("Array size is: " + Formatter.format(arraySize));
        RandomArray.saveArrayToFile(RandomArray.generateRandomArray(arraySize), "randomArray.txt");
        int[] arrayFromFile = RandomArray.readArrayFromFile("randomArray.txt");

        int randomKthElementKey = ThreadLocalRandom.current().nextInt(1, arraySize + 1);

        benchmarkQuickSort(arrayFromFile.clone(), randomKthElementKey);
        benchmarkParallelQuickSort(arrayFromFile.clone(), randomKthElementKey);
        benchmarkQuickSelect(arrayFromFile.clone(), randomKthElementKey);
        benchmarkJavaParallelSort(arrayFromFile.clone(), randomKthElementKey);
    }

    public static void benchmarkQuickSelect(int[] uniqueArray, int kthElementKey) {
        long millis = System.nanoTime();
        int kthElementValue = QuickSelect.quickSelect(uniqueArray.clone(), kthElementKey);

        System.out.printf("%d-th smallest element in unsorted array %s is: %d \n", kthElementKey, kthElementKey < 100 ? Arrays.toString(uniqueArray) : "", +kthElementValue);
        System.out.printf("QuickSelect was completed in %s nanoSeconds\n\n", Formatter.format(System.nanoTime() - millis));
    }

    public static void benchmarkQuickSort(int[] uniqueArray, int kthElementKey) {
        long millis = System.nanoTime();
        QuickSort.quickSort(uniqueArray);
        int kthElementValue = uniqueArray[kthElementKey - 1];

        System.out.printf("%d-th smallest element in unsorted array %s is: %d \n", kthElementKey, kthElementKey < 100 ? Arrays.toString(uniqueArray) : "", +kthElementValue);
        System.out.printf("QuickSort was completed in %s nanoSeconds\n\n", Formatter.format(System.nanoTime() - millis));
    }

    public static void benchmarkParallelQuickSort(int[] uniqueArray, int kthElementKey) {
        long millis = System.nanoTime();
        ParallelQuickSort.parallelQuickSort(uniqueArray);
        int kthElementValue = uniqueArray[kthElementKey - 1];

        System.out.printf("%d-th smallest element in unsorted array %s is: %d \n", kthElementKey, kthElementKey < 100 ? Arrays.toString(uniqueArray) : "", +kthElementValue);
        System.out.printf("Parallel QuickSort was completed in %s nanoSeconds\n\n", Formatter.format(System.nanoTime() - millis));
    }

    public static void benchmarkJavaParallelSort(int[] uniqueArray, int kthElementKey) {
        long millis = System.nanoTime();
        Arrays.parallelSort(uniqueArray);
        int kthElementValue = uniqueArray[kthElementKey - 1];

        System.out.printf("%d-th smallest element in unsorted array %s is: %d \n", kthElementKey, kthElementKey < 100 ? Arrays.toString(uniqueArray) : "", +kthElementValue);
        System.out.printf("Java ParallelSort was completed in %s nanoSeconds\n\n", Formatter.format(System.nanoTime() - millis));
    }
}
