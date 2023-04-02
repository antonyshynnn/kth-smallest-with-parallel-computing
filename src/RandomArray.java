import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class RandomArray {

    public static int[] generateRandomArray(int size) {
        return ThreadLocalRandom.current().ints(0, size * 10).distinct().limit(size).toArray();
    }

    public static void saveArrayToFile(int[] arr, String filename) throws IOException {
        String arrayAsString = Arrays.toString(arr);
        Files.write(Paths.get(filename), arrayAsString.getBytes());
    }

    public static int[] readArrayFromFile(String filename) throws IOException {
        byte[] fileBytes = Files.readAllBytes(Paths.get(filename));
        String fileAsString = new String(fileBytes);
        String[] arrayAsString = fileAsString.substring(1, fileAsString.length() - 1).split(", ");
        int[] arr = new int[arrayAsString.length];
        for (int i = 0; i < arrayAsString.length; i++) {
            arr[i] = Integer.parseInt(arrayAsString[i]);
        }
        return arr;
    }
}
