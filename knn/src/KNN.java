import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class KNN {

    private double distance(double[] a, double[] b) {
        double sum = 0;
        for (int i = 0; i < a.length; i++)
            sum += Math.pow(a[i] - b[i], 2);
        return Math.sqrt(sum);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("iris.txt"));
        ReadData readData = new ReadData(sc);
        Map<Kl, Data> inputData = readData.readData();
        inputData.forEach((key, value) -> System.out.println(key + " ---- " + value));
    }
}
